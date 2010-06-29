package ccuni.java.sysNotas.dao;

import java.util.ArrayList;

import ccuni.java.sysNotas.domain.dto.UsuarioTO;

public interface UsuarioDAO extends GenericDAO
{
    public int insert(UsuarioTO usuario, String codAdmin) throws TransactionException;

    public int deleteAll(String codGrpUsr) throws TransactionException;

    public UsuarioTO find(String usuarioId) throws TransactionException;

    public boolean update(UsuarioTO usuario);

    public ArrayList selectAllUsuarios(String cosUser) throws TransactionException;

    public ArrayList selectAllDocentes() throws TransactionException;

    public UsuarioTO validar(String usuario, String password) throws TransactionException;

    public ArrayList selectUsuariosHabilitados() throws TransactionException;

    public int modificarEstado(int estado, String codUsr) throws TransactionException;

    public void cambiarPasswordUsuario(String userName, String password) throws Exception;
}
