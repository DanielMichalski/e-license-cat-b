package ui.main_menu.view;

import util.Const;
import util.ImageUtils;

import javax.swing.*;
import java.awt.*;

/**
 * Author: dmichalski
 */
public class MainMenuPanel extends JPanel {
    private JLabel exerciseBtn;
    private JLabel egxamBtn;
    private JLabel aboutApp;
    private JLabel closeBtn;

    public MainMenuPanel() {
        setUpPanel();
        initializeComponents();
    }

    private void setUpPanel() {
        setBackground(Const.Colors.BACKGROUND_COLOR);
        setLayout(null);
    }

    private void initializeComponents() {
        exerciseBtn = createBtn("bl_un.png", 140, 470);
        egxamBtn = createBtn("e_un.png", 390, 470);
        aboutApp = createBtn("op_un.png", 640, 470);
        closeBtn = createCloseBtn();

        add(exerciseBtn);
        add(egxamBtn);
        add(aboutApp);
        add(closeBtn);
    }

    private JLabel createCloseBtn() {
        final ImageIcon imgUn = ImageUtils.getProgramImage("close_btn.png");
        final JLabel button = new JLabel(imgUn);
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        button.setBounds(915, 5, 40, 40);
        return button;
    }

    private JLabel createBtn(String imgUnPath, int x, int y) {
        final ImageIcon imgUn = ImageUtils.getProgramImage(imgUnPath);

        final JLabel button = new JLabel(imgUn);
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        button.setBounds(x, y, 190, 40);
        return button;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        ImageIcon programImage = ImageUtils.getProgramImage("bg.png");
        g.drawImage(programImage.getImage(), 0, 0, null);
    }

    public JLabel getExerciseBtn() {
        return exerciseBtn;
    }

    public JLabel getEgxamBtn() {
        return egxamBtn;
    }

    public JLabel getAboutApp() {
        return aboutApp;
    }

    public JLabel getCloseBtn() {
        return closeBtn;
    }
}
