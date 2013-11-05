package database;

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

    public static String getPath(String name) {
        ResourceBundle rb = ResourceBundle.getBundle("properties/paths");
        return rb.getString(name);
    }
}
