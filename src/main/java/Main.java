import ui.main_menu.view.MainMenuFrame;

import javax.swing.*;
import java.awt.*;

/**
 * Author: dmichalski
 * Date: 01.11.13
 */
public class Main {
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                MainMenuFrame mv = new MainMenuFrame();
                mv.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
                mv.setVisible(true);

                // test github
            }
        });

    }
}

