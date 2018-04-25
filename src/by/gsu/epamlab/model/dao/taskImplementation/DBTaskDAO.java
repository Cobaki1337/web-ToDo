package by.gsu.epamlab.model.dao.taskImplementation;

import by.gsu.epamlab.controller.exceptions.DAOException;
import by.gsu.epamlab.model.beans.Task;
import by.gsu.epamlab.model.dao.TasksDAO;
import by.gsu.epamlab.controller.database.DBConnector;
import by.gsu.epamlab.model.dates.DateUtil;

import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static by.gsu.epamlab.controller.constants.DataBaseConstants.*;
import static by.gsu.epamlab.controller.constants.UtilsConstants.DATE_FORMAT;

public class DBTaskDAO implements TasksDAO {

    @Override
    public void addTask(String idUser, String taskName, String taskDate, String taskContent) throws DAOException {
        try (Connection connection = DBConnector.getConnection();
             PreparedStatement addTask = connection.prepareStatement(QUERY_ADD_TASK) ){
            addTask.setString(1, idUser);
            addTask.setString(2, taskName);
            addTask.setString(3, taskDate);
            addTask.setString(4, taskContent);
            addTask.executeUpdate();
        }catch (SQLException e){
            throw new DAOException(e);
        }
    }

    @Override
    public List<Task> getTasks(String idUser, String date) throws DAOException {
        try (Connection connection = DBConnector.getConnection();
             PreparedStatement getTasks = connection.prepareStatement(QUERY_GET_TASKS);
             PreparedStatement getTasksDate = connection.prepareStatement(QUERY_GET_TASKS_DATE)){
            List<Task> tasks = new ArrayList<>();
            if (date.isEmpty()){
                getTasks.setString(1, idUser);
                ResultSet resultSet = getTasks.executeQuery();
                while (resultSet.next()){
                    if (checkDate(resultSet.getString(4))){
                        tasks.add(initTask(resultSet));
                    }
                }
            }else {
                getTasksDate.setString(1, idUser);
                getTasksDate.setString(2, date);
                ResultSet resultSet = getTasksDate.executeQuery();
                while (resultSet.next()){
                    tasks.add(initTask(resultSet));
                }
            }
            return tasks;
        }catch (SQLException e){
            throw new DAOException(e);
        }
    }

    @Override
    public void updateTaskFile(String id, String fileExistence) throws DAOException {
        try (Connection connection = DBConnector.getConnection();
             PreparedStatement updateTaskFile = connection.prepareStatement(QUERY_UPDATE_TASK_FILE)){
            updateTaskFile.setString(1, fileExistence);
            updateTaskFile.setString(2, id);
            updateTaskFile.executeUpdate();
        }catch (SQLException e){
            throw new DAOException(e);
        }
    }

    //Recycle bin
    @Override
    public void addRecycleTask(String id, String idUser) throws DAOException {
        try (Connection connection = DBConnector.getConnection();
             PreparedStatement getTask = connection.prepareStatement(QUERY_GET_TASK);
             PreparedStatement removeTask = connection.prepareStatement(QUERY_REMOVE_TASK);
             PreparedStatement addRecycleTask = connection.prepareStatement(QUERY_ADD_RECYCLETASK)){
            getTask.setString(1, id);
            getTask.setString(2, idUser);
            ResultSet resultSet = getTask.executeQuery();

            if (resultSet.first()){
                removeTask.setString(1, id);
                removeTask.setString(2, idUser);
                removeTask.executeUpdate();

                String taskName = resultSet.getString(3);
                String taskDate = resultSet.getString(4);
                String taskContent = resultSet.getString(5);
                String taskFile = resultSet.getString(6);

                addRecycleTask.setString(1, id);
                addRecycleTask.setString(2, idUser);
                addRecycleTask.setString(3, taskName);
                addRecycleTask.setString(4, taskDate);
                addRecycleTask.setString(5, taskContent);
                addRecycleTask.setString(6, taskFile);
                addRecycleTask.executeUpdate();
            }
        }catch (SQLException e){
            throw new DAOException(e);
        }
    }

    @Override
    public List<Task> getRecycleTasks(String idUser) throws DAOException {
        try (Connection connection = DBConnector.getConnection();
             PreparedStatement getRecycleTasks = connection.prepareStatement(QUERY_GET_RECYCLETASKS)){
            List<Task> recycleTasks = new ArrayList<>();
            getRecycleTasks.setString(1, idUser);
            ResultSet resultSet = getRecycleTasks.executeQuery();
            while (resultSet.next()){
                recycleTasks.add(initTask(resultSet));
            }
            return recycleTasks;
        }catch (SQLException e){
            throw new DAOException(e);
        }
    }

    @Override
    public void removeAllRecycleTasks(String idUser) throws DAOException {
        try (Connection connection = DBConnector.getConnection();
             PreparedStatement removeAllRecycleTasks = connection.prepareStatement(QUERY_REMOVE_ALL_RECYCLETASKS)){
            removeAllRecycleTasks.setString(1, idUser);
            removeAllRecycleTasks.executeUpdate();
        }catch (SQLException e){
            throw new DAOException(e);
        }
    }

    @Override
    public void removeRecycleTask(String id, String idUser) throws DAOException {
        try (Connection connection = DBConnector.getConnection();
             PreparedStatement removeRecycleTask = connection.prepareStatement(QUERY_REMOVE_RECYCLETASK)){
            removeRecycleTask.setString(1, id);
            removeRecycleTask.setString(2, idUser);
            removeRecycleTask.executeUpdate();
        }catch (SQLException e){
            throw new DAOException(e);
        }
    }

    @Override
    public void recoveryAllRecycleTasks(String idUser) throws DAOException {
        try (Connection connection = DBConnector.getConnection();
             PreparedStatement getRecycleTasks = connection.prepareStatement(QUERY_GET_RECYCLETASKS);
             PreparedStatement addTasks = connection.prepareStatement(QUERY_RECOVERY_TASK)){
            getRecycleTasks.setString(1, idUser);
            ResultSet resultSet = getRecycleTasks.executeQuery();

            removeAllRecycleTasks(idUser);

            while (resultSet.next()){
                String idTask = resultSet.getString(1);
                String userId = resultSet.getString(2);
                String taskName = resultSet.getString(3);
                String taskDate = resultSet.getString(4);
                String taskContent = resultSet.getString(5);
                String taskFile = resultSet.getString(6);

                addTasks.setString(1, idTask);
                addTasks.setString(2, userId);
                addTasks.setString(3, taskName);
                addTasks.setString(4, taskDate);
                addTasks.setString(5, taskContent);
                addTasks.setString(6, taskFile);
                addTasks.executeUpdate();
            }
        }catch (SQLException e){
            throw new DAOException(e);
        }
    }

    @Override
    public void recoveryRecycleTask(String id, String idUser) throws DAOException {
        try (Connection connection = DBConnector.getConnection();
             PreparedStatement getRecycleTask = connection.prepareStatement(QUERY_GET_RECYCLETASK);
             PreparedStatement removeRecycleTask = connection.prepareStatement(QUERY_REMOVE_RECYCLETASK);
             PreparedStatement addTask = connection.prepareStatement(QUERY_RECOVERY_TASK)){
            getRecycleTask.setString(1, id);
            getRecycleTask.setString(2, idUser);
            ResultSet resultSet = getRecycleTask.executeQuery();

            if (resultSet.first()){
                removeRecycleTask.setString(1, id);
                removeRecycleTask.setString(2, idUser);
                removeRecycleTask.executeUpdate();

                String taskName = resultSet.getString(3);
                String taskDate = resultSet.getString(4);
                String taskContent = resultSet.getString(5);
                String taskFile = resultSet.getString(6);

                addTask.setString(1, id);
                addTask.setString(2, idUser);
                addTask.setString(3, taskName);
                addTask.setString(4, taskDate);
                addTask.setString(5, taskContent);
                addTask.setString(6, taskFile);
                addTask.executeUpdate();
            }
        }catch (SQLException e){
            throw new DAOException(e);
        }
    }

    //Completed

    @Override
    public void addCompletedTask(String id, String idUser) throws DAOException {
        DateFormat formatter = new SimpleDateFormat(DATE_FORMAT);
        String currentDate = formatter.format(new java.util.Date());

        try (Connection connection = DBConnector.getConnection();
             PreparedStatement getTask = connection.prepareStatement(QUERY_GET_TASK);
             PreparedStatement removeTask = connection.prepareStatement(QUERY_REMOVE_TASK);
             PreparedStatement addCompletedTask = connection.prepareStatement(QUERY_ADD_COMPLETED_TASK)){
            getTask.setString(1, id);
            getTask.setString(2, idUser);
            ResultSet resultSet = getTask.executeQuery();

            if (resultSet.first()){
                removeTask.setString(1, id);
                removeTask.setString(2, idUser);
                removeTask.executeUpdate();

                String taskName = resultSet.getString(3);
                String taskContent = resultSet.getString(5);
                String taskFile = resultSet.getString(6);

                addCompletedTask.setString(1, id);
                addCompletedTask.setString(2, idUser);
                addCompletedTask.setString(3, taskName);
                addCompletedTask.setString(4, currentDate);
                addCompletedTask.setString(5, taskContent);
                addCompletedTask.setString(6, taskFile);
                addCompletedTask.executeUpdate();
            }
        }catch (SQLException e){
            throw new DAOException(e);
        }
    }

    @Override
    public List<Task> getCompletedTasks(String idUser) throws DAOException {
        try (Connection connection = DBConnector.getConnection();
             PreparedStatement getCompletedTasks = connection.prepareStatement(QUERY_GET_COMPLETED_TASKS)){
            List<Task> completedTasks = new ArrayList<>();
            getCompletedTasks.setString(1, idUser);
            ResultSet resultSet = getCompletedTasks.executeQuery();
            while (resultSet.next()){
                completedTasks.add(initTask(resultSet));
            }
            return completedTasks;
        }catch (SQLException e){
            throw new DAOException(e);
        }
    }

    @Override
    public void removeAllCompletedTasks(String idUser) throws DAOException {
        try (Connection connection = DBConnector.getConnection();
             PreparedStatement removeAllCompletedTasks = connection.prepareStatement(QUERY_REMOVE_ALL_COMPLETED_TASKS)){
            removeAllCompletedTasks.setString(1, idUser);
            removeAllCompletedTasks.executeUpdate();
        }catch (SQLException e){
            throw new DAOException(e);
        }
    }

    @Override
    public void removeCompletedTask(String id, String idUser) throws DAOException {
        try (Connection connection = DBConnector.getConnection();
             PreparedStatement removeCompletedTask = connection.prepareStatement(QUERY_REMOVE_COMPLETED_TASK)){
            removeCompletedTask.setString(1, id);
            removeCompletedTask.setString(2, idUser);
            removeCompletedTask.executeUpdate();
        }catch (SQLException e){
            throw new DAOException(e);
        }
    }


    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////

    private Task initTask(ResultSet resultSet) throws SQLException {
        return new Task(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3),
                resultSet.getString(4), resultSet.getString(5), resultSet.getString(6));
    }

    private boolean checkDate(String date){
        DateFormat formatter = new SimpleDateFormat(DATE_FORMAT);

        String today = formatter.format(new java.util.Date());
        String tomorrow = formatter.format(DateUtil.addDays(new Date(), 1));

        return !date.equals(today) && !date.equals(tomorrow);
    }
}

