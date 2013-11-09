package ui.exam.view.balls;

import util.Const;
import util.ImagesUtil;

import javax.swing.*;
import java.awt.*;

/**
 * Author: Daniel
 * Date: 04.11.13
 */
public abstract class BallsPanel extends JPanel {
    protected int howManyBalls;
    protected int questionNumber;

    public BallsPanel(int howManyBalls) {
        setBackground(Const.Colors.EXAM_BACKGROUND_COLOR);

        this.howManyBalls = howManyBalls;
        questionNumber = 0;
    }

    public void setQuestionNumber(int questionNumber) {
        this.questionNumber = questionNumber;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
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

        super.validate();
    }
}
