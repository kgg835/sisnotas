package ccuni.java.sysNotas.dao;

import ccuni.java.sysNotas.domain.dto.PruebaTO;

public interface PruebaLogicDAO extends GenericDAO
{
    public String findAction(PruebaTO p) throws TransactionException;
}
