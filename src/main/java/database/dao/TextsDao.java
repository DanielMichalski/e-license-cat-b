package database.dao;

import java.util.ResourceBundle;

/**
 * Author: dmichalski
 * Date: 01.11.13
 */
public class TextsDao {
    public static String getText(String name) {
        ResourceBundle rb = ResourceBundle.getBundle("properties/texts");
        return rb.getString(name);
    }

    public static String getFileName(String name) {
        ResourceBundle rb = ResourceBundle.getBundle("properties/file_names");
        return rb.getString(name);
    }
}
