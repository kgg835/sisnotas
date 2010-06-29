package ccuni.java.sysNotas.dao;

import ccuni.java.sysNotas.implDao.MysqlConsultaDAO;
import ccuni.java.sysNotas.implDao.MysqlCursoDAO;
import ccuni.java.sysNotas.implDao.MysqlCursoSeccionDAO;
import ccuni.java.sysNotas.implDao.MysqlEstadoPruebaDAO;
import ccuni.java.sysNotas.implDao.MysqlEvaluacionDAO;
import ccuni.java.sysNotas.implDao.MysqlParametrosDAO;
import ccuni.java.sysNotas.implDao.MysqlPeriodoDAO;
import ccuni.java.sysNotas.implDao.MysqlPromedioDAO;
import ccuni.java.sysNotas.implDao.MysqlPruebaDAO;
import ccuni.java.sysNotas.implDao.MysqlSincronizaDAO;
import ccuni.java.sysNotas.implDao.MysqlTipoPruebaDAO;
import ccuni.java.sysNotas.implDao.MysqlToConsultaDAO;
import ccuni.java.sysNotas.implDao.MysqlUsuarioDAO;

public class MysqlDAOFactory extends DAOFactory
{
    public UsuarioDAO getUsuarioDAO(Transaction t)
    {
        // MysqlUsuarioDAO implements UsuarioDAO
        return new MysqlUsuarioDAO(t);
    }

    public PruebaDAO getPruebaDAO(Transaction t)
    {
        return new MysqlPruebaDAO(t);
    }

    public DocenteDAO getDocenteDAO(Transaction t)
    {
        return null;
    }

    public InsertarResultadoDAO getInsertarResultadoDAO(Transaction t)
    {
        return null;
    }

    @Override
    public MatriculaDAO getMatriculaDAO(Transaction t)
    {
        // TODO Auto-generated method stub
        return null;
    }

    public CursoDAO getCursoDAO(Transaction t)
    {
        // TODO Auto-generated method stub
        return new MysqlCursoDAO(t);
    }

    public EvaluacionDAO getEvaluacionDAO(Transaction t)
    {
        return new MysqlEvaluacionDAO(t);
    }

    public ParametrosDAO getParametrosDAO(Transaction t)
    {
        // TODO Auto-generated method stub
        return new MysqlParametrosDAO(t);
    }

    public PromedioDAO getPromedioDAO(Transaction t)
    {
        // TODO Auto-generated method stub
        return new MysqlPromedioDAO(t);
    }

    public ConsultaDAO getConsultaDAO(Transaction t)
    {
        // TODO Auto-generated method stub
        return new MysqlConsultaDAO(t);
    }

    public ToConsultaDAO getToConsultaDAO(Transaction t)
    {
        // TODO Auto-generated method stub
        return new MysqlToConsultaDAO(t);
    }

    public PeriodoDAO getPeriodoDAO(Transaction t)
    {
        // TODO Auto-generated method stub
        return new MysqlPeriodoDAO(t);
    }

    public EstadoPruebaDAO getEstadoPruebaDAO(Transaction t)
    {
        // TODO Auto-generated method stub
        return new MysqlEstadoPruebaDAO(t);
    }

    public CursoSeccionDAO getCursoSeccionDAO(Transaction t)
    {
        return new MysqlCursoSeccionDAO(t);
    }

    public TipoPruebaDAO getTipoPruebaDAO(Transaction t)
    {
        return new MysqlTipoPruebaDAO(t);
    }

    public SincronizaDAO getSincronizaDAO(Transaction t)
    {
        return new MysqlSincronizaDAO(t);
    }
}
