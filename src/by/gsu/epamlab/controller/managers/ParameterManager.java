package by.gsu.epamlab.controller.managers;

import java.util.ResourceBundle;

public class ParameterManager {
    private static final ResourceBundle resourceBundle = ResourceBundle.getBundle("resources.parameter");
    private ParameterManager(){}
    public static String getProperty(String key){
        return resourceBundle.getString(key);
    }
}
