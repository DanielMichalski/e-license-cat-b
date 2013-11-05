package util;

import javax.swing.*;
import java.net.URL;

/**
 * Author: Daniel
 * Date: 03.11.13
 */
public class ImagesUtil {
    public static ImageIcon getMarkedBallIcon() {
        URL imageSrc = ImagesUtil.class.getResource("/program_images/point_was.png");
        return new ImageIcon(imageSrc);
    }

    public static ImageIcon getUnmarkedBallIcon() {
        URL imageSrc = ImagesUtil.class.getResource("/program_images/point_before.png");
        return new ImageIcon(imageSrc);
    }

    public static ImageIcon getAboutExamImage() {
        URL imageSrc = ImagesUtil.class.getResource("/program_images/wait_start.jpg");
        return new ImageIcon(imageSrc);
    }
}
