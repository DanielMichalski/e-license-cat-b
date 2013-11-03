package view.main_menu;

import controller.MainMenuPresenter;
import database.TextsDao;

import javax.swing.*;
import java.awt.*;

/**
 * Author: dmichalski
 * Date: 01.11.13
 */
public class MainMenuFrame extends JFrame {
    private JMenuItem startExamMenuItem;
    private JMenuItem closeMenuItem;

    public static final int WIDTH = 400;
    public static final int HEIGHT = 300;

    public MainMenuFrame() {
        setUpFrame();
        initializeComponents();
    }

    private void setUpFrame() {
        // zmiana wyglądu na domyślny dla systemu
        try {
            UIManager.setLookAndFeel(
                    UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException ignored) {}
        catch (InstantiationException ignored) {}
        catch (IllegalAccessException ignored) {}
        catch (UnsupportedLookAndFeelException ignored) {}

        setTitle(TextsDao.get("view.MainView.title"));
        setSize(WIDTH, HEIGHT);
        setLocationRelativeTo(null);
    }

    private void initializeComponents() {
        MainMenuPresenter presenter = new MainMenuPresenter();

        MainMenuPanel mainPanel = new MainMenuPanel();
        add(mainPanel, BorderLayout.CENTER);

        JMenuBar menuBar = createJMenuBar();
        setJMenuBar(menuBar);

        presenter.setVisitsBtn(mainPanel.getRegisterBtn());
        presenter.setStartExamMenuItem(startExamMenuItem);
        presenter.setCloseMenuItem(closeMenuItem);
    }

    private JMenuBar createJMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu(TextsDao.get("view.fileMenu"));
        menuBar.add(fileMenu);

        startExamMenuItem = new JMenuItem(TextsDao.get("view.starExamMenuItem"));
        fileMenu.add(startExamMenuItem);

        closeMenuItem = new JMenuItem(TextsDao.get("view.closeMenuItem"));
        fileMenu.add(closeMenuItem);

        return menuBar;
    }
}
