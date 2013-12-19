package ui.learning.view;

import database.dao.TextsDao;
import util.Const;

import javax.swing.*;
import java.awt.*;

/**
 * Author: Daniel
 * Date: 08.11.13
 */
public class LearningRightPanel extends JPanel {
    private JButton closeBtn;
    private JTextArea categoryName;
    private JButton playMovieBtn;
    private JButton previousBtn;
    private JButton nextBtn;
    private JButton randomQuestion;
    private JButton checkAnswerBtn;
    private JLabel howManyPointsForQuestionLbl;

    public LearningRightPanel() {
        setUpPanel();
        initializeComponents();
    }

    private void setUpPanel() {
        setLayout(null);
        setBorder(BorderFactory.createEmptyBorder(5, 10, 10, 10));
        setBackground(Const.Colors.EXAM_BACKGROUND_COLOR);
    }

    private void initializeComponents() {
        closeBtn = createCloseBtn();
        Component categoryLabel = createCategoryLabel();
        categoryName = createCategoryName();
        howManyPointsForQuestionLbl = createHowManyPointsLbl();
        playMovieBtn = createPlayMovieBtn();
        previousBtn = createPreviousBtn();
        nextBtn = createNextBtn();
        randomQuestion = createRandomQuestionBtn();
        checkAnswerBtn = createCheckAnswerBtn();

        add(closeBtn);
        add(categoryLabel);
        add(categoryName);
        add(playMovieBtn);
        add(previousBtn);
        add(nextBtn);
        add(randomQuestion);
        add(checkAnswerBtn);
        add(howManyPointsForQuestionLbl);
    }

    private Component createCategoryLabel() {
        JLabel label = new JLabel("Kategoria");
        label.setBounds(82, 60, 200, 30);
        label.setFont(Const.Fonts.BIGGER_FONT);
        return label;
    }

    private JTextArea createCategoryName() {
        JTextArea moduleNameTextArea = new JTextArea();
        moduleNameTextArea.setBounds(0, 90, 240, 100);
        moduleNameTextArea.setMargin(new Insets(2, 2, 2, 2));
        moduleNameTextArea.setLineWrap(true);
        moduleNameTextArea.setWrapStyleWord(true);
        moduleNameTextArea.setFont(Const.Fonts.NORMAL_FONT);
        moduleNameTextArea.setBackground(Const.Colors.EXAM_BACKGROUND_COLOR);
        return moduleNameTextArea;
    }

    private JButton createCloseBtn() {
        JButton button = new JButton(TextsDao.getText("ExamPointRigthPanel.btnCloseLbl"));
        button.setBounds(0, 10, 240, 35);
        button.setFont(Const.Fonts.NORMAL_FONT);
        button.setFocusable(false);
        return button;
    }

    private JButton createPlayMovieBtn() {
        JButton button = new JButton("Odtwórz film");
        button.setBounds(0, 325, 240, 50);
        button.setFont(Const.Fonts.NORMAL_FONT);
        button.setFocusable(false);
        return button;
    }

    private JButton createPreviousBtn() {
        JButton button = new JButton("<< Poprzednie");
        button.setBounds(0, 395, 120, 45);
        button.setFont(Const.Fonts.NORMAL_FONT);
        button.setFocusable(false);
        return button;
    }

    private JButton createNextBtn() {
        JButton button = new JButton("Następne >>");
        button.setBounds(120, 395, 120, 45);
        button.setFont(Const.Fonts.NORMAL_FONT);
        button.setFocusable(false);
        return button;
    }

    private JButton createRandomQuestionBtn() {
        JButton button = new JButton("Pytanie losowe");
        button.setBounds(0, 460, 240, 50);
        button.setFont(Const.Fonts.NORMAL_FONT);
        button.setFocusable(false);
        return button;
    }

    private JButton createCheckAnswerBtn() {
        JButton button = new JButton("Sprawdź odpowiedź");
        button.setBounds(0, 530, 240, 50);
        button.setFont(Const.Fonts.NORMAL_FONT);
        button.setFocusable(false);
        return button;
    }

    private JLabel createHowManyPointsLbl() {
        JLabel howManyPointsLbl = new JLabel("3 pkt");
        howManyPointsLbl.setBounds(110, 590, 240, 30);
        howManyPointsLbl.setFont(Const.Fonts.TEXTS_FONT);
        return howManyPointsLbl;
    }

    public void setModuleName(String moduleName) {
        categoryName.setText(moduleName);
    }

    public JButton getCloseBtn() {
        return closeBtn;
    }

    public JTextArea getCategoryName() {
        return categoryName;
    }

    public JButton getPlayMovieBtn() {
        return playMovieBtn;
    }

    public JButton getPreviousBtn() {
        return previousBtn;
    }

    public JButton getNextBtn() {
        return nextBtn;
    }

    public JButton getRandomQuestion() {
        return randomQuestion;
    }

    public JButton getCheckAnswerBtn() {
        return checkAnswerBtn;
    }

    public JLabel getHowManyPointsForQuestionLbl() {
        return howManyPointsForQuestionLbl;
    }
}
