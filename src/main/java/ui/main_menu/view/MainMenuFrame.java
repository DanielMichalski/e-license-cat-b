package ui.main_menu.view;

import database.dao.TextsDao;
import ui.main_menu.logic.MainMenuPresenter;
import util.ApplicationUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Author: dmichalski
 * Date: 01.11.13
 */
public class MainMenuFrame extends JFrame implements IMinimalize {

    private JMenuItem startExamMenuItem;
    private JMenuItem closeMenuItem;
    private JMenuItem aboutMenuItem;

    public MainMenuFrame() {
        setUpFrame();
        initializeComponents();
    }

    private void setUpFrame() {
        ApplicationUtils.setNimbusLookAndFeel();
        setTitle(TextsDao.getText("view.MainMenu.title"));
        setResizable(false);
        ApplicationUtils.setApplicationIcon(this);
    }

    private void initializeComponents() {
        final MainMenuPresenter presenter = new MainMenuPresenter(this);

        MainMenuPanel mainPanel = new MainMenuPanel();
        add(mainPanel, BorderLayout.CENTER);

        JMenuBar menuBar = createJMenuBar();
        setJMenuBar(menuBar);

        presenter.setStartExamBtn(mainPanel.getStartExamBtn());
        presenter.setInfoAboutExamBtn(mainPanel.getInfoAboutExamBtn());
        presenter.setStartExamMenuItem(startExamMenuItem);
        presenter.setCloseMenuItem(closeMenuItem);
        presenter.setAboutMenuItem(aboutMenuItem);

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
        JMenu helpMenu = new JMenu(TextsDao.getText("view.MainMenuFrame.Menu.helpMenu"));

        menuBar.add(fileMenu);
        menuBar.add(helpMenu);

        startExamMenuItem = new JMenuItem(TextsDao.getText("view.MainMenuFrame.Menu.starExamMenuItem"));
        fileMenu.add(startExamMenuItem);

        closeMenuItem = new JMenuItem(TextsDao.getText("view.MainMenuFrame.Menu.closeMenuItem"));
        fileMenu.add(closeMenuItem);

        aboutMenuItem = new JMenuItem(TextsDao.getText("view.MainMenuFrame.Menu.aboutMenuItem"));
        helpMenu.add(aboutMenuItem);

        return menuBar;
    }

    @Override
    public void minimalize() {
        setState(Frame.ICONIFIED);
    }
}
