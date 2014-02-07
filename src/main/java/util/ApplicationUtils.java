package util;

import database.dao.TextsDao;
import database.provider.ModuleProvider;
import database.provider.QuestionsProvider;
import encrypt.Encrypter;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import ui.exam.logic.ExamPresenter;
import ui.main_menu.view.MainMenuFrame;
import uk.co.caprica.vlcj.player.embedded.EmbeddedMediaPlayer;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.io.File;
import java.io.InputStream;
import java.util.Arrays;

/**
 * Author: Daniel
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
                    FileUtils.createTempFolder();
                    FileUtils.deleteTempFolderContent();
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

    public static void checkCD() {
        String cdName = "Testy_B_v3_0";

        java.util.List<File> files = Arrays.asList(File.listRoots());
        for (File drv : files) {
            String drvName = FileSystemView.getFileSystemView().getSystemDisplayName(drv);

            if (drvName.length() == 0) {
                drvName = FileSystemView.getFileSystemView().getSystemTypeDescription(drv);
            }

            logger.info("Znaleziony napęd: " + drvName);

            if (drvName.contains(cdName)) {
                logger.info("Płyta jest w napędzie, można uruchomić aplikację");
                return;
            }
        }

        showNoCDErrorMessageAndExit();
    }

    private static void showNoCDErrorMessageAndExit() {
        logger.error("Brak płyty w napędzie, aplikacja nie może się uruchomić bez płyty. Trwa zamykanie aplikacji.");

        JOptionPane.showMessageDialog(
                null,
                "Brak płyty w napędzie. Włóż płytę z aplikacją Testy B i spróbuj ponownie uruchomić aplikację.",
                "Błąd",
                JOptionPane.ERROR_MESSAGE);

        System.exit(1);
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

    public static void prepareAndPlayMedia(EmbeddedMediaPlayer player, String mediaTitle) {
        try {
            String mrl = FileUtils.getTempDirPath() + File.separator + mediaTitle + ".prode";
            Encrypter.decodeMedia(mediaTitle);
            logger.info("Loading image: " + mediaTitle + ".prode");
            FileUtils.showIfFileExists(mrl, mediaTitle);

            player.stop();
            boolean lodedCorrectly = player.prepareMedia(mrl);

            if (!lodedCorrectly) {
                logger.error("Wystąpił błąd podczas załadowania pliku: " + mediaTitle);
            }

            player.parseMedia();
            player.play();
        } catch (Exception e) {
            logger.error(e);
        }
    }
}
