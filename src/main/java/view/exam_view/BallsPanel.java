package view.exam_view;

import util.Const;
import util.ImagesUtil;

import javax.swing.*;
import java.awt.*;

/**
 * Author: Daniel
 * Date: 04.11.13
 */
public class BallsPanel extends JPanel {
    private int howManyBalls;
    private int questionNumber;
    private BallPanelType ballPanelType;

    public BallsPanel(int howManyBalls, BallPanelType ballPanelType) {
        setBackground(Const.Colors.examBackgroundColor);

        this.howManyBalls = howManyBalls;
        questionNumber = 0;
        this.ballPanelType = ballPanelType;

        switch (ballPanelType) {
            case basic:
                setLayout(new GridLayout(4, 5, 0, 5));
                break;
            case specialist:
                setLayout(new GridLayout(3, 5, 0, 5));
        }
    }

    public void setQuestionNumber(int questionNumber) {
        this.questionNumber = questionNumber;
        repaint();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        removeAll();

        ImageIcon markedBallIcon = ImagesUtil.getMarkedBallIcon();
        ImageIcon unmarkedBallIcon = ImagesUtil.getUnmarkedBallIcon();

        for (int i = 1; i <= howManyBalls; i++) {
            if (i <= questionNumber) {
                add(new JLabel("", markedBallIcon, SwingConstants.CENTER));
            } else {
                add(new JLabel("", unmarkedBallIcon, SwingConstants.CENTER));
            }
        }

        if (ballPanelType == BallPanelType.specialist) {
            add(new JLabel());
            add(new JLabel());
            add(new JLabel());
        }

        super.validate();
    }

    enum BallPanelType {
        basic,
        specialist
    }
}
