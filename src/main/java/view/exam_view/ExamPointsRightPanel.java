package view.exam_view;

import util.Const;

import javax.swing.*;
import java.awt.*;


/**
 * Author: Daniel
 * Date: 03.11.13
 */
public class ExamPointsRightPanel extends JPanel {
    private BasicPartPanel basicPartPanel;
    private SpecjalistPartPanel specjalistPartPanel;
    private TimeAndBtnConfirmPanel timeAndBtnConfirmPanel;

    private JButton confirmBtn;
    private JLabel timerLbl;
    private JLabel howManyPoints;

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
        basicPartPanel = new BasicPartPanel();
        specjalistPartPanel = new SpecjalistPartPanel();
        timeAndBtnConfirmPanel = new TimeAndBtnConfirmPanel();

        add(basicPartPanel);
        add(specjalistPartPanel);
        add(timeAndBtnConfirmPanel);
    }

    public class BasicPartPanel extends JPanel {
        private JLabel basicQuestionNumLbl;
        private BallsPanel standardBallsPanel;

        public BasicPartPanel() {
            setLayout(new BoxLayout(BasicPartPanel.this, BoxLayout.Y_AXIS));
            setBackground(Const.Colors.examBackgroundColor);

            JLabel basicPartLbl = new JLabel("Część podstawowa");
            basicPartLbl.setAlignmentX(Component.CENTER_ALIGNMENT);
            basicPartLbl.setFont(Const.Fonts.textsFont);

            basicQuestionNumLbl = new JLabel("Pytanie numer: 1 / 20");
            basicQuestionNumLbl.setAlignmentX(Component.CENTER_ALIGNMENT);
            basicQuestionNumLbl.setFont(Const.Fonts.textsFont);

            standardBallsPanel = new BallsPanel(20, BallsPanel.BallPanelType.basic);

            add(basicPartLbl);
            add(basicQuestionNumLbl);
            add(Box.createRigidArea(new Dimension(0, 10)));
            add(standardBallsPanel);
        }

        public void setQuestionNumber(int number) {
            basicQuestionNumLbl.setText("Pytanie numer: " + number + " / 20");
            standardBallsPanel.setQuestionNumber(number);
        }
    }

    public class SpecjalistPartPanel extends JPanel {
        private JLabel specjalistQuestionNumLbl;
        private BallsPanel specialistBallsPanel;

        public SpecjalistPartPanel() {
            setLayout(new BoxLayout(SpecjalistPartPanel.this, BoxLayout.Y_AXIS));
            setBackground(Const.Colors.examBackgroundColor);

            JLabel specjalistPartLbl = new JLabel("Część specjalistyczna");
            specjalistPartLbl.setAlignmentX(Component.CENTER_ALIGNMENT);
            specjalistPartLbl.setFont(Const.Fonts.textsFont);

            specjalistQuestionNumLbl = new JLabel("Pytanie numer: 0 / 12");
            specjalistQuestionNumLbl.setAlignmentX(Component.CENTER_ALIGNMENT);
            specjalistQuestionNumLbl.setFont(Const.Fonts.textsFont);

            specialistBallsPanel = new BallsPanel(12, BallsPanel.BallPanelType.specialist);

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
        public TimeAndBtnConfirmPanel() {
            setLayout(new BoxLayout(TimeAndBtnConfirmPanel.this, BoxLayout.Y_AXIS));
            setBackground(Const.Colors.examBackgroundColor);

            JLabel timerLbl = getTimerLbl();
            confirmBtn = getAcceptBtn();
            JPanel howManyPointsPanel = getHowManyPointsPanel();

            add(timerLbl);
            add(Box.createRigidArea(new Dimension(0, 10)));
            add(confirmBtn);
            add(Box.createRigidArea(new Dimension(0, 10)));
            add(howManyPointsPanel);
        }

        private JLabel getTimerLbl() {
            timerLbl = new JLabel("40");
            timerLbl.setAlignmentX(Component.CENTER_ALIGNMENT);
            timerLbl.setFont(Const.Fonts.timerFont);
            timerLbl.setForeground(Const.Colors.timerFontColor);

            return timerLbl;
        }

        private JButton getAcceptBtn() {
            JButton button = new JButton("Zatwierdź");
            button.setFont(Const.Fonts.btnConfirmFont);
            button.setPreferredSize(Const.Dimensions.examConfirmBtnSize);
            button.setMinimumSize(Const.Dimensions.examConfirmBtnSize);
            button.setMaximumSize(Const.Dimensions.examConfirmBtnSize);
            button.setAlignmentX(Component.CENTER_ALIGNMENT);
            return button;
        }

        private JPanel getHowManyPointsPanel() {
            JPanel howManyPointsPanel = new JPanel();
            howManyPointsPanel.setBackground(Const.Colors.examBackgroundColor);
            howManyPoints = new JLabel();
            howManyPoints.setAlignmentX(Component.CENTER_ALIGNMENT);
            howManyPoints.setAlignmentY(Component.BOTTOM_ALIGNMENT);
            howManyPoints.setFont(Const.Fonts.textsFont);

            howManyPointsPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            howManyPointsPanel.add(howManyPoints);
            return howManyPointsPanel;
        }

    }

    public JLabel getHowManyPoints() {
        return howManyPoints;
    }

    public JLabel getTimerLbl() {
        return timerLbl;
    }

    public BasicPartPanel getBasicPartPanel() {
        return basicPartPanel;
    }

    public SpecjalistPartPanel getSpecjalistPartPanel() {
        return specjalistPartPanel;
    }

    public TimeAndBtnConfirmPanel getTimeAndBtnConfirmPanel() {
        return timeAndBtnConfirmPanel;
    }

    public JButton getConfirmBtn() {
        return confirmBtn;
    }
}
