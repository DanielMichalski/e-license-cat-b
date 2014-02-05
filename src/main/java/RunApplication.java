import org.apache.log4j.Logger;
import ui.loading_new.LoadingFrame;
import util.ApplicationUtils;
import util.FilesUtils;

import java.awt.*;

/**
 * Author: dmichalski
 * Date: 01.11.13
 */
public class RunApplication {
    private static Logger logger = ApplicationUtils.getLogger(RunApplication.class);

    public static void main(String[] args) {
        try {
            ApplicationUtils.setNimbusLookAndFeel();
//            ApplicationUtils.checkCD();
            startApp();
        } catch (Exception e) {
            logger.error(e);
        }
    }

    private static void startApp() {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                FilesUtils.loadVLCJNativeLibraries();
                new LoadingFrame();
            }
        });
    }
}

