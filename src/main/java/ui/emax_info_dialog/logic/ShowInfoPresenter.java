package ui.emax_info_dialog.logic;

import ui.login.view.LoginFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Author: Daniel
 * Date: 08.11.13
 */
public class ShowInfoPresenter {
    public ActionListener getStartExamListener(final IWindowCloser windowCloser) {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                windowCloser.close();

                EventQueue.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        LoginFrame loginFrame = new LoginFrame(true);
                        loginFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                        loginFrame.setVisible(true);
                    }
                });
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
