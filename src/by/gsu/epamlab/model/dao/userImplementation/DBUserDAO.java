package by.gsu.epamlab.model.dao.userImplementation;

import by.gsu.epamlab.model.beans.User;
import by.gsu.epamlab.model.dao.UserDAO;
import by.gsu.epamlab.controller.exceptions.DAOException;
import by.gsu.epamlab.controller.exceptions.ProfileExistException;
import by.gsu.epamlab.controller.exceptions.WrongAuthenticationException;
import by.gsu.epamlab.controller.database.DBConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static by.gsu.epamlab.controller.constants.DataBaseConstants.QUERY_ADD_USER;
import static by.gsu.epamlab.controller.constants.DataBaseConstants.QUERY_CHECK_EXISTS_USER;
import static by.gsu.epamlab.controller.constants.DataBaseConstants.QUERY_GET_USER;
import static by.gsu.epamlab.controller.constants.MessageConstants.ERROR_INVALID_LOGIN_PASSWORD;
import static by.gsu.epamlab.controller.constants.MessageConstants.ERROR_USER_EXISTS;

public class DBUserDAO implements UserDAO {

    @Override
    public User getUser(String login, String password) throws DAOException {
        try (Connection connection = DBConnector.getConnection();
             PreparedStatement getUser = connection.prepareStatement(QUERY_GET_USER)){
            getUser.setString(1, login);
            getUser.setString(2, password);
            ResultSet resultSet = getUser.executeQuery();
            if (resultSet.first()){
                String userId = String.valueOf(resultSet.getInt(1));
                String userLogin = resultSet.getString(2);
                return new User(userId, userLogin);
            }else {
                throw new WrongAuthenticationException(ERROR_INVALID_LOGIN_PASSWORD);
            }
        }catch (SQLException e){
            throw new DAOException(e);
        }
    }

    @Override
    public void addUser(String login, String password) throws DAOException {
        try (Connection connection = DBConnector.getConnection();
             PreparedStatement statement = connection.prepareStatement(QUERY_ADD_USER);
             PreparedStatement checkExistsUser = connection.prepareStatement(QUERY_CHECK_EXISTS_USER);){
            synchronized (DBUserDAO.class) {
                checkExistsUser(login, checkExistsUser);
                statement.setString(1, login);
                statement.setString(2, password);
                statement.executeUpdate();
            }
        }catch (SQLException e){
            throw new DAOException(e);
        }
    }

    private void checkExistsUser(String login, PreparedStatement checkExistsUser) throws SQLException, ProfileExistException {
        checkExistsUser.setString(1, login);
        if (checkExistsUser.executeQuery().first()){
            throw new ProfileExistException(ERROR_USER_EXISTS);
        }
    }
}
