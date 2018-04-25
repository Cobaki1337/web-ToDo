package by.gsu.epamlab.controller.constants;

import by.gsu.epamlab.controller.managers.QueryManager;

public class DataBaseConstants {
    //For users
    public static final String QUERY_GET_USER = QueryManager.getProperty("query.user.get-user");
    public static final String QUERY_ADD_USER = QueryManager.getProperty("query.user.add-user");
    public static final String QUERY_CHECK_EXISTS_USER = QueryManager.getProperty("query.user.check-exists-user");

    //For files
    public static final String QUERY_ADD_FILE = QueryManager.getProperty("query.file.add-file");
    public static final String QUERY_GET_FILE = QueryManager.getProperty("query.file.get-file");
    public static final String QUERY_REMOVE_FILE = QueryManager.getProperty("query.file.remove-file");
    public static final String QUERY_GET_FILE_NAME = QueryManager.getProperty("query.file.get-file-name");
    public static final String QUERY_GET_FILES_ID = QueryManager.getProperty("query.file.get-files-id");

    //For initialization tables
    public static final String QUERY_GET_TASKS = QueryManager.getProperty("query.task.get-tasks");
    public static final String QUERY_GET_TASK = QueryManager.getProperty("query.task.get-task");
    public static final String QUERY_GET_TASKS_DATE = QueryManager.getProperty("query.task.get-tasks-date");
    public static final String QUERY_ADD_TASK = QueryManager.getProperty("query.task.add-task");
    public static final String QUERY_UPDATE_TASK_FILE = QueryManager.getProperty("query.task.update-task-file");
    public static final String QUERY_REMOVE_TASK = QueryManager.getProperty("query.task.remove-task");

    //For recycle bin
    public static final String QUERY_ADD_RECYCLETASK = QueryManager.getProperty("query.recycle-bin.add-task");
    public static final String QUERY_GET_RECYCLETASKS = QueryManager.getProperty("query.recycle-bin.get-tasks");
    public static final String QUERY_GET_RECYCLETASK = QueryManager.getProperty("query.recycle-bin.get-task");
    public static final String QUERY_REMOVE_ALL_RECYCLETASKS = QueryManager.getProperty("query.recycle-bin.remove-all-tasks");
    public static final String QUERY_REMOVE_RECYCLETASK = QueryManager.getProperty("query.recycle-bin.remove-task");
    public static final String QUERY_RECOVERY_TASK = QueryManager.getProperty("query.recycle-bin.recovery-task");

    //For completed tasks
    public static final String QUERY_ADD_COMPLETED_TASK = QueryManager.getProperty("query.completed.add-task");
    public static final String QUERY_GET_COMPLETED_TASKS = QueryManager.getProperty("query.completed.get-tasks");
    public static final String QUERY_REMOVE_ALL_COMPLETED_TASKS = QueryManager.getProperty("query.completed.remove-all-tasks");
    public static final String QUERY_REMOVE_COMPLETED_TASK = QueryManager.getProperty("query.completed.remove-task");

}
