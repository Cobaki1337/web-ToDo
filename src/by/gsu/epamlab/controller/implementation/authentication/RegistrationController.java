package by.gsu.epamlab.controller.implementation.authentication;

import by.gsu.epamlab.controller.AbstractController;
import by.gsu.epamlab.controller.ApplicationContext;
import by.gsu.epamlab.controller.exceptions.DAOException;
import by.gsu.epamlab.controller.exceptions.ValidationException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static by.gsu.epamlab.controller.constants.AttributeConstants.ATTRIBUTE_ERROR;
import static by.gsu.epamlab.controller.constants.AttributeConstants.ATTRIBUTE_REGISTRATION;
import static by.gsu.epamlab.controller.constants.MessageConstants.ERROR_INVALID_PASSWORD;
import static by.gsu.epamlab.controller.constants.MessageConstants.ERROR_LOGIN_PASSWORD_ABSENT;
import static by.gsu.epamlab.controller.constants.MessageConstants.REGISTRATION_SUCCESSFUL;
import static by.gsu.epamlab.controller.constants.PageConstants.PAGE_LOGIN;
import static by.gsu.epamlab.controller.constants.PageConstants.PAGE_REGISTRATION;
import static by.gsu.epamlab.controller.constants.UserConstants.USER_LOGIN;
import static by.gsu.epamlab.controller.constants.UserConstants.USER_PASSWORD;
import static by.gsu.epamlab.controller.constants.UserConstants.USER_REPEAT_PASSWORD;
import static by.gsu.epamlab.controller.constants.UtilsConstants.EMPTY_STRING;

public class RegistrationController extends AbstractController {
    @Override
    protected void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            HttpSession session = req.getSession();
            if (checkSession(session, resp)){
                return;
            }
            String login = req.getParameter(USER_LOGIN);
            String password = req.getParameter(USER_PASSWORD);
            String repeatPassword = req.getParameter(USER_REPEAT_PASSWORD);
            checkRegistrationInput(login, password, repeatPassword);
            ApplicationContext.getUserDAO().addUser(login, password);
            req.setAttribute(ATTRIBUTE_REGISTRATION, REGISTRATION_SUCCESSFUL);
            req.getRequestDispatcher(PAGE_LOGIN).forward(req, resp);
        }catch (DAOException e){
            System.err.println(e.getMessage());
            req.setAttribute(ATTRIBUTE_ERROR, e.getMessage());
            req.getRequestDispatcher(PAGE_LOGIN).forward(req, resp);
        }catch (ValidationException e){
            System.err.println(e.getMessage());
            req.setAttribute(ATTRIBUTE_ERROR, e.getMessage());
            req.getRequestDispatcher(PAGE_REGISTRATION).forward(req, resp);
        }
    }

    private static void checkRegistrationInput(String login, String password, String repeatPassword) throws ValidationException {
        if (EMPTY_STRING.equals(password) || EMPTY_STRING.equals(repeatPassword) || EMPTY_STRING.equals(login.trim())){
            throw new ValidationException(ERROR_LOGIN_PASSWORD_ABSENT);
        }
        if (!(password.equals(repeatPassword))){
            throw new ValidationException(ERROR_INVALID_PASSWORD);
        }
    }
}
