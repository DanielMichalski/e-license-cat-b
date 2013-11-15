package ui.exam_result.view;

import database.dao.TextsDao;
import model.SpecialistQuestion;
import model.StandardQuestion;
import ui.exam_result.controller.ExamResultPresenter;
import ui.exam_result.view.interfaces.WindowAutoSizer;
import util.ApplicationUtils;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * Author: Daniel
 * Date: 08.11.13
 */
public class ExamResultFrame extends JDialog implements WindowAutoSizer {
    private List<StandardQuestion> standardQuestions;
    private List<SpecialistQuestion> specialistQuestions;

    public ExamResultFrame(List<StandardQuestion> standardQuestions,
                           List<SpecialistQuestion> specialistQuestions) {

        this.standardQuestions = standardQuestions;
        this.specialistQuestions = specialistQuestions;

        setUpFrame();
        initializeComponents();
        pack();
        setLocationRelativeTo(null);
    }

    private void setUpFrame() {
        setTitle(TextsDao.getText("view.ExamResultFrame.title"));
        ApplicationUtils.setApplicationIcon(this);
        setModal(true);
        setResizable(false);
    }

    private void initializeComponents() {
        ExamResultPresenter presenter = new ExamResultPresenter(standardQuestions, specialistQuestions);

        ExamResultLeftPanel leftPanel = new ExamResultLeftPanel(this);
        ExamResultRightPanel rightPanel = new ExamResultRightPanel(presenter, standardQuestions, specialistQuestions);

        add(leftPanel, BorderLayout.CENTER);
        add(rightPanel, BorderLayout.EAST);

        presenter.setLeftPanel(leftPanel);
        presenter.setRightPanel(rightPanel);

        presenter.setUpPanels();
    }

    @Override
    public void autoSize() {
        pack();
    }
}
