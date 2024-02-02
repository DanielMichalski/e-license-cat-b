package com.danielmichalski.elicense.database.dao;

import com.danielmichalski.elicense.database.provider.ModuleProvider;
import com.danielmichalski.elicense.model.Module;

import java.util.List;

/**
 * Author: Daniel
 */
public class ModuleDao {
    public static List<Module> getAllModules() {
        ModuleProvider moduleProvider =
                ModuleProvider.getInstance();

        return moduleProvider.getAllModules();
    }
}
