package test.main;

import model.Module;
import model.SpecialistQuestion;
import model.StandardQuestion;
import ui.learning.view.LearningFrame;
import util.ApplicationUtils;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Author: Daniel
 */
public class LearningTest {
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                ApplicationUtils.setNimbusLookAndFeel();

                LearningFrame learningFrame = new LearningFrame(
                        new Module(1, "Testowy"), new ArrayList<StandardQuestion>(),
                        new ArrayList<SpecialistQuestion>()
                );
                learningFrame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
                learningFrame.setVisible(true);
            }
        });
    }
}
