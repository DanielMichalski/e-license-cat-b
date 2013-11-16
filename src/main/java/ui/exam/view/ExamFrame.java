package ui.exam.view;

import database.dao.TextsDao;
import ui.exam.logic.ExamPresenter;
import ui.exam.view.interfaces.WindowAutoSizer;
import ui.exam.view.interfaces.WindowCloser;
import util.ApplicationUtils;

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

        System.out.println(getSize());
    }

    private void setUpFrame() {
        ApplicationUtils.setNimbusLookAndFeel();
        ApplicationUtils.setApplicationIcon(this);
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
        examResultPanel.getCloseBtnPanel().getCloseBtn().addActionListener(presenter.getCloseBtnListener(this));

        presenter.setExamQuestionsLeftPanel(examQuestionsPanel);
        presenter.setBasicPartPanel(examResultPanel.getBasicPartPanel());
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
