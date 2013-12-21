package timer;

import ui.exam.logic.ExamPresenter;

import java.util.TimerTask;

/**
 * Author: Daniel
 * Date: 09.11.13
 */
public abstract class TimerCountDown extends TimerTask {
    private int sec;
    private ExamPresenter examPresenter;
    private boolean isStandardPart;

    public TimerCountDown(ExamPresenter examPresenter, int sec, boolean isStandardPart) {
        this.sec = sec;
        this.examPresenter = examPresenter;
        this.isStandardPart = isStandardPart;

        if (isStandardPart) {
            this.examPresenter.showWaitMedia();
        }
    }

    @Override
    public void run() {
        if (sec > 0) {
            if (isStandardPart && sec == 15) {
                examPresenter.showMedia();
            } else if (sec == 50) {
                examPresenter.showMedia();
            }
            examPresenter.getTimerLbl().setText("" + sec);
        } else {
            examPresenter.cancelTimerCountdownTask();
            examPresenter.trySaveAnswer();
            examPresenter.nextQuestion();
        }

        sec--;
    }
}
