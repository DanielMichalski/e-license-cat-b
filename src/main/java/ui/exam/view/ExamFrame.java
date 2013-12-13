package ui.exam.view;

import database.dao.TextsDao;
import ui.exam.logic.ExamPresenter;
import ui.exam.view.interfaces.WindowCloser;
import util.ApplicationUtils;

import javax.swing.*;
import java.awt.*;

/**
 * Author: Daniel
 * Date: 03.11.13
 */
public class ExamFrame extends JDialog implements WindowCloser {

    public static final int WIDTH = 953;
    public static final int HEIGHT = 670;

    private ExamQuestionsLeftPanel examQuestionsPanel;

    public ExamFrame(JDialog dialog) {
        setUpFrame();
        initializeComponents();
        setVisible(true);

        if (dialog != null) {
            dialog.dispose();
        }
    }

    private void setUpFrame() {
        ApplicationUtils.setApplicationIcon(this);
        setSize(WIDTH, HEIGHT);
        setLocationRelativeTo(null);
        setTitle(TextsDao.getText("view.ExamFrame.title"));
        setResizable(false);
    }

    private void initializeComponents() {
        ExamPresenter presenter = new ExamPresenter(this);


        examQuestionsPanel = new ExamQuestionsLeftPanel();
        ExamPointsRightPanel examResultPanel = new ExamPointsRightPanel();

        add(examQuestionsPanel, BorderLayout.CENTER);
        add(examResultPanel, BorderLayout.EAST);

        addWindowListener(presenter.getWindowListener(this));
        examResultPanel.getCloseBtnPanel().getCloseBtn().addActionListener(presenter.getCloseBtnListener(this));

        presenter.setExamQuestionsLeftPanel(examQuestionsPanel);
        presenter.setBasicPartPanel(examResultPanel.getBasicPartPanel());
        presenter.setSpecjalistPartPanel(examResultPanel.getSpecjalistPartPanel());
        presenter.setTimeAndBtnConfirmPanel(examResultPanel.getTimeAndBtnConfirmPanel());

        setVisible(true);

        presenter.nextQuestion();
    }

    @Override
    public void close() {
        dispose();
    }
}
