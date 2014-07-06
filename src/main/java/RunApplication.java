import org.apache.log4j.Logger;
import ui.loading_new.LoadingFrame;
import util.ApplicationUtils;
import util.FileUtils;

import java.awt.*;

/**
 * Author: dmichalski
 */
public class RunApplication {
    // test
    private static Logger logger = ApplicationUtils.getLogger(RunApplication.class);

    public static void main(String[] args) {
        try {
            ApplicationUtils.setNimbusLookAndFeel();
            ApplicationUtils.checkCD();
            startApp();
        } catch (Exception e) {
            logger.error(e);
        }
    }

    private static void startApp() {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                FileUtils.loadVLCJNativeLibraries();
                new LoadingFrame();
            }
        });
    }
}

