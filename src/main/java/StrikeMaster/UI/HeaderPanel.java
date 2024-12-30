package StrikeMaster.UI;

import StrikeMaster.LabelSizer;
import StrikeMaster.MsgManager;
import StrikeMaster.UnitManager;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

public class HeaderPanel extends JPanel implements Observer {
    private JLabel titleLabel;
    private JLabel modeLabel;
    private JSeparator bar;
    private JTextArea msgBoard;

    public HeaderPanel() {
        MsgManager.getInstance().addObserver(this);
        //this.setBorder(BorderFactory.createEmptyBorder(1, 5, 2, 5));

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
        //this.add(msgBoard, BorderLayout.CENTER);

        // create the scroll pane that will hold the msg panel
        JScrollPane msgScroll = new JScrollPane();
        //msgScroll.getVerticalScrollBar().setUnitIncrement(5); // speed up the scrolling
        //msgScroll.getViewport().setLayout(new FlowLayout(FlowLayout.LEADING));
        //msgScroll.setPreferredSize(new Dimension(msgBoard.getPreferredSize().width+30, 0));
        //msgScroll.setPreferredSize(new Dimension(msgBoard.getPreferredSize().width+30, 0));
        msgScroll.getViewport().add(msgBoard);
        this.add(msgScroll, BorderLayout.CENTER);

        //this.setPreferredSize(this.getPreferredSize());
        this.setPreferredSize(new Dimension(200, 100));
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

        //System.getProperty("line.separator")

    }
}
