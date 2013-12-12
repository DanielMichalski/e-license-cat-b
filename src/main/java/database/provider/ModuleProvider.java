package database.provider;

import database.csv.CSVModuleDataProvider;
import model.Module;

import java.util.List;

/**
 * Author: Daniel
 */
public class ModuleProvider {
    private List<Module> allModules;

    private static ModuleProvider ourInstance = new ModuleProvider();

    public static ModuleProvider getInstance() {
        return ourInstance;
    }

    private ModuleProvider() {
        CSVModuleDataProvider csvModuleDataProvider =
                new CSVModuleDataProvider();

        allModules = csvModuleDataProvider.getAllModules();
    }

    public List<Module> getAllModules() {
        return allModules;
    }
}
