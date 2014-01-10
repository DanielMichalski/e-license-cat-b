package ui.loading_new;

import util.Const;

import javax.swing.*;
import java.awt.*;

/**
 * Author: Daniel
 */
public class LoadingPanel extends JPanel {
    public LoadingPanel() {
        setUpPanel();
        initializeComponents();
    }

    private void setUpPanel() {
        setBackground(Const.Colors.BACKGROUND_COLOR);
    }

    private void initializeComponents() {
        ImageIcon imageIcon = new ImageIcon("loading.gif");
        JLabel image = new JLabel(imageIcon);
        add(image);
    }
}
