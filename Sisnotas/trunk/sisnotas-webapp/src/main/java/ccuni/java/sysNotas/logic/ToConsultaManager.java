package ccuni.java.sysNotas.logic;

import java.util.ArrayList;

import javax.sql.DataSource;

import ccuni.java.sysNotas.dao.DAOFactory;
import ccuni.java.sysNotas.dao.MysqlTransaction;
import ccuni.java.sysNotas.dao.ToConsultaDAO;
import ccuni.java.sysNotas.dao.TransactionException;
import ccuni.java.sysNotas.domain.dto.PeriodoTO;

public class ToConsultaManager
{
    private DataSource dataSource;

    public ToConsultaManager(DataSource dataSource)
    {
        super();
        // TODO Auto-generated constructor stub
        this.dataSource = dataSource;
    }

    public ArrayList<String> getAllCursos(String perAcad) throws TransactionException
    {
        MysqlTransaction t = new MysqlTransaction(dataSource);
        ArrayList<String> alC = new ArrayList<String>();
        try
        {
            DAOFactory mysqlFactory = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
            ToConsultaDAO toConsultaDAO = mysqlFactory.getToConsultaDAO(t);
            alC = toConsultaDAO.getAllCursos(perAcad);
        }
        finally
        {
            t.close();
        }
        return alC;
    }

    public ArrayList<String> getAllCursosHist(String perAcad) throws TransactionException
    {
        MysqlTransaction t = new MysqlTransaction(dataSource);
        ArrayList<String> alCH = new ArrayList<String>();
        try
        {
            DAOFactory mysqlFactory = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
            ToConsultaDAO toConsultaDAO = mysqlFactory.getToConsultaDAO(t);
            alCH = toConsultaDAO.getAllCursosHist(perAcad);
        }
        finally
        {
            t.close();
        }
        return alCH;
    }

    public ArrayList<String> getAllSecciones(String perAcad, String codCur) throws TransactionException
    {
        MysqlTransaction t = new MysqlTransaction(dataSource);
        ArrayList<String> alS = new ArrayList<String>();
        try
        {
            DAOFactory mysqlFactory = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
            ToConsultaDAO toConsultaDAO = mysqlFactory.getToConsultaDAO(t);
            alS = toConsultaDAO.getAllSecciones(perAcad, codCur);
        }
        finally
        {
            t.close();
        }
        return alS;
    }

    public ArrayList<String> getAllSeccionesHist(String perAcad, String codCur) throws TransactionException
    {
        MysqlTransaction t = new MysqlTransaction(dataSource);
        ArrayList<String> alSH = new ArrayList<String>();
        try
        {
            DAOFactory mysqlFactory = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
            ToConsultaDAO toConsultaDAO = mysqlFactory.getToConsultaDAO(t);
            alSH = toConsultaDAO.getAllSeccionesHist(perAcad, codCur);
        }
        finally
        {
            t.close();
        }
        return alSH;
    }

    public ArrayList<PeriodoTO> getAllPerAcad() throws TransactionException
    {
        MysqlTransaction t = new MysqlTransaction(dataSource);
        ArrayList<PeriodoTO> alP = new ArrayList<PeriodoTO>();
        try
        {
            DAOFactory mysqlFactory = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
            ToConsultaDAO toConsultaDAO = mysqlFactory.getToConsultaDAO(t);
            alP = toConsultaDAO.getAllPerAcad();
        }
        finally
        {
            t.close();
        }
        return alP;
    }
}
