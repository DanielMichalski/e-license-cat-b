import view.dialogs.ShowInfoAboutExamDialog;
import view.main_menu.MainMenuFrame;

import javax.swing.*;
import java.awt.*;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

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
            mv.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            mv.setVisible(true);
            /*ShowInfoAboutExamDialog frame = new ShowInfoAboutExamDialog();
            frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            frame.setVisible(true);*/
            }
        });
    }
}
