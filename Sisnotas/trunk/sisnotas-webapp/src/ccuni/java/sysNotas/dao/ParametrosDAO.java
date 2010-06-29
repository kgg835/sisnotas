package ccuni.java.sysNotas.dao;

import ccuni.java.sysNotas.domain.dto.ParametrosTO;

public interface ParametrosDAO extends GenericDAO{
	public ParametrosTO getParametros() throws TransactionException;
	public int updateParametros(ParametrosTO to) throws TransactionException;
}
