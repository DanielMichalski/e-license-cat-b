package database.csv;

import com.csvreader.CsvReader;
import database.columns.ModuleColumnNames;
import encrypt.Encrypter;
import model.Module;
import org.apache.log4j.Logger;
import util.ApplicationUtils;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * Author: Daniel
 */
public class CSVModuleDataProvider {
    private static Logger LOGGER = ApplicationUtils.getLogger(CSVModuleDataProvider.class);

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
                    LOGGER.error(e.toString());
                }
            }
            csvReader.close();
        } catch (IOException ex) {
            LOGGER.error(ex.toString());
        }
        return modules;
    }
}
