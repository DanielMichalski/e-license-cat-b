package controller;

import database.TextsDao;
import view.dialogs.ShowInfoAboutExamDialog;
import view.main_menu.MainMenuFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Author: dmichalski
 * Date: 01.11.13
 */
public class MainMenuPresenter {

    class StartExamBtnListener implements ActionListener {
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
        JButton visitsBtn1 = visitsBtn;
        visitsBtn1.addActionListener(new StartExamBtnListener());
    }

    public void setCloseMenuItem(JMenuItem closeMenuItem) {
        closeMenuItem.addActionListener(new CloseMenuListener());
    }
}
