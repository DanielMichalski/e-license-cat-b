package controller;

import database.QuestionsDao;
import model.StandardQuestion;
import model.YesOrNoAnswer;
import util.Const;
import view.exam_view.ExamPointsRightPanel;
import view.exam_view.ExamQuestionsLeftPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;

/**
 * Author: Daniel
 * Date: 03.11.13
 */
public class ExamPresenter {
    private Map<Integer, StandardQuestion> standardQuestions;
    private Map<Integer, YesOrNoAnswer> standardAnswers;

    private Map<Integer, StandardQuestion> specialistQuestions;
    private Map<Integer, YesOrNoAnswer> specialistAnswers;

    private int actualStandardQuestion;
    private int actualSpecialistQuestion;

    private ExamQuestionsLeftPanel examQuestionsLeftPanel;
    private ExamPointsRightPanel.BasicPartPanel basicPartPanel;
    private ExamPointsRightPanel.SpecjalistPartPanel specjalistPartPanel;
    private ExamPointsRightPanel.TimeAndBtnConfirmPanel timeAndBtnConfirmPanel;

    private JLabel timerLbl;
    private JButton yesBtn;
    private JButton noBtn;

    private Timer timer;
    private boolean isStandardPartCompleted;

    public ExamPresenter() {
        this.standardQuestions = QuestionsDao.getlistOfStandardQuestion();
        this.standardAnswers = new HashMap<Integer, YesOrNoAnswer>();

        this.specialistQuestions = QuestionsDao.getlistOfSpecialistdQuestion();
        this.specialistAnswers = new HashMap<Integer, YesOrNoAnswer>();

        actualStandardQuestion = 1;
        actualSpecialistQuestion = 1;

        timer = new Timer();
        isStandardPartCompleted = false;
    }

    public void nextQuestion() {
        TimerCountdown countDown = new TimerCountdown(this, timerLbl, 3);
        timer.schedule(countDown, 0, 50);

        if (!isStandardPartCompleted) {
            if (actualStandardQuestion <= 20) {
                if (actualStandardQuestion == 20) {
                    isStandardPartCompleted = true;
                }
                setNumberOfStandardQuestion(actualStandardQuestion);

                actualStandardQuestion++;
            }
        }  else {
            if (actualSpecialistQuestion <= 12) {
                setNumberOfSpecialistQuestion(actualSpecialistQuestion);

                actualSpecialistQuestion++;
            } else {
                timer.cancel();
                System.out.println("Koniec");
            }
        }

    }

    public void setNumberOfStandardQuestion(int questionNumber) {
        basicPartPanel.setQuestionNumber(questionNumber);
        examQuestionsLeftPanel.setQestion(standardQuestions.get(questionNumber));
    }

    public void setNumberOfSpecialistQuestion(int questionNumber) {
        specjalistPartPanel.setQuestionNumber(questionNumber);
        examQuestionsLeftPanel.setQestion(specialistQuestions.get(questionNumber));
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

    class ConfirmBtnListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (yesBtn.isSelected()) {
                System.out.println("yes");
            } else if (noBtn.isSelected()){
                System.out.println("no");
            } else {
                System.out.println("żaden");
            }
        }
    }

    private void markBtn(JButton whichButtonToMark) {
        unmarkBtns();
        whichButtonToMark.setBackground(Const.Colors.markedBtnBgColor);
        whichButtonToMark.setSelected(true);
    }

    private void unmarkBtns() {
        yesBtn.setBackground(Const.Colors.unmarkedBtnBgColor);
        noBtn.setBackground(Const.Colors.unmarkedBtnBgColor);

        yesBtn.setSelected(false);
        noBtn.setSelected(false);
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

    public void setConfirmBtn(JButton confirmBtn) {
        confirmBtn.addActionListener(new ConfirmBtnListener());
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

    public void setTimeAndBtnConfirmPanel(ExamPointsRightPanel.TimeAndBtnConfirmPanel timeAndBtnConfirmPanel) {
        this.timeAndBtnConfirmPanel = timeAndBtnConfirmPanel;
    }

}
