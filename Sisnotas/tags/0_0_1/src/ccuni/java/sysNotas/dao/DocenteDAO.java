package ccuni.java.sysNotas.dao;

import ccuni.java.sysNotas.domain.dto.*;


import java.util.ArrayList;

public interface DocenteDAO extends GenericDAO{
    public ArrayList selectAllDocentes() throws TransactionException;
    public DocenteTO find(String codDocente) throws  TransactionException;
    public int eliminar() throws TransactionException;
}
