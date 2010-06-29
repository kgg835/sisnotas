package ccuni.java.sysNotas.dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class ClipperTransaction extends Transaction
{
    public static Connection getJDBCConnection() throws TransactionException
    {
        try
        {
            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
        }
        catch (Exception e)
        {
            e.printStackTrace();
            throw new TransactionException("No se encontro el driver");
        }
        try
        {
            String url = ("jdbc:odbc:clipper");
            Connection con = DriverManager.getConnection(url, "", "");
            return con;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            throw new TransactionException("Error al conectar con la base de datos Clipper");
        }
    }

    public Connection getConnection() throws TransactionException
    {
        // patrón SINGLETOM
        if (con == null)
        {
            con = getJDBCConnection();
        }
        return con;
    }
}
