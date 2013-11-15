package test.main;

import database.csv.CSVModuleDataProvider;
import database.csv.CSVSpecialistQuestionDataProvider;
import database.csv.CSVStandardQuestionDataProvider;
import encrypt.Encrypter;
import model.Module;
import model.SpecialistQuestion;
import model.StandardQuestion;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import java.io.IOException;
import java.util.List;

/**
 * Author: Daniel
 * Date: 12.11.13.
 */
public class EncryptTest {
    public static void main(String[] args) throws IOException, InvalidFormatException {

        List<StandardQuestion> allStandardQuestions =
                CSVStandardQuestionDataProvider.getAllStandardQuestions();
        System.out.println(allStandardQuestions.size() + " " + allStandardQuestions);

        List<SpecialistQuestion> specialistQuestions =
                CSVSpecialistQuestionDataProvider.getAllSpecialistQuestions();
        System.out.println(specialistQuestions.size() + " " + specialistQuestions);

        List<Module> allModules = CSVModuleDataProvider.getAllModules();
        System.out.println(allModules.size() + " " + allModules);

        Encrypter.enryptChoosenFile();
    }
}
