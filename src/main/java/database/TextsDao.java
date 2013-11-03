package database;

import java.io.File;
import java.util.ResourceBundle;

/**
 * Author: dmichalski
 * Date: 01.11.13
 */
public class TextsDao {
    public static String get(String name) {
        ResourceBundle rb = ResourceBundle.getBundle("properties" + File.separator + "texts");
        return rb.getString(name);
    }
}
