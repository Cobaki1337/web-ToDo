package by.gsu.epamlab.controller.constants;

import by.gsu.epamlab.controller.managers.UserManager;

public class UserConstants {
    public static final String USER_LOGIN = UserManager.getProperty("user.login");
    public static final String USER_PASSWORD = UserManager.getProperty("user.password");
    public static final String USER_REPEAT_PASSWORD = UserManager.getProperty("user.repeat-password");
}
