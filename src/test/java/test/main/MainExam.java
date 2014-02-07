package test.main;

import ui.exam.view.ExamFrame;
import util.ApplicationUtils;
import util.FileUtils;

import javax.swing.*;
import java.awt.*;

/**
 * Author: Daniel
 */
public class MainExam {
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                FileUtils.loadVLCJNativeLibraries();

                ExamFrame frame = new ExamFrame(null);
                ApplicationUtils.setApplicationIcon(frame);
                frame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
            }
        });
    }
}
