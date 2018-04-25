package by.gsu.epamlab.model.factories;

import by.gsu.epamlab.model.dao.FileDAO;
import by.gsu.epamlab.model.dao.fileimplementation.DBFileDAO;

public enum FileImplFactory {
    DB{
        @Override
        FileDAO getFileImpl() {
            return new DBFileDAO();
        }
    };
    abstract FileDAO getFileImpl();

    public static FileDAO getFileImplFromFactory(String implementation){
        return valueOf(implementation.toUpperCase()).getFileImpl();
    }
}
