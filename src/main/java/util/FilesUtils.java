package util;

import com.sun.jna.NativeLibrary;
import org.apache.log4j.Logger;
import uk.co.caprica.vlcj.runtime.RuntimeUtil;

import javax.swing.*;
import java.io.File;

/**
 * Author: Daniel
 */
public class FilesUtils {
    private static Logger logger = ApplicationUtils.getLogger(FilesUtils.class);

    public static void createTempFolder() {
        try {
            File file = new File(FilesUtils.getTempDirPath());
            if (!file.exists()) {
                boolean mkdir = file.mkdirs();
                if (!mkdir) {
                    throw new Exception();
                }

                logger.info("Utworzenie folderu temp");
            } else {
                logger.info("Folder temp już istnieje, nie ma potrzeby tworzenia");
            }
        } catch (Exception e) {
            logger.error("Aplikacja nie mogła utworzyć wymaganego folderu: " + e);
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
        return System.getProperty("java.io.tmpdir") + "prod";
    }

    public static void loadVLCJNativeLibraries() {
        try {
            if (is64bitJavaJREInstalled()) {
                NativeLibrary.addSearchPath(RuntimeUtil.getLibVlcLibraryName(), "bin" + File.separator + "VLCx64");
                logger.info("VLCx64 library loaded correctly");
            } else {
                NativeLibrary.addSearchPath(RuntimeUtil.getLibVlcLibraryName(), "bin" + File.separator + "VLCx86");
                logger.info("VLCx86 library loaded correctly");
            }
        } catch (RuntimeException e) {
            JOptionPane.showMessageDialog(
                    null,
                    "Nie można uruchomić programu, ponieważ wystąpił błąd z załadowaniem biblioteki vlcj. " + e,
                    "Uwaga",
                    JOptionPane.ERROR_MESSAGE
            );
            System.exit(1);
        }
    }

    private static boolean is64bitJavaJREInstalled() {
        String osArch = System.getProperty("sun.arch.data.model");
        return osArch != null && osArch.equals("64");
    }
}
