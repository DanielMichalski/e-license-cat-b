package components;

import util.Const;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

/**
 * Author: Daniel
 */
public class QuestionTextArea extends JTextArea {
    private int howManyToWrapLine;

    public QuestionTextArea(int howManyToWrapLine) {
        this.howManyToWrapLine = howManyToWrapLine;

        setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));
        setFocusable(false);
        setPreferredSize(Const.Dimensions.ABC_BTNS_SIZE);
        setMinimumSize(Const.Dimensions.ABC_BTNS_SIZE);
        setMaximumSize(Const.Dimensions.ABC_BTNS_SIZE);
        setFocusable(false);

        setBackground(Const.Colors.BTN_UNSELECTED_COLOR);
        setEditable(false);
        setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        setLineWrap(true);
        setWrapStyleWord(true);
        Border compoundBorder = BorderFactory.createLineBorder(Const.Colors.BTN_BORDER_COLOR, 1, true);
        setBorder(BorderFactory.createCompoundBorder(compoundBorder,
                BorderFactory.createEmptyBorder(10, 10, 10, 10)));
    }

    @Override
    public Insets getInsets() {
        Insets insets = super.getInsets();
        int right = insets.right;
        int bottom = insets.bottom;
        int left = insets.left;

        if (getText().length() <= howManyToWrapLine) {
            return new Insets(14, left, bottom, right);
        } else {
            return new Insets(5, left, bottom, right);
        }
    }
}
