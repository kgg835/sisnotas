package ccuni.java.sysNotas.implDao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import ccuni.java.sysNotas.dao.SincronizaDAO;
import ccuni.java.sysNotas.dao.Transaction;
import ccuni.java.sysNotas.dao.TransactionException;
import ccuni.java.sysNotas.logic.ManagerInsertarResultado;

public class MysqlSincronizaDAO implements SincronizaDAO
{
    private Transaction transaction = null;

    public MysqlSincronizaDAO(Transaction t)
    {
        this.transaction = t;
    }

    public Transaction getTransaction()
    {
        return transaction;
    }

    public List sincronizarMysqltoClipper(String perAcad) throws TransactionException
    {
        ManagerInsertarResultado mir = new ManagerInsertarResultado();
        Connection con = null;
        Statement statement = null;
        ResultSet rs = null;
        String cursoId = null;
        String seccion = null;
        String codAlumno = null;
        String notfin = null;
        String recfin = null;
        String notpar = null;
        String recpar = null;
        String notsus = null;
        String recsus = null;
        String prom = null;
        String promnum = null;
        int contador = 0;
        List listaFaltantes = new ArrayList();
        try
        {
            con = transaction.getConnection();
            statement = con.createStatement();
            rs = statement.executeQuery(" select * from matri_actual");
            while (rs.next())
            {
                cursoId = rs.getString("CODCUR");
                seccion = rs.getString("SECCION");
                codAlumno = rs.getString("CODALUMNO");
                notfin = rs.getString("NOTFIN");
                recfin = rs.getString("RECFIN");
                notpar = rs.getString("NOTPAR");
                recpar = rs.getString("RECPAR");
                notsus = rs.getString("NOTSUS");
                recsus = rs.getString("RECSUS");
                prom = rs.getString("PROM");
                promnum = rs.getString("PROMNUM");
                // actualiza tablas de Clipper
                System.out.println(cursoId + " " + seccion + " " + notfin + " " + recfin + " " + notpar + " " + recpar
                        + " " + notsus + " " + recsus + " " + prom + " " + promnum);
                int ind = mir.insertar(cursoId, seccion, codAlumno, perAcad, notfin, recfin, notpar, recpar, notsus,
                        recsus, prom, promnum);
                if (ind == 1)
                    contador++;
                else
                    listaFaltantes.add(codAlumno + " " + cursoId + " " + seccion);
            }
            statement.close();
            rs.close();
        }
        catch (SQLException e1)
        {
            e1.printStackTrace();
            throw new TransactionException(e1);
        }
        return listaFaltantes;
    }
}
