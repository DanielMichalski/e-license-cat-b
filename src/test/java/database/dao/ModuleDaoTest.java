package database.dao;

import model.Module;

import java.util.List;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Author: Daniel
 * Date: 08.11.13
 */
public class ModuleDaoTest {
    @org.junit.Test
    public void testGetAllModules() throws Exception {
        List<Module> allModules = ModuleDao.getAllModules();
        assertNotNull(allModules);
        assertNotEquals(0, allModules.size());
    }
}
