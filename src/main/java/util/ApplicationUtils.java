package util;

import database.dao.TextsDao;
import media.images.ImageUtils;

import javax.swing.*;
import java.awt.*;

/**
 * Author: Daniel
 * Date: 05.11.13
 */
public class ApplicationUtils {
    public static void setSystemLookAndFeel() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException ignored) {
        } catch (InstantiationException ignored) {
        } catch (IllegalAccessException ignored) {
        } catch (UnsupportedLookAndFeelException ignored) {
        }
    }

    public static void setNimbusLookAndFeel() {
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (ClassNotFoundException ignored) {
        } catch (InstantiationException ignored) {
        } catch (IllegalAccessException ignored) {
        } catch (UnsupportedLookAndFeelException ignored) {
        }
    }

    public static void setApplicationIcon(Window owner) {
        String appIconFileName = TextsDao.getFileName("img.app_icon");
        ImageIcon iconImage = ImageUtils.getProgramImage(appIconFileName);
        owner.setIconImage(iconImage.getImage());
    }

    public static Dimension getScreenResolution() {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        return toolkit.getScreenSize();
    }
}
