package test.main;

import ui.exam.view.ExamFrame;
import util.Utils;

import javax.swing.*;
import java.awt.*;

/**
 * Author: Daniel
 * Date: 04.11.13
 */
public class MainExam {
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                ExamFrame frame = new ExamFrame();
                Utils.setApplicationIcon(frame);
                frame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
                frame.setVisible(true);
            }
        });

    }
}
