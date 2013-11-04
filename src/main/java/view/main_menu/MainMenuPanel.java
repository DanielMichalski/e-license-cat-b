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
    private JButton startExamBtn;
    private JButton infoAboutExamBtn;

    public MainMenuPanel() {
        setUpPanel();
        initializeComponents();
    }

    private void setUpPanel() {
        setBackground(Const.Colors.mainMenubackgroundColor);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));
    }

    private void initializeComponents() {
        String startExamImageResourcePath = "/images/start_exam.jpg";
        String infoAboutExamImageResourcePath = "/images/information.jpg";

        String startExamBtnLabel = TextsDao.get("view.mainPanel.startExamBtn.text");
        String infoAboutExamBtnLabel = TextsDao.get("view.mainPanel.infoAboutExamBtn.text");

        startExamBtn = createBtn(startExamBtnLabel, startExamImageResourcePath);
        infoAboutExamBtn = createBtn(infoAboutExamBtnLabel, infoAboutExamImageResourcePath);

        startExamBtn.setMargin(new Insets(0, 0, 0, 15));

        add(startExamBtn);
        add(Box.createRigidArea(new Dimension(0, 20)));
        add(infoAboutExamBtn);
    }

    private JButton createBtn(String btnLabel, String imageResource) {
        URL startExamIconResource = getClass().getResource(imageResource);
        Icon startExamIcon = new ImageIcon(startExamIconResource);

        JButton button = new JButton(btnLabel);
        button.setHorizontalTextPosition(SwingConstants.RIGHT);
        button.setPreferredSize(Const.Dimensions.mainMenubtnSize);
        button.setMinimumSize(Const.Dimensions.mainMenubtnSize);
        button.setMaximumSize(Const.Dimensions.mainMenubtnSize);
        button.setForeground(Color.darkGray);
        button.setIcon(startExamIcon);

        return button;
    }

    public JButton getStartExamBtn() {
        return startExamBtn;
    }

    public JButton getInfoAboutExamBtn() {
        return infoAboutExamBtn;
    }
}
