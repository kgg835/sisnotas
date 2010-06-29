package ccuni.java.sysNotas.dao;

import java.sql.Connection;
import java.sql.SQLException;

public abstract class Transaction
{
    protected Connection con = null;

    public Transaction()
    {
    }

    public static Connection getJDBCConnection() throws TransactionException
    {
        return null;
    }

    public Connection getConnection() throws TransactionException
    {
        return null;
    }

    public void close()
    {
        try
        {
            if (con != null)
            {
                con.close();
            }
        }
        catch (SQLException ignored)
        {
            ignored.printStackTrace();
        }
    }
}
