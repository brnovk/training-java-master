package by.training.constans;

import java.io.File;

public class JDBCPropertiesPath {

    private static final String FOLDER_WITH_PROPERTIES = "WEB-INF";
    private static final String EMPTY = "";
    private static final Object initialLock = new Object();
    private static String pathProperties;
    private static JDBCPropertiesPath instance;

    private JDBCPropertiesPath() {
    }

    public static void initialize(String realPath) {
        if (!EMPTY.equals(realPath)) {
            synchronized (initialLock) {
                if (instance == null) {
                	pathProperties = realPath + File.separator + FOLDER_WITH_PROPERTIES
                            + File.separator;
                    instance = new JDBCPropertiesPath();
                }
            }
        }
    }

    public static String getPathJDBCPropertiesFolder() {
        if (pathProperties == null) {
            throw new RuntimeException("The class is not initialized.");
        }
        return pathProperties;
    }
}
