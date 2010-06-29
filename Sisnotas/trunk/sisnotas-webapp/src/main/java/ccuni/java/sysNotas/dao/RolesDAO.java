package ccuni.java.sysNotas.dao;

import java.util.Collection;

import ccuni.java.sysNotas.domain.dto.RolesTO;

public interface RolesDAO extends GenericDAO
{
    public int insert(RolesTO t) throws TransactionException;

    public boolean delete(String t);

    public RolesTO find(String t);

    public boolean update(RolesTO t);

    public Collection selectAllRoles();
}
