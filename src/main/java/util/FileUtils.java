package util;

import com.sun.jna.NativeLibrary;
import org.apache.log4j.Logger;

import javax.swing.*;
import java.io.File;

/**
 * Author: Daniel
 */
public class FileUtils {
    private static Logger logger = ApplicationUtils.getLogger(FileUtils.class);

    public static void createTempFolder() {
        try {
            File file = new File(FileUtils.getTempDirPath());
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
        File dir = new File(FileUtils.getTempDirPath());
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
        if (is64bitJavaJREInstalled()) {
            loadLib("VLCx64");
        } else {
            loadLib("VLCx86");
        }
    }

    private static void loadLib(String libName) {
        //TODO sprawdzic czy to dziala
        File file = new File(PathUtils.getVlcLibDirPath() + libName);
        if (!file.exists()) {
            showNoLibErrorAndExit(file.getAbsolutePath());
        }
        NativeLibrary.addSearchPath("libvlc", file.getAbsolutePath());

        logger.info(libName + " library loaded correctly from dir " + file.getAbsolutePath());
    }

    private static void showNoLibErrorAndExit(String absolutePath) {
        JOptionPane.showMessageDialog(
                null,
                "Nie można uruchomić programu, ponieważ wystąpił błąd z załadowaniem biblioteki vlcj. Folder " + absolutePath + " nie istnieje.",
                "Uwaga",
                JOptionPane.ERROR_MESSAGE
        );
        System.exit(1);

    }

    private static boolean is64bitJavaJREInstalled() {
        String osArch = System.getProperty("sun.arch.data.model");
        return osArch != null && osArch.equals("64");
    }

    public static void showIfFileExists(String mrl, String fileName) {
        File file = new File(mrl);

        if (file.exists()) {
            logger.info("Plik istnieje: " + fileName);
        } else {
            logger.error("Plik nie istnieje: " + fileName);
        }
    }
}
