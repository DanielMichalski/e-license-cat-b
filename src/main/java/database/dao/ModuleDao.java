package database.dao;

import database.csv.CSVModuleDataProvider;
import model.Module;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import java.io.IOException;
import java.util.List;

/**
 * Author: Daniel
 * Date: 05.11.13
 */
public class ModuleDao {
    public static List<Module> getAllModules()
            throws IOException, InvalidFormatException {

        return CSVModuleDataProvider.getAllModules();
    }

    public static Module getModuleById(int moduleId)
            throws IOException {

        return CSVModuleDataProvider.getModule(moduleId);
    }
}
