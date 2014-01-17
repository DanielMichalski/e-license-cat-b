package timer;

import ui.exam.logic.ExamPresenter;

import java.awt.*;

/**
 * Author: Daniel
 * Date: 07.11.13
 */
public class StandardPartTimerCountdown extends TimerCountDown {
    private int sec = 20;
    private ExamPresenter examPresenter;
    private int secToVid;
    private LoadingType loadingType = LoadingType.question;

    public StandardPartTimerCountdown(ExamPresenter presenter, int secToVid) {
        this.examPresenter = presenter;
        this.secToVid = secToVid;
    }

    @Override
    public void run() {
        if (loadingType == LoadingType.question) {
            if (sec == 20) {
                examPresenter.getTimerLbl().setForeground(Color.blue);
                examPresenter.showWaitMedia();
            } else if (sec < 0) {
                loadingType = LoadingType.movie;
                sec = secToVid;
                return;
            }
        }

        if (loadingType == LoadingType.movie) {
            if (sec == secToVid) {
                examPresenter.getTimerLbl().setText("");
                examPresenter.getTimerLbl().setForeground(Color.red);
                examPresenter.showMedia();
            } else if (sec <= 0) {
                loadingType = LoadingType.answer;
                sec = 15;
                return;
            }
        }

        if (loadingType == LoadingType.answer) {
            if (sec <= 0) {
                examPresenter.cancelTimerCountdownTask();
                examPresenter.trySaveAnswer();
                examPresenter.nextQuestion();
            }
        }

        if (loadingType != LoadingType.movie) {
            examPresenter.getTimerLbl().setText("" + sec);
        }

        sec--;
    }
}
