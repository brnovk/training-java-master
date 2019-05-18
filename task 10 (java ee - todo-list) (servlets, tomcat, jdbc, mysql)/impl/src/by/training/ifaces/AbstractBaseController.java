package by.training.ifaces;

import java.io.IOException;

import javax.servlet.*;
import javax.servlet.http.*;

import by.training.constans.Constants;
import by.training.constans.JDBCPropertiesPath;
import by.training.constans.UploadPath;
import by.training.model.beans.User;

public abstract class AbstractBaseController extends HttpServlet {

    private static boolean isInitializeUploadPath;
    private static boolean isInitializeJDBCPropertiesPath;

    @Override
    public void init() throws ServletException {
        super.init();
        final String PROJECT_ROOT = "";
        if (!isInitializeUploadPath) {
            String realPath = getServletContext().getRealPath(PROJECT_ROOT);
            UploadPath.initialize(realPath);
            isInitializeUploadPath = true;
        }
        if (!isInitializeJDBCPropertiesPath) {
            String realPath = getServletContext().getRealPath(PROJECT_ROOT);
            JDBCPropertiesPath.initialize(realPath);
            isInitializeJDBCPropertiesPath = true;
        }
    }

    private static final long serialVersionUID = 1799028902733411448L;

    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        performTask(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        performTask(request, response);
    }

    abstract protected void performTask(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException;

    protected void jump(String url, String message, HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute(Constants.KEY_ERROR_MESSAGE, message);
        RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
        rd.forward(request, response);
    }

    protected void jump(String url, HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
        rd.forward(request, response);
        return;
    }

    protected User getSessionUser(HttpServletRequest request)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        return (User) session.getAttribute(Constants.KEY_CURRENT_USER);
    }
}
