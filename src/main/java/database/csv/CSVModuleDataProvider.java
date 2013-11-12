package database.csv;

import com.csvreader.CsvReader;
import database.columns.ModuleColumnNames;
import encrypt.Encrypter;
import model.Module;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Author: Daniel
 * Date: 12.11.13.
 */
public class CSVModuleDataProvider {
    public static List<Module> readAllModules() throws IOException {
        List<Module> modules = new ArrayList<Module>();

        Path modulesPath = Paths.get("src/main/resources/csv/modules_enc.csv");
        byte[] bytesArray = Encrypter.decryptFile(modulesPath, null, false);

        InputStream byteInputStream = new ByteArrayInputStream(bytesArray);
        CsvReader csvReader = new CsvReader(byteInputStream, Charset.defaultCharset());

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
        List<Module> modules = readAllModules();

        for (Module module : modules) {
            if (module.getId() == moduleId) {
                return module;
            }
        }

        return null;
    }
}
