package ui.exam_result.view;

import database.dao.TextsDao;
import model.SpecialistQuestion;
import model.StandardQuestion;
import ui.exam_result.controller.ExamResultPresenter;
import util.Utils;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * Author: Daniel
 * Date: 08.11.13
 */
public class ExamResultFrame extends JFrame {
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
        Utils.setApplicationIcon(this);
        setTitle(TextsDao.getText("view.ExamResultFrame.title"));
    }

    private void initializeComponents() {
        ExamResultPresenter presenter = new ExamResultPresenter();

        ExamResultLeftPanel leftPanel = new ExamResultLeftPanel();
        JScrollPane scrollPane = new JScrollPane(leftPanel);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());

        ExamResultRightPanel rightPanel = new ExamResultRightPanel();

        add(scrollPane, BorderLayout.CENTER);
        add(rightPanel, BorderLayout.EAST);

        addWindowListener(presenter.getWindowListener(this));

        presenter.setLeftPanel(leftPanel);
        presenter.setRightPanel(rightPanel);
    }

}
