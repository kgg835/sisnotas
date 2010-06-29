package ccuni.java.sysNotas.logic;

import javax.sql.DataSource;

import ccuni.java.sysNotas.dao.DAOFactory;
import ccuni.java.sysNotas.dao.MysqlTransaction;
import ccuni.java.sysNotas.dao.ParametrosDAO;
import ccuni.java.sysNotas.dao.TransactionException;
import ccuni.java.sysNotas.domain.dto.ParametrosTO;

public class ParametrosManager
{
    private DataSource dataSource;

    public ParametrosManager(DataSource dataSource)
    {
        super();
        // TODO Auto-generated constructor stub
        this.dataSource = dataSource;
    }

    public ParametrosTO getParametros() throws TransactionException
    {
        MysqlTransaction t = new MysqlTransaction(dataSource);
        ParametrosTO to = null;
        try
        {
            DAOFactory mysqlFactory = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
            ParametrosDAO parametrosDAO = mysqlFactory.getParametrosDAO(t);
            to = parametrosDAO.getParametros();
        }
        catch (TransactionException e)
        {
            e.printStackTrace();
            throw new TransactionException("Error de base de datos");
        }
        finally
        {
            t.close();
        }
        return to;
    }

    public int updateParametros(ParametrosTO to) throws TransactionException
    {
        MysqlTransaction t = new MysqlTransaction(dataSource);
        int n = 0;
        try
        {
            DAOFactory mysqlFactory = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
            ParametrosDAO parametrosDAO = mysqlFactory.getParametrosDAO(t);
            n = parametrosDAO.updateParametros(to);
            if (n != 0)
                t.commit();
        }
        catch (TransactionException e)
        {
            e.printStackTrace();
            throw new TransactionException("Error de base de datos");
        }
        finally
        {
            t.close();
        }
        return n;
    }
}
