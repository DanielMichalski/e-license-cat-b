package ui.main_menu.view;

import database.dao.TextsDao;
import util.Const;
import media.images.ImageUtils;

import javax.swing.*;
import java.awt.*;

/**
 * Author: dmichalski
 * Date: 01.11.13
 */
public class MainMenuPanel extends JPanel {
    private JButton startExamBtn;
    private JButton infoAboutExamBtn;

    public MainMenuPanel() {
        setUpPanel();
        initializeComponents();
    }

    private void setUpPanel() {
        setBackground(Const.Colors.MAIN_MENU_BACKGROUND_COLOR);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));
    }

    private void initializeComponents() {
        String startExamImageName = TextsDao.getFileName("img.start_exam");
        String infoAboutExamImageName = TextsDao.getFileName("img.information");

        String startExamBtnLabel = TextsDao.getText("view.mainMenuPanel.startExamBtn.text");
        String infoAboutExamBtnLabel = TextsDao.getText("view.mainMenuPanel.infoAboutExamBtn.text");

        startExamBtn = createBtn(startExamBtnLabel, startExamImageName);
        infoAboutExamBtn = createBtn(infoAboutExamBtnLabel, infoAboutExamImageName);

        startExamBtn.setMargin(new Insets(0, 0, 0, 15));

        add(startExamBtn);
        add(Box.createRigidArea(new Dimension(0, 20)));
        add(infoAboutExamBtn);
    }

    private JButton createBtn(String btnLabel, String imageName) {
            ImageIcon imageIcon = ImageUtils.getProgramImage(imageName);
            JButton button = new JButton(btnLabel);
            button.setHorizontalTextPosition(SwingConstants.RIGHT);
            button.setPreferredSize(Const.Dimensions.MAIN_MENUBTN_SIZE);
            button.setMinimumSize(Const.Dimensions.MAIN_MENUBTN_SIZE);
            button.setMaximumSize(Const.Dimensions.MAIN_MENUBTN_SIZE);
            button.setForeground(Color.darkGray);
            button.setIcon(imageIcon);
            return button;
    }

    public JButton getStartExamBtn() {
        return startExamBtn;
    }

    public JButton getInfoAboutExamBtn() {
        return infoAboutExamBtn;
    }
}
