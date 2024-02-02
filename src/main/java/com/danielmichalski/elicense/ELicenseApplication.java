package com.danielmichalski.elicense;

import org.apache.log4j.Logger;
import com.danielmichalski.elicense.ui.loading_new.LoadingFrame;
import com.danielmichalski.elicense.util.ApplicationUtils;
import com.danielmichalski.elicense.util.FileUtils;

import java.awt.*;

/**
 * Author: dmichalski
 */
public class ELicenseApplication {
    // test
    private static Logger logger = ApplicationUtils.getLogger(ELicenseApplication.class);

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

