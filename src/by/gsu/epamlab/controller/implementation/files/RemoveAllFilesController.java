package by.gsu.epamlab.controller.implementation.files;

import by.gsu.epamlab.controller.AbstractController;
import by.gsu.epamlab.controller.ApplicationContext;
import by.gsu.epamlab.controller.exceptions.DAOException;
import by.gsu.epamlab.model.beans.File;
import by.gsu.epamlab.model.beans.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

import static by.gsu.epamlab.controller.constants.AttributeConstants.ATTRIBUTE_USER;

public class RemoveAllFilesController extends AbstractController{
    @Override
    protected void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            User user = (User) req.getSession().getAttribute(ATTRIBUTE_USER);
            List<String> filesId = ApplicationContext.getFileDAO().getFilesId(user.getId());
            for (String fileId : filesId){
                File file = ApplicationContext.getFileDAO().getFile(fileId);
                if (file != null){
                    Files.deleteIfExists(new java.io.File(file.getFilePath()).toPath());
                }
                ApplicationContext.getFileDAO().removeFile(fileId);
            }
            filesId.clear();
        }catch (DAOException e){
            System.err.println(e.getMessage());
        }
    }
}
