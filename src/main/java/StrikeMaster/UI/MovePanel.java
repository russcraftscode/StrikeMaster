package StrikeMaster.UI;

import StrikeMaster.ImageManager;
import StrikeMaster.UnitManager;
import StrikeMaster.Units.Unit;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

public class MovePanel extends UnitSelectPanel {

    public enum MoveType {HALT, MOVE, JUMP, SPRINT}

    ;
    private ArrayList<MoveSingleUnitPanel> moveSingleUnitPanels;

    /**
     *
     */
    @Override
    protected void buildUnitPanel() {
        moveSingleUnitPanels = new ArrayList<>();
        this.panelLabel.setText("Enter Unit Movements");

        unitDataPanel.setLayout(new GridBagLayout());
        GridBagConstraints gloc = new GridBagConstraints();
        gloc.insets = new Insets(2, 0, 1, 0);
        gloc.gridx = 0;
        gloc.gridy = 0;
        gloc.fill = GridBagConstraints.HORIZONTAL;
        gloc.weightx = 1;

        for (int idNum = 0; idNum < UnitManager.getUnitCount(); idNum++) {
            MoveSingleUnitPanel moveSingleUnitPanel =
                    new MoveSingleUnitPanel(UnitManager.getUnit(idNum));
            // add the single unit panel to the main panel
            unitDataPanel.add(moveSingleUnitPanel, gloc);
            gloc.gridy++;
            // add the single unit panel to the list of single unit panels
            moveSingleUnitPanels.add(moveSingleUnitPanel);
        }

        // set the default selection to the first  sub panel  to prevent no-unit selected errors
        //moveSingleUnitPanels.get(0).setSelected(true);

        editUnitButton.addActionListener(e -> {
            EditUnitPopup editUnitPopup = new EditUnitPopup(UnitManager.getSelectedMoveUnit());
        });
    }

    /**
     * Updates all unit entries to reflect any changes to unit objects.
     */
    @Override
    public void updateUnits() {
        for (MoveSingleUnitPanel singlePanel : moveSingleUnitPanels) {
            singlePanel.updateGraphics();
        }
    }

    class MoveSingleUnitPanel extends JPanel {

        private Unit unit;
        //ID components
        private JRadioButton unitSelectButton = new JRadioButton();
        private JLabel iconLabel = new JLabel();
        private JLabel idLabel = new JLabel();
        private JLabel sideLabel = new JLabel();
        private JLabel nameLabel = new JLabel();
        // Condition components
        private JLabel movementLabel = new JLabel();
        private JLabel armorLabel = new JLabel("Armor:");
        private JLabel armorImage = new JLabel();
        private JLabel structureLabel = new JLabel("Internal:");
        private JLabel structureImage = new JLabel();
        private JLabel tmmLabel = new JLabel();
        private JLabel wepSysHitsLabel = new JLabel("Wep Hits");
        private JLabel wepSysHitsImage = new JLabel();
        private JLabel engineHitsLabel = new JLabel("Engine Hits");
        private JLabel engineHitsImage = new JLabel();
        private JLabel mpHitsLabel = new JLabel("Drivetrain Hits");
        private JLabel mpHitsImage = new JLabel();
        private JLabel tarSysHitsLabel = new JLabel("FC Hits");
        private JLabel tarSysHitsImage = new JLabel();
        private JLabel heatLabel = new JLabel("Heat");
        private JLabel heatImage = new JLabel();
        // status components
        private JLabel moveTypeLabel = new JLabel("Move Type:");
        private JComboBox<MoveType> moveTypeBox = new JComboBox(MoveType.values());
        private JCheckBox inWaterBox = new JCheckBox("In Waist-Deep Water or Deeper");

        private ArrayList<JComponent> components = new ArrayList<>();

        private JPanel topRow = new JPanel();
        private JPanel bottomRow = new JPanel();

        /**
         * Creates a panel that represents a single unit to go onto a UnitSelectPanel
         * @param unit the unit this panel will represent
         */
        public MoveSingleUnitPanel(Unit unit) {
            this.unit = unit;

            this.setLayout(new GridBagLayout());
            GridBagConstraints mLoc = new GridBagConstraints();
            mLoc.insets = new Insets(0, 1, 0, 2);

            // add the generic stuff to the left of the panel
            mLoc.gridx = 0;
            mLoc.gridy = 0;
            mLoc.fill = GridBagConstraints.BOTH;
            mLoc.anchor = GridBagConstraints.WEST;
            this.add(unitSelectButton, mLoc);
            unitSelectGroup.add(unitSelectButton); // add button to parent panel's group
            //unitSelectButton.setBackground(Color.GREEN);// DEBUG

            mLoc.gridy = 1;
            idLabel.setHorizontalAlignment(SwingConstants.CENTER);
            this.add(idLabel, mLoc);
            //idLabel.setBorder(BorderFactory.createLineBorder(Color.GREEN));// DEBUG

            mLoc.gridy = 1;
            mLoc.gridx = 1;
            this.add(iconLabel, mLoc);
            //iconLabel.setBorder(BorderFactory.createLineBorder(Color.GREEN));// DEBUG

            mLoc.gridy = 0;
            this.add(sideLabel, mLoc);
            //sideLabel.setBorder(BorderFactory.createLineBorder(Color.GREEN));// DEBUG

            // add the status inputs to the top row
            topRow.setLayout(new BoxLayout(topRow, BoxLayout.X_AXIS));
            mLoc.weightx = 1;
            mLoc.fill = GridBagConstraints.HORIZONTAL;
            mLoc.gridx = 2;
            mLoc.gridy = 0;
            this.add(topRow, mLoc);
            //topRow.setBorder(BorderFactory.createLineBorder(Color.GREEN));// DEBUG

            nameLabel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
            topRow.add(nameLabel);
            topRow.add(movementLabel);
            tmmLabel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
            topRow.add(tmmLabel);
            topRow.add(moveTypeLabel);
            topRow.add(moveTypeBox);
            topRow.add(inWaterBox);

            // add the condition info to the bottom row
            bottomRow.setLayout(new BoxLayout(bottomRow, BoxLayout.X_AXIS));
            mLoc.gridx = 2;
            mLoc.gridy = 1;
            this.add(bottomRow, mLoc);

            bottomRow.add(armorLabel);
            bottomRow.add(armorImage);

            structureLabel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
            bottomRow.add(structureLabel);
            bottomRow.add(structureImage);

            engineHitsLabel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
            bottomRow.add(engineHitsLabel);
            bottomRow.add(engineHitsImage);

            wepSysHitsLabel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
            bottomRow.add(wepSysHitsLabel);
            bottomRow.add(wepSysHitsImage);

            mpHitsLabel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
            bottomRow.add(mpHitsLabel);
            bottomRow.add(mpHitsImage);

            tarSysHitsLabel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
            bottomRow.add(tarSysHitsLabel);
            bottomRow.add(tarSysHitsImage);

            heatLabel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
            bottomRow.add(heatLabel);
            bottomRow.add(heatImage);

            // tell UnitManger a new unit has been selected
            this.unitSelectButton.addActionListener(s ->
            {
                UnitManager.changeSelectedMoveUnit(unit.getID());
            });

            // add all graphical components to array list for easier group updates
            components.add(unitSelectButton);
            components.add(iconLabel);
            components.add(idLabel);
            components.add(sideLabel);
            components.add(nameLabel);
            components.add(movementLabel);
            components.add(armorLabel);
            components.add(armorImage);
            components.add(structureLabel);
            components.add(structureImage);
            components.add(tmmLabel);
            components.add(wepSysHitsLabel);
            components.add(wepSysHitsImage);
            components.add(engineHitsLabel);
            components.add(engineHitsImage);
            components.add(mpHitsLabel);
            components.add(mpHitsImage);
            components.add(tarSysHitsLabel);
            components.add(tarSysHitsImage);
            components.add(heatLabel);
            components.add(heatImage);
            components.add(moveTypeLabel);
            components.add(moveTypeBox);
            components.add(inWaterBox);

            // add logic to boxes
            moveTypeBox.addItemListener(new ItemListener() {
                @Override
                public void itemStateChanged(ItemEvent e) {
                    System.out.println("Change");
                    // TODO: offload this into unit class
                    // set unit move type
                    if(moveTypeBox.getSelectedItem() == MoveType.HALT){
                        System.out.println("halt");
                        unit.setMovedThisRound(false);
                        unit.setJumped(false);
                        unit.setSprinted(false);
                    }
                    if(moveTypeBox.getSelectedItem() == MoveType.MOVE){
                        System.out.println("move");
                        System.out.println(unit.getName());
                        unit.setMovedThisRound(true);
                        unit.setJumped(false);
                        unit.setSprinted(false);
                    }
                    if(moveTypeBox.getSelectedItem() == MoveType.SPRINT){
                        unit.setMovedThisRound(true);
                        unit.setJumped(false);
                        unit.setSprinted(true);
                    }
                    if(moveTypeBox.getSelectedItem() == MoveType.JUMP){
                        unit.setMovedThisRound(true);
                        unit.setJumped(true);
                        unit.setSprinted(false);
                    }
                    UnitManager.getInstance().updatedUnit();
                }
            });

            inWaterBox.addItemListener(new ItemListener() {
                @Override
                public void itemStateChanged(ItemEvent e) {
                    if(inWaterBox.isSelected())unit.setInWater(true);
                    else unit.setInWater(false);
                    UnitManager.getInstance().updatedUnit();
                }
            });

            this.updateGraphics();
        }

        /**
         * Updates all UI elements to reflect any changes to the unit
         */
        public void updateGraphics() {
            nameLabel.setText(unit.getName() + " " + unit.getVariant());
            sideLabel.setText(unit.getFaction());
            idLabel.setText(String.valueOf(unit.getID()));
            int sprintVal = (int) (unit.getMoveCur() * 1.5);
            tmmLabel.setText("TMM:" + unit.getTMM());
            movementLabel.setText("Movement: Move "
                    + unit.getMoveCur() + " / Sprint "
                    + sprintVal + " / Jump "
                    + unit.getJumpCur());
            switch (unit.getType()) {
                // TODO add other unit types
                default:
                    if (unit.getFaction().equals("Red"))
                        iconLabel.setIcon(ImageManager.getInfIcon());
                    else iconLabel.setIcon(ImageManager.getInfIconB());
                    break;
                case 'm':
                    if (unit.getFaction().equals("Red"))
                        iconLabel.setIcon(ImageManager.getMechIcon());
                    else iconLabel.setIcon(ImageManager.getMechIconB());
                    break;
            }
            wepSysHitsImage.setIcon(new ImageIcon(
                    ImageManager.getCounterImage(4, 4 - unit.getWepHits())));
            tarSysHitsImage.setIcon(new ImageIcon(
                    ImageManager.getCounterImage(4, 4 - unit.getFCHits())));
            heatImage.setIcon(new ImageIcon(
                    ImageManager.getCounterImage(4, 4 - unit.getHeatCur())));
            engineHitsImage.setIcon(new ImageIcon(
                    ImageManager.getCounterImage(2, 2 - unit.getEngHits())));
            mpHitsImage.setIcon(new ImageIcon(
                    ImageManager.getCounterImage(4, 4 - unit.getMPHits())));
            armorImage.setIcon(new ImageIcon(
                    ImageManager.getCounterImage(unit.getArmorMax(), unit.getArmorCur())));
            structureImage.setIcon(new ImageIcon(
                    ImageManager.getCounterImage(unit.getStructureMax(), unit.getStructureCur())));

            // shade every other line
            if (unit.getID() % 2 == 0) {
                this.setBackground(Color.LIGHT_GRAY);
                unitSelectButton.setBackground(Color.LIGHT_GRAY);
                topRow.setBackground(Color.LIGHT_GRAY);
                bottomRow.setBackground(Color.LIGHT_GRAY);
                this.setOpaque(true);
                /*for(JComponent component : components){
                    component.setBackground(Color.LIGHT_GRAY);
                }*/
                inWaterBox.setBackground(Color.LIGHT_GRAY);
            }

            // pick fonts
            Font panelFont;
            if (unit.isDestroyed()) panelFont = destroyedFont;
            else if (unit.didAttackThisRound()) panelFont = turnTakenFont;
            else panelFont = readyFont;
            // apply font
            for (JComponent component : components) {
                component.setFont(panelFont);
            }

            this.revalidate();
            this.repaint();
        }

        /**
         * Gets the unit that this panel represents.
         * @return the unit this panel represents
         */
        public Unit getUnit() {
            return unit;
        }

        /**
         * Sets the radio button for this unit.
         * @param sel if the unit should be selected.
         */
        public void setSelected(boolean sel) {
            // TODO do we even need this for a move panel
            //unitSelectButton.setSelected(sel);
            // tell UnitManger a new unit has been selected
            //UnitManager.changeSelectedAttacker(unit.getID());
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