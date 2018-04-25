package by.gsu.epamlab.controller.implementation.completed;

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
import java.io.PrintWriter;
import java.util.List;

import static by.gsu.epamlab.controller.constants.AttributeConstants.ATTRIBUTE_USER;

public class GetCompletedTasksController extends AbstractController {
    @Override
    protected void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            PrintWriter writer = resp.getWriter();
            HttpSession session = req.getSession();
            User user = (User) session.getAttribute(ATTRIBUTE_USER);
            List<Task> taskList = ApplicationContext.getTasksDAO().getCompletedTasks(user.getId());
            String completedTasks = new Gson().toJson(taskList);
            writer.print(completedTasks);
        }catch (DAOException e){
            System.err.println(e.getMessage());
        }
    }
}
