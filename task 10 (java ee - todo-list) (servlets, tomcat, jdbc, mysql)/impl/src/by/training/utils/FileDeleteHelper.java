package by.training.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import by.training.constans.UploadPath;
import by.training.model.beans.Note;
import by.training.model.beans.User;

public class FileDeleteHelper extends AbstractFileHelper {

    public void deletingFile(User user, Note note) throws IOException {
        int idUser = user.getId();
        int idNote = note.getId();
        String uploadPath = UploadPath.getPathUpload();
        String fileName = note.getFile().getName();
        String fileCatalog = getCurentFileCatalog(uploadPath, idUser, idNote);

        Files.delete(Paths.get(fileCatalog + fileName));
        Files.delete(Paths.get(fileCatalog));
    }
}
