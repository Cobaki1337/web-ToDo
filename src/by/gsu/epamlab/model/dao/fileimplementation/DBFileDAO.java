package by.gsu.epamlab.model.dao.fileimplementation;

import by.gsu.epamlab.controller.exceptions.DAOException;
import by.gsu.epamlab.model.beans.File;
import by.gsu.epamlab.model.dao.FileDAO;
import by.gsu.epamlab.controller.database.DBConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static by.gsu.epamlab.controller.constants.DataBaseConstants.*;

public class DBFileDAO implements FileDAO {

    @Override
    public void addFile(File file) throws DAOException {
        try (Connection connection = DBConnector.getConnection();
             PreparedStatement addFile = connection.prepareStatement(QUERY_ADD_FILE)) {
            addFile.setString(1, file.getId());
            addFile.setString(2, file.getFileName());
            addFile.setString(3, file.getFilePath());
            addFile.executeUpdate();
        }catch (SQLException e){
            throw new DAOException(e);
        }
    }

    @Override
    public File getFile(String fileId) throws DAOException {
        try (Connection connection = DBConnector.getConnection();
             PreparedStatement getFile = connection.prepareStatement(QUERY_GET_FILE)){
            getFile.setString(1, fileId);
            ResultSet resultSet = getFile.executeQuery();
            File file = null;
            if (resultSet.first()){
                String id = resultSet.getString(1);
                String fileName = resultSet.getString(2);
                String filePath = resultSet.getString(3);
                file = new File(id, fileName, filePath);
            }
            return file;
        }catch (SQLException e){
            throw new DAOException(e);
        }
    }

    @Override
    public void removeFile(String fileId) throws DAOException {
        try (Connection connection = DBConnector.getConnection();
             PreparedStatement removeFile = connection.prepareStatement(QUERY_REMOVE_FILE)){
            removeFile.setString(1, fileId);
            removeFile.executeUpdate();
        }catch (SQLException e){
            throw new DAOException(e);
        }
    }

    @Override
    public String getFileName(String fileId) throws DAOException {
        try (Connection connection = DBConnector.getConnection();
            PreparedStatement getFileName = connection.prepareStatement(QUERY_GET_FILE_NAME)){
            getFileName.setString(1, fileId);
            ResultSet resultSet = getFileName.executeQuery();
            String fileName = null;
            if (resultSet.first()){
                fileName = resultSet.getString(1);
            }
            return fileName;
        }catch (SQLException e){
            throw new DAOException(e);
        }
    }

    @Override
    public List<String> getFilesId(String userId) throws DAOException {
        try (Connection connection = DBConnector.getConnection();
            PreparedStatement getFilesId = connection.prepareStatement(QUERY_GET_FILES_ID)){
            List<String> filesId = new ArrayList<>();
            getFilesId.setString(1, userId);
            ResultSet resultSet = getFilesId.executeQuery();
            while (resultSet.next()){
                filesId.add(resultSet.getString(1));
            }
            return filesId;
        }catch (SQLException e){
            throw new DAOException(e);
        }
    }
}
