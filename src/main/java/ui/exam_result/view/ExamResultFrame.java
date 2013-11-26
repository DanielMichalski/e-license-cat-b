package ui.exam_result.view;

import database.dao.TextsDao;
import model.SpecialistQuestion;
import model.StandardQuestion;
import ui.exam_result.logic.ExamResultPresenter;
import util.ApplicationUtils;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * Author: Daniel
 * Date: 08.11.13
 */
public class ExamResultFrame extends JDialog {

    private List<StandardQuestion> standardQuestions;
    private List<SpecialistQuestion> specialistQuestions;

    public static final int WIDTH = 953;
    public static final int HEIGHT = 670;

    public ExamResultFrame(List<StandardQuestion> standardQuestions,
                           List<SpecialistQuestion> specialistQuestions) {

        this.standardQuestions = standardQuestions;
        this.specialistQuestions = specialistQuestions;

        setUpFrame();
        initializeComponents();
        setLocationRelativeTo(null);
    }

    private void setUpFrame() {
        setTitle(TextsDao.getText("view.ExamResultFrame.title"));
        ApplicationUtils.setApplicationIcon(this);
        setSize(WIDTH, HEIGHT);
        setModal(true);
        setResizable(false);
    }

    private void initializeComponents() {
        ExamResultPresenter presenter = new ExamResultPresenter(standardQuestions, specialistQuestions);

        ExamResultLeftPanel leftPanel = new ExamResultLeftPanel();
        ExamResultRightPanel rightPanel = new ExamResultRightPanel(presenter, standardQuestions, specialistQuestions);

        add(leftPanel, BorderLayout.CENTER);
        add(rightPanel, BorderLayout.EAST);

        presenter.setLeftPanel(leftPanel);
        presenter.setRightPanel(rightPanel);

        presenter.setCloseBtn(rightPanel.getCloseBtn(), this);
        presenter.setPrintBtn(rightPanel.getPrintBtn());

        presenter.setUpPanels();
    }
}
