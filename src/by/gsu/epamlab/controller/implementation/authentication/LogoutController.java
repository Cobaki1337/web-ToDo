package by.gsu.epamlab.controller.implementation.authentication;

import by.gsu.epamlab.controller.AbstractController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static by.gsu.epamlab.controller.constants.PageConstants.PAGE_ROOT;

public class LogoutController extends AbstractController {
    @Override
    protected void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().invalidate();
        resp.sendRedirect(PAGE_ROOT);
    }
}
