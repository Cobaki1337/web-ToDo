package by.gsu.epamlab.controller.implementation;

import by.gsu.epamlab.controller.AbstractController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static by.gsu.epamlab.controller.constants.PageConstants.PAGE_MAIN;

public class StartController extends AbstractController{
    @Override
    protected void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(PAGE_MAIN).forward(req, resp);
    }
}
