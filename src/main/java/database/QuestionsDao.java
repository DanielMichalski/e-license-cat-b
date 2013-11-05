package database;

import database.xls.XLSModuleDataProvider;
import database.xls.XLSSpecialistQuestionDataProvider;
import database.xls.XLSStandardQuestionDataProvider;
import model.SpecialistQuestion;
import model.StandardQuestion;

import java.util.Map;

/**
 * Author: Daniel
 * Date: 03.11.13
 */
public class QuestionsDao {
    /*public static Map<Integer, StandardQuestion> getAllStandardQuestion() {
        return XLSStandardQuestionDataProvider.getAllStandardQuestions();
    }*/

    public static Map<Integer, StandardQuestion> get20StandardQuestion() {
        return XLSStandardQuestionDataProvider.get20StandardQuestions();
    }

    public static Map<Integer, SpecialistQuestion> getAllSpecialistQuestion() {
        return XLSSpecialistQuestionDataProvider.getAllSpecialistQuestions();
    }

    public static Map<Integer, SpecialistQuestion> get12SpecialistQuestion() {
        return XLSSpecialistQuestionDataProvider.get12SpecialistQuestions();
    }
}
