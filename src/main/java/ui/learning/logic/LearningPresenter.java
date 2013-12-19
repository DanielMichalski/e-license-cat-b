package ui.learning.logic;

import database.dao.TextsDao;
import model.MediaType;
import model.SpecialistQuestion;
import model.StandardQuestion;
import ui.learning.view.LearningLeftPanel;
import util.Const;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

/**
 * Author: Daniel
 * Date: 08.11.13
 */
public class LearningPresenter {
    private int questionNum;

    private LearningLeftPanel learningLeftPanel;

    private JButton yesBtn;
    private JButton noBtn;
    private JButton btnA;
    private JButton btnB;
    private JButton btnC;

    private JTextArea categoryName;
    private JLabel howManyPointsForQuestionLbl;

    private List<StandardQuestion> standardQuestions;
    private List<SpecialistQuestion> specialistQuestions;

    private Color defaultColor;

    public LearningPresenter(List<StandardQuestion> standardQuestions,
                             List<SpecialistQuestion> specialistQuestions) {
        this.standardQuestions = standardQuestions;
        this.specialistQuestions = specialistQuestions;

        this.questionNum = 1;
    }

    private void markBtn(JButton whichButtonToMark) {
        unmarkBtns();
        whichButtonToMark.setBackground(Const.Colors.CLICKED_BTN_COLOR);
    }

    private void unmarkBtns() {
        learningLeftPanel.unmarkAllBtns();
    }

    public void setUpPanels() {
        setFirstQuestion();
    }

    private void setFirstQuestion() {
        questionNum = 1;
        if (standardQuestions.size() > 0) {
            StandardQuestion question = standardQuestions.get(questionNum - 1);
            MediaType mediaType = question.getMediaType();

            String mediaPath = question.getMediaPath();
            switch (mediaType) {
                case IMAGE:
                    learningLeftPanel.setImageName(mediaPath);
                    break;
                case VIDEO:
                    learningLeftPanel.setVideoName(mediaPath);
                    break;
            }

            learningLeftPanel.setQestion(question.getQuestion());
            howManyPointsForQuestionLbl.setText(question.getPoints() + " pkt");

        } else if (specialistQuestions.size() > 0) {
            SpecialistQuestion question = specialistQuestions.get(questionNum - 1);
            MediaType mediaType = question.getMediaType();

            String answerA = question.getAnswerA();
            String answerB = question.getAnswerB();
            String answerC = question.getAnswerC();

            String mediaPath = question.getMediaPath();
            switch (mediaType) {
                case IMAGE:
                    learningLeftPanel.setImageName(mediaPath);
                    break;
                case VIDEO:
                    learningLeftPanel.setVideoName(mediaPath);
                    break;
            }

            learningLeftPanel.setQestion(question.getQuestion());
            howManyPointsForQuestionLbl.setText(question.getPoints() + " pkt");
            learningLeftPanel.setBtnABCTexts(answerA, answerB, answerC);
        } else {
            showLastQuestionInfo();
        }

    }

    private void showLastQuestionInfo() {
        JOptionPane.showMessageDialog(
                null,
                "Koniec pytań",
                "Informacja",
                JOptionPane.INFORMATION_MESSAGE);
    }


    class YesBtnListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            markBtn(yesBtn);
        }
    }

    class NoBtnListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            markBtn(noBtn);
        }
    }

    class ABtnListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            markBtn(btnA);
        }
    }

    class BBtnListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            markBtn(btnB);
        }
    }

    class CBtnListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            markBtn(btnC);
        }
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

    public void setLearningLeftPanel(LearningLeftPanel learningLeftPanel) {
        this.learningLeftPanel = learningLeftPanel;

        this.yesBtn = learningLeftPanel.getYesBtn();
        this.yesBtn.addActionListener(new YesBtnListener());

        this.noBtn = learningLeftPanel.getNoBtn();
        this.noBtn.addActionListener(new NoBtnListener());

        this.btnA = learningLeftPanel.getBtnA();
        this.btnA.addActionListener(new ABtnListener());

        this.btnB = learningLeftPanel.getBtnB();
        this.btnB.addActionListener(new BBtnListener());

        this.btnC = learningLeftPanel.getBtnC();
        this.btnC.addActionListener(new CBtnListener());

        defaultColor = yesBtn.getBackground();
    }

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
