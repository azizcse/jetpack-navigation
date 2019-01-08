package com.w3engineers.jitpackbottomnav.util;

import android.os.Environment;
import com.w3engineers.jitpackbottomnav.App;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FileUtils {

    public static final String ROOT_DIR = "Android/data/" + getPackageName();

    public static boolean isSDCardAvailable() {
        return Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState());
    }

    public static String getDir(String name) {

        String StoragePath = Environment.getExternalStorageDirectory() + "/" + name;
        File sdStorageDir = new File(StoragePath);
        if (!sdStorageDir.exists()) {
            sdStorageDir.mkdirs();
        }
        return StoragePath;

        /*StringBuilder sb = new StringBuilder();
        if (isSDCardAvailable()) {
            sb.append(getExternalStoragePath());
        } else {
            sb.append(getCachePath());
        }
        sb.append(name);
        sb.append(File.separator);
        String path = sb.toString();
        if (createDirs(path)) {
            return path;
        } else {
            return null;
        }*/
    }

    public static String getExternalStoragePath() {
        StringBuilder sb = new StringBuilder();
        sb.append(Environment.getExternalStorageDirectory().getAbsolutePath());
        sb.append(File.separator);
        //sb.append(ROOT_DIR);
        //sb.append(File.separator);
        return sb.toString();
    }

    public static String getCachePath() {
        File f = App.getContext().getCacheDir();
        if (null == f) {
            return null;
        } else {
            return f.getAbsolutePath() + "/";
        }
    }

    public static boolean createDirs(String dirPath) {
        File file = new File(dirPath);
        if (!file.exists() || !file.isDirectory()) {
            return file.mkdirs();
        }
        return true;
    }

    public static String getFileNameFromPath(String filepath) {
        if ((filepath != null) && (filepath.length() > 0)) {
            int sep = filepath.lastIndexOf('/');
            if ((sep > -1) && (sep < filepath.length() - 1)) {
                return filepath.substring(sep + 1);
            }
        }
        return filepath;
    }

    public static String getPackageName() {
        return App.getContext().getPackageName();
    }

    public static String TIME_STAMP_FORMAT_FOR_IMAGE = "yyyyMMdd_HHmmss";
    public static String IMAGE_NAME_PREFIX = "IMG_";
    public static String PHOTO_SUFFIX = ".jpg";
    public static String FILE_PREFIX = "file:";

    public static File createImageFile() throws IOException {
        String timeStamp;
        timeStamp = new SimpleDateFormat(TIME_STAMP_FORMAT_FOR_IMAGE).format(new Date());
        String imageFileName = IMAGE_NAME_PREFIX + timeStamp;
        File storageDirectory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        try {
            if (storageDirectory.mkdirs()) {
                return File.createTempFile(imageFileName, PHOTO_SUFFIX, storageDirectory);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return File.createTempFile(imageFileName, PHOTO_SUFFIX, storageDirectory);
    }
}
