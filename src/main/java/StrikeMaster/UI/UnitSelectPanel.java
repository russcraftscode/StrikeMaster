package StrikeMaster.UI;

import StrikeMaster.UnitManager;
import StrikeMaster.Units.Unit;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;


/**
 * This panel displays contextually relevant information about all units and allows
 * the user to select a unit. UnitManger tracks which unit is selected.
 */
public class UnitSelectPanel extends JPanel implements Observer {
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

    // create a button group for all unit select radio buttons
    private final ButtonGroup unitSelectGroup = new ButtonGroup();

    private final ArrayList<SingleUnitPanel> singleUnitPanels = new ArrayList<>();

    /**
     * Constructs either an attacker-unit select panel, a target-unit select panel,
     * or a move-unit select panel.
     * @param panelType use static constants to determine ATTACK, TARGET, or MOVE
     */
    public UnitSelectPanel(int panelType) {
        // make this panel an observer of UnitManger
        UnitManager.getInstance().addObserver(this);

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
            //TODO make a move unit sub panel
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

        this.buildUnitPanel();

        // create the scroll pane that will hold the unit data panel
        JScrollPane unitScroll = new JScrollPane();
        unitScroll.getVerticalScrollBar().setUnitIncrement(5); // speed up the scrolling
        // anchor the unitDataPanel to the top of the scroll pane if it isn't big enough to scroll
        unitScroll.getViewport().setLayout(new FlowLayout(FlowLayout.LEADING));
        unitScroll.setPreferredSize(new Dimension(unitDataPanel.getPreferredSize().width+30, 200));
        // Add unit data panel to scroll pane and add that to AttackPanel
        unitScroll.getViewport().add(unitDataPanel);

        editUnitButton.setPreferredSize(new Dimension(editUnitButton.getPreferredSize().width, 20));
        editUnitButton.addActionListener(e -> {
            EditUnitPopup editUnitPopup;
            // pass a different unit to the popup depending on which type of panel this is
            switch (this.panelType){
                // TODO add move panel
                case UnitSelectPanel.ATTACK:
                    editUnitPopup = new EditUnitPopup(UnitManager.getSelectedAttacker());
                    break;
                case UnitSelectPanel.TARGET:
                    editUnitPopup = new EditUnitPopup(UnitManager.getSelectedTarget());
                    break;
                default:
                    editUnitPopup = new EditUnitPopup(UnitManager.getSelectedAttacker());
                    break;
            }
        });

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

    private void buildUnitPanel(){
        unitDataPanel.setLayout(new GridBagLayout());
        GridBagConstraints gloc = new GridBagConstraints();
        gloc.insets = new Insets(2, 0, 1, 0);
        gloc.gridx = 0;
        gloc.gridy = 0;
        gloc.fill = GridBagConstraints.HORIZONTAL;
        gloc.weightx = 1;

        for (int idNum = 0; idNum < UnitManager.getUnitCount(); idNum++) {
            SingleUnitPanel singleUnitPanel;
            switch (this.panelType){
                // TODO add move panel
                case UnitSelectPanel.ATTACK:
                    singleUnitPanel = new AttackSingleUnitPanel(UnitManager.getUnit(idNum));
                    break;
                case UnitSelectPanel.TARGET:
                    singleUnitPanel = new TargetSingleUnitPanel(UnitManager.getUnit(idNum));
                    break;
                default:
                    singleUnitPanel = new AttackSingleUnitPanel(UnitManager.getUnit(idNum));
                    break;
            }
            unitDataPanel.add(singleUnitPanel, gloc);
            gloc.gridy++;
            singleUnitPanels.add(singleUnitPanel);
        }

        // set the default selection to the first  sub panel  to prevent no-unit selected errors
        singleUnitPanels.get(0).setSelected(true);
    }

    /**
     * Updates all unit entries to reflect any changes to unit objects.
     */
    public void updateUnits() {
        for(SingleUnitPanel entryPanel : singleUnitPanels){
            entryPanel.updateGraphics();
        }
    }

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

    /**
     * Gets an image of circle counters that represent an x out of y situation.
     * @param total the total number of circles
     * @param current the number of circles not filled in
     * @return the requested image of counters
     */
    private BufferedImage getCounterImage(int total, int current) {
        // TODO consider moving this to it's own class. Maybe a static class.
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

    /**
     * Subclass of SingleUnitPanel that represents a single unit that is making an attack.
     */
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

    /**
     * Subclass of SingleUnitPanel that represents a single unit that is
     * a potential target for an attack.
     */
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

        /**
         * Updates all UI elements to reflect any changes to the unit
         */
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
        public void setSelected(boolean sel){unitSelectButton.setSelected(sel);}

        /**
         * Returns if this panel is selected.
         * @return true if the unit this panel represents is the selected unit
         */
        public boolean isSelected(){
            return unitSelectButton.isSelected();
        }
    }

}
