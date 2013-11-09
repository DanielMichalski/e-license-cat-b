package timer_tasks;

import ui.exam.controller.ExamPresenter;

/**
 * Author: Daniel
 * Date: 07.11.13
 */
public class StandardPartTimerCountdown extends TimerCountDown {
    private static final int HOW_MANY_SEC = 35;

    public StandardPartTimerCountdown(ExamPresenter presenter) {
        super(presenter, HOW_MANY_SEC);
    }
}
