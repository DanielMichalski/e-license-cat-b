package database.dao;

import database.provider.QuestionsProvider;
import model.SpecialistQuestion;
import model.StandardQuestion;

import java.util.List;

/**
 * Author: Daniel
 */
public class QuestionsDao {
    public static List<StandardQuestion> getAllStandardQuestions() {
        QuestionsProvider questionsProvider =
                QuestionsProvider.getInstance();

        return questionsProvider.getAllStandardQuestionsForExam();
    }

    public static List<StandardQuestion> getStandard20Questions() {

        QuestionsProvider questionsProvider =
                QuestionsProvider.getInstance();

        return questionsProvider.getStandard20Questions();
    }

    public static List<SpecialistQuestion> getAllSpecialistQuestions() {
        QuestionsProvider questionsProvider =
                QuestionsProvider.getInstance();

        return questionsProvider.getAllSpecialistQuestionsForExam();
    }

    public static List<SpecialistQuestion> getSpecialist12Questions() {

        QuestionsProvider questionsProvider =
                QuestionsProvider.getInstance();

        return questionsProvider.getSpecialist12Questions();
    }
}
