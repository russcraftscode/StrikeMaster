package StrikeMaster.UI;

import javax.swing.*;
import java.awt.*;

public class CombatPanel extends JPanel {
    public CombatPanel() {
        setLayout(new GridBagLayout());
        GridBagConstraints combatLoc = new GridBagConstraints();

        AttackerSelectPanel attackerSelectPanel = new AttackerSelectPanel();
        TargetSelectPanel targetSelectPanel = new TargetSelectPanel();
        AttackOptionsPanel attackOptionsPanel = new AttackOptionsPanel();

        combatLoc.fill = GridBagConstraints.VERTICAL;
        combatLoc.gridx = 0;
        combatLoc.gridy = 0;
        add(attackerSelectPanel, combatLoc);
        combatLoc.gridx++;
        add(targetSelectPanel, combatLoc);
        combatLoc.gridx++;
        combatLoc.fill = GridBagConstraints.BOTH;
        combatLoc.weighty = 1;
        add(attackOptionsPanel, combatLoc);
    }
}
