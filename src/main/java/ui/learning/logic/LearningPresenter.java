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
    private JTextArea categoryName;
    private JLabel howManyPointsForQuestionLbl;

    private List<StandardQuestion> standardQuestions;
    private List<SpecialistQuestion> specialistQuestions;

    public LearningPresenter(List<StandardQuestion> standardQuestions,
                             List<SpecialistQuestion> specialistQuestions) {
        this.standardQuestions = standardQuestions;
        this.specialistQuestions = specialistQuestions;
    }

    private class CloseBtnListener implements ActionListener {
        private Window jDialog;

        public CloseBtnListener(Window jDialog) {
            this.jDialog = jDialog;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            EventQueue.invokeLater(new Runnable() {
                @Override
                public void run() {
                    showCloseConfirmDialog(jDialog);
                }
            });

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
            System.exit(0);
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

    class PlayMovieBtnListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("play");
        }
    }

    class PreviousBtnListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("prev");
        }
    }

    class NextBtnListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("next");
        }
    }

    class RandomQuestionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("random");
        }
    }

    class CheckAnswerBtnListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("check");
        }
    }


    /*
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

    */

    public void setCloseBtn(JButton closeBtn, Window window) {
        closeBtn.addActionListener(new CloseBtnListener(window));
    }

    public void setCategoryName(JTextArea categoryName) {
        this.categoryName = categoryName;
    }

    public void setPlayMovieBtn(JButton playMovieBtn) {
        playMovieBtn.addActionListener(new PlayMovieBtnListener());
    }

    public void setPreviousBtn(JButton previousBtn) {
        previousBtn.addActionListener(new PreviousBtnListener());
    }

    public void setNextBtn(JButton nextBtn) {
        nextBtn.addActionListener(new NextBtnListener());
    }

    public void setRandomQuestionBtn(JButton randomQuestionBtn) {
        randomQuestionBtn.addActionListener(new RandomQuestionListener());
    }

    public void setCheckAnswerBtn(JButton checkAnswerBtn) {
        checkAnswerBtn.addActionListener(new CheckAnswerBtnListener());
    }

    public void setHowManyPointsForQuestionLbl(JLabel howManyPointsForQuestionLbl) {
        this.howManyPointsForQuestionLbl = howManyPointsForQuestionLbl;
    }
}
