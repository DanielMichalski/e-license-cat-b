import ui.main_menu.view.MainMenuFrame;

import javax.swing.*;
import java.awt.*;

/**
 * Author: dmichalski
 * Date: 01.11.13
 */
public class RunApplication {
    public static void main(String[] args) {

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 1; i <= 100; i++) {
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    System.out.println(i + " %");
                }
            }
        });

        thread.start();

        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                MainMenuFrame mv = new MainMenuFrame();
                mv.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
                mv.setVisible(true);
            }
        });

    }
}

