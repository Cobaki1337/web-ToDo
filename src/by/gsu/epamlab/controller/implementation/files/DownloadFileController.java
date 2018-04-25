package by.gsu.epamlab.controller.implementation.files;

import by.gsu.epamlab.controller.AbstractController;
import by.gsu.epamlab.controller.ApplicationContext;
import by.gsu.epamlab.controller.exceptions.DAOException;
import by.gsu.epamlab.model.beans.File;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Path;
import java.nio.file.Files;

import static by.gsu.epamlab.controller.constants.FileConstants.*;
import static by.gsu.epamlab.controller.constants.ParameterConstants.PARAMETER_FILE_ID;

public class DownloadFileController extends AbstractController {
    private static final String QUOTE = "\"";


    @Override
    protected void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String fileId = req.getParameter(PARAMETER_FILE_ID);
        try {
            File file = ApplicationContext.getFileDAO().getFile(fileId);
            if (file != null){
                java.io.File toBeSent = new java.io.File(file.getFilePath());
                resp.setContentType(FILE_RESPONSE_CONTENT_TYPE);
                resp.setHeader(FILE_RESPONSE_HEADER, FILE_RESPONSE_HEADER_CONTENT + QUOTE + file.getFileName() + QUOTE);
                resp.setContentLength(Long.valueOf(toBeSent.length()).intValue());
                try (OutputStream out = resp.getOutputStream()){
                    Path path = toBeSent.toPath();
                    Files.copy(path, out);
                }
            }
        }catch (DAOException e){
            System.err.println(e.getMessage());
        }
    }
}
