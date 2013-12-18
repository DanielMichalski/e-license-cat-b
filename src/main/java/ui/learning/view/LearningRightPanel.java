package ui.learning.view;

import database.dao.TextsDao;
import util.Const;

import javax.swing.*;
import java.awt.*;

/**
 * Author: Daniel
 * Date: 08.11.13
 */
public class LearningRightPanel extends JPanel {
    private CloseBtnPanel closeBtnPanel;
    private HowManyPointsPanel howManyPointsPanel;
    private JButton closeBtn;

    public LearningRightPanel() {
        setUpPanel();
        initializeComponents();
    }

    private void setUpPanel() {
        BoxLayout layout =
                new BoxLayout(LearningRightPanel.this, BoxLayout.Y_AXIS);
        setLayout(layout);
        setBorder(BorderFactory.createEmptyBorder(5, 10, 10, 10));
        setBackground(Const.Colors.EXAM_BACKGROUND_COLOR);
    }

    private void initializeComponents() {
        closeBtnPanel = new CloseBtnPanel();
        howManyPointsPanel = new HowManyPointsPanel();

        add(closeBtnPanel);
        add(howManyPointsPanel);
    }

    public JButton getCloseBtn() {
        return closeBtn;
    }

    public class CloseBtnPanel extends JPanel {
        public CloseBtnPanel() {
            setBackground(Const.Colors.EXAM_BACKGROUND_COLOR);

            add(createCloseBtn());
        }

        private JButton createCloseBtn() {
            closeBtn = new JButton(TextsDao.getText("ExamPointRigthPanel.btnCloseLbl"));
            closeBtn.setFont(Const.Fonts.BTN_CLOSE_FONT);
            closeBtn.setPreferredSize(Const.Dimensions.EXAM_CLOSE_BTN_SIZE);
            closeBtn.setMinimumSize(Const.Dimensions.EXAM_CLOSE_BTN_SIZE);
            closeBtn.setMaximumSize(Const.Dimensions.EXAM_CLOSE_BTN_SIZE);
            closeBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
            closeBtn.setFocusable(false);
            return closeBtn;
        }

        public JButton getCloseBtn() {
            return closeBtn;
        }
    }

    public class HowManyPointsPanel extends JPanel {
        private JLabel howManyPointsForQuestionLbl;

        public HowManyPointsPanel() {
            setLayout(new BoxLayout(HowManyPointsPanel.this, BoxLayout.Y_AXIS));
            setBackground(Const.Colors.EXAM_BACKGROUND_COLOR);

            howManyPointsForQuestionLbl = createHowManyPointsLbl();

            add(howManyPointsForQuestionLbl);
        }

        private JLabel createHowManyPointsLbl() {
            JLabel howManyPointsLbl = new JLabel();
            howManyPointsLbl.setHorizontalAlignment(SwingConstants.CENTER);
            howManyPointsLbl.setAlignmentX(Component.CENTER_ALIGNMENT);
            howManyPointsLbl.setPreferredSize(Const.Dimensions.HOW_MANY_POINTS_LBL);
            howManyPointsLbl.setMinimumSize(Const.Dimensions.HOW_MANY_POINTS_LBL);
            howManyPointsLbl.setMaximumSize(Const.Dimensions.HOW_MANY_POINTS_LBL);
            howManyPointsLbl.setFont(Const.Fonts.TEXTS_FONT);
            howManyPointsLbl.setBorder(BorderFactory.createLineBorder(Const.Colors.HOW_MANY_POINTS_BORDER_COLOR));
            return howManyPointsLbl;
        }

        private void setHowManyPoints(int points) {
            howManyPointsForQuestionLbl.setText(points + " pkt");
        }
    }

    public void setHowManyQuestionPoints(int howManyPoints) {
        howManyPointsPanel.setHowManyPoints(howManyPoints);
    }

    public CloseBtnPanel getCloseBtnPanel() {
        return closeBtnPanel;
    }
}
