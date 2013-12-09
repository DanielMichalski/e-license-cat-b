package database.csv;

import com.csvreader.CsvReader;
import database.columns.QuestionColumnNames;
import database.dao.TextsDao;
import encrypt.Encrypter;
import model.ABCAnswer;
import model.MediaType;
import model.Module;
import model.SpecialistQuestion;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import static database.columns.QuestionColumnNames.*;

/**
 * Author: Daniel
 * Date: 12.11.13.
 */
public class CSVSpecialistQuestionDataProvider {
    private static Logger LOGGER = Logger.getLogger(CSVModuleDataProvider.class.getName());

    public static List<SpecialistQuestion> getAllSpecialistQuestions()
            throws IOException, InvalidFormatException {

        List<SpecialistQuestion> specialistQuestionList
                = new ArrayList<SpecialistQuestion>();

        InputStream resourceAsStream = CSVSpecialistQuestionDataProvider.class.getResourceAsStream("/csv/questions_enc");
        byte[] bytesArray = Encrypter.decryptFile(resourceAsStream);

        InputStream byteInputStream = new ByteArrayInputStream(bytesArray);
        CsvReader csvReader = new CsvReader(byteInputStream, ';', Charset.forName("UTF-8"));

        csvReader.readHeaders();

        while (csvReader.readRecord()) {
            try {
                if (csvReader.get(Q_QUESTION_TYPE).equals(TextsDao.getText("specialistQuestion"))) {
                    int points = Integer.parseInt(csvReader.get(QuestionColumnNames.Q_POINTS));
                    String question = csvReader.get(Q_QUESTION);
                    ABCAnswer correctAnser = ABCAnswer.valueOf(csvReader.get(Q_CORRECT_ANSWER));
                    String answerA = csvReader.get(Q_ANSWER_A);
                    String answerB = csvReader.get(Q_ANSWER_B);
                    String answerC = csvReader.get(Q_ANSWER_C);
                    int moduleId = Integer.parseInt(csvReader.get(Q_MODULE));
                    Module module = CSVModuleDataProvider.getModule(moduleId);

                    String mediaPath = null;
                    if (!csvReader.get(Q_FIRST_MEDIA_PATH).equals("")) {
                        mediaPath = csvReader.get(Q_FIRST_MEDIA_PATH);
                    } else if (!csvReader.get(Q_SECOND_MEDIA_PATH).equals("")) {
                        mediaPath = csvReader.get(Q_SECOND_MEDIA_PATH);
                    }

                    MediaType mediaType = MediaType.IMAGE;
                    if (csvReader.get(Q_MEDIA_TYPE).equals("VIDEO") ) {
                        mediaType = MediaType.VIDEO;
                    }

                    SpecialistQuestion specialistQuestion = new SpecialistQuestion(
                            points,
                            question,
                            answerA,
                            answerB,
                            answerC,
                            null,
                            correctAnser,
                            module,
                            mediaPath,
                            mediaType
                    );

                    specialistQuestionList.add(specialistQuestion);
                }
            } catch (IOException e) {
                LOGGER.warning(e.toString());
            } catch (IllegalArgumentException e) {
                LOGGER.warning(e.toString());
            }
        }

        return specialistQuestionList;
    }

    public static List<SpecialistQuestion> get12SpecialistQuestion()
            throws IOException, InvalidFormatException {

        List<SpecialistQuestion> list = getAllSpecialistQuestions();
        List<SpecialistQuestion> list20 = new ArrayList<SpecialistQuestion>();

        int i = 0;
        for (SpecialistQuestion specialistQuestion : list) {
            if (i == 12) {
                break;
            }
            list20.add(specialistQuestion);
            i++;
        }

        return list20;
    }
}
