package StrikeMaster.UI;

import StrikeMaster.MsgManager;

import javax.swing.*;
import java.awt.*;

public class AlertPopup extends JDialog {
    public enum AlertType {ERROR, NOTE, ACK}

    private Font msgFont = new Font("Ariel", Font.PLAIN, 14);
    private Font typeFont = new Font("Impact", Font.ITALIC, 24);

    /**
     * Constructs the popup displays entire message log
     */
    public AlertPopup(String title, String msg, AlertType alertType) {
        // TODO add an icon to the west that is selected based on alertType
        // TODO this looks terrible. Make it look better when you get to lower priority tasks

        this.setTitle(title);
        this.setLayout(new BorderLayout());

        JLabel typeLabel = new JLabel();
        typeLabel.setFont(typeFont);
        switch (alertType) {
            case ACK:
                typeLabel.setText("");
                break;
            case NOTE:
                typeLabel.setText("Note:");
                break;
            default:
                typeLabel.setText("Error:");
                break;
        }
        this.add(typeLabel, BorderLayout.NORTH);

        JLabel msgLabel = new JLabel("   " + msg + "   ");
        msgLabel.setFont(msgFont);
        this.add(msgLabel, BorderLayout.CENTER);

        JButton closeButton = new JButton("Close");
        this.add(closeButton, BorderLayout.SOUTH);

        closeButton.addActionListener(close -> {
            this.dispose();
        });

        this.pack();
        this.setLocationRelativeTo(null); // make popup appear centered
        this.setVisible(true);
    }

}

