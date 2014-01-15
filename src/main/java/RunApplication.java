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
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                Logger logger = ApplicationUtils.getLogger(RunApplication.class);
                logger.info("informacja");

                /*FilesUtils.loadVLCJNativeLibraries();
                new LoadingFrame();*/
            }
        });
    }
}

