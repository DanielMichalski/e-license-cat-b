package ui.loading_new;

import util.Const;
import util.IconUtils;

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
        Image loadingImage = IconUtils.getLoadingIcon().getImage();
        ImageIcon imageIcon = new ImageIcon(loadingImage);
        JLabel image = new JLabel(imageIcon);
        image.setMinimumSize(new Dimension(200, 200));
        image.setMaximumSize(new Dimension(200, 200));
        image.setPreferredSize(new Dimension(200, 200));
        add(image);
    }
}
