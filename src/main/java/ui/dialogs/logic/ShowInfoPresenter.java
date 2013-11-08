package ui.dialogs.logic;

import ui.exam.view.ExamFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE;

/**
 * Author: Daniel
 * Date: 08.11.13
 */
public class ShowInfoPresenter {
    public ActionListener getStartExamListener(final WindowCloser windowCloser) {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                windowCloser.close();
                ExamFrame examFrame = new ExamFrame();
                examFrame.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
                examFrame.setVisible(true);
            }
        };
    }

    public ActionListener getCloseAboutExamListener(final WindowCloser windowCloser) {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                windowCloser.close();
            }
        };
    }
}
