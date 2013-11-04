package controller;

import database.QuestionsDao;
import model.StandardQuestion;
import model.YesOrNoAnswer;
import util.Const;
import view.exam_view.ExamPointsRightPanel;

import javax.swing.*;
import java.awt.*;
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
    private int actualStandardQuestion;

    private ExamPointsRightPanel examPointsRightPanel;
    private JLabel timerLbl;
    private JButton yesBtn;
    private JButton noBtn;

    private Timer timer;

    public ExamPresenter() {
        this.standardQuestions = QuestionsDao.getlistOfStandardQuestion();
        this.standardAnswers = new HashMap<Integer, YesOrNoAnswer>();
        actualStandardQuestion = 1;

        timer = new Timer();
    }

    public void nextStandardQuestion() {
        if (actualStandardQuestion < 20) {

            examPointsRightPanel.setNumberOfStandardQuestion(actualStandardQuestion);

            System.out.println(actualStandardQuestion);

            TimerCountdown countDown = new TimerCountdown(this, timerLbl, 5);
            timer.schedule(countDown, 0, 1000);
            actualStandardQuestion++;
        } else {
            System.out.println("Przechodzę do drugiego rodzaju pytań");
        }
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

    private void markBtn(JButton whichButtonToMark) {
        unmarkBtns();
        whichButtonToMark.setBackground(Const.Colors.markedBtnBgColor);
    }

    private void unmarkBtns() {
        yesBtn.setBackground(Const.Colors.unmarkedBtnBgColor);
        noBtn.setBackground(Const.Colors.unmarkedBtnBgColor);
    }

    public void setTimerLbl(JLabel timerLbl) {
        this.timerLbl = timerLbl;
        startTimer(timerLbl);
    }

    private void startTimer(JLabel timerLbl) {
        Timer timer = new Timer();
        TimerCountdown countDown = new TimerCountdown(this, timerLbl, 5);
        timer.schedule(countDown, 0, 1000);
    }

    public void setYesBtn(JButton yesBtn) {
        this.yesBtn = yesBtn;
        this.yesBtn.addActionListener(new YesBtnListener());
    }

    public void setNoBtn(JButton noBtn) {
        this.noBtn = noBtn;
        this.noBtn.addActionListener(new NoBtnListener());
    }

    public void setExamPointsRightPanel(ExamPointsRightPanel examPointsRightPanel) {
        this.examPointsRightPanel = examPointsRightPanel;
    }
}
