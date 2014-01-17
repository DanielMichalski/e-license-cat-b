import org.apache.log4j.Logger;
import ui.loading_new.LoadingFrame;
import util.ApplicationUtils;
import util.FilesUtils;

import javax.swing.*;
import java.awt.*;

/**
 * Author: dmichalski
 * Date: 01.11.13
 */
public class RunApplication {
    public static Logger logger = ApplicationUtils.getLogger(RunApplication.class);

    public static void main(String[] args) {
        ApplicationUtils.setNimbusLookAndFeel();

        //TODO poprawic
        if (ApplicationUtils.isCDInTheDrive()) {
            EventQueue.invokeLater(new Runnable() {
                @Override
                public void run() {
                    FilesUtils.loadVLCJNativeLibraries();
                    new LoadingFrame();
                }
            });
        } else {
            showNoCDErrorMessage();

            EventQueue.invokeLater(new Runnable() {
                @Override
                public void run() {
                    FilesUtils.loadVLCJNativeLibraries();
                    new LoadingFrame();
                }
            });
        }
    }

    private static void showNoCDErrorMessage() {
        logger.error("Brak płyty w napędzie, aplikacja nie może się uruchomić bez płyty");

        JOptionPane.showMessageDialog(
                null,
                "Brak płyty w napędzie. Włóż płytę z aplikacją ePrawko i spróbuj ponownie uruchomić aplikację. (na potrzeby testów uruchomi się)",
                "Błąd",
                JOptionPane.ERROR_MESSAGE);
    }
}

