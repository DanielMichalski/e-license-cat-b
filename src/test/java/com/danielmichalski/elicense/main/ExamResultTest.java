package com.danielmichalski.elicense.main;

import com.danielmichalski.elicense.database.dao.QuestionsDao;
import com.danielmichalski.elicense.database.provider.QuestionsProvider;
import com.danielmichalski.elicense.model.SpecialistQuestion;
import com.danielmichalski.elicense.model.StandardQuestion;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import com.danielmichalski.elicense.ui.exam_result.view.ExamResultFrame;
import com.danielmichalski.elicense.util.ApplicationUtils;
import com.danielmichalski.elicense.util.FileUtils;

import java.io.IOException;
import java.util.List;

import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

/**
 * Author: Daniel
 */
public class ExamResultTest {
    public static void main(String[] args)
            throws IOException, InvalidFormatException {

        FileUtils.loadVLCJNativeLibraries();
        ApplicationUtils.setNimbusLookAndFeel();

        QuestionsProvider questionsProvider =
                QuestionsProvider.getInstance();

        List<StandardQuestion> standardQuestions
                = QuestionsDao.getStandard20Questions();

        List<SpecialistQuestion> specialistQuestions =
                QuestionsDao.getSpecialist12Questions();

        ExamResultFrame frame = new ExamResultFrame(standardQuestions, specialistQuestions);
        frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        frame.setVisible(true);
    }
}
