package view.exam_view;

import util.Const;
import util.ImagesUtil;

import javax.swing.*;
import java.awt.*;

/**
 * Author: Daniel
 * Date: 03.11.13
 */
public class ExamPointsRightPanel extends JPanel {

    public ExamPointsRightPanel() {
        setUpPanel();
        initializeComponents();
    }

    private void setUpPanel() {
        BoxLayout layout =
                new BoxLayout(ExamPointsRightPanel.this, BoxLayout.Y_AXIS);
        setLayout(layout);
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    }

    private void initializeComponents() {
        add(new BasicPartPanel());
        add(new SpecjalistPartPanel());
        add(new TimeAndBtnConfirmPanel());
    }

    class BasicPartPanel extends JPanel {
        public BasicPartPanel() {
            setLayout(new BoxLayout(BasicPartPanel.this, BoxLayout.Y_AXIS));

            JLabel basicPartLbl = new JLabel("Część podstawowa");
            basicPartLbl.setAlignmentX(Component.CENTER_ALIGNMENT);
            basicPartLbl.setFont(Const.Fonts.textsFont);

            JLabel basicQuestionNumLbl = new JLabel("Pytanie numer: 1/20");
            basicQuestionNumLbl.setAlignmentX(Component.CENTER_ALIGNMENT);
            basicQuestionNumLbl.setFont(Const.Fonts.textsFont);

            JPanel ballsPanel = new JPanel(new GridLayout(4, 5, 5, 5));

            ImageIcon unmarkedBallIcon = ImagesUtil.getUnmarkedBallIcon();

            for (int i = 1; i <= 20; i++) {
                JLabel imageLabel = new JLabel("", unmarkedBallIcon, SwingConstants.CENTER);
                ballsPanel.add(imageLabel);
            }

            add(basicPartLbl);
            add(basicQuestionNumLbl);
            add(Box.createRigidArea(new Dimension(0, 10)));
            add(ballsPanel);
        }
    }

    class SpecjalistPartPanel extends JPanel {
        public SpecjalistPartPanel() {
            setLayout(new BoxLayout(SpecjalistPartPanel.this, BoxLayout.Y_AXIS));

            JLabel specjalistPartLbl = new JLabel("Część specjalistyczna");
            specjalistPartLbl.setAlignmentX(Component.CENTER_ALIGNMENT);
            specjalistPartLbl.setFont(Const.Fonts.textsFont);

            JLabel specjalistQuestionNumLbl = new JLabel("Pytanie numer: 1/20");
            specjalistQuestionNumLbl.setAlignmentX(Component.CENTER_ALIGNMENT);
            specjalistQuestionNumLbl.setFont(Const.Fonts.textsFont);

            JPanel ballsPanel = new JPanel(new GridLayout(3, 5, 5, 5));

            ImageIcon markedBallIcon = ImagesUtil.getMarkedBallIcon();

            for (int i = 1; i <= 15; i++) {
                if (i <= 12) {
                    JLabel imageLabel = new JLabel("", markedBallIcon, JLabel.LEADING);
                    ballsPanel.add(imageLabel);
                } else {
                    ballsPanel.add(new JLabel());
                }
            }

            add(Box.createRigidArea(new Dimension(0, 10)));
            add(specjalistPartLbl);
            add(specjalistQuestionNumLbl);
            add(Box.createRigidArea(new Dimension(0, 10)));
            add(ballsPanel);
        }
    }

    class TimeAndBtnConfirmPanel extends JPanel {
        public TimeAndBtnConfirmPanel() {
            setLayout(new BoxLayout(TimeAndBtnConfirmPanel.this, BoxLayout.Y_AXIS));

            JLabel timerLbl = getTimerLbl();
            JButton button = getAcceptBtn();
            JPanel howManyPointsPanel = getHowManyPointsPanel();

            add(timerLbl);
            add(Box.createRigidArea(new Dimension(0, 10)));
            add(button);
            add(Box.createRigidArea(new Dimension(0, 10)));
            add(howManyPointsPanel);
        }

        private JPanel getHowManyPointsPanel() {
            JPanel howManyPointsPanel = new JPanel();
            JLabel howManyPoints = new JLabel("2 pkt.");
            howManyPoints.setAlignmentX(Component.CENTER_ALIGNMENT);
            howManyPoints.setFont(Const.Fonts.textsFont);
            howManyPointsPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            howManyPointsPanel.add(howManyPoints);
            return howManyPointsPanel;
        }

        private JButton getAcceptBtn() {
            JButton button = new JButton("Zatwierdź");
            button.setBackground(Color.WHITE);
            button.setPreferredSize(Const.Dimensions.btnSize);
            button.setMinimumSize(Const.Dimensions.btnSize);
            button.setMaximumSize(Const.Dimensions.btnSize);
            button.setAlignmentX(Component.CENTER_ALIGNMENT);
            return button;
        }

        private JLabel getTimerLbl() {
            JLabel timerLbl = new JLabel("40");
            timerLbl.setAlignmentX(Component.CENTER_ALIGNMENT);
            timerLbl.setFont(Const.Fonts.timerFont);
            timerLbl.setForeground(Const.Colors.timerFontColor);
            return timerLbl;
        }
    }
}
