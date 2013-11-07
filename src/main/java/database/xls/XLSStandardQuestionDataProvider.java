package database.xls;

import database.TextsDao;
import model.Module;
import model.StandardQuestion;
import model.enums.YesOrNoAnswer;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Author: Daniel
 * Date: 05.11.13
 */
public class XLSStandardQuestionDataProvider {
    public static List<StandardQuestion> getAllStandardQuestions()
            throws IOException, InvalidFormatException {

        List<StandardQuestion> standardQuestionList
                = new ArrayList<StandardQuestion>();

        String questionPath = TextsDao.getPath("questions_path");
        InputStream resourceAsStream = XLSModuleDataProvider.class.getResourceAsStream(questionPath);

        Workbook exWorkBook = WorkbookFactory.create(resourceAsStream);

        Sheet sheet = exWorkBook.getSheetAt(0);

        for (Row tempRow : sheet) {
            if (tempRow.getCell(11) != null) {
                if (tempRow.getCell(11).getStringCellValue().equals("podstawowa")) {
                    StandardQuestion standardQuestion =
                            XLSUtil.getStandardQuestionFromRow(tempRow);
                    standardQuestionList.add(standardQuestion);
                }
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
