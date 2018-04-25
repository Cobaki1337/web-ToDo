package by.gsu.epamlab.controller.implementation;

import by.gsu.epamlab.controller.AbstractController;
import by.gsu.epamlab.controller.ApplicationContext;
import by.gsu.epamlab.controller.exceptions.DAOException;
import by.gsu.epamlab.model.beans.Task;
import by.gsu.epamlab.model.beans.User;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static by.gsu.epamlab.controller.constants.AttributeConstants.ATTRIBUTE_USER;
import static by.gsu.epamlab.controller.constants.ParameterConstants.PARAMETER_TASK;

public class AddTaskController extends AbstractController {
    @Override
    protected void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            HttpSession session = req.getSession();
            String jTask = req.getParameter(PARAMETER_TASK);
            Task task = new Gson().fromJson(jTask, Task.class);
            User user = (User) session.getAttribute(ATTRIBUTE_USER);
            ApplicationContext.getTasksDAO().addTask(user.getId(), task.getTaskName(), task.getTaskDate(), task.getTaskContent());
        }catch (DAOException e){
            resp.getWriter().print(e.getMessage());
        }
    }
}
