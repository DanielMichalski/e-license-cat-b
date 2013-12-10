package database.csv;

import com.csvreader.CsvReader;
import database.columns.QuestionColumnNames;
import database.dao.TextsDao;
import encrypt.Encrypter;
import model.MediaType;
import model.Module;
import model.StandardQuestion;
import model.YesNoAnswer;

import java.io.ByteArrayInputStream;
import java.io.File;
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
public class CSVStandardQuestionDataProvider {
    private static Logger LOGGER = Logger.getLogger(CSVModuleDataProvider.class.getName());

    public static List<StandardQuestion> getAllStandardQuestions()
            throws IOException {

        List<StandardQuestion> standardQuestionList =
                new ArrayList<StandardQuestion>();

        InputStream resourceAsStream = CSVStandardQuestionDataProvider.class.getResourceAsStream("/csv/questions_enc");

        byte[] bytesArray = Encrypter.decryptFile(resourceAsStream);

        InputStream byteInputStream = new ByteArrayInputStream(bytesArray);
        CsvReader csvReader = new CsvReader(byteInputStream, ';', Charset.forName("UTF-8"));

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

                    File file = new File("media" + File.separator + mediaPath);
                    if (!file.exists()) {
                        continue;
                    }

                    MediaType mediaType = MediaType.IMAGE;
                    if (csvReader.get(Q_MEDIA_TYPE).equals("VIDEO") ) {
                        mediaType = MediaType.VIDEO;
                    }

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
                LOGGER.warning(e.toString());
            } catch (IllegalArgumentException e) {
                LOGGER.warning(e.toString());
            }
        }

        return standardQuestionList;
    }

    public static List<StandardQuestion> get20StandardQuestions()
            throws IOException {

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
