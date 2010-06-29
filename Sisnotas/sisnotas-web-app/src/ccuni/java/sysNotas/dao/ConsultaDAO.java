package ccuni.java.sysNotas.dao;

import java.sql.ResultSet;
import java.util.ArrayList;

public interface  ConsultaDAO extends GenericDAO{
	public ArrayList<String> getAllTipoPrueba(String codCur,String seccion,String perAcad) throws TransactionException;
	public ArrayList<String> getAllAlumno(String codCur,String seccion,String perAcad) throws TransactionException;
	public ArrayList<String> getAllPrueba(String codCur,String seccion,String perAcad) throws TransactionException;
	public ArrayList<String> getAllTipoPruebaHist(String codCur,String seccion,String perAcad) throws TransactionException;
	public ArrayList<String> getAllAlumnoHist(String codCur,String seccion,String perAcad) throws TransactionException;
	public ArrayList<String> getAllPruebaHist(String codCur,String seccion,String perAcad) throws TransactionException;
	
}
