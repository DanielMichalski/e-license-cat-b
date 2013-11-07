package view.exam_view;

import controller.ExamPresenter;
import database.TextsDao;
import util.Utils;

import javax.swing.*;
import java.awt.*;

/**
 * Author: Daniel
 * Date: 03.11.13
 */
public class ExamFrame extends JDialog implements WindowAutoSizer {
    public ExamFrame() {
        setUpFrame();
        initializeComponents();
        pack();
        setLocationRelativeTo(null);
    }

    private void setUpFrame() {
        Utils.setSystemLookAndFeel();
        Utils.setApplicationIcon(this);
        setTitle(TextsDao.getText("view.ExamFrame.title"));
        setModal(true);
        //setResizable(false);
    }

    private void initializeComponents() {
        ExamPresenter presenter = new ExamPresenter();

        ExamQuestionsLeftPanel examQuestionsPanel = new ExamQuestionsLeftPanel(this);
        ExamPointsRightPanel examResultPanel = new ExamPointsRightPanel();

        add(examQuestionsPanel, BorderLayout.CENTER);
        add(examResultPanel, BorderLayout.EAST);

        presenter.setYesBtn(examQuestionsPanel.getYesBtn());
        presenter.setNoBtn(examQuestionsPanel.getNoBtn());
        presenter.setTimerLbl(examResultPanel.getTimerLbl());
        presenter.setConfirmBtn(examResultPanel.getConfirmBtn());
        presenter.setHowManyPoints(examResultPanel.getHowManyPoints());

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
}
