package by.gsu.epamlab.controller.managers;

import java.util.ResourceBundle;

public class UserManager {
    private static final ResourceBundle resourceBundle = ResourceBundle.getBundle("resources.user");
    private UserManager() {}
    public static String getProperty(String key) {
        return resourceBundle.getString(key);
    }
}
