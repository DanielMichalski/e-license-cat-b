package com.danielmichalski.elicense.ui.login.view;

import com.danielmichalski.elicense.database.dao.TextsDao;
import com.danielmichalski.elicense.ui.login.logic.LoginPresenter;
import com.danielmichalski.elicense.ui.main_menu.view.MainMenuFrame;
import com.danielmichalski.elicense.util.ApplicationUtils;
import com.danielmichalski.elicense.util.Const;
import com.danielmichalski.elicense.util.FileUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Author: Daniel
 */
public class LoginFrame extends JFrame implements IWindowCloser {

    public static final int WIDTH = 280;
    public static final int HEIGHT = 210;

    public LoginFrame() {
        setUpFrame();
        initializeComponents();
    }

    private void setUpFrame() {
        setIgnoreRepaint(false);
        setTitle(TextsDao.getText("view.LoginFrame.title"));
        setSize(WIDTH, HEIGHT);
        setLocationRelativeTo(null);
        setResizable(false);
        getContentPane().setBackground(Const.Colors.BACKGROUND_COLOR);
        ApplicationUtils.setApplicationIcon(this);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                EventQueue.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        FileUtils.deleteTempFolderContent();

                        dispose();

                        MainMenuFrame mv = new MainMenuFrame();
                        mv.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
                        mv.setVisible(true);
                    }
                });
            }
        });
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
