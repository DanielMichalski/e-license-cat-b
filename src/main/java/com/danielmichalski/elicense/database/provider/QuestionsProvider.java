package com.danielmichalski.elicense.database.provider;

import com.danielmichalski.elicense.database.csv.CSVQuestionDataProvider;
import com.danielmichalski.elicense.model.SpecialistQuestion;
import com.danielmichalski.elicense.model.StandardQuestion;
import com.danielmichalski.elicense.util.ApplicationUtils;
import java.util.List;
import org.apache.log4j.Logger;

/**
 * Author: Daniel
 */
public class QuestionsProvider {
    public Logger logger = ApplicationUtils.getLogger(QuestionsProvider.class);

    private static QuestionsProvider ourInstance = new QuestionsProvider();

    List<StandardQuestion> allStQuestions;
    List<SpecialistQuestion> allSpQuestions;


    public static QuestionsProvider getInstance() {
        return ourInstance;
    }


    private QuestionsProvider() {

        CSVQuestionDataProvider csvQuestionDataProvider =
                new CSVQuestionDataProvider();

        allStQuestions =
                csvQuestionDataProvider.getAllStandardQuestions();
        allSpQuestions =
                csvQuestionDataProvider.getAllSpecialistQuestions();
    }


    public List<StandardQuestion> getAllStQuestions() {
        return allStQuestions;
    }

    public List<SpecialistQuestion> getAllSpQuestions() {
        return allSpQuestions;
    }
}
