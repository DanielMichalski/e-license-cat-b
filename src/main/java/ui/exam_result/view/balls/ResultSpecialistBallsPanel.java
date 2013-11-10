package ui.exam_result.view.balls;

import model.SpecialistQuestion;
import ui.exam_result.controller.ExamResultPresenter;
import util.Const;
import util.IconUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

/**
 * Author: Daniel
 * Date: 09.11.13
 */
public class ResultSpecialistBallsPanel extends JPanel {
    private ExamResultPresenter presenter;
    private List<SpecialistQuestion> specialistQuestions;

    public ResultSpecialistBallsPanel(ExamResultPresenter presenter,
                                      List<SpecialistQuestion> specialistQuestions) {

        this.presenter = presenter;
        this.specialistQuestions = specialistQuestions;

        setUpPanel();
        initComponents();
    }

    private void setUpPanel() {
        setBackground(Const.Colors.EXAM_BACKGROUND_COLOR);
        setLayout(new GridLayout(3, 5, 0, 5));
    }

    private void initComponents() {
        ImageIcon noAnswerBallIcon = IconUtils.getUnmarkedBallIcon();
        ImageIcon goodBallIcon = IconUtils.getGoodBallIcon();
        ImageIcon badBallIcon = IconUtils.getBadBallIcon();

        int howManyBalls = 12;

        for (int i = 0; i < howManyBalls; i++) {
            JLabel imageLbl = new JLabel();
            if (specialistQuestions.get(i).getUserAnswer() == null) {
                imageLbl.setIcon(noAnswerBallIcon);
            } else if (specialistQuestions.get(i).getUserAnswer() == specialistQuestions.get(i).getCorrectAnswer()) {
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
                    presenter.setNumberOfSpecialistQuestion(jLabel.getDisplayedMnemonic());
                    presenter.changePanelToSpecialistPanel();
                }
            });
        }

        add(new JLabel());
        add(new JLabel());
        add(new JLabel());
    }
}
