package ccuni.java.sysNotas.implDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import ccuni.java.sysNotas.dao.PruebaDAO;
import ccuni.java.sysNotas.dao.Transaction;
import ccuni.java.sysNotas.dao.TransactionException;
import ccuni.java.sysNotas.domain.dto.CursoTO;
import ccuni.java.sysNotas.domain.dto.EstadoPrueba;
import ccuni.java.sysNotas.domain.dto.PruebaTO;

public class MysqlPruebaDAO implements PruebaDAO
{
    private Transaction transaction = null;

    public MysqlPruebaDAO(Transaction t)
    {
        this.transaction = t;
    }

    public Transaction getTransaction()
    {
        return transaction;
    }

    public int insert(PruebaTO t)
    {
        return 0;
    }

    public int delete(String codigoCurso, String seccion) throws TransactionException
    {
        Connection c;
        PreparedStatement statement = null;
        int i = 1;
        int n = 0;
        try
        {
            c = transaction.getConnection();
            statement = c.prepareStatement("DELETE FROM PRUEBAS WHERE CODCUR=? and SECCION=? and LOGDEL=1");
            statement.setString(i++, codigoCurso);
            statement.setString(i++, seccion);
            n = statement.executeUpdate();
            statement.close();
        }
        catch (SQLException e1)
        {
            e1.printStackTrace();
            throw new TransactionException(e1);
        }
        return n;
    }

    public PruebaTO find(PruebaTO t) throws TransactionException
    {
        Connection c;
        PreparedStatement statement = null;
        ResultSet rs = null;
        int i = 1;
        try
        {
            c = transaction.getConnection();
            statement = c
                    .prepareStatement(" select * from PRUEBAS  WHERE CODCUR=? and SECCION=? and PERACAD=? and CODTIPPBA=? and NUMTIPPBA=? and LOGDEL=1");
            statement.setString(i++, t.getCodCur());
            statement.setString(i++, t.getSeccion());
            statement.setString(i++, t.getPerAcad());
            statement.setString(i++, t.getCodtippba());
            statement.setInt(i++, t.getNumtippba());
            rs = statement.executeQuery();
            boolean flag = rs.next();
            if (flag != true)
            {
                return null;
            }
            t.setPeso(rs.getInt("PESO"));
            t.setUsrins(rs.getString("USRINS"));
            t.setUsrupd(rs.getString("USRUPD"));
            statement.close();
            rs.close();
        }
        catch (SQLException e1)
        {
            e1.printStackTrace();
            throw new TransactionException(e1);
        }
        return t;
    }

    public boolean verifica(PruebaTO t) throws TransactionException
    {
        Connection c;
        PreparedStatement statement = null;
        ResultSet rs = null;
        int i = 1;
        try
        {
            c = transaction.getConnection();
            statement = c
                    .prepareStatement(" select 'x' from EVALUACION  WHERE CODCUR=? and SECCION=? and PERACAD=? and CODTIPPBA=? and NUMTIPPBA=?  and LOGDEL=1");
            statement.setString(i++, t.getCodCur());
            statement.setString(i++, t.getSeccion());
            statement.setString(i++, t.getPerAcad());
            statement.setString(i++, t.getCodtippba());
            statement.setInt(i++, t.getNumtippba());
            rs = statement.executeQuery();
            if (rs.next())
                return true;
            statement.close();
            rs.close();
        }
        catch (SQLException e1)
        {
            throw new TransactionException(e1);
        }
        return false;
    }

    public int update(PruebaTO t) throws TransactionException
    {
        Connection c = null;
        PreparedStatement statement = null;
        int i = 1;
        int n;
        // formando el query
        String query = "UPDATE  PRUEBAS  SET ";
        if (t.getUsrins() != null)
            query += "USRINS=?,DTEINS=CURRENT_TIMESTAMP(),";
        if (t.getUsrupd() != null)
            query += "USRUPD=?,LOGREC=1,DTEUPD=CURRENT_TIMESTAMP(),";
        query += "ACCIONID=?";
        query += " WHERE CODTIPPBA=? and CODCUR=? and SECCION=? and PERACAD=? and NUMTIPPBA=? and LOGDEL=1";
        try
        {
            c = transaction.getConnection();
            statement = c.prepareStatement(query);
            if (t.getUsrins() != null)
                statement.setString(i++, t.getUsrins());
            if (t.getUsrupd() != null)
                statement.setString(i++, t.getUsrupd());
            statement.setInt(i++, t.getAccionId());
            statement.setString(i++, t.getCodtippba());
            statement.setString(i++, t.getCodCur());
            statement.setString(i++, t.getSeccion());
            statement.setString(i++, t.getPerAcad());
            statement.setInt(i++, t.getNumtippba());
            n = statement.executeUpdate();
            if (n == 0)
                return -1;
            // throw new TransactionException("No se pudo crear el usuario");
        }
        catch (SQLException e1)
        {
            e1.printStackTrace();
            throw new TransactionException("Error al actualizar la tabla prueba");
        }
        finally
        {
            try
            {
                if (statement != null)
                    statement.close();
            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }
        }
        return 1;
    }

    public CursoTO selectAllPrueba(CursoTO curso) throws TransactionException
    {
        Connection c;
        PreparedStatement statement = null;
        ResultSet rs = null;
        int i = 1;
        try
        {
            c = transaction.getConnection();
            statement = c
                    .prepareStatement("select * from PRUEBAS,ESTADO_PRUEBA   WHERE PRUEBAS.ACCIONID=ESTADO_PRUEBA.CODIGO AND CODCUR=? AND SECCION=? AND PERACAD=? AND LOGDEL=1;");
            statement.setString(i++, curso.getCursoId());
            statement.setString(i++, curso.getSeccion());
            statement.setString(i++, curso.getPerAcad());
            rs = statement.executeQuery();
            ArrayList<PruebaTO> pruebas = new ArrayList<PruebaTO>();
            while (rs.next())
            {
                PruebaTO p = new PruebaTO();
                EstadoPrueba e = new EstadoPrueba();
                p.setCodCur(rs.getString("CODCUR"));
                p.setCodtippba(rs.getString("CODTIPPBA"));
                p.setNumtippba(rs.getInt("NUMTIPPBA"));
                p.setPeso(rs.getInt("PESO"));
                p.setUsrins(rs.getString("USRINS"));
                p.setUsrupd(rs.getString("USRUPD"));
                p.setSeccion(rs.getString("SECCION"));
                p.setPerAcad(rs.getString("PERACAD"));
                p.setLogRec(rs.getInt("LOGREC"));
                e.setCodigo(rs.getInt("CODIGO"));
                e.setAccion(rs.getString("ACCION"));
                e.setDescripcion(rs.getString("DESCRIPCION"));
                e.setDraw(rs.getInt("DRAW"));
                p.setEstado(e);
                pruebas.add(p);
            }
            curso.setPruebas(pruebas);
            statement.close();
            rs.close();
            return curso;
        }
        catch (SQLException e1)
        {
            throw new TransactionException(e1);
        }
    }
}
