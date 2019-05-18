package by.training.controllers;

import java.io.IOException;

import by.training.constans.Constants;
import by.training.constans.ErrorMessage;
import by.training.constans.Pages;
import by.training.exceptions.DaoException;
import by.training.exceptions.ValidationException;
import by.training.ifaces.AbstractBaseController;
import by.training.ifaces.IUserDAO;
import by.training.model.beans.User;
import by.training.model.factories.UserFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class RegisterController extends AbstractBaseController {

    private static final long serialVersionUID = -7610657925011371859L;

    @Override
    protected void performTask(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {

        String login = request.getParameter(Constants.KEY_REGISTER_LOGIN);
        String password = request.getParameter(Constants.KEY_REGISTER_PASSWORD);
        String retypePassword
                = request.getParameter(Constants.KEY_REGISTER_RETYPE_PASSWORD);
        String email = request.getParameter(Constants.KEY_REGISTER_EMAIL);

        try {
            validationInputData(login, password, retypePassword, email);
            IUserDAO userDAO = UserFactory.getClassFromFactory();
            User user = userDAO.setUser(login, password,email);
            HttpSession session = request.getSession();
            session.setAttribute(Constants.KEY_CURRENT_USER, user);
            jump(Pages.INDEX_PAGE, request, response);
        } catch (ValidationException | DaoException ex) {
            jump(Pages.REGISTER_PAGE, ex.getMessage(), request, response);
        }
    }

    private static void validationInputData(String login, String password,
            String retypePassword, String email) throws ValidationException {

        if (login == null || password == null || retypePassword == null
                || email == null) {
            throw new ValidationException(
            		ErrorMessage.ONE_OR_MORE_VALUES_ABSENT_ERROR);
        }

        login = login.trim();
        if (Constants.EMPTY.equals(login)) {
            throw new ValidationException(ErrorMessage.LOGIN_EMPTY_ERROR);
        }

        if (Constants.EMPTY.equals(password)) {
            throw new ValidationException(ErrorMessage.PASSWORD_EMPTY_ERROR);
        }

        if (!password.equals(retypePassword)) {
            throw new ValidationException(ErrorMessage.PASSWORD_AND_REPEAT_DO_NOT_MATCH);
        }

        email = email.trim();
        if (Constants.EMPTY.equals(email)) {
            throw new ValidationException(ErrorMessage.EMAIL_EMPTY_ERROR);
        }
    }
}
