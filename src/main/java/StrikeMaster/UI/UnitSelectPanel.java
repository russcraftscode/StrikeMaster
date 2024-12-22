package StrikeMaster.UI;

import StrikeMaster.UnitManager;
import StrikeMaster.Units.Unit;
import StrikeMaster.LabelSizer;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;


/**
 * This panel allows the player to select a unit
 */
public class UnitSelectPanel extends JPanel {
    public static final int ATTACK = 1;
    public static final int TARGET = 2;
    public static final int MOVE = 3;

    private final int panelType;

    private BufferedImage mechIcon;
    private BufferedImage armorIcon;
    private BufferedImage infIcon;
    private BufferedImage artyIcon;
    private BufferedImage vtolIcon;

    // counter circle icons
    private BufferedImage one0Icon;
    private BufferedImage one1Icon;
    private BufferedImage two0Icon;
    private BufferedImage two1Icon;
    private BufferedImage two2Icon;
    private BufferedImage three0Icon;
    private BufferedImage three1Icon;
    private BufferedImage three2Icon;
    private BufferedImage three3Icon;
    private BufferedImage four0Icon;
    private BufferedImage four1Icon;
    private BufferedImage four2Icon;
    private BufferedImage four3Icon;
    private BufferedImage four4Icon;
    private BufferedImage five0Icon;
    private BufferedImage five1Icon;
    private BufferedImage five2Icon;
    private BufferedImage five3Icon;
    private BufferedImage five4Icon;
    private BufferedImage five5Icon;
    private BufferedImage six0Icon;
    private BufferedImage six1Icon;
    private BufferedImage six2Icon;
    private BufferedImage six3Icon;
    private BufferedImage six4Icon;
    private BufferedImage six5Icon;
    private BufferedImage six6Icon;
    private BufferedImage seven0Icon;
    private BufferedImage seven1Icon;
    private BufferedImage seven2Icon;
    private BufferedImage seven3Icon;
    private BufferedImage seven4Icon;
    private BufferedImage seven5Icon;
    private BufferedImage seven6Icon;
    private BufferedImage seven7Icon;
    private BufferedImage eight0Icon;
    private BufferedImage eight1Icon;
    private BufferedImage eight2Icon;
    private BufferedImage eight3Icon;
    private BufferedImage eight4Icon;
    private BufferedImage eight5Icon;
    private BufferedImage eight6Icon;
    private BufferedImage eight7Icon;
    private BufferedImage eight8Icon;
    private BufferedImage nine0Icon;
    private BufferedImage nine1Icon;
    private BufferedImage nine2Icon;
    private BufferedImage nine3Icon;
    private BufferedImage nine4Icon;
    private BufferedImage nine5Icon;
    private BufferedImage nine6Icon;
    private BufferedImage nine7Icon;
    private BufferedImage nine8Icon;
    private BufferedImage nine9Icon;
    private BufferedImage ten0Icon;
    private BufferedImage ten1Icon;
    private BufferedImage ten2Icon;
    private BufferedImage ten3Icon;
    private BufferedImage ten4Icon;
    private BufferedImage ten5Icon;
    private BufferedImage ten6Icon;
    private BufferedImage ten7Icon;
    private BufferedImage ten8Icon;
    private BufferedImage ten9Icon;
    private BufferedImage ten10Icon;

    private final JPanel unitDataPanel = new JPanel();
    private final JButton editUnitButton = new JButton("Edit Unit");
    private final JLabel panelLabel = new JLabel();

    private UnitManager unitManager; // TODO this needs to be replaced with singleton

    // create a button group for all unit select radio buttons
    private final ButtonGroup unitSelectGroup = new ButtonGroup();

    private ArrayList<Unit> units = new ArrayList<>();

    private int selectedUnitId = 0;

    //private ArrayList<ArrayList<JComponent>> unitRows = new ArrayList<>();
    private ArrayList<UnitEntryPanel> unitEntryPanels = new ArrayList<UnitEntryPanel>();
    //private ArrayList<TargetUnitEntryPanel> unitSubPanels = new ArrayList<AttackUnitEntryPanel>();
//TODO make a move unit sub panel
    public UnitSelectPanel(int panelType) {


        // TODO delete this prototyping
        unitManager = new UnitManager();
        unitManager.getUnit(0).setFCHits(1);

        // end prototyping

        //this.setLayout(new BorderLayout());
        this.setLayout(new GridBagLayout());


        // define the type of unit select panel
        this.panelType = panelType;
        switch (this.panelType) {
            case UnitSelectPanel.ATTACK:
                this.panelLabel.setText("Select Unit to make attack");
                break;
            case UnitSelectPanel.TARGET:
                this.panelLabel.setText("Select Unit to target");
                break;
            default:
                this.panelLabel.setText("Select unit to move");
                break;
        }

        // load the graphics
        try {
            this.loadImages();
        } catch (IOException e) {
            // TODO make this exception better
            throw new RuntimeException(e);
        }

        //this.update();
        this.buildUnitPanel();


        //unitDataPanel.setPreferredSize(unitDataPanel.getPreferredSize());

        // Add unit data panel to scroll pane and add that to AttackPanel
        JScrollPane unitScroll = new JScrollPane();
        // anchor the unitDataPanel to the top of the scroll pane if it isn't big enough to scroll
        unitScroll.getViewport().setLayout(new FlowLayout(FlowLayout.LEADING));
        unitScroll.getViewport().add(unitDataPanel);

        editUnitButton.setPreferredSize(new Dimension(editUnitButton.getPreferredSize().width, 20));
        editUnitButton.addActionListener(e -> {
            // TODO pass the selected unit instead of a random unit
            EditUnitPopup editUnitPopup = new EditUnitPopup(this.unitManager.getUnit(0), this);

        });

        // add components to unit select panel
        GridBagConstraints selectLoc = new GridBagConstraints();
        selectLoc.gridy=0;
        //this.add(panelLabel, BorderLayout.NORTH);
        this.add(panelLabel, selectLoc);
        selectLoc.gridy++;
        //this.add(unitScroll, BorderLayout.CENTER);
        this.add(unitScroll, selectLoc);
        //this.add(editUnitButton, BorderLayout.SOUTH);
        selectLoc.gridy++;
        selectLoc.weighty = 1;
        this.add(editUnitButton, selectLoc);
    }

    private void buildUnitPanel(){
        unitDataPanel.setLayout(new GridBagLayout());
        GridBagConstraints gloc = new GridBagConstraints();
        gloc.insets = new Insets(2, 0, 1, 0);
        gloc.gridx = 0;
        gloc.gridy = 0;
        gloc.fill = GridBagConstraints.HORIZONTAL;
        gloc.weightx = 1;

        for (int idNum = 0; idNum < unitManager.getUnitCount(); idNum++) {
            UnitEntryPanel unitEntryPanel;
            switch (this.panelType){
                // TODO add move panel
                case UnitSelectPanel.ATTACK:
                    unitEntryPanel = new AttackUnitEntryPanel(unitManager.getUnit(idNum));
                    break;
                case UnitSelectPanel.TARGET:
                    unitEntryPanel = new TargetUnitEntryPanel(unitManager.getUnit(idNum));
                    break;
                default:
                    unitEntryPanel = new AttackUnitEntryPanel(unitManager.getUnit(idNum));
                    break;
            }
            unitDataPanel.add(unitEntryPanel, gloc);
            unitEntryPanel.setBorder(BorderFactory.createLineBorder(Color.RED)); // DEBUG
            gloc.gridy++;
            unitEntryPanels.add(unitEntryPanel);
        }
    }


    /**
     * This builds swing elements to go in the scroll pane
     */
    public void updateUnits() {
        for(UnitEntryPanel entryPanel : unitEntryPanels){
            entryPanel.updateGraphics();
        }

    }
    public void updateUnitsOld() {
        // Define the layout of the sub panel that will hold all units
        unitDataPanel.setLayout(new GridBagLayout());
        GridBagConstraints gloc = new GridBagConstraints();
        gloc.insets = new Insets(2, 0, 1, 0);

        // create a button group for all unit select radio buttons
        ButtonGroup unitSelectGroup = new ButtonGroup();

        for (int i = 0; i < units.size(); i++) {
            Unit rowUnit = units.get(i);

            ArrayList<JComponent> rowElements = new ArrayList<>();

            gloc.gridy = i;
            gloc.gridx = 0;
            gloc.fill = 1;
            JRadioButton unitSelectButton = new JRadioButton();
            unitSelectGroup.add(unitSelectButton);
            unitDataPanel.add(unitSelectButton, gloc);

            // Create the icon for the unit
            gloc.gridx++; // move to the next col
            JLabel iconLabel;
            switch (rowUnit.getType()) {
                default:
                    iconLabel = new JLabel(new ImageIcon(infIcon));
                    break;
                case 'm':
                    iconLabel = new JLabel(new ImageIcon(mechIcon));
                    break;
            }
            unitDataPanel.add(iconLabel, gloc);
            rowElements.add(iconLabel);


            // Create the name label for the unit
            gloc.gridx++; // move to the next col
            JLabel nameLabel = new JLabel(rowUnit.getName() + " " + rowUnit.getVariant());
            unitDataPanel.add(nameLabel, gloc);
            rowElements.add(nameLabel);

            // if attack panel show damage values
            if (this.panelType == UnitSelectPanel.ATTACK) {
                gloc.gridx++; // move to the next col
                JLabel damageLabel = new JLabel("  Attack Strength "
                        + rowUnit.getShortDmg() + "/"
                        + rowUnit.getMedDmg() + "/"
                        + rowUnit.getLongDmg());
                unitDataPanel.add(damageLabel, gloc);
                rowElements.add(damageLabel);

                gloc.gridx++; // move to the next col
                JLabel wepSysHitsLabel = new JLabel("  Wep Damage");
                unitDataPanel.add(wepSysHitsLabel, gloc);
                rowElements.add(wepSysHitsLabel);

                gloc.gridx++; // move to the next col
                JLabel wepSysHitsImage = new JLabel(new ImageIcon(
                        this.getCounterImage(4, 4- rowUnit.getWepHits())));
                unitDataPanel.add(wepSysHitsImage, gloc);
                rowElements.add(wepSysHitsImage);

                gloc.gridx++; // move to the next col
                JLabel tarSysHitsLabel = new JLabel("  FC Damage");
                unitDataPanel.add(tarSysHitsLabel, gloc);
                rowElements.add(tarSysHitsLabel);

                gloc.gridx++; // move to the next col
                JLabel tarSysHitsImage = new JLabel(new ImageIcon(
                        this.getCounterImage(4, 4- rowUnit.getFCHits())));
                unitDataPanel.add(tarSysHitsImage, gloc);
                rowElements.add(tarSysHitsImage);
            }

            // if target panel show armor / structure values
            if (this.panelType == UnitSelectPanel.TARGET) {
                gloc.gridx++; // move to the next col
                JLabel armorLabel = new JLabel(" Armor:");
                unitDataPanel.add(armorLabel, gloc);
                rowElements.add(armorLabel);
                // Display a graphic for prototyping purposes
                gloc.gridx++; // move to the next col
                JLabel armorImage = new JLabel(new ImageIcon(
                        getCounterImage( rowUnit.getArmorMax(), rowUnit.getArmorCur())));
                unitDataPanel.add(armorImage, gloc);
                rowElements.add(armorImage);
                gloc.gridx++; // move to the next col
                JLabel structureLabel = new JLabel(" Internal:");
                unitDataPanel.add(structureLabel, gloc);
                rowElements.add(structureLabel);
                // Display a graphic for prototyping purposes
                gloc.gridx++; // move to the next col
                JLabel structureImage = new JLabel(new ImageIcon(
                        getCounterImage( rowUnit.getArmorMax(), rowUnit.getArmorCur())));
                unitDataPanel.add(structureImage, gloc);
                rowElements.add(structureImage);
                gloc.gridx++; // move to the next col
                JLabel tmmLabel = new JLabel(" TMM: ");
                unitDataPanel.add(tmmLabel, gloc);
                rowElements.add(tmmLabel);
                gloc.gridx++; // move to the next col
                JLabel tmmValue = new JLabel(rowUnit.getTMM() + " ");
                tmmValue.setPreferredSize(LabelSizer.dimension(tmmValue));
                unitDataPanel.add(tmmValue, gloc);
                rowElements.add(tmmValue);
            }


            // Darken every other line
            if (i % 2 == 0) {
                for (JComponent component : rowElements) {
                    component.setBackground(Color.LIGHT_GRAY);
                    component.setOpaque(true);
                }
            }
        }

        //unitDataPanel.setPreferredSize(unitDataPanel.getPreferredSize());

        // Add unit data panel to scroll pane and add that to AttackPanel
        JScrollPane unitScroll = new JScrollPane();
        // anchor the unitDataPanel to the top of the scroll pane if it isn't big enough to scroll
        unitScroll.getViewport().setLayout(new FlowLayout(FlowLayout.LEADING));
        unitScroll.getViewport().add(unitDataPanel);

        editUnitButton.setPreferredSize(new Dimension(editUnitButton.getPreferredSize().width, 20));
        editUnitButton.addActionListener(e -> {
            // TODO pass the selected unit instead of a random unit
            EditUnitPopup editUnitPopup = new EditUnitPopup(this.units.get(0), this);

        });

        // add components to unit select panel
        GridBagConstraints selectLoc = new GridBagConstraints();
        selectLoc.gridy=0;
        //this.add(panelLabel, BorderLayout.NORTH);
        this.add(panelLabel, selectLoc);
        selectLoc.gridy++;
        //this.add(unitScroll, BorderLayout.CENTER);
        this.add(unitScroll, selectLoc);
        //this.add(editUnitButton, BorderLayout.SOUTH);
        selectLoc.gridy++;
        selectLoc.weighty = 1;
        this.add(editUnitButton, selectLoc);


    }

    private void loadImages() throws IOException {
        ClassLoader classLoader = getClass().getClassLoader();

        mechIcon = ImageIO.read(classLoader.getResourceAsStream("simpleMechV3.png"));
        infIcon = ImageIO.read(classLoader.getResourceAsStream("simpleInf.png"));
        armorIcon = ImageIO.read(classLoader.getResourceAsStream("simpleArmorInf.png"));
        artyIcon = ImageIO.read(classLoader.getResourceAsStream("simpleArty.png"));
        vtolIcon = ImageIO.read(classLoader.getResourceAsStream("simpleVtol.png"));

        one0Icon = ImageIO.read(classLoader.getResourceAsStream("0of1.png"));
        one1Icon = ImageIO.read(classLoader.getResourceAsStream("1of1.png"));
        two0Icon = ImageIO.read(classLoader.getResourceAsStream("0of2.png"));
        two1Icon = ImageIO.read(classLoader.getResourceAsStream("1of2.png"));
        two2Icon = ImageIO.read(classLoader.getResourceAsStream("2of2.png"));
        three0Icon = ImageIO.read(classLoader.getResourceAsStream("0of3.png"));
        three1Icon = ImageIO.read(classLoader.getResourceAsStream("1of3.png"));
        three2Icon = ImageIO.read(classLoader.getResourceAsStream("2of3.png"));
        three3Icon = ImageIO.read(classLoader.getResourceAsStream("3of3.png"));
        four0Icon = ImageIO.read(classLoader.getResourceAsStream("0of4.png"));
        four1Icon = ImageIO.read(classLoader.getResourceAsStream("1of4.png"));
        four2Icon = ImageIO.read(classLoader.getResourceAsStream("2of4.png"));
        four3Icon = ImageIO.read(classLoader.getResourceAsStream("3of4.png"));
        four4Icon = ImageIO.read(classLoader.getResourceAsStream("4of4.png"));
        five0Icon = ImageIO.read(classLoader.getResourceAsStream("0of5.png"));
        five1Icon = ImageIO.read(classLoader.getResourceAsStream("1of5.png"));
        five2Icon = ImageIO.read(classLoader.getResourceAsStream("2of5.png"));
        five3Icon = ImageIO.read(classLoader.getResourceAsStream("3of5.png"));
        five4Icon = ImageIO.read(classLoader.getResourceAsStream("4of5.png"));
        five5Icon = ImageIO.read(classLoader.getResourceAsStream("5of5.png"));
        six0Icon = ImageIO.read(classLoader.getResourceAsStream("0of6.png"));
        six1Icon = ImageIO.read(classLoader.getResourceAsStream("1of6.png"));
        six2Icon = ImageIO.read(classLoader.getResourceAsStream("2of6.png"));
        six3Icon = ImageIO.read(classLoader.getResourceAsStream("3of6.png"));
        six4Icon = ImageIO.read(classLoader.getResourceAsStream("4of6.png"));
        six5Icon = ImageIO.read(classLoader.getResourceAsStream("5of6.png"));
        six6Icon = ImageIO.read(classLoader.getResourceAsStream("6of6.png"));
        seven0Icon = ImageIO.read(classLoader.getResourceAsStream("0of7.png"));
        seven1Icon = ImageIO.read(classLoader.getResourceAsStream("1of7.png"));
        seven2Icon = ImageIO.read(classLoader.getResourceAsStream("2of7.png"));
        seven3Icon = ImageIO.read(classLoader.getResourceAsStream("3of7.png"));
        seven4Icon = ImageIO.read(classLoader.getResourceAsStream("4of7.png"));
        seven5Icon = ImageIO.read(classLoader.getResourceAsStream("5of7.png"));
        seven6Icon = ImageIO.read(classLoader.getResourceAsStream("6of7.png"));
        seven7Icon = ImageIO.read(classLoader.getResourceAsStream("7of7.png"));
        eight0Icon = ImageIO.read(classLoader.getResourceAsStream("0of8.png"));
        eight1Icon = ImageIO.read(classLoader.getResourceAsStream("1of8.png"));
        eight2Icon = ImageIO.read(classLoader.getResourceAsStream("2of8.png"));
        eight3Icon = ImageIO.read(classLoader.getResourceAsStream("3of8.png"));
        eight4Icon = ImageIO.read(classLoader.getResourceAsStream("4of8.png"));
        eight5Icon = ImageIO.read(classLoader.getResourceAsStream("5of8.png"));
        eight6Icon = ImageIO.read(classLoader.getResourceAsStream("6of8.png"));
        eight7Icon = ImageIO.read(classLoader.getResourceAsStream("7of8.png"));
        eight8Icon = ImageIO.read(classLoader.getResourceAsStream("8of8.png"));
        nine0Icon = ImageIO.read(classLoader.getResourceAsStream("0of9.png"));
        nine1Icon = ImageIO.read(classLoader.getResourceAsStream("1of9.png"));
        nine2Icon = ImageIO.read(classLoader.getResourceAsStream("2of9.png"));
        nine3Icon = ImageIO.read(classLoader.getResourceAsStream("3of9.png"));
        nine4Icon = ImageIO.read(classLoader.getResourceAsStream("4of9.png"));
        nine5Icon = ImageIO.read(classLoader.getResourceAsStream("5of9.png"));
        nine6Icon = ImageIO.read(classLoader.getResourceAsStream("6of9.png"));
        nine7Icon = ImageIO.read(classLoader.getResourceAsStream("7of9.png"));
        nine8Icon = ImageIO.read(classLoader.getResourceAsStream("8of9.png"));
        nine9Icon = ImageIO.read(classLoader.getResourceAsStream("9of9.png"));
        ten0Icon = ImageIO.read(classLoader.getResourceAsStream("0of10.png"));
        ten1Icon = ImageIO.read(classLoader.getResourceAsStream("1of10.png"));
        ten2Icon = ImageIO.read(classLoader.getResourceAsStream("2of10.png"));
        ten3Icon = ImageIO.read(classLoader.getResourceAsStream("3of10.png"));
        ten4Icon = ImageIO.read(classLoader.getResourceAsStream("4of10.png"));
        ten5Icon = ImageIO.read(classLoader.getResourceAsStream("5of10.png"));
        ten6Icon = ImageIO.read(classLoader.getResourceAsStream("6of10.png"));
        ten7Icon = ImageIO.read(classLoader.getResourceAsStream("7of10.png"));
        ten8Icon = ImageIO.read(classLoader.getResourceAsStream("8of10.png"));
        ten9Icon = ImageIO.read(classLoader.getResourceAsStream("9of10.png"));
        ten10Icon = ImageIO.read(classLoader.getResourceAsStream("10of10.png"));
    }

    public BufferedImage getCounterImage(int total, int current) {
        switch (total) {
            case 1:
                switch (current) {
                    case 1:
                        return one1Icon;
                    default:
                        return one0Icon;
                }
            case 2:
                switch (current) {
                    case 1:
                        return two1Icon;
                    case 2:
                        return two2Icon;
                    default:
                        return two0Icon;
                }
            case 3:
                switch (current) {
                    case 1:
                        return three1Icon;
                    case 2:
                        return three2Icon;
                    case 3:
                        return three3Icon;
                    default:
                        return three0Icon;
                }
            case 4:
                switch (current) {
                    case 1:
                        return four1Icon;
                    case 2:
                        return four2Icon;
                    case 3:
                        return four3Icon;
                    case 4:
                        return four4Icon;
                    default:
                        return four0Icon;
                }
            case 5:
                switch (current) {
                    case 1:
                        return five1Icon;
                    case 2:
                        return five2Icon;
                    case 3:
                        return five3Icon;
                    case 4:
                        return five4Icon;
                    case 5:
                        return five5Icon;
                    default:
                        return five0Icon;
                }
            case 6:
                switch (current) {
                    case 1:
                        return six1Icon;
                    case 2:
                        return six2Icon;
                    case 3:
                        return six3Icon;
                    case 4:
                        return six4Icon;
                    case 5:
                        return six5Icon;
                    case 6:
                        return six6Icon;
                    default:
                        return six0Icon;
                }
            case 7:
                switch (current) {
                    case 1:
                        return seven1Icon;
                    case 2:
                        return seven2Icon;
                    case 3:
                        return seven3Icon;
                    case 4:
                        return seven4Icon;
                    case 5:
                        return seven5Icon;
                    case 6:
                        return seven6Icon;
                    case 7:
                        return seven7Icon;
                    default:
                        return seven0Icon;
                }
            case 8:
                switch (current) {
                    case 1:
                        return eight1Icon;
                    case 2:
                        return eight2Icon;
                    case 3:
                        return eight3Icon;
                    case 4:
                        return eight4Icon;
                    case 5:
                        return eight5Icon;
                    case 6:
                        return eight6Icon;
                    case 7:
                        return eight7Icon;
                    case 8:
                        return eight8Icon;
                    default:
                        return eight0Icon;
                }
            case 9:
                switch (current) {
                    case 1:
                        return nine1Icon;
                    case 2:
                        return nine2Icon;
                    case 3:
                        return nine3Icon;
                    case 4:
                        return nine4Icon;
                    case 5:
                        return nine5Icon;
                    case 6:
                        return nine6Icon;
                    case 7:
                        return nine7Icon;
                    case 8:
                        return nine8Icon;
                    case 9:
                        return nine9Icon;
                    default:
                        return nine0Icon;
                }
            case 10:
                switch (current) {
                    case 1:
                        return ten1Icon;
                    case 2:
                        return ten2Icon;
                    case 3:
                        return ten3Icon;
                    case 4:
                        return ten4Icon;
                    case 5:
                        return ten5Icon;
                    case 6:
                        return ten6Icon;
                    case 7:
                        return ten7Icon;
                    case 8:
                        return ten8Icon;
                    case 9:
                        return ten9Icon;
                    case 10:
                        return ten10Icon;
                    default:
                        return ten0Icon;
                }
            default:
                return one1Icon;
        }
    }

    class UnitEntryPanel extends JPanel{

        public UnitEntryPanel(){

        }

        public UnitEntryPanel(Unit unit){

            this.updateGraphics();
        }

        public void updateGraphics(){

            this.revalidate();
            this.repaint();
        }


    }

    class AttackUnitEntryPanel extends UnitEntryPanel{
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

        private Unit unit;

        public AttackUnitEntryPanel(Unit unit){
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

            this.updateGraphics();
        }

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
                    iconLabel.setIcon(new ImageIcon(infIcon));
                    break;
                case 'm':
                    iconLabel.setIcon(new ImageIcon(mechIcon));
                    break;
            }

            wepSysHitsImage.setIcon(new ImageIcon(
                    getCounterImage(4, 4- unit.getWepHits())));

            tarSysHitsImage.setIcon(new ImageIcon(
                    getCounterImage(4, 4- unit.getFCHits())));

            this.revalidate();
            this.repaint();
        }


    }
    class TargetUnitEntryPanel extends UnitEntryPanel{
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

        private Unit unit;

        public TargetUnitEntryPanel(Unit unit){
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
            //structureImage.setHorizontalAlignment(SwingConstants.RIGHT);
            //structureImage.setBorder(BorderFactory.createLineBorder(Color.GREEN));// DEBUG

            this.updateGraphics();
        }

        public void updateGraphics(){
            nameLabel.setText(unit.getName() + " " + unit.getVariant());

            sideLabel.setText("Red");

            idLabel.setText(String.valueOf(unit.getID()));

            tmmLabel.setText(" Target Movement Mod: " + unit.getTMM());

            switch (unit.getType()) {
                // TODO add other unit types
                default:
                    iconLabel.setIcon(new ImageIcon(infIcon));
                    break;
                case 'm':
                    iconLabel.setIcon(new ImageIcon(mechIcon));
                    break;
            }

            armorImage.setIcon(new ImageIcon(
                    getCounterImage(unit.getArmorMax(), unit.getArmorCur())));

            structureImage.setIcon(new ImageIcon(
                    getCounterImage(unit.getStructureMax(), unit.getStructureCur())));

            this.revalidate();
            this.repaint();
        }


    }


}
