package ui.login.view;

import database.dao.TextsDao;

import javax.swing.*;

/**
 * Author: Daniel
 * Date: 24.11.13
 */
public class ButtonPanel extends JPanel {
    private JButton loginBtn;

    public ButtonPanel() {
        setUpPanel();
        initializeComponents();
    }

    private void setUpPanel() {
        setBorder(BorderFactory.createEmptyBorder(0, 10, 5, 10));
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
