package ui.preloader;

import media.images.IconUtils;
import ui.exam.view.ExamFrame;
import util.Const;

import javax.swing.*;
import java.awt.*;

/**
 * Author: Daniel
 * Date: 25.11.13.
 */
public class PreloaderFrame extends JFrame {

    public static final int WIDTH = 200;
    public static final int HEIGHT = 200;

    public PreloaderFrame() {
        setUpFrame();
        initializeComponents();

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                showExamForm();
                dispose();
            }
        });

        thread.start();


    }

    private void showExamForm() {
        ExamFrame examFrame = new ExamFrame();
        examFrame.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        examFrame.setVisible(true);
    }

    private void setUpFrame() {
        setLayout(null);
        setSize(new Dimension(WIDTH, HEIGHT));
        setLocationRelativeTo(null);
        getContentPane().setBackground(Color.gray);
        setUndecorated(true);
        setAlwaysOnTop(true);
    }

    private void initializeComponents() {
        ImageIcon preloaderIcon = IconUtils.getPreloaderIcon();
        JLabel imageLabel = new JLabel(preloaderIcon);
        imageLabel.setBounds(75, 75, 50, 50);

        JLabel textLbl = new JLabel("Ładowanie");
        textLbl.setBounds(70, 120, 100, 50);

        getContentPane().add(imageLabel);
        getContentPane().add(textLbl);
    }
}
