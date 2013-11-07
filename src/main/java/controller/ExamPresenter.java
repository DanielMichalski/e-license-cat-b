package controller;

import database.QuestionsDao;
import model.SpecialistQuestion;
import model.StandardQuestion;
import model.enums.ABCAnswer;
import model.enums.YesOrNoAnswer;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import view.exam_view.ExamPointsRightPanel;
import view.exam_view.ExamQuestionsLeftPanel;

import javax.swing.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Author: Daniel
 * Date: 03.11.13
 */
public class ExamPresenter {
    private List<StandardQuestion> standardQuestions;

    private List<SpecialistQuestion> specialistQuestions;

    private int actualStandardQuestion;
    private int actualSpecialistQuestion;

    private ExamQuestionsLeftPanel examQuestionsLeftPanel;
    private ExamPointsRightPanel.BasicPartPanel basicPartPanel;
    private ExamPointsRightPanel.SpecjalistPartPanel specjalistPartPanel;

    private JLabel timerLbl;
    private JButton yesBtn;
    private JButton noBtn;
    private JButton btnA;
    private JButton btnB;
    private JButton btnC;
    private JLabel howManyPoints;

    private Timer timer;
    private boolean isStandardPartCompleted;

    public ExamPresenter() {
        try {
            this.standardQuestions = QuestionsDao.get20StandardQuestion();
            this.specialistQuestions = QuestionsDao.get12SpecialistQuestion();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, e);
        } catch (InvalidFormatException e) {
            JOptionPane.showMessageDialog(null, e);
        }

        actualStandardQuestion = 0;
        actualSpecialistQuestion = 0;

        isStandardPartCompleted = false;
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
                endExam();
            }
        }
        enableBtns();
    }

    private void endExam() {
        timer.cancel();
        timerLbl.setText("KONIEC");
    }

    private void changePanelFromStandardToSpecial() {
        examQuestionsLeftPanel.changePanelFromStandarToSpecial();
    }

    public void setNumberOfStandardQuestion(int questionNumber) {
        basicPartPanel.setQuestionNumber(questionNumber);
        examQuestionsLeftPanel.setQestion(standardQuestions.get(questionNumber - 1).getQuestion());
        howManyPoints.setText(standardQuestions.get(questionNumber - 1).getPoints() + " pkt");
    }

    public void setNumberOfSpecialistQuestion(int questionNumber) {
        specjalistPartPanel.setQuestionNumber(questionNumber);
        examQuestionsLeftPanel.setQestion(specialistQuestions.get(questionNumber - 1).getQuestion());
        String btnAText = specialistQuestions.get(questionNumber - 1).getAnswerA();
        String btnBText = specialistQuestions.get(questionNumber - 1).getAnswerB();
        String btnCText = specialistQuestions.get(questionNumber - 1).getAnswerC();

        examQuestionsLeftPanel.setBtnABCTexts(btnAText, btnBText, btnCText);

        howManyPoints.setText(specialistQuestions.get(questionNumber - 1).getPoints() + " pkt");
    }

    public WindowListener getWindowListener() {
        return new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                endExam();
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
            enableBtns();
        }

        private void setStandardAnswer(YesOrNoAnswer answer) {
            standardQuestions.get(actualStandardQuestion - 1).setUserAnswer(answer);
            StandardQuestion standardQuestion = standardQuestions.get(actualStandardQuestion - 1);
            String text = String.format("%d. User answer: %s, correct: %s", (actualStandardQuestion), standardQuestion.getUserAnswer(), standardQuestion.getCorrectAnswer());
            System.out.println(text);
            timer.cancel();
            nextQuestion();
        }

        private void setSpecialistAnswer(ABCAnswer answer) {
            specialistQuestions.get(actualSpecialistQuestion - 1).setUserAnswer(answer);
            SpecialistQuestion specialistQuestion = specialistQuestions.get(actualSpecialistQuestion - 1);
            String text = String.format("%d. User answer: %s, correct: %s", (actualSpecialistQuestion), specialistQuestion.getUserAnswer(), specialistQuestion.getCorrectAnswer());
            System.out.println(text);
            timer.cancel();
            nextQuestion();
        }
    }

    private void markBtn(JButton whichButtonToMark) {
        enableBtns();
        whichButtonToMark.setEnabled(false);
    }

    private void enableBtns() {
        yesBtn.setEnabled(true);
        noBtn.setEnabled(true);
        btnA.setEnabled(true);
        btnB.setEnabled(true);
        btnC.setEnabled(true);
    }

    public void setTimerLbl(JLabel timerLbl) {
        this.timerLbl = timerLbl;
    }

    public void setYesBtn(JButton yesBtn) {
        this.yesBtn = yesBtn;
        this.yesBtn.addActionListener(new YesBtnListener());
    }

    public void setNoBtn(JButton noBtn) {
        this.noBtn = noBtn;
        this.noBtn.addActionListener(new NoBtnListener());
    }

    public void setBtnA(JButton btnA) {
        this.btnA = btnA;
        this.btnA.addActionListener(new ABtnListener());
    }

    public void setBtnB(JButton btnB) {
        this.btnB = btnB;
        this.btnB.addActionListener(new BBtnListener());
    }

    public void setBtnC(JButton btnC) {
        this.btnC = btnC;
        this.btnC.addActionListener(new CBtnListener());
    }

    public void setConfirmBtn(JButton confirmBtn) {
        confirmBtn.addActionListener(new ConfirmBtnListener());
    }

    public void setHowManyPoints(JLabel howManyPoints) {
        this.howManyPoints = howManyPoints;
    }

    public void setExamQuestionsLeftPanel(ExamQuestionsLeftPanel examQuestionsLeftPanel) {
        this.examQuestionsLeftPanel = examQuestionsLeftPanel;
    }

    public void setBasicPartPanel(ExamPointsRightPanel.BasicPartPanel basicPartPanel) {
        this.basicPartPanel = basicPartPanel;
    }

    public void setSpecjalistPartPanel(ExamPointsRightPanel.SpecjalistPartPanel specjalistPartPanel) {
        this.specjalistPartPanel = specjalistPartPanel;
    }

    public JLabel getTimerLbl() {
        return timerLbl;
    }
}
