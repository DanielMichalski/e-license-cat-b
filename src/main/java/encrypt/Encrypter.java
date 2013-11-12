package encrypt;

import org.jasypt.util.binary.BasicBinaryEncryptor;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

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
}

