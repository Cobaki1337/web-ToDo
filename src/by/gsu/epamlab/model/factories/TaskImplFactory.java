package by.gsu.epamlab.model.factories;

import by.gsu.epamlab.model.dao.TasksDAO;
import by.gsu.epamlab.model.dao.taskImplementation.DBTaskDAO;

public enum  TaskImplFactory {
    DB{
        @Override
        TasksDAO getTaskImpl() {
            return new DBTaskDAO();
        }
    };
    abstract TasksDAO getTaskImpl();

    public static TasksDAO getTaskImplFromFactory(String implementation){
        return valueOf(implementation.toUpperCase()).getTaskImpl();
    }
}
