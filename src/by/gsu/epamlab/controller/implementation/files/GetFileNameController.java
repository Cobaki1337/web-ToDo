package by.gsu.epamlab.controller.implementation.files;

import by.gsu.epamlab.controller.AbstractController;
import by.gsu.epamlab.controller.ApplicationContext;
import by.gsu.epamlab.controller.exceptions.DAOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import static by.gsu.epamlab.controller.constants.ParameterConstants.PARAMETER_FILE_ID;

public class GetFileNameController extends AbstractController {
    @Override
    protected void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            PrintWriter writer = resp.getWriter();
            String fileId = req.getParameter(PARAMETER_FILE_ID);
            String fileName = ApplicationContext.getFileDAO().getFileName(fileId);
            writer.print(fileName);
        }catch (DAOException e){
            System.err.println(e.getMessage());
        }
    }
}
