/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autistify.dal;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.io.IOException;
import java.sql.Connection;

/**
 *
 * @author Jesper Riis
 */
public class DataBaseConnecter {
    
    private SQLServerDataSource dataSource;

    public DataBaseConnecter() throws IOException
    {
        dataSource = new SQLServerDataSource();

        dataSource.setServerName("EASV-DB2");
        dataSource.setPortNumber(1433);
        dataSource.setDatabaseName("Autistify");
        dataSource.setUser("CS2017A_15");
        dataSource.setPassword("1234");
    }
    
    public Connection getConnection() throws SQLServerException
    {
        return dataSource.getConnection();
    }

    
}
