package StrikeMaster.UI;

import StrikeMaster.MsgManager;
import StrikeMaster.UnitManager;
import StrikeMaster.Units.Unit;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MsgLogPopup extends JDialog{

    /**
     * Constructs the popup displays entire message log
     */
    public MsgLogPopup() {
        this.setTitle("Message Log");
        this.setLayout(new BorderLayout());

        JTextArea msgBoard = new JTextArea();
        msgBoard.setLineWrap(true);
        msgBoard.setWrapStyleWord(true); // will keep whole-words together when wrapping

        // create the scroll pane that will hold the msg panel
        JScrollPane msgScroll = new JScrollPane(msgBoard);
        this.add(msgScroll, BorderLayout.CENTER);

        //this.setPreferredSize(this.getPreferredSize());
        this.setPreferredSize(new Dimension(600, 800));
        this.setMinimumSize(new Dimension(600, 800));

        JButton closeButton = new JButton("Close");
        this.add(closeButton, BorderLayout.SOUTH);

        closeButton.addActionListener( close ->{
            this.dispose();
                });

        // add text to message board
        StringBuilder msgBoardString = new StringBuilder();
        MsgManager.resetIndex();
        while(MsgManager.hasNext()){
            msgBoardString.append(MsgManager.next());
            msgBoardString.append(System.lineSeparator());
        }
        msgBoard.setText(msgBoardString.toString());

        this.setLocationRelativeTo(null); // make popup appear centered
        this.setVisible(true);

    }


}
