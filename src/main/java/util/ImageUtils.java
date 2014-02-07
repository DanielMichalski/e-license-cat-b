package util;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

/**
 * Author: Daniel
 */
public class ImageUtils {

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
                Const.Dimensions.IMAGE_AND_VIDEO_SIZE.width,
                Const.Dimensions.IMAGE_AND_VIDEO_SIZE.height,
                BufferedImage.SCALE_DEFAULT);

        return new ImageIcon(scaledInstance);
    }
}
