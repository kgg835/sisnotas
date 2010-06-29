package ccuni.java.sysNotas.dao;

import ccuni.java.sysNotas.domain.dto.CursoTipoPruebaTO;

import ccuni.java.sysNotas.domain.dto.PaginaTO;

import java.util.Collection;

public interface PaginaDAO extends GenericDAO{

    public int insert(PaginaTO t)throws TransactionException;
    public boolean delete(String t);
    public PaginaTO find(String t);
    public boolean update(PaginaTO t);
    public Collection selectAllPaginaTO();
}
