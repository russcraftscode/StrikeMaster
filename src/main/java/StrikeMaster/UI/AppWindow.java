package StrikeMaster.UI;

import StrikeMaster.PhaseName;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;


public class AppWindow extends JFrame {
    private final HeaderPanel headerPanel = new HeaderPanel();
    private BufferedImage appIcon;
    private PhasePanel phasePanel;

    public AppWindow( boolean hiRezMode) {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // load the graphics
        try {
            this.loadImages();
            ImageIcon alphaStrikeIcon = new ImageIcon(appIcon);
            this.setIconImage(alphaStrikeIcon.getImage());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        this.setLayout(new GridBagLayout());
        GridBagConstraints mainLoc = new GridBagConstraints();

        // combatPanel holds all elements needed only in the combat phase
        // TODO move combatPanel into its own class
        JPanel combatPanel = new JPanel();
       combatPanel.setLayout(new GridBagLayout());
       GridBagConstraints combatLoc = new GridBagConstraints();

        AttackerSelectPanel attackerSelectPanel = new AttackerSelectPanel();
        TargetSelectPanel targetSelectPanel = new TargetSelectPanel();
        AttackOptionsPanel attackOptionsPanel = new AttackOptionsPanel();

        combatLoc.fill = GridBagConstraints.VERTICAL;
        combatLoc.gridx = 0;
        combatLoc.gridy = 0;
        combatPanel.add(attackerSelectPanel, combatLoc);
        combatLoc.gridx++;
        combatPanel.add(targetSelectPanel, combatLoc);
        combatLoc.gridx++;
        combatLoc.fill = GridBagConstraints.BOTH;
        combatLoc.weighty = 1;
        combatPanel.add(attackOptionsPanel, combatLoc);

        // phase select panel holds all the elements to switch phases and end the turn
        phasePanel = new PhasePanel(this);

        // place all panels on the frame
        mainLoc.gridx = 0;
        mainLoc.gridy = 0;
        mainLoc.gridwidth = 2;
        mainLoc.weightx = 1;
        mainLoc.weighty = 0;
        mainLoc.fill = GridBagConstraints.HORIZONTAL;
        this.add(headerPanel, mainLoc);
        mainLoc.gridy++;
        mainLoc.gridwidth = 1;
        mainLoc.weighty = 1;
        mainLoc.fill = GridBagConstraints.VERTICAL;
        this.add(phasePanel, mainLoc);
        mainLoc.gridx++;
        mainLoc.fill = GridBagConstraints.BOTH;
        this.add(combatPanel, mainLoc);

        this.pack();
        this.setVisible(true);
    }

    public void changeMode(PhaseName mode) {
        switch (mode) {
            case MOVE:
                this.headerPanel.setModeText("Movement Phase  ");
                break;
            case COMBAT:
                this.headerPanel.setModeText("Combat Phase  ");
                break;
            default:
                this.headerPanel.setModeText("End Phase  ");
                break;
        }
    }

    public void changeModeOld(String mode) {
        this.headerPanel.setModeText(mode);
    }

    private void loadImages() throws IOException {
        // TODO move this resposibility to the ImageManger class
        ClassLoader classLoader = getClass().getClassLoader();

        appIcon = ImageIO.read(classLoader.getResourceAsStream("as16.png"));

    }

}
