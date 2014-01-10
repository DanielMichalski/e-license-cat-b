package ui.learning.logic;

import database.dao.TextsDao;
import model.*;
import ui.choose_category.view.ChooseCategoryFrame;
import ui.learning.view.LearningLeftPanel;
import ui.main_menu.view.MainMenuFrame;
import util.Const;
import util.FilesUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;
import java.util.Random;
import java.util.logging.Logger;

/**
 * Author: Daniel
 * Date: 08.11.13
 */
public class LearningPresenter {
    public Logger LOGGER = Logger.getLogger(getClass().getName());

    private int questionNum;
    private int allQuestiionNum;

    private LearningLeftPanel learningLeftPanel;

    private JButton playMovieBtn;
    private JButton yesBtn;
    private JButton noBtn;
    private JButton btnA;
    private JButton btnB;
    private JButton btnC;

    private JLabel howManyPointsForQuestionLbl;
    private JTextArea questionArea;

    private List<StandardQuestion> standardQuestions;
    private List<SpecialistQuestion> specialistQuestions;

    private Random random;

    public LearningPresenter(List<StandardQuestion> standardQuestions,
                             List<SpecialistQuestion> specialistQuestions) {
        this.standardQuestions = standardQuestions;
        this.specialistQuestions = specialistQuestions;

        this.questionNum = 1;
        this.allQuestiionNum = standardQuestions.size() + specialistQuestions.size();
        this.random = new Random();
    }

    private void markBtn(JButton whichButtonToMark) {
        unmarkBtns();
        whichButtonToMark.setBackground(Const.Colors.CLICKED_BTN_COLOR);
    }

    private void unmarkBtns() {
        learningLeftPanel.unmarkAllBtns();
    }

    public void setUpPanels() {
        setQuestion();
    }

    private void setQuestion() {
        questionArea.setText("Pytanie " + questionNum + " z " + allQuestiionNum);

        if (questionNum <= standardQuestions.size() && questionNum >= 1) {
            setStandardQuestion();
        } else if (questionNum <= allQuestiionNum) {
            setSpecialistQuestion();
        }
    }

    private void setStandardQuestion() {
        learningLeftPanel.changePanelToStandardPanel();

        StandardQuestion question = standardQuestions.get(questionNum - 1);
        MediaType mediaType = question.getMediaType();
        String mediaPath = question.getMediaPath();
        switch (mediaType) {
            case IMAGE:
                playMovieBtn.setEnabled(false);
                learningLeftPanel.setImageName(mediaPath);
                break;
            case VIDEO:
                playMovieBtn.setEnabled(true);
                learningLeftPanel.setVideoName(mediaPath);
                break;
        }

        learningLeftPanel.setQestion(question.getQuestion());
        howManyPointsForQuestionLbl.setText(question.getPoints() + " pkt");
    }

    private void setSpecialistQuestion() {
        learningLeftPanel.changePanelToSpecialistPanel();

        SpecialistQuestion question = specialistQuestions.get(questionNum - 1);
        MediaType mediaType = question.getMediaType();

        String answerA = question.getAnswerA();
        String answerB = question.getAnswerB();
        String answerC = question.getAnswerC();

        String mediaPath = question.getMediaPath();
        switch (mediaType) {
            case IMAGE:
                playMovieBtn.setEnabled(false);
                learningLeftPanel.setImageName(mediaPath);
                break;
            case VIDEO:
                playMovieBtn.setEnabled(true);
                learningLeftPanel.setVideoName(mediaPath);
                break;
        }

        learningLeftPanel.setQestion(question.getQuestion());
        howManyPointsForQuestionLbl.setText(question.getPoints() + " pkt");
        learningLeftPanel.setBtnABCTexts(answerA, answerB, answerC);
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

            if (questionNum <= standardQuestions.size()) {
                if (standardQuestions.get(questionNum - 1).getMediaType() == MediaType.VIDEO) {
                    learningLeftPanel.setVideoName(standardQuestions.get(questionNum - 1).getMediaPath());
                }
            } else if (questionNum <= allQuestiionNum) {
                if (specialistQuestions.get(questionNum - 1).getMediaType() == MediaType.VIDEO) {
                    learningLeftPanel.setVideoName(specialistQuestions.get(questionNum - 1).getMediaPath());
                }
            }
        }
    }

    class PreviousBtnListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (questionNum > 1) {
                unmarkBtns();
                questionNum--;
                setQuestion();
            }
        }
    }

    class NextBtnListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (questionNum < allQuestiionNum) {
                unmarkBtns();
                questionNum++;
                setQuestion();
            } else {
                showLastQuestionInfo();
            }
        }
    }

    class RandomQuestionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            unmarkBtns();
            questionNum = random.nextInt(allQuestiionNum) + 1;
            setQuestion();
        }
    }

    class CheckAnswerBtnListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (questionNum <= standardQuestions.size()) {
                YesNoAnswer userAnswer = learningLeftPanel.getUserAnswerYesNo();
                YesNoAnswer correctAnswer = standardQuestions.get(questionNum - 1).getCorrectAnswer();
                learningLeftPanel.setUserAndCorrectAnswer(userAnswer, correctAnswer);
                LOGGER.info(String.format("User answer: %s, correct answer: %s", userAnswer, correctAnswer));
            } else if (questionNum <= allQuestiionNum) {
                ABCAnswer userAnswer = learningLeftPanel.getUserAnswerAbc();
                ABCAnswer correctAnswer = specialistQuestions.get(questionNum - 1).getCorrectAnswer();
                learningLeftPanel.setUserAndCorrectAnswer(userAnswer, correctAnswer);
                LOGGER.info(String.format("User answer: %s, correct answer: %s", userAnswer, correctAnswer));
            }
        }
    }

    private class CategoryChooseListener implements ActionListener {
        Window window;

        private CategoryChooseListener(Window window) {
            this.window = window;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            EventQueue.invokeLater(new Runnable() {
                @Override
                public void run() {
                    learningLeftPanel.disposePlayer();
                    window.dispose();

                    ChooseCategoryFrame frame = new ChooseCategoryFrame();
                    frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                    frame.setVisible(true);
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
                EventQueue.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        learningLeftPanel.disposePlayer();
                        window.dispose();

                        FilesUtils.deleteTempFolderContent();

                        MainMenuFrame mv = new MainMenuFrame();
                        mv.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
                        mv.setVisible(true);
                    }
                });
            }
        };
    }

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
    }

    public void setCategoryChooseBtn(JButton categoryChooseButton, Window window) {
        categoryChooseButton.addActionListener(new CategoryChooseListener(window));
    }

    public void setQuestionArea(JTextArea questionArea) {
        this.questionArea = questionArea;
    }

    public void setPlayMovieBtn(JButton playMovieBtn) {
        this.playMovieBtn = playMovieBtn;
        this.playMovieBtn.addActionListener(new PlayMovieBtnListener());
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
