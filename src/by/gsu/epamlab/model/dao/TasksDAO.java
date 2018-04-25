package by.gsu.epamlab.model.dao;

import by.gsu.epamlab.controller.exceptions.DAOException;
import by.gsu.epamlab.model.beans.Task;

import java.util.List;

public interface TasksDAO {

    //Usual tasks
    void addTask(String idUser, String taskName, String taskDate, String taskContent) throws DAOException;
    List<Task> getTasks(String idUser, String date) throws DAOException;
    void updateTaskFile(String id, String fileExistence) throws DAOException;

    //Recycle bin
    void addRecycleTask(String id, String idUser) throws DAOException;
    List<Task> getRecycleTasks(String idUser) throws DAOException;
    void removeAllRecycleTasks(String idUser) throws DAOException;
    void removeRecycleTask(String id, String idUser) throws DAOException;
    void recoveryAllRecycleTasks(String idUser) throws DAOException;
    void recoveryRecycleTask(String id, String idUser) throws DAOException;

    //Completed
    void addCompletedTask(String id, String idUser) throws DAOException;
    List<Task> getCompletedTasks(String idUser) throws DAOException;
    void removeAllCompletedTasks(String idUser) throws DAOException;
    void removeCompletedTask(String id, String idUser) throws DAOException;
}
