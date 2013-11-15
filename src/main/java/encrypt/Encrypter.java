package encrypt;

import org.jasypt.util.binary.BasicBinaryEncryptor;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
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
        byte[] myBinary = Files.readAllBytes(oldPath);

        BasicBinaryEncryptor binaryEncryptor = new BasicBinaryEncryptor();
        binaryEncryptor.setPassword(myEncryptionPassword);

        byte[] decrypted = binaryEncryptor.decrypt(myBinary);

        if (createFile) {
            Files.write(newPath, decrypted);
        }

        return decrypted;
    }

    public static void chooseFileToEncrypt() throws IOException {
        JFileChooser jChooser = new JFileChooser();
        int result = jChooser.showOpenDialog(null);

        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = jChooser.getSelectedFile();
            String canonicalPath = selectedFile.getCanonicalPath();
            Path oldPath = Paths.get(canonicalPath);

            Path newPath = Paths.get(canonicalPath + "enc");

            encryptFile(oldPath, newPath);
        }
    }
}

