package com.danielmichalski.elicense.timer;

import com.danielmichalski.elicense.ui.exam.logic.ExamPresenter;

import java.awt.*;

/**
 * Author: Daniel
 */
public class SpecialistPartTimerCountdown extends TimerCountDown {
    private int sec = 50;
    private ExamPresenter examPresenter;

    public SpecialistPartTimerCountdown(ExamPresenter presenter) {
        this.examPresenter = presenter;
    }

    @Override
    public void run() {
        examPresenter.getTimerLbl().setForeground(Color.red);
        if (sec == 50) {
            examPresenter.showMedia();
            examPresenter.getTimerLbl().setText("" + sec);
        } else if (sec <= 0) {
            examPresenter.cancelTimerCountdownTask();
            examPresenter.trySaveAnswer();
            examPresenter.nextQuestion();
        }

        examPresenter.getTimerLbl().setText("" + sec);
        sec--;
    }
}

