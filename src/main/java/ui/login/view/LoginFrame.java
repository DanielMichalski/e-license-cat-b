package ui.login.view;

import database.dao.TextsDao;
import ui.login.logic.LoginPresenter;
import util.Const;

import javax.swing.*;
import java.awt.*;

/**
 * Author: Daniel
 * Date: 24.11.13
 */
public class LoginFrame extends JFrame implements IWindowCloser {

    public static final int WIDTH = 280;
    public static final int HEIGHT = 210;

    public LoginFrame() {
        setUpFrame();
        initializeComponents();
    }

    private void setUpFrame() {
        setTitle(TextsDao.getText("view.LoginFrame.title"));
        setSize(WIDTH, HEIGHT);
        setLocationRelativeTo(null);
        setResizable(false);
        getContentPane().setBackground(Const.Colors.MAIN_MENU_BACKGROUND_COLOR);

    }

    private void initializeComponents() {
        LoginPresenter loginPresenter = new LoginPresenter(this);

        FormPanel formPanel = new FormPanel();
        ButtonPanel buttonPanel = new ButtonPanel();

        add(formPanel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.SOUTH);

        loginPresenter.setFirstNameTF(formPanel.getFirstNameTF());
        loginPresenter.setLastNameTF(formPanel.getLastNameTF());
        loginPresenter.setPeselTF(formPanel.getPeselTF());
        loginPresenter.setLoginBtn((buttonPanel.getLoginBtn()));
    }

    @Override
    public void close() {
        dispose();
    }
}
