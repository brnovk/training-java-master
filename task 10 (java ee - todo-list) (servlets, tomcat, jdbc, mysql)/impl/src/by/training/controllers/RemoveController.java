package by.training.controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.training.constans.Constants;
import by.training.constans.ErrorMessage;
import by.training.constans.Pages;
import by.training.exceptions.DaoException;
import by.training.ifaces.AbstractBaseController;
import by.training.ifaces.INoteDAO;
import by.training.model.beans.Note;
import by.training.model.beans.User;
import by.training.model.factories.NoteFactory;
import by.training.utils.FileDeleteHelper;

public class RemoveController extends AbstractBaseController {

	private static final long serialVersionUID = 8288485730031375449L;

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

		@SuppressWarnings("unchecked")
		List<Note> selectedNotes = (List<Note>) request
				.getAttribute(Constants.KEY_SELECTED_NOTES);
		try {
			INoteDAO nodeDAO = NoteFactory.getClassFromFactory();
			nodeDAO.removeNotes(selectedNotes);
			removeFiles(currentUser, selectedNotes);

			jump(Pages.INDEX_PAGE, request, response);
		} catch (DaoException ex) {
			jump(Pages.MAIN_PAGE, ex.getMessage(), request, response);
		}
	}

	private void removeFiles(User currentUser, List<Note> notes)
			throws IOException {
		FileDeleteHelper helper = new FileDeleteHelper();
		for (Note note : notes) {
			if (note.getFile() != null) {
				helper.deletingFile(currentUser, note);
			}
		}
	}
}
