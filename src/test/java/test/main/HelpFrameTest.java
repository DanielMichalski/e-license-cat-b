package test.main;

import ui.help.view.HelpDialog;

import javax.swing.*;

/**
 * Author: Daniel
 */
public class HelpFrameTest {
    public static void main(String[] args) {
        HelpDialog helpFrame = new HelpDialog();
        helpFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        helpFrame.setVisible(true);
    }
}
