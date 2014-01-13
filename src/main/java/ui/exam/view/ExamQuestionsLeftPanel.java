package ui.exam.view;

import database.dao.TextsDao;
import encrypt.Encrypter;
import ui.exam.view.components.MediaPanel;
import uk.co.caprica.vlcj.player.embedded.EmbeddedMediaPlayer;
import util.Const;
import util.FilesUtils;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.io.File;

/**
 * Author: Daniel
 * Date: 03.11.13
 */
public class ExamQuestionsLeftPanel extends JPanel {
    private MediaPanel component;
    private EmbeddedMediaPlayer player;

    private JPanel abcBtnPanel;
    private JPanel yesNoBtnPanel;

    private JTextArea questionTextArea;

    private JButton yesBtn;
    private JButton noBtn;
    private JButton btnA;
    private JButton btnB;
    private JButton btnC;

    private Color defaultColor;

    public ExamQuestionsLeftPanel() {
        setUpPanel();
        initializeComponents();
    }

    private void setUpPanel() {
        component = new MediaPanel();
        player = component.getMediaPlayer();

        Border emptyBorder = BorderFactory.createEmptyBorder(10, 10, 10, 10);

        setLayout(null);
        setBackground(Const.Colors.EXAM_BACKGROUND_COLOR);
        setAlignmentX(Component.LEFT_ALIGNMENT);
        setBorder(emptyBorder);
    }


    private void initializeComponents() {
        JPanel imagePanel = getImagePanel();
        JPanel questionPanel = getQuestionPanel();
        abcBtnPanel = getABCBtnPanel();
        yesNoBtnPanel = getYesNoBtnPanel();

        add(imagePanel);
        add(questionPanel);
        add(yesNoBtnPanel);
    }

    public JPanel getImagePanel() {
        JPanel imagePanel = new JPanel();
        imagePanel.setBorder(BorderFactory.createLineBorder(Color.black));
        imagePanel.setBounds(10, 10, 640, 357);
        imagePanel.setBackground(Const.Colors.EXAM_BACKGROUND_COLOR);

        imagePanel.setLayout(new BorderLayout());
        imagePanel.add(component);

        return imagePanel;
    }

    public JPanel getQuestionPanel() {
        JPanel questionPanel = new JPanel();
        questionPanel.setBackground(Const.Colors.EXAM_BACKGROUND_COLOR);
        questionPanel.setBounds(0, 380, 680, 80);

        questionTextArea = getQuestionTextArea();

        questionPanel.add(questionTextArea);
        return questionPanel;
    }

    private JTextArea getQuestionTextArea() {
        JTextArea questionTextArea = new JTextArea(3, 49);
        questionTextArea.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        questionTextArea.setFont(Const.Fonts.TEXTS_FONT);
        questionTextArea.setBackground(Const.Colors.EXAM_BACKGROUND_COLOR);
        questionTextArea.setLineWrap(true);
        questionTextArea.setWrapStyleWord(true);
        questionTextArea.setEditable(false);
        questionTextArea.setFocusable(false);
        return questionTextArea;
    }

    public JPanel getYesNoBtnPanel() {
        JPanel buttonPanel = new JPanel();

        buttonPanel.setBounds(0, 570, 680, 60);
        buttonPanel.setLayout(new GridLayout(1, 2, 10, 10));
        buttonPanel.setBackground(Const.Colors.EXAM_BACKGROUND_COLOR);
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));

        yesBtn = createYesNoBtn(TextsDao.getText("yesButtonLbl"));
        noBtn = createYesNoBtn(TextsDao.getText("noButtonLbl"));

        defaultColor = noBtn.getBackground();

        buttonPanel.add(yesBtn);
        buttonPanel.add(noBtn);

        return buttonPanel;
    }

    private JPanel getABCBtnPanel() {
        JPanel buttonPanel = new JPanel();

        buttonPanel.setBounds(0, 475, 680, 160);
        buttonPanel.setLayout(new GridLayout(3, 1, 10, 10));
        buttonPanel.setBackground(Const.Colors.EXAM_BACKGROUND_COLOR);
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));

        btnA = createABCBtn();
        btnB = createABCBtn();
        btnC = createABCBtn();

        buttonPanel.add(btnA);
        buttonPanel.add(btnB);
        buttonPanel.add(btnC);

        return buttonPanel;
    }

    private JButton createYesNoBtn(String label) {
        JButton button = new JButton(label);
        button.setAlignmentX(CENTER_ALIGNMENT);
        button.setFont(Const.Fonts.BTNS_YES_NO_FONT);
        button.setPreferredSize(Const.Dimensions.EXAM_YES_NO_BTN_SIZE);
        button.setMinimumSize(Const.Dimensions.EXAM_YES_NO_BTN_SIZE);
        button.setMaximumSize(Const.Dimensions.EXAM_YES_NO_BTN_SIZE);
        button.setHorizontalAlignment(SwingConstants.CENTER);
        button.setFocusable(false);
        return button;
    }

    private JButton createABCBtn() {
        JButton button = new JButton();
        button.setFont(Const.Fonts.BTNS_ABC_FONT);
        button.setPreferredSize(Const.Dimensions.ABC_BTNS_SIZE);
        button.setMinimumSize(Const.Dimensions.ABC_BTNS_SIZE);
        button.setMaximumSize(Const.Dimensions.ABC_BTNS_SIZE);
        button.setHorizontalAlignment(SwingConstants.LEFT);
        button.setFocusable(false);
        return button;
    }

    public void setQestion(String qestion) {
        questionTextArea.setText(qestion);
    }

    public void setBtnABCTexts(String btnAText, String btnBText, String btnCText) {
        this.btnA.setText(btnAText);
        this.btnB.setText(btnBText);
        this.btnC.setText(btnCText);
    }

    public void changePanelFromStandarToSpecial() {
        remove(yesNoBtnPanel);
        add(abcBtnPanel);
        repaint();
    }

    public void showWaitImageImage() {
        Encrypter.decodeMedia("wait_photo");

        player.stop();
        player.prepareMedia(FilesUtils.getTempDirPath() + File.separator + "wait_photo.prode");
        player.parseMedia();
        player.play();
    }

    public void showWaitVideoImage() {
        Encrypter.decodeMedia("wait_video");

        player.stop();
        player.prepareMedia(FilesUtils.getTempDirPath() + File.separator + "wait_video.prode");
        player.parseMedia();
        player.play();
    }

    public void setImageName(String imageName) {
        Encrypter.decodeMedia(imageName);

        player.stop();
        player.prepareMedia(FilesUtils.getTempDirPath() + File.separator + imageName + ".prode");
        player.parseMedia();
        player.play();
    }

    public void setVideoName(String videoTitle) {
        Encrypter.decodeMedia(videoTitle);

        player.stop();
        player.prepareMedia(FilesUtils.getTempDirPath() + File.separator + videoTitle + ".prode");
        player.parseMedia();
        int sec = (int) (player.getMediaMeta().getLength() / 1000);
        System.out.println(sec);
        player.play();
    }

    public void enableAllBtns() {
        yesBtn.setBackground(defaultColor);
        noBtn.setBackground(defaultColor);
        btnA.setBackground(defaultColor);
        btnB.setBackground(defaultColor);
        btnC.setBackground(defaultColor);
    }

    public JButton getYesBtn() {
        return yesBtn;
    }

    public JButton getNoBtn() {
        return noBtn;
    }

    public JButton getBtnA() {
        return btnA;
    }

    public JButton getBtnB() {
        return btnB;
    }

    public JButton getBtnC() {
        return btnC;
    }
}
