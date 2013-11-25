package ui.main_menu.logic;

import database.dao.TextsDao;
import ui.emax_info_dialog.view.ShowInfoDialog;
import ui.main_menu.view.IMinimalize;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Author: dmichalski
 * Date: 01.11.13
 */
public class MainMenuPresenter {
    private IMinimalize iMinimalize;

    public MainMenuPresenter(IMinimalize iMinimalize) {
        this.iMinimalize = iMinimalize;
    }

    class StartExamActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            ShowInfoDialog dialog = new ShowInfoDialog(true, iMinimalize);
            dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            dialog.setVisible(true);
        }
    }

    class ShowInfoAboutExam implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            ShowInfoDialog dialog = new ShowInfoDialog(false, iMinimalize);
            dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
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
        UIManager.put("OptionPane.noButtonText", TextsDao.getText("noButtonLbl"));
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
