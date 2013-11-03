package util;

import java.awt.*;

/**
 * Author: dmichalski
 * Date: 01.11.13
 */
public class Const {
    public static interface Colors {
        Color backgroundColor = new Color(228, 250, 255);
        Color timerFontColor = new Color(200, 73, 46);

        Color markedBtnBgColor = new Color(124, 210, 255);
        Color unmarkedBtnBgColor = new Color(255, 255, 255);
    }

    public static interface Fonts {
        Font textsFont = new Font("SansSerif", Font.PLAIN, 16);
        Font timerFont = new Font("SansSerif", Font.PLAIN, 48);
    }

    public static interface Dimensions {
        Dimension btnSize = new Dimension(250, 60);
    }
}
