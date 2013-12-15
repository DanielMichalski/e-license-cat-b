package ui.emax_info_dialog.logic;

import ui.login.view.LoginFrame;
import ui.main_menu.view.IMinimalize;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Author: Daniel
 * Date: 08.11.13
 */
public class ShowInfoPresenter {
    public ActionListener getStartExamListener(final IWindowCloser windowCloser,
                                               final IMinimalize iMinimalize) {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                iMinimalize.minimalize();
                windowCloser.close();

                LoginFrame loginFrame = new LoginFrame();
                loginFrame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
                loginFrame.setVisible(true);
            }
        };
    }

    public ActionListener getCloseAboutExamListener(final IWindowCloser windowCloser) {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                windowCloser.close();
            }
        };
    }
}
