package ccuni.java.sysNotas.dao;

import ccuni.java.sysNotas.implDao.ClipperCursoSeccionDAO;
import ccuni.java.sysNotas.implDao.ClipperDocenteDAO;
import ccuni.java.sysNotas.implDao.ClipperInsertarResultadoDAO;
import ccuni.java.sysNotas.implDao.ClipperMatriculaDAO;

public class ClipperDAOFactory extends DAOFactory
{
    public UsuarioDAO getUsuarioDAO(Transaction t)
    {
        return null;
    }

    public PruebaDAO getPruebaDAO(Transaction t)
    {
        return null;
    }

    public DocenteDAO getDocenteDAO(Transaction t)
    {
        return new ClipperDocenteDAO(t);
    }

    public PruebaLogicDAO getPruebaLogicDAO(Transaction t)
    {
        return null;
    }

    @Override
    public MatriculaDAO getMatriculaDAO(Transaction t)
    {
        // TODO Auto-generated method stub
        return new ClipperMatriculaDAO(t);
    }

    public EvaluacionDAO getEvaluacionDAO(Transaction t)
    {
        // TODO Auto-generated method stub
        return null;
    }

    public CursoDAO getCursoDAO(Transaction t)
    {
        // TODO Auto-generated method stub
        return null;
    }

    public ParametrosDAO getParametrosDAO(Transaction t)
    {
        // TODO Auto-generated method stub
        return null;
    }

    public PromedioDAO getPromedioDAO(Transaction t)
    {
        return null;
    }

    public InsertarResultadoDAO getInsertarResultadoDAO(Transaction t)
    {
        return new ClipperInsertarResultadoDAO(t);
    }

    public ConsultaDAO getConsultaDAO(Transaction t)
    {
        return null;
    }

    public ToConsultaDAO getToConsultaDAO(Transaction t)
    {
        return null;
    }

    public PeriodoDAO getPeriodoDAO(Transaction t)
    {
        return null;
    }

    public EstadoPruebaDAO getEstadoPruebaDAO(Transaction t)
    {
        // TODO Auto-generated method stub
        return null;
    }

    public CursoSeccionDAO getCursoSeccionDAO(Transaction t)
    {
        return new ClipperCursoSeccionDAO(t);
    }

    public TipoPruebaDAO getTipoPruebaDAO(Transaction t)
    {
        return null;
    }

    @Override
    public SincronizaDAO getSincronizaDAO(Transaction t)
    {
        // TODO Auto-generated method stub
        return null;
    }
}
