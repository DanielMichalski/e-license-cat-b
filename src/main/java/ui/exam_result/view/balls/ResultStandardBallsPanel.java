package ui.exam_result.view.balls;

import media.images.IconUtils;
import model.StandardQuestion;
import ui.exam_result.logic.ExamResultPresenter;
import util.Const;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

/**
 * Author: Daniel
 * Date: 08.11.13
 */
public class ResultStandardBallsPanel extends JPanel {
    ExamResultPresenter presenter;
    private List<StandardQuestion> standardQuestionList;


    public ResultStandardBallsPanel(ExamResultPresenter presenter,
                                    List<StandardQuestion> standardQuestionList) {

        this.presenter = presenter;
        this.standardQuestionList = standardQuestionList;

        setUpPanel();
        initComponents();
    }

    private void setUpPanel() {
        setBackground(Const.Colors.EXAM_BACKGROUND_COLOR);
        setLayout(new GridLayout(4, 5, 0, 5));
    }

    private void initComponents() {
        ImageIcon noAnswerBallIcon = IconUtils.getUnmarkedBallIcon();
        ImageIcon goodBallIcon = IconUtils.getGoodBallIcon();
        ImageIcon badBallIcon = IconUtils.getBadBallIcon();

        int howManyBalls = 20;
        for (int i = 0; i < howManyBalls; i++) {
            JLabel imageLbl = new JLabel();
            if (standardQuestionList.get(i).getUserAnswer() == null) {
                imageLbl.setIcon(noAnswerBallIcon);
            } else if (standardQuestionList.get(i).getUserAnswer() == standardQuestionList.get(i).getCorrectAnswer()) {
                imageLbl.setIcon(goodBallIcon);
            } else {
                imageLbl.setIcon(badBallIcon);
            }

            add(imageLbl);
            imageLbl.setDisplayedMnemonic(i + 1);
            imageLbl.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

            imageLbl.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    JLabel jLabel = (JLabel) e.getSource();
                    presenter.setNumberOfStandardQuestion(jLabel.getDisplayedMnemonic());
                    presenter.changePanelToStandardPanel();
                }
            });
        }
    }
}
