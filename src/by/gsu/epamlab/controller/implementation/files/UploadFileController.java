package by.gsu.epamlab.controller.implementation.files;

import by.gsu.epamlab.controller.AbstractController;
import by.gsu.epamlab.controller.ApplicationContext;
import by.gsu.epamlab.controller.exceptions.DAOException;
import by.gsu.epamlab.model.beans.File;
import org.apache.commons.codec.digest.DigestUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.util.Date;

import static by.gsu.epamlab.controller.constants.AttributeConstants.ATTRIBUTE_FILE;
import static by.gsu.epamlab.controller.constants.FileConstants.FILE_FILEPATH;
import static by.gsu.epamlab.controller.constants.ParameterConstants.PARAMETER_FILE_ID;
import static by.gsu.epamlab.controller.constants.UtilsConstants.WITH_FILE;


@MultipartConfig
public class UploadFileController extends AbstractController {

    @Override
    protected void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String fileId = req.getParameter(PARAMETER_FILE_ID);
            Part file = req.getPart(ATTRIBUTE_FILE);
            String fileName = file.getSubmittedFileName();
            String hash = DigestUtils.sha256Hex(fileName + new Date().toString());
            String filePath = FILE_FILEPATH + hash;
            file.write(filePath);
            ApplicationContext.getFileDAO().addFile(new File (fileId, fileName, filePath));
            ApplicationContext.getTasksDAO().updateTaskFile(fileId, WITH_FILE);
        }catch (DAOException e){
            System.err.println(e.getMessage());
        }
    }
}
