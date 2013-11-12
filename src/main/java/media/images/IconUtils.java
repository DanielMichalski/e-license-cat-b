package media.images;

import database.dao.TextsDao;

import javax.swing.*;
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

    public static ImageIcon getImageIcon(String fileName) {
        String iconsFolder = "/program_images/";
        URL imageSrc = IconUtils.class.getResource(iconsFolder + fileName);
        return new ImageIcon(imageSrc);
    }
}
