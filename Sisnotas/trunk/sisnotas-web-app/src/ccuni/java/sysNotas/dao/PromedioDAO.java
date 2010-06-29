package ccuni.java.sysNotas.dao;

import java.util.ArrayList;

public interface PromedioDAO extends GenericDAO{
	public ArrayList<String> calcularPromedio(String perAcad) throws TransactionException;
	//public int calculaPromedioCurso(String cursoId,String seccion) throws TransactionException;
}
