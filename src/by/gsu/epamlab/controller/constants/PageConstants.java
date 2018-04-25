package by.gsu.epamlab.controller.constants;

import by.gsu.epamlab.controller.managers.ConfigurationManager;

public class PageConstants {
    public static final String PAGE_LOGIN = ConfigurationManager.getProperty("page.login");
    public static final String PAGE_HEADER = ConfigurationManager.getProperty("page.header");
    public static final String PAGE_MAIN = ConfigurationManager.getProperty("page.main");
    public static final String PAGE_ROOT = ConfigurationManager.getProperty("page.root");
    public static final String PAGE_REGISTRATION = ConfigurationManager.getProperty("page.registration");
}
