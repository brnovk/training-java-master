package by.training.constans;

import java.io.File;

public class UploadPath {

    private static final String UPLOAD_FOLDER = "upload";
    private static final String EMPTY = "";
    private static final Object initialLock = new Object();
    private static String pathUpload;
    private static UploadPath instance;

    private UploadPath() {
    }

    public static void initialize(String realPath) {
        if (!EMPTY.equals(realPath)) {
            synchronized (initialLock) {
                if (instance == null) {
                    pathUpload = realPath + File.separator + UPLOAD_FOLDER
                            + File.separator;
                    instance = new UploadPath();
                }
            }
        }
    }

    public static String getPathUpload() {
        if (pathUpload == null) {
            throw new RuntimeException("The class is not initialized.");
        }
        return pathUpload;
    }
}
