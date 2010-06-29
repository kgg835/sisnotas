package ccuni.java.sysNotas.logic;

import java.util.ArrayList;

import javax.sql.DataSource;

import ccuni.java.sysNotas.dao.ConsultaDAO;
import ccuni.java.sysNotas.dao.DAOFactory;
import ccuni.java.sysNotas.dao.MysqlTransaction;
import ccuni.java.sysNotas.dao.TransactionException;

public class ConsultaManager
{
    private DataSource dataSource;

    public ConsultaManager(DataSource dataSource)
    {
        super();
        // TODO Auto-generated constructor stub
        this.dataSource = dataSource;
    }

    public ArrayList<String> getAllTipoPrueba(String codCur, String seccion, String perAcad)
            throws TransactionException
    {
        MysqlTransaction t = new MysqlTransaction(dataSource);
        ArrayList<String> alTp = new ArrayList<String>();
        try
        {
            DAOFactory mysqlFactory = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
            ConsultaDAO consultaDAO = mysqlFactory.getConsultaDAO(t);
            alTp = consultaDAO.getAllTipoPrueba(codCur, seccion, perAcad);
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
        return alTp;
    }

    public ArrayList<String> getAllTipoPruebaHist(String codCur, String seccion, String perAcad)
            throws TransactionException
    {
        MysqlTransaction t = new MysqlTransaction(dataSource);
        ArrayList<String> alTpH = new ArrayList<String>();
        try
        {
            DAOFactory mysqlFactory = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
            ConsultaDAO consultaDAO = mysqlFactory.getConsultaDAO(t);
            alTpH = consultaDAO.getAllTipoPruebaHist(codCur, seccion, perAcad);
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
        return alTpH;
    }

    public ArrayList<String> getAllAlumno(String codCur, String seccion, String perAcad) throws TransactionException
    {
        MysqlTransaction t = new MysqlTransaction(dataSource);
        ArrayList<String> alAl = new ArrayList<String>();
        try
        {
            DAOFactory mysqlFactory = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
            ConsultaDAO consultaDAO = mysqlFactory.getConsultaDAO(t);
            alAl = consultaDAO.getAllAlumno(codCur, seccion, perAcad);
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
        return alAl;
    }

    public ArrayList<String> getAllAlumnoHist(String codCur, String seccion, String perAcad)
            throws TransactionException
    {
        MysqlTransaction t = new MysqlTransaction(dataSource);
        ArrayList<String> alAlH = new ArrayList<String>();
        try
        {
            DAOFactory mysqlFactory = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
            ConsultaDAO consultaDAO = mysqlFactory.getConsultaDAO(t);
            alAlH = consultaDAO.getAllAlumnoHist(codCur, seccion, perAcad);
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
        return alAlH;
    }

    public ArrayList<String> getAllPrueba(String codCur, String seccion, String perAcad) throws TransactionException
    {
        MysqlTransaction t = new MysqlTransaction(dataSource);
        ArrayList<String> alP = new ArrayList<String>();
        try
        {
            DAOFactory mysqlFactory = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
            ConsultaDAO consultaDAO = mysqlFactory.getConsultaDAO(t);
            alP = consultaDAO.getAllPrueba(codCur, seccion, perAcad);
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
        return alP;
    }

    public ArrayList<String> getAllPruebaHist(String codCur, String seccion, String perAcad)
            throws TransactionException
    {
        MysqlTransaction t = new MysqlTransaction(dataSource);
        ArrayList<String> alPH = new ArrayList<String>();
        try
        {
            DAOFactory mysqlFactory = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
            ConsultaDAO consultaDAO = mysqlFactory.getConsultaDAO(t);
            alPH = consultaDAO.getAllPruebaHist(codCur, seccion, perAcad);
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
        return alPH;
    }
}
