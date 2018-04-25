package by.gsu.epamlab.controller.constants;

import by.gsu.epamlab.controller.managers.MessageManager;

public class MessageConstants {
    public static final String ERROR_LOGIN_PASSWORD_ABSENT = MessageManager.getProperty("message.error.login-password-absent");
    public static final String ERROR_LOGIN_EMPTY = MessageManager.getProperty("message.error.login-empty");
    public static final String ERROR_INVALID_PASSWORD = MessageManager.getProperty("message.error.invalid-password");
    public static final String ERROR_INVALID_LOGIN_PASSWORD = MessageManager.getProperty("message.error.invalid-login-password");
    public static final String ERROR_USER_EXISTS = MessageManager.getProperty("message.error.user-exists");

    public static final String REGISTRATION_SUCCESSFUL = MessageManager.getProperty("message.registration.successful");
}
