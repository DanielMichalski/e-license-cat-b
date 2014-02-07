package util;

import database.dao.TextsDao;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.net.URL;

/**
 * Author: Daniel
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

    public static ImageIcon getLoadingIcon() {
        String iconsFolder = "/program_images/";
        URL imageSrc = IconUtils.class.getResource(iconsFolder + TextsDao.getFileName("img.loading"));
        return new ImageIcon(imageSrc);
    }

    public static ImageIcon getLogo() {
        String logo = TextsDao.getFileName("logo");

        String iconsFolder = "/program_images/";
        URL imageSrc = IconUtils.class.getResource(iconsFolder + logo);
        return new ImageIcon(imageSrc);
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
