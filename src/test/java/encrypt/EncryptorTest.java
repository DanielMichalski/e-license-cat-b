package encrypt;

import org.junit.Test;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Author: Daniel
 * Date: 12.11.13.
 */
public class EncryptorTest {
    @Test
    public void testEncryption() throws Exception {
        Path path1 = Paths.get("src/test/java/encrypt/file1.csv");
        Path path2 = Paths.get("src/test/java/encrypt/file2.csv");
        Path path3 = Paths.get("src/test/java/encrypt/file3.csv");

        Encrypter.encryptFile(path1, path2);

        Encrypter.decryptFile(path2, path3, true);
    }
}
