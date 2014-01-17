package timer;

import ui.exam.logic.ExamPresenter;

/**
 * Author: Daniel
 * Date: 03.11.13
 */
public class SpecialistPartTimerCountdown extends TimerCountDown {
    private int sec = 50;
    private ExamPresenter examPresenter;

    public SpecialistPartTimerCountdown(ExamPresenter presenter) {
        this.examPresenter = presenter;
    }

    @Override
    public void run() {
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

