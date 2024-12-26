package StrikeMaster.UI;

import StrikeMaster.UnitManager;

import javax.swing.*;
import java.awt.*;

public class PhasePanel extends JPanel {
    
    public PhasePanel() {
        this.setLayout(new GridLayout(4, 1, 2, 2));
        JButton movePhaseButton = new JButton("Move Phase");
        movePhaseButton.setMinimumSize(new Dimension(150, 50));
        this.add(movePhaseButton);
        JButton combatPhaseButton = new JButton("Combat Phase");
        this.add(combatPhaseButton);
        combatPhaseButton.setPreferredSize(new Dimension(150, 50));
        JButton damagePhaseButton = new JButton("Resolve Damage");
        damagePhaseButton.setPreferredSize(new Dimension(150, 50));
        this.add(damagePhaseButton);
        JButton endRoundButton = new JButton("End Round");
        endRoundButton.setPreferredSize(new Dimension(150, 50));
        this.add(endRoundButton);

        // add logic to buttons

        endRoundButton.addActionListener(e->{
            UnitManager.endRound();
    });
    }
}
