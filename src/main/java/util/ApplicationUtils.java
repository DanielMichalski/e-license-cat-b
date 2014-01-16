package util;

import database.dao.TextsDao;
import database.provider.ModuleProvider;
import database.provider.QuestionsProvider;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import ui.exam.logic.ExamPresenter;
import ui.main_menu.view.MainMenuFrame;

import javax.swing.*;
import java.awt.*;
import java.io.InputStream;

/**
 * Author: Daniel
 * Date: 05.11.13
 */
public class ApplicationUtils {
    private static final Logger logger = getLogger(ExamPresenter.class);

    public static void setSystemLookAndFeel() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException e) {
            logger.warn(e.toString());
        } catch (InstantiationException e) {
            logger.warn(e.toString());
        } catch (IllegalAccessException e) {
            logger.warn(e.toString());
        } catch (UnsupportedLookAndFeelException e) {
            logger.warn(e.toString());
        }
    }

    public static void setNimbusLookAndFeel() {
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (ClassNotFoundException e) {
            logger.warn(e.toString());
        } catch (InstantiationException e) {
            logger.warn(e.toString());
        } catch (IllegalAccessException e) {
            logger.warn(e.toString());
        } catch (UnsupportedLookAndFeelException e) {
            logger.warn(e.toString());
        }
    }

    public static void setApplicationIcon(Window owner) {
        try {
            String appIconFileName = TextsDao.getFileName("img.app_icon");
            ImageIcon iconImage = ImageUtils.getProgramImage(appIconFileName);
            owner.setIconImage(iconImage.getImage());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e, "Informacja", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public static void checkResource(InputStream resourceAsStream) {
        if (resourceAsStream == null) {
            logger.error("Wystąpił błąd przy wczytywaniu pytań. Aplikacja zostanie zamknięta.");
            JOptionPane.showMessageDialog(
                    null,
                    "Wystąpił błąd przy wczytywaniu pytań. Aplikacja zostanie zamknięta",
                    "Informacja",
                    JOptionPane.INFORMATION_MESSAGE);
            System.exit(0);
        }
    }

    public static void prepareApp(final Window window) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    FilesUtils.createTempFolder();
                    FilesUtils.deleteTempFolderContent();
                    readQuestions();
                    window.dispose();

                    EventQueue.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            MainMenuFrame mainMenuFrame = new MainMenuFrame();
                            mainMenuFrame.setVisible(true);
                        }
                    });
                } catch (Exception e) {
                    showErrorMessage(e);
                }
            }
        }).start();
    }

    private static void showErrorMessage(Exception e) {
        JOptionPane.showMessageDialog(
                null,
                "Wystąpił błąd przy próbie uruchomienia aplikacji: " + e,
                "Informacja",
                JOptionPane.ERROR_MESSAGE);
    }

    private static void readQuestions() {
        QuestionsProvider.getInstance();
        ModuleProvider.getInstance();
    }

    public static Logger getLogger(Class className) {
        final Logger logger = Logger.getLogger(className);
        PropertyConfigurator.configure(ApplicationUtils.class.getResource("/log4j.properties"));
        return logger;
    }
}
