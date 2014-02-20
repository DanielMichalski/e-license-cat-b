package ui.application_info.view;

import database.dao.TextsDao;
import ui.application_info.logic.ShowInfoPresenter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

/**
 * Author: dmichalski
 */
public class ShowInfoDialog extends JDialog {

    public static final int WIDTH = 520;
    public static final int HEIGHT = 700;

    public ShowInfoDialog() {
        setUpDialog();
        initializeComponents();
        setLocationRelativeTo(null);
    }

    private void setUpDialog() {
        setSize(new Dimension(WIDTH, HEIGHT));
        setIgnoreRepaint(false);
        setUndecorated(true);
        setTitle(TextsDao.getText("view.ShowInfoAboutApp.title"));
        setModal(true);
        setResizable(false);

    }

    private void initializeComponents() {
        ShowInfoPresenter presenter = new ShowInfoPresenter();

        ShowInfoPanel showInfoPanel = new ShowInfoPanel() {
            @Override
            public Dimension getPreferredSize() {
                return new Dimension(490, 1000);
            }
        };

        JScrollPane scrollPane = new JScrollPane(showInfoPanel);
        scrollPane.getVerticalScrollBar().setUnitIncrement(15);
        getContentPane().add(scrollPane, BorderLayout.CENTER);
        presenter.setAboutApp(showInfoPanel.getAboutApp(), this);

        setWindowRemoveble();
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
}
