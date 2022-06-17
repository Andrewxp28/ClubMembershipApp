package com.andrew.myutils;


import java.io.File;

public class MyUtils {
    private static MyUtils instance;


    public static MyUtils getInstance() {
        if (instance == null) {
            instance = new MyUtils();
        }
        return instance;
    }

    public OS_TYPE getOSType() {
        String OS = System.getProperty("os.name").toLowerCase();
        boolean isWindows = OS.contains("win");
        boolean isMac = OS.contains("mac");
        boolean isUnix = (OS.contains("nix") || OS.contains("nux") || OS.indexOf("aix") > 0);

        if (isWindows) return OS_TYPE.WINDOWS;
        if (isMac) return OS_TYPE.MAC;
        if (isUnix) return OS_TYPE.UNIX;
        return null;
    }

    private String fixFileSeparators(String path) {
        OS_TYPE OS = MyUtils.getInstance().getOSType();
        String fileSeparator = File.separator;
        // if non-window system, we swap the file separators, if windows, do nothing as windows is default
        if (OS.equals(OS_TYPE.MAC) || OS.equals(OS_TYPE.UNIX)) {
            path = path.replaceAll("\\\\", fileSeparator);
        }
        return path;
    }
}
