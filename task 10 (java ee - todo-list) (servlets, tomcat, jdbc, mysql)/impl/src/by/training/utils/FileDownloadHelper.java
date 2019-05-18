package by.training.utils;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import by.training.constans.UploadPath;
import by.training.model.beans.Note;
import by.training.model.beans.User;

public class FileDownloadHelper extends AbstractFileHelper {

    public void downloadFile(HttpServletResponse response, User user, Note note)
            throws IOException {

        ServletOutputStream outputStream = null;
        InputStream inputStream = null;
        try {

            int idUser = user.getId();
            int idNote = note.getId();
            String uploadPath = UploadPath.getPathUpload();
            String fileName = note.getFile().getName();
            String fileCatalog = getCurentFileCatalog(uploadPath, idUser, idNote);

            File file = new File(fileCatalog + fileName);
            outputStream = response.getOutputStream();

            // set response headers
            response.setContentType("Content-type: application/octet-stream\n");
            response.setDateHeader("Expires", 0);
            
            // change character set for Cyrillic and replace 'spaces and nbsp' to '_'
            String normalizeFileName = new String(fileName.getBytes("Cp1251"),"Cp1252");  
            normalizeFileName = normalizeFileName.replaceAll("[\u00a0\\s]","_");

            response.addHeader("Content-Disposition", "attachment; filename=\""
                    + normalizeFileName + "\"");
            response.setContentLength((int) file.length());
            inputStream = new BufferedInputStream(new FileInputStream(file));
            int readBytes;
            while ((readBytes = inputStream.read()) != -1) {
                outputStream.write(readBytes);
            }
        } finally {
            if (outputStream != null) {
                try {
                    outputStream.flush();
                    outputStream.close();
                } catch (IOException ex) {
                    throw ex;
                }
            }
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException ex) {
                    throw ex;
                }
            }
        }
    }
}
