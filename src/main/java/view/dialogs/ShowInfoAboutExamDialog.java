package view.dialogs;

import database.TextsDao;
import util.Const;
import view.exam_view.ExamFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.InputStream;
import java.util.Scanner;

/**
 * Author: dmichalski
 * Date: 02.11.13
 */
public class ShowInfoAboutExamDialog extends JDialog {

    public ShowInfoAboutExamDialog() {
        setUpDialog();
        initializeComponents();
        pack();
        setLocationRelativeTo(null);
    }

    private void setUpDialog() {
        setTitle(TextsDao.get("view.ShowInfoAboutExam.dialogName"));
        setModal(true);
    }

    private void initializeComponents() {
        add(createCenterComponent(), BorderLayout.CENTER);
        add(createSouthComponent(), BorderLayout.SOUTH);
    }

    /**
     * Metoda tworzy centralny panel okna dialogowego na ktorym
     * są wyświetlane informacje o egzaminie. Informacje o egzaminie są
     * pobierane z pliku about_egzam.txt znajdującego się w resources
     *
     * @return komponent zawierający tekst opisujący egzamin
     */
    protected JComponent createCenterComponent() {

        JTextArea aboutTextArea = new JTextArea();
        aboutTextArea.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        Font sansSerifFont = new Font("SansSerif", Font.PLAIN, 12);
        aboutTextArea.setFont(sansSerifFont);
        aboutTextArea.setEditable(false);
        aboutTextArea.setBackground(Const.Colors.backgroundColor);

        String aboutPluginTxtFileName = TextsDao.get("view.showInfoAboutExam.txtFileName");
        InputStream textFileStream = getClass().getResourceAsStream(aboutPluginTxtFileName);
        Scanner scanner = new Scanner(textFileStream);

        while (scanner.hasNextLine()) {
            aboutTextArea.append(scanner.nextLine() + "\n");
        }

        return aboutTextArea;
    }

    /**
     * Metoda tworzy dolny panel z przyciskami
     *
     * @return dolny panel z przyciskiem
     */
    protected JComponent createSouthComponent() {

        JButton startBtn = new JButton(TextsDao.get("views.ShowInfoAboutExam.startBtn.text"));
        startBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showExamFrameAndCloseThis();
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Const.Colors.backgroundColor);
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
