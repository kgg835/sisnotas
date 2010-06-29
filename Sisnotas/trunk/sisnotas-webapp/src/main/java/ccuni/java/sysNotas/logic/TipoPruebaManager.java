package ccuni.java.sysNotas.logic;

import java.util.ArrayList;

import javax.sql.DataSource;

import ccuni.java.sysNotas.dao.DAOFactory;
import ccuni.java.sysNotas.dao.MysqlTransaction;
import ccuni.java.sysNotas.dao.TipoPruebaDAO;
import ccuni.java.sysNotas.dao.TransactionException;
import ccuni.java.sysNotas.domain.dto.TipoPruebaTO;

public class TipoPruebaManager
{
    private DataSource dataSource;

    public TipoPruebaManager(DataSource dataSource)
    {
        super();
        // TODO Auto-generated constructor stub
        this.dataSource = dataSource;
    }

    public ArrayList<TipoPruebaTO> selectAll() throws TransactionException
    {
        MysqlTransaction t = new MysqlTransaction(dataSource);
        ArrayList<TipoPruebaTO> list = null;
        try
        {
            DAOFactory mysqlFactory = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
            TipoPruebaDAO tipoPruebaDAO = mysqlFactory.getTipoPruebaDAO(t);
            list = tipoPruebaDAO.selectAll();
        }
        catch (TransactionException e)
        {
            e.printStackTrace();
            throw new TransactionException("Error al registrar usuario");
        }
        finally
        {
            t.close();
        }
        return list;
    }

    public TipoPruebaTO find(String codTipPba) throws TransactionException
    {
        MysqlTransaction t = new MysqlTransaction(dataSource);
        TipoPruebaTO to = null;
        try
        {
            DAOFactory mysqlFactory = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
            TipoPruebaDAO tipoPruebaDAO = mysqlFactory.getTipoPruebaDAO(t);
            to = tipoPruebaDAO.find(codTipPba);
        }
        catch (TransactionException e)
        {
            e.printStackTrace();
            throw new TransactionException("Error al registrar usuario");
        }
        finally
        {
            t.close();
        }
        return to;
    }

    public int delete(String codTipPba, String codUser) throws TransactionException
    {
        MysqlTransaction t = new MysqlTransaction(dataSource);
        TipoPruebaTO to = null;
        int n = 0;
        try
        {
            DAOFactory mysqlFactory = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
            TipoPruebaDAO tipoPruebaDAO = mysqlFactory.getTipoPruebaDAO(t);
            n = tipoPruebaDAO.delete(codTipPba, codUser);
            if (n == 1)
                t.commit();
        }
        catch (TransactionException e)
        {
            e.printStackTrace();
            throw new TransactionException("Error al registrar usuario");
        }
        finally
        {
            t.close();
        }
        return n;
    }

    public int insert(TipoPruebaTO to, String codUser) throws TransactionException
    {
        MysqlTransaction t = new MysqlTransaction(dataSource);
        int n = 0;
        try
        {
            DAOFactory mysqlFactory = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
            TipoPruebaDAO tipoPruebaDAO = mysqlFactory.getTipoPruebaDAO(t);
            n = tipoPruebaDAO.insert(to, codUser);
            if (n == 1)
                t.commit();
        }
        catch (TransactionException e)
        {
            e.printStackTrace();
            throw new TransactionException("Error al registrar usuario");
        }
        finally
        {
            t.close();
        }
        return n;
    }

    public int update(TipoPruebaTO to, String codUser) throws TransactionException
    {
        MysqlTransaction t = new MysqlTransaction(dataSource);
        int n = 0;
        try
        {
            DAOFactory mysqlFactory = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
            TipoPruebaDAO tipoPruebaDAO = mysqlFactory.getTipoPruebaDAO(t);
            n = tipoPruebaDAO.update(to, codUser);
            if (n == 1)
                t.commit();
        }
        catch (TransactionException e)
        {
            e.printStackTrace();
            throw new TransactionException("Error al registrar usuario");
        }
        finally
        {
            t.close();
        }
        return n;
    }
}
