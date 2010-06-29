package ccuni.java.sysNotas.dao;

import java.util.ArrayList;

import ccuni.java.sysNotas.domain.dto.AlumnoTO;
import ccuni.java.sysNotas.domain.dto.CursoTO;
import ccuni.java.sysNotas.domain.dto.PruebaTO;

public interface MatriculaDAO extends GenericDAO
{
    public String[] getAlumnosxCurso(CursoTO curso) throws TransactionException;

    public void getAlumnos(PruebaTO prueba, ArrayList<String> listAlumno, ArrayList<String> listNombres,
            ArrayList<String> listNota, ArrayList<String> listNotaRec) throws TransactionException;

    public AlumnoTO find(String alumnoId) throws TransactionException;
}
