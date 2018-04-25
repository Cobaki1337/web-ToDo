package by.gsu.epamlab.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static by.gsu.epamlab.controller.constants.AttributeConstants.ATTRIBUTE_USER;
import static by.gsu.epamlab.controller.constants.PageConstants.PAGE_ROOT;

public abstract class AbstractController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    protected boolean checkSession(HttpSession session, HttpServletResponse response) throws IOException {
        if (session.getAttribute(ATTRIBUTE_USER) != null){
            response.sendRedirect(PAGE_ROOT);
            return true;
        }else {
            return false;
        }
    }

    abstract protected void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException;
}
