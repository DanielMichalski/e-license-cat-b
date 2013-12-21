package test.main;

import database.provider.QuestionsProvider;
import model.Module;
import model.SpecialistQuestion;
import model.StandardQuestion;
import ui.learning.view.LearningFrame;
import util.ApplicationUtils;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * Author: Daniel
 */
public class LearningTest {
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                ApplicationUtils.setNimbusLookAndFeel();

                QuestionsProvider instance = QuestionsProvider.getInstance();
                List<StandardQuestion> standardQuestionList = instance.getStanQuestionsByModule(new Module(10, "test"));
                List<SpecialistQuestion> specialistQuestions = instance.getSpecQuestionsByModule(new Module(10, "test"));

                LearningFrame learningFrame = new LearningFrame(
                        new Module(1, "Testowy"),
                        standardQuestionList,
                        specialistQuestions
                );
                learningFrame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
                learningFrame.setVisible(true);
            }
        });
    }
}
