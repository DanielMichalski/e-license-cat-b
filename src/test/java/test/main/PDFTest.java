package test.main;

import database.csv.CSVModuleDataProvider;
import database.csv.CSVSpecialistQuestionDataProvider;
import database.csv.CSVStandardQuestionDataProvider;
import model.SpecialistQuestion;
import model.StandardQuestion;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import pdf.PDFGenerator;

import java.io.IOException;
import java.util.List;

/**
 * Author: Daniel
 * Date: 25.11.13.
 */
public class PDFTest {
    public static void main(String[] args) throws IOException, InvalidFormatException {
        List<StandardQuestion> standardQuestions =
                CSVStandardQuestionDataProvider.get20StandardQuestions();

        List<SpecialistQuestion> specialistQuestion =
                CSVSpecialistQuestionDataProvider.get12SpecialistQuestion();

        PDFGenerator pdfGenerator = new PDFGenerator(standardQuestions, specialistQuestion);
    }
}
