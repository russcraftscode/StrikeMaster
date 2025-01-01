package StrikeMaster.UI;

import StrikeMaster.MsgManager;

import javax.swing.*;
import java.awt.*;

public class AlertPopup extends JDialog{
 public enum AlertType {ERROR, NOTE, ACK}

    /**
     * Constructs the popup displays entire message log
     */
    public AlertPopup(String title, String msg, AlertType alertType) {
        // TODO add an icon to the west that is selected based on alertType

        this.setTitle(title);
        this.setLayout(new BorderLayout());

        JLabel msgLabel = new JLabel(msg);
        this.add(msgLabel,BorderLayout.CENTER);

        JButton closeButton = new JButton("Close");
        this.add(closeButton, BorderLayout.SOUTH);

        closeButton.addActionListener( close ->{
            this.dispose();
        });

        this.setLocationRelativeTo(null); // make popup appear centered
        this.setVisible(true);
    }


}

