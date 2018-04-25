package by.gsu.epamlab.controller.managers;

import java.util.ResourceBundle;

public class AttributeManager {
    private static final ResourceBundle resourceBundle = ResourceBundle.getBundle("resources.attribute");
    private AttributeManager(){}
    public static String getProperty(String key){
        return resourceBundle.getString(key);
    }
}
