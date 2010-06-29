package ccuni.java.sysNotas.dao;

import java.util.ArrayList;

import ccuni.java.sysNotas.domain.dto.PeriodoTO;

public interface ToConsultaDAO extends GenericDAO
{
    public ArrayList<String> getAllCursos(String perAcad) throws TransactionException;

    public ArrayList<String> getAllSecciones(String perAcad, String codCur) throws TransactionException;

    public ArrayList<PeriodoTO> getAllPerAcad() throws TransactionException;

    public ArrayList<String> getAllCursosHist(String perAcad) throws TransactionException;

    public ArrayList<String> getAllSeccionesHist(String perAcad, String codCur) throws TransactionException;
}
