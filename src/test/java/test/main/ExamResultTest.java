package test.main;

import database.dao.QuestionsDao;
import model.SpecialistQuestion;
import model.StandardQuestion;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import ui.exam_result.view.ExamResultFrame;
import util.ApplicationUtils;

import java.io.IOException;
import java.util.List;

import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

/**
 * Author: Daniel
 * Date: 09.11.13
 */
public class ExamResultTest {
    public static void main(String[] args)
            throws IOException, InvalidFormatException {

        ApplicationUtils.setNimbusLookAndFeel();

        List<StandardQuestion> standardQuestions
                = QuestionsDao.get20StandardQuestion();

        List<SpecialistQuestion> specialistQuestions =
                QuestionsDao.get12SpecialistQuestion();

        ExamResultFrame frame = new ExamResultFrame(standardQuestions, specialistQuestions);
        frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        frame.setVisible(true);
    }
}
