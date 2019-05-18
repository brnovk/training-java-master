package by.training.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import by.training.constans.Constants;
import by.training.constans.ErrorMessage;
import by.training.constans.Pages;
import by.training.enums.ActionType;
import by.training.exceptions.ValidationException;
import by.training.ifaces.AbstractBaseController;
import by.training.model.beans.Note;
import by.training.model.beans.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ActionController extends AbstractBaseController {

    private static final long serialVersionUID = -2688953365710569815L;

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

        ActionType actionType = getActionType(request);
        try {
            String[] selectedFlags = request.getParameterValues(
                    Constants.KEY_SELECTED_FLAGS);
            HttpSession session = request.getSession();

            @SuppressWarnings("unchecked")
            List<Note> sourceNotes = (List<Note>) session
                    .getAttribute(Constants.KEY_SUBLIST);
            List<Note> selectedNotes = getSelectedNotes(selectedFlags,
                    sourceNotes);
            request.setAttribute(Constants.KEY_SELECTED_NOTES, selectedNotes);
            request.getRequestDispatcher(actionType.getController()).forward(
                    request, response);
        } catch (ValidationException ex) {
            jump(Pages.MAIN_PAGE, ex.getMessage(), request, response);
        }
    }

    private static List<Note> getSelectedNotes(String[] selectedFlags,
            List<Note> notes) throws ValidationException {
        if (selectedFlags == null) {
            throw new ValidationException(ErrorMessage.NO_SELECT_FLAG_ERROR);
        }
        List<Note> selectedList = new ArrayList<Note>();

        for (String rawNumberNote : selectedFlags) {
            int indexNote = Integer.parseInt(rawNumberNote);
            for (Note currentNote : notes) {
                if (currentNote.getId() == indexNote) {
                    selectedList.add(currentNote);
                    break;
                }
            }
        }
        return selectedList;
    }

    private ActionType getActionType(HttpServletRequest request) {
        for (ActionType currentAction : ActionType.values()) {
            if (request.getParameter(
                    currentAction.toString().toLowerCase()) != null) {
                return currentAction;
            }
        }
        return null;
    }
}
