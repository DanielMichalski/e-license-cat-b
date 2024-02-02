package com.danielmichalski.elicense.encrypt;

import org.apache.log4j.Logger;
import org.apache.poi.util.IOUtils;
import org.jasypt.util.binary.BasicBinaryEncryptor;
import com.danielmichalski.elicense.util.ApplicationUtils;
import com.danielmichalski.elicense.util.FileUtils;
import com.danielmichalski.elicense.util.PathUtils;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Author: Daniel
 */
public class Encrypter {
    public static Logger logger = ApplicationUtils.getLogger(Encrypter.class);

    static String myEncryptionPassword = "J3#&0(%7%z";

    public static void encryptFile(Path oldPath, Path newPath) throws IOException {
        byte[] myBinary = Files.readAllBytes(oldPath);

        BasicBinaryEncryptor binaryEncryptor = new BasicBinaryEncryptor();
        binaryEncryptor.setPassword(myEncryptionPassword);
        byte[] myEncryptedBytes = binaryEncryptor.encrypt(myBinary);

        Files.write(newPath, myEncryptedBytes);
    }

    public static byte[] decryptFile(Path oldPath, Path newPath, boolean createFile) throws IOException {
        FileUtils.deleteTempFolderContent();
        byte[] myBinary = Files.readAllBytes(oldPath);

        BasicBinaryEncryptor binaryEncryptor = new BasicBinaryEncryptor();
        binaryEncryptor.setPassword(myEncryptionPassword);

        byte[] decrypted = binaryEncryptor.decrypt(myBinary);

        if (createFile) {
            Files.write(newPath, decrypted);
        }

        return decrypted;
    }

    public static byte[] decryptFile(InputStream inputStream) throws IOException {
        byte[] myBinary = IOUtils.toByteArray(inputStream);

        BasicBinaryEncryptor binaryEncryptor = new BasicBinaryEncryptor();
        binaryEncryptor.setPassword(myEncryptionPassword);

        return binaryEncryptor.decrypt(myBinary);
    }


    public static void enryptChoosenFile() throws IOException {
        JFileChooser fChooser = new JFileChooser();
        int result = fChooser.showOpenDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fChooser.getSelectedFile();

            Path oldFile = Paths.get(selectedFile.getAbsolutePath());
            Path newFile = Paths.get(selectedFile.getAbsolutePath() + "-encrypted");

            encryptFile(oldFile, newFile);
        }
    }

    public static void decryptChoosenFile() throws IOException {
        JFileChooser fChooser = new JFileChooser();
        int result = fChooser.showOpenDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fChooser.getSelectedFile();

            Path oldFile = Paths.get(selectedFile.getAbsolutePath());
            Path newFile = Paths.get(selectedFile.getAbsolutePath() + "-decrypted");

            decryptFile(oldFile, newFile, true);
        }
    }

    public static void encryptAllFilesInChoosenDir() throws IOException {
        JFileChooser fChooser = new JFileChooser();
        fChooser.setAcceptAllFileFilterUsed(true);
        int result = fChooser.showOpenDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            File dir = fChooser.getSelectedFile().getParentFile();
            File[] files = dir.listFiles();

            for (File file : files) {
                Path oldFile = Paths.get(file.getAbsolutePath());
                Path newFile = Paths.get(file.getAbsolutePath());

                encryptFile(oldFile, newFile);
            }
        }
    }

    public static void decodeMedia(String mediaPath) {
        Path oldPath = Paths.get(PathUtils.getMediaDirPath() + mediaPath + ".prode");
        Path newPath = Paths.get(FileUtils.getTempDirPath() + File.separator + mediaPath + ".prode");
        try {
            Encrypter.decryptFile(oldPath, newPath, true);
        } catch (IOException e) {
            logger.error("Brakuje pliku: " + mediaPath + " " + e);
            JOptionPane.showMessageDialog(
                    null,
                    "Nie znaleziono pliku " + mediaPath + ". Program zakończy swoje działanie",
                    "Błąd",
                    JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }
    }

}

