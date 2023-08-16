package com.bookstore.util;

import java.io.File;
import java.io.Serializable;

public class FileUtil implements Serializable {

    public static boolean fileExists(String fileName){
        File f = new File(getWebRootPath() + "css/" + fileName);
        System.out.println("FileUtil (fileExists) :" + f.exists());
        return f.exists();
    }

    private static String getWebRootPath() {
        return FileUtil.class.getProtectionDomain().getCodeSource().getLocation().getPath().split("WEB-INF/")[0];
    }
}
