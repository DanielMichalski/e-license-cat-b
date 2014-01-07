package encrypt;

import org.apache.poi.util.IOUtils;
import org.jasypt.util.binary.BasicBinaryEncryptor;
import util.FilesUtils;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Author: Daniel
 * Date: 12.11.13.
 */
public class Encrypter {

    static String myEncryptionPassword = "J3#&0(%7%z";

    public static void encryptFile(Path oldPath, Path newPath) throws IOException {
        byte[] myBinary = Files.readAllBytes(oldPath);

        BasicBinaryEncryptor binaryEncryptor = new BasicBinaryEncryptor();
        binaryEncryptor.setPassword(myEncryptionPassword);
        byte[] myEncryptedBytes = binaryEncryptor.encrypt(myBinary);

        Files.write(newPath, myEncryptedBytes);
    }

    public static byte[] decryptFile(Path oldPath, Path newPath, boolean createFile) throws IOException {
        FilesUtils.deleteTempFolderContent();
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

    public static void encryptAllFilesInDir(String dirName) throws IOException {
        File dir = new File(dirName);
        File[] files = dir.listFiles();

        for (File file : files) {
            Path oldFile = Paths.get(file.getAbsolutePath());
            Path newFile = Paths.get(file.getAbsolutePath() + "e");

            encryptFile(oldFile, newFile);
        }
    }

    public static void decodeMedia(String mediaPath) {
        Path oldPath = Paths.get("bin" + File.separator + "media" + File.separator + mediaPath + ".prode");
        Path newPath = Paths.get("prod" + File.separator + mediaPath + ".prode");
        try {
            Encrypter.decryptFile(oldPath, newPath, true);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, e, "Informacja", JOptionPane.INFORMATION_MESSAGE);
            System.exit(1);
        }
    }

}

