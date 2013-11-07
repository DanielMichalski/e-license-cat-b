package database;

import database.xls.XLSModuleDataProvider;
import database.xls.XLSSpecialistQuestionDataProvider;
import database.xls.XLSStandardQuestionDataProvider;
import model.SpecialistQuestion;
import model.StandardQuestion;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Author: Daniel
 * Date: 03.11.13
 */
public class QuestionsDao {
    public static List<StandardQuestion> get20StandardQuestion()
            throws IOException, InvalidFormatException {
        return XLSStandardQuestionDataProvider.get20StandardQuestions();
    }

    public static List<StandardQuestion> getAllStandardQuestion()
            throws IOException, InvalidFormatException {
        return XLSStandardQuestionDataProvider.getAllStandardQuestions();
    }

    public static List<SpecialistQuestion> get12SpecialistQuestion()
            throws IOException, InvalidFormatException {
        return XLSSpecialistQuestionDataProvider.get12SpecialistQuestions();
    }

    public static List<SpecialistQuestion> getAllSpecialistQuestion()
            throws IOException, InvalidFormatException {
        return XLSSpecialistQuestionDataProvider.getAllSpecialistQuestions();
    }
}
