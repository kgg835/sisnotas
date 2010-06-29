package ccuni.java.sysNotas.dao;

public interface InsertarResultadoDAO extends GenericDAO
{
    public void insertar(String perAcad, String codAlu, String codCur, String seccion, double promPra, int notPar,
            int notFin, int notSus, int recPar, int recFin, int recSus) throws TransactionException;

    public int insertIntoMatriClipper(String cursoId, String seccion, String codAlumno, String perAcad, String notfin,
            String recfin, String notpar, String recpar, String notsus, String recsus, String prom, String promnum)
            throws TransactionException;
}
