package StrikeMaster.UI;

import StrikeMaster.MsgManager;

import javax.swing.*;
        import java.awt.*;

public class EndPhasePanel extends JPanel {
    JTextArea msgBoard;

    public EndPhasePanel() {
        this.setLayout(new BorderLayout());

        msgBoard = new JTextArea();
        msgBoard.setLineWrap(true);
        msgBoard.setWrapStyleWord(true); // will keep whole-words together when wrapping

        // create the scroll pane that will hold the msg panel
        JScrollPane msgScroll = new JScrollPane(msgBoard);
        this.add(msgScroll, BorderLayout.CENTER);

        updateMessages();
    }

    public void updateMessages(){
        StringBuilder msgBoardString = new StringBuilder();
        MsgManager.resetIndex();
        while(MsgManager.hasNext()){
            msgBoardString.append(MsgManager.next());
            msgBoardString.append(System.lineSeparator());
        }
        msgBoard.setText(msgBoardString.toString());
    }
}