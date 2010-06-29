package ccuni.java.sysNotas.dao;

import ccuni.java.sysNotas.domain.dto.PruebaTO;
import java.util.*;

import ccuni.java.sysNotas.domain.dto.TipoPruebaTO;

import java.util.Collection;

public interface TipoPruebaDAO {
    public int insert(TipoPruebaTO t, String cosUser) throws TransactionException;
    public int delete(String t, String cosUser) throws TransactionException;
    public TipoPruebaTO find(String t) throws TransactionException;
    public int update(TipoPruebaTO t, String codUser) throws TransactionException;
    public ArrayList selectAll() throws TransactionException;
}
