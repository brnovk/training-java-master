package by.training.controllers;

import java.io.IOException;

import by.training.constans.Constants;
import by.training.constans.Controllers;
import by.training.constans.ErrorMessage;
import by.training.constans.Pages;
import by.training.exceptions.DaoException;
import by.training.exceptions.UploadException;
import by.training.ifaces.AbstractBaseController;
import by.training.ifaces.IFileDAO;
import by.training.ifaces.INoteDAO;
import by.training.model.beans.File;
import by.training.model.beans.User;
import by.training.model.factories.FileFactory;
import by.training.model.factories.NoteFactory;
import by.training.utils.FileUploadHelper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FileUploadController extends AbstractBaseController {

    private static final long serialVersionUID = -428008052743661407L;

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
            FileUploadHelper helper = new FileUploadHelper(currentUser, request);
            helper.upload();
            int idNote = helper.getUploadedIdNote();
            String fileName = helper.getUploadedFileName();

            IFileDAO fileDAO = FileFactory.getClassFromFactory();
            File file = fileDAO.setFile(fileName);

            INoteDAO noteDAO = NoteFactory.getClassFromFactory();
            noteDAO.updateFileNote(idNote, file);

            jump(Controllers.ALLOCATION_CONTROLLER, request, response);
        } catch (UploadException | DaoException ex) {
            jump(Pages.MAIN_PAGE, ex.getMessage(), request, response);
        }
    }
}
