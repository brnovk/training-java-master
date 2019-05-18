package by.training.controllers;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.training.constans.Constants;
import by.training.constans.Controllers;
import by.training.constans.ErrorMessage;
import by.training.constans.Pages;
import by.training.enums.SublistType;
import by.training.exceptions.DaoException;
import by.training.exceptions.ValidationException;
import by.training.ifaces.AbstractBaseController;
import by.training.ifaces.INoteDAO;
import by.training.model.beans.User;
import by.training.model.factories.NoteFactory;

public class AddNoteController extends AbstractBaseController {

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
        try {
            Date date = getInputDate(request);
            String content = getInputContent(request);
            INoteDAO noteDAO = NoteFactory.getClassFromFactory();
            noteDAO.addNote(content, date, currentUser);
            jump(Controllers.ALLOCATION_CONTROLLER, request, response);
        } catch (ValidationException | DaoException ex) {
            jump(Pages.ADD_NOTE_PAGE, ex.getMessage(), request, response);
        }
    }

    private static Date getInputDate(HttpServletRequest request) 
            throws ValidationException {

        final String DATE_DELIMETER = ".";
        final String FORMAT_DATE = "dd.MM.yyyy";

        HttpSession session = request.getSession();
        SublistType sublistType = (SublistType) session.getAttribute(
                Constants.KEY_SUBLIST_TYPE);
        switch (sublistType) {
            case TODAY:
                return new Date();
            case TOMORROW:
                Calendar calendar = Calendar.getInstance();
                calendar.add(Calendar.DATE, 1);
                return calendar.getTime();
            case SOMEDAY:
                String day = request.getParameter(Constants.KEY_NOTE_DATE_DAY);
                String month = request.getParameter(Constants.KEY_NOTE_DATE_MONTH);
                String year = request.getParameter(Constants.KEY_NOTE_DATE_YEAR);
                if ((day == null) || (month == null) || (year == null)) {
                    throw new ValidationException(ErrorMessage.DATE_ABSENT_ERROR);
                }
                String rawDate = day + DATE_DELIMETER + month + DATE_DELIMETER + year;
                try {
                    DateFormat formatter = new SimpleDateFormat(FORMAT_DATE);
                    formatter.setLenient(false);
                    return formatter.parse(rawDate);
                } catch (ParseException e) {
                    throw new ValidationException(e.getMessage());
                }
            default:
                throw new ValidationException(ErrorMessage.INCORRECT_VALUE_SUBLISTS);
        }
    }

    private static String getInputContent(HttpServletRequest request)
            throws ValidationException, UnsupportedEncodingException {
    	
        final int MAX_LENGTH_CONTENT_NOTE = 1000;
        String noteContent = request.getParameter(Constants.KEY_NOTE_CONTENT);
        if (noteContent == null) {
            throw new ValidationException(ErrorMessage.CONTENT_ABSENT_ERROR);
        }
        noteContent = noteContent.trim();
        if (Constants.EMPTY.equals(noteContent)) {
            throw new ValidationException(ErrorMessage.CONTENT_EMPTY_ERROR);
        }
        if (noteContent.length() > MAX_LENGTH_CONTENT_NOTE) {
            throw new ValidationException(ErrorMessage.CONTENT_UNACCEPTABLY_LONG_ERROR);
        }
		return new String(noteContent.getBytes("ISO-8859-1"),"UTF-8");
    }
}
