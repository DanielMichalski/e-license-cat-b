package util;

import javax.swing.*;
import java.io.File;

/**
 * Author: Daniel
 */
public class FilesUtils {
    public static void createTempFolder() {
        try {
            File file = new File(FilesUtils.getTempDirPath());
            if (!file.exists()) {
                boolean mkdir = file.mkdirs();
                if (!mkdir) {
                    throw new Exception();
                }
                System.out.println("Utworzenie folderu prod");
            } else {
                System.out.println("Folder prod już istnieje");
            }
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
        File dir = new File(FilesUtils.getTempDirPath());
        File[] files = dir.listFiles();

        if (files != null) {
            for (File file : files) {
                file.delete();
            }
        }
    }

    public static String getTempDirPath() {
        return System.getProperty("java.io.tmpdir") + File.separator + "prod";
    }
}
