package ccuni.java.sysNotas.dao;

import ccuni.java.sysNotas.domain.dto.PruebaTO;

import ccuni.java.sysNotas.domain.dto.RolesTO;

import java.util.Collection;

public interface RolesDAO extends GenericDAO{

    public int insert(RolesTO t)throws TransactionException;
    public boolean delete(String t);
    public RolesTO find(String t);
    public boolean update(RolesTO t);
    public Collection selectAllRoles();
}
