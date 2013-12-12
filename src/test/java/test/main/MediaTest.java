package test.main;

import com.csvreader.CsvReader;
import encrypt.Encrypter;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

import static database.columns.QuestionColumnNames.Q_FIRST_MEDIA_PATH;
import static database.columns.QuestionColumnNames.Q_SECOND_MEDIA_PATH;

/**
 * Author: Daniel
 */
public class MediaTest {
    public static void main(String[] args) throws IOException {
        long start = System.currentTimeMillis();

        InputStream resourceAsStream = MediaTest.class.getResourceAsStream("/csv/q_enc");
        byte[] bytesArray = Encrypter.decryptFile(resourceAsStream);

        InputStream byteInputStream = new ByteArrayInputStream(bytesArray);
        CsvReader csvReader = new CsvReader(byteInputStream, ';', Charset.forName("UTF-8"));

        csvReader.readHeaders();

        int suma = 0;
        while (csvReader.readRecord()) {
            try {
                String mediaPath = null;
                if (!csvReader.get(Q_FIRST_MEDIA_PATH).equals("")) {
                    mediaPath = csvReader.get(Q_FIRST_MEDIA_PATH);
                } else if (!csvReader.get(Q_SECOND_MEDIA_PATH).equals("")) {
                    mediaPath = csvReader.get(Q_SECOND_MEDIA_PATH);
                }

                File file = new File("media" + File.separator + mediaPath);
                if (!file.exists()) {
                    System.out.println(mediaPath + " nie istnieje");
                    //continue;
                }

                suma++;

            } catch (IOException e) {
                e.printStackTrace();
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            }
        }

        System.out.println(suma);

        long end = System.currentTimeMillis();
        long time = end-start;

        double timeInSec = (double)time / 1000;
        System.out.println(String.format("Time: %.2f sec.", timeInSec));

    }
}
