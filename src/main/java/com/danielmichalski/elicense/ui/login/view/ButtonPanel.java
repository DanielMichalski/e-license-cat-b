package com.danielmichalski.elicense.ui.login.view;

import com.danielmichalski.elicense.database.dao.TextsDao;
import com.danielmichalski.elicense.util.Const;

import javax.swing.*;

/**
 * Author: Daniel
 */
public class ButtonPanel extends JPanel {
    private JButton loginBtn;

    public ButtonPanel() {
        setUpPanel();
        initializeComponents();
    }

    private void setUpPanel() {
        setBorder(BorderFactory.createEmptyBorder(0, 10, 5, 10));
        setBackground(Const.Colors.BACKGROUND_COLOR);
    }

    private void initializeComponents() {
        String btnText = TextsDao.getText("view.login.LoginBtn.text");
        loginBtn = new JButton(btnText);

        add(loginBtn);
    }

    public JButton getLoginBtn() {
        return loginBtn;
    }
}
