package ui.learning.logic;

import database.dao.TextsDao;
import model.SpecialistQuestion;
import model.StandardQuestion;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

/**
 * Author: Daniel
 * Date: 08.11.13
 */
public class LearningPresenter {

    private List<StandardQuestion> standardQuestions;
    private List<SpecialistQuestion> specialistQuestions;

    public LearningPresenter(List<StandardQuestion> standardQuestions,
                             List<SpecialistQuestion> specialistQuestions) {
        this.standardQuestions = standardQuestions;
        this.specialistQuestions = specialistQuestions;
    }

    private class CloseBtnListener implements ActionListener {
        private JDialog jDialog;

        public CloseBtnListener(JDialog jDialog) {
            this.jDialog = jDialog;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            showCloseConfirmDialog(jDialog);
        }
    }

    public void showCloseConfirmDialog(Window window) {
        UIManager.put("OptionPane.yesButtonText", TextsDao.getText("yesButtonLbl"));
        UIManager.put("OptionPane.noButtonText", TextsDao.getText("noButtonLbl"));
        int answer = JOptionPane.showConfirmDialog(
                null,
                TextsDao.getText("view.confirmDialog.message"),
                TextsDao.getText("view.confirmDialog.title"),
                JOptionPane.YES_NO_OPTION,
                JOptionPane.INFORMATION_MESSAGE);

        if (answer == JOptionPane.YES_OPTION) {
            window.dispose();
        }
    }

    public WindowListener getWindowListener(final Window window) {
        return new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                showCloseConfirmDialog(window);
            }
        };
    }

    /*class PrintBtnListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            PDFGenerator pdfGenerator = new PDFGenerator(standardQuestions,
                    specialistQuestions,
                    countUserPoints(),
                    countAllPoints());

            pdfGenerator.generatePDFFile();
        }
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
        StandardQuestion question = standardQuestions.get(numOfQuestion - 1);
        MediaType mediaType = question.getMediaType();

        YesNoAnswer userAnswer = question.getUserAnswer();
        YesNoAnswer correctAnswer = question.getCorrectAnswer();

        String mediaPath = question.getMediaPath();
        switch (mediaType) {
            case IMAGE:
                leftPanel.setImageName(mediaPath);
                break;
            case VIDEO:
                leftPanel.setVideoName(mediaPath);
                break;
        }

        leftPanel.setQestion(question.getQuestion());

        rightPanel.setHowManyQuestionPoints(question.getPoints());

        leftPanel.setUserAndCorrectAnswer(userAnswer, correctAnswer);
    }

    public void setNumberOfSpecialistQuestion(int numOfQuestion) {
        SpecialistQuestion question = specialistQuestions.get(numOfQuestion - 1);
        MediaType mediaType = question.getMediaType();

        String answerA = question.getAnswerA();
        String answerB = question.getAnswerB();
        String answerC = question.getAnswerC();

        ABCAnswer userAnswer = question.getUserAnswer();
        ABCAnswer correctAnswer = question.getCorrectAnswer();

        String mediaPath = question.getMediaPath();
        switch (mediaType) {
            case IMAGE:
                leftPanel.setImageName(mediaPath);
                break;
            case VIDEO:
                leftPanel.setVideoName(mediaPath);
                break;
        }

        leftPanel.setQestion(question.getQuestion());

        rightPanel.setHowManyQuestionPoints(question.getPoints());

        leftPanel.setBtnABCTexts(answerA, answerB, answerC);
        leftPanel.setUserAndCorrectAnswer(userAnswer, correctAnswer);
    }

    private class CloseBtnListener implements ActionListener {
        private JDialog jDialog;

        public CloseBtnListener(JDialog jDialog) {
            this.jDialog = jDialog;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            showCloseConfirmDialog(jDialog);
        }
    }

    private void showCloseConfirmDialog(JDialog dialog) {
        UIManager.put("OptionPane.yesButtonText", TextsDao.getText("yesButtonLbl"));
        UIManager.put("OptionPane.noButtonText", TextsDao.getText("noButtonLbl"));
        int answer = JOptionPane.showConfirmDialog(
                null,
                TextsDao.getText("view.confirmDialog.message"),
                TextsDao.getText("view.confirmDialog.title"),
                JOptionPane.YES_NO_OPTION,
                JOptionPane.INFORMATION_MESSAGE);

        if (answer == JOptionPane.YES_OPTION) {
            dialog.dispose();
        }
    }

    public void changePanelToStandardPanel() {
        leftPanel.changePanelToStandardPanel();
    }

    public void changePanelToSpecialistPanel() {
        leftPanel.changePanelToSpecialistPanel();
    }

    public void setLeftPanel(LearningLeftPanel leftPanel) {
        this.leftPanel = leftPanel;
    }

    public void setRightPanel(LearningRightPanel rightPanel) {
        this.rightPanel = rightPanel;
    }

    public void setStandardQuestions(List<StandardQuestion> standardQuestions) {
        this.standardQuestions = standardQuestions;
    }

    public void setSpecialistQuestions(List<SpecialistQuestion> specialistQuestions) {
        this.specialistQuestions = specialistQuestions;
    }

    public void setCloseBtn(JButton closeBtn, JDialog jDialog) {
        closeBtn.addActionListener(new CloseBtnListener(jDialog));
    }

    public void setPrintBtn(JButton printBtn) {
        printBtn.addActionListener(new PrintBtnListener());
    }

    */
}