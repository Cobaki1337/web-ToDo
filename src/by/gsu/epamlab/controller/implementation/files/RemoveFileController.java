package by.gsu.epamlab.controller.implementation.files;

import by.gsu.epamlab.controller.AbstractController;
import by.gsu.epamlab.controller.ApplicationContext;
import by.gsu.epamlab.controller.exceptions.DAOException;
import by.gsu.epamlab.model.beans.File;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.Files;

import static by.gsu.epamlab.controller.constants.ParameterConstants.PARAMETER_FILE_ID;
import static by.gsu.epamlab.controller.constants.UtilsConstants.WITHOUT_FILE;

public class RemoveFileController extends AbstractController {
    @Override
    protected void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String fileId = req.getParameter(PARAMETER_FILE_ID);
            File file = ApplicationContext.getFileDAO().getFile(fileId);
            if (file != null){
                Files.deleteIfExists(new java.io.File(file.getFilePath()).toPath());
            }
            ApplicationContext.getFileDAO().removeFile(fileId);
            ApplicationContext.getTasksDAO().updateTaskFile(fileId, WITHOUT_FILE);
        }catch (DAOException e){
            System.err.println(e.getMessage());
        }
    }
}
