package by.gsu.epamlab.model.dao;

import by.gsu.epamlab.controller.exceptions.DAOException;
import by.gsu.epamlab.model.beans.File;

import javax.naming.NamingException;
import java.sql.SQLException;
import java.util.List;

public interface FileDAO {
    void addFile(File file) throws DAOException;
    File getFile(String fileId) throws DAOException;
    void removeFile(String fileId) throws DAOException;
    String getFileName(String fileId) throws DAOException;
    List<String> getFilesId (String userId) throws DAOException;
}
