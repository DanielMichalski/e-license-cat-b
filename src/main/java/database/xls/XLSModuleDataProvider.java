package database.xls;

import database.TextsDao;
import model.Module;
import model.SpecialistQuestion;
import model.StandardQuestion;
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
public class XLSModuleDataProvider {
    private static Map<Integer, StandardQuestion> a20StandardQuestions;

    public static List<Module> getAllModules() throws IOException, InvalidFormatException {
        List<Module> listOfModules = new ArrayList<Module>();

        String modulesPath = TextsDao.getPath("modules_path");
        InputStream resourceAsStream = XLSModuleDataProvider.class.getResourceAsStream(modulesPath);

        Workbook exWorkBook = WorkbookFactory.create(resourceAsStream);

        Sheet sheet = exWorkBook.getSheetAt(0);

        for (Row tempRow : sheet) {
            int id = 0;
            String moduleName = "";

            for (int column = 0; column < 2; column++) {
                Cell tempCell = tempRow.getCell(column);

                switch (column) {
                    case 0:
                        id = (int)tempCell.getNumericCellValue();
                        break;
                    case 1:
                        moduleName = tempCell.getStringCellValue();
                        break;
                }
            }

            Module module = new Module(id, moduleName);
            listOfModules.add(module);
        }

        return listOfModules;
    }

    public static Module getModuleFromModuleId(int id) throws IOException, InvalidFormatException {
        Module module;
        int moduleId = 0;
        String moduleName = null;

        String modulesPath = TextsDao.getPath("modules_path");
        InputStream resourceAsStream = XLSModuleDataProvider.class.getResourceAsStream(modulesPath);

        Workbook exWorkBook = WorkbookFactory.create(resourceAsStream);

        Sheet sheet = exWorkBook.getSheetAt(0);

        for (Row tempRow : sheet) {

            for (int column = 0; column < 2; column++) {
                Cell tempCell = tempRow.getCell(column);

                int idFromXls = (int) tempCell.getNumericCellValue();

                System.out.println(idFromXls);

                if (id == idFromXls) {
                    switch (column) {
                        case 0:
                            moduleId = (int)tempCell.getNumericCellValue();
                            break;
                        case 1:
                            moduleName = tempCell.getStringCellValue();
                            break;
                    }

                    return new Module(moduleId, moduleName);
                }
            }
        }

        return null;
    }
}
