package by.gsu.epamlab.model.dao;

import by.gsu.epamlab.model.beans.User;
import by.gsu.epamlab.controller.exceptions.DAOException;

public interface UserDAO {
    User getUser(String login, String password) throws DAOException;
    void addUser(String login, String password) throws DAOException;
}
