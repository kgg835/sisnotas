package ccuni.java.sysNotas.dao;

import java.util.Collection;

import ccuni.java.sysNotas.domain.dto.PaginaTO;

public interface PaginaDAO extends GenericDAO
{
    public int insert(PaginaTO t) throws TransactionException;

    public boolean delete(String t);

    public PaginaTO find(String t);

    public boolean update(PaginaTO t);

    public Collection selectAllPaginaTO();
}
