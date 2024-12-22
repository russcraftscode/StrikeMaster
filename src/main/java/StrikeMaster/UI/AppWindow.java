package StrikeMaster.UI;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;


public class AppWindow extends JFrame {
    HeaderPanel headerPanel = new HeaderPanel();
    private BufferedImage appIcon;

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

        UnitSelectPanel attackerSelectPanel = new UnitSelectPanel(UnitSelectPanel.ATTACK);
        UnitSelectPanel targetSelectPanel = new UnitSelectPanel(UnitSelectPanel.TARGET);
        AttackOptionsPanel attackOptionsPanel = new AttackOptionsPanel();
        //attackerSelectPanel.setPreferredSize(new Dimension(attackerSelectPanel.getPreferredSize().width, attackOptionsPanel.getPreferredSize().height));
        //targetSelectPanel.setPreferredSize(new Dimension(targetSelectPanel.getPreferredSize().width, attackOptionsPanel.getPreferredSize().height));

        attackerSelectPanel.setBorder(BorderFactory.createLineBorder(Color.yellow)); // debug
        targetSelectPanel.setBorder(BorderFactory.createLineBorder(Color.blue)); // debug

        combatLoc.fill = GridBagConstraints.VERTICAL;
        combatLoc.gridx = 0;
        combatLoc.gridy = 0;
        combatPanel.add(attackerSelectPanel, combatLoc);
        combatLoc.gridx++;
        combatPanel.add(targetSelectPanel, combatLoc);
        combatLoc.gridx++;
        combatLoc.fill = GridBagConstraints.BOTH;
        combatPanel.add(attackOptionsPanel, combatLoc);

        // phase select panel holds all the elements to switch phases and end the turn
        // TODO move phasePanel to its own class or method
        JPanel phasePanel = new JPanel();
        phasePanel.setLayout(new GridLayout(4,1,2,2));
        JButton movePhaseButton = new JButton("Move Phase");
        movePhaseButton.setMinimumSize(new Dimension(150,50));
        phasePanel.add(movePhaseButton);
        JButton combatPhaseButton = new JButton("Combat Phase");
        phasePanel.add(combatPhaseButton);
        combatPhaseButton.setPreferredSize(new Dimension(150,50));
        JButton damagePhaseButton = new JButton("Resolve Damage");
        damagePhaseButton.setPreferredSize(new Dimension(150,50));
        phasePanel.add(damagePhaseButton);
        JButton endRoundButton = new JButton("End Round");
        endRoundButton.setPreferredSize(new Dimension(150,50));
        phasePanel.add(endRoundButton);

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

    public void changeMode(String mode) {
        this.headerPanel.setModeText(mode);
    }

    private void loadImages() throws IOException {
        ClassLoader classLoader = getClass().getClassLoader();

        appIcon = ImageIO.read(classLoader.getResourceAsStream("as16.png"));

    }

}
