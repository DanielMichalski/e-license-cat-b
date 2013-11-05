package database.xls;

import database.TextsDao;
import model.Module;
import model.StandardQuestion;
import model.enums.YesOrNoAnswer;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * Author: Daniel
 * Date: 05.11.13
 */
public class XLSStandardQuestionDataProvider {
    /*public static Map<Integer, StandardQuestion> getAllStandardQuestions() throws IOException, InvalidFormatException {
        Map<Integer, StandardQuestion> standardQuestionMap
                = new HashMap<Integer, StandardQuestion>();

        String questionPath = TextsDao.getPath("questions_path");
        InputStream resourceAsStream = XLSModuleDataProvider.class.getResourceAsStream(questionPath);

        Workbook exWorkBook = WorkbookFactory.create(resourceAsStream);

        Sheet sheet = exWorkBook.getSheetAt(0);

        int rowNum = 1;
        for (Row tempRow : sheet) {
            if (rowNum == 1) {
                continue;
            }

            StandardQuestion standardQuestion =
                    XLSUtil.;

            standardQuestionMap.put(rowNum, standardQuestion);
            rowNum++;
        }

        return standardQuestionMap;
    }*/

    public static Map<Integer, StandardQuestion> get20StandardQuestions() {
        Map<Integer, StandardQuestion> map = new HashMap<Integer, StandardQuestion>();

        for (int i = 1; i <= 20; i++) {
            StandardQuestion standardQuestion = new StandardQuestion(
                    i,
                    i + ". Czy po wjeździe na to skrzyżowanie powinieneś poruszać się dookoła wyspy zgodnie z kierunkiem ruchu wskazówek zegara?",
                    YesOrNoAnswer.no,
                    new Module(1, "module name"),
                    null,
                    null
            );
            map.put(i, standardQuestion);
        }

        return map;
    }
}
