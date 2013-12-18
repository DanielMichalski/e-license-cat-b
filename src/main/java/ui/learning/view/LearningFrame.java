package ui.learning.view;

import database.dao.TextsDao;
import model.SpecialistQuestion;
import model.StandardQuestion;
import ui.learning.logic.LearningPresenter;
import util.ApplicationUtils;

import javax.swing.*;
import java.util.List;

/**
 * Author: Daniel
 * Date: 08.11.13
 */
public class LearningFrame extends JFrame {

    private List<StandardQuestion> standardQuestions;
    private List<SpecialistQuestion> specialistQuestions;

    public static final int WIDTH = 940;
    public static final int HEIGHT = 670;

    public LearningFrame(List<StandardQuestion> standardQuestions,
                         List<SpecialistQuestion> specialistQuestions) {

        this.standardQuestions = standardQuestions;
        this.specialistQuestions = specialistQuestions;

        setUpFrame();
        initializeComponents();

    }

    private void setUpFrame() {
        setTitle(TextsDao.getText("view.LearningFrame.title"));
        ApplicationUtils.setApplicationIcon(this);
        setSize(WIDTH, HEIGHT);
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
    }

    private void initializeComponents() {
        LearningPresenter presenter =
                new LearningPresenter(standardQuestions, specialistQuestions);

        LearningLeftPanel learningPanel = new LearningLeftPanel();
        add(learningPanel);
    }
}
