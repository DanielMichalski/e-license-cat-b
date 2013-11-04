package view.dialogs;

import database.TextsDao;
import util.Const;
import view.exam_view.ExamFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;

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
        setTitle(TextsDao.get("view.ShowInfoAboutExam.dialogName"));
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
        aboutTextArea.setBackground(Const.Colors.examBackgroundColor);

        try {
            String aboutPluginTxtFileName = TextsDao.get("view.showInfoAboutExam.txtFileName");
            InputStream textFileStream = getClass().getResourceAsStream(aboutPluginTxtFileName);
            Reader reader = new InputStreamReader(textFileStream, "UTF-8");

            Scanner scanner = new Scanner(reader);

            while (scanner.hasNextLine()) {
                aboutTextArea.append(scanner.nextLine() + "\n");
            }
        } catch (UnsupportedEncodingException ignored) {
        }

        return aboutTextArea;
    }

    /**
     * Metoda tworzy dolny panel z przyciskami
     *
     * @return dolny panel z przyciskiem
     */
    protected JComponent createSouthComponent() {
        JButton startBtn;
        if (startExamAfterRead) {
            startBtn = new JButton(TextsDao.get("views.ShowInfoAboutExam.startBtn.text"));
            startBtn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    showExamFrameAndCloseThis();
                }
            });
        } else {
            startBtn = new JButton(TextsDao.get("views.ShowInfoAboutExam.closeBtn.text"));
            startBtn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    ShowInfoAboutExamDialog.this.dispose();
                }
            });
        }

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Const.Colors.examBackgroundColor);
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
