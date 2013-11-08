package ui.exam.controller;

import database.dao.QuestionsDao;
import database.dao.TextsDao;
import model.ABCAnswer;
import model.SpecialistQuestion;
import model.StandardQuestion;
import model.YesOrNoAnswer;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import timer_tasks.SpecialistPartTimerCountdown;
import timer_tasks.StandardPartTimerCountdown;
import ui.exam.view.ExamPointsRightPanel;
import ui.exam.view.ExamQuestionsLeftPanel;
import ui.exam.view.interfaces.WindowCloser;
import ui.exam_result.view.ExamResultFrame;

import javax.swing.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Logger;

/**
 * Author: Daniel
 * Date: 03.11.13
 */
public class ExamPresenter {
    public Logger LOGGER = Logger.getLogger(getClass().getName());

    private List<StandardQuestion> standardQuestions;
    private List<SpecialistQuestion> specialistQuestions;

    private int actualStandardQuestion = 0;
    private int actualSpecialistQuestion = 0;
    private boolean isStandardPartCompleted = false;

    private JButton yesBtn;
    private JButton noBtn;
    private JButton btnA;
    private JButton btnB;
    private JButton btnC;
    private JLabel timerLbl;

    private ExamQuestionsLeftPanel examQuestionsLeftPanel;
    private ExamPointsRightPanel.BasicPartPanel basicPartPanel;
    private ExamPointsRightPanel.SpecjalistPartPanel specjalistPartPanel;
    private ExamPointsRightPanel.TimeAndBtnConfirmPanel timeAndBtnConfirmPanel;

    private WindowCloser windowCloser;
    private Timer timer;

    public ExamPresenter(WindowCloser windowCloser) {
        this.windowCloser = windowCloser;

        try {
            this.standardQuestions = QuestionsDao.get20StandardQuestion();
            this.specialistQuestions = QuestionsDao.get12SpecialistQuestion();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, e);
        } catch (InvalidFormatException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public void nextQuestion() {
        timer = new Timer();
        TimerTask countDown;
        long period = 1000;

        try {
            actualStandardQuestion++;
            standardQuestions.get(actualStandardQuestion - 1);
            countDown = new StandardPartTimerCountdown(this);
            timer.schedule(countDown, 0, period);

            setNumberOfStandardQuestion(actualStandardQuestion);

        } catch (IndexOutOfBoundsException e) {
            try {
                if (actualSpecialistQuestion == 0) {
                    isStandardPartCompleted = true;
                    changePanelFromStandardToSpecial();
                }

                actualSpecialistQuestion++;
                specialistQuestions.get(actualSpecialistQuestion - 1);
                countDown = new SpecialistPartTimerCountdown(this);
                timer.schedule(countDown, 0, period);

                setNumberOfSpecialistQuestion(actualSpecialistQuestion);


            } catch (IndexOutOfBoundsException e1) {
                finishExam();
            }
        }
        enableAllBtns();
    }

    private void finishExam() {
        timer.cancel();
        windowCloser.close();
        showExamResultFrame();
    }

    private void showExamResultFrame() {
        ExamResultFrame examResultFrame = new ExamResultFrame(standardQuestions, specialistQuestions);
        examResultFrame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        examResultFrame.setVisible(true);
    }

    private void disableAllBtns() {
        examQuestionsLeftPanel.disableAllBtns();
    }

    private void enableAllBtns() {
        examQuestionsLeftPanel.enableAllBtns();
    }

    private void breakExam() {
        timer.cancel();
    }

    private void changePanelFromStandardToSpecial() {
        examQuestionsLeftPanel.changePanelFromStandarToSpecial();
    }

    public void setNumberOfStandardQuestion(int questionNumber) {
        basicPartPanel.setQuestionNumber(questionNumber);
        examQuestionsLeftPanel.setQestion(standardQuestions.get(questionNumber - 1).getQuestion());
        timeAndBtnConfirmPanel.setHowManyPointsText(standardQuestions.get(questionNumber - 1).getPoints() + " pkt");
    }

    public void setNumberOfSpecialistQuestion(int questionNumber) {
        specjalistPartPanel.setQuestionNumber(questionNumber);
        examQuestionsLeftPanel.setQestion(specialistQuestions.get(questionNumber - 1).getQuestion());
        String btnAText = specialistQuestions.get(questionNumber - 1).getAnswerA();
        String btnBText = specialistQuestions.get(questionNumber - 1).getAnswerB();
        String btnCText = specialistQuestions.get(questionNumber - 1).getAnswerC();

        examQuestionsLeftPanel.setBtnABCTexts(btnAText, btnBText, btnCText);

        timeAndBtnConfirmPanel.setHowManyPointsText(specialistQuestions.get(questionNumber - 1).getPoints() + " pkt");
    }

    public WindowListener getWindowListener(final JDialog dialog) {
        return new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                UIManager.put("OptionPane.yesButtonText", TextsDao.getText("yesButtonLbl"));
                UIManager.put("noButtonLbl", TextsDao.getText("noButtonLbl"));
                int answer = JOptionPane.showConfirmDialog(
                        null,
                        TextsDao.getText("view.examFramConfirmDialog.message"),
                        TextsDao.getText("view.confirmDialog.title"),
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.INFORMATION_MESSAGE);

                if (answer == JOptionPane.YES_OPTION) {
                    breakExam();
                    dialog.dispose();
                }
            }
        };
    }

    public void showWaitImage() {
        examQuestionsLeftPanel.setImagePath("/program_images/wait_photo.jpg");
    }

    public void showImage() {
        examQuestionsLeftPanel.setImagePath("/images/Slajd117_ITS.jpg");
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

    class ConfirmBtnListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (!isStandardPartCompleted) {
                if (!yesBtn.isEnabled()) {
                    setStandardAnswer(YesOrNoAnswer.TAK);
                } else if (!noBtn.isEnabled()) {
                    setStandardAnswer(YesOrNoAnswer.NIE);
                }
            } else {
                if (!btnA.isEnabled()) {
                    setSpecialistAnswer(ABCAnswer.A);
                } else if (!btnB.isEnabled()) {
                    setSpecialistAnswer(ABCAnswer.B);
                } else if (!btnC.isEnabled()) {
                    setSpecialistAnswer(ABCAnswer.C);
                }
            }
            enableAllBtns();
        }

        private void setStandardAnswer(YesOrNoAnswer answer) {
            standardQuestions.get(actualStandardQuestion - 1).setUserAnswer(answer);
            StandardQuestion standardQuestion = standardQuestions.get(actualStandardQuestion - 1);

            int resultPoints = 0;
            if (standardQuestion.getUserAnswer() == standardQuestion.getCorrectAnswer()) {
                resultPoints = standardQuestion.getPoints();
            }

            String text = String.format("%d. User answer: %s, correct: %s, resultPoints: %d",
                    (actualStandardQuestion),
                    standardQuestion.getUserAnswer(),
                    standardQuestion.getCorrectAnswer(),
                    resultPoints);

            LOGGER.info(text);
            timer.cancel();
            nextQuestion();
        }

        private void setSpecialistAnswer(ABCAnswer answer) {
            specialistQuestions.get(actualSpecialistQuestion - 1).setUserAnswer(answer);
            SpecialistQuestion specialistQuestion = specialistQuestions.get(actualSpecialistQuestion - 1);

            int resultPoints = 0;
            if (specialistQuestion.getUserAnswer() == specialistQuestion.getCorrectAnswer()) {
                resultPoints = specialistQuestion.getPoints();
            }

            String text = String.format("%d. User answer: %s, correct: %s, resultPoints: %d",
                    (actualSpecialistQuestion),
                    specialistQuestion.getUserAnswer(),
                    specialistQuestion.getCorrectAnswer(),
                    resultPoints);

            LOGGER.info(text);
            timer.cancel();
            nextQuestion();
        }
    }

    private void markBtn(JButton whichButtonToMark) {
        enableAllBtns();
        whichButtonToMark.setEnabled(false);
    }

    public void setExamQuestionsLeftPanel(ExamQuestionsLeftPanel examQuestionsLeftPanel) {
        this.examQuestionsLeftPanel = examQuestionsLeftPanel;

        this.yesBtn = examQuestionsLeftPanel.getYesBtn();
        this.yesBtn.addActionListener(new YesBtnListener());

        this.noBtn = examQuestionsLeftPanel.getNoBtn();
        this.noBtn.addActionListener(new NoBtnListener());

        this.btnA = examQuestionsLeftPanel.getBtnA();
        this.btnA.addActionListener(new ABtnListener());

        this.btnB = examQuestionsLeftPanel.getBtnB();
        this.btnB.addActionListener(new BBtnListener());

        this.btnC = examQuestionsLeftPanel.getBtnC();
        this.btnC.addActionListener(new CBtnListener());
    }

    public void setBasicPartPanel(ExamPointsRightPanel.BasicPartPanel basicPartPanel) {
        this.basicPartPanel = basicPartPanel;
    }

    public void setSpecjalistPartPanel(ExamPointsRightPanel.SpecjalistPartPanel specjalistPartPanel) {
        this.specjalistPartPanel = specjalistPartPanel;
    }

    public void setTimeAndBtnConfirmPanel(ExamPointsRightPanel.TimeAndBtnConfirmPanel timeAndBtnConfirmPanel) {
        this.timeAndBtnConfirmPanel = timeAndBtnConfirmPanel;

        timerLbl = timeAndBtnConfirmPanel.getTimerLbl();

        timeAndBtnConfirmPanel.getConfirmBtn().addActionListener(new ConfirmBtnListener());
    }

    public JLabel getTimerLbl() {
        return timerLbl;
    }
}
