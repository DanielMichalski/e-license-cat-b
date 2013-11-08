package ui.exam.view;

import database.dao.TextsDao;
import ui.exam.view.balls.BallsPanel;
import ui.exam.view.balls.SpecialistBallsPanel;
import ui.exam.view.balls.StandardBallsPanel;
import util.Const;

import javax.swing.*;
import java.awt.*;


/**
 * Author: Daniel
 * Date: 03.11.13
 */
public class ExamPointsRightPanel extends JPanel {
    private BasicPartPanel standardPartPanel;
    private SpecjalistPartPanel specjalistPartPanel;
    private TimeAndBtnConfirmPanel timeAndBtnConfirmPanel;

    public ExamPointsRightPanel() {
        setUpPanel();
        initializeComponents();
    }

    private void setUpPanel() {
        BoxLayout layout =
                new BoxLayout(ExamPointsRightPanel.this, BoxLayout.Y_AXIS);
        setLayout(layout);
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        setBackground(Const.Colors.examBackgroundColor);
    }

    private void initializeComponents() {
        standardPartPanel = new BasicPartPanel();
        specjalistPartPanel = new SpecjalistPartPanel();
        timeAndBtnConfirmPanel = new TimeAndBtnConfirmPanel();

        add(standardPartPanel);
        add(specjalistPartPanel);
        add(timeAndBtnConfirmPanel);
    }

    public class BasicPartPanel extends JPanel {
        private JLabel basicQuestionNumLbl;
        private StandardBallsPanel standardBallsPanel;

        public BasicPartPanel() {
            setLayout(new BoxLayout(BasicPartPanel.this, BoxLayout.Y_AXIS));
            setBackground(Const.Colors.examBackgroundColor);

            JLabel basicPartLbl = new JLabel(TextsDao.getText("ExamPointRigthPanel.basicPartLbl"));
            basicPartLbl.setAlignmentX(Component.CENTER_ALIGNMENT);
            basicPartLbl.setFont(Const.Fonts.textsFont);

            basicQuestionNumLbl = new JLabel(TextsDao.getText("ExamPointRigthPanel.basicPointsLbl"));
            basicQuestionNumLbl.setAlignmentX(Component.CENTER_ALIGNMENT);
            basicQuestionNumLbl.setFont(Const.Fonts.textsFont);

            standardBallsPanel = new StandardBallsPanel();

            add(basicPartLbl);
            add(basicQuestionNumLbl);
            add(Box.createRigidArea(new Dimension(0, 10)));
            add(standardBallsPanel);
        }

        public void setQuestionNumber(int number) {
            basicQuestionNumLbl.setText("Pytanie numer: " + number + " / 20");
            standardBallsPanel.setQuestionNumber(number);
        }

        public StandardBallsPanel getStandardBallsPanel() {
            return standardBallsPanel;
        }
    }

    public class SpecjalistPartPanel extends JPanel {
        private JLabel specjalistQuestionNumLbl;
        private BallsPanel specialistBallsPanel;

        public SpecjalistPartPanel() {
            setLayout(new BoxLayout(SpecjalistPartPanel.this, BoxLayout.Y_AXIS));
            setBackground(Const.Colors.examBackgroundColor);

            JLabel specjalistPartLbl = new JLabel(TextsDao.getText("ExamPointRigthPanel.specialistPartLbl"));
            specjalistPartLbl.setAlignmentX(Component.CENTER_ALIGNMENT);
            specjalistPartLbl.setFont(Const.Fonts.textsFont);

            specjalistQuestionNumLbl = new JLabel(TextsDao.getText("ExamPointRigthPanel.specialistPointsLbl"));
            specjalistQuestionNumLbl.setAlignmentX(Component.CENTER_ALIGNMENT);
            specjalistQuestionNumLbl.setFont(Const.Fonts.textsFont);

            specialistBallsPanel = new SpecialistBallsPanel();

            add(Box.createRigidArea(new Dimension(0, 10)));
            add(specjalistPartLbl);
            add(specjalistQuestionNumLbl);
            add(Box.createRigidArea(new Dimension(0, 10)));
            add(specialistBallsPanel);
        }

        public void setQuestionNumber(int number) {
            specjalistQuestionNumLbl.setText("Pytanie numer: " + number + " / 12");
            specialistBallsPanel.setQuestionNumber(number);
        }
    }

    public class TimeAndBtnConfirmPanel extends JPanel {
        private JLabel timerLbl;
        private JButton confirmBtn;
        private JLabel howManyPointsLbl;

        public TimeAndBtnConfirmPanel() {
            setLayout(new BoxLayout(TimeAndBtnConfirmPanel.this, BoxLayout.Y_AXIS));
            setBackground(Const.Colors.examBackgroundColor);

            timerLbl = createTimerLbl();
            confirmBtn = createConfirmBtn();
            howManyPointsLbl = createHowManyPointsLbl();

            add(timerLbl);
            add(Box.createRigidArea(new Dimension(0, 10)));
            add(confirmBtn);
            add(Box.createRigidArea(new Dimension(0, 10)));
            add(howManyPointsLbl);
        }

        private JLabel createTimerLbl() {
            JLabel timerLbl = new JLabel();
            timerLbl.setHorizontalAlignment(SwingConstants.CENTER);
            timerLbl.setAlignmentX(Component.CENTER_ALIGNMENT);
            timerLbl.setFont(Const.Fonts.timerFont);
            timerLbl.setPreferredSize(Const.Dimensions.examTimeLblSize);
            timerLbl.setMinimumSize(Const.Dimensions.examTimeLblSize);
            timerLbl.setMaximumSize(Const.Dimensions.examTimeLblSize);
            timerLbl.setForeground(Const.Colors.timerFontColor);

            return timerLbl;
        }

        private JButton createConfirmBtn() {
            JButton button = new JButton(TextsDao.getText("ExamPointRigthPanel.btnConfirmLbl"));
            button.setFont(Const.Fonts.btnConfirmFont);
            button.setPreferredSize(Const.Dimensions.examConfirmBtnSize);
            button.setMinimumSize(Const.Dimensions.examConfirmBtnSize);
            button.setMaximumSize(Const.Dimensions.examConfirmBtnSize);
            button.setAlignmentX(Component.CENTER_ALIGNMENT);
            return button;
        }

        private JLabel createHowManyPointsLbl() {
            JLabel howManyPointsLbl = new JLabel();
            howManyPointsLbl.setHorizontalAlignment(SwingConstants.CENTER);
            howManyPointsLbl.setAlignmentX(Component.CENTER_ALIGNMENT);
            howManyPointsLbl.setPreferredSize(Const.Dimensions.examConfirmBtnSize);
            howManyPointsLbl.setMinimumSize(Const.Dimensions.examConfirmBtnSize);
            howManyPointsLbl.setMaximumSize(Const.Dimensions.examConfirmBtnSize);
            howManyPointsLbl.setFont(Const.Fonts.textsFont);
            howManyPointsLbl.setBorder(BorderFactory.createLineBorder(Const.Colors.howManyPointsBorderColor));
            return howManyPointsLbl;
        }

        public JLabel getTimerLbl() {
            return timerLbl;
        }

        public JButton getConfirmBtn() {
            return confirmBtn;
        }

        public void setHowManyPointsText(String howManyPointsText) {
            howManyPointsLbl.setText(howManyPointsText);
        }
    }

    public BasicPartPanel getStandardPartPanel() {
        return standardPartPanel;
    }

    public SpecjalistPartPanel getSpecjalistPartPanel() {
        return specjalistPartPanel;
    }

    public TimeAndBtnConfirmPanel getTimeAndBtnConfirmPanel() {
        return timeAndBtnConfirmPanel;
    }

}
