package by.training.controllers;

import java.io.IOException;

import by.training.constans.Constants;
import by.training.constans.ErrorMessage;
import by.training.constans.Pages;
import by.training.exceptions.DaoException;
import by.training.ifaces.AbstractBaseController;
import by.training.ifaces.INoteDAO;
import by.training.model.beans.Note;
import by.training.model.beans.User;
import by.training.model.factories.NoteFactory;
import by.training.utils.FileDownloadHelper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FileDownloadController extends AbstractBaseController {

    private static final long serialVersionUID = 9078324825361923304L;

    @Override
    protected void performTask(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {

        User currentUser = getSessionUser(request);
        if (currentUser == null) {
            request.setAttribute(Constants.KEY_ABOUT_MESSAGE, 
                    ErrorMessage.WELCOM_MESSAGE);
            jump(Pages.MAIN_PAGE, request, response);
            return;
        }

        try {
            String rawIdNote = request.getParameter(
                    Constants.KEY_ID_NOTE_DOWNLOAD);
            int idNote = Integer.parseInt(rawIdNote);

            INoteDAO noteDAO = NoteFactory.getClassFromFactory();
            Note currentNote = noteDAO.getNote(currentUser, idNote);

            FileDownloadHelper helper = new FileDownloadHelper();
            helper.downloadFile(response, currentUser, currentNote);

        } catch (DaoException | NumberFormatException ex) {
            jump(Pages.MAIN_PAGE, ex.getMessage(), request, response);
        }
    }
}
