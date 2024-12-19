package StrikeMaster;

import StrikeMaster.Units.Unit;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;

public class SatorPanel extends JPanel {
    private JPanel overHeatPanel;
    private JPanel situationalPanel;
    private JPanel rangePanel;

    private Unit attackingUnit;
    private Unit targetUnit;

    private GridBagConstraints gloc;

    private int attackerSkill; // value of the attacker skill
    private int situationalMod; // various situational factors that affect the to-hit number
    private int attackMoveMod; // modifier caused by attacker movement
    private int targetMoveMod; // modifier caused by target movement
    private int rangeMod; // modifier caused by range between the attacker and target
    private int heatMod; // modifier caused by the attacker's heat level before firing
    private int attackerDamMod; // modifier caused by damage to the attacker's targeting system
    private int toHitNumber; // roll needed to hit the target


    // do I even need these? Isn't the button a way to store this?
    private boolean indirectFire = true;
    private boolean partialCover = false;
    private boolean woods = false;
    private boolean aftArc = false;

    public SatorPanel() {
        this.update();

    }

    public void setAttacker (Unit attacker){
        this.attackingUnit = attacker;
    }

    public void setTarget(Unit target){
        this.targetUnit = target;
    }

    public void update() {
        // TODO seperate UPDATE from CREATE. make the things that need to be updated referenced to by class attributes
        // reset all attack roll variables
        attackerSkill = 0;
        situationalMod = 0;
        attackMoveMod = 0;
        targetMoveMod = 0;
        rangeMod = 0;
        heatMod = 0;
        attackerDamMod = 0;

        // TODO calculate the remaining modifiers

        // calculate the values from the selected units
        if(this.attackingUnit != null) { // if no unit selected then just use default values
            this.attackerSkill = this.attackingUnit.getSkill();
            this.attackMoveMod = this.attackingUnit.getAttackMoveMod();
        }

        if(this.targetUnit != null) { // if no unit selected then just use default values
            this.targetMoveMod = this.targetUnit.getTMM();
        }

        // calculate situational mods
        if (this.indirectFire) this.situationalMod += 1;
        if (this.partialCover) this.situationalMod += 1;
        if (this.woods) this.situationalMod += 1;
        if (this.aftArc) this.situationalMod += 1;


        // toHitNumber is the sum of all modifiers
        this.toHitNumber = this.attackerSkill + this.situationalMod + this.attackMoveMod
                + this.targetMoveMod + this.rangeMod + this.heatMod + this.attackerDamMod
                + this.toHitNumber;

        this.setLayout(new GridBagLayout());
        this.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));

        gloc = new GridBagConstraints();
        gloc.insets = new Insets(2,4,2,4);

        gloc.gridy = 0;

        JLabel satorLabel = new JLabel("AttackOptions");
        this.locationMid(gloc);
        gloc.gridwidth = 5;
        this.add(satorLabel, gloc);

        JSeparator satorSep = new JSeparator(SwingConstants.HORIZONTAL);
        //satorSep.setMinimumSize(new Dimension(2,2));
        this.locationMid(gloc);
        gloc.fill = GridBagConstraints.HORIZONTAL;
        gloc.gridwidth = 5;
        this.add(satorSep, gloc);

        this.toHitLabel("Attacker Skill", gloc);
        this.toHitValue(this.attackerSkill, gloc);

        this.toHitLabel("Situational Modifiers", gloc);
        this.toHitValue(this.situationalMod, gloc);

        // position the attack mod panel
        situationalPanel = new JPanel();
        this.updateModPanel();
        this.locationMid(gloc);
        this.add(situationalPanel, gloc);

        this.toHitLabel("Attacker Movement Modifier:", gloc);
        this.toHitValue(this.attackMoveMod, gloc);

        this.toHitLabel("Target Movement Modifier:", gloc);
        this.toHitValue(this.targetMoveMod, gloc);

        this.toHitLabel("Range Modifier:", gloc);
        this.toHitValue(this.rangeMod, gloc);

        // build and position the range selector panel
        rangePanel = new JPanel();
        this.updateRangePanel();
        this.locationMid(gloc);
        this.add(rangePanel, gloc);

        this.toHitLabel("Current Heat Level Modifier:", gloc);
        this.toHitValue(this.heatMod, gloc);

        // Overheat
        JLabel heatLabel = new JLabel("Selected OverHeat");
        heatLabel.setHorizontalAlignment(SwingConstants.CENTER);
        this.locationMid(gloc);
        this.add(heatLabel, gloc);

        overHeatPanel = new JPanel();
        overHeatPanel.setLayout(new BoxLayout(overHeatPanel, BoxLayout.X_AXIS));
        ButtonGroup heatButtons = new ButtonGroup();

        JRadioButton overHeat0 = new JRadioButton("0");
        heatButtons.add(overHeat0);
        overHeatPanel.add(overHeat0);
        JRadioButton overHeat1 = new JRadioButton("1");
        heatButtons.add(overHeat1);
        overHeatPanel.add(overHeat1);
        JRadioButton overHeat2 = new JRadioButton("2");
        heatButtons.add(overHeat2);
        overHeatPanel.add(overHeat2);
        JRadioButton overHeat3 = new JRadioButton("3");
        heatButtons.add(overHeat3);
        overHeatPanel.add(overHeat3);
        JRadioButton overHeat4 = new JRadioButton("4");
        heatButtons.add(overHeat4);
        overHeatPanel.add(overHeat4);
        JRadioButton overHeat5 = new JRadioButton("5");
        heatButtons.add(overHeat5);
        overHeatPanel.add(overHeat5);
        this.locationMid(gloc);
        this.add(overHeatPanel, gloc);

        this.toHitLabel("Targeting System Damage Modifier:", gloc);
        this.toHitValue(this.attackerDamMod, gloc);

        this.toHitLabel("Roll Needed to Hit:", gloc);
        this.toHitValue(this.toHitNumber, gloc);

        // have everything from here down stick to the bottom of the page
        // Fire Button
        JButton fireButton = new JButton("FIRE");
        Font fireFont = new Font("Impact", Font.BOLD, 30);
        fireButton.setFont(fireFont);
        fireButton.setForeground(Color.RED);
        //fireButton.setPreferredSize(new Dimension(150,70));
        fireButton.setMinimumSize(new Dimension(200, 50));
        gloc.weighty = 0.0; // have everything from here down stick to the bottom of the page
        this.locationMid(gloc);
        this.add(fireButton, gloc);

        JSeparator numSep = new JSeparator(SwingConstants.VERTICAL);
        gloc.fill = GridBagConstraints.VERTICAL;
        gloc.gridx = 2;
        gloc.gridwidth = 1;
        gloc.gridheight = gloc.gridy-2;
        gloc.gridy = 2;
        this.add(numSep, gloc);

        // visually apply the update
        this.repaint();
    }


    private void toHitLabel(String labelText, GridBagConstraints loc){
        loc.gridx = 0;
        loc.gridy++;
        loc.gridwidth = 2;
        loc.anchor = GridBagConstraints.EAST;
        JLabel toHitLabel = new JLabel(labelText);
        toHitLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        this.add(toHitLabel, loc);
    }

    private void toHitValue(int value, GridBagConstraints loc){
        loc.gridx = 3;
        loc.gridwidth = 1;
        loc.anchor = GridBagConstraints.EAST;
        JLabel modNumber = new JLabel();
        if(value < 0){ // if number is negative add a minus sign
            modNumber.setText("-" + value);
        }else{
            modNumber.setText("+" + value);
        }
        modNumber.setHorizontalAlignment(SwingConstants.RIGHT);
        this.add(modNumber, gloc);
    }

    private void locationBar(GridBagConstraints loc) {
        loc.gridx = 2;
        loc.gridwidth = 1;
        loc.anchor = GridBagConstraints.CENTER;

    }

    private void locationMid(GridBagConstraints loc) {
        loc.gridx = 0;
        loc.gridy++;
        loc.gridwidth = 2;
        loc.anchor = GridBagConstraints.CENTER;
    }

    private void updateModPanel(){
        situationalPanel.setLayout(new BoxLayout(situationalPanel, BoxLayout.Y_AXIS));
        situationalPanel.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        JCheckBox indirectCheckBox = new JCheckBox("Indirect Fire");
        indirectCheckBox.setSelected(this.indirectFire);
        indirectCheckBox.addItemListener(e -> {
            this.indirectFire = (e.getStateChange() == ItemEvent.SELECTED);
            System.out.println(this.indirectFire);// DEBUG
        this.update();
        });
        situationalPanel.add(indirectCheckBox);
        JCheckBox partialCheckBox = new JCheckBox("Partial Cover");
        situationalPanel.add(partialCheckBox);
        JCheckBox woodsCheckBox = new JCheckBox("Woods");
        situationalPanel.add(woodsCheckBox);
        JCheckBox rearArmorCheckBox = new JCheckBox("Rear Armor");
        situationalPanel.add(rearArmorCheckBox);
        JCheckBox aftArcCheckBox = new JCheckBox("Aft Firing Arc");
        situationalPanel.add(aftArcCheckBox);
    }

    private void updateRangePanel(){
        rangePanel.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        rangePanel.setLayout(new GridBagLayout());
        GridBagConstraints rangeLoc = new GridBagConstraints();
        rangeLoc.weightx = 1.0;
        rangeLoc.insets = new Insets(2,2,2,2);

        ButtonGroup rangeButtons = new ButtonGroup();

        JLabel rangePanelLabel = new JLabel("Enter Range");
        rangeLoc.gridx = 0;
        rangeLoc.gridy = 0;
        rangeLoc.gridwidth = 4;
        rangeLoc.anchor = GridBagConstraints.NORTH;
        rangePanel.add(rangePanelLabel, rangeLoc);

        JLabel shortLabel = new JLabel("Short ");
        shortLabel.setHorizontalAlignment(SwingConstants.CENTER);
        rangeLoc.gridx = 0;
        rangeLoc.gridy = 1;
        rangeLoc.gridwidth = 1;
        rangeLoc.anchor = GridBagConstraints.SOUTH;
        rangePanel.add(shortLabel, rangeLoc);

        JLabel medLabel = new JLabel("Med");
        rangeLoc.gridx = 1;
        rangePanel.add(medLabel, rangeLoc);

        JLabel longLabel = new JLabel("Long");
        longLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        rangeLoc.gridx = 2;
        rangePanel.add(longLabel, rangeLoc);

        JLabel extLabel = new JLabel("Ext");
        extLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        rangeLoc.gridx = 3;
        rangePanel.add(extLabel, rangeLoc);

        JRadioButton shortButton = new JRadioButton();
        rangeButtons.add(shortButton);
        rangeLoc.gridx = 0;
        rangeLoc.gridy = 2;
        rangeLoc.anchor = GridBagConstraints.NORTH;
        rangePanel.add(shortButton, rangeLoc);

        JRadioButton medButton = new JRadioButton();
        rangeButtons.add(medButton);
        rangeLoc.gridx = 1;
        rangePanel.add(medButton, rangeLoc);

        JRadioButton longButton = new JRadioButton();
        rangeButtons.add(longButton);
        rangeLoc.gridx = 2;
        rangePanel.add(longButton, rangeLoc);

        JRadioButton extButton = new JRadioButton();
        rangeButtons.add(extButton);
        rangeLoc.gridx = 3;
        rangePanel.add(extButton, rangeLoc);
    }

}