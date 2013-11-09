package ui.exam.view;

import database.dao.TextsDao;
import ui.exam.controller.ExamPresenter;
import ui.exam.view.interfaces.WindowAutoSizer;
import ui.exam.view.interfaces.WindowCloser;
import util.Utils;

import javax.swing.*;
import java.awt.*;

/**
 * Author: Daniel
 * Date: 03.11.13
 */
public class ExamFrame extends JDialog implements WindowAutoSizer, WindowCloser {
    public ExamFrame() {
        setUpFrame();
        initializeComponents();
        pack();
        setLocationRelativeTo(null);
    }

    private void setUpFrame() {
        Utils.setNimbusLookAndFeel();
        Utils.setApplicationIcon(this);
        setTitle(TextsDao.getText("view.ExamFrame.title"));
        setModal(true);
        setResizable(false);
    }

    private void initializeComponents() {
        ExamPresenter presenter = new ExamPresenter(this);

        ExamQuestionsLeftPanel examQuestionsPanel = new ExamQuestionsLeftPanel(this);
        ExamPointsRightPanel examResultPanel = new ExamPointsRightPanel();

        add(examQuestionsPanel, BorderLayout.CENTER);
        add(examResultPanel, BorderLayout.EAST);

        addWindowListener(presenter.getWindowListener(this));

        presenter.setExamQuestionsLeftPanel(examQuestionsPanel);
        presenter.setBasicPartPanel(examResultPanel.getStandardPartPanel());
        presenter.setSpecjalistPartPanel(examResultPanel.getSpecjalistPartPanel());
        presenter.setTimeAndBtnConfirmPanel(examResultPanel.getTimeAndBtnConfirmPanel());

        presenter.nextQuestion();
    }

    @Override
    public void autoSize() {
        pack();
    }

    @Override
    public void close() {
        dispose();
    }
}
