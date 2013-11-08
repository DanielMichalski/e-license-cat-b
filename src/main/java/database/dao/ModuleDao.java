package database.dao;

import database.xls.XLSModuleDataProvider;
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

        return XLSModuleDataProvider.getAllModules();
    }
}
