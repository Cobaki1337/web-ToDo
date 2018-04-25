package by.gsu.epamlab.controller.constants;

import by.gsu.epamlab.controller.managers.ContextManager;

public class ContextConstants {
    public static final String CONTEXT = ContextManager.getProperty("context");
    public static final String CONTEXT_NAME = ContextManager.getProperty("context.dao.name");
    public static final String CONTEXT_DATASOURCE_DB = ContextManager.getProperty("context.datasource.db");
}
