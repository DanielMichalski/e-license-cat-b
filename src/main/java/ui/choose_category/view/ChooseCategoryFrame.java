package ui.choose_category.view;

import database.dao.TextsDao;
import ui.choose_category.logic.ChooseCategoryPresenter;
import util.ApplicationUtils;
import util.Const;

import javax.swing.*;

/**
 * Author: Daniel
 */
public class ChooseCategoryFrame extends JFrame {
    public static final int WIDTH = 720;
    public static final int HEIGHT = 410;

    public ChooseCategoryFrame() {
        setUpFrame();
        initializeComponents();
    }

    private void setUpFrame() {
        setTitle(TextsDao.getText("view.ChooseCategory.title"));
        setSize(WIDTH, HEIGHT);
        setLocationRelativeTo(null);
        setResizable(false);
        getContentPane().setBackground(Const.Colors.BACKGROUND_COLOR);
        ApplicationUtils.setApplicationIcon(this);
    }

    private void initializeComponents() {
        ChooseCategoryPresenter presenter =
                new ChooseCategoryPresenter(this);

        ChooseCategoryPanel panel = new ChooseCategoryPanel();
        add(panel);

        presenter.setChoosebtn(panel.getChoosebtn());
        presenter.setModuleJList(panel.getModuleJList());
        addWindowListener(presenter.getWindowListener());
    }
}
