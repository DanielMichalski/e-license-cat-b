package test.main;

import database.csv.CSVModuleDataProvider;
import database.xls.XLSSpecialistQuestionDataProvider;
import database.xls.XLSStandardQuestionDataProvider;
import model.SpecialistQuestion;
import model.StandardQuestion;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import ui.exam_result.view.ExamResultFrame;

import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Author: Daniel
 * Date: 05.11.13
 */
public class MainTest {
    public static void main(String[] args) {
        //testExamResultFrame();
        //testdao();
        testCSV();
    }

    private static void testCSV() {
        try {
            CSVModuleDataProvider.getAllModules();
        } catch (IOException e) {
            e.printStackTrace();
        }
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
                System.out.println(i);
                i++;
            }


            List<SpecialistQuestion> allSpecialistQuestions =
                    XLSSpecialistQuestionDataProvider.getAllSpecialistQuestions();
            i = 1;
            for (SpecialistQuestion specialistQuestion : allSpecialistQuestions) {
                System.out.println(i);
                i++;
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        }
    }
}
