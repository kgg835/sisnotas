package ccuni.java.sysNotas.implDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import ccuni.java.sysNotas.constantes.Constantes;
import ccuni.java.sysNotas.dao.TipoPruebaDAO;
import ccuni.java.sysNotas.dao.Transaction;
import ccuni.java.sysNotas.dao.TransactionException;
import ccuni.java.sysNotas.domain.dto.TipoPruebaTO;

public class MysqlTipoPruebaDAO implements TipoPruebaDAO
{
    Log log = LogFactory.getLog(MysqlTipoPruebaDAO.class);

    private Transaction transaction = null;

    public MysqlTipoPruebaDAO(Transaction transaction)
    {
        super();
        // TODO Auto-generated constructor stub
        this.transaction = transaction;
    }

    public Transaction getTransaction()
    {
        return transaction;
    }

    public void setTransaction(Transaction transaction)
    {
        this.transaction = transaction;
    }

    public ArrayList<TipoPruebaTO> selectAll() throws TransactionException
    {
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        boolean r = false;
        ArrayList<TipoPruebaTO> list = null;
        TipoPruebaTO to = null;
        try
        {
            con = transaction.getConnection();
            st = con.prepareStatement("SELECT CODTIPPBA, NOMBRE FROM TIPO_PRUEBA WHERE LOGDEL=?");
            st.setInt(1, Constantes.ACTIVO);
            rs = st.executeQuery();
            while (rs.next())
            {
                to = new TipoPruebaTO();
                if (list == null)
                    list = new ArrayList<TipoPruebaTO>();
                to.setCodTipPba(rs.getString("CODTIPPBA"));
                to.setNombre(rs.getString("NOMBRE"));
                list.add(to);
            }
        }
        catch (SQLException e1)
        {
            log.error("Error al consultar los tipos de prueba");
            e1.printStackTrace();
            throw new TransactionException(e1);
        }
        return list;
    }

    public TipoPruebaTO find(String codTipPba) throws TransactionException
    {
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        TipoPruebaTO to = null;
        try
        {
            con = transaction.getConnection();
            st = con.prepareStatement("SELECT LOGDEL,CODTIPPBA,NOMBRE FROM TIPO_PRUEBA WHERE CODTIPPBA=?");
            // st.setInt(1, Constantes.ACTIVO);
            st.setString(1, codTipPba);
            rs = st.executeQuery();
            while (rs.next())
            {
                to = new TipoPruebaTO();
                to.setCodTipPba(rs.getString("CODTIPPBA"));
                to.setNombre(rs.getString("NOMBRE"));
                to.setLogdel(rs.getInt("LOGDEL"));
            }
        }
        catch (SQLException e1)
        {
            e1.printStackTrace();
            log.error("Error al buscar el tipo de prueba " + codTipPba);
            throw new TransactionException(e1);
        }
        return to;
    }

    public int delete(String codTipPba, String codUser) throws TransactionException
    {
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        TipoPruebaTO to = null;
        int n = 0;
        try
        {
            con = transaction.getConnection();
            st = con.prepareStatement("UPDATE TIPO_PRUEBA SET LOGDEL=?, USRUPD=?, DTEUPD=NOW() WHERE CODTIPPBA=?");
            st.setInt(1, Constantes.INACTIVO);
            st.setString(2, codUser);
            st.setString(3, codTipPba);
            n = st.executeUpdate();
        }
        catch (SQLException e1)
        {
            e1.printStackTrace();
            log.error("Error al eliminar el tipo de prueba " + codTipPba);
            throw new TransactionException(e1);
        }
        return n;
    }

    public int insert(TipoPruebaTO to, String codUser) throws TransactionException
    {
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        int n = 0;
        try
        {
            con = transaction.getConnection();
            st = con.prepareStatement("INSERT TIPO_PRUEBA(CODTIPPBA, NOMBRE, USRINS, DTEINS) VALUES(?,?,?,NOW())");
            // st.setInt(1, Constantes.ACTIVO);
            st.setString(1, to.getCodTipPba());
            st.setString(2, to.getNombre());
            st.setString(3, codUser);
            n = st.executeUpdate();
        }
        catch (SQLException e1)
        {
            e1.printStackTrace();
            log.error("Error al insertar el tipo de prueba " + to.getCodTipPba());
            throw new TransactionException(e1);
        }
        return n;
    }

    public int update(TipoPruebaTO to, String codUser) throws TransactionException
    {
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        int n = 0;
        try
        {
            con = transaction.getConnection();
            st = con
                    .prepareStatement("UPDATE TIPO_PRUEBA SET NOMBRE=?, USRUPD=?, LOGDEL=?,DTEUPD=NOW() WHERE CODTIPPBA=?");
            st.setString(1, to.getNombre());
            st.setString(2, codUser);
            st.setInt(3, to.getLogdel());
            st.setString(4, to.getCodTipPba());
            n = st.executeUpdate();
        }
        catch (SQLException e1)
        {
            e1.printStackTrace();
            log.error("Error al actualizar el tipo de prueba " + to.getCodTipPba());
            throw new TransactionException(e1);
        }
        return n;
    }
}
