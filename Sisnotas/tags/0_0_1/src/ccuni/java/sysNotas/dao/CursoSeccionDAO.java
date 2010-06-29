package ccuni.java.sysNotas.dao;

import ccuni.java.sysNotas.domain.dto.*;

import java.util.*;

public interface CursoSeccionDAO extends GenericDAO{
	public ArrayList<CursoTO> obtenerCursosSeccion(String perAcad) throws TransactionException;
	public int insert(CursoTO to, String perAcad)throws TransactionException;
	public int registrarCursosSeccion(List list, String perAcad)
	throws TransactionException;
	public ArrayList<CursoTO> obtenerCursosSeccion()
	throws TransactionException;
	public int delete(CursoTO to)throws TransactionException;
	public List listaCursosSinPromedio() throws TransactionException;
}
