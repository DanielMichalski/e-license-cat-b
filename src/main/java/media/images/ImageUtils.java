package media.images;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

/**
 * Author: Daniel
 * Date: 10.11.13.
 */
public class ImageUtils {
    public static final int IMAGE_WIDTH = 640;
    public static final int IMAGE_HEIGHT = 360;

    public static ImageIcon getQuestionImage(String imageFileName)
            throws IOException {
        String questionImagesFolder = "/images/";
        ImageIcon imageIcon = getImageIconFromFilePath(questionImagesFolder + imageFileName);
        return scaleImageIcon(imageIcon);
    }

    public static ImageIcon getProgramImage(String imageFileName) {
        String programImagesFolder = "/program_images/";
        return getImageIconFromFilePath(programImagesFolder + imageFileName);
    }

    private static ImageIcon getImageIconFromFilePath(String imageFilePath) {

        Class<ImageUtils> imageUtilsClass = ImageUtils.class;
        URL imageUrl = imageUtilsClass.getResource(imageFilePath);

        return new ImageIcon(imageUrl);
    }

    private static ImageIcon scaleImageIcon(ImageIcon imageIcon) {
        Image image = imageIcon.getImage();

        Image scaledInstance = image.getScaledInstance(
                IMAGE_WIDTH,
                IMAGE_HEIGHT,
                BufferedImage.SCALE_DEFAULT);

        return new ImageIcon(scaledInstance);
    }
}
