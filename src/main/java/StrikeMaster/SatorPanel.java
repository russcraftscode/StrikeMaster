package StrikeMaster;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.util.concurrent.BlockingQueue;

public class SatorPanel extends JPanel {
    private JPanel overHeatPanel;
    private JPanel modPanel;
    private JPanel rangePanel;


    public SatorPanel() {
        //this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setLayout(new GridBagLayout());
        this.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
        //this.setAlignmentY(Component.TOP_ALIGNMENT);

        GridBagConstraints gloc = new GridBagConstraints();
        gloc.insets = new Insets(2,4,2,4);

        gloc.gridy = 0;
        //gloc.anchor = GridBagConstraints.NORTH;

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

        JLabel skillModLabel = new JLabel("Attacker Skill");
        skillModLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        this.locationLeft(gloc);
        this.add(skillModLabel, gloc);

        JLabel skillModNumber = new JLabel("4");
        skillModNumber.setHorizontalAlignment(SwingConstants.RIGHT);
        this.locationRight(gloc);
        this.add(skillModNumber, gloc);

        JLabel attackModLabel = new JLabel("Attack Modifiers");
        attackModLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        this.locationLeft(gloc);
        this.add(attackModLabel, gloc);

        JLabel attackModNumber = new JLabel("+1");
        attackModNumber.setHorizontalAlignment(SwingConstants.RIGHT);
        this.locationRight(gloc);
        this.add(attackModNumber, gloc);

        // position the attack mod panel
        modPanel = new JPanel();
        this.updateModPanel();
        this.locationMid(gloc);
        this.add(modPanel, gloc);

        /*JSeparator modRangeSep = new JSeparator(SwingConstants.HORIZONTAL);
        modRangeSep.setMaximumSize(new Dimension(500, 10));
        modRangeSep.setPreferredSize(new Dimension(200, 3));
        this.add(modRangeSep);*/

        JLabel attackMoveLabel = new JLabel("Attacker Movement Modifier:");
        attackMoveLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        this.locationLeft(gloc);
        this.add(attackMoveLabel, gloc);

        JLabel attackMoveNumber = new JLabel("+0");
        attackMoveNumber.setHorizontalAlignment(SwingConstants.RIGHT);
        this.locationRight(gloc);
        this.add(attackMoveNumber, gloc);

        JLabel targetMoveLabel = new JLabel("Target Movement Modifier:");
        targetMoveLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        this.locationLeft(gloc);
        this.add(targetMoveLabel, gloc);

        JLabel targetMoveNumber = new JLabel("+2");
        targetMoveNumber.setHorizontalAlignment(SwingConstants.RIGHT);
        this.locationRight(gloc);
        this.add(targetMoveNumber, gloc);

        JLabel rangeLabel = new JLabel("Range Modifier:");
        rangeLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        this.locationLeft(gloc);
        this.add(rangeLabel, gloc);

        JLabel rangeNumber = new JLabel("+2");
        rangeNumber.setHorizontalAlignment(SwingConstants.RIGHT);
        this.locationRight(gloc);
        this.add(rangeNumber, gloc);

        // build and position the range selector panel
        rangePanel = new JPanel();
        this.updateRangePanel();
        this.locationMid(gloc);
        this.add(rangePanel, gloc);

        JLabel attackHeatLabel = new JLabel("Current Heat Level Modifier:");
        attackHeatLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        this.locationLeft(gloc);
        this.add(attackHeatLabel, gloc);

        JLabel attackHeatNumber = new JLabel("+0");
        attackHeatNumber.setHorizontalAlignment(SwingConstants.RIGHT);
        this.locationRight(gloc);
        this.add(attackHeatNumber, gloc);

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

        JLabel attackDamModLabel = new JLabel("Targeting System Damage Modifier:");
        attackDamModLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        this.locationLeft(gloc);
        this.add(attackDamModLabel, gloc);

        JLabel attackDamModNumber = new JLabel("+0");
        attackDamModNumber.setHorizontalAlignment(SwingConstants.RIGHT);
        this.locationRight(gloc);
        this.add(attackDamModNumber, gloc);

        // have everything from here down stick to the bottom of the page
        JLabel toHitLabel = new JLabel("Roll Needed to Hit:");
        toHitLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        gloc.gridwidth = 1;
        gloc.gridy++;
        gloc.gridx = 0;
        gloc.weighty = 1.0;
        gloc.anchor = GridBagConstraints.SOUTHWEST;
        this.add(toHitLabel, gloc);

        JLabel toHitNumber = new JLabel("9");
        toHitNumber.setHorizontalAlignment(SwingConstants.RIGHT);
        gloc.gridx = 3;
        gloc.weighty = 1.0;
        gloc.anchor = GridBagConstraints.SOUTHEAST;
        this.add(toHitNumber, gloc);

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
    }

    public static String convertToMultiline(String orig) {
        return "<html>" + orig.replaceAll("\n", "<br>");
    }

    private void locationLeft(GridBagConstraints loc) {
        loc.gridx = 0;
        loc.gridy++;
        loc.gridwidth = 2;
        loc.anchor = GridBagConstraints.EAST;
    }

    private void locationRight(GridBagConstraints loc) {
        loc.gridx = 3;
        loc.gridwidth = 1;
        loc.anchor = GridBagConstraints.EAST;
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
        modPanel.setLayout(new BoxLayout(modPanel, BoxLayout.Y_AXIS));
        modPanel.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        JCheckBox indirectCheckBox = new JCheckBox("Indirect Fire");
        modPanel.add(indirectCheckBox);
        JCheckBox partialCheckBox = new JCheckBox("Partial Cover");
        modPanel.add(partialCheckBox);
        JCheckBox woodsCheckBox = new JCheckBox("Woods");
        modPanel.add(woodsCheckBox);
        JCheckBox rearArmorCheckBox = new JCheckBox("Rear Armor");
        modPanel.add(rearArmorCheckBox);
        JCheckBox aftArcCheckBox = new JCheckBox("Aft Firing Arc");
        modPanel.add(aftArcCheckBox);
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

    private void updateRangePanelOld(){
        rangePanel.setLayout(new GridLayout(1, 4));
        rangePanel.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        ButtonGroup rangeButtons = new ButtonGroup();

        JPanel shortPanel = new JPanel();
        shortPanel.setLayout(new BoxLayout(shortPanel, BoxLayout.Y_AXIS));
        JLabel shortLabel = new JLabel("Short ");
        shortLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        shortLabel.setHorizontalAlignment(SwingConstants.CENTER);
        shortPanel.add(shortLabel);
        JRadioButton shortButton = new JRadioButton();
        rangeButtons.add(shortButton);
        shortPanel.add(shortButton);
        shortButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        rangePanel.add(shortPanel);

        JPanel medPanel = new JPanel();
        medPanel.setLayout(new BoxLayout(medPanel, BoxLayout.Y_AXIS));
        JLabel medLabel = new JLabel("Med");
        medLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        medLabel.setHorizontalAlignment(SwingConstants.CENTER);
        medPanel.add(medLabel);
        JRadioButton medButton = new JRadioButton();
        medButton.setHorizontalAlignment(SwingConstants.CENTER);
        rangeButtons.add(medButton);
        medPanel.add(medButton);
        medButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        rangePanel.add(medPanel);

        JPanel longPanel = new JPanel();
        longPanel.setLayout(new BoxLayout(longPanel, BoxLayout.Y_AXIS));
        JLabel longLabel = new JLabel("Long");
        longLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        longLabel.setHorizontalAlignment(SwingConstants.CENTER);
        longPanel.add(longLabel);
        JRadioButton longButton = new JRadioButton();
        rangeButtons.add(longButton);
        longPanel.add(longButton);
        longButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        rangePanel.add(longPanel);

        JPanel extPanel = new JPanel();
        extPanel.setLayout(new BoxLayout(extPanel, BoxLayout.Y_AXIS));
        JLabel extLabel = new JLabel("Ext");
        extLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        extPanel.add(extLabel);
        JRadioButton extButton = new JRadioButton();
        rangeButtons.add(extButton);
        extPanel.add(extButton);
        extButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        rangePanel.add(extPanel);
    }
}