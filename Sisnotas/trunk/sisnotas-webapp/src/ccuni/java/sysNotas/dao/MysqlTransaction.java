package ccuni.java.sysNotas.dao;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.sql.DataSource;

public class MysqlTransaction extends Transaction{

    private static DataSource dataSource;

    public MysqlTransaction(DataSource dt) {
        dataSource = dt;
    }

    public static Connection getJDBCConnection() throws TransactionException {
        try {
            //solicitar la conexión
            Connection connection = null;
            connection = dataSource.getConnection();
            return connection;
        } catch (SQLException e1) {
            //si no pudo conectarse
            System.out.println("No se pudo conectar a MYSQL");
            throw new TransactionException(e1);
        }
    }

    public Connection getConnection() throws TransactionException {
        //patrón SINGLETOM
        if (con == null) {
            con = getJDBCConnection();
            try {
                con.setAutoCommit(false);
               
            } catch (SQLException e) {
                System.out.println("problemas con mysql");
                throw new TransactionException(e);
            }
        }
        return con;
    }


    public void commit() throws TransactionException {
        try {
            if (con != null)
                con.commit();
        } catch (SQLException e) {
            throw new TransactionException(e);
        }
    }


    public void rollback() {
        try {
            if (con != null)
                con.rollback();
        } catch (SQLException ignored) {
            ignored.printStackTrace();
        }
    }
}
