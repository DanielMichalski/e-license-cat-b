package ui.exam_result.controller;

import database.dao.TextsDao;
import model.SpecialistQuestion;
import model.StandardQuestion;
import ui.exam_result.view.ExamResultFrame;
import ui.exam_result.view.ExamResultLeftPanel;
import ui.exam_result.view.ExamResultRightPanel;
import ui.main_menu.view.MainMenuFrame;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.List;

/**
 * Author: Daniel
 * Date: 08.11.13
 */
public class ExamResultPresenter {
    private List<StandardQuestion> standardQuestions;
    private List<SpecialistQuestion> specialistQuestions;

    private ExamResultLeftPanel leftPanel;
    private ExamResultRightPanel rightPanel;

    public WindowListener getWindowListener(final ExamResultFrame examResultFrame) {
        return new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                UIManager.put("OptionPane.yesButtonText", TextsDao.getText("yesButtonLbl"));
                UIManager.put("noButtonLbl", TextsDao.getText("noButtonLbl"));
                int answer = JOptionPane.showConfirmDialog(
                        null,
                        TextsDao.getText("view.examFramConfirmDialog.message"),
                        TextsDao.getText("view.confirmDialog.title"),
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.INFORMATION_MESSAGE);

                if (answer == JOptionPane.YES_OPTION) {
                    examResultFrame.dispose();
                    MainMenuFrame mainMenuFrame = new MainMenuFrame();
                    mainMenuFrame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
                    mainMenuFrame.setVisible(true);
                }
            }
        };
    }

    public void setLeftPanel(ExamResultLeftPanel leftPanel) {
        this.leftPanel = leftPanel;
    }

    public void setRightPanel(ExamResultRightPanel rightPanel) {
        this.rightPanel = rightPanel;
    }

    public void setStandardQuestions(List<StandardQuestion> standardQuestions) {
        this.standardQuestions = standardQuestions;
    }

    public void setSpecialistQuestions(List<SpecialistQuestion> specialistQuestions) {
        this.specialistQuestions = specialistQuestions;
    }
}
