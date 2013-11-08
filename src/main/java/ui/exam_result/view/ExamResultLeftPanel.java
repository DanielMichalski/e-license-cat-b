package ui.exam_result.view;

import database.dao.TextsDao;
import util.Const;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.net.URL;

/**
 * Author: Daniel
 * Date: 08.11.13
 */
public class ExamResultLeftPanel extends JPanel {
    private JPanel imagePanel;
    private JPanel questionPanel;
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
        emptyBorder = BorderFactory.createEmptyBorder(10, 10, 10, 10);

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(Const.Colors.examBackgroundColor);
        setAlignmentX(Component.LEFT_ALIGNMENT);
        setBorder(emptyBorder);
    }

    private void initializeComponents() {
        imagePanel = getImagePanel();
        questionPanel = getQuestionPanel();
        abcBtnPanel = getABCBtnPanel();
        yesNoBtnPanel = getYesNoBtnPanel();

        add(imagePanel);
        add(questionPanel);
        add(yesNoBtnPanel);
    }

    public JPanel getImagePanel() {
        JPanel imagePanel = new JPanel();

        imagePanel.setBackground(Const.Colors.examBackgroundColor);

        URL imageSrc = getClass().getResource("/program_images/no_photo.jpg");
        ImageIcon image = new ImageIcon(imageSrc);
        imageLabel = new JLabel("", image, SwingConstants.CENTER);
        imageLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        imagePanel.add(imageLabel);
        return imagePanel;
    }

    public JPanel getQuestionPanel() {
        JPanel questionPanel = new JPanel();
        questionPanel.setBackground(Const.Colors.examBackgroundColor);

        questionTextArea = getQuestionTextArea();

        questionPanel.add(questionTextArea);
        return questionPanel;
    }

    private JTextArea getQuestionTextArea() {
        JTextArea questionTextArea = new JTextArea(3, 49);
        questionTextArea.setBorder(emptyBorder);
        questionTextArea.setFont(Const.Fonts.textsFont);
        questionTextArea.setBackground(Const.Colors.examBackgroundColor);
        questionTextArea.setLineWrap(true);
        questionTextArea.setWrapStyleWord(true);
        questionTextArea.setEditable(false);
        return questionTextArea;
    }

    public JPanel getYesNoBtnPanel() {
        JPanel buttonPanel = new JPanel();

        buttonPanel.setBackground(Const.Colors.examBackgroundColor);

        yesBtn = createYesNoBtn(TextsDao.getText("yesButtonLbl"));
        noBtn = createYesNoBtn(TextsDao.getText("noButtonLbl"));

        buttonPanel.add(yesBtn);
        buttonPanel.add(noBtn);

        return buttonPanel;
    }

    private JPanel getABCBtnPanel() {
        JPanel buttonPanel = new JPanel();

        buttonPanel.setLayout(new GridLayout(3, 1, 10, 10));
        buttonPanel.setBackground(Const.Colors.examBackgroundColor);

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
        button.setFont(Const.Fonts.btnsYesNoFont);
        button.setPreferredSize(Const.Dimensions.examYesNoBtnSize);
        button.setMinimumSize(Const.Dimensions.examYesNoBtnSize);
        button.setMaximumSize(Const.Dimensions.examYesNoBtnSize);
        return button;
    }

    private JButton createABCBtn() {
        JButton button = new JButton();
        button.setFont(Const.Fonts.btnsABCFont);
        button.setPreferredSize(Const.Dimensions.ABCBtnsSize);
        button.setMinimumSize(Const.Dimensions.ABCBtnsSize);
        button.setMaximumSize(Const.Dimensions.ABCBtnsSize);
        button.setHorizontalAlignment(SwingConstants.LEFT);
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
        //TODO zmienic panel na "special"
    }

    public void setImagePath(String imagePath) {
        URL imageSrc = getClass().getResource(imagePath);
        ImageIcon image = new ImageIcon(imageSrc);
        imagePanel.remove(imageLabel);
        imageLabel = new JLabel("", image, JLabel.LEADING);
        imageLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        imagePanel.add(imageLabel);
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
