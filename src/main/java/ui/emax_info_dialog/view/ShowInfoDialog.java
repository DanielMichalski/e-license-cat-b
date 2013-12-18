package ui.emax_info_dialog.view;

import database.dao.TextsDao;
import ui.emax_info_dialog.logic.IWindowCloser;
import ui.emax_info_dialog.logic.ShowInfoPresenter;
import ui.main_menu.view.MainMenuFrame;
import util.ImageUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Author: dmichalski
 * Date: 02.11.13
 */
public class ShowInfoDialog extends JDialog implements IWindowCloser {
    private ShowInfoPresenter presenter;

    public ShowInfoDialog() {
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

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                EventQueue.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        MainMenuFrame mv = new MainMenuFrame();
                        mv.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
                        mv.setVisible(true);
                    }
                });
            }
        });
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
        JButton startBtn = new JButton(TextsDao.getText(
                "views.ShowInfoAboutExam.startBtn.text"));

        startBtn.addActionListener(presenter.getStartExamListener(this));

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
