package StrikeMaster;

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



        this.setLayout(new BorderLayout());

        // combatPanel holds all elements needed only in the combat phase
        // TODO move combatPanel into its own class
        JPanel combatPanel = new JPanel();
       combatPanel.setLayout(new BoxLayout(combatPanel,BoxLayout.X_AXIS));

        UnitSelectPanel attackerSelectPanel = new UnitSelectPanel(UnitSelectPanel.ATTACK);

        // TODO create attack options panel
        AttackOptionsPanel attackOptionsPanel = new AttackOptionsPanel();
        UnitSelectPanel targetSelectPanel = new UnitSelectPanel(UnitSelectPanel.TARGET);

        combatPanel.add(attackerSelectPanel);
        combatPanel.add(targetSelectPanel);
        combatPanel.add(attackOptionsPanel);

        // phase select panel holds all the elements to switch phases and end the turn
        // TODO move phasePanel to its own class
        JPanel phasePanel = new JPanel();
        //phasePanel.setBorder(BorderFactory.createLineBorder(Color.BLUE)); // DEBUG
        phasePanel.setPreferredSize(new Dimension(150,0));
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

        //this.headerPanel.setBorder(BorderFactory.createLineBorder(Color.GREEN)); // DEBUG


        this.add(headerPanel, BorderLayout.NORTH);
        this.add(combatPanel, BorderLayout.CENTER);
        this.add(phasePanel, BorderLayout.WEST);

        //System.setProperty("sun.java2d.uiScale", "1.0");
        if(hiRezMode){
            //setMinimumSize(new Dimension(1000, 1200)); // Minimum size
            setSize(new Dimension(1100, 600));       // Default size
        }else{
            setMinimumSize(new Dimension(1000, 600)); // Minimum size
            //setSize(new Dimension(1000, 700));       // Default size
        }
        //System.setProperty("sun.java2d.uiScale", "2.0");


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
