package ccuni.java.sysNotas.dao;

public interface PeriodoDAO extends GenericDAO
{
    public void iniciarPeriodo(String perAcad, String newPerAcad) throws TransactionException;

    public boolean PeriodoExiste(String perAcad) throws TransactionException;

    public int insert(String perAcad, String detalle, String username, String nombre) throws TransactionException;
}
