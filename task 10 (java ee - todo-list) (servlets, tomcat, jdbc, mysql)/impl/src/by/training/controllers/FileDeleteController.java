package by.training.controllers;

import java.io.IOException;

import by.training.constans.Constants;
import by.training.constans.Controllers;
import by.training.constans.ErrorMessage;
import by.training.constans.Pages;
import by.training.exceptions.DaoException;
import by.training.ifaces.AbstractBaseController;
import by.training.ifaces.IFileDAO;
import by.training.ifaces.INoteDAO;
import by.training.model.beans.Note;
import by.training.model.beans.User;
import by.training.model.factories.FileFactory;
import by.training.model.factories.NoteFactory;
import by.training.utils.FileDeleteHelper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FileDeleteController extends AbstractBaseController {

    private static final long serialVersionUID = -4235203883129578102L;

    @Override
    protected void performTask(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {

        String rawIdNote = request.getParameter(Constants.KEY_ID_NOTE_DELETE);
        int idNote = Integer.parseInt(rawIdNote);

        User currentUser = getSessionUser(request);
        if (currentUser == null) {
            request.setAttribute(Constants.KEY_ABOUT_MESSAGE,
                    ErrorMessage.WELCOM_MESSAGE);
            jump(Pages.MAIN_PAGE, request, response);
            return;
        }

        try {
            INoteDAO noteDAO = NoteFactory.getClassFromFactory();
            Note currentNote = noteDAO.getNote(currentUser, idNote);
            noteDAO.updateFileNote(currentNote.getId(), null);

            IFileDAO fileDAO = FileFactory.getClassFromFactory();
            fileDAO.removeFile(currentNote.getFile().getId());

            FileDeleteHelper helper = new FileDeleteHelper();
            helper.deletingFile(currentUser, currentNote);

            jump(Controllers.ALLOCATION_CONTROLLER, request, response);
        } catch (DaoException | IOException ex) {
            jump(Pages.MAIN_PAGE, ex.getMessage(), request, response);
        }
    }
}
