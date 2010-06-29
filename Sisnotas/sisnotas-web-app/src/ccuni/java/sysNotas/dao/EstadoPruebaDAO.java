package ccuni.java.sysNotas.dao;

import java.util.ArrayList;

import ccuni.java.sysNotas.domain.dto.EstadoPruebaTO;
import ccuni.java.sysNotas.domain.dto.PruebaTO;

public interface EstadoPruebaDAO extends GenericDAO{
	public int insert(PruebaTO t)throws TransactionException;
    public boolean delete(String t);
    public EstadoPruebaTO find(EstadoPruebaTO p) throws TransactionException;
    public int update(EstadoPruebaTO p)throws TransactionException;
    public ArrayList<String> selectAll()throws TransactionException;
   

}
