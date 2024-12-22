package StrikeMaster.UI;

import StrikeMaster.UnitFactory;
import StrikeMaster.UnitLibrary;
import StrikeMaster.Units.Unit;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.io.IOException;

public class AttackOptionsPanel extends JPanel {
    private JPanel overHeatPanel;
    private JPanel situationalPanel;
    private JPanel rangePanel;

    private Unit attackingUnit;
    private Unit targetUnit;

    private GridBagConstraints gloc;

    private int attackerSkill = 0; // value of the attacker skill
    private int situationalMod = 0; // various situational factors that affect the to-hit number
    private int attackMoveMod = 0; // modifier caused by attacker movement
    private int targetMoveMod = 0; // modifier caused by target movement
    private int rangeMod = 0; // modifier caused by range between the attacker and target
    private int heatMod = 0; // modifier caused by the attacker's heat level before firing
    private int attackerDamMod = 0; // modifier caused by damage to the attacker's targeting system
    private int toHitFinal = 0; // roll needed to hit the target

    private boolean indirectFire = false;
    private boolean partialCover = false;
    private boolean woods = false;
    private boolean aftArc = false;
    private boolean rearArmor = false;

    private int rangeValue = 0; // 0 = short, 1 = med, 2 = long

    private final JLabel attackerSkillNumber = new JLabel();
    private final JLabel situationalModNumber = new JLabel();
    private final JLabel attackMoveModNumber = new JLabel();
    private final JLabel targetMoveModNumber = new JLabel();
    private final JLabel rangeModNumber = new JLabel();
    private final JLabel heatModNumber = new JLabel();
    private final JLabel attackerDamModNumber = new JLabel();
    private final JLabel toHitNumber = new JLabel();

    private final JComboBox overHeatBox = new JComboBox();

    /**
     * Panel that lets users input all variables that will affect an attack roll.
     */
    public AttackOptionsPanel() {

        // TODO delete this next part. This is only to provide data for prototyping the panel.
        UnitLibrary protoLib = null;
        try {
            protoLib = new UnitLibrary();
            //protoLib = new UnitLibrary("src/main/resources/mech_data.csv");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        this.attackingUnit = UnitFactory.buidUnit(protoLib.getUnitData("AWS-9M"),99);
        this.targetUnit = UnitFactory.buidUnit(protoLib.getUnitData("AWS-9M"),98);
        // end prototyping


        buildPanel();
        this.setPreferredSize(this.getPreferredSize());
        update();
    }

    public void setAttacker(Unit attacker) {
        this.attackingUnit = attacker;
    }

    public void setTarget(Unit target) {
        this.targetUnit = target;
    }

    /**
     * Updates the labels and re-calculates to-hit numbers
     */
    public void update() {
        // TODO separate UPDATE from CREATE. make the things that need to be updated referenced to by class attributes
        // reset all attack roll variables
        attackerSkill = 0;
        situationalMod = 0;
        attackMoveMod = 0;
        targetMoveMod = 0;
        rangeMod = rangeValue * 2;
        heatMod = 0;
        attackerDamMod = 0;

        // TODO calculate the remaining modifiers

        // calculate the values from the selected units
        if (attackingUnit != null) { // if no unit selected then just use default values
            attackerSkill = attackingUnit.getSkill();
            attackMoveMod = attackingUnit.getAttackMoveMod();
            attackerDamMod = attackingUnit.getFCHits() * 2;
            heatMod = attackingUnit.getHeatCur() * 1;
        }

        if (targetUnit != null) { // if no unit selected then just use default values
            targetMoveMod = targetUnit.getTMM();
        }

        // calculate situational mods
        if (indirectFire) situationalMod += 1;
        if (partialCover) situationalMod += 1;
        if (woods) situationalMod += 1;
        if (aftArc) situationalMod += 1;

        // toHitNumber is the sum of all modifiers
        toHitFinal = attackerSkill + situationalMod + attackMoveMod
                + targetMoveMod + rangeMod + heatMod + attackerDamMod;

        // update labels
        updateModNumber(attackerSkillNumber, attackerSkill, false);
        updateModNumber(situationalModNumber, situationalMod, true);
        updateModNumber(attackMoveModNumber, attackMoveMod, true);
        updateModNumber(targetMoveModNumber, targetMoveMod, true);
        updateModNumber(rangeModNumber, rangeMod, true);
        updateModNumber(heatModNumber, heatMod, true);
        updateModNumber(attackerDamModNumber, attackerDamMod, true);
        updateModNumber(toHitNumber, toHitFinal, false);

        // update heat select
        overHeatBox.removeAllItems();
        overHeatBox.addItem(0);
        if(attackingUnit != null){
            for (int i = 1; i <= attackingUnit.getOverheat(); i++){
                overHeatBox.addItem(i);
            }
        }

        this.revalidate();
        this.repaint();
    }

    /**
     * Initializes and places all swing components
     */
    public void buildPanel() {
        this.setLayout(new GridBagLayout());
        this.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));

        gloc = new GridBagConstraints();
        gloc.insets = new Insets(2, 4, 2, 4);

        gloc.gridy = 0;

        JLabel attackOptionsLabel = new JLabel("AttackOptions");
        this.locationMid(gloc);
        gloc.gridwidth = 5;
        this.add(attackOptionsLabel, gloc);

        JSeparator satorSep = new JSeparator(SwingConstants.HORIZONTAL);
        this.locationMid(gloc);
        gloc.fill = GridBagConstraints.HORIZONTAL;
        gloc.gridwidth = 5;
        this.add(satorSep, gloc);

        this.createModLabel("Attacker Skill", gloc);
        this.createModNumber(this.attackerSkillNumber, this.attackerSkill, gloc, false);

        this.createModLabel("Situational Modifiers", gloc);
        this.createModNumber(this.situationalModNumber, this.situationalMod, gloc, true);

        // position the attack mod panel
        situationalPanel = new JPanel();
        this.createSituationalPanel();
        this.locationMid(gloc);
        this.add(situationalPanel, gloc);

        this.createModLabel("Attacker Movement Modifier:", gloc);
        this.createModNumber(this.attackMoveModNumber, this.attackMoveMod, gloc, true);

        this.createModLabel("Target Movement Modifier:", gloc);
        this.createModNumber(this.targetMoveModNumber, this.targetMoveMod, gloc, true);

        this.createModLabel("Range Modifier:", gloc);
        this.createModNumber(this.rangeModNumber, this.rangeMod, gloc, true);

        // build and position the range selector panel
        rangePanel = new JPanel();
        this.createRangePanel();
        this.locationMid(gloc);
        this.add(rangePanel, gloc);

        this.createModLabel("Current Heat Level Modifier:", gloc);
        this.createModNumber(this.heatModNumber, this.heatMod, gloc, true);

        // Overheat
        overHeatPanel = new JPanel();
        createOverheatPanel();
        this.locationMid(gloc);
        this.add(overHeatPanel, gloc);

        this.createModLabel("Targeting System Damage Modifier:", gloc);
        this.createModNumber(this.attackerDamModNumber, this.attackerDamMod, gloc, true);

        this.createModLabel("Roll Needed to Hit:", gloc);
        this.createModNumber(this.toHitNumber, this.toHitFinal, gloc, false);
        // highlight the needed to-hit number with a boarder
        this.toHitNumber.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));

        // Fire Button
        JButton fireButton = new JButton("FIRE");
        Font fireFont = new Font("Impact", Font.BOLD, 30);
        fireButton.setFont(fireFont);
        fireButton.setForeground(Color.RED);
        fireButton.setMinimumSize(new Dimension(200, 50));
        gloc.weighty = 0.0; // have everything from here down stick to the bottom of the page
        this.locationMid(gloc);
        this.add(fireButton, gloc);

        JSeparator numSep = new JSeparator(SwingConstants.VERTICAL);
        gloc.fill = GridBagConstraints.VERTICAL;
        gloc.gridx = 2;
        gloc.gridwidth = 1;
        gloc.gridheight = gloc.gridy - 2;
        gloc.gridy = 2;
        this.add(numSep, gloc);
    }

    /**
     * Creates and formats a JLabel to be a label of an attack roll modifier
     * @param labelText the text explaining what the modifier is
     * @param loc the GridBagConstraints that needs to be modified to place the label
     */
    private void createModLabel(String labelText, GridBagConstraints loc) {
        loc.gridx = 0;
        loc.gridy++;
        loc.gridwidth = 2;
        loc.anchor = GridBagConstraints.EAST;
        JLabel toHitLabel = new JLabel(labelText);
        toHitLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        this.add(toHitLabel, loc);
    }

    /**
     * Formats an existing JLabel to be a show a value of an attack roll modifier
     * @param label the label to be formatted
     * @param value the value to be shown
     * @param loc the GridBagConstraints that needs to be modified to place the label
     * @param sign true if the + sign is to be shown for positive numbers
     */
    private void createModNumber(JLabel label, int value, GridBagConstraints loc, boolean sign) {
        loc.gridx = 3;
        loc.gridwidth = 1;
        loc.anchor = GridBagConstraints.EAST;
        // put a +/- sign in front of the number if sign is true
        if (sign) {
            if (value < 0) { // minus sign automatically comes with negative numbers
                label.setText(String.valueOf( value) );
            } else {
                label.setText("+" + value);
            }
        } else {
            label.setText(String.valueOf(value));
        }
        label.setHorizontalAlignment(SwingConstants.RIGHT);
        this.add(label, gloc);
    }

    /**
     * Updates a JLabel that is being used to show the value of an attack modifier
     * @param label the JLabel to be adjusted
     * @param value the value to be shown
     * @param sign true if the + sign is to be shown for positive numbers
     */
    private void updateModNumber(JLabel label, int value, boolean sign) {
        // put a +/- sign in front of the number if sign is true
        if (sign) {
            if (value < 0) { // minus sign automatically comes with negative numbers
                label.setText(String.valueOf(value));
            } else { // add a plus sign to positive numbers
                label.setText("+" + value);
            }
        } else {
            label.setText(String.valueOf(value));
        }
    }

    /**
     * Reconfigures a GridBagConstraint object to be in the center column of the AttackOptionsPanel
     * @param loc the GridBagConstraint object to be formatted
     */
    private void locationMid(GridBagConstraints loc) {
        loc.gridx = 0;
        loc.gridy++;
        loc.gridwidth = 2;
        loc.anchor = GridBagConstraints.CENTER;
    }

    /**
     * Creates and places the components on the sub panel that lets the user
     * input the situational attack-roll modifiers.
     */
    private void createSituationalPanel() {
        situationalPanel.setLayout(new BoxLayout(situationalPanel, BoxLayout.Y_AXIS));
        situationalPanel.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));

        JCheckBox indirectCheckBox = new JCheckBox("Indirect Fire");
        indirectCheckBox.setSelected(this.indirectFire);
        indirectCheckBox.addItemListener(e -> {
            this.indirectFire = (e.getStateChange() == ItemEvent.SELECTED);
            this.update();
        });
        situationalPanel.add(indirectCheckBox);

        JCheckBox partialCheckBox = new JCheckBox("Partial Cover");
        partialCheckBox.setSelected(this.partialCover);
        partialCheckBox.addItemListener(e -> {
            this.partialCover = (e.getStateChange() == ItemEvent.SELECTED);
            this.update();
        });
        situationalPanel.add(partialCheckBox);

        JCheckBox woodsCheckBox = new JCheckBox("Woods");
        woodsCheckBox.setSelected(this.woods);
        woodsCheckBox.addItemListener(e -> {
            this.woods = (e.getStateChange() == ItemEvent.SELECTED);
            this.update();
        });
        situationalPanel.add(woodsCheckBox);

        JCheckBox rearArmorCheckBox = new JCheckBox("Rear Armor");
        rearArmorCheckBox.setSelected(this.rearArmor);
        rearArmorCheckBox.addItemListener(e -> {
            this.rearArmor = (e.getStateChange() == ItemEvent.SELECTED);
            this.update();
        });
        situationalPanel.add(rearArmorCheckBox);

        JCheckBox aftArcCheckBox = new JCheckBox("Aft Firing Arc");
        aftArcCheckBox.setSelected(this.aftArc);
        aftArcCheckBox.addItemListener(e -> {
            this.aftArc = (e.getStateChange() == ItemEvent.SELECTED);
            this.update();
        });
        situationalPanel.add(aftArcCheckBox);
    }

    /**
     * Creates and places the components on the sub panel that lets the user
     * input the range attack-roll modifiers.
     */
    private void createRangePanel() {
        rangePanel.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        rangePanel.setLayout(new GridBagLayout());
        GridBagConstraints rangeLoc = new GridBagConstraints();
        rangeLoc.weightx = 1.0;
        rangeLoc.insets = new Insets(2, 2, 2, 2);

        ButtonGroup rangeButtons = new ButtonGroup();

        // create the labels for the ranges
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

        // Create the buttons to select the ranges
        JRadioButton shortButton = new JRadioButton();
        rangeButtons.add(shortButton);
        rangeLoc.gridx = 0;
        rangeLoc.gridy = 2;
        rangeLoc.anchor = GridBagConstraints.NORTH;
        rangePanel.add(shortButton, rangeLoc);
        shortButton.setSelected(true); // default to short range

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

        // add logic to buttons
        shortButton.addItemListener(e -> {
            if(e.getStateChange() == ItemEvent.SELECTED) rangeValue = 0;
            this.update();
        });

        medButton.addItemListener(e -> {
            if(e.getStateChange() == ItemEvent.SELECTED) rangeValue = 1;
            this.update();
        });

        longButton.addItemListener(e -> {
            if(e.getStateChange() == ItemEvent.SELECTED) rangeValue = 2;
            this.update();
        });

        extButton.addItemListener(e -> {
            if(e.getStateChange() == ItemEvent.SELECTED) rangeValue = 3;
            this.update();
        });

    }

    /**
     * Creates and places the components on the sub panel that lets the users
     * input the amount of overheat they want to add to this attack.
     */
    private void createOverheatPanel() {
       overHeatPanel.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        overHeatPanel.setLayout(new FlowLayout());

        JLabel heatLabel = new JLabel("OverHeat for this Attack");
        heatLabel.setHorizontalAlignment(SwingConstants.CENTER);
        overHeatPanel.add(heatLabel);

        overHeatPanel.add(overHeatBox);
        overHeatBox.addItem(0);
    }
}