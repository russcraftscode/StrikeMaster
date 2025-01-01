package StrikeMaster.UI;

import StrikeMaster.LabelSizer;
import StrikeMaster.MsgManager;
import StrikeMaster.UnitManager;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class HeaderPanel extends JPanel implements Observer {
    private JLabel titleLabel;
    private JLabel modeLabel;
    private JSeparator bar;
    private JTextArea msgBoard;

    public HeaderPanel() {
        MsgManager.getInstance().addObserver(this);

        Font titleFont = new Font("Impact", Font.ITALIC, 24);
        Font modeFont = new Font("Courier", Font.PLAIN, 18);

        this.setLayout(new BorderLayout());

        titleLabel = new JLabel("StrikeMaster");
        // set the size to accommodate all text
        titleLabel.setFont(titleFont);
        FontMetrics titleMetrics = titleLabel.getFontMetrics(titleLabel.getFont());
        titleLabel.setPreferredSize(LabelSizer.dimension(titleLabel));

        System.out.println(titleMetrics.stringWidth(titleLabel.getText()));
        this.add(titleLabel, BorderLayout.WEST);

        modeLabel = new JLabel();
        modeLabel.setFont(modeFont);
        modeLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        modeLabel.setPreferredSize(LabelSizer.dimension(modeLabel));
        this.add(modeLabel, BorderLayout.EAST);

        bar = new JSeparator();
        this.add(bar, BorderLayout.SOUTH);

        msgBoard = new JTextArea();
        msgBoard.setRows(3);
        msgBoard.setLineWrap(true);
        msgBoard.setWrapStyleWord(true); // will keep whole-words together when wrapping

        // Add click-logic to msgboard
        msgBoard.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                MsgLogPopup msgLogPopup = new MsgLogPopup();
            }
        });

        // create the scroll pane that will hold the msg panel
        JScrollPane msgScroll = new JScrollPane(msgBoard);
        this.add(msgScroll, BorderLayout.CENTER);

        this.setPreferredSize(new Dimension(200, 80));
        this.setMinimumSize(new Dimension(200, 80));
    }


    public void setModeText(String mode) {
        this.modeLabel.setText(mode);
        this.modeLabel.setPreferredSize(LabelSizer.dimension(this.modeLabel));
        this.revalidate();
        this.repaint();
    }

    /**
     * This method is called whenever the observed object is changed. An application calls an
     * {@code Observable} object's {@code notifyObservers} method to have all the object's observers
     * notified of the change.
     * @param o   the observable object.
     * @param arg an argument passed to the {@code notifyObservers} method.
     */
    @Override
    public void update(Observable o, Object arg) {
        StringBuilder msgBoardString = new StringBuilder();
        MsgManager.resetIndex();
        while(MsgManager.hasNext()){
            msgBoardString.append(MsgManager.next());
            msgBoardString.append(System.lineSeparator());
        }

        msgBoard.setText(msgBoardString.toString());
        this.repaint();
        this.revalidate();

    }
}
