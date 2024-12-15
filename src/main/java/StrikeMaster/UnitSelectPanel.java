package StrikeMaster;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;


/**
 * This panel allows the player to select the unit that is making an attack
 */
public class UnitSelectPanel extends JPanel {
    public static final int ATTACK = 1;
    public static final int TARGET = 2;
    public static final int MOVE = 3;

    private int panelType = 0;

    private BufferedImage mechIcon;
    private BufferedImage armorIcon;
    private BufferedImage infIcon;
    private BufferedImage artyIcon;
    private BufferedImage vtolIcon;
    private BufferedImage fullIcon;
    private BufferedImage emptyIcon;

    private final JPanel unitDataPanel = new JPanel();
    private final JButton editUnitButton = new JButton("Edit Unit");
    private JLabel panelLabel;

    public UnitSelectPanel(int panelType) {
        this.setLayout(new BorderLayout());

        this.panelType = panelType;

        switch (this.panelType) {
            case UnitSelectPanel.ATTACK:
                this.panelLabel = new JLabel("Select Unit to make attack");

                break;
            case UnitSelectPanel.TARGET:
                this.panelLabel = new JLabel("Select Unit to target");
                break;
            case UnitSelectPanel.MOVE:
                this.panelLabel = new JLabel("Select unit to move");
                break;
        }

        // load the graphics
        try {
            this.loadImages();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        /*try {
            // TODO fix the file structure

            mechIcon = ImageIO.read(new File(
                    "./src/main/resources/simpleMechV3.png"));
            infIcon = ImageIO.read(new File(
                    "./src/main/resources/simpleInf.png"));
            armorIcon = ImageIO.read(new File(
                    "./src/main/resources/simpleArmorInf.png"));
            artyIcon = ImageIO.read(new File(
                    "./src/main/resources/simpleArty.png"));
            vtolIcon = ImageIO.read(new File(
                    "./src/main/resources/simpleVtol.png"));
            fullIcon = ImageIO.read(new File(
                    "./src/main/resources/4of4.png"));
            emptyIcon = ImageIO.read(new File(
                    "./src/main/resources/2of3.png"));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }*/

        this.update();
    }

    /**
     * This builds swing elements to go in the scroll pane
     */
    public void update() {
        // Define the layout of the sub panel that will hold all units
        unitDataPanel.setLayout(new GridBagLayout());
        GridBagConstraints gloc = new GridBagConstraints();
        gloc.insets = new Insets(2, 0, 1, 0);

        // create a button group for all unit select radio buttons
        ButtonGroup unitSelectGroup = new ButtonGroup();

        // create rows of unit data
        for (int i = 1; i < 30; i++) {
            ArrayList<JComponent> rowElements = new ArrayList<>();
            // Create the unit select radio button
            gloc.gridy = i;
            gloc.gridx = 0;
            gloc.fill = 1;
            JRadioButton unitSelectButton = new JRadioButton();
            unitSelectGroup.add(unitSelectButton);
            unitDataPanel.add(unitSelectButton, gloc);

            // Create the icon for the unit
            gloc.gridx++; // move to the next col
            JLabel iconLabel;
            switch (i % 5) {
                default:
                    iconLabel = new JLabel(new ImageIcon(mechIcon));
                    break;
                case 1:
                    iconLabel = new JLabel(new ImageIcon(vtolIcon));
                    break;
                case 2:
                    iconLabel = new JLabel(new ImageIcon(infIcon));
                    break;
                case 3:
                    iconLabel = new JLabel(new ImageIcon(armorIcon));
                    break;
                case 4:
                    iconLabel = new JLabel(new ImageIcon(artyIcon));
                    break;
            }
            unitDataPanel.add(iconLabel, gloc);
            rowElements.add(iconLabel);

            // Create the type label for the unit
            gloc.gridx++; // move to the next col
            JLabel typeLabel = new JLabel();
            switch (i % 5) {
                default:
                    typeLabel.setText(" BM ");
                    break;
                case 1:
                    typeLabel.setText(" VTOL ");
                    break;
                case 2:
                    typeLabel.setText(" Infantry ");
                    break;
                case 3:
                    typeLabel.setText(" BattleArmor ");
                    break;
                case 4:
                    typeLabel.setText(" Artillery ");
                    break;
            }

            unitDataPanel.add(typeLabel, gloc);
            rowElements.add(typeLabel);

            // Create the name label for the unit
            gloc.gridx++; // move to the next col
            JLabel nameLabel = new JLabel(" Unit " + i + " ");
            unitDataPanel.add(nameLabel, gloc);
            rowElements.add(nameLabel);

            // if attack panel show damage values
            if (this.panelType == UnitSelectPanel.ATTACK) {
                gloc.gridx++; // move to the next col
                JLabel damageLabel = new JLabel("  Wep Damage 3/2/2");
                unitDataPanel.add(damageLabel, gloc);
                rowElements.add(damageLabel);
            }

            // if target panel show armor / structure values
            if (this.panelType == UnitSelectPanel.TARGET) {
                gloc.gridx++; // move to the next col
                JLabel armorLabel = new JLabel("Armor:");
                unitDataPanel.add(armorLabel, gloc);
                rowElements.add(armorLabel);
                // Display a graphic for prototyping purposes
                gloc.gridx++; // move to the next col
                JLabel h1Label = new JLabel(new ImageIcon(fullIcon));
                unitDataPanel.add(h1Label, gloc);
                rowElements.add(h1Label);
                gloc.gridx++; // move to the next col
                JLabel structureLabel = new JLabel("Internal:");
                unitDataPanel.add(armorLabel, gloc);
                rowElements.add(structureLabel);
                // Display a graphic for prototyping purposes
                gloc.gridx++; // move to the next col
                JLabel h2Label = new JLabel(new ImageIcon(emptyIcon));
                unitDataPanel.add(h2Label, gloc);
                rowElements.add(h2Label);
            }


            // Darken every other line
            if (i % 2 == 0) {
                for (JComponent component : rowElements) {
                    component.setBackground(Color.LIGHT_GRAY);
                    component.setOpaque(true);
                }
            }
        }


        // Add unit data panel to scroll pane and add that to AttackPanel
        JScrollPane unitScroll = new JScrollPane();
        // anchor the unitDataPanel to the top of the scroll pane if it isn't big enough to scroll
        unitScroll.getViewport().setLayout(new FlowLayout(FlowLayout.LEADING));
        unitScroll.getViewport().add(unitDataPanel);
        this.add(unitScroll, BorderLayout.CENTER);
        // TODO calculate how much width is needed when all components are complete
        switch (this.panelType) {
            case UnitSelectPanel.ATTACK:
                unitScroll.setPreferredSize(new Dimension(250, 200));

                break;
            case UnitSelectPanel.TARGET:
                unitScroll.setPreferredSize(new Dimension(300, 200));
                break;
            case UnitSelectPanel.MOVE:
                unitScroll.setPreferredSize(new Dimension(300, 200));
                break;
        }

        // add panel label
        panelLabel.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(panelLabel, BorderLayout.NORTH);

        // add unit button
        this.add(editUnitButton, BorderLayout.SOUTH);
    }

    private void loadImages() throws IOException {
        ClassLoader classLoader = getClass().getClassLoader();

        mechIcon = ImageIO.read(classLoader.getResourceAsStream("simpleMechV3.png"));
        infIcon = ImageIO.read(classLoader.getResourceAsStream("simpleInf.png"));
        armorIcon = ImageIO.read(classLoader.getResourceAsStream("simpleArmorInf.png"));
        artyIcon = ImageIO.read(classLoader.getResourceAsStream("simpleArty.png"));
        vtolIcon = ImageIO.read(classLoader.getResourceAsStream("simpleVtol.png"));
        fullIcon = ImageIO.read(classLoader.getResourceAsStream("4of4.png"));
        emptyIcon = ImageIO.read(classLoader.getResourceAsStream("2of3.png"));

    }

}
