package ui.loading_new;

import database.provider.ModuleProvider;
import database.provider.QuestionsProvider;
import ui.main_menu.view.MainMenuFrame;
import util.FilesUtils;

import javax.swing.*;
import java.awt.*;

/**
 * Author: Daniel
 */
public class LoadingFrame extends JDialog {

    public static final int HEIGHT = 220;
    public static final int WIDTH = 300;

    public LoadingFrame() {
        setUpFrame();
        initializeComponents();
        setVisible(true);
        prepareApp();
    }

    private void setUpFrame() {
        setSize(WIDTH, HEIGHT);
        setLocationRelativeTo(null);
        setUndecorated(true);
    }

    private void initializeComponents() {
        LoadingPanel loadingPanel = new LoadingPanel();
        add(loadingPanel);
    }

    private void prepareApp() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                FilesUtils.createTempFolder();
                FilesUtils.deleteTempFolderContent();
                readQuestions();
                dispose();

                EventQueue.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        MainMenuFrame mainMenuFrame = new MainMenuFrame();
                        mainMenuFrame.setVisible(true);
                    }
                });
            }
        }).start();
    }

    private void readQuestions() {
        QuestionsProvider.getInstance();
        ModuleProvider.getInstance();
    }
}
