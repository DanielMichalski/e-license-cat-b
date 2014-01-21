package ui.exam_info.logic;

import ui.login.view.LoginFrame;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Author: Daniel
 */
public class ExamInfoPresenter {
    class SluchaczKiknięciaStartExam extends MouseAdapter {
        private JDialog dialog;

        public SluchaczKiknięciaStartExam(JDialog dialog) {
            this.dialog = dialog;
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            dialog.dispose();

            LoginFrame loginFrame = new LoginFrame();
            loginFrame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
            loginFrame.setVisible(true);
        }
    }

    public void setStartExamBtn(JLabel startExamBtn, JDialog dialog) {
        startExamBtn.addMouseListener(new SluchaczKiknięciaStartExam(dialog));
    }
}
