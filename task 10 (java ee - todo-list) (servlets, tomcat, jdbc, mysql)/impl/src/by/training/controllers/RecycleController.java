package by.training.controllers;

import java.io.IOException;
import java.util.List;

import by.training.constans.Constants;
import by.training.constans.Pages;
import by.training.exceptions.DaoException;
import by.training.ifaces.AbstractBaseController;
import by.training.ifaces.INoteDAO;
import by.training.model.beans.Note;
import by.training.model.factories.NoteFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RecycleController extends AbstractBaseController {

    private static final long serialVersionUID = 2922069192729546780L;

    @Override
    protected void performTask(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {

        @SuppressWarnings("unchecked")
        List<Note> selectedNotes = (List<Note>) request.getAttribute(
                Constants.KEY_SELECTED_NOTES);
        try {
            INoteDAO nodeDAO = NoteFactory.getClassFromFactory();
            nodeDAO.recycleNotes(selectedNotes);
            jump(Pages.INDEX_PAGE, request, response);
        } catch (DaoException ex) {
            jump(Pages.MAIN_PAGE, ex.getMessage(), request, response);
        }
    }
}
