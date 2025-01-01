package StrikeMaster.UI;

import javax.swing.*;
import java.awt.*;

public class MovePanel extends JPanel {
    public MovePanel() {
        setLayout(new BorderLayout());
        AttackerSelectPanel attackerSelectPanel = new AttackerSelectPanel();
        add(attackerSelectPanel, BorderLayout.CENTER);
    }
}