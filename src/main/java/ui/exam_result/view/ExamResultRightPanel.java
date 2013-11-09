package ui.exam_result.view;

import database.dao.TextsDao;
import model.SpecialistQuestion;
import model.StandardQuestion;
import ui.exam_result.controller.ExamResultPresenter;
import ui.exam_result.view.balls.ResultSpecialistBallsPanel;
import ui.exam_result.view.balls.ResultStandardBallsPanel;
import util.Const;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * Author: Daniel
 * Date: 08.11.13
 */
public class ExamResultRightPanel extends JPanel {
    private ExamResultPresenter presenter;

    private List<StandardQuestion> standardQuestions;
    private List<SpecialistQuestion> specialistQuestions;

    private StandardPartPanel standardPartPanel;
    private SpecjalistPartPanel specjalistPartPanel;
    private HowManyPointsPanel timeAndBtnConfirmPanel;

    public ExamResultRightPanel(ExamResultPresenter presenter,
                                List<StandardQuestion> standardQuestions,
                                List<SpecialistQuestion> specialistQuestions) {

        this.presenter = presenter;
        this.standardQuestions = standardQuestions;
        this.specialistQuestions = specialistQuestions;

        setUpPanel();
        initializeComponents();
    }

    private void setUpPanel() {
        BoxLayout layout =
                new BoxLayout(ExamResultRightPanel.this, BoxLayout.Y_AXIS);
        setLayout(layout);
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        setBackground(Const.Colors.EXAM_BACKGROUND_COLOR);
    }

    private void initializeComponents() {
        standardPartPanel = new StandardPartPanel();
        specjalistPartPanel = new SpecjalistPartPanel();
        timeAndBtnConfirmPanel = new HowManyPointsPanel();

        add(standardPartPanel);
        add(specjalistPartPanel);
        add(timeAndBtnConfirmPanel);
    }

    public class StandardPartPanel extends JPanel {
        private JLabel basicQuestionNumLbl;
        private ResultStandardBallsPanel standardBallsPanel;

        public StandardPartPanel() {
            setLayout(new BoxLayout(StandardPartPanel.this, BoxLayout.Y_AXIS));
            setBackground(Const.Colors.EXAM_BACKGROUND_COLOR);

            JLabel basicPartLbl = new JLabel(TextsDao.getText("ExamPointRigthPanel.basicPartLbl"));
            basicPartLbl.setAlignmentX(Component.CENTER_ALIGNMENT);
            basicPartLbl.setFont(Const.Fonts.TEXTS_FONT);

            basicQuestionNumLbl = new JLabel(TextsDao.getText("ExamResultPointRigthPanel.basicPointsLb"));
            basicQuestionNumLbl.setAlignmentX(Component.CENTER_ALIGNMENT);
            basicQuestionNumLbl.setFont(Const.Fonts.TEXTS_FONT);

            standardBallsPanel = new ResultStandardBallsPanel(presenter, standardQuestions);

            add(basicPartLbl);
            add(basicQuestionNumLbl);
            add(Box.createRigidArea(new Dimension(0, 10)));
            add(standardBallsPanel);
        }

        private void setNumberOfQuestion(int numOfQuestion) {
            basicQuestionNumLbl.setText("Pytanie numer: " + numOfQuestion + " / 20");
        }
    }

    public class SpecjalistPartPanel extends JPanel {
        private JLabel specjalistQuestionNumLbl;
        private ResultSpecialistBallsPanel specialistBallsPanel;

        public SpecjalistPartPanel() {
            setLayout(new BoxLayout(SpecjalistPartPanel.this, BoxLayout.Y_AXIS));
            setBackground(Const.Colors.EXAM_BACKGROUND_COLOR);

            JLabel specjalistPartLbl = new JLabel(TextsDao.getText("ExamPointRigthPanel.specialistPartLbl"));
            specjalistPartLbl.setAlignmentX(Component.CENTER_ALIGNMENT);
            specjalistPartLbl.setFont(Const.Fonts.TEXTS_FONT);

            specjalistQuestionNumLbl = new JLabel(TextsDao.getText("ExamResultPointRigthPanel.specialistPointsLb"));
            specjalistQuestionNumLbl.setAlignmentX(Component.CENTER_ALIGNMENT);
            specjalistQuestionNumLbl.setFont(Const.Fonts.TEXTS_FONT);

            specialistBallsPanel = new ResultSpecialistBallsPanel(presenter, specialistQuestions);

            add(Box.createRigidArea(new Dimension(0, 10)));
            add(specjalistPartLbl);
            add(specjalistQuestionNumLbl);
            add(Box.createRigidArea(new Dimension(0, 10)));
            add(specialistBallsPanel);
        }

        private void setNumberOfQuestion(int numberOfQuestion) {
            specjalistQuestionNumLbl.setText("Pytanie numer: " + numberOfQuestion + " / 12");
        }
    }

    public class HowManyPointsPanel extends JPanel {
        private JLabel howManyPointsLbl;

        public HowManyPointsPanel() {
            setLayout(new BoxLayout(HowManyPointsPanel.this, BoxLayout.Y_AXIS));
            setBackground(Const.Colors.EXAM_BACKGROUND_COLOR);


            howManyPointsLbl = createHowManyPointsLbl();

            add(Box.createRigidArea(new Dimension(0, 10)));
            add(howManyPointsLbl);
        }

        private JLabel createHowManyPointsLbl() {
            JLabel howManyPointsLbl = new JLabel();
            howManyPointsLbl.setHorizontalAlignment(SwingConstants.CENTER);
            howManyPointsLbl.setAlignmentX(Component.CENTER_ALIGNMENT);
            howManyPointsLbl.setPreferredSize(Const.Dimensions.EXAM_CONFIRM_BTN_SIZE);
            howManyPointsLbl.setMinimumSize(Const.Dimensions.EXAM_CONFIRM_BTN_SIZE);
            howManyPointsLbl.setMaximumSize(Const.Dimensions.EXAM_CONFIRM_BTN_SIZE);
            howManyPointsLbl.setFont(Const.Fonts.TEXTS_FONT);
            howManyPointsLbl.setBorder(BorderFactory.createLineBorder(Const.Colors.HOW_MANY_POINTS_BORDER_COLOR));
            return howManyPointsLbl;
        }

        private void setHowManyPoints(int points) {
            howManyPointsLbl.setText(points + " pkt");
        }
    }

    public void setStandardQuestionNumber(int number) {
        standardPartPanel.setNumberOfQuestion(number);
    }

    public void setSpecialistQuestionNumber(int number) {
        specjalistPartPanel.setNumberOfQuestion(number);
    }

    public void setHowManyQuestionPoints(int howManyPoints) {
        timeAndBtnConfirmPanel.setHowManyPoints(howManyPoints);
    }
}
