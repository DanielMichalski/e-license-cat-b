package controller;

import database.TextsDao;
import view.dialogs.ShowInfoAboutExamDialog;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Author: dmichalski
 * Date: 01.11.13
 */
public class MainMenuPresenter {

    class StartExamActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            ShowInfoAboutExamDialog dialog = new ShowInfoAboutExamDialog();
            dialog.setVisible(true);
        }
    }

    class CloseMenuListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int answer = JOptionPane.showConfirmDialog(
                    null,
                    TextsDao.get("view.confirmDialog.message"),
                    TextsDao.get("view.confirmDialog.title"),
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.INFORMATION_MESSAGE);

            if (answer == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        }
    }

    public void setVisitsBtn(JButton visitsBtn) {
        visitsBtn.addActionListener(new StartExamActionListener());
    }

    public void setStartExamMenuItem(JMenuItem startExamMenuItem) {
        startExamMenuItem.addActionListener(new StartExamActionListener());
    }

    public void setCloseMenuItem(JMenuItem closeMenuItem) {
        closeMenuItem.addActionListener(new CloseMenuListener());
    }
}
