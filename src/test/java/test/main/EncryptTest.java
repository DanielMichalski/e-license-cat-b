package test.main;

import database.csv.CSVModuleDataProvider;
import encrypt.Encrypter;
import model.Module;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * Author: Daniel
 * Date: 12.11.13.
 */
public class EncryptTest {
    public static void main(String[] args) throws IOException {
        List<Module> modules1 = CSVModuleDataProvider.readAllModules();
        System.out.println(modules1);
    }
}
