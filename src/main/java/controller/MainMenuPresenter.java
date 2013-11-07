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
            ShowInfoAboutExamDialog dialog = new ShowInfoAboutExamDialog(true);
            dialog.setVisible(true);
        }
    }

    class ShowInfoAboutExam implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            ShowInfoAboutExamDialog dialog = new ShowInfoAboutExamDialog(false);
            dialog.setVisible(true);
        }
    }

    class CloseWindowListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            showConfirmClosingDialog();
        }
    }

    public void showConfirmClosingDialog() {
        UIManager.put("OptionPane.yesButtonText", TextsDao.getText("yesButtonLbl"));
        UIManager.put("noButtonLbl", TextsDao.getText("noButtonLbl"));
        int answer = JOptionPane.showConfirmDialog(
                null,
                TextsDao.getText("view.confirmDialog.message"),
                TextsDao.getText("view.confirmDialog.title"),
                JOptionPane.YES_NO_OPTION,
                JOptionPane.INFORMATION_MESSAGE);

        if (answer == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }

    public void setStartExamBtn(JButton startExamBtn) {
        startExamBtn.addActionListener(new StartExamActionListener());
    }

    public void setInfoAboutExamBtn(JButton aboutExamBtn) {
        aboutExamBtn.addActionListener(new ShowInfoAboutExam());
    }

    public void setStartExamMenuItem(JMenuItem startExamMenuItem) {
        startExamMenuItem.addActionListener(new StartExamActionListener());
    }

    public void setCloseMenuItem(JMenuItem closeMenuItem) {
        closeMenuItem.addActionListener(new CloseWindowListener());
    }
}
