package ui.exam_result.view;

import com.sun.jna.NativeLibrary;
import database.dao.TextsDao;
import model.ABCAnswer;
import model.YesNoAnswer;
import uk.co.caprica.vlcj.component.EmbeddedMediaPlayerComponent;
import uk.co.caprica.vlcj.player.embedded.EmbeddedMediaPlayer;
import uk.co.caprica.vlcj.runtime.RuntimeUtil;
import util.Const;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.io.File;

/**
 * Author: Daniel
 * Date: 08.11.13
 */
public class ExamResultLeftPanel extends JPanel {
    private EmbeddedMediaPlayerComponent component;
    private EmbeddedMediaPlayer player;

    private JPanel abcBtnPanel;
    private JPanel yesNoBtnPanel;

    private JTextArea questionTextArea;

    private JButton yesBtn;
    private JButton noBtn;
    private JButton btnA;
    private JButton btnB;
    private JButton btnC;

    public ExamResultLeftPanel() {
        setUpPanel();
        initializeComponents();
    }

    private void setUpPanel() {
        NativeLibrary.addSearchPath(RuntimeUtil.getLibVlcLibraryName(), "VLCx86");
        component = new EmbeddedMediaPlayerComponent();
        player = component.getMediaPlayer();


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
        imagePanel.setBounds(10, 10, 640, 360);
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
        questionTextArea.setVisible(true);
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
        return questionTextArea;
    }

    public JPanel getYesNoBtnPanel() {
        JPanel buttonPanel = new JPanel();

        buttonPanel.setBounds(0, 570, 680, 60);
        buttonPanel.setLayout(new GridLayout(1, 2, 10, 10));
        buttonPanel.setBackground(Const.Colors.EXAM_BACKGROUND_COLOR);
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));

        noBtn = createYesNoBtn(TextsDao.getText("noButtonLbl"));
        yesBtn = createYesNoBtn(TextsDao.getText("yesButtonLbl"));

        buttonPanel.add(noBtn);
        buttonPanel.add(yesBtn);

        return buttonPanel;
    }

    private JPanel getABCBtnPanel() {
        JPanel buttonPanel = new JPanel();

        buttonPanel.setBounds(0, 470, 680, 160);
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
        player.stop();
        player.prepareMedia("media" + File.separator + imageName + ".prod");
        player.parseMedia();
        player.play();
    }

    public synchronized void setVideoName(final String videoName) {
        player.stop();
        player.prepareMedia("media" + File.separator + videoName + ".prod");
        player.parseMedia();
        player.play();
    }

    public void enableAllBtns() {
        yesBtn.setEnabled(true);
        noBtn.setEnabled(true);
        btnA.setEnabled(true);
        btnB.setEnabled(true);
        btnC.setEnabled(true);
    }

    public void disableAllBtns() {
        yesBtn.setEnabled(false);
        noBtn.setEnabled(false);
        btnA.setEnabled(false);
        btnB.setEnabled(false);
        btnC.setEnabled(false);
    }
}
