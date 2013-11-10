package ui.exam_result.controller;

import model.ABCAnswer;
import model.SpecialistQuestion;
import model.StandardQuestion;
import model.YesNoAnswer;
import ui.exam_result.view.ExamResultLeftPanel;
import ui.exam_result.view.ExamResultRightPanel;

import java.util.List;

/**
 * Author: Daniel
 * Date: 08.11.13
 */
public class ExamResultPresenter {
    private List<StandardQuestion> standardQuestions;
    private List<SpecialistQuestion> specialistQuestions;

    private ExamResultLeftPanel leftPanel;
    private ExamResultRightPanel rightPanel;

    public static final int HOW_MANY_POINTS_TO_PASS = 68;

    public ExamResultPresenter(List<StandardQuestion> standardQuestions,
                               List<SpecialistQuestion> specialistQuestions) {
        this.standardQuestions = standardQuestions;
        this.specialistQuestions = specialistQuestions;
    }

    public void setUpPanels() {
        int userPoints = countUserPoints();
        int allPoints = countAllPoints();

        boolean isPassed = userPoints >= HOW_MANY_POINTS_TO_PASS;
        rightPanel.setExamResult(isPassed, userPoints, allPoints);

        setNumberOfStandardQuestion(1);
    }

    private int countUserPoints() {
        int points = 0;

        for (StandardQuestion question : standardQuestions) {
            if (question.getUserAnswer() == question.getCorrectAnswer()) {
                points += question.getPoints();
            }
        }

        for (SpecialistQuestion question : specialistQuestions) {
            if (question.getUserAnswer() == question.getCorrectAnswer()) {
                points += question.getPoints();
            }
        }

        return points;
    }

    private int countAllPoints() {
        int points = 0;

        for (StandardQuestion question : standardQuestions) {
            points += question.getPoints();
        }

        for (SpecialistQuestion question : specialistQuestions) {
            points += question.getPoints();
        }

        return points;
    }

    public void setNumberOfStandardQuestion(int numOfQuestion) {
        YesNoAnswer userAnswer = standardQuestions.get(numOfQuestion - 1).getUserAnswer();
        YesNoAnswer correctAnswer = standardQuestions.get(numOfQuestion - 1).getCorrectAnswer();

        leftPanel.setImageName("Slajd117_ITS.jpg");
        leftPanel.setQestion(standardQuestions.get(numOfQuestion - 1).getQuestion());

        rightPanel.setHowManyQuestionPoints(standardQuestions.get(numOfQuestion - 1).getPoints());

        leftPanel.setUserAndCorrectAnswer(userAnswer, correctAnswer);
    }

    public void setNumberOfSpecialistQuestion(int numOfQuestion) {
        String answerA = specialistQuestions.get(numOfQuestion - 1).getAnswerA();
        String answerB = specialistQuestions.get(numOfQuestion - 1).getAnswerB();
        String answerC = specialistQuestions.get(numOfQuestion - 1).getAnswerC();

        ABCAnswer userAnswer = specialistQuestions.get(numOfQuestion - 1).getUserAnswer();
        ABCAnswer correctAnswer = specialistQuestions.get(numOfQuestion - 1).getCorrectAnswer();

        leftPanel.setImageName("Slajd117_ITS.jpg");
        leftPanel.setQestion(specialistQuestions.get(numOfQuestion - 1).getQuestion());

        rightPanel.setHowManyQuestionPoints(specialistQuestions.get(numOfQuestion - 1).getPoints());

        leftPanel.setBtnABCTexts(answerA, answerB, answerC);
        leftPanel.setUserAndCorrectAnswer(userAnswer, correctAnswer);
    }

    public void changePanelToStandardPanel() {
        leftPanel.changePanelToStandardPanel();
    }

    public void changePanelToSpecialistPanel() {
        leftPanel.changePanelToSpecialistPanel();
    }

    public void setLeftPanel(ExamResultLeftPanel leftPanel) {
        this.leftPanel = leftPanel;
    }

    public void setRightPanel(ExamResultRightPanel rightPanel) {
        this.rightPanel = rightPanel;
    }

    public void setStandardQuestions(List<StandardQuestion> standardQuestions) {
        this.standardQuestions = standardQuestions;
    }

    public void setSpecialistQuestions(List<SpecialistQuestion> specialistQuestions) {
        this.specialistQuestions = specialistQuestions;
    }

}
