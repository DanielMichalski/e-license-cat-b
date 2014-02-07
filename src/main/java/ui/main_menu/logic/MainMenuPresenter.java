package ui.main_menu.logic;

import database.dao.TextsDao;
import ui.application_info.view.ShowInfoDialog;
import ui.exam_info.ExamInfoDialog;
import ui.main_menu.view.MainMenuFrame;
import ui.splash_screen.SplashScreen;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Author: dmichalski
 */
public class MainMenuPresenter {
    private MainMenuFrame mainMenuFrame;

    public MainMenuPresenter(MainMenuFrame mainMenuFrame) {
        this.mainMenuFrame = mainMenuFrame;
    }

    class ExerciseBtnListener extends MouseAdapter {
        @Override
        public void mouseClicked(MouseEvent e) {
            mainMenuFrame.dispose();

            EventQueue.invokeLater(new Runnable() {
                @Override
                public void run() {
                    SplashScreen splashScreen = new SplashScreen(3, null);
                }
            });
        }
    }

    class StartExamActionListener extends MouseAdapter {
        @Override
        public void mouseClicked(MouseEvent e) {
            mainMenuFrame.dispose();

            EventQueue.invokeLater(new Runnable() {
                @Override
                public void run() {
                    ExamInfoDialog dialog = new ExamInfoDialog();
                    dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                    dialog.setVisible(true);
                }
            });

        }
    }

    class ShowInfoAboutExamListener extends MouseAdapter {
        @Override
        public void mouseClicked(MouseEvent e) {
            EventQueue.invokeLater(new Runnable() {
                @Override
                public void run() {
                    ShowInfoDialog dialog = new ShowInfoDialog();
                    dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                    dialog.setVisible(true);
                }
            });
        }
    }

    class CloseBtnListener extends MouseAdapter {
        @Override
        public void mouseClicked(MouseEvent e) {
            System.exit(0);
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

    public void setExerciseBtn(JLabel exerciseBtn) {
        exerciseBtn.addMouseListener(new ExerciseBtnListener());
    }

    public void setEgxamBtn(JLabel examBtn) {
        examBtn.addMouseListener(new StartExamActionListener());
    }

    public void setAboutExamBtn(JLabel aboutExamBtn) {
        aboutExamBtn.addMouseListener(new ShowInfoAboutExamListener());
    }

    public void setCloseBtn(JLabel closeBtn) {
        closeBtn.addMouseListener(new CloseBtnListener());
    }
}
