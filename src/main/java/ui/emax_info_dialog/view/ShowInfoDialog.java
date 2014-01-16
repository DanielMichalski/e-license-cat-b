package ui.emax_info_dialog.view;

import database.dao.TextsDao;
import ui.emax_info_dialog.logic.ShowInfoPresenter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

/**
 * Author: dmichalski
 * Date: 02.11.13
 */
public class ShowInfoDialog extends JDialog {
    public static final int HEIGHT = 850;
    public static final int WIDTH = 798;

    private ShowInfoPresenter presenter;

    public ShowInfoDialog() {
        this.presenter = new ShowInfoPresenter();

        setUpDialog();
        initializeComponents();
        setLocationRelativeTo(null);
    }

    private void setUpDialog() {
        setSize(new Dimension(WIDTH, HEIGHT));
        setUndecorated(true);
        setTitle(TextsDao.getText("view.ShowInfoAboutExam.title"));
        setModal(true);
        setResizable(false);
        setWindowRemoveble();
    }

    private void initializeComponents() {
        presenter = new ShowInfoPresenter();

        ShowInfoPanel showInfoPanel = new ShowInfoPanel();
        add(showInfoPanel);

        presenter.setAboutApp(showInfoPanel.getAboutApp(), this);
    }

    private void setWindowRemoveble() {
        final Point point = new Point();
        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                if (!e.isMetaDown()) {
                    point.x = e.getX();
                    point.y = e.getY();
                }
            }
        });
        addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(MouseEvent e) {
                if (!e.isMetaDown()) {
                    Point p = getLocation();
                    setLocation(p.x + e.getX() - point.x,
                            p.y + e.getY() - point.y);
                }
            }
        });
    }

   /* *//**
     * Metoda tworzy centralny panel okna dialogowego na ktorym
     * są wyświetlane informacje o egzaminie. Informacje o egzaminie są
     * pobierane z pliku information.jpg znajdującego się w resources
     *
     * @return komponent zawierający dane opisujące egzamin
     *//*
    protected JComponent createCenterComponent() {
        JPanel imagePanel = new JPanel();
        imagePanel.setBackground(Color.WHITE);

        String aboutExamImageFileName = TextsDao.getFileName("img.wait_start");
        ImageIcon aboutExamImage = ImageUtils.getProgramImage(aboutExamImageFileName);
        JLabel imageLbl = new JLabel(aboutExamImage);

        imagePanel.add(imageLbl);

        return imagePanel;

    }

    *//**
     * Metoda tworzy dolny panel z przyciskiem
     *
     * @return dolny panel z przyciskiem
     *//*
    protected JComponent createSouthComponent() {
        JButton startBtn = new JButton(TextsDao.getText(
                "views.ShowInfoAboutExam.startBtn.text"));

        startBtn.addActionListener(presenter.getStartExamListener(this));

        startBtn.setFocusable(false);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.WHITE);
        buttonPanel.add(startBtn);

        return buttonPanel;
    }*/

}
