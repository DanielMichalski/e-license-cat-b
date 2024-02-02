package com.danielmichalski.elicense.util;

import java.io.File;

/**
 * Author: Daniel
 */
public class PathUtils {
    public static String getMediaDirPath() {
        return "data" + File.separator + "bin" + File.separator + "media" + File.separator;
    }

    public static String getVlcLibDirPath() {
        return "data" + File.separator + "bin" + File.separator;
    }
}
