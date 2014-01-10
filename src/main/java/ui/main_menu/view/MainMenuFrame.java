package ui.main_menu.view;

import ui.main_menu.logic.MainMenuPresenter;
import util.ApplicationUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

/**
 * Author: dmichalski
 * Date: 01.11.13
 */
public class MainMenuFrame extends JFrame {

    public static final int HEIGHT = 600;
    public static final int WIDTH = 960;

    public MainMenuFrame() {
        setUpFrame();
        initializeComponents();
    }

    private void setUpFrame() {
        setSize(WIDTH, HEIGHT);
        setUndecorated(true);
        ApplicationUtils.setNimbusLookAndFeel();
        ApplicationUtils.setApplicationIcon(this);
        setLocationRelativeTo(null);
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

    private void initializeComponents() {
        final MainMenuPresenter presenter = new MainMenuPresenter(this);

        MainMenuPanel mainPanel = new MainMenuPanel();
        add(mainPanel, BorderLayout.CENTER);

        presenter.setExerciseBtn(mainPanel.getExerciseBtn());
        presenter.setEgxamBtn(mainPanel.getEgxamBtn());
        presenter.setAboutExamBtn(mainPanel.getAboutApp());
        presenter.setCloseBtn(mainPanel.getCloseBtn());
    }
}
