package util;

import database.dao.TextsDao;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.net.URL;

/**
 * Author: Daniel
 * Date: 03.11.13
 */
public class IconUtils {

    public static ImageIcon getMarkedBallIcon() {
        return getImageIcon(TextsDao.getFileName("img.point_was"));
    }

    public static ImageIcon getUnmarkedBallIcon() {
        return getImageIcon(TextsDao.getFileName("img.point_before"));
    }

    public static ImageIcon getGoodBallIcon() {
        return getImageIcon(TextsDao.getFileName("img.point_ok"));
    }

    public static ImageIcon getBadBallIcon() {
        return getImageIcon(TextsDao.getFileName("img.point_bad"));
    }

    public static ImageIcon getPreloaderIcon() {
        return getImageIcon(TextsDao.getFileName("img.preloader"));
    }

    public static ImageIcon getImageIcon(String fileName) {
        String iconsFolder = "/program_images/";
        URL imageSrc = IconUtils.class.getResource(iconsFolder + fileName);
        return scaleImageIcon(new ImageIcon(imageSrc));
    }

    private static ImageIcon scaleImageIcon(ImageIcon imageIcon) {
        Image image = imageIcon.getImage();

        Image scaledInstance = image.getScaledInstance(
                Const.Dimensions.BALL_ICON_SIZE.width,
                Const.Dimensions.BALL_ICON_SIZE.height,
                BufferedImage.SCALE_DEFAULT);

        return new ImageIcon(scaledInstance);
    }
}
