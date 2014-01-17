package ui.learning.view;

import database.dao.TextsDao;
import encrypt.Encrypter;
import model.ABCAnswer;
import model.YesNoAnswer;
import org.apache.log4j.Logger;
import uk.co.caprica.vlcj.player.MediaPlayerFactory;
import uk.co.caprica.vlcj.player.embedded.EmbeddedMediaPlayer;
import util.ApplicationUtils;
import util.Const;
import util.FilesUtils;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.io.File;

/**
 * Author: Daniel
 * Date: 08.11.13
 */
public class LearningLeftPanel extends JPanel {
    private Logger logger = ApplicationUtils.getLogger(LearningLeftPanel.class);

    private Canvas component;
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

    public LearningLeftPanel() {
        setUpPanel();
        initializeComponents();
    }

    private void setUpPanel() {
        MediaPlayerFactory mediaPlayerFactory = new MediaPlayerFactory();
        component = new Canvas();
        player = mediaPlayerFactory.newEmbeddedMediaPlayer();
        player.setVideoSurface(mediaPlayerFactory.newVideoSurface(component));

        Border emptyBorder = BorderFactory.createEmptyBorder(10, 10, 10, 10);
        setLayout(null);
        setBackground(Const.Colors.EXAM_BACKGROUND_COLOR);
        setAlignmentX(Component.LEFT_ALIGNMENT);
        setBorder(emptyBorder);
    }

    private void initializeComponents() {
        JPanel imageAndVideoPanel = getImageAndVideoPanel();
        JPanel questionPanel = getQuestionPanel();
        abcBtnPanel = getABCBtnPanel();
        yesNoBtnPanel = getYesNoBtnPanel();

        add(imageAndVideoPanel);
        add(questionPanel);
        add(yesNoBtnPanel);
    }

    public JPanel getImageAndVideoPanel() {
        JPanel imagePanel = new JPanel();
        imagePanel.setBorder(BorderFactory.createLineBorder(Color.black));
        imagePanel.setBounds(45, 10, 640, 360);
        imagePanel.setBackground(Const.Colors.EXAM_BACKGROUND_COLOR);

        imagePanel.setLayout(new BorderLayout());
        imagePanel.add(component);

        return imagePanel;
    }

    public JPanel getQuestionPanel() {
        JPanel questionPanel = new JPanel();
        questionPanel.setLayout(null);
        questionPanel.setBackground(Const.Colors.EXAM_BACKGROUND_COLOR);
        questionPanel.setBounds(10, 380, 720, 80);
        questionTextArea = getQuestionTextArea();
        questionTextArea.setVisible(true);
        questionPanel.add(questionTextArea);
        return questionPanel;
    }

    private JTextArea getQuestionTextArea() {
        JTextArea questionTextArea = new JTextArea(3, 52);
        questionTextArea.setBounds(10, 0, 700, 80);
        questionTextArea.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        questionTextArea.setFont(Const.Fonts.TEXTS_FONT);
        questionTextArea.setBackground(Const.Colors.EXAM_BACKGROUND_COLOR);
        questionTextArea.setLineWrap(true);
        questionTextArea.setWrapStyleWord(true);
        questionTextArea.setEditable(false);
        return questionTextArea;
    }

    public JPanel getYesNoBtnPanel() {
        JPanel buttonPanel = new JPanel();

        buttonPanel.setBounds(10, 560, 720, 60);
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

        buttonPanel.setBounds(0, 460, 720, 160);
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
        button.setFont(Const.Fonts.BTNS_YES_NO_FONT);
        button.setPreferredSize(Const.Dimensions.EXAM_YES_NO_BTN_SIZE);
        button.setMinimumSize(Const.Dimensions.EXAM_YES_NO_BTN_SIZE);
        button.setMaximumSize(Const.Dimensions.EXAM_YES_NO_BTN_SIZE);
        button.setFocusable(false);
        return button;
    }

    private JButton createABCBtn() {
        JButton button = new JButton();
        button.setFocusable(false);
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

        this.btnA.setToolTipText(btnAText);
        this.btnB.setToolTipText(btnBText);
        this.btnC.setToolTipText(btnCText);
    }

    public YesNoAnswer getUserAnswerYesNo() {
        if (noBtn.getBackground().equals(Const.Colors.CLICKED_BTN_COLOR)) {
            return YesNoAnswer.NIE;
        } else if (yesBtn.getBackground().equals(Const.Colors.CLICKED_BTN_COLOR)) {
            return YesNoAnswer.TAK;
        } else {
            return null;
        }
    }

    public void setUserAndCorrectAnswer(ABCAnswer userAnswer, ABCAnswer correctAnswer) {
        Color positiveColor = Const.Colors.POSITIVE_RESULT_COLOR;
        Color negativeColor = Const.Colors.NEGATIVE_RESULT_COLOR;

        btnA.setBackground(null);
        btnB.setBackground(null);
        btnC.setBackground(null);

        if (userAnswer == null) {
            colorBtn(correctAnswer, positiveColor);
        } else if (userAnswer == correctAnswer) {
            colorBtn(correctAnswer, positiveColor);
        } else {
            colorBtn(userAnswer, negativeColor);
            colorBtn(correctAnswer, positiveColor);
        }
    }

    private void colorBtn(ABCAnswer abcBtn, Color color) {
        switch (abcBtn) {
            case A:
                btnA.setBackground(color);
                break;
            case B:
                btnB.setBackground(color);
                break;
            case C:
                btnC.setBackground(color);
        }
    }

    public ABCAnswer getUserAnswerAbc() {
        if (btnA.getBackground().equals(Const.Colors.CLICKED_BTN_COLOR)) {
            return ABCAnswer.A;
        } else if (btnB.getBackground().equals(Const.Colors.CLICKED_BTN_COLOR)) {
            return ABCAnswer.B;
        } else if (btnC.getBackground().equals(Const.Colors.CLICKED_BTN_COLOR)) {
            return ABCAnswer.C;
        } else {
            return null;
        }
    }

    public void setUserAndCorrectAnswer(YesNoAnswer userAnswer, YesNoAnswer correctAnswer) {
        Color positiveColor = Const.Colors.POSITIVE_RESULT_COLOR;
        Color negativeColor = Const.Colors.NEGATIVE_RESULT_COLOR;

        yesBtn.setBackground(null);
        noBtn.setBackground(null);

        if (userAnswer == null) {
            colorBtn(correctAnswer, positiveColor);
        } else if (userAnswer == correctAnswer) {
            colorBtn(correctAnswer, positiveColor);
        } else {
            colorBtn(userAnswer, negativeColor);
            colorBtn(correctAnswer, positiveColor);
        }
    }

    private void colorBtn(YesNoAnswer yesNoAnswer, Color color) {
        switch (yesNoAnswer) {
            case TAK:
                yesBtn.setBackground(color);
                break;
            case NIE:
                noBtn.setBackground(color);
                break;
        }
    }

    public void changePanelToStandardPanel() {
        remove(abcBtnPanel);
        add(yesNoBtnPanel);
        repaint();
    }

    public void changePanelToSpecialistPanel() {
        remove(yesNoBtnPanel);
        add(abcBtnPanel);
        repaint();
    }

    public void setImageName(String imageName) {
        logger.info("Loading image: " + imageName + ".prode");
        Encrypter.decodeMedia(imageName);

        try {
            player.stop();
            player.prepareMedia(FilesUtils.getTempDirPath() + File.separator + imageName + ".prode");
            player.parseMedia();
            player.play();
        } catch (Exception e) {
            logger.error(e);
        }
    }

    public void setVideoName(String videoName) {
        logger.info("Loading video: " + videoName + ".prode");
        Encrypter.decodeMedia(videoName);

        try {
            player.stop();
            player.prepareMedia(FilesUtils.getTempDirPath() + File.separator + videoName + ".prode");
            player.parseMedia();
            player.play();
        } catch (Exception e) {
            logger.error(e);
        }
    }

    public void unmarkAllBtns() {
        yesBtn.setBackground(defaultColor);
        noBtn.setBackground(defaultColor);
        btnA.setBackground(defaultColor);
        btnB.setBackground(defaultColor);
        btnC.setBackground(defaultColor);
    }

    public void disposePlayer() {
        logger.info("Disposing media player");
        player.release();
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
