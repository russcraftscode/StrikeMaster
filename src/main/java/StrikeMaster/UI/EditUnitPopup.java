package StrikeMaster.UI;

import StrikeMaster.Units.Unit;

import javax.swing.*;
import java.awt.*;

public class EditUnitPopup extends JDialog {
    GridBagConstraints gbc = new GridBagConstraints();
    final int idCol = 0;
    final int firstSepCol = 1;
    final int statLabelCol = 2;
    final int statTextCol = 3;
    final int secondSepCol = 4;
    final int boxCol = 5;
    final int statTextW = 30;
    final int okButtonRow = 20;

    //public EditUnitPopup(Unit selectedUnit) {
    public EditUnitPopup(Unit selectedUnit, JPanel ownerPanel) {
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
        JTextField skillText = new JTextField(String.valueOf(selectedUnit.getSkill()));
        addStatText(skillText);

        JTextField armorText = new JTextField(String.valueOf(selectedUnit.getArmorCur()));
        addStatText(armorText);

        JTextField structureText = new JTextField(String.valueOf(selectedUnit.getStructureCur()));
        addStatText(structureText);

        JTextField heatText = new JTextField(String.valueOf(selectedUnit.getHeatCur()));
        addStatText(heatText);

        JTextField engineHitsText = new JTextField(String.valueOf(selectedUnit.getEngHits()));
        addStatText(engineHitsText);

        JTextField fcHitsText = new JTextField(String.valueOf(selectedUnit.getFCHits()));
        addStatText(fcHitsText);

        JTextField mpHitsText = new JTextField(String.valueOf(selectedUnit.getMPHits()));
        addStatText(mpHitsText);

        JTextField wepHitsText = new JTextField(String.valueOf(selectedUnit.getWepHits()));
        addStatText(wepHitsText);

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

        gbc.gridy = 1;
        gbc.gridheight = okButtonRow-2;
        gbc.fill = GridBagConstraints.VERTICAL;
        gbc.gridx = firstSepCol;
        JSeparator firstSep = new JSeparator(SwingConstants.VERTICAL);
        this.add(firstSep, gbc);

        gbc.gridx = secondSepCol;
        JSeparator secondSep = new JSeparator(SwingConstants.VERTICAL);
        this.add(secondSep, gbc);

        // create an add OK and Cancel buttons
        JPanel okButtonPanel = new JPanel(new FlowLayout());
        gbc.gridy = okButtonRow;
        gbc.gridx = 0;
        gbc.gridwidth = boxCol;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.WEST;

        JButton saveButton = new JButton("Save Edits");
        saveButton.addActionListener(e -> {
            saveChanges();
        });

        okButtonPanel.add(saveButton);

        JButton closeButton = new JButton("Close Without Saving");
        closeButton.addActionListener(e -> this.dispose());
        okButtonPanel.add(closeButton);

        this.add(okButtonPanel, gbc);


        this.setLocationRelativeTo(ownerPanel);
        this.pack();
        this.setVisible(true);
    }

    private void saveChanges(){
        // TODO make a better icon
        // DEBUG
        JDialog savedDialog = new JDialog(this, "TODO", true);

        JLabel msg = new JLabel("DEBUG - Save button pushed");

        JButton closeButton = new JButton("OK");
        closeButton.setPreferredSize(new Dimension(50, 20));
        closeButton.addActionListener(e -> savedDialog.dispose());

        savedDialog.setLayout(new BorderLayout());
        savedDialog.add(msg, BorderLayout.NORTH);
        savedDialog.add(closeButton, BorderLayout.SOUTH);

        savedDialog.pack();
        savedDialog.setLocationRelativeTo(this);
        savedDialog.setVisible(true);
        // DEBUG
    }

    private void addIDLabel(JLabel label){
        gbc.gridx = idCol;
        gbc.gridy ++;
        gbc.anchor = GridBagConstraints.WEST;
        label.setHorizontalAlignment(SwingConstants.LEFT);
        this.add(label, gbc);
    }

    private void addStatLabel(JLabel label){
        gbc.gridx = statLabelCol;
        gbc.gridy ++;
        gbc.anchor = GridBagConstraints.EAST;
        label.setHorizontalAlignment(SwingConstants.RIGHT);
        this.add(label, gbc);
    }

    private void addStatText(JTextField textField){
        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridx = statTextCol;
        gbc.gridy++;
        textField.setHorizontalAlignment(SwingConstants.RIGHT);
        textField.setPreferredSize(new Dimension(statTextW,
                textField.getPreferredSize().height));
        this.add(textField, gbc);
    }

    private void addBox(JCheckBox box){
        gbc.gridx = boxCol;
        gbc.gridy ++;
        box.setHorizontalAlignment(SwingConstants.LEFT);
        this.add(box, gbc);
    }


}
        /*
        popup.setSize(300, 500);
        popup.setLayout(new GridBagLayout());
        GridBagConstraints gridLoc = new GridBagConstraints();
        gridLoc.insets = new Insets(5, 5, 5, 5);
        gridLoc.fill = GridBagConstraints.HORIZONTAL;

        // Username data. Not editable.
        JLabel usernameLabel = new JLabel("Username: ");
        JLabel usernameDataLabel = new JLabel(user.getUsername());
        gridLoc.gridx = 0;
        gridLoc.gridy = 1;
        popup.add(usernameLabel, gridLoc);
        gridLoc.gridx = 1;
        gridLoc.gridy = 1;
        popup.add(usernameDataLabel, gridLoc);

        // Name data
        JLabel nameLabel = new JLabel("Name: ");
        JTextField nameField = new JTextField(user.getName());
        gridLoc.gridx = 0;
        gridLoc.gridy = 2;
        popup.add(nameLabel, gridLoc);
        gridLoc.gridx = 1;
        gridLoc.gridy = 2;
        popup.add(nameField, gridLoc);

        // Belt rank data
        JLabel beltRankLabel = new JLabel("Belt Rank: ");
        JTextField beltRankField = new JTextField(String.valueOf(user.getBeltRank()));
        gridLoc.gridx = 0;
        gridLoc.gridy = 3;
        popup.add(beltRankLabel, gridLoc);
        gridLoc.gridx = 1;
        gridLoc.gridy = 3;
        popup.add(beltRankField, gridLoc);

        // Training rank data
        JLabel trainingRankLabel = new JLabel("Training Rank: ");
        JTextField trainingRankField = new JTextField(String.valueOf(user.getTrainingRank()));
        gridLoc.gridx = 0;
        gridLoc.gridy = 4;
        popup.add(trainingRankLabel, gridLoc);
        gridLoc.gridx = 1;
        gridLoc.gridy = 4;
        popup.add(trainingRankField, gridLoc);

        // make owner/trainer checkboxes
        JCheckBox trainerBox = new JCheckBox("Is a Trainer?");
        trainerBox.setSelected(user.getIsTrainer());
        gridLoc.gridx = 0;
        gridLoc.gridwidth = 2;
        gridLoc.gridy = 5;
        popup.add(trainerBox, gridLoc);

        JCheckBox ownerBox = new JCheckBox("Is a Gym Owner?");
        ownerBox.setSelected(user.getIsOwner());
        gridLoc.gridx = 0;
        gridLoc.gridwidth = 2;
        gridLoc.gridy = 6;
        popup.add(ownerBox, gridLoc);

        // Make availability label & checkboxes
        JLabel availabilityLabel = new JLabel("Edit Availability:");
        gridLoc.gridx = 0;
        gridLoc.gridy = 7;
        popup.add(availabilityLabel, gridLoc);

        // monday = 2
        gridLoc.gridwidth = 1;
        JCheckBox mondayBox = new JCheckBox("Monday");
        if (user.getAvailability().contains(2)) {
            mondayBox.setSelected(true);
        }
        gridLoc.gridx = 0;
        gridLoc.gridy = 8;
        popup.add(mondayBox, gridLoc);

        // Tuesday = 3
        JCheckBox tuesdayBox = new JCheckBox("Tuesday");
        if (user.getAvailability().contains(3)) {
            tuesdayBox.setSelected(true);
        }
        gridLoc.gridx = 1;
        gridLoc.gridy = 8;
        popup.add(tuesdayBox, gridLoc);

        // Wednesday = 4
        JCheckBox wednesdayBox = new JCheckBox("Wednesday");
        if (user.getAvailability().contains(4)) {
            wednesdayBox.setSelected(true);
        }
        gridLoc.gridx = 0;
        gridLoc.gridy = 9;
        popup.add(wednesdayBox, gridLoc);

        // Thursday = 5
        JCheckBox thursdayBox = new JCheckBox("Thursday");
        if (user.getAvailability().contains(5)) {
            thursdayBox.setSelected(true);
        }
        gridLoc.gridx = 1;
        gridLoc.gridy = 9;
        popup.add(thursdayBox, gridLoc);

        // Friday = 6
        JCheckBox fridayBox = new JCheckBox("Friday");
        if (user.getAvailability().contains(6)) {
            fridayBox.setSelected(true);
        }
        gridLoc.gridx = 0;
        gridLoc.gridy = 10;
        popup.add(fridayBox, gridLoc);

        // Saturday = 7
        JCheckBox saturdayBox = new JCheckBox("Saturday");
        if (user.getAvailability().contains(7)) {
            saturdayBox.setSelected(true);
        }
        gridLoc.gridx = 1;
        gridLoc.gridy = 10;
        popup.add(saturdayBox, gridLoc);

        // Sunday = 1
        JCheckBox sundayBox = new JCheckBox("Sunday");
        if (user.getAvailability().contains(1)) {
            sundayBox.setSelected(true);
        }
        gridLoc.gridx = 0;
        gridLoc.gridy = 11;
        popup.add(sundayBox, gridLoc);

        // Add the buttons to the bottom of the popup
        JButton applyButton = new JButton("Close & Save");
        applyButton.setBackground(Color.GREEN);
        gridLoc.gridx = 1;
        gridLoc.gridy = 12;
        popup.add(applyButton, gridLoc);

        JButton deleteButton = new JButton("Delete User");
        deleteButton.setBackground(Color.RED);
        gridLoc.gridx = 0;
        gridLoc.gridy = 12;
        popup.add(deleteButton, gridLoc);

        JButton closeButton = new JButton("Close & Discard Changes");
        gridLoc.gridx = 0;
        gridLoc.gridwidth = 2;
        gridLoc.gridy = 13;
        popup.add(closeButton, gridLoc);

        // logic for apply button
        applyButton.addActionListener(e -> {
            boolean goodData = true;
            // gather the data from the fields
            String name = nameField.getText();
            // get data for the belt rank and make sure it is valid
            String beltRank = beltRankField.getText();
            int beltRankInt = 1;
            try {
                beltRankInt = Integer.parseInt(beltRank);
                // only apply change is rank is in range
                if (beltRankInt <= Belt.toRank("black3")
                        && beltRankInt >= Belt.toRank("white")) {
                    user.setBeltRank(beltRankInt);
                } else {
                    goodData = false;
                    this.badRankDialog();
                }
            } catch (NumberFormatException exc) {
                goodData = false;
                this.badRankDialog();
            }
            // get data for the training rank and make sure it is valid
            String trainingRank = trainingRankField.getText();
            int trainingRankInt = 1;
            try {
                trainingRankInt = Integer.parseInt(trainingRank);
                // only apply change is rank is in range
                if (trainingRankInt <= Belt.toRank("black3")
                        && trainingRankInt >= Belt.toRank("white")) {
                    user.setTrainingRank(trainingRankInt);
                } else {
                    goodData = false;
                    this.badRankDialog();
                }
            } catch (NumberFormatException exc) {
                goodData = false;
                this.badRankDialog();
            }
            // only save and close if given good data
            if (goodData) {
                user.setName(name);
                user.setIsTrainer(trainerBox.isSelected());
                user.setIsOwner(ownerBox.isSelected());
                // sunday = 1
                if (sundayBox.isSelected()) {
                    user.addAvailability(1);
                } else {
                    user.removeAvailability(1);
                }
                if (mondayBox.isSelected()) {
                    user.addAvailability(2);
                } else {
                    user.removeAvailability(2);
                }
                if (tuesdayBox.isSelected()) {
                    user.addAvailability(3);
                } else {
                    user.removeAvailability(3);
                }
                if (wednesdayBox.isSelected()) {
                    user.addAvailability(4);
                } else {
                    user.removeAvailability(4);
                }
                if (thursdayBox.isSelected()) {
                    user.addAvailability(5);
                } else {
                    user.removeAvailability(5);
                }
                if (fridayBox.isSelected()) {
                    user.addAvailability(6);
                } else {
                    user.removeAvailability(6);
                }
                if (saturdayBox.isSelected()) {
                    user.addAvailability(7);
                } else {
                    user.removeAvailability(7);
                }
                // close out the popup and apply changes visually
                popup.dispose();
                updateRightPanel();
            }
        });

        // logic for delete user button
        deleteButton.addActionListener(e -> {
            // check to make sure not deleting current active user
            if (user.getUsername().equals(
                    TacticController.getUserManager().getCurrentUser().getUsername())) {
                noDeleteCurrentDialog();
            } else {
                TacticController.getUserManager().deleteUser(user.getUsername());
                popup.dispose();
                updateRightPanel();
            }
        });

        // logic for close button
        closeButton.addActionListener(e -> popup.dispose());

        popup.setLocationRelativeTo(this);
        popup.setVisible(true);
    }
}
*/