package ui.login.view;

import database.dao.TextsDao;
import util.Const;

import javax.swing.*;
import java.awt.*;

/**
 * Author: Daniel
 * Date: 24.11.13
 */
public class FormPanel extends JPanel {
    private JTextField firstNameTF;
    private JTextField lastNameTF;
    private JTextField peselTF;

    public FormPanel() {
        setUpPanel();
        initializeComponents();
    }

    private void setUpPanel() {
        setLayout(new GridLayout(3, 2, 0, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 0, 10));
        setBackground(Const.Colors.BACKGROUND_COLOR);
    }

    private void initializeComponents() {
        String firstBameText = TextsDao.getText("view.login.firstNameLbl.text");
        String lastNameText = TextsDao.getText("view.login.lastNameLbl.text");
        String peselText = TextsDao.getText("view.login.peselLbl.text");

        JLabel firstNameLbl = new JLabel(firstBameText);
        firstNameTF = createTextField();
        JLabel lastNameLbl = new JLabel(lastNameText);
        lastNameTF = createTextField();
        JLabel peselLbl = new JLabel(peselText);
        peselTF = createTextField();

        add(firstNameLbl);
        add(firstNameTF);
        add(lastNameLbl);
        add(lastNameTF);
        add(peselLbl);
        add(peselTF);
    }

    private JTextField createTextField() {
        JTextField jTextField = new JTextField();

        jTextField.setColumns(30);
        jTextField.setPreferredSize(new Dimension(250, 30));
        jTextField.setMinimumSize(new Dimension(250, 30));
        jTextField.setMaximumSize(new Dimension(250, 30));

        return jTextField;
    }

    public JTextField getFirstNameTF() {
        return firstNameTF;
    }

    public JTextField getLastNameTF() {
        return lastNameTF;
    }

    public JTextField getPeselTF() {
        return peselTF;
    }
}
