import database.provider.QuestionsProvider;
import ui.main_menu.view.MainMenuFrame;

import javax.swing.*;
import java.awt.*;

/**
 * Author: dmichalski
 * Date: 01.11.13
 */
public class RunApplication {
    public static void main(String[] args) {
        readQuestions();
        startApp();
    }

    private static void readQuestions() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                QuestionsProvider provider = QuestionsProvider.getInstance();
            }
        });

        thread.start();
    }

    private static void startApp() {
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

