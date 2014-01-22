package ui.exam_info;

import database.dao.TextsDao;
import ui.exam_info.logic.ExamInfoPresenter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

/**
 * Author: dmichalski
 * Date: 02.11.13
 */
public class ExamInfoDialog extends JDialog {

    public static final int WIDTH = 640;
    public static final int HEIGHT = 383;

    public ExamInfoDialog() {
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

    }

    private void initializeComponents() {
        ExamInfoPresenter presenter = new ExamInfoPresenter();

        ExamInfoPanel showInfoPanel = new ExamInfoPanel();
        add(showInfoPanel);

        presenter.setStartExamBtn(showInfoPanel.getStartExamLbl(), this);
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
