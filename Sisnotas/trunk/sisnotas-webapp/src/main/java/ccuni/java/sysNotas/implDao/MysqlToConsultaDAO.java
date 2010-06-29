package ccuni.java.sysNotas.implDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import ccuni.java.sysNotas.dao.ToConsultaDAO;
import ccuni.java.sysNotas.dao.Transaction;
import ccuni.java.sysNotas.dao.TransactionException;
import ccuni.java.sysNotas.domain.dto.PeriodoTO;

public class MysqlToConsultaDAO implements ToConsultaDAO
{
    private Transaction transaction = null;

    public Transaction getTransaction()
    {
        return transaction;
    }

    public void setTransaction(Transaction transaction)
    {
        this.transaction = transaction;
    }

    public MysqlToConsultaDAO(Transaction transaction)
    {
        super();
        // TODO Auto-generated constructor stub
        this.transaction = transaction;
    }

    public ArrayList<String> getAllCursos(String perAcad) throws TransactionException
    {
        Connection con = null;
        PreparedStatement stC = null;
        ResultSet rsC = null;
        ArrayList<String> alC = new ArrayList<String>();
        try
        {
            con = transaction.getConnection();
            stC = con
                    .prepareStatement("select codcur from curso_tipo_prueba where peracad=? group by codcur order by codcur;");
            stC.setString(1, perAcad);
            rsC = stC.executeQuery();
            while (rsC.next())
            {
                alC.add(rsC.getString("codcur"));
            }
        }
        catch (SQLException e1)
        {
            throw new TransactionException(e1);
        }
        return alC;
    }

    public ArrayList<String> getAllCursosHist(String perAcad) throws TransactionException
    {
        Connection con = null;
        PreparedStatement stCH = null;
        ResultSet rsCH = null;
        ArrayList<String> alCH = new ArrayList<String>();
        try
        {
            con = transaction.getConnection();
            stCH = con
                    .prepareStatement("select codcur from hist_curso_tipo_prueba where peracad=? group by codcur order by codcur;");
            stCH.setString(1, perAcad);
            rsCH = stCH.executeQuery();
            while (rsCH.next())
            {
                alCH.add(rsCH.getString("codcur"));
            }
        }
        catch (SQLException e1)
        {
            throw new TransactionException(e1);
        }
        return alCH;
    }

    public ArrayList<String> getAllSecciones(String perAcad, String codCur) throws TransactionException
    {
        Connection con = null;
        PreparedStatement stS = null;
        ResultSet rsS = null;
        ArrayList<String> alS = new ArrayList<String>();
        try
        {
            con = transaction.getConnection();
            stS = con
                    .prepareStatement("select seccion from curso_tipo_prueba where peracad=? and codcur=? group by codcur,seccion order by seccion");
            stS.setString(1, perAcad);
            stS.setString(2, codCur);
            rsS = stS.executeQuery();
            while (rsS.next())
            {
                alS.add(rsS.getString("seccion"));
            }
        }
        catch (SQLException e1)
        {
            throw new TransactionException(e1);
        }
        return alS;
    }

    public ArrayList<String> getAllSeccionesHist(String perAcad, String codCur) throws TransactionException
    {
        Connection con = null;
        PreparedStatement stSH = null;
        ResultSet rsSH = null;
        ArrayList<String> alSH = new ArrayList<String>();
        try
        {
            con = transaction.getConnection();
            stSH = con
                    .prepareStatement("select seccion from hist_curso_tipo_prueba where peracad=? and codcur=? group by codcur,seccion order by seccion");
            stSH.setString(1, perAcad);
            stSH.setString(2, codCur);
            rsSH = stSH.executeQuery();
            while (rsSH.next())
            {
                alSH.add(rsSH.getString("seccion"));
            }
        }
        catch (SQLException e1)
        {
            throw new TransactionException(e1);
        }
        return alSH;
    }

    public ArrayList<PeriodoTO> getAllPerAcad() throws TransactionException
    {
        Connection con = null;
        PreparedStatement stP = null;
        ResultSet rsP = null;
        ArrayList<PeriodoTO> alP = new ArrayList<PeriodoTO>();
        try
        {
            con = transaction.getConnection();
            stP = con.prepareStatement("select peracad,nombreperacad from periodo_academico");
            rsP = stP.executeQuery();
            while (rsP.next())
            {
                PeriodoTO to = new PeriodoTO();
                to.setPeracad(rsP.getString("peracad"));
                to.setNombreperacad(rsP.getString("nombreperacad"));
                alP.add(to);
            }
        }
        catch (SQLException e1)
        {
            throw new TransactionException(e1);
        }
        return alP;
    }
}
