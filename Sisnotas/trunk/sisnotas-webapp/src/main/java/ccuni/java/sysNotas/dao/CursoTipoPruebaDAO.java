package ccuni.java.sysNotas.dao;

import java.util.Collection;

import ccuni.java.sysNotas.domain.dto.CursoTipoPruebaTO;

public interface CursoTipoPruebaDAO extends GenericDAO
{
    public int insert(CursoTipoPruebaTO t) throws TransactionException;

    public boolean delete(String t);

    public CursoTipoPruebaTO find(String t);

    public boolean update(CursoTipoPruebaTO t);

    public Collection selectAllCursoTipoPrueba();
    // public UsuarioTO selectUsuarioRS();
}
