package by.gsu.epamlab.model.factories;

import by.gsu.epamlab.model.dao.UserDAO;
import by.gsu.epamlab.model.dao.userImplementation.DBUserDAO;
import by.gsu.epamlab.model.dao.userImplementation.RamUserDAO;

public enum  UserImplFactory {
    RAM{
        @Override
        UserDAO getUserImpl() {
            return new RamUserDAO();
        }
    },
    DB{
        @Override
        UserDAO getUserImpl() {
            return new DBUserDAO();
        }
    };
    abstract UserDAO getUserImpl();

    public static UserDAO getUserImplFromFactory(String implementation){
        return valueOf(implementation.toUpperCase()).getUserImpl();
    }
}
