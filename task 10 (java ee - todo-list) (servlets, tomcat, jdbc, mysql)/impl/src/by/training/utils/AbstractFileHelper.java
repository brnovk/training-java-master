package by.training.utils;

import java.io.File;

public abstract class AbstractFileHelper {

    protected final String getCurentFileCatalog(
            String uploadPath, int idUser, int idNote) {
        return uploadPath + File.separator
                + idUser + File.separator
                + idNote + File.separator;
    }
}
