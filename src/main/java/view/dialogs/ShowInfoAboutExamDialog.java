package view.dialogs;

import database.TextsDao;
import util.ImagesUtil;
import view.exam_view.ExamFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Author: dmichalski
 * Date: 02.11.13
 */
public class ShowInfoAboutExamDialog extends JDialog {
    private boolean startExamAfterRead;

    public ShowInfoAboutExamDialog(boolean startExamAfterRead) {
        this.startExamAfterRead = startExamAfterRead;
        setUpDialog();
        initializeComponents();
        pack();
        setLocationRelativeTo(null);
    }

    private void setUpDialog() {
        setTitle(TextsDao.getText("view.ShowInfoAboutExam.dialogName"));
        setModal(true);
        setResizable(false);
    }

    private void initializeComponents() {
        add(createCenterComponent(), BorderLayout.CENTER);
        add(createSouthComponent(), BorderLayout.SOUTH);
    }

    /**
     * Metoda tworzy centralny panel okna dialogowego na ktorym
     * są wyświetlane informacje o egzaminie. Informacje o egzaminie są
     * pobierane z pliku information.jpg znajdującego się w resources
     *
     * @return komponent zawierający dane opisujące egzamin
     */
    protected JComponent createCenterComponent() {
        JPanel imagePanel = new JPanel();
        imagePanel.setBackground(Color.WHITE);

        ImageIcon aboutExamImage = ImagesUtil.getAboutExamImage();
        JLabel imageLbl = new JLabel(aboutExamImage);

        imagePanel.add(imageLbl);

        return imagePanel;

    }

    /**
     * Metoda tworzy dolny panel z przyciskiem
     *
     * @return dolny panel z przyciskiem
     */
    protected JComponent createSouthComponent() {
        JButton startBtn;
        if (startExamAfterRead) {
            startBtn = new JButton(TextsDao.getText("views.ShowInfoAboutExam.startBtn.text"));
            startBtn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    showExamFrameAndCloseThis();
                }
            });
        } else {
            startBtn = new JButton(TextsDao.getText("views.ShowInfoAboutExam.closeBtn.text"));
            startBtn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    ShowInfoAboutExamDialog.this.dispose();
                }
            });
        }

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.WHITE);
        buttonPanel.add(startBtn);

        return buttonPanel;
    }

    private void showExamFrameAndCloseThis() {
        this.dispose();
        ExamFrame examFrame = new ExamFrame();
        examFrame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        examFrame.setVisible(true);
    }
}
