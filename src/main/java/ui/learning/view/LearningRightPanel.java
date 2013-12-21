package ui.learning.view;

import util.Const;

import javax.swing.*;
import java.awt.*;

/**
 * Author: Daniel
 * Date: 08.11.13
 */
public class LearningRightPanel extends JPanel {
    private JButton categoryChooseBtn;
    private JTextArea categoryNameTF;
    private JTextArea questionArea;
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
        categoryChooseBtn = createCategoryChooseBtn();
        Component categoryLabel = createCategoryLabel();
        categoryNameTF = createCategoryName();
        Component questionLb = createQuestionLabel();
        questionArea = createQuestionArea();
        howManyPointsForQuestionLbl = createHowManyPointsLbl();
        playMovieBtn = createPlayMovieBtn();
        previousBtn = createPreviousBtn();
        nextBtn = createNextBtn();
        randomQuestion = createRandomQuestionBtn();
        checkAnswerBtn = createCheckAnswerBtn();

        add(categoryChooseBtn);
        add(categoryLabel);
        add(categoryNameTF);
        add(questionLb);
        add(questionArea);
        add(playMovieBtn);
        add(previousBtn);
        add(nextBtn);
        add(randomQuestion);
        add(checkAnswerBtn);
        add(howManyPointsForQuestionLbl);
    }

    private Component createCategoryLabel() {
        JLabel label = new JLabel("Nazwa kategorii");
        label.setBounds(55, 55, 200, 30);
        label.setFont(Const.Fonts.BIGGER_FONT);
        return label;
    }

    private JTextArea createCategoryName() {
        JTextArea moduleNameTextArea = new JTextArea();
        moduleNameTextArea.setBounds(0, 85, 240, 100);
        moduleNameTextArea.setMargin(new Insets(2, 2, 2, 2));
        moduleNameTextArea.setLineWrap(true);
        moduleNameTextArea.setWrapStyleWord(true);
        moduleNameTextArea.setFont(Const.Fonts.NORMAL_FONT);
        moduleNameTextArea.setBackground(Const.Colors.EXAM_BACKGROUND_COLOR);
        moduleNameTextArea.setEditable(false);
        moduleNameTextArea.setFocusable(false);
        return moduleNameTextArea;
    }

    private Component createQuestionLabel() {
        JLabel label = new JLabel("Numer pytania");
        label.setBounds(60, 190, 200, 30);
        label.setFont(Const.Fonts.BIGGER_FONT);
        return label;
    }

    private JTextArea createQuestionArea() {
        JTextArea moduleNameTextArea = new JTextArea();
        moduleNameTextArea.setBounds(0, 220, 240, 50);
        moduleNameTextArea.setMargin(new Insets(2, 2, 2, 2));
        moduleNameTextArea.setLineWrap(true);
        moduleNameTextArea.setWrapStyleWord(true);
        moduleNameTextArea.setFont(Const.Fonts.NORMAL_FONT);
        moduleNameTextArea.setBackground(Const.Colors.EXAM_BACKGROUND_COLOR);
        moduleNameTextArea.setEditable(false);
        moduleNameTextArea.setFocusable(false);
        return moduleNameTextArea;
    }


    private JButton createCategoryChooseBtn() {
        JButton button = new JButton("Wybór kategorii");
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
        categoryNameTF.setText(moduleName);
    }

    public JButton getCategoryChooseBtn() {
        return categoryChooseBtn;
    }

    public JTextArea getCategoryNameTF() {
        return categoryNameTF;
    }

    public JTextArea getQuestionArea() {
        return questionArea;
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
