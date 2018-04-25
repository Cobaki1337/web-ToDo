package by.gsu.epamlab.controller.implementation.recyclebin;

import by.gsu.epamlab.controller.AbstractController;
import by.gsu.epamlab.controller.ApplicationContext;
import by.gsu.epamlab.controller.exceptions.DAOException;
import by.gsu.epamlab.model.beans.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static by.gsu.epamlab.controller.constants.AttributeConstants.ATTRIBUTE_USER;

public class RemoveAllRecycleTasksController extends AbstractController {
    @Override
    protected void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            User user = (User) req.getSession().getAttribute(ATTRIBUTE_USER);
            ApplicationContext.getTasksDAO().removeAllRecycleTasks(user.getId());
        }catch (DAOException e){
            System.err.println(e.getMessage());
        }
    }
}
