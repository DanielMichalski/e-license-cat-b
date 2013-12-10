package util;

import database.dao.TextsDao;

import javax.swing.*;
import java.awt.*;
import java.util.logging.Logger;

/**
 * Author: Daniel
 * Date: 05.11.13
 */
public class ApplicationUtils {
    private static Logger LOGGER = Logger.getLogger(ApplicationUtils.class.getName());

    public static void setSystemLookAndFeel() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException e) {
            LOGGER.warning(e.toString());
        } catch (InstantiationException e) {
            LOGGER.warning(e.toString());
        } catch (IllegalAccessException e) {
            LOGGER.warning(e.toString());
        } catch (UnsupportedLookAndFeelException e) {
            LOGGER.warning(e.toString());
        }
    }

    public static void setNimbusLookAndFeel() {
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (ClassNotFoundException e) {
            LOGGER.warning(e.toString());
        } catch (InstantiationException e) {
            LOGGER.warning(e.toString());
        } catch (IllegalAccessException e) {
            LOGGER.warning(e.toString());
        } catch (UnsupportedLookAndFeelException e) {
            LOGGER.warning(e.toString());
        }
    }

    public static void setApplicationIcon(Window owner) {
        String appIconFileName = TextsDao.getFileName("img.app_icon");
        ImageIcon iconImage = ImageUtils.getProgramImage(appIconFileName);
        owner.setIconImage(iconImage.getImage());
    }
}
