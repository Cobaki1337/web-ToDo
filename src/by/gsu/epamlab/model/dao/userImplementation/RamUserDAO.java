package by.gsu.epamlab.model.dao.userImplementation;

import by.gsu.epamlab.model.beans.User;
import by.gsu.epamlab.model.dao.UserDAO;
import by.gsu.epamlab.controller.exceptions.ProfileExistException;
import by.gsu.epamlab.controller.exceptions.WrongAuthenticationException;

import java.util.HashMap;
import java.util.Map;

import static by.gsu.epamlab.controller.constants.MessageConstants.ERROR_INVALID_LOGIN_PASSWORD;
import static by.gsu.epamlab.controller.constants.MessageConstants.ERROR_USER_EXISTS;

public class RamUserDAO implements UserDAO {
    private static final Map<String, UserRam> users = new HashMap<>();
    private static int userIdCounter = 1;

    @Override
    public User getUser(String login, String password) throws WrongAuthenticationException {
        UserRam userRam = users.get(login);
        if (userRam != null && password.equals(userRam.getPassword())){
            return new User(userRam.getId(), userRam.getLogin());
        }
        throw new WrongAuthenticationException(ERROR_INVALID_LOGIN_PASSWORD);
    }

    @Override
    public void addUser(String login, String password) throws ProfileExistException {
        synchronized (RamUserDAO.class){
            if (users.get(login) != null){
                throw new ProfileExistException(ERROR_USER_EXISTS);
            }
            String id = String.valueOf(userIdCounter);
            userIdCounter ++;
            UserRam userRam = new UserRam(id, login, password);
            users.put(login, userRam);
        }
    }
    private static class UserRam{
        private String id;
        private String login;
        private String password;

        UserRam(String id, String login, String password) {
            this.id = id;
            this.login = login;
            this.password = password;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getLogin() {
            return login;
        }

        public void setLogin(String login) {
            this.login = login;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            UserRam userRam = (UserRam) o;

            if (!login.equals(userRam.login)) return false;
            return password.equals(userRam.password);
        }
        @Override
        public int hashCode() {
            int result = login.hashCode();
            result = 31 * result + password.hashCode();
            return result;
        }
    }
}
