package database.xls;

import database.dao.TextsDao;
import model.ABCAnswer;
import model.SpecialistQuestion;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Author: Daniel
 * Date: 05.11.13
 */
public class XLSSpecialistQuestionDataProvider {
    public static List<SpecialistQuestion> getAllSpecialistQuestions()
            throws IOException, InvalidFormatException {

        List<SpecialistQuestion> specialistQuestionList =
                new ArrayList<SpecialistQuestion>();

        String questionPath = TextsDao.getFileName("xls.questions");
        InputStream resourceAsStream = XLSModuleDataProvider.class.getResourceAsStream("/xls/" + questionPath);

        Workbook exWorkBook = WorkbookFactory.create(resourceAsStream);

        Sheet sheet = exWorkBook.getSheetAt(0);

        for (Row tempRow : sheet) {
            if (tempRow.getCell(11) != null) {
                if (tempRow.getCell(11).getStringCellValue().equals("specjalistyczna")) {
                    SpecialistQuestion specialistQuestion =
                            XLSUtil.getSpecialistQuestionFromRow(tempRow);
                    specialistQuestionList.add(specialistQuestion);
                }
            }
        }

        return specialistQuestionList;
    }

    public static List<SpecialistQuestion> get12SpecialistQuestions()
            throws IOException, InvalidFormatException {

        List<SpecialistQuestion> list = getAllSpecialistQuestions();
        List<SpecialistQuestion> list12 = new ArrayList<SpecialistQuestion>();

        int i = 0;
        for (SpecialistQuestion specialistQuestion : list) {
            if (i == 12) {
                break;
            }
            list12.add(specialistQuestion);
            i++;
        }

        //TODO usunac po tescie
        ABCAnswer correctAnswer = list12.get(0).getCorrectAnswer();
        list12.get(0).setUserAnswer(correctAnswer);

        list12.get(1).setUserAnswer(ABCAnswer.B);

        return list12;
    }
}
