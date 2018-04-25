package by.gsu.epamlab.controller.database;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

import static by.gsu.epamlab.controller.constants.ContextConstants.CONTEXT;
import static by.gsu.epamlab.controller.constants.ContextConstants.CONTEXT_DATASOURCE_DB;

public class DBConnector {

    private static DataSource dataSource;

    public static Connection getConnection() throws SQLException {
        try {
            if (dataSource == null) {
                Context envCont = (Context) (new InitialContext().lookup(CONTEXT));
                dataSource = (DataSource) envCont.lookup(CONTEXT_DATASOURCE_DB);
            }
        }catch (NamingException e){
            throw new IllegalStateException(e);
        }
        return dataSource.getConnection();
    }
}
