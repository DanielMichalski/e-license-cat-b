package test.main;

import model.Module;
import ui.learning.view.LearningFrame;
import util.ApplicationUtils;
import util.FileUtils;

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
