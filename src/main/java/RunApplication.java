import ui.loading_new.LoadingFrame;

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
                new LoadingFrame();
            }
        });
    }
}

