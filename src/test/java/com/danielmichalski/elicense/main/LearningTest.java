package com.danielmichalski.elicense.main;

import com.danielmichalski.elicense.model.Module;
import com.danielmichalski.elicense.ui.learning.view.LearningFrame;
import com.danielmichalski.elicense.util.ApplicationUtils;
import com.danielmichalski.elicense.util.FileUtils;

import javax.swing.*;
import java.awt.*;

/**
 * Author: Daniel
 */
public class LearningTest {
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                FileUtils.loadVLCJNativeLibraries();
                ApplicationUtils.setNimbusLookAndFeel();

                LearningFrame learningFrame = new LearningFrame(new Module(1, "Testowy"));
                learningFrame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
                learningFrame.setVisible(true);
            }
        });
    }
}
