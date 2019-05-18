package by.training.utils;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import by.training.constans.Constants;
import by.training.constans.ErrorMessage;
import by.training.constans.UploadPath;
import by.training.exceptions.UploadException;
import by.training.model.beans.User;
import by.training.utils.beans.AddressPartHeader;
import by.training.utils.beans.FileInfo;

public class FileUploadHelper extends AbstractFileHelper {

    private static final int DATA_EMPTY = -1;
    private static final String HEADER_DATA_DELIMETER = "\r\n\r\n";
    private static final int OFFSET_HEADER_DATA_DELIMETER
            = HEADER_DATA_DELIMETER.length();

    private final User user;
    private final HttpServletRequest request;
    private String fileName;
    private int idNote = Constants.EMPTY_ID;

    public FileUploadHelper(User user, HttpServletRequest request) {
        this.user = user;
        this.request = request;
    }

    public int getUploadedIdNote() throws UploadException {
        if (idNote == Constants.EMPTY_ID) {
            throw new UploadException("No note to upload the file.");
        }
        return idNote;
    }

    public String getUploadedFileName() throws UploadException {
        if (fileName == null) {
            throw new UploadException("No uploaded file.");
        }
        return fileName;
    }

    public void upload() throws IOException, UploadException {

        // Storing the contents of the request
        byte[] realRequestData;

        // Maximum amount of information stored
        final int maximumDataSize = 1024 * 1024 * 31; // ~31 mb

        InputStream inputStream = request.getInputStream();
        BufferedInputStream bufferedInputStream = new BufferedInputStream(
                inputStream, maximumDataSize);

        // temporary storage of the contents of the request
        byte[] maximumRequestData = new byte[maximumDataSize];
        int buffer = 0;
        int readDateSize = 0;
        while ((buffer = bufferedInputStream.read()) != DATA_EMPTY) {
            maximumRequestData[readDateSize] = (byte) buffer;
            readDateSize++;
        }
        realRequestData = new byte[readDateSize];
        System.arraycopy(maximumRequestData, 0, realRequestData, 0,
                readDateSize);

        String contentTypeHeader = request.getHeader("Content-Type");
        String boundary = getBoundary(contentTypeHeader);

        List<AddressPartHeader> parts = getPartsHeader(realRequestData,
                boundary);
        idNote = getHiddenFieldIdNote(parts, realRequestData,
                Constants.KEY_ID_NOTE_UPLOAD);
        if (idNote == Constants.EMPTY_ID) {
        	System.out.println("=  idNote" + idNote);
            throw new UploadException(ErrorMessage.NO_SELECTED_NOTE);
        }
        FileInfo fileAddress = getFileInfo(parts, realRequestData, idNote);
        if (fileAddress == null) {
            throw new UploadException(ErrorMessage.NO_SELECTED_FILE);
        }

        String fileCatalog = getCurentFileCatalog(
                UploadPath.getPathUpload(), user.getId(), idNote);
        fileAddress.saveFile(realRequestData, fileCatalog);
        fileName = fileAddress.getFileName();
    }

    private FileInfo getFileInfo(List<AddressPartHeader> parts,
            byte[] requestData, int idUploadNote) throws UnsupportedEncodingException {

        final char QUOTE = '"';
        final int FIRST_QUOTE = 1;

        final String LABEL_ID_NOTE = " name=";
        final String LABEL_FILENAME = "filename=";
        final int OFFSET_ID_NOTE = LABEL_ID_NOTE.length() + FIRST_QUOTE;
        final int OFFSET_LABEL_FILENAME = LABEL_FILENAME.length() + FIRST_QUOTE;

        FileInfo resultFile = null;
        for (AddressPartHeader currentPart : parts) {
            String currentDataPart = new String(requestData,
                    currentPart.getStartIndex(), currentPart.getLastIndex()
                    - currentPart.getStartIndex(), "UTF-8");
            int indexStartDelim = currentDataPart.indexOf(HEADER_DATA_DELIMETER, 2);
            String header = currentDataPart.substring(0, indexStartDelim);
            int indexLabelId;
            if ((indexLabelId = header.indexOf(LABEL_ID_NOTE)) != DATA_EMPTY) {
                int indexLabelFileName;
                if ((indexLabelFileName = header.indexOf(LABEL_FILENAME)) != DATA_EMPTY) {

                    int endIdIndex = header.indexOf((int) QUOTE, indexLabelId
                            + OFFSET_ID_NOTE);
                    String rawIdNote = header.substring(indexLabelId
                            + OFFSET_ID_NOTE, endIdIndex);

                    int endFilenameIndex = header.indexOf((int) QUOTE,
                            indexLabelFileName + OFFSET_LABEL_FILENAME);
                    String rawFilename = header.substring(indexLabelFileName
                            + OFFSET_LABEL_FILENAME, endFilenameIndex);
                    
                    int curentIdNote = Integer.parseInt(rawIdNote);
                    if (curentIdNote == idUploadNote) {
                        if (!rawFilename.isEmpty()) {
                            int offsetStartFile = currentPart.getStartIndex()
                                    + indexStartDelim + OFFSET_HEADER_DATA_DELIMETER;
                            // 2 unnecessary character before the border
                            int offsetEndFile = currentPart.getLastIndex() - 2;
                            resultFile = new FileInfo(offsetStartFile,
                                    offsetEndFile, rawFilename);
                        }
                        break;
                    }
                }
            }
        }
        return resultFile;
    }

    private int getHiddenFieldIdNote(List<AddressPartHeader> parts,
            byte[] requestData, String fieldUploadIdNote) {

        final String HEADER_FIELD_UPLOAD = "name=\"" + fieldUploadIdNote + "\"";
        final int OFFSET_FIELD_NAME = HEADER_FIELD_UPLOAD.length();

        for (AddressPartHeader currentPart : parts) {
            String currentDataPart = new String(requestData,
                    currentPart.getStartIndex(), currentPart.getLastIndex()
                    - currentPart.getStartIndex());
            int indexStartDelim = currentDataPart.indexOf(HEADER_DATA_DELIMETER, 2);
            String header = currentDataPart.substring(0, indexStartDelim);
            int indexStartName;
            if ((indexStartName = header.indexOf(HEADER_FIELD_UPLOAD)) != DATA_EMPTY) {
                int startData = indexStartName + OFFSET_FIELD_NAME
                        + OFFSET_HEADER_DATA_DELIMETER;
                String rawId = currentDataPart.substring(startData,
                        currentDataPart.length());
                return Integer.parseInt(rawId.trim());
            }
        }
        
        return -1;
    }

    private List<AddressPartHeader> getPartsHeader(byte[] requestData,
            String boundary) {

        final int INITIAL_VALUE = 0;
        final int EMPTY_VALUE = -1;

        String requestString = new String(requestData);
        List<AddressPartHeader> parts = new ArrayList<>();
        int i = INITIAL_VALUE;
        int prevIndex = INITIAL_VALUE;
        int tmpIndex = INITIAL_VALUE;
        while ((tmpIndex = requestString.indexOf(boundary, tmpIndex)) != EMPTY_VALUE) {
            if (i != INITIAL_VALUE) {
                AddressPartHeader currentPart = new AddressPartHeader(
                        prevIndex, tmpIndex);
                parts.add(currentPart);
            }
            tmpIndex += boundary.length();
            prevIndex = tmpIndex;
            i++;
        }
        return parts;
    }

    private String getBoundary(String contentTypeHeader) {
        // real boundary on the two characters '-' long.
        final String extraStartBoundary = "--";
        final String labelBoundary = "boundary=";
        final int offset = labelBoundary.length();
        final int startIndex = contentTypeHeader.lastIndexOf(labelBoundary);
        String rawBoundary = contentTypeHeader.substring(startIndex + offset);
        return extraStartBoundary + rawBoundary;
    }
}
