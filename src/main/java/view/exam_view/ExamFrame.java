package view.exam_view;

import controller.ExamPresenter;
import database.TextsDao;

import javax.swing.*;
import java.awt.*;

/**
 * Author: Daniel
 * Date: 03.11.13
 */
public class ExamFrame extends JDialog {
    public ExamFrame() {
        setUpFrame();
        initializeComponents();
        pack();
        setLocationRelativeTo(null);
    }

    private void setUpFrame() {
        setTitle(TextsDao.get("view.ExamView.title"));
        setModal(true);
    }

    private void initializeComponents() {
        ExamPresenter presenter = new ExamPresenter();

        ExamQuestionsLeftPanel examQuestionsPanel = new ExamQuestionsLeftPanel();
        ExamPointsRightPanel examResultPanel = new ExamPointsRightPanel();

        add(examQuestionsPanel, BorderLayout.CENTER);
        add(examResultPanel, BorderLayout.EAST);

        presenter.setYesBtn(examQuestionsPanel.getYesBtn());
        presenter.setNoBtn(examQuestionsPanel.getNoBtn());
    }

}
