package view.exam_view;

import util.Const;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.net.URL;

/**
 * Author: Daniel
 * Date: 03.11.13
 */
public class ExamQuestionsLeftPanel extends JPanel {
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

    private WindowAutoSizer validateWindowSize;

    public ExamQuestionsLeftPanel(WindowAutoSizer validateWindowSize) {
        this.validateWindowSize = validateWindowSize;
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

        imagePanel.setBackground(Const.Colors.examBackgroundColor);

        URL imageSrc = getClass().getResource("/images/Slajd117_ITS.jpg");
        ImageIcon image = new ImageIcon(imageSrc);
        imageLabel = new JLabel("", image, JLabel.LEADING);
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
        questionTextArea.setFont(Const.Fonts.textsFont);
        questionTextArea.setBackground(Const.Colors.examBackgroundColor);
        questionTextArea.setLineWrap(true);
        questionTextArea.setEditable(false);
        return questionTextArea;
    }

    public JPanel getYesNoBtnPanel() {
        JPanel buttonPanel = new JPanel();

        buttonPanel.setBackground(Const.Colors.examBackgroundColor);

        yesBtn = createYesNoBtn("Tak");
        noBtn = createYesNoBtn("Nie");

        buttonPanel.add(yesBtn);
        buttonPanel.add(noBtn);

        return buttonPanel;
    }

    private JPanel getABCBtnPanel() {
        JPanel buttonPanel = new JPanel();

        buttonPanel.setLayout(new GridLayout(3, 1, 0, 10));
        buttonPanel.setBackground(Const.Colors.examBackgroundColor);

        btnA = createABCBtn("A) hamowanie");
        btnB = createABCBtn("B) wymijanie");
        btnC = createABCBtn("C) zmianę kierunku jazdy");

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

    private JButton createABCBtn(String label) {
        JButton button = new JButton(label);
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

    public JButton getYesBtn() {
        return yesBtn;
    }

    public JButton getNoBtn() {
        return noBtn;
    }

    public void changePanelFromStandarToSpecial() {
        remove(yesNoBtnPanel);
        add(abcBtnPanel);
        validateWindowSize.autoSize();
    }
}
