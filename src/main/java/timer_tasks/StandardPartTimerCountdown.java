package timer_tasks;

import ui.exam.controller.ExamPresenter;

import java.util.TimerTask;

/**
 * Author: Daniel
 * Date: 07.11.13
 */
public class StandardPartTimerCountdown extends TimerTask {
    private ExamPresenter examPresenter;
    private int sec = 35;

    public StandardPartTimerCountdown(ExamPresenter examPresenter) {
        this.examPresenter = examPresenter;
        this.examPresenter.showWaitImage();
    }

    @Override
    public void run() {
        if (sec >= 0) {
            if (sec == 15) {
                examPresenter.showImage();
            }
            examPresenter.getTimerLbl().setText("" + sec);
        } else {
            examPresenter.nextQuestion();
            this.cancel();
        }

        sec--;
    }
}
