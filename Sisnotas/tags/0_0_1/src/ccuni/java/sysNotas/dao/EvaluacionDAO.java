package ccuni.java.sysNotas.dao;


import ccuni.java.sysNotas.domain.dto.PruebaTO;

import ccuni.java.sysNotas.domain.dto.EvaluacionTO;

import java.util.ArrayList;
import java.util.Collection;

public interface EvaluacionDAO extends GenericDAO{

    public int insert(EvaluacionTO t)throws TransactionException;
    public boolean delete(String t);
    public EvaluacionTO find(String t);
    public int update(EvaluacionTO t)throws TransactionException;    
    public Collection selectAllEvaluacion();
    public ArrayList selectxPrueba(PruebaTO prueba) throws TransactionException;
    public void select(PruebaTO prueba,ArrayList<String> listAlumno,ArrayList<String> listNombres,ArrayList<String> listNota,ArrayList<String> listNotaRec,ArrayList <String> condicion) throws TransactionException;
    public boolean existeRegistro(PruebaTO prueba) throws TransactionException;
}
