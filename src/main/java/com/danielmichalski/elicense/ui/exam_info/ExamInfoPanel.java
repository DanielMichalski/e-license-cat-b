package com.danielmichalski.elicense.ui.exam_info;

import com.danielmichalski.elicense.util.ImageUtils;

import javax.swing.*;
import java.awt.*;

/**
 * Author: Daniel
 */
public class ExamInfoPanel extends JPanel {
    private JLabel aboutApp;

    public ExamInfoPanel() {
        setUpPanel();
        initializeComponents();
    }

    private void setUpPanel() {
        setLayout(null);
    }

    private void initializeComponents() {
        aboutApp = createBtn("info_poczatek-button2.png", 220, 330);
        add(aboutApp);
    }

    private JLabel createBtn(String imgUnPath, int x, int y) {
        final ImageIcon imgUn = ImageUtils.getProgramImage(imgUnPath);

        JLabel button = new JLabel(imgUn);
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        button.setBounds(x, y, 190, 30);
        return button;
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        ImageIcon programImage = ImageUtils.getProgramImage("info_poczatek-bg.png");
        g.drawImage(programImage.getImage(), 0, 0, null);
    }

    public JLabel getStartExamLbl() {
        return aboutApp;
    }
}
