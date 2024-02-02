package com.danielmichalski.elicense.main;

import com.danielmichalski.elicense.ui.exam.view.ExamFrame;
import com.danielmichalski.elicense.util.ApplicationUtils;
import com.danielmichalski.elicense.util.FileUtils;

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
