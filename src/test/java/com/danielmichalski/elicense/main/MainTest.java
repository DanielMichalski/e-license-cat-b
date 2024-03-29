package com.danielmichalski.elicense.main;

import com.danielmichalski.elicense.database.xls.XLSSpecialistQuestionDataProvider;
import com.danielmichalski.elicense.database.xls.XLSStandardQuestionDataProvider;
import com.danielmichalski.elicense.model.SpecialistQuestion;
import com.danielmichalski.elicense.model.StandardQuestion;
import org.apache.log4j.Logger;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import com.danielmichalski.elicense.ui.exam_result.view.ExamResultFrame;
import com.danielmichalski.elicense.util.ApplicationUtils;

import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Author: Daniel
 */
public class MainTest {
    public static Logger logger = ApplicationUtils.getLogger(MainTest.class);

    public static void main(String[] args) {
        //testExamResultFrame();
        //testdao();
    }

    private static void testExamResultFrame() {
        ExamResultFrame frame = new ExamResultFrame(
                new ArrayList<StandardQuestion>(),
                new ArrayList<SpecialistQuestion>());
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
    }

    private static void testdao() {
        try {
            List<StandardQuestion> allStandardQuestions =
                    XLSStandardQuestionDataProvider.get20StandardQuestions();
            int i = 1;
            for (StandardQuestion standardQuestion : allStandardQuestions) {
                logger.info(i);
                i++;
            }


            List<SpecialistQuestion> allSpecialistQuestions =
                    XLSSpecialistQuestionDataProvider.getAllSpecialistQuestions();
            i = 1;
            for (SpecialistQuestion specialistQuestion : allSpecialistQuestions) {
                logger.info(i);
                i++;
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        }
    }
}
