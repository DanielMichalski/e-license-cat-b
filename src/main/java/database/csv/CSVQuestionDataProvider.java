package database.csv;

import com.csvreader.CsvReader;
import database.columns.QuestionColumnNames;
import database.dao.TextsDao;
import encrypt.Encrypter;
import model.*;

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
 */
public class CSVQuestionDataProvider {
    private static Logger LOGGER = Logger.getLogger(CSVModuleDataProvider.class.getName());

    private CsvReader csvReader;

    private List<StandardQuestion> standardQuestions;
    private List<SpecialistQuestion> specialistQuestions;

    public CSVQuestionDataProvider() {
        standardQuestions = new ArrayList<StandardQuestion>();
        specialistQuestions = new ArrayList<SpecialistQuestion>();

        long start = System.currentTimeMillis();
        readQuestions();
        long end = System.currentTimeMillis();
        double time = (double)(end-start) / 1000;
        System.out.println(String.format("Wczytywanie pytań: %.2f sec.", time));
    }

    private void readQuestions() {
        try {
            InputStream resourceAsStream = CSVQuestionDataProvider.class.getResourceAsStream("/csv/questions_enc");
            byte[] bytesArray = Encrypter.decryptFile(resourceAsStream);

            long start = System.currentTimeMillis();
            InputStream byteInputStream = new ByteArrayInputStream(bytesArray);
            csvReader = new CsvReader(byteInputStream, ';', Charset.forName("UTF-8"));
            long end = System.currentTimeMillis();
            double time = (double)(end-start) / 1000;
            System.out.println(String.format("Odszyfrowywanie: %.2f sec.", time));

            csvReader.readHeaders();

            while (csvReader.readRecord()) {
                if (csvReader.get(Q_QUESTION_TYPE).equals(TextsDao.getText("standardQuestion"))) {
                    readStandardQuestion();
                } else {
                    readSpecialistQuesion();
                }
            }
        } catch (IOException e) {
            LOGGER.warning(e.toString());
        } catch (IllegalArgumentException e) {
            LOGGER.warning(e.toString());
        }
    }

    private void readStandardQuestion() throws IOException {
        int points = Integer.parseInt(csvReader.get(QuestionColumnNames.Q_POINTS));
        String question = csvReader.get(Q_QUESTION);
        YesNoAnswer correctAnser = YesNoAnswer.valueOf(csvReader.get(Q_CORRECT_ANSWER));
        String module = csvReader.get(Q_MODULE);

        String mediaPath = null;
        if (!csvReader.get(Q_FIRST_MEDIA_PATH).equals("")) {
            mediaPath = csvReader.get(Q_FIRST_MEDIA_PATH);
        } else if (!csvReader.get(Q_SECOND_MEDIA_PATH).equals("")) {
            mediaPath = csvReader.get(Q_SECOND_MEDIA_PATH);
        }

        File file = new File("media" + File.separator + mediaPath + ".prod");
        if (!file.exists()) {
            return;
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

        standardQuestions.add(standardQuestion);
    }

    private void readSpecialistQuesion() throws IOException {
        int points = Integer.parseInt(csvReader.get(QuestionColumnNames.Q_POINTS));
        String question = csvReader.get(Q_QUESTION);
        ABCAnswer correctAnser = ABCAnswer.valueOf(csvReader.get(Q_CORRECT_ANSWER));
        String answerA = csvReader.get(Q_ANSWER_A);
        String answerB = csvReader.get(Q_ANSWER_B);
        String answerC = csvReader.get(Q_ANSWER_C);
        String module = csvReader.get(Q_MODULE);

        String mediaPath = null;
        if (!csvReader.get(Q_FIRST_MEDIA_PATH).equals("")) {
            mediaPath = csvReader.get(Q_FIRST_MEDIA_PATH);
        } else if (!csvReader.get(Q_SECOND_MEDIA_PATH).equals("")) {
            mediaPath = csvReader.get(Q_SECOND_MEDIA_PATH);
        }

        File file = new File("media" + File.separator + mediaPath + ".prod");
        if (!file.exists()) {
            return;
        }

        MediaType mediaType = MediaType.IMAGE;
        if (csvReader.get(Q_MEDIA_TYPE).equals("VIDEO")) {
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

        specialistQuestions.add(specialistQuestion);
    }

    public List<StandardQuestion> getAllStandardQuestions() {
        return standardQuestions;
    }

    public List<SpecialistQuestion> getAllSpecialistQuestions() {
        return specialistQuestions;
    }
}
