package StrikeMaster.UI;

import StrikeMaster.ImageManager;
import StrikeMaster.UnitManager;
import StrikeMaster.Units.Unit;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class TargetSelectPanel extends UnitSelectPanel {
    private ArrayList<TargetSingleUnitPanel> targetSingleUnitPanels;

    protected void buildUnitPanel() {
        targetSingleUnitPanels = new ArrayList<>();

        this.panelLabel.setText("Select Unit to target");

        unitDataPanel.setLayout(new GridBagLayout());
        GridBagConstraints gloc = new GridBagConstraints();
        gloc.insets = new Insets(2, 0, 1, 0);
        gloc.gridx = 0;
        gloc.gridy = 0;
        gloc.fill = GridBagConstraints.HORIZONTAL;
        gloc.weightx = 1;

        for (int idNum = 0; idNum < UnitManager.getUnitCount(); idNum++) {
            TargetSingleUnitPanel singleUnitPanel =
                    new TargetSingleUnitPanel(UnitManager.getUnit(idNum));
            // add the single unit panel to the main panel
            unitDataPanel.add(singleUnitPanel, gloc);
            gloc.gridy++;
            // add the single unit panel to the list of single unit panels
            targetSingleUnitPanels.add(singleUnitPanel);
            System.out.println("targetSingleUnitPanels size = " + targetSingleUnitPanels.size());// DEBUG
        }

        // set the default selection to the first  sub panel  to prevent no-unit selected errors
        targetSingleUnitPanels.get(0).setSelected(true);

        editUnitButton.addActionListener(e -> {
            EditUnitPopup editUnitPopup = new EditUnitPopup(UnitManager.getSelectedTarget());
        });
    }

    /**
     * Updates all unit entries to reflect any changes to unit objects.
     */
    public void updateUnits() {
        //System.out.println("Updating");// DEBUG
        //System.out.println(targetSingleUnitPanels.size());
        for (TargetSingleUnitPanel singlePanel : targetSingleUnitPanels) {
            //System.out.println("Updating panel");// DEBUG
            singlePanel.updateGraphics();
        }
    }


    /**
     * Subclass of SingleUnitPanel that represents a single unit that is
     * a potential target for an attack.
     */

    class TargetSingleUnitPanel extends JPanel {
        private Unit unit;
        private JRadioButton unitSelectButton = new JRadioButton();
        private JLabel iconLabel = new JLabel();
        private JLabel idLabel = new JLabel();
        private JLabel nameLabel = new JLabel();
        private JLabel sideLabel = new JLabel();
        private JLabel armorLabel = new JLabel("Armor:");
        private JLabel armorImage = new JLabel();
        private JLabel structureLabel = new JLabel(" Internal:");
        private JLabel structureImage = new JLabel();
        private JLabel tmmLabel = new JLabel();


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

        public TargetSingleUnitPanel(Unit unit) {
            this.unit = unit;

            this.setLayout(new GridBagLayout());
            GridBagConstraints aLoc = new GridBagConstraints();
            aLoc.insets = new Insets(0, 1, 0, 2);

            aLoc.gridx = 0;
            aLoc.gridy = 0;
            aLoc.fill = GridBagConstraints.BOTH;
            aLoc.anchor = GridBagConstraints.WEST;
            aLoc.weightx = 1;
            this.add(unitSelectButton, aLoc);
            unitSelectGroup.add(unitSelectButton); // add button to parent panel's group
            //unitSelectButton.setBackground(Color.GREEN);// DEBUG

            aLoc.gridy = 1;
            idLabel.setHorizontalAlignment(SwingConstants.CENTER);
            this.add(idLabel, aLoc);
            //idLabel.setBorder(BorderFactory.createLineBorder(Color.GREEN));// DEBUG

            aLoc.gridy = 1;
            aLoc.gridx = 1;
            //aLoc.weightx = 0;
            this.add(iconLabel, aLoc);
            //iconLabel.setBorder(BorderFactory.createLineBorder(Color.GREEN));// DEBUG

            aLoc.gridy = 0;
            this.add(sideLabel, aLoc);
            //sideLabel.setBorder(BorderFactory.createLineBorder(Color.GREEN));// DEBUG

            aLoc.gridx = 2;
            aLoc.gridwidth = 2;
            nameLabel.setText(unit.getName() + " " + unit.getVariant());
            this.add(nameLabel, aLoc);
            //nameLabel.setBorder(BorderFactory.createLineBorder(Color.GREEN));// DEBUG

            aLoc.gridx = 4;
            aLoc.anchor = GridBagConstraints.EAST;
            this.add(tmmLabel, aLoc);
            //tmmLabel.setBorder(BorderFactory.createLineBorder(Color.GREEN));// DEBUG

            aLoc.gridy = 1;
            aLoc.gridx = 2;
            aLoc.gridwidth = 1;
            aLoc.anchor = GridBagConstraints.WEST;
            this.add(armorLabel, aLoc);
            //armorLabel.setBorder(BorderFactory.createLineBorder(Color.GREEN));// DEBUG

            aLoc.gridwidth = 1;
            aLoc.gridx = 3;
            this.add(armorImage, aLoc);
            //armorImage.setBorder(BorderFactory.createLineBorder(Color.GREEN));// DEBUG

            aLoc.gridx = 4;
            this.add(structureLabel, aLoc);
            aLoc.anchor = GridBagConstraints.EAST;
            structureLabel.setHorizontalAlignment(SwingConstants.RIGHT);
            //structureLabel.setBorder(BorderFactory.createLineBorder(Color.GREEN));// DEBUG

            aLoc.gridx = 5;
            this.add(structureImage, aLoc);
            //structureImage.setBorder(BorderFactory.createLineBorder(Color.GREEN));// DEBUG

            // tell UnitManger a new unit has been selected
            this.unitSelectButton.addActionListener(s ->
            {
                UnitManager.changeSelectedTarget(unit.getID());
            });

            this.updateGraphics();
        }


        /**
         * Updates all UI elements to reflect any changes to the unit
         */

        public void updateGraphics() {
            nameLabel.setText(unit.getName() + " " + unit.getVariant());

            sideLabel.setText("Red");

            idLabel.setText(String.valueOf(unit.getID()));

            tmmLabel.setText(" Target Movement Mod: " + unit.getTMM());

            switch (unit.getType()) {
                // TODO add other unit types
                default:
                    iconLabel.setIcon(ImageManager.getInfIcon());
                    break;
                case 'm':
                    iconLabel.setIcon(ImageManager.getMechIcon());
                    break;
            }

            //System.out.println("Updating target graphics");// DEBUG
            armorImage.setIcon(new ImageIcon(
                    ImageManager.getCounterImage(unit.getArmorMax(), unit.getArmorCur())));

            structureImage.setIcon(new ImageIcon(
                    ImageManager.getCounterImage(unit.getStructureMax(), unit.getStructureCur())));

            // shade every other line
            if (unit.getID() % 2 == 0) {
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

        public void setSelected(boolean sel) {
            unitSelectButton.setSelected(sel);
        }


        /**
         * Returns if this panel is selected.
         * @return true if the unit this panel represents is the selected unit
         */

        public boolean isSelected() {
            return unitSelectButton.isSelected();
        }
    }

}
