package util;

import java.awt.*;

/**
 * Author: dmichalski
 * Date: 01.11.13
 */
public class Const {
    public static interface Colors {
        Color MAIN_MENU_BACKGROUND_COLOR = new Color(228, 250, 255);
        Color EXAM_BACKGROUND_COLOR = new Color(240, 255, 255);
        Color TIMER_FONT_COLOR = new Color(192, 27, 14);
        Color POSITIVE_RESULT_COLOR = new Color(83, 170, 12);
        Color NEGATIVE_RESULT_COLOR = new Color(192, 27, 14);
        Color HOW_MANY_POINTS_BORDER_COLOR = new Color(128, 125, 122);
    }

    public static interface Fonts {
        Font TEXTS_FONT = new Font("Arial", Font.PLAIN, 16);
        Font TIMER_FONT = new Font("Arial", Font.PLAIN, 48);
        Font IS_PASSED_OR_NO_LBL_FONT = new Font("Arial", Font.PLAIN, 48);
        Font RESULT_POINT_FONT = new Font("Arial", Font.PLAIN, 30);
        Font BTNS_YES_NO_FONT = new Font("Arial", Font.PLAIN, 16);
        Font BTNS_ABC_FONT = new Font("Arial", Font.PLAIN, 16);
        Font BTN_CONFIRM_FONT = new Font("Arial", Font.PLAIN, 14);
        Font BTN_CLOSE_FONT = new Font("Arial", Font.PLAIN, 14);
        Font BTN_PRINT_FONT = new Font("Arial", Font.PLAIN, 14);
    }

    public static interface Dimensions {
        Dimension EXAM_YES_NO_BTN_SIZE = new Dimension(230, 50);
        Dimension ABC_BTNS_SIZE = new Dimension(230, 50);
        Dimension EXAM_TIME_LBL_SIZE = new Dimension(230, 50);
        Dimension RESULT_POINT = new Dimension(230, 50);
        Dimension IS_PASSED_OR_NO_LBL = new Dimension(230, 50);
        Dimension EXAM_CONFIRM_BTN_SIZE = new Dimension(230, 50);
        Dimension EXAM_CLOSE_BTN_SIZE = new Dimension(230, 35);
        Dimension EXAM_PRINT_BTN_SIZE = new Dimension(230, 35);
        Dimension HOW_MANY_POINTS_LBL = new Dimension(230, 50);
        Dimension MAIN_MENUBTN_SIZE = new Dimension(280, 86);
        Dimension IMAGE_SIZE = new Dimension(640, 360);
        Dimension VIDEO_SIZE = new Dimension(640, 360);
        Dimension BALL_ICON_SIZE = new Dimension(28, 29);

    }
}
