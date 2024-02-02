package com.danielmichalski.elicense.database.xls;

import com.danielmichalski.elicense.database.dao.TextsDao;
import com.danielmichalski.elicense.model.StandardQuestion;
import com.danielmichalski.elicense.model.Module;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

/**
 * Author: Daniel
 */
public class XLSModuleDataProvider {
  private static Map<Integer, StandardQuestion> a20StandardQuestions;

  public static List<Module> getAllModules() throws IOException, InvalidFormatException {
    List<Module> listOfModules = new ArrayList<Module>();

    String modulesPath = TextsDao.getFileName("xls.modules");
    InputStream resourceAsStream = XLSModuleDataProvider.class.getResourceAsStream("/xls/" + modulesPath);

    Workbook exWorkBook = WorkbookFactory.create(resourceAsStream);

    Sheet sheet = exWorkBook.getSheetAt(0);

    for (Row tempRow : sheet) {
      Module module = XLSUtil.getModuleFromRow(tempRow);
      listOfModules.add(module);
    }

    return listOfModules;
  }

  public static Module getModuleFromModuleId(int id) throws IOException, InvalidFormatException {
    String modulesPath = TextsDao.getFileName("xls.modules");
    InputStream resourceAsStream = XLSModuleDataProvider.class.getResourceAsStream("/xls/" + modulesPath);

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

