package controller;

import java.util.TimerTask;

/**
 * Author: Daniel
 * Date: 03.11.13
 */
public class SpecialistPartTimerCountdown extends TimerTask {
    private ExamPresenter examPresenter;
    private int sec = 50;

    public SpecialistPartTimerCountdown(ExamPresenter examPresenter) {
        this.examPresenter = examPresenter;
    }

    @Override
    public void run() {

        if (sec >= 0) {
            examPresenter.getTimerLbl().setText("" + sec);
        } else {
            examPresenter.nextQuestion();
            this.cancel();
        }

        sec--;
    }
}

