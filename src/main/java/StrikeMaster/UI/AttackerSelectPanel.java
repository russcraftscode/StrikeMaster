package StrikeMaster.UI;

import StrikeMaster.ImageManager;
import StrikeMaster.UnitManager;
import StrikeMaster.Units.Unit;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class AttackerSelectPanel extends UnitSelectPanel{
    private ArrayList<AttackSingleUnitPanel> attackSingleUnitPanels ;

    protected void buildUnitPanel(){
        attackSingleUnitPanels = new ArrayList<>();
        this.panelLabel.setText("Select Unit to make attack");

        unitDataPanel.setLayout(new GridBagLayout());
        GridBagConstraints gloc = new GridBagConstraints();
        gloc.insets = new Insets(2, 0, 1, 0);
        gloc.gridx = 0;
        gloc.gridy = 0;
        gloc.fill = GridBagConstraints.HORIZONTAL;
        gloc.weightx = 1;

        for (int idNum = 0; idNum < UnitManager.getUnitCount(); idNum++) {
            AttackSingleUnitPanel attackSingleUnitPanel =
                    new AttackSingleUnitPanel(UnitManager.getUnit(idNum));
            // add the single unit panel to the main panel
            unitDataPanel.add(attackSingleUnitPanel, gloc);
            gloc.gridy++;
            // add the single unit panel to the list of single unit panels
            attackSingleUnitPanels.add(attackSingleUnitPanel);
        }

        // set the default selection to the first  sub panel  to prevent no-unit selected errors
        attackSingleUnitPanels.get(0).setSelected(true);

        editUnitButton.addActionListener(e -> {
            EditUnitPopup editUnitPopup = new EditUnitPopup(UnitManager.getSelectedAttacker());
        });
    }

    /**
     * Updates all unit entries to reflect any changes to unit objects.
     */
    public void updateUnits() {
        for(AttackSingleUnitPanel singlePanel : attackSingleUnitPanels){
            singlePanel.updateGraphics();
        }
    }


    /**
     * Subclass of SingleUnitPanel that represents a single unit that is making an attack.
     */
    class AttackSingleUnitPanel extends JPanel {
        private Unit unit;
        private JRadioButton unitSelectButton = new JRadioButton();
        private JLabel iconLabel = new JLabel();
        private JLabel idLabel = new JLabel();
        private JLabel sideLabel = new JLabel();
        private JLabel nameLabel = new JLabel();
        private JLabel damageLabel = new JLabel();
        private JLabel wepSysHitsLabel = new JLabel("Wep Damage");
        private JLabel wepSysHitsImage = new JLabel();
        private JLabel tarSysHitsLabel = new JLabel("FC Damage");
        private JLabel tarSysHitsImage = new JLabel();


        /**
         * Gets the unit that this panel represents.
         * @return the unit this panel represents
         */
        public Unit getUnit() {
            return unit;
        }

        /**
         * Creates a panel that represents a single unit to go onto a
         * UnitSelectPanel
         * @param unit the unit this panel will represent
         */
        public AttackSingleUnitPanel(Unit unit){
            this.unit = unit;

            this.setLayout(new GridBagLayout());
            GridBagConstraints aLoc = new GridBagConstraints();
            aLoc.insets = new Insets(0,1,0,2);

            aLoc.gridx = 0;
            aLoc.gridy = 0;
            aLoc.fill = GridBagConstraints.BOTH;
            aLoc.anchor = GridBagConstraints.WEST;
            this.add(unitSelectButton, aLoc);
            unitSelectGroup.add(unitSelectButton); // add button to parent panel's group
            //unitSelectButton.setBackground(Color.GREEN);// DEBUG

            aLoc.gridy = 1;
            idLabel.setHorizontalAlignment(SwingConstants.CENTER);
            this.add(idLabel, aLoc);
            //idLabel.setBorder(BorderFactory.createLineBorder(Color.GREEN));// DEBUG

            aLoc.gridy = 1;
            aLoc.gridx = 1;
            this.add(iconLabel, aLoc);
            //iconLabel.setBorder(BorderFactory.createLineBorder(Color.GREEN));// DEBUG

            aLoc.gridy = 0;
            this.add(sideLabel, aLoc);
            //sideLabel.setBorder(BorderFactory.createLineBorder(Color.GREEN));// DEBUG

            aLoc.gridx = 2;
            aLoc.gridwidth = 2;
            this.add(nameLabel, aLoc);
            //nameLabel.setBorder(BorderFactory.createLineBorder(Color.GREEN));// DEBUG

            aLoc.gridx = 4;
            this.add(damageLabel, aLoc);
            //damageLabel.setBorder(BorderFactory.createLineBorder(Color.GREEN));// DEBUG

            aLoc.gridy = 1;
            aLoc.gridx = 2;
            aLoc.gridwidth = 1;
            this.add(wepSysHitsLabel, aLoc);
            //wepSysHitsLabel.setBorder(BorderFactory.createLineBorder(Color.GREEN));// DEBUG

            aLoc.gridwidth = 1;
            aLoc.gridx = 3;
            this.add(wepSysHitsImage, aLoc);
            //wepSysHitsImage.setBorder(BorderFactory.createLineBorder(Color.GREEN));// DEBUG

            aLoc.gridx = 4;
            this.add(tarSysHitsLabel, aLoc);
            //tarSysHitsLabel.setBorder(BorderFactory.createLineBorder(Color.GREEN));// DEBUG

            aLoc.gridx = 5;
            this.add(tarSysHitsImage, aLoc);
            //tarSysHitsImage.setBorder(BorderFactory.createLineBorder(Color.GREEN));// DEBUG

            // tell UnitManger a new unit has been selected
            this.unitSelectButton.addActionListener(s->
            {
                UnitManager.changeSelectedAttacker(unit.getID());
            });

            this.updateGraphics();
        }

        /**
         * Updates all UI elements to reflect any changes to the unit
         */
        public void updateGraphics(){
            nameLabel.setText(unit.getName() + " " + unit.getVariant());
            sideLabel.setText("Red");
            idLabel.setText(String.valueOf(unit.getID()));
            damageLabel.setText("  Attack Strength "
                    + unit.getShortDmg() + "/"
                    + unit.getMedDmg() + "/"
                    + unit.getLongDmg());
            switch (unit.getType()) {
                // TODO add other unit types
                default:
                    iconLabel.setIcon(ImageManager.getInfIcon());
                    break;
                case 'm':
                    iconLabel.setIcon(ImageManager.getMechIcon());
                    break;
            }
            wepSysHitsImage.setIcon(new ImageIcon(
                    ImageManager.getCounterImage(4, 4- unit.getWepHits())));
            tarSysHitsImage.setIcon(new ImageIcon(
                    ImageManager.getCounterImage(4, 4- unit.getFCHits())));

            // shade every other line
            if(unit.getID()%2 == 0){
                this.setBackground(Color.LIGHT_GRAY);
                unitSelectButton.setBackground(Color.LIGHT_GRAY);
                this.setOpaque(true);
            }

            this.revalidate();
            this.repaint();
        }

        /**
         * Sets the radio button for this unit.
         * @param sel if the unit should be selected.
         */
        public void setSelected(boolean sel){
            unitSelectButton.setSelected(sel);
            // tell UnitManger a new unit has been selected
            UnitManager.changeSelectedAttacker(unit.getID());
        }

        /**
         * Returns if this panel is selected.
         * @return true if the unit this panel represents is the selected unit
         */
        public boolean isSelected(){
            return unitSelectButton.isSelected();
        }
    }

}
