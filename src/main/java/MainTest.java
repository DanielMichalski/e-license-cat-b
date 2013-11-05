import database.ModuleDao;
import database.xls.XLSModuleDataProvider;
import database.xls.XLSUtil;
import model.Module;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import java.io.IOException;
import java.util.List;

/**
 * Author: Daniel
 * Date: 05.11.13
 */
public class MainTest {
    public static void main(String[] args) {
        try {
            Module moduleFromModuleId = XLSModuleDataProvider.getModuleFromModuleId(1);
            System.out.println(moduleFromModuleId);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        }
    }
}
