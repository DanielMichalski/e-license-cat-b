package util;

import javax.swing.*;
import java.io.File;

/**
 * Author: Daniel
 */
public class FilesUtils {
    public static void createTempFolder() {
        try {
            File file = new File(FilesUtils.getUserDir() + File.separator + "prod");
            if (!file.exists()) {
                boolean mkdir = file.mkdirs();
                if (!mkdir) {
                    throw new Exception();
                }
                System.out.println("Utworzenie folderu prod");
            } else {
                System.out.println("Folder prod już istnieje");
            }
            Process p = Runtime.getRuntime().exec("attrib +H " + file.getPath());
            p.waitFor();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(
                    null,
                    "Aplikacja nie mogła utworzyć wymaganego folderu: " + e,
                    "Informacja",
                    JOptionPane.INFORMATION_MESSAGE);
            System.exit(1);
        }
    }

    public static void deleteTempFolderContent() {
        File dir = new File(FilesUtils.getUserDir() + File.separator + "prod");
        File[] files = dir.listFiles();

        if (files != null) {
            for (File file : files) {
                file.delete();
            }
        }
    }

    public static String getUserDir() {
        return System.getProperty("user.home");
    }
}
