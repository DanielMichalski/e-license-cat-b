package database.xls;

import database.TextsDao;
import model.Module;
import model.StandardQuestion;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Author: Daniel
 * Date: 05.11.13
 */
public class XLSModuleDataProvider {
    private static Map<Integer, StandardQuestion> a20StandardQuestions;

    public static List<Module> getAllModules() throws IOException, InvalidFormatException {
        List<Module> listOfModules = new ArrayList<Module>();

        String modulesPath = TextsDao.getPath("modules_path");
        InputStream resourceAsStream = XLSModuleDataProvider.class.getResourceAsStream(modulesPath);

        Workbook exWorkBook = WorkbookFactory.create(resourceAsStream);

        Sheet sheet = exWorkBook.getSheetAt(0);

        for (Row tempRow : sheet) {
            Module module = XLSUtil.getModuleFromRow(tempRow);
            listOfModules.add(module);
        }

        return listOfModules;
    }

    public static Module getModuleFromModuleId(int id) throws IOException, InvalidFormatException {
        String modulesPath = TextsDao.getPath("modules_path");
        InputStream resourceAsStream = XLSModuleDataProvider.class.getResourceAsStream(modulesPath);

        Workbook exWorkBook = WorkbookFactory.create(resourceAsStream);

        Sheet sheet = exWorkBook.getSheetAt(0);

        for (Row tempRow : sheet) {
            Module moduleFromRow = XLSUtil.getModuleFromRow(tempRow);

            if (moduleFromRow.getId() == id) {
                return moduleFromRow;
            }
        }

        return null;
    }
}

