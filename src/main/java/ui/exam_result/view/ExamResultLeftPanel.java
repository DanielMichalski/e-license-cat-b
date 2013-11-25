package ui.exam_result.view;

import database.dao.TextsDao;
import media.images.ImageUtils;
import media.videos.VideoCodec;
import media.videos.VideoPanel;
import model.ABCAnswer;
import model.YesNoAnswer;
import util.Const;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.io.IOException;

/**
 * Author: Daniel
 * Date: 08.11.13
 */
public class ExamResultLeftPanel extends JPanel {

    private JPanel imagePanel;
    private JPanel abcBtnPanel;
    private JPanel yesNoBtnPanel;

    private JLabel imageLabel;
    private JTextArea questionTextArea;

    private JButton yesBtn;
    private JButton noBtn;
    private JButton btnA;
    private JButton btnB;
    private JButton btnC;

    private Border emptyBorder;

    public ExamResultLeftPanel() {

        setUpPanel();
        initializeComponents();
    }

    private void setUpPanel() {
        emptyBorder = BorderFactory.createEmptyBorder(10, 5, 10, 10);

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(Const.Colors.EXAM_BACKGROUND_COLOR);
        setAlignmentX(Component.LEFT_ALIGNMENT);
        setBorder(emptyBorder);
    }

    private void initializeComponents() {
        imagePanel = getImagePanel();
        JPanel questionPanel = getQuestionPanel();
        abcBtnPanel = getABCBtnPanel();
        yesNoBtnPanel = getYesNoBtnPanel();

        add(imagePanel);
        add(questionPanel);
        add(yesNoBtnPanel);
    }

    public JPanel getImagePanel() {
        JPanel imagePanel = new JPanel();
        imagePanel.setBackground(Const.Colors.EXAM_BACKGROUND_COLOR);

        ImageIcon image = ImageUtils.getProgramImage("no_photo.jpg");
        imageLabel = new JLabel("", image, SwingConstants.CENTER);
        imageLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        imagePanel.add(imageLabel);

        return imagePanel;
    }

    public JPanel getQuestionPanel() {
        JPanel questionPanel = new JPanel();
        questionPanel.setBackground(Const.Colors.EXAM_BACKGROUND_COLOR);

        questionTextArea = getQuestionTextArea();

        questionPanel.add(questionTextArea);
        return questionPanel;
    }

    private JTextArea getQuestionTextArea() {
        JTextArea questionTextArea = new JTextArea(3, 49);
        questionTextArea.setBorder(emptyBorder);
        questionTextArea.setFont(Const.Fonts.TEXTS_FONT);
        questionTextArea.setBackground(Const.Colors.EXAM_BACKGROUND_COLOR);
        questionTextArea.setLineWrap(true);
        questionTextArea.setWrapStyleWord(true);
        questionTextArea.setEditable(false);
        return questionTextArea;
    }

    public JPanel getYesNoBtnPanel() {
        JPanel buttonPanel = new JPanel();

        buttonPanel.setLayout(new GridLayout(2, 1, 10, 10));
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
            colorBtn(correctAnswer, negativeColor);
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
            colorBtn(correctAnswer, negativeColor);
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
        try {
            ImageIcon image = ImageUtils.getQuestionImage(imageName + TextsDao.getText("imageFilesExtension"));
            imagePanel.removeAll();
            imageLabel = new JLabel("", image, JLabel.LEADING);
            imageLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            imagePanel.add(imageLabel);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, e, "Błąd", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void setVideoName(final String videoName) {
        imagePanel.removeAll();
        final VideoPanel videoPanel = new VideoPanel();
        videoPanel.setPreferredSize(Const.Dimensions.VIDEO_SIZE);
        videoPanel.setMinimumSize(Const.Dimensions.VIDEO_SIZE);
        videoPanel.setMaximumSize(Const.Dimensions.VIDEO_SIZE);
        imagePanel.add(videoPanel);
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                VideoCodec videoCodec =
                        new VideoCodec(videoPanel, videoName + TextsDao.getText("videoFilesExtension"));
            }
        };

        Thread thread = new Thread(runnable);
        thread.start();
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
