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
        Color positivePointsResultColor = new Color(44, 175, 99);
        Color negativePointsResultColor = new Color(200, 73, 46);
        Color howManyPointsBorderColor = new Color(128, 125, 122);
    }

    public static interface Fonts {
        Font textsFont = new Font("Arial", Font.PLAIN, 16);
        Font timerFont = new Font("Arial", Font.PLAIN, 48);
        Font resultPointFont = new Font("Arial", Font.PLAIN, 42);
        Font btnsYesNoFont = new Font("Arial", Font.PLAIN, 16);
        Font btnsABCFont = new Font("Arial", Font.PLAIN, 14);
        Font btnConfirmFont = new Font("Arial", Font.PLAIN, 14);
    }

    public static interface Dimensions {
        Dimension examYesNoBtnSize = new Dimension(320, 50);
        Dimension ABCBtnsSize = new Dimension(250, 50);
        Dimension examTimeLblSize = new Dimension(230, 50);
        Dimension examConfirmBtnSize = new Dimension(230, 50);
        Dimension mainMenubtnSize = new Dimension(280, 86);
    }
}
