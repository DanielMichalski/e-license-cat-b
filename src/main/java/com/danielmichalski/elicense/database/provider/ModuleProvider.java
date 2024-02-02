package com.danielmichalski.elicense.database.provider;

import com.danielmichalski.elicense.database.csv.CSVModuleDataProvider;
import com.danielmichalski.elicense.model.Module;

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

        int i = 1;
        for (Module module : allModules) {
            String moduleName = module.getName();
            String firstLetter = moduleName.substring(0, 1);
            String firstBigLetter = firstLetter.toUpperCase();
            String otherText = moduleName.substring(1, moduleName.length());
            module.setName(i + ". " + firstBigLetter + otherText);
            i++;
        }
    }

    public List<Module> getAllModules() {
        return allModules;
    }
}
