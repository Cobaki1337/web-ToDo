package by.gsu.epamlab.controller.managers;

import java.util.ResourceBundle;

public class ContextManager {
    private static final ResourceBundle resourceBundle = ResourceBundle.getBundle("resources.context");
    private ContextManager(){}
    public static String getProperty(String key){
        return resourceBundle.getString(key);
    }
}
