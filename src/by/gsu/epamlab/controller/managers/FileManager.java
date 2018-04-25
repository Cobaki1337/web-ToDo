package by.gsu.epamlab.controller.managers;

import java.util.ResourceBundle;

public class FileManager {
    private static final ResourceBundle resourceBundle = ResourceBundle.getBundle("resources.file");
    private FileManager(){}
    public static String getProperty(String key){
        return resourceBundle.getString(key);
    }
}
