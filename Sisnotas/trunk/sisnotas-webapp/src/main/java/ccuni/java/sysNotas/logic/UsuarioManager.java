package ccuni.java.sysNotas.logic;

import java.util.ArrayList;

import javax.sql.DataSource;

import ccuni.java.sysNotas.constantes.Constantes;
import ccuni.java.sysNotas.dao.DAOFactory;
import ccuni.java.sysNotas.dao.MysqlTransaction;
import ccuni.java.sysNotas.dao.TransactionException;
import ccuni.java.sysNotas.dao.UsuarioDAO;
import ccuni.java.sysNotas.domain.dto.DocenteTO;
import ccuni.java.sysNotas.domain.dto.UsuarioTO;

public class UsuarioManager
{
    private DataSource dataSource;

    public UsuarioManager(DataSource dataSource)
    {
        this.dataSource = dataSource;
    }

    public boolean registrarUsuario(UsuarioTO usuario, String codAdmin) throws TransactionException
    {
        MysqlTransaction t = new MysqlTransaction(dataSource);
        try
        {
            DAOFactory mysqlFactory = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
            UsuarioDAO usuarioDAO = mysqlFactory.getUsuarioDAO(t);
            if (usuarioDAO.insert(usuario, codAdmin) == 1)
            {
                t.commit();
            }
            else
            {
                return false;
            }
        }
        catch (TransactionException e)
        {
            e.printStackTrace();
            t.rollback();
            throw new TransactionException("Error al registrar usuario");
        }
        finally
        {
            t.close();
        }
        return true;
    }

    public boolean userNameExist(String username) throws TransactionException
    {
        boolean val = false;
        MysqlTransaction t = new MysqlTransaction(dataSource);
        try
        {
            DAOFactory mysqlFactory = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
            UsuarioDAO usuarioDAO = mysqlFactory.getUsuarioDAO(t);
            if (usuarioDAO.find(username) == null)
            {
                return val;
            }
        }
        catch (TransactionException e)
        {
            e.printStackTrace();
            t.rollback();
            throw new TransactionException("Error de base de datos");
        }
        finally
        {
            t.close();
        }
        val = true;
        return val;
    }

    public UsuarioTO validarUsuario(String usuario, String password) throws Exception
    {
        MysqlTransaction t = new MysqlTransaction(dataSource);
        try
        {
            DAOFactory mysqlFactory = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
            UsuarioDAO usuarioDAO = mysqlFactory.getUsuarioDAO(t);
            return usuarioDAO.validar(usuario, password);
        }
        finally
        {
            t.close();
        }
    }

    //
    public ArrayList registrarDocentes(ArrayList docentes, String codAdmin) throws TransactionException
    {
        DocenteTO doc;
        UsuarioTO user;
        ArrayList userReg = new ArrayList();
        GeneradorClaves gen = new GeneradorClaves(Constantes.LONG_CLAVE);
        for (int i = 0; i < docentes.size(); i++)
        {
            doc = (DocenteTO) docentes.get(i);
            user = new UsuarioTO();
            user.setCodUsr(doc.getCodDoc());
            user.setCodGrpUsr(Constantes.DOCENTES);
            user.setCodDoc(doc.getCodDoc());
            user.setNombres(doc.getNombres());
            user.setApellidos(doc.getApellidos());
            user.setUserName(doc.getCodDoc());
            user.setPassword(gen.generarClave().toString());
            registrarUsuario(user, codAdmin);
            userReg.add(user);
        }
        return userReg;
    }

    public ArrayList selectUsuariosHabilitados() throws TransactionException
    {
        MysqlTransaction t = new MysqlTransaction(dataSource);
        ArrayList list = null;
        try
        {
            DAOFactory mysqlFactory = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
            UsuarioDAO usuarioDAO = mysqlFactory.getUsuarioDAO(t);
            list = usuarioDAO.selectUsuariosHabilitados();
        }
        catch (TransactionException e)
        {
            throw new TransactionException("Error al consultar usuarios");
        }
        finally
        {
            t.close();
        }
        return list;
    }

    public ArrayList<UsuarioTO> selectAllUsuarios(String codUser) throws TransactionException
    {
        MysqlTransaction t = new MysqlTransaction(dataSource);
        ArrayList list = null;
        try
        {
            DAOFactory mysqlFactory = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
            UsuarioDAO usuarioDAO = mysqlFactory.getUsuarioDAO(t);
            list = usuarioDAO.selectAllUsuarios(codUser);
        }
        catch (TransactionException e)
        {
            throw new TransactionException("Error al consultar usuarios");
        }
        finally
        {
            t.close();
        }
        return list;
    }

    public ArrayList<UsuarioTO> selectAllDocentes() throws TransactionException
    {
        MysqlTransaction t = new MysqlTransaction(dataSource);
        ArrayList list = null;
        try
        {
            DAOFactory mysqlFactory = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
            UsuarioDAO usuarioDAO = mysqlFactory.getUsuarioDAO(t);
            list = usuarioDAO.selectAllDocentes();
        }
        catch (TransactionException e)
        {
            throw new TransactionException("Error al consultar usuarios");
        }
        finally
        {
            t.close();
        }
        return list;
    }

    // docentes en la bd clipper q no esten en la tabla usuario mysql
    public ArrayList selectDocentesNoRegistrados() throws TransactionException
    {
        DocenteTO doc = null;
        UsuarioTO user = null;
        ArrayList list = null;
        try
        {
            DocenteManager dm = new DocenteManager();
            ArrayList listDoc = dm.selectAllDocentes();
            ArrayList listUser = this.selectAllDocentes();
            for (int i = 0; i < listDoc.size(); i++)
            {
                doc = (DocenteTO) listDoc.get(i);
                user = new UsuarioTO();
                user.setCodUsr(doc.getCodDoc());
                if (!listUser.contains(user))
                {
                    if (list == null)
                    {
                        list = new ArrayList();
                        list.add(doc);
                    }
                    else
                        list.add(doc);
                }
            }
        }
        catch (TransactionException e)
        {
            e.printStackTrace();
            throw new TransactionException("Error consultando docentes no registrados", e);
        }
        return list;
    }

    public UsuarioTO find(String codUsuario) throws TransactionException
    {
        MysqlTransaction t = new MysqlTransaction(dataSource);
        UsuarioTO to = null;
        try
        {
            DAOFactory mysqlFactory = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
            UsuarioDAO usuarioDAO = mysqlFactory.getUsuarioDAO(t);
            to = usuarioDAO.find(codUsuario);
        }
        catch (TransactionException e)
        {
            throw new TransactionException("Error al consultar usuarios");
        }
        finally
        {
            t.close();
        }
        return to;
    }

    public int modificarEstado(int estado, String codUsr) throws TransactionException
    {
        MysqlTransaction t = new MysqlTransaction(dataSource);
        int n;
        try
        {
            DAOFactory mysqlFactory = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
            UsuarioDAO usuarioDAO = mysqlFactory.getUsuarioDAO(t);
            n = usuarioDAO.modificarEstado(estado, codUsr);
            if (n == 1)
                t.commit();
        }
        catch (TransactionException e)
        {
            throw new TransactionException("Error al consultar usuarios");
        }
        finally
        {
            t.close();
        }
        return n;
    }

    public int deleteAll(String codGrpUsr) throws TransactionException
    {
        MysqlTransaction t = new MysqlTransaction(dataSource);
        int n;
        try
        {
            DAOFactory mysqlFactory = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
            UsuarioDAO usuarioDAO = mysqlFactory.getUsuarioDAO(t);
            n = usuarioDAO.deleteAll(codGrpUsr);
            if (n != 0)
                t.commit();
        }
        catch (TransactionException e)
        {
            throw new TransactionException("Error al consultar usuarios");
        }
        finally
        {
            t.close();
        }
        return n;
    }

    public void cambiarPasswordUsuario(String userName, String password) throws Exception
    {
        MysqlTransaction t = new MysqlTransaction(dataSource);
        try
        {
            DAOFactory mysqlFactory = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
            UsuarioDAO usuarioDAO = mysqlFactory.getUsuarioDAO(t);
            usuarioDAO.cambiarPasswordUsuario(userName, password);
            t.commit();
        }
        finally
        {
            t.close();
        }
    }

    public static void main(String[] args)
    {
        System.err.append("gggggg");
    }
}
