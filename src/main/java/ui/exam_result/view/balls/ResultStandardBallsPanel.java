package ui.exam_result.view.balls;

import model.StandardQuestion;
import util.Const;
import util.ImagesUtil;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * Author: Daniel
 * Date: 08.11.13
 */
public class ResultStandardBallsPanel extends JPanel {
    private List<StandardQuestion> standardQuestionList;
    private int howManyBalls = 20;

    public ResultStandardBallsPanel(List<StandardQuestion> standardQuestionList) {
        this.standardQuestionList = standardQuestionList;
        setBackground(Const.Colors.examBackgroundColor);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        ImageIcon goodBallIcon = ImagesUtil.getGoodBallIcon();
        ImageIcon badBallIcon = ImagesUtil.getBadBallIcon();

        for (StandardQuestion standardQuestion : standardQuestionList) {
            if (standardQuestion.getUserAnswer() == standardQuestion.getCorrectAnswer()) {
                add(new JLabel("", goodBallIcon, SwingConstants.CENTER));
            } else {
                add(new JLabel("", badBallIcon, SwingConstants.CENTER));
            }
        }

        super.validate();
    }
}
