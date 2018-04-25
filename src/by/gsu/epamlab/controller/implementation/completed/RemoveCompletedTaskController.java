package by.gsu.epamlab.controller.implementation.completed;

import by.gsu.epamlab.controller.AbstractController;
import by.gsu.epamlab.controller.ApplicationContext;
import by.gsu.epamlab.controller.exceptions.DAOException;
import by.gsu.epamlab.model.beans.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static by.gsu.epamlab.controller.constants.AttributeConstants.ATTRIBUTE_USER;
import static by.gsu.epamlab.controller.constants.ParameterConstants.PARAMETER_TASK_ID;

public class RemoveCompletedTaskController extends AbstractController {
    @Override
    protected void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String taskId = req.getParameter(PARAMETER_TASK_ID);
            User user = (User) req.getSession().getAttribute(ATTRIBUTE_USER);
            ApplicationContext.getTasksDAO().removeCompletedTask(taskId, user.getId());
        }catch (DAOException e){
            System.err.println(e.getMessage());
        }
    }
}
