package util;

import javax.swing.*;
import java.net.URL;

/**
 * Author: Daniel
 * Date: 03.11.13
 */
public class ImagesUtil {
    public static ImageIcon getMarkedBallIcon() {
        URL imageSrc = ImagesUtil.class.getResource("/images/marked_ball.png");
        return new ImageIcon(imageSrc);
    }

    public static ImageIcon getUnmarkedBallIcon() {
        URL imageSrc = ImagesUtil.class.getResource("/images/unmarked_ball.png");
        return new ImageIcon(imageSrc);
    }
}
