package StrikeMaster.UI;

import StrikeMaster.UnitManager;
import StrikeMaster.Units.Unit;
import StrikeMaster.ImageManager;

import javax.swing.*;
import java.awt.*;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;


/**
 * This panel displays contextually relevant information about all units and allows
 * the user to select a unit. UnitManger tracks which unit is selected.
 */
public abstract class UnitSelectPanel extends JPanel implements Observer {



    protected final JPanel unitDataPanel = new JPanel();
    protected final JButton editUnitButton = new JButton("Edit Unit");
    protected final JLabel panelLabel = new JLabel();

    // create a button group for all unit select radio buttons
    protected final ButtonGroup unitSelectGroup = new ButtonGroup();


    /**
     * Constructs either an attacker-unit select panel, a target-unit select panel,
     * or a move-unit select panel.
     */
    //public UnitSelectPanel(int panelType) {
    public UnitSelectPanel() {
        // make this panel an observer of UnitManger
        UnitManager.getInstance().addObserver(this);

        this.setLayout(new GridBagLayout());
        this.buildUnitPanel();

        // create the scroll pane that will hold the unit data panel
        JScrollPane unitScroll = new JScrollPane();
        unitScroll.getVerticalScrollBar().setUnitIncrement(5); // speed up the scrolling
        // anchor the unitDataPanel to the top of the scroll pane if it isn't big enough to scroll
        unitScroll.getViewport().setLayout(new FlowLayout(FlowLayout.LEADING));
        // preferred height of 0 will let the AttackOptions panel set vertical size of this window
        unitScroll.setPreferredSize(new Dimension(unitDataPanel.getPreferredSize().width+30, 0));
        // Add unit data panel to scroll pane and add that to AttackPanel
        unitScroll.getViewport().add(unitDataPanel);

        editUnitButton.setPreferredSize(new Dimension(editUnitButton.getPreferredSize().width, 20));

        // add components to unit select panel
        GridBagConstraints selectLoc = new GridBagConstraints();
        selectLoc.gridy=0;
        this.add(panelLabel, selectLoc);

        selectLoc.gridy++;
        selectLoc.weighty = 1;
        selectLoc.fill = GridBagConstraints.VERTICAL;
        this.add(unitScroll, selectLoc);

        selectLoc.anchor = GridBagConstraints.SOUTH;
        selectLoc.fill = GridBagConstraints.NONE;
        selectLoc.gridy++;
        selectLoc.weighty = 0;
        this.add(editUnitButton, selectLoc);
    }

    protected abstract void buildUnitPanel();

    /**
     * Updates all unit entries to reflect any changes to unit objects.
     */
    public abstract void updateUnits();

    /**
     * Whenever a unit is changed the panel will update its graphics.
     * @param o   the observable object.
     * @param arg an argument passed to the {@code notifyObservers} method.
     */
    @Override
    public void update(Observable o, Object arg) {
        // TODO determine if updating only a single unit will be needed to maintain performance
        if (o instanceof UnitManager) {
            updateUnits();
        }
    }

    /**
     * Superclass for AttackSingleUnitPanel, TargetSingleUnitPanel, and MoveSingleUnitPanel
     */
    class SingleUnitPanel extends JPanel{
        protected Unit unit;

        public SingleUnitPanel(){}

        /**
         * Creates a panel that represents a single unit to go onto a
         * UnitSelectPanel
         * @param unit the unit this panel will represent
         */
        public SingleUnitPanel(Unit unit){
            this.unit = unit;
            this.updateGraphics();
        }

        /**
         * Updates all UI elements to reflect any changes to the unit
         */
        public void updateGraphics(){
            this.revalidate();
            this.repaint();
        }

        /**
         * Sets the radio button for this unit.
         * @param sel if the unit should be selected.
         */
        public void setSelected(boolean sel){
        }

        /**
         * Returns if this panel is selected.
         * @return true if the unit this panel represents is the selected unit
         */
        public boolean isSelected(){
            return true;
        }
    }
/*

    */
/**
     * Subclass of SingleUnitPanel that represents a single unit that is making an attack.
     *//*

    class AttackSingleUnitPanel extends SingleUnitPanel {
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

        */
/**
         * Gets the unit that this panel represents.
         * @return the unit this panel represents
         *//*

        public Unit getUnit() {
            return unit;
        }

        */
/**
         * Creates a panel that represents a single unit to go onto a
         * UnitSelectPanel
         * @param unit the unit this panel will represent
         *//*

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

        */
/**
         * Updates all UI elements to reflect any changes to the unit
         *//*

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

        */
/**
         * Sets the radio button for this unit.
         * @param sel if the unit should be selected.
         *//*

        public void setSelected(boolean sel){
            unitSelectButton.setSelected(sel);
            // tell UnitManger a new unit has been selected
            UnitManager.changeSelectedAttacker(unit.getID());
        }

        */
/**
         * Returns if this panel is selected.
         * @return true if the unit this panel represents is the selected unit
         *//*

        public boolean isSelected(){
            return unitSelectButton.isSelected();
        }
    }

    */
/**
     * Subclass of SingleUnitPanel that represents a single unit that is
     * a potential target for an attack.
     *//*

    class TargetSingleUnitPanel extends SingleUnitPanel {
        private JRadioButton unitSelectButton = new JRadioButton();
        private JLabel iconLabel = new JLabel();
        private JLabel idLabel = new JLabel();
        private JLabel nameLabel = new JLabel();
        private JLabel sideLabel = new JLabel();
        private JLabel armorLabel   = new JLabel("Armor:");
        private JLabel armorImage = new JLabel();
        private JLabel structureLabel = new JLabel(" Internal:");
        private JLabel structureImage = new JLabel();
        private JLabel tmmLabel = new JLabel();

        */
/**
         * Gets the unit that this panel represents.
         * @return the unit this panel represents
         *//*

        public Unit getUnit() {
            return unit;
        }

        */
/**
         * Creates a panel that represents a single unit to go onto a
         * UnitSelectPanel
         * @param unit the unit this panel will represent
         *//*

        public TargetSingleUnitPanel(Unit unit){
            this.unit = unit;

            this.setLayout(new GridBagLayout());
            GridBagConstraints aLoc = new GridBagConstraints();
            aLoc.insets = new Insets(0,1,0,2);

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
            this.unitSelectButton.addActionListener(s->
            {
                UnitManager.changeSelectedTarget(unit.getID());
            });

            this.updateGraphics();
        }

        */
/**
         * Updates all UI elements to reflect any changes to the unit
         *//*

        public void updateGraphics(){
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

            armorImage.setIcon(new ImageIcon(
                    ImageManager.getCounterImage(unit.getArmorMax(), unit.getArmorCur())));

            structureImage.setIcon(new ImageIcon(
                    ImageManager.getCounterImage(unit.getStructureMax(), unit.getStructureCur())));

            // shade every other line
            if(unit.getID()%2 == 0){
                this.setBackground(Color.LIGHT_GRAY);
                unitSelectButton.setBackground(Color.LIGHT_GRAY);
                this.setOpaque(true);
            }

            this.revalidate();
            this.repaint();
        }

        */
/**
         * Sets the radio button for this unit.
         * @param sel if the unit should be selected.
         *//*

        public void setSelected(boolean sel){unitSelectButton.setSelected(sel);}

        */
/**
         * Returns if this panel is selected.
         * @return true if the unit this panel represents is the selected unit
         *//*

        public boolean isSelected(){
            return unitSelectButton.isSelected();
        }
    }
*/

}
