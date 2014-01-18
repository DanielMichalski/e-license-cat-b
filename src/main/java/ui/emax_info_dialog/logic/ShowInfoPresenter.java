package ui.emax_info_dialog.logic;

import ui.emax_info_dialog.model.NextActionType;
import ui.login.view.LoginFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Author: Daniel
 * Date: 08.11.13
 */
public class ShowInfoPresenter {
    private NextActionType actionType;

    public ShowInfoPresenter(NextActionType actionType) {
        this.actionType = actionType;
    }

    class SluchaczKiknięciaOk extends MouseAdapter {
        private JDialog dialog;

        public SluchaczKiknięciaOk(JDialog dialog) {
            this.dialog = dialog;
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            dialog.dispose();

            if (actionType == NextActionType.start_exam) {
                EventQueue.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        LoginFrame loginFrame = new LoginFrame();
                        loginFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                        loginFrame.setVisible(true);
                    }
                });
            }
        }
    }

    public void setAboutApp(JLabel aboutApp, JDialog dialog) {
        aboutApp.addMouseListener(new SluchaczKiknięciaOk(dialog));
    }
}
