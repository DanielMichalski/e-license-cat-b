package com.danielmichalski.elicense.database.dao;

import com.danielmichalski.elicense.database.provider.ExamQuestionProvider;
import com.danielmichalski.elicense.database.provider.QuestionsProvider;
import com.danielmichalski.elicense.model.SpecialistQuestion;
import com.danielmichalski.elicense.model.StandardQuestion;

import java.util.List;

/**
 * Author: Daniel
 */
public class QuestionsDao {
    public static List<StandardQuestion> getAllStandardQuestions() {
        QuestionsProvider questionsProvider =
                QuestionsProvider.getInstance();

        return questionsProvider.getAllStQuestions();
    }

    public static List<StandardQuestion> getStandard20Questions() {
        ExamQuestionProvider examQuestionProvider =
                ExamQuestionProvider.getInstance();

        return examQuestionProvider.getStandard20Questions();
    }

    public static List<SpecialistQuestion> getAllSpecialistQuestions() {
        QuestionsProvider questionsProvider =
                QuestionsProvider.getInstance();

        return questionsProvider.getAllSpQuestions();
    }

    public static List<SpecialistQuestion> getSpecialist12Questions() {
        ExamQuestionProvider examQuestionProvider =
                ExamQuestionProvider.getInstance();

        return examQuestionProvider.getSpecialist12Questions();
    }
}
