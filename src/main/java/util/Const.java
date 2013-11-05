package util;

import java.awt.*;

/**
 * Author: dmichalski
 * Date: 01.11.13
 */
public class Const {
    public static interface Colors {
        Color mainMenubackgroundColor = new Color(228, 250, 255);
        Color examBackgroundColor = new Color(240, 255, 255);
        Color timerFontColor = new Color(200, 73, 46);
        Color markedBtnBgColor = new Color(124, 210, 255);
        Color unmarkedBtnBgColor = new Color(255, 255, 255);
    }

    public static interface Fonts {
        Font textsFont = new Font("Arial", Font.PLAIN, 16);
        Font timerFont = new Font("Arial", Font.PLAIN, 48);
        Font btnsYesNoFont = new Font("Arial", Font.PLAIN,18);
        Font btnsABCFont = new Font("Arial", Font.PLAIN, 18);
        Font btnConfirmFont = new Font("Arial", Font.PLAIN, 18);
    }

    public static interface Dimensions {
        Dimension examYesNoBtnSize = new Dimension(320, 50);
        Dimension ABCBtnsSize = new Dimension(450, 50);
        Dimension examConfirmBtnSize = new Dimension(230, 50);
        Dimension mainMenubtnSize = new Dimension(280, 85);
    }
}
