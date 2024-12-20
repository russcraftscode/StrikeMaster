package StrikeMaster;

import javax.swing.*;
import java.awt.*;


public class HeaderPanel extends JPanel {
    private JLabel titleLabel;
    private JLabel modeLabel;
    private JSeparator bar;

    public HeaderPanel() {
        this.setBorder(BorderFactory.createEmptyBorder(1, 5, 2, 5));

        Font titleFont = new Font("Impact", Font.ITALIC, 24);
        Font modeFont = new Font("Courier", Font.PLAIN, 18);

        this.setLayout(new BorderLayout());

        titleLabel = new JLabel("StrikeMaster");
        // set the size to accommodate all text
        titleLabel.setFont(titleFont);
        FontMetrics titleMetrics = titleLabel.getFontMetrics(titleLabel.getFont());
        titleLabel.setPreferredSize(labelSizer.dimension(titleLabel));

        System.out.println(titleMetrics.stringWidth(titleLabel.getText()));
        this.add(titleLabel, BorderLayout.WEST);

        modeLabel = new JLabel();
        modeLabel.setFont(modeFont);
        modeLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        modeLabel.setPreferredSize(labelSizer.dimension(modeLabel));
        this.add(modeLabel, BorderLayout.EAST);

        bar = new JSeparator();
        this.add(bar, BorderLayout.SOUTH);

        this.setPreferredSize(new Dimension(800, 50));
    }


    public void setModeText(String mode) {
        this.modeLabel.setText(mode);
        this.modeLabel.setPreferredSize(labelSizer.dimension(this.modeLabel));
        this.revalidate();
        this.repaint();
    }


}
