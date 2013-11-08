package ui.exam.view.balls;

import javax.swing.*;
import java.awt.*;

/**
 * Author: Daniel
 * Date: 08.11.13
 */
public class SpecialistBallsPanel extends BallsPanel {
    private static final int HOW_MANY_BALLS = 12;

    public SpecialistBallsPanel() {
        super(HOW_MANY_BALLS);

        setLayout(new GridLayout(3, 5, 0, 5));
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        add(new JLabel(""));
        add(new JLabel(""));
        add(new JLabel(""));

        super.validate();
    }
}
