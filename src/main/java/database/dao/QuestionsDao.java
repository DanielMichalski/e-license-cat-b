package database.dao;

import database.csv.CSVSpecialistQuestionDataProvider;
import database.csv.CSVStandardQuestionDataProvider;
import model.SpecialistQuestion;
import model.StandardQuestion;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import java.io.IOException;
import java.util.List;

/**
 * Author: Daniel
 * Date: 03.11.13
 */
public class QuestionsDao {
    public static List<StandardQuestion> get20StandardQuestion()
            throws IOException {
        return CSVStandardQuestionDataProvider.get20StandardQuestions();
    }

    public static List<StandardQuestion> getAllStandardQuestion()
            throws IOException, InvalidFormatException {
        return CSVStandardQuestionDataProvider.getAllStandardQuestions();
    }

    public static List<SpecialistQuestion> get12SpecialistQuestion()
            throws IOException, InvalidFormatException {
        return CSVSpecialistQuestionDataProvider.get12SpecialistQuestion();
    }

    public static List<SpecialistQuestion> getAllSpecialistQuestion()
            throws IOException, InvalidFormatException {
        return CSVSpecialistQuestionDataProvider.getAllSpecialistQuestions();
    }
}
