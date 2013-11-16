package database.csv;

import com.csvreader.CsvReader;
import database.columns.ModuleColumnNames;
import encrypt.Encrypter;
import model.Module;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * Author: Daniel
 * Date: 12.11.13.
 */
public class CSVModuleDataProvider {
    public static List<Module> getAllModules() throws IOException {
        List<Module> modules = new ArrayList<Module>();

        InputStream resourceAsStream = CSVModuleDataProvider.class.getResourceAsStream("/csv/modules_enc");

        byte[] bytesArray = Encrypter.decryptFile(resourceAsStream);

        InputStream byteInputStream = new ByteArrayInputStream(bytesArray);
        CsvReader csvReader = new CsvReader(byteInputStream, ';', Charset.forName("UTF-8"));

        csvReader.readHeaders();

        while (csvReader.readRecord()) {
            try {
                int moduleId = Integer.parseInt(csvReader.get(ModuleColumnNames.MODULE_ID));
                String moduleName = csvReader.get(ModuleColumnNames.MODULE_NAME);

                Module module = new Module(moduleId, moduleName);
                modules.add(module);
            } catch (NumberFormatException ignored) {
            }
        }
        csvReader.close();

        return modules;
    }

    public static Module getModule(int moduleId) throws IOException {
        List<Module> modules = getAllModules();

        for (Module module : modules) {
            if (module.getId() == moduleId) {
                return module;
            }
        }

        return null;
    }
}
