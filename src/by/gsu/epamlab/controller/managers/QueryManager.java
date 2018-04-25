package by.gsu.epamlab.controller.managers;

import java.util.ResourceBundle;

public class QueryManager {
    private static final ResourceBundle resourceBundle = ResourceBundle.getBundle("resources.query");
    private QueryManager() {}
    public static String getProperty(String key) {
        return resourceBundle.getString(key);
    }
}
