import ui.main_menu.view.MainMenuFrame;
import util.ApplicationUtils;

import javax.swing.*;
import java.awt.*;

/**
 * Author: dmichalski
 * Date: 01.11.13
 */
public class RunApplication {
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                checkResolution();

                MainMenuFrame mv = new MainMenuFrame();
                mv.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
                mv.setVisible(true);
            }
        });

    }

    private static void checkResolution() {
        Dimension resolution = ApplicationUtils.getScreenResolution();
        String text = String.format("Rodzielczość ekranu: %d x %d", resolution.width, resolution.height);
        System.out.println(text);
    }
}

