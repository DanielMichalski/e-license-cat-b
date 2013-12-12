package database.csv;

import com.csvreader.CsvReader;
import database.columns.ModuleColumnNames;
import encrypt.Encrypter;
import model.Module;
import util.ApplicationUtils;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * Author: Daniel
 * Date: 12.11.13.
 */
public class CSVModuleDataProvider {
    private static Logger LOGGER = Logger.getLogger(CSVModuleDataProvider.class.getName());

    public List<Module> getAllModules() {
        List<Module> modules = new ArrayList<Module>();

        try {
            InputStream resourceAsStream = CSVModuleDataProvider.class.getResourceAsStream("/csv/m_enc");
            ApplicationUtils.checkResource(resourceAsStream);

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
                } catch (NumberFormatException e) {
                    LOGGER.warning(e.toString());
                }
            }
            csvReader.close();
        } catch (IOException ex) {
            LOGGER.warning(ex.toString());
        }
        return modules;
    }
}
