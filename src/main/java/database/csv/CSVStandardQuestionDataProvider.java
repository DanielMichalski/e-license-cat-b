package database.csv;

import com.csvreader.CsvReader;
import database.columns.QuestionColumnNames;
import database.dao.TextsDao;
import encrypt.Encrypter;
import model.MediaType;
import model.Module;
import model.StandardQuestion;
import model.YesNoAnswer;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static database.columns.QuestionColumnNames.*;

/**
 * Author: Daniel
 * Date: 12.11.13.
 */
public class CSVStandardQuestionDataProvider {
    public static List<StandardQuestion> getAllStandardQuestions()
            throws IOException, InvalidFormatException {

        List<StandardQuestion> standardQuestionList
                = new ArrayList<StandardQuestion>();

        Path questionsPath = Paths.get(TextsDao.getFileName("csv.questions"));
        byte[] bytesArray = Encrypter.decryptFile(questionsPath, null, false);

        InputStream byteInputStream = new ByteArrayInputStream(bytesArray);
        CsvReader csvReader = new CsvReader(byteInputStream, ';', Charset.defaultCharset());

        csvReader.readHeaders();

        while (csvReader.readRecord()) {
            try {
                if (csvReader.get(Q_QUESTION_TYPE).equals(TextsDao.getText("standardQuestion"))) {
                    int points = Integer.parseInt(csvReader.get(QuestionColumnNames.Q_POINTS));
                    String question = csvReader.get(Q_QUESTION);
                    YesNoAnswer correctAnser = YesNoAnswer.valueOf(csvReader.get(Q_CORRECT_ANSWER));
                    int moduleId = Integer.parseInt(csvReader.get(Q_MODULE));
                    Module module = CSVModuleDataProvider.getModule(moduleId);

                    String mediaPath = null;
                    if (!csvReader.get(Q_FIRST_MEDIA_PATH).equals("")) {
                        mediaPath = csvReader.get(Q_FIRST_MEDIA_PATH);
                    } else if (!csvReader.get(Q_SECOND_MEDIA_PATH).equals("")) {
                        mediaPath = csvReader.get(Q_SECOND_MEDIA_PATH);
                    }

                    MediaType mediaType = MediaType.valueOf(csvReader.get(Q_MEDIA_TYPE));

                    StandardQuestion standardQuestion = new StandardQuestion(
                            points,
                            question,
                            null,
                            correctAnser,
                            module,
                            mediaPath,
                            mediaType
                    );

                    standardQuestionList.add(standardQuestion);

                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            }
        }

        return standardQuestionList;
    }

    public static List<StandardQuestion> get20StandardQuestions()
            throws IOException, InvalidFormatException {

        List<StandardQuestion> list = getAllStandardQuestions();
        List<StandardQuestion> list20 = new ArrayList<StandardQuestion>();

        int i = 0;
        for (StandardQuestion standardQuestion : list) {
            if (i == 20) {
                break;
            }
            list20.add(standardQuestion);
            i++;
        }

        return list20;
    }
}
