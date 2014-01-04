package ui.learning.view;

import database.dao.TextsDao;
import model.Module;
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

    private Module module;
    private List<StandardQuestion> standardQuestions;
    private List<SpecialistQuestion> specialistQuestions;

    public static final int WIDTH = 1000;
    public static final int HEIGHT = 670;

    public LearningFrame(Module module,
                         List<StandardQuestion> standardQuestions,
                         List<SpecialistQuestion> specialistQuestions) {

        this.module = module;
        this.standardQuestions = standardQuestions;
        this.specialistQuestions = specialistQuestions;

        setUpFrame();
        initializeComponents();

    }

    private void setUpFrame() {
        setLayout(null);
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

        LearningLeftPanel leftPanel = new LearningLeftPanel();
        LearningRightPanel rightPanel = new LearningRightPanel();
        rightPanel.setModuleName(module.toString());

        leftPanel.setBounds(0, 0, 735, 640);
        rightPanel.setBounds(735, 0, 260, 640);

        add(leftPanel);
        add(rightPanel);

        addWindowListener(presenter.getWindowListener(this));
        presenter.setLearningLeftPanel(leftPanel);
        presenter.setCategoryChooseBtn(rightPanel.getCategoryChooseBtn(), this);
        presenter.setQuestionArea(rightPanel.getQuestionArea());
        presenter.setPlayMovieBtn(rightPanel.getPlayMovieBtn());
        presenter.setPreviousBtn(rightPanel.getPreviousBtn());
        presenter.setNextBtn(rightPanel.getNextBtn());
        presenter.setRandomQuestionBtn(rightPanel.getRandomQuestion());
        presenter.setCheckAnswerBtn(rightPanel.getCheckAnswerBtn());
        presenter.setHowManyPointsForQuestionLbl(rightPanel.getHowManyPointsForQuestionLbl());

        presenter.setUpPanels();
    }
}
