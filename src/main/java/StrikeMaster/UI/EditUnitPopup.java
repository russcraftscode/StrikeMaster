package StrikeMaster.UI;

import StrikeMaster.Units.Unit;

import javax.swing.*;
import java.awt.*;

/**
 * A popup window that lets users directly edit a unit's stats.
 */
public class EditUnitPopup extends JDialog {
    private GridBagConstraints gbc = new GridBagConstraints();
    private final int idCol = 0;
    private final int firstSepCol = 1;
    private final int statLabelCol = 2;
    private final int statTextCol = 3;
    private final int secondSepCol = 4;
    private final int boxCol = 5;
    private final int statTextW = 60;
    private final int okButtonRow = 20;

    // TODO this popup should call on other panels to update by
    // a better means than being passed its parent panel.

    /**
     * Constructs the popup that lets a user directly edit a unit outside
     * of normal game interactions.
     * @param selectedUnit the unit to be edited.
     * @param ownerPanel the panel that called this popup
     */
    public EditUnitPopup(Unit selectedUnit, UnitSelectPanel ownerPanel) {
        this.setTitle("Manually Edit Unit");
        this.setLayout(new GridBagLayout());

        gbc.insets = new Insets(1,1,1,1);

        // create and add labels about unit ID info
        gbc.gridy = 0;
        JLabel nameLabel = new JLabel(selectedUnit.getName());
        addIDLabel(nameLabel);

        JLabel varLabel = new JLabel( selectedUnit.getVariant());
        addIDLabel(varLabel);

        JLabel typeLabel = new JLabel();
        switch (selectedUnit.getType()){
            // TODO add other types
            case 'm':
                typeLabel.setText("BattleMech");
                break;
            default:
                typeLabel.setText("--Error: undef type --");
        }
        addIDLabel(typeLabel);

        JLabel sideLabel = new JLabel("Side: TODO" );
        addIDLabel(sideLabel);

        JLabel pvLabel = new JLabel("Point Value: " + selectedUnit.getPV() );
        addIDLabel(pvLabel);

        // create and place unit stat labels
        gbc.gridy = 0;
        JLabel skillLabel = new JLabel("Skill:" );
        addStatLabel(skillLabel);

        JLabel armorLabel = new JLabel("Armor (max "
                + selectedUnit.getArmorMax() + "):" );
        addStatLabel(armorLabel);

        JLabel structureLabel = new JLabel("Structure (max "
                + selectedUnit.getStructureMax() + "):" );
        addStatLabel(structureLabel);

        JLabel heatLabel = new JLabel("Current Heat:" );
        addStatLabel(heatLabel);

        JLabel engineHitsLabel = new JLabel("Reactor Hits:" );
        addStatLabel(engineHitsLabel);

        JLabel fcHitsLabel = new JLabel("Fire Control Hits:" );
        addStatLabel(fcHitsLabel);

        JLabel mpHitsLabel = new JLabel("Drivetrain Hits:" );
        addStatLabel(mpHitsLabel);

        JLabel wepHitsLabel = new JLabel("Weapon Hits:" );
        addStatLabel(wepHitsLabel);

        // create and place unit stat entry texts
        gbc.gridy = 0;

        JComboBox<Integer> skillBox = new JComboBox<>();
        addStatCombobox(skillBox, 2,6,selectedUnit.getSkill());

        JComboBox<Integer> armorBox = new JComboBox<>();
        addStatCombobox(armorBox, 0,selectedUnit.getArmorMax(),selectedUnit.getArmorCur());

        JComboBox<Integer> structureBox = new JComboBox<>();
        addStatCombobox(structureBox, 0,selectedUnit.getStructureMax(),selectedUnit.getStructureCur());

        JComboBox<Integer> heatBox = new JComboBox<>();
        addStatCombobox(heatBox, 0,4,selectedUnit.getHeatCur());

        JComboBox<Integer> engineHitsBox = new JComboBox<>();
        addStatCombobox(engineHitsBox, 0,1,selectedUnit.getEngHits());

        JComboBox<Integer> fcHitsBox = new JComboBox<>();
        addStatCombobox(fcHitsBox, 0,4,selectedUnit.getFCHits());

        JComboBox<Integer> mpHitsBox = new JComboBox<>();
        addStatCombobox(mpHitsBox, 0,4,selectedUnit.getMPHits());

        JComboBox<Integer> wepHitsBox = new JComboBox<>();
        addStatCombobox(wepHitsBox, 0,4,selectedUnit.getWepHits());

        // create and place status checkboxes
        gbc.gridy = 0;
        JCheckBox sprintedBox = new JCheckBox("Sprinted");
        sprintedBox.setSelected(selectedUnit.didSprintThisRound());
        addBox(sprintedBox);

        JCheckBox jumpedBox = new JCheckBox("Jumped");
        jumpedBox.setSelected(selectedUnit.didSprintThisRound());
        addBox(jumpedBox);

        JCheckBox movedBox = new JCheckBox("Moved this Round");
        movedBox.setSelected(selectedUnit.didSprintThisRound());
        addBox(movedBox);

        JCheckBox firedBox = new JCheckBox("Fired Weapons this Round");
        firedBox.setSelected(selectedUnit.didSprintThisRound());
        addBox(firedBox);

        JCheckBox waterBox = new JCheckBox("In Water");
        waterBox.setSelected(selectedUnit.didSprintThisRound());
        addBox(waterBox);

        JCheckBox shutdownBox = new JCheckBox("Shutdown");
        shutdownBox.setSelected(selectedUnit.didSprintThisRound());
        addBox(shutdownBox);

        JCheckBox immobileBox = new JCheckBox("Immobile");
        immobileBox.setSelected(selectedUnit.didSprintThisRound());
        addBox(immobileBox);

        JCheckBox destroyedBox = new JCheckBox("Destroyed");
        destroyedBox.setSelected(selectedUnit.isDestroyed());
        addBox(destroyedBox);

        // place a separator between the 1st and 2nd column
        gbc.gridy = 1;
        gbc.gridheight = okButtonRow-2;
        gbc.fill = GridBagConstraints.VERTICAL;
        gbc.gridx = firstSepCol;
        JSeparator firstSep = new JSeparator(SwingConstants.VERTICAL);
        this.add(firstSep, gbc);

        // place a separator between the 2nd and 3rd column
        gbc.gridx = secondSepCol;
        JSeparator secondSep = new JSeparator(SwingConstants.VERTICAL);
        this.add(secondSep, gbc);

        // create sub-panel to hold OK and Cancel buttons
        JPanel okButtonPanel = new JPanel(new FlowLayout());
        gbc.gridy = okButtonRow;
        gbc.gridx = 0;
        gbc.gridwidth = 10;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.SOUTH;
        this.add(okButtonPanel, gbc);


        // create and add Save and Cancel buttons to sub-panel
        JButton saveButton = new JButton("Save Edits");
        okButtonPanel.add(saveButton);
        JButton closeButton = new JButton("Close Without Saving");
        okButtonPanel.add(closeButton);

        // add logic to buttons
        saveButton.addActionListener(e -> {
            // update values
            selectedUnit.setSkill((Integer) skillBox.getSelectedItem());
            selectedUnit.setArmorCur((Integer) armorBox.getSelectedItem());
            selectedUnit.setStructureCur((Integer) structureBox.getSelectedItem());
            selectedUnit.setHeatCur((Integer) heatBox.getSelectedItem());
            selectedUnit.setEngHits((Integer) engineHitsBox.getSelectedItem());
            selectedUnit.setFCHits((Integer) fcHitsBox.getSelectedItem());
            selectedUnit.setMPHits((Integer) mpHitsBox.getSelectedItem());
            selectedUnit.setWepHits((Integer) wepHitsBox.getSelectedItem());

            // update status
            selectedUnit.setSprinted(sprintedBox.isSelected());
            selectedUnit.setJumped(jumpedBox.isSelected());
            selectedUnit.setMovedThisRound(movedBox.isSelected());
            selectedUnit.setAttackedThisRound(firedBox.isSelected());
            selectedUnit.setInWater(waterBox.isSelected());
            selectedUnit.setShutdown(shutdownBox.isSelected());
            selectedUnit.setImmobile(immobileBox.isSelected());
            selectedUnit.setDestroyed(destroyedBox.isSelected());

            // close the popup
            ownerPanel.updateUnits();
            this.dispose();
        });
        closeButton.addActionListener(e -> this.dispose());

        this.setLocationRelativeTo(ownerPanel); // make popup appear centered
        this.pack();
        this.setVisible(true);
    }

    /**
     * Place a non-editable text table that describes the unit's identity.
     * @param label the label to be placed
     */
    private void addIDLabel(JLabel label){
        gbc.gridx = idCol;
        gbc.gridy ++;
        gbc.anchor = GridBagConstraints.WEST;
        label.setHorizontalAlignment(SwingConstants.LEFT);
        this.add(label, gbc);
    }

    /**
     * Place a non-editable text label that names a category
     * that describes the unit's current condition.
     * @param label the label to be placed
     */
    private void addStatLabel(JLabel label){
        gbc.gridx = statLabelCol;
        gbc.gridy ++;
        gbc.anchor = GridBagConstraints.EAST;
        label.setHorizontalAlignment(SwingConstants.RIGHT);
        this.add(label, gbc);
    }


    /**
     * Place a combobox that lets the user select a valid value
     * for a variable that describes the unit's current condition.
     * @param box the JComboBox to be placed
     * @param min the minimum value that can be selected
     * @param max the maximum value that can be selected
     * @param currentValue the current value of that variable
     */
    private void addStatCombobox(JComboBox<Integer> box, int min, int max, int currentValue){
        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridx = statTextCol;
        gbc.gridy++;
        box.removeAllItems();
        for(int i = min; i <= max; i++){
            box.addItem(i);
            // if the item being added is the current value then select the currently last item
            if(i == currentValue) box.setSelectedIndex(box.getItemCount() - 1);
        }
        box.setPreferredSize(new Dimension(statTextW,
                box.getPreferredSize().height));
        this.add(box, gbc);
    }

    /**
     * Place a checkbox that lets the user select describe a unit's
     * current status.
     * @param box the checkbox to be placed
     */
    private void addBox(JCheckBox box){
        gbc.gridx = boxCol;
        gbc.gridy ++;
        box.setHorizontalAlignment(SwingConstants.LEFT);
        this.add(box, gbc);
    }
}
