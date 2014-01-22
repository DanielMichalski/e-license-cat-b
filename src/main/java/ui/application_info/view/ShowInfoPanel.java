package ui.application_info.view;

import util.ImageUtils;

import javax.swing.*;
import java.awt.*;

/**
 * Author: Daniel
 */
public class ShowInfoPanel extends JPanel {
    private JLabel aboutApp;

    public ShowInfoPanel() {
        setUpPanel();
        initializeComponents();
    }

    private void setUpPanel() {
        setLayout(null);
    }

    private void initializeComponents() {
        aboutApp = createBtn("bl_ok.png", 150, 960);
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

        ImageIcon programImage = ImageUtils.getProgramImage("info2.png");
        g.drawImage(programImage.getImage(), 0, 0, null);
    }

    public JLabel getAboutApp() {
        return aboutApp;
    }
}
