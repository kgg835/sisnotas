package ccuni.java.sysNotas.dao;

public abstract class DAOFactory
{
    // List of DAO types supported by the factory
    public static final int MYSQL = 1;

    public static final int CLIPPER = 2;

    // There will be a method for each DAO that can be
    // created. The concrete factories will have to
    // implement these methods.
    public abstract UsuarioDAO getUsuarioDAO(Transaction t);

    public abstract PruebaDAO getPruebaDAO(Transaction t);

    public abstract DocenteDAO getDocenteDAO(Transaction t);

    public abstract MatriculaDAO getMatriculaDAO(Transaction t);

    public abstract EvaluacionDAO getEvaluacionDAO(Transaction t);

    public abstract CursoDAO getCursoDAO(Transaction t);

    public abstract ParametrosDAO getParametrosDAO(Transaction t);

    public abstract CursoSeccionDAO getCursoSeccionDAO(Transaction t);

    public abstract TipoPruebaDAO getTipoPruebaDAO(Transaction t);

    public abstract InsertarResultadoDAO getInsertarResultadoDAO(Transaction t);

    public abstract PromedioDAO getPromedioDAO(Transaction t);

    public abstract ConsultaDAO getConsultaDAO(Transaction t);

    public abstract ToConsultaDAO getToConsultaDAO(Transaction t);

    public abstract PeriodoDAO getPeriodoDAO(Transaction t);

    public abstract EstadoPruebaDAO getEstadoPruebaDAO(Transaction t);

    public abstract SincronizaDAO getSincronizaDAO(Transaction t);

    public static DAOFactory getDAOFactory(int whichFactory)
    {
        switch (whichFactory)
        {
        case MYSQL:
            return new MysqlDAOFactory();
        case CLIPPER:
            return new ClipperDAOFactory();
        default:
            return null;
        }
    }
}
