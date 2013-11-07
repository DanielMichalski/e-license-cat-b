import database.ModuleDao;
import database.xls.XLSModuleDataProvider;
import database.xls.XLSSpecialistQuestionDataProvider;
import database.xls.XLSStandardQuestionDataProvider;
import database.xls.XLSUtil;
import model.Module;
import model.SpecialistQuestion;
import model.StandardQuestion;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Author: Daniel
 * Date: 05.11.13
 */
public class MainTest {
    public static void main(String[] args) {
        try {
            List<StandardQuestion> allStandardQuestions = XLSStandardQuestionDataProvider.get20StandardQuestions();
            int i = 1;
            for (StandardQuestion standardQuestion : allStandardQuestions) {
                System.out.println(i);
                i++;
            }


            List<SpecialistQuestion> allSpecialistQuestions = XLSSpecialistQuestionDataProvider.getAllSpecialistQuestions();
            i = 1;
            for (SpecialistQuestion specialistQuestion : allSpecialistQuestions) {
                System.out.println(i);
                i++;
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        }
    }
}
