package test.main;

import ui.login.view.LoginFrame;

import javax.swing.*;

/**
 * Author: Daniel
 */
public class LoginTest {
    public static void main(String[] args) {
        LoginFrame loginFrame = new LoginFrame();
        loginFrame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        loginFrame.setVisible(true);
    }
}
