package ui.main_menu.view;

import database.dao.TextsDao;
import ui.main_menu.controller.MainMenuPresenter;
import util.Utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Author: dmichalski
 * Date: 01.11.13
 */
public class MainMenuFrame extends JFrame {

    private JMenuItem startExamMenuItem;
    private JMenuItem closeMenuItem;

    public MainMenuFrame() {
        setUpFrame();
        initializeComponents();
    }

    private void setUpFrame() {
        Utils.setSystemLookAndFeel();

        setTitle(TextsDao.getText("view.MainMenu.title"));
        setResizable(false);
        Utils.setApplicationIcon(this);
    }

    private void initializeComponents() {
        final MainMenuPresenter presenter = new MainMenuPresenter();

        MainMenuPanel mainPanel = new MainMenuPanel();
        add(mainPanel, BorderLayout.CENTER);

        JMenuBar menuBar = createJMenuBar();
        setJMenuBar(menuBar);

        presenter.setStartExamBtn(mainPanel.getStartExamBtn());
        presenter.setInfoAboutExamBtn(mainPanel.getInfoAboutExamBtn());
        presenter.setStartExamMenuItem(startExamMenuItem);
        presenter.setCloseMenuItem(closeMenuItem);

        pack();
        setLocationRelativeTo(null);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                presenter.showConfirmClosingDialog();
            }
        });
    }

    private JMenuBar createJMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu(TextsDao.getText("view.MainMenuFrame.Menu.fileMenu"));
        menuBar.add(fileMenu);

        startExamMenuItem = new JMenuItem(TextsDao.getText("view.MainMenuFrame.Menu.starExamMenuItem"));
        fileMenu.add(startExamMenuItem);

        closeMenuItem = new JMenuItem(TextsDao.getText("view.MainMenuFrame.Menu.closeMenuItem"));
        fileMenu.add(closeMenuItem);

        return menuBar;
    }
}
