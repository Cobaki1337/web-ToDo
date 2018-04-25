package by.gsu.epamlab.controller.implementation.authentication;

import by.gsu.epamlab.controller.AbstractController;
import by.gsu.epamlab.controller.ApplicationContext;
import by.gsu.epamlab.model.beans.User;
import by.gsu.epamlab.controller.exceptions.DAOException;
import by.gsu.epamlab.controller.exceptions.ValidationException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static by.gsu.epamlab.controller.constants.AttributeConstants.ATTRIBUTE_ERROR;
import static by.gsu.epamlab.controller.constants.AttributeConstants.ATTRIBUTE_USER;
import static by.gsu.epamlab.controller.constants.MessageConstants.ERROR_LOGIN_EMPTY;
import static by.gsu.epamlab.controller.constants.MessageConstants.ERROR_LOGIN_PASSWORD_ABSENT;
import static by.gsu.epamlab.controller.constants.PageConstants.PAGE_LOGIN;
import static by.gsu.epamlab.controller.constants.PageConstants.PAGE_MAIN;
import static by.gsu.epamlab.controller.constants.UserConstants.USER_LOGIN;
import static by.gsu.epamlab.controller.constants.UserConstants.USER_PASSWORD;
import static by.gsu.epamlab.controller.constants.UtilsConstants.EMPTY_STRING;

public class LoginController extends AbstractController {

    @Override
    protected void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            HttpSession session = req.getSession(true);
            if (checkSession(session, resp)){
                return;
            }
            String login = req.getParameter(USER_LOGIN);
            String password = req.getParameter(USER_PASSWORD);
            checkLoginInput(login, password);
            User user = ApplicationContext.getUserDAO().getUser(login, password);
            session.setAttribute(ATTRIBUTE_USER, user);
            resp.sendRedirect(PAGE_MAIN);
        }catch (ValidationException | DAOException e){
            System.err.println(e.getMessage());
            req.setAttribute(ATTRIBUTE_ERROR, e.getMessage());
            req.getRequestDispatcher(PAGE_LOGIN).forward(req, resp);
        }
    }
    private static void checkLoginInput(String login, String password) throws ValidationException {
        if (login == null || password == null){
            throw new ValidationException(ERROR_LOGIN_PASSWORD_ABSENT);
        }
        login = login.trim();
        if (EMPTY_STRING.equals(login)){
            throw new ValidationException(ERROR_LOGIN_EMPTY);
        }
    }
}
