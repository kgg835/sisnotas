package ccuni.java.sysNotas.dao;

import java.util.ArrayList;

import ccuni.java.sysNotas.domain.dto.DocenteTO;

public interface DocenteDAO extends GenericDAO
{
    public ArrayList selectAllDocentes() throws TransactionException;

    public DocenteTO find(String codDocente) throws TransactionException;

    public int eliminar() throws TransactionException;
}
