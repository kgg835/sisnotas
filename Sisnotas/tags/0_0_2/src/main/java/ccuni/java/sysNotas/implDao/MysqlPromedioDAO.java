package ccuni.java.sysNotas.implDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import ccuni.java.sysNotas.dao.PromedioDAO;
import ccuni.java.sysNotas.dao.Transaction;
import ccuni.java.sysNotas.dao.TransactionException;
import ccuni.java.sysNotas.logic.ManagerInsertarResultado;

public class MysqlPromedioDAO implements PromedioDAO
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

    public MysqlPromedioDAO(Transaction transaction)
    {
        super();
        // TODO Auto-generated constructor stub
        this.transaction = transaction;
    }

    public ArrayList<String> calcularPromedio(String perAcad) throws TransactionException
    {
        ManagerInsertarResultado mir = new ManagerInsertarResultado();
        double SumaPromedios = 0;
        int SumaPesos = 0;
        double PromedioFinal = -1;
        double Suma = 0;
        int Peso = 0;
        ArrayList<String> al = new ArrayList<String>();
        Connection con = null;
        PreparedStatement stCS = null;
        ResultSet rsCS = null;
        PreparedStatement stTp = null;
        ResultSet rsTp = null;
        PreparedStatement stAl = null;
        ResultSet rsAl = null;
        PreparedStatement stP = null;
        ResultSet rsP = null;
        PreparedStatement iP = null;
        PreparedStatement stEp = null;
        ResultSet rsEp = null;
        PreparedStatement stEx = null;
        ResultSet rsEx = null;
        int notFin = 0;
        int notPar = 0;
        int notSus = 0;
        int recFin = 0;
        int recPar = 0;
        int recSus = 0;
        try
        {
            con = transaction.getConnection();
            stCS = con
                    .prepareStatement("select codcur,seccion from evaluacion  where peracad = ? group by codcur,seccion");
            stCS.setString(1, perAcad);
            rsCS = stCS.executeQuery();
            while (rsCS.next())
            {
                stEp = con
                        .prepareStatement("select count(*) as c from pruebas where codcur = ? and seccion=? and peracad=? and accionid < 3 and logdel=1 and codtippba <> ? and codtippba <> ? and codtippba <> ?");
                stEp.setString(1, rsCS.getString("codcur"));
                stEp.setString(2, rsCS.getString("seccion"));
                stEp.setString(3, perAcad);
                stEp.setString(4, "ep");
                stEp.setString(5, "ef");
                stEp.setString(6, "es");
                rsEp = stEp.executeQuery();
                while (rsEp.next())
                {
                    stTp = con
                            .prepareStatement("select codtippba,peso,numero-numelim as num from curso_tipo_prueba where codcur = ? and seccion = ? and peracad = ? and logdel = 1 and codtippba <> ? and codtippba <> ? and codtippba <> ?");
                    stTp.setString(1, rsCS.getString("codcur"));
                    stTp.setString(2, rsCS.getString("seccion"));
                    stTp.setString(3, perAcad);
                    stTp.setString(4, "ep");
                    stTp.setString(5, "ef");
                    stTp.setString(6, "es");
                    rsTp = stTp.executeQuery();
                    stAl = con
                            .prepareStatement("select codalum from evaluacion where peracad=? and codcur = ? and seccion = ? group by codalum");
                    stAl.setString(1, perAcad);
                    stAl.setString(2, rsCS.getString("codcur"));
                    stAl.setString(3, rsCS.getString("seccion"));
                    rsAl = stAl.executeQuery();
                    while (rsAl.next())
                    {
                        if (rsEp.getInt("c") == 0)
                        {
                            SumaPromedios = 0;
                            SumaPesos = 0;
                            while (rsTp.next())
                            {
                                Suma = 0;
                                Peso = 0;
                                stP = con
                                        .prepareStatement("select e.notfin as notfin, p.peso as peso  from evaluacion as e, pruebas as p where e.peracad= ? and e.codcur=? and e.seccion = ? and e.codalum = ? and e.codcur=p.codcur and e.seccion = p.seccion and e.peracad = p.peracad and e.codtippba = p.codtippba and e.numtippba = p.numtippba and e.codtippba = ? order by e.codcon desc, e.notfin desc limit ?");
                                stP.setString(1, perAcad);
                                stP.setString(2, rsCS.getString("codcur"));
                                stP.setString(3, rsCS.getString("seccion"));
                                stP.setString(4, rsAl.getString("codalum"));
                                stP.setString(5, rsTp.getString("codtippba"));
                                stP.setInt(6, rsTp.getInt("num"));
                                rsP = stP.executeQuery();
                                while (rsP.next())
                                {
                                    Suma = Suma + rsP.getInt("notfin") * rsP.getInt("peso");
                                    Peso = Peso + rsP.getInt("peso");
                                }
                                SumaPromedios = rsTp.getInt("peso") * (Suma / Peso) + SumaPromedios;
                                SumaPesos = rsTp.getInt("peso") + SumaPesos;
                            }
                            rsTp.beforeFirst();
                            PromedioFinal = SumaPromedios / SumaPesos;
                        }
                        else
                        {
                            String temp = "El promedio de prácticas del curso " + rsCS.getString("codcur")
                                    + rsCS.getString("seccion") + " no ha sido calculado";
                            al.add(temp);
                        }
                        notFin = -1;
                        notPar = -1;
                        notSus = -1;
                        recFin = -1;
                        recPar = -1;
                        recSus = -1;
                        stEx = con
                                .prepareStatement("select codtippba,nota,notrec from evaluacion where peracad=? and codcur=? and codalum=? and seccion=? and (codtippba = ? or codtippba = ? or codtippba = ?) order by codtippba");
                        stEx.setString(1, perAcad);
                        stEx.setString(2, rsCS.getString("codcur"));
                        stEx.setString(3, rsAl.getString("codalum"));
                        stEx.setString(4, rsCS.getString("seccion"));
                        stEx.setString(5, "ep");
                        stEx.setString(6, "ef");
                        stEx.setString(7, "es");
                        rsEx = stEx.executeQuery();
                        while (rsEx.next())
                        {
                            if (rsEx.getString("codtippba").equals("EF"))
                            {
                                notFin = rsEx.getInt("nota");
                                if (rsEx.getString("notrec") != null)
                                {
                                    recFin = rsEx.getInt("notrec");
                                }
                            }
                            if (rsEx.getString("codtippba").equals("EP"))
                            {
                                notPar = rsEx.getInt("nota");
                                if (rsEx.getString("notrec") != null)
                                {
                                    recPar = rsEx.getInt("notrec");
                                }
                            }
                            if (rsEx.getString("codtippba").equals("ES"))
                            {
                                notSus = rsEx.getInt("nota");
                                if (rsEx.getString("notrec") != null)
                                {
                                    recSus = rsEx.getInt("notrec");
                                }
                            }
                        }
                        // mir.insertar(perAcad,rsAl.getString("codalum"),rsCS.getString("codcur"),rsCS.getString("seccion"),PromedioFinal,notPar,notFin,notSus,recPar,recFin,recSus);
                    }
                }
            }
        }
        catch (SQLException e1)
        {
            throw new TransactionException(e1);
        }
        return al;
    }
}
