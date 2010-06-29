package ccuni.java.sysNotas.implDao;

import ccuni.java.sysNotas.dao.RolesDAO;
import ccuni.java.sysNotas.dao.Transaction;
import ccuni.java.sysNotas.domain.dto.RolesTO;

import java.util.Collection;

public class MysqlRolesDAO implements RolesDAO{
    public MysqlRolesDAO() {
    }

    public int insert(RolesTO t) {
        return 0;
    }

    public boolean delete(String t) {
        return false;
    }

    public RolesTO find(String t) {
        return null;
    }

    public boolean update(RolesTO t) {
        return false;
    }

    public Collection selectAllRoles() {
        return null;
    }

    public Transaction getTransaction() {
        return null;
    }
}
