package ui.emax_info_dialog.view;

import database.dao.TextsDao;
import media.images.ImageUtils;
import ui.emax_info_dialog.logic.IWindowCloser;
import ui.emax_info_dialog.logic.ShowInfoPresenter;
import ui.main_menu.view.IMinimalize;

import javax.swing.*;
import java.awt.*;

/**
 * Author: dmichalski
 * Date: 02.11.13
 */
public class ShowInfoDialog extends JDialog implements IWindowCloser {
    private boolean startExamAfterRead;
    private IMinimalize iMinimalize;
    private ShowInfoPresenter presenter;

    public ShowInfoDialog(boolean startExamAfterRead, IMinimalize iMinimalize) {
        this.startExamAfterRead = startExamAfterRead;
        this.iMinimalize = iMinimalize;
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

            startBtn.addActionListener(presenter.getStartExamListener(this, iMinimalize));
        } else {
            startBtn = new JButton(TextsDao.getText("" +
                    "views.ShowInfoAboutExam.closeBtn.text"));

            startBtn.addActionListener(presenter.getCloseAboutExamListener(this));
        }

        startBtn.setFocusable(false);

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
