package by.training.controllers;

import java.io.IOException;

import by.training.constans.Pages;
import by.training.ifaces.AbstractBaseController;

import javax.servlet.ServletException;
import javax.servlet.http.*;

public class LogoutController extends AbstractBaseController {

    private static final long serialVersionUID = -6083946295946892079L;

    @Override
    protected void performTask(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {

        // Check the current session for logged on user
        HttpSession currentSession = request.getSession();
        
        currentSession.invalidate();
        response.sendRedirect(request.getContextPath() + Pages.INDEX_PAGE);
    }
}
