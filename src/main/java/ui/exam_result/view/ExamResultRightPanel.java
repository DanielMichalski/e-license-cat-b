package ui.exam_result.view;

import database.dao.TextsDao;
import model.SpecialistQuestion;
import model.StandardQuestion;
import ui.exam_result.logic.ExamResultPresenter;
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

    private CloseBtnPanel closeBtnPanel;
    private StandardPartPanel standardPartPanel;
    private SpecjalistPartPanel specjalistPartPanel;
    private HowManyPointsPanel howManyPointsPanel;

    private JButton printBtn;
    private JButton closeBtn;

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
        setBorder(BorderFactory.createEmptyBorder(5, 10, 10, 10));
        setBackground(Const.Colors.EXAM_BACKGROUND_COLOR);
    }

    private void initializeComponents() {
        closeBtnPanel = new CloseBtnPanel();
        standardPartPanel = new StandardPartPanel();
        specjalistPartPanel = new SpecjalistPartPanel();
        howManyPointsPanel = new HowManyPointsPanel();

        add(closeBtnPanel);
        add(standardPartPanel);
        add(specjalistPartPanel);
        add(howManyPointsPanel);
    }

    public JButton getCloseBtn() {
        return closeBtn;
    }

    public class CloseBtnPanel extends JPanel {
        public CloseBtnPanel() {
            setBackground(Const.Colors.EXAM_BACKGROUND_COLOR);

            add(createCloseBtn());
        }

        private JButton createCloseBtn() {
            closeBtn = new JButton(TextsDao.getText("ExamPointRigthPanel.btnCloseLbl"));
            closeBtn.setFont(Const.Fonts.BTN_CLOSE_FONT);
            closeBtn.setPreferredSize(Const.Dimensions.EXAM_CLOSE_BTN_SIZE);
            closeBtn.setMinimumSize(Const.Dimensions.EXAM_CLOSE_BTN_SIZE);
            closeBtn.setMaximumSize(Const.Dimensions.EXAM_CLOSE_BTN_SIZE);
            closeBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
            closeBtn.setFocusable(false);
            return closeBtn;
        }

        public JButton getCloseBtn() {
            return closeBtn;
        }
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
        private JLabel examResultLbl;
        private JLabel userPointsLbl;
        private JLabel howManyPointsForQuestionLbl;

        public HowManyPointsPanel() {
            setLayout(new BoxLayout(HowManyPointsPanel.this, BoxLayout.Y_AXIS));
            setBackground(Const.Colors.EXAM_BACKGROUND_COLOR);

            examResultLbl = createIsPassedOrNoLbl();
            userPointsLbl = createUserPointsLbl();
            howManyPointsForQuestionLbl = createHowManyPointsLbl();
            printBtn = createPrintBtn();

            Component rigidArea = Box.createRigidArea(new Dimension(0, 10));

            add(rigidArea);
            add(examResultLbl);
            add(rigidArea);
            add(userPointsLbl);
            add(rigidArea);
            add(howManyPointsForQuestionLbl);
            add(rigidArea);
            add(printBtn);
        }

        private JButton createPrintBtn() {
            printBtn = new JButton(TextsDao.getText("ExamPointRigthPanel.btnPrintLbl"));
            printBtn.setFont(Const.Fonts.BTN_PRINT_FONT);
            printBtn.setPreferredSize(Const.Dimensions.EXAM_PRINT_BTN_SIZE);
            printBtn.setMinimumSize(Const.Dimensions.EXAM_PRINT_BTN_SIZE);
            printBtn.setMaximumSize(Const.Dimensions.EXAM_PRINT_BTN_SIZE);
            printBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
            printBtn.setFocusable(false);
            return printBtn;
        }

        private JLabel createIsPassedOrNoLbl() {
            JLabel isPaassedOrNoLbl = new JLabel("");
            isPaassedOrNoLbl.setHorizontalAlignment(SwingConstants.CENTER);
            isPaassedOrNoLbl.setAlignmentX(Component.CENTER_ALIGNMENT);
            isPaassedOrNoLbl.setFont(Const.Fonts.IS_PASSED_OR_NO_LBL_FONT);
            isPaassedOrNoLbl.setPreferredSize(Const.Dimensions.IS_PASSED_OR_NO_LBL);
            isPaassedOrNoLbl.setMinimumSize(Const.Dimensions.IS_PASSED_OR_NO_LBL);
            isPaassedOrNoLbl.setMaximumSize(Const.Dimensions.IS_PASSED_OR_NO_LBL);

            return isPaassedOrNoLbl;
        }

        private JLabel createUserPointsLbl() {
            JLabel userPointsLbl = new JLabel("");
            userPointsLbl.setHorizontalAlignment(SwingConstants.CENTER);
            userPointsLbl.setAlignmentX(Component.CENTER_ALIGNMENT);
            userPointsLbl.setFont(Const.Fonts.RESULT_POINT_FONT);
            userPointsLbl.setPreferredSize(Const.Dimensions.RESULT_POINT);
            userPointsLbl.setMinimumSize(Const.Dimensions.RESULT_POINT);
            userPointsLbl.setMaximumSize(Const.Dimensions.RESULT_POINT);

            return userPointsLbl;
        }

        private JLabel createHowManyPointsLbl() {
            JLabel howManyPointsLbl = new JLabel();
            howManyPointsLbl.setHorizontalAlignment(SwingConstants.CENTER);
            howManyPointsLbl.setAlignmentX(Component.CENTER_ALIGNMENT);
            howManyPointsLbl.setPreferredSize(Const.Dimensions.HOW_MANY_POINTS_LBL);
            howManyPointsLbl.setMinimumSize(Const.Dimensions.HOW_MANY_POINTS_LBL);
            howManyPointsLbl.setMaximumSize(Const.Dimensions.HOW_MANY_POINTS_LBL);
            howManyPointsLbl.setFont(Const.Fonts.TEXTS_FONT);
            howManyPointsLbl.setBorder(BorderFactory.createLineBorder(Const.Colors.HOW_MANY_POINTS_BORDER_COLOR));
            return howManyPointsLbl;
        }

        private void setExamResult(boolean isPassed, int userPoints, int allPoints) {
            String howManyUserPointsText = String.format("%d / %d pkt", userPoints, allPoints);
            userPointsLbl.setText(howManyUserPointsText);

            if (isPassed) {
                String examPassedText = TextsDao.getText("ExamResultPointRigthPanel.examPassedText");

                examResultLbl.setForeground(Const.Colors.POSITIVE_RESULT_COLOR);
                examResultLbl.setText(examPassedText);
                userPointsLbl.setForeground(Const.Colors.POSITIVE_RESULT_COLOR);
            } else {
                String examFailedText = TextsDao.getText("ExamResultPointRigthPanel.examFailedText");

                examResultLbl.setForeground(Const.Colors.NEGATIVE_RESULT_COLOR);
                examResultLbl.setText(examFailedText);
                userPointsLbl.setForeground(Const.Colors.NEGATIVE_RESULT_COLOR);
            }
        }

        private void setHowManyPoints(int points) {
            howManyPointsForQuestionLbl.setText(points + " pkt");
        }
    }

    public void setStandardQuestionNumber(int number) {
        standardPartPanel.setNumberOfQuestion(number);
    }

    public void setSpecialistQuestionNumber(int number) {
        specjalistPartPanel.setNumberOfQuestion(number);
    }

    public void setHowManyQuestionPoints(int howManyPoints) {
        howManyPointsPanel.setHowManyPoints(howManyPoints);
    }

    public void setExamResult(boolean isPassed, int userPoints, int allPoints) {
        howManyPointsPanel.setExamResult(isPassed, userPoints, allPoints);
    }

    public CloseBtnPanel getCloseBtnPanel() {
        return closeBtnPanel;
    }

    public JButton getPrintBtn() {
        return printBtn;
    }
}
