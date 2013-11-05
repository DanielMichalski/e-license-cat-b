package controller;

import javax.swing.*;
import java.util.TimerTask;

/**
 * Author: Daniel
 * Date: 03.11.13
 */
public class TimerCountdown extends TimerTask {
    private ExamPresenter examPresenter;
    private JLabel timerLbl;
    private int sec = 40;

    public TimerCountdown(ExamPresenter examPresenter, JLabel timerLbl, int sec) {
        this.examPresenter = examPresenter;
        this.timerLbl = timerLbl;
        this.sec = sec;
    }

    @Override
    public void run() {

        if (sec >= 0) {
            timerLbl.setText("" + sec);
        } else {
            examPresenter.nextQuestion();
            this.cancel();
        }

        sec--;
    }
}

