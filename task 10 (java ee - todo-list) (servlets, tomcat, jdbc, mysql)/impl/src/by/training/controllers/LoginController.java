package by.training.controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import by.training.constans.Constants;
import by.training.constans.Controllers;
import by.training.constans.ErrorMessage;
import by.training.constans.Pages;
import by.training.exceptions.DaoException;
import by.training.ifaces.*;
import by.training.model.beans.User;
import by.training.model.factories.UserFactory;

public class LoginController extends AbstractBaseController {

    private static final long serialVersionUID = -3748371706611735312L;

    @Override
    protected void performTask(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {

        String login = (String) request.getParameter(Constants.KEY_ENTRANCE_LOGIN);
        String password = (String) request.getParameter(Constants.KEY_ENTRANCE_PASSWORD);

        String inputResult = getInputResult(login, password);
        if (inputResult != null) {
            jump(Pages.LOGIN_PAGE, inputResult, request, response);
            return;
        }
        IUserDAO userDAO = UserFactory.getClassFromFactory();
        try {
            User user = userDAO.getUser(login.trim(), password);
            HttpSession session = request.getSession();
            session.setAttribute(Constants.KEY_CURRENT_USER, user);
            jump(Controllers.ALLOCATION_CONTROLLER, request, response);
        } catch (DaoException e) {
            jump(Pages.LOGIN_PAGE, e.getMessage(), request, response);
        }
    }

    private String getInputResult(String login, String password) {
        if (login == null || password == null) {
            return ErrorMessage.LOGIN_OR_PASSWORD_ABSENT_ERROR;
        }
        login = login.trim();
        if (Constants.EMPTY.equals(login)) {
            return ErrorMessage.LOGIN_EMPTY_ERROR;
        }

        if (Constants.EMPTY.equals(password)) {
            return ErrorMessage.PASSWORD_EMPTY_ERROR;
        }
        return null;
    }
}
