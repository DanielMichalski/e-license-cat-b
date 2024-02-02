package com.danielmichalski.elicense.ui.loading_new;

import com.danielmichalski.elicense.util.ApplicationUtils;

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
        setIgnoreRepaint(false);
        setSize(WIDTH, HEIGHT);
        setLocationRelativeTo(null);
        setUndecorated(true);
    }

    private void initializeComponents() {
        LoadingPanel loadingPanel = new LoadingPanel();
        add(loadingPanel);
    }


}
