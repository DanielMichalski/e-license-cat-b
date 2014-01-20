package database.provider;

import model.Module;
import model.SpecialistQuestion;
import model.StandardQuestion;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: Daniel
 */
public class LearningQuestionProvider {
    List<StandardQuestion> allStQuestions;
    List<SpecialistQuestion> allSpQuestions;

    private static LearningQuestionProvider ourInstance = new LearningQuestionProvider();

    private LearningQuestionProvider() {
        QuestionsProvider provider = QuestionsProvider.getInstance();

        this.allStQuestions = provider.getAllStQuestions();
        this.allSpQuestions = provider.getAllSpQuestions();
    }

    public List<StandardQuestion> getStanQuestionsByModule(Module module) {
        List<StandardQuestion> questions = new ArrayList<StandardQuestion>();

        for (StandardQuestion standardQuestion : allStQuestions) {
            if (standardQuestion.getModule().equalsIgnoreCase(String.valueOf(module.getId()))) {
                questions.add(standardQuestion);
            }
        }

        return questions;
    }

    public List<SpecialistQuestion> getSpecQuestionsByModule(Module module) {
        List<SpecialistQuestion> questions = new ArrayList<SpecialistQuestion>();

        for (SpecialistQuestion specialistQuestion : allSpQuestions) {
            if (specialistQuestion.getModule().equalsIgnoreCase(String.valueOf(module.getId()))) {
                questions.add(specialistQuestion);
            }
        }

        return questions;
    }

    public static LearningQuestionProvider getInstance() {
        return ourInstance;
    }
}
