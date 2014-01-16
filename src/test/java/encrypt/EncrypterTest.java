package encrypt;

import org.junit.Test;

/**
 * Author: Daniel
 */
public class EncrypterTest {
   /* @Test
    public void testEncrypt() throws Exception {
        Encrypter.enryptChoosenFile();
    }
*/
    /*@Test
    public void testDecryptChoosenFile() throws Exception {
        Encrypter.decryptChoosenFile();
    }*/

    @Test
    public void testEncryptFilesInDir() throws Exception {
        Encrypter.encryptAllFilesInDir("bin/media");
    }
}
