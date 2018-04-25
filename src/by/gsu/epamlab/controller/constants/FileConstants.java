package by.gsu.epamlab.controller.constants;

import by.gsu.epamlab.controller.managers.FileManager;

public class FileConstants {
    public static final String FILE_RESPONSE_CONTENT_TYPE = FileManager.getProperty("file.response.content.type");
    public static final String FILE_RESPONSE_HEADER = FileManager.getProperty("file.response.header");
    public static final String FILE_RESPONSE_HEADER_CONTENT = FileManager.getProperty("file.response.header.content");

    public static final String FILE_FILEPATH = FileManager.getProperty("file.filepath");
}
