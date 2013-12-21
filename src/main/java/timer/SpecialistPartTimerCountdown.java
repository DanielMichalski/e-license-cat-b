package timer;

import ui.exam.logic.ExamPresenter;

/**
 * Author: Daniel
 * Date: 03.11.13
 */
public class SpecialistPartTimerCountdown extends TimerCountDown {
    private static final int HOW_MANY_SEC = 50;

    public SpecialistPartTimerCountdown(ExamPresenter presenter) {
        super(presenter, HOW_MANY_SEC, false);
    }
}

