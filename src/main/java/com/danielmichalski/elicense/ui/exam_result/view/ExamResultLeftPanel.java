package com.danielmichalski.elicense.ui.exam_result.view;

import com.danielmichalski.elicense.components.QuestionTextArea;
import com.danielmichalski.elicense.database.dao.TextsDao;
import com.danielmichalski.elicense.model.ABCAnswer;
import com.danielmichalski.elicense.model.YesNoAnswer;
import org.apache.log4j.Logger;
import uk.co.caprica.vlcj.player.MediaPlayerFactory;
import uk.co.caprica.vlcj.player.embedded.EmbeddedMediaPlayer;
import com.danielmichalski.elicense.util.ApplicationUtils;
import com.danielmichalski.elicense.util.Const;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

/**
 * Author: Daniel
 */
public class ExamResultLeftPanel extends JPanel {
    private Logger logger = ApplicationUtils.getLogger(ExamResultLeftPanel.class);

    private Canvas component;
    private EmbeddedMediaPlayer player;

    private JPanel abcBtnPanel;
    private JPanel yesNoBtnPanel;

    private JTextArea questionTextArea;

    private JTextArea yesBtn;
    private JTextArea noBtn;
    private JTextArea btnA;
    private JTextArea btnB;
    private JTextArea btnC;

    public ExamResultLeftPanel() {
        setUpPanel();
        initializeComponents();
    }

    private void setUpPanel() {
        try {
            MediaPlayerFactory mediaPlayerFactory = new MediaPlayerFactory();
            component = new Canvas();
            component.setIgnoreRepaint(false);
            player = mediaPlayerFactory.newEmbeddedMediaPlayer();
            player.setVideoSurface(mediaPlayerFactory.newVideoSurface(component));

            Border emptyBorder = BorderFactory.createEmptyBorder(10, 10, 10, 10);
            setLayout(null);
            setBackground(Const.Colors.EXAM_BACKGROUND_COLOR);
            setAlignmentX(Component.LEFT_ALIGNMENT);
            setBorder(emptyBorder);
        } catch (Exception e) {
            logger.error(e);
        }
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
        questionTextArea.setFocusable(false);
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

        yesBtn = createYesNoBtn(TextsDao.getText("yesButtonLbl"));
        noBtn = createYesNoBtn(TextsDao.getText("noButtonLbl"));

        buttonPanel.add(yesBtn);
        buttonPanel.add(noBtn);

        return buttonPanel;
    }

    private JPanel getABCBtnPanel() {
        JPanel buttonPanel = new JPanel();

        buttonPanel.setBounds(0, 470, 680, 160);
        buttonPanel.setLayout(new GridLayout(3, 1, 10, 10));
        buttonPanel.setBackground(Const.Colors.EXAM_BACKGROUND_COLOR);

        buttonPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));

        btnA = new QuestionTextArea(91);
        btnB = new QuestionTextArea(91);
        btnC = new QuestionTextArea(91);

        buttonPanel.add(btnA);
        buttonPanel.add(btnB);
        buttonPanel.add(btnC);

        return buttonPanel;
    }

    private JTextArea createYesNoBtn(String label) {
        JTextArea textArea = new JTextArea(label);
        textArea.setFont(Const.Fonts.BTNS_YES_NO_FONT);
        textArea.setPreferredSize(Const.Dimensions.EXAM_YES_NO_BTN_SIZE);
        textArea.setMinimumSize(Const.Dimensions.EXAM_YES_NO_BTN_SIZE);
        textArea.setMaximumSize(Const.Dimensions.EXAM_YES_NO_BTN_SIZE);

        textArea.setFocusable(false);
        textArea.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        Border compoundBorder = BorderFactory.createLineBorder(Const.Colors.BTN_BORDER_COLOR, 1, true);
        textArea.setBorder(BorderFactory.createCompoundBorder(compoundBorder,
                BorderFactory.createEmptyBorder(20, 150, 10, 10)));

        return textArea;
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

        btnA.setBackground(Const.Colors.BTN_UNSELECTED_COLOR);
        btnB.setBackground(Const.Colors.BTN_UNSELECTED_COLOR);
        btnC.setBackground(Const.Colors.BTN_UNSELECTED_COLOR);

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

        yesBtn.setBackground(Const.Colors.BTN_UNSELECTED_COLOR);
        noBtn.setBackground(Const.Colors.BTN_UNSELECTED_COLOR);

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

    public void setImageName(String mediaTitle) {
        ApplicationUtils.prepareAndPlayMedia(player, mediaTitle);
    }

    public void setVideoName(final String mediaTitle) {
        ApplicationUtils.prepareAndPlayMedia(player, mediaTitle);
    }
}
