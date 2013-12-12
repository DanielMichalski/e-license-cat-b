package database.dao;

import database.provider.ModuleProvider;
import model.Module;

import java.util.List;

/**
 * Author: Daniel
 * Date: 05.11.13
 */
public class ModuleDao {
    public static List<Module> getAllModules() {
        ModuleProvider moduleProvider =
                ModuleProvider.getInstance();

        return moduleProvider.getAllModules();
    }
}
