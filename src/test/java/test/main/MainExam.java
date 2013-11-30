package test.main;

import ui.exam.view.ExamFrame;
import util.ApplicationUtils;

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
                ExamFrame frame = new ExamFrame(null);
                ApplicationUtils.setApplicationIcon(frame);
                frame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
            }
        });
    }
}
