package view.main_menu;

import controller.MainMenuPresenter;
import database.TextsDao;

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
        // zmiana wyglądu na domyślny dla systemu
        try {
            UIManager.setLookAndFeel(
                    UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException ignored) {}
        catch (InstantiationException ignored) {}
        catch (IllegalAccessException ignored) {}
        catch (UnsupportedLookAndFeelException ignored) {}

        setTitle(TextsDao.get("view.MainView.title"));
        setResizable(false);
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
        JMenu fileMenu = new JMenu(TextsDao.get("view.fileMenu"));
        menuBar.add(fileMenu);

        startExamMenuItem = new JMenuItem(TextsDao.get("view.starExamMenuItem"));
        fileMenu.add(startExamMenuItem);

        closeMenuItem = new JMenuItem(TextsDao.get("view.closeMenuItem"));
        fileMenu.add(closeMenuItem);

        return menuBar;
    }
}
