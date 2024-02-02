package com.danielmichalski.elicense.ui.exam.view.balls;

import com.danielmichalski.elicense.util.Const;
import com.danielmichalski.elicense.util.IconUtils;

import javax.swing.*;
import java.awt.*;

/**
 * Author: Daniel
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

        ImageIcon markedBallIcon = IconUtils.getMarkedBallIcon();
        ImageIcon unmarkedBallIcon = IconUtils.getUnmarkedBallIcon();

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
