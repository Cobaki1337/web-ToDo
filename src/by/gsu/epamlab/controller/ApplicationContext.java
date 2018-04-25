package by.gsu.epamlab.controller;

import by.gsu.epamlab.model.dao.FileDAO;
import by.gsu.epamlab.model.dao.TasksDAO;
import by.gsu.epamlab.model.dao.UserDAO;
import by.gsu.epamlab.model.factories.FileImplFactory;
import by.gsu.epamlab.model.factories.TaskImplFactory;
import by.gsu.epamlab.model.factories.UserImplFactory;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import static by.gsu.epamlab.controller.constants.ContextConstants.*;

public class ApplicationContext implements ServletContextListener {
    private static UserDAO userDAO;
    private static TasksDAO tasksDAO;
    private static FileDAO fileDAO;
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        try {
            Context env = (Context)new InitialContext().lookup(CONTEXT);
            String dao = (String) env.lookup(CONTEXT_NAME);
            userDAO = UserImplFactory.getUserImplFromFactory(dao);
            tasksDAO = TaskImplFactory.getTaskImplFromFactory(dao);
            fileDAO = FileImplFactory.getFileImplFromFactory(dao);
        }catch (NamingException e){
            e.printStackTrace();
        }
    }
    public static UserDAO getUserDAO(){
        return userDAO;
    }
    public static TasksDAO getTasksDAO(){
        return tasksDAO;
    }
    public static FileDAO getFileDAO(){
        return fileDAO;
    }
}
