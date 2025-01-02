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
    private CombatPanel combatPanel;
    private MovePanel movePanel;
    private EndPhasePanel endPhasePanel;

    public AppWindow(boolean hiRezMode) {
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
        combatPanel = new CombatPanel();
        movePanel = new MovePanel();
        endPhasePanel = new EndPhasePanel();

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
        mainLoc.weightx = .1;
        mainLoc.anchor = GridBagConstraints.WEST;
        mainLoc.fill = GridBagConstraints.VERTICAL;
        this.add(phasePanel, mainLoc);
        mainLoc.gridx++;
        mainLoc.weightx = .9;
        mainLoc.weighty = .9;
        mainLoc.anchor = GridBagConstraints.EAST;
        mainLoc.fill = GridBagConstraints.BOTH;
        this.add(combatPanel, mainLoc);
        this.add(movePanel, mainLoc);
        this.add(endPhasePanel, mainLoc);

        changeMode(PhaseName.COMBAT); // set to combat mode for sizing purposes
        this.pack();
        this.setVisible(true);
        changeMode(PhaseName.MOVE);
    }

    public void changeMode(PhaseName mode) {
        switch (mode) {
            case MOVE:
                this.headerPanel.setModeText("Movement Phase  ");
                combatPanel.setVisible(false);
                movePanel.setVisible(true);
                endPhasePanel.setVisible(false);
                break;
            case COMBAT:
                this.headerPanel.setModeText("Combat Phase  ");
                combatPanel.setVisible(true);
                movePanel.setVisible(false);
                endPhasePanel.setVisible(false);
                break;
            default:
                this.headerPanel.setModeText("End Phase  ");
                combatPanel.setVisible(false);
                movePanel.setVisible(false);
                endPhasePanel.setVisible(true);
                endPhasePanel.updateMessages();
                break;
        }
        //this.pack();
        revalidate();
        repaint();
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
