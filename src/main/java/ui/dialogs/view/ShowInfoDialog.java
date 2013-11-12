package ui.dialogs.view;

import database.dao.TextsDao;
import ui.dialogs.logic.ShowInfoPresenter;
import ui.dialogs.logic.WindowCloser;
import media.images.ImageUtils;

import javax.swing.*;
import java.awt.*;

/**
 * Author: dmichalski
 * Date: 02.11.13
 */
public class ShowInfoDialog extends JDialog implements WindowCloser {
    private boolean startExamAfterRead;
    private ShowInfoPresenter presenter;

    public ShowInfoDialog(boolean startExamAfterRead) {
        this.startExamAfterRead = startExamAfterRead;
        this.presenter = new ShowInfoPresenter();

        setUpDialog();
        initializeComponents();
        pack();
        setLocationRelativeTo(null);
    }

    private void setUpDialog() {
        setTitle(TextsDao.getText("view.ShowInfoAboutExam.title"));
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

        String aboutExamImageFileName = TextsDao.getFileName("img.wait_start");
        ImageIcon aboutExamImage = ImageUtils.getProgramImage(aboutExamImageFileName);
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
            startBtn = new JButton(TextsDao.getText(
                    "views.ShowInfoAboutExam.startBtn.text"));

            startBtn.addActionListener(presenter.getStartExamListener(this));
        } else {
            startBtn = new JButton(TextsDao.getText("" +
                    "views.ShowInfoAboutExam.closeBtn.text"));

            startBtn.addActionListener(presenter.getCloseAboutExamListener(this));
        }

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.WHITE);
        buttonPanel.add(startBtn);

        return buttonPanel;
    }

    @Override
    public void close() {
        dispose();
    }
}
