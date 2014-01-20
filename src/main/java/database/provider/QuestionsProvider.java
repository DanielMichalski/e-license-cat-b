package database.provider;

import database.csv.CSVQuestionDataProvider;
import model.SpecialistQuestion;
import model.StandardQuestion;
import org.apache.log4j.Logger;
import util.ApplicationUtils;

import java.util.List;

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
