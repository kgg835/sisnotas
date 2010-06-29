package ccuni.java.sysNotas.dao;

import ccuni.java.sysNotas.domain.dto.CursoTipoPruebaTO;
import ccuni.java.sysNotas.domain.dto.UsuarioTO;

import java.util.Collection;

public interface CursoTipoPruebaDAO extends GenericDAO{
    
    public int insert(CursoTipoPruebaTO t)throws TransactionException;
    public boolean delete(String t);
    public CursoTipoPruebaTO find(String t);
    public boolean update(CursoTipoPruebaTO t);
    public Collection selectAllCursoTipoPrueba();
    // public UsuarioTO selectUsuarioRS();
}
