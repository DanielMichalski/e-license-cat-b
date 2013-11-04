package view.exam_view;

import model.StandardQuestion;
import util.Const;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.net.URL;

/**
 * Author: Daniel
 * Date: 03.11.13
 */
public class ExamQuestionsLeftPanel extends JPanel {
    private JLabel imageLabel;
    private JLabel questionLabel;

    private JButton yesBtn;
    private JButton noBtn;

    private Border emptyBorder;

    public ExamQuestionsLeftPanel() {
        setUpPanel();
        initializeComponents();
    }

    private void setUpPanel() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setAlignmentX(Component.LEFT_ALIGNMENT);
        setBackground(Const.Colors.examBackgroundColor);
    }

    private void initializeComponents() {
        emptyBorder = BorderFactory.createEmptyBorder(10, 10, 10, 10);

        add(getImage());
        add(Box.createRigidArea(new Dimension(0, 50)));
        add(getQuestion());
        add(Box.createRigidArea(new Dimension(0, 50)));
        add(getButtonPanel());
    }

    public Component getImage() {
        JPanel imagePanel = new JPanel();

        imagePanel.setBorder(emptyBorder);
        imagePanel.setBackground(Const.Colors.examBackgroundColor);

        URL imageSrc = getClass().getResource("/images/wait_photo.jpg");
        ImageIcon image = new ImageIcon(imageSrc);
        imageLabel = new JLabel("", image, JLabel.LEADING);
        imageLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        imagePanel.add(imageLabel);
        return imagePanel;
    }

    public Component getQuestion() {
        JPanel questionPanel = new JPanel();

        questionPanel.setBorder(emptyBorder);
        questionPanel.setBackground(Const.Colors.examBackgroundColor);

        questionLabel = new JLabel();
        questionLabel.setText("Czy wyjeżdzając za te znaki informacyjne włączysz się do ruchu?");
        questionLabel.setFont(Const.Fonts.textsFont);

        questionPanel.add(questionLabel);

        return questionPanel;
    }

    public JPanel getButtonPanel() {
        JPanel buttonPanel = new JPanel();

        buttonPanel.setBorder(emptyBorder);
        buttonPanel.setBackground(Const.Colors.examBackgroundColor);

        yesBtn = createBtn("Tak");
        noBtn = createBtn("Nie");

        buttonPanel.add(yesBtn);
        buttonPanel.add(noBtn);

        return buttonPanel;
    }

    public void setQestion(StandardQuestion qestion) {
        questionLabel.setText(qestion.getQuestion());
    }

    private JButton createBtn(String label) {
        JButton button = new JButton(label);
        button.setBackground(Color.WHITE);
        button.setPreferredSize(Const.Dimensions.exambtnSize);
        return button;
    }

    public JButton getYesBtn() {
        return yesBtn;
    }

    public JButton getNoBtn() {
        return noBtn;
    }
}
