package ccuni.java.sysNotas.logic;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletContext;
import javax.sql.DataSource;

import ccuni.java.sysNotas.dao.DAOFactory;
import ccuni.java.sysNotas.dao.MysqlTransaction;
import ccuni.java.sysNotas.dao.ParametrosDAO;
import ccuni.java.sysNotas.dao.PeriodoDAO;
import ccuni.java.sysNotas.dao.TransactionException;
import ccuni.java.sysNotas.domain.dto.ParametrosTO;

public class PeriodoManager
{
    private DataSource dataSource;

    public PeriodoManager(DataSource dataSource)
    {
        super();
        // TODO Auto-generated constructor stub
        this.dataSource = dataSource;
    }

    public void iniciarPeriodo(String perAcad, String nombrePerAcad) throws TransactionException
    {
        MysqlTransaction t = new MysqlTransaction(dataSource);
        try
        {
            DAOFactory mysqlFactory = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
            PeriodoDAO periodoDAO = mysqlFactory.getPeriodoDAO(t);
            periodoDAO.iniciarPeriodo(perAcad, nombrePerAcad);
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
    }

    public boolean PeriodoExiste(String perAcad) throws TransactionException
    {
        MysqlTransaction t = new MysqlTransaction(dataSource);
        boolean v = false;
        try
        {
            DAOFactory mysqlFactory = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
            PeriodoDAO periodoDAO = mysqlFactory.getPeriodoDAO(t);
            v = periodoDAO.PeriodoExiste(perAcad);
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
        return v;
    }

    public boolean finalizaPeriodo(ServletContext sc, String detalle, String username) throws TransactionException,
            SQLException
    {
        MysqlTransaction t = new MysqlTransaction(dataSource);
        Connection con = null;
        try
        {
            // guarda a historial y limpia tablas
            // if(!toHistorial()) return false;
            con = t.getConnection();
            CallableStatement cs = con.prepareCall("{call toHistory}");
            cs.executeUpdate();
            String perAcad = ((ParametrosTO) sc.getAttribute("parametrosSistema")).getPeracad();
            String nombre = ((ParametrosTO) sc.getAttribute("parametrosSistema")).getNombre_periodo();
            DAOFactory mysqlFactory = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
            PeriodoDAO periodoDAO = mysqlFactory.getPeriodoDAO(t);
            ParametrosDAO parametroDAO = mysqlFactory.getParametrosDAO(t);
            /** registra el historial el periodo academico cursado */
            periodoDAO.insert(perAcad, detalle, username, nombre);
            /** actualiza parametros del sistema */
            ParametrosTO paramTO = new ParametrosTO();
            paramTO = parametroDAO.getParametros();
            paramTO.setInicio_periodo(0);
            paramTO.setMigracion_cursos(0);
            paramTO.setParametros_cursos(0);
            paramTO.setTipos_prueba_definidos(0);
            parametroDAO.updateParametros(paramTO);
            t.commit();
            sc.setAttribute("parametrosSistema", paramTO);
        }
        catch (TransactionException e)
        {
            t.rollback();
            e.printStackTrace();
            throw new TransactionException("PeriodoManager.finalizaPeriodo:Error al finalizar periodo academico");
        }
        finally
        {
            t.close();
        }
        return true;
    }

    public Boolean toHistorial() throws TransactionException
    {
        MysqlTransaction t = new MysqlTransaction(dataSource);
        Connection con = null;
        try
        {
            con = t.getConnection();
            CallableStatement cs = con.prepareCall("{call toHistory}");
            cs.executeUpdate();
            t.commit();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            t.rollback();
            throw new TransactionException("PeriodoMAnage.toHistorial:Error al realizar el store procedure");
        }
        finally
        {
            t.close();
        }
        return true;
    }
}
