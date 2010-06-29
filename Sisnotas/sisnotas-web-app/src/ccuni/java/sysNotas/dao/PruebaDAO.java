package ccuni.java.sysNotas.dao;

import ccuni.java.sysNotas.domain.dto.CursoTO;
import ccuni.java.sysNotas.domain.dto.CursoTipoPruebaTO;

import ccuni.java.sysNotas.domain.dto.PruebaTO;

import java.util.ArrayList;
import java.util.Collection;

public interface PruebaDAO extends GenericDAO{

	public int insert(PruebaTO t)throws TransactionException;
	public int delete(String codigoCurso, String seccion) throws TransactionException;
    public PruebaTO find(PruebaTO p) throws TransactionException;
    public int update(PruebaTO t)throws TransactionException;
    public CursoTO selectAllPrueba(CursoTO curso)throws TransactionException;
    public boolean verifica(PruebaTO t) throws TransactionException ;
}
