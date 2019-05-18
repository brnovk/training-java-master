package by.training.controllers;

import java.io.IOException;
import java.util.List;

import by.training.constans.Constants;
import by.training.constans.ErrorMessage;
import by.training.constans.Pages;
import by.training.enums.SublistType;
import by.training.exceptions.DaoException;
import by.training.ifaces.AbstractBaseController;
import by.training.ifaces.INoteDAO;
import by.training.model.beans.Note;
import by.training.model.beans.User;
import by.training.model.factories.NoteFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet-controller of distribution lists. Selects the specified query type 
 * sublists (or stored in the session), and pushes the sublist in the session.
 * 
 * @author BaranauViktar
 */
public class AllocationController extends AbstractBaseController {
	
	private static final long serialVersionUID = 7798632458964885538L;

    @Override
    protected void performTask(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
    	
    	// Check for a user in the current session. If the user does not exist, 
    	// then forward to the main page and display the welcome message.
    	User currentUser = getSessionUser(request);
    	if (currentUser == null) {
    		request.setAttribute(Constants.KEY_ABOUT_MESSAGE, 
    				ErrorMessage.WELCOM_MESSAGE);
    		jump(Pages.MAIN_PAGE, request, response);
    		return;
    	}
    	try {
    		HttpSession session = request.getSession();
    		
    		// Get the current type in the session.
    		SublistType currentType = (SublistType) session.getAttribute(
    				Constants.KEY_SUBLIST_TYPE);
    		
    		SublistType newType = getSublistType(request, currentType);
    		session.setAttribute(Constants.KEY_SUBLIST_TYPE, newType);

    		List<Note> notes = getSublist(newType, currentUser);
            session.setAttribute(Constants.KEY_SUBLIST, notes);
            jump(Pages.MAIN_PAGE, request, response);
        } catch (DaoException e) {
        	//e.printStackTrace();
            jump(Pages.MAIN_PAGE, e.getMessage(), request, response);
        }
    }
    
    
    /**
     * Getting the value of the "sublists" selected by the user.
     * Returns one of the following values: TODAY, TOMORROW, 
     * SOMEDAY, FIXED or RECYCLE.
     * If neither is set, it returns the old value of the parameter.
     * If old null, returns TODAY.
     * 
     * @param request An incoming request from a page.
     * @param oldType The old value of the sublists (of the session).
     * @return TODAY, TOMORROW, SOMEDAY, FIXED or RECYCLE.
     */
    private SublistType getSublistType(HttpServletRequest request, 
    		SublistType oldType) {
    	for (SublistType currentAction : SublistType.values()) {
    		if (request.getParameter(
    				currentAction.toString().toLowerCase())!=null) {
    			return currentAction;
    		}
    	}
    	return (oldType == null) ? SublistType.TODAY : oldType;
    }
    
    /**
     * Gets a list of annotations for the user, depending on the type 
     * of sublists: TODAY, TOMORROW, SOMEDAY, FIXED or RECYCLE.
     * 
     * @param sublistType 
     * @param user The user for which you want to select notes.
     * @return List of notes.
     * @throws DaoException Thrown when a DAO application layer.
     */
    private List<Note> getSublist(SublistType sublistType, User user) 
    		throws DaoException {
    	
    	INoteDAO noteDAO = NoteFactory.getClassFromFactory();
    	switch(sublistType) {
    		case TODAY:
    			return noteDAO.getTodayNotes(user);
    		case TOMORROW:
    			return noteDAO.getTomorrowNotes(user);
    		case SOMEDAY:
    			return noteDAO.getSomedayNotes(user);
    		case FIXED:
    			return noteDAO.getPerformedNotes(user);
    		case RECYCLE:
    			return noteDAO.getRecycleNotes(user);
    		default:
    			throw new IllegalArgumentException(sublistType.toString());
    	}
    }
}
