package view.dialogs;

import database.TextsDao;
import util.Const;
import view.main_menu.MainMenuFrame;

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
    }

    private void setUpDialog() {
        setTitle(TextsDao.get("view.ShowInfoAboutExam.dialogName"));
        setLocationRelativeTo(null);
        setModal(true);
    }

    private void initializeComponents() {
        add(createCenterPanel(), BorderLayout.CENTER);
        add(createSouthPanel(), BorderLayout.SOUTH);
    }

    /**
     * Metoda tworzy centralny panel okna dialogowego na ktorym
     * są wyświetlane informacje o egzaminie. Informacje o egzaminie są
     * pobierane z pliku about_egzam.txt znajdującego się w resources
     *
     * @return komponent zawierający tekst opisujący egzamin
     */
    protected JComponent createCenterPanel() {

        JTextArea aboutTextArea = new JTextArea();
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
    protected JComponent createSouthPanel() {

        JButton saveBtn = new JButton(TextsDao.get("views.ShowInfoAboutExam.startBtn.text"));

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Const.Colors.backgroundColor);
        buttonPanel.add(saveBtn);

        return buttonPanel;
    }
}
