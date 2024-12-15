package StrikeMaster;

import javax.swing.*;
import java.awt.*;

public class labelSizer {
    private static final int HORIZONTAL_BUFFER = 10;
    private static final int VERTICAL_BUFFER = 3;

    /**
     * @param label JLabel object to be sized
     * @return Dimension object of the label
     */
    public static Dimension dimension(JLabel label){
        FontMetrics titleMetrics = label.getFontMetrics(label.getFont());
        return new Dimension(titleMetrics.stringWidth(label.getText())+HORIZONTAL_BUFFER,
                titleMetrics.getHeight() + VERTICAL_BUFFER);
    }

}
