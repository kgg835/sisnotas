package ccuni.java.sysNotas.dao;

import java.util.ArrayList;

import ccuni.java.sysNotas.domain.dto.TipoPruebaTO;

public interface TipoPruebaDAO
{
    public int insert(TipoPruebaTO t, String cosUser) throws TransactionException;

    public int delete(String t, String cosUser) throws TransactionException;

    public TipoPruebaTO find(String t) throws TransactionException;

    public int update(TipoPruebaTO t, String codUser) throws TransactionException;

    public ArrayList selectAll() throws TransactionException;
}
