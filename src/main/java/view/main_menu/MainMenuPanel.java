package view.main_menu;

import database.TextsDao;
import util.Const;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

/**
 * Author: dmichalski
 * Date: 01.11.13
 */
public class MainMenuPanel extends JPanel {
    private static final int BUTTON_WIDTH = 280;
    private static final int BUTTON_HEIGHT = 88;

    private JButton registerBtn;

    public MainMenuPanel() {
        setUpPanel();
        initializeComponents();
    }

    private void setUpPanel() {
        setBackground(Const.Colors.backgroundColor);
    }

    private void initializeComponents() {
        // Tworzenie ikon wykorzystywanych do przyciskow
        URL registerIconResource = getClass().getResource("/images/start_exam.jpg");

        Icon registerIcon = new ImageIcon(registerIconResource);

        String registerBtnText = TextsDao.get("view.registerBtn.text");
        registerBtn = new JButton(registerBtnText);
        //registerBtn.setBounds(10, 10, BUTTON_WIDTH, BUTTON_HEIGHT);
        registerBtn.setForeground(Color.darkGray);
        registerBtn.setIcon(registerIcon);
        //registerBtn.setHorizontalAlignment(SwingConstants.LEFT);

        add(registerBtn);
    }

    public JButton getRegisterBtn() {
        return registerBtn;
    }
}
