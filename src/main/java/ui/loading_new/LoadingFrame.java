package ui.loading_new;

import util.ApplicationUtils;

import javax.swing.*;

/**
 * Author: Daniel
 */
public class LoadingFrame extends JDialog {

    public static final int HEIGHT = 220;
    public static final int WIDTH = 300;

    public LoadingFrame() {
        setUpFrame();
        initializeComponents();
        ApplicationUtils.setApplicationIcon(this);
        setVisible(true);
        ApplicationUtils.prepareApp(this);
    }

    private void setUpFrame() {
        setSize(WIDTH, HEIGHT);
        setLocationRelativeTo(null);
        setUndecorated(true);
    }

    private void initializeComponents() {
        LoadingPanel loadingPanel = new LoadingPanel();
        add(loadingPanel);
    }


}
