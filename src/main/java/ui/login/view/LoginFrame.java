package ui.login.view;

import database.dao.TextsDao;
import ui.login.logic.LoginPresenter;
import ui.main_menu.view.MainMenuFrame;
import util.ApplicationUtils;
import util.Const;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Author: Daniel
 * Date: 24.11.13
 */
public class LoginFrame extends JFrame implements IWindowCloser {
    private boolean isStartExam;

    public static final int WIDTH = 280;
    public static final int HEIGHT = 210;

    public LoginFrame(boolean isStartExam) {
        this.isStartExam = isStartExam;

        setUpFrame();
        initializeComponents();
    }

    private void setUpFrame() {
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
                        MainMenuFrame mv = new MainMenuFrame();
                        mv.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
                        mv.setVisible(true);
                    }
                });
            }
        });
    }

    private void initializeComponents() {
        LoginPresenter loginPresenter = new LoginPresenter(this, isStartExam);

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
