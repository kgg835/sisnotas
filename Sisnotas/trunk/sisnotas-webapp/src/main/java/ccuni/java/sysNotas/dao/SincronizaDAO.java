package ccuni.java.sysNotas.dao;

import java.util.List;

public interface SincronizaDAO extends GenericDAO
{
    public List sincronizarMysqltoClipper(String perAcad) throws TransactionException;
}
