package ccuni.java.sysNotas.implDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import ccuni.java.sysNotas.dao.InsertarResultadoDAO;
import ccuni.java.sysNotas.dao.Transaction;
import ccuni.java.sysNotas.dao.TransactionException;

public class ClipperInsertarResultadoDAO implements InsertarResultadoDAO
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

    public ClipperInsertarResultadoDAO()
    {
        super();
    }

    public ClipperInsertarResultadoDAO(Transaction t)
    {
        this.transaction = t;
    }

    public void insertar(String perAcad, String codAlu, String codCur, String seccion, double promPra, int notPar,
            int notFin, int notSus, int recPar, int recFin, int recSus) throws TransactionException
    {
        Connection c = null;
        PreparedStatement st = null;
        PreparedStatement st2 = null;
        int i = 1;
        try
        {
            c = transaction.getConnection();
            String query1 = null;
            String query1a = "UPDATE " + "MATR" + perAcad + " SET NOTPRA = ? ";
            String query1b = ",NOTPAR = ? ";
            String query1c = ",NOTFIN = ? ";
            String query1d = ",NOTSUS = ? ";
            String query2 = "WHERE CODALU = ? AND CODCUR = ? AND SECCION = ? ";
            String query3 = ",RECPAR = ?,RECLAMP = ? ";
            String query4 = ",RECFIN = ?,RECLAMF = ? ";
            String query5 = ",RECSUS = ?,RECLAMS = ? ";
            String query6 = null;
            String query7 = null;
            String query8 = null;
            String query9 = null;
            String query1A = null;
            String query1B = null;
            String query1C = null;
            if (notPar != -1)
            {
                query1A = query1a + query1b;
            }
            else
            {
                query1A = query1a;
            }
            if (notFin != -1)
            {
                query1B = query1A + query1c;
            }
            else
            {
                query1B = query1A;
            }
            if (notSus != -1)
            {
                query1C = query1B + query1d;
            }
            else
            {
                query1C = query1B;
            }
            query1 = query1C;
            if (recPar != -1)
            {
                query6 = query1 + query3;
            }
            else
            {
                query6 = query1;
            }
            if (recFin != -1)
            {
                query7 = query6 + query4;
            }
            else
            {
                query7 = query6;
            }
            if (recSus != -1)
            {
                query8 = query7 + query5;
            }
            else
            {
                query8 = query7;
            }
            query9 = query8 + query2;
            st = c.prepareStatement(query9);
            st.setDouble(i++, promPra);
            if (notPar != -1)
            {
                st.setInt(i++, notPar);
            }
            if (notFin != -1)
            {
                st.setInt(i++, notFin);
            }
            if (notSus != -1)
            {
                st.setInt(i++, notSus);
            }
            if (recPar != -1)
            {
                st.setInt(i++, recPar);
                st.setBoolean(i++, true);
            }
            if (recFin != -1)
            {
                st.setInt(i++, recFin);
                st.setBoolean(i++, true);
            }
            if (recSus != -1)
            {
                st.setInt(i++, recSus);
                st.setBoolean(i++, true);
            }
            st.setString(i++, codAlu);
            st.setString(i++, codCur);
            st.setString(i++, seccion);
            st.executeUpdate();
        }
        catch (TransactionException e)
        {
            throw new TransactionException(e);
        }
        catch (SQLException e1)
        {
            throw new TransactionException(e1);
        }
    }

    public int insertIntoMatriClipper(String cursoId, String seccion, String codAlumno, String perAcad, String notfin,
            String recfin, String notpar, String recpar, String notsus, String recsus, String prom, String promnum)
            throws TransactionException
    {
        Connection c = null;
        PreparedStatement statement = null;
        Statement st = null;
        boolean flag = false;
        int n = -1;
        // String
        // kuery="UPDATE MATR062 SET NOTPAR=18 WHERE CODALU='20064533D' AND CODCUR='AU511' AND SECCION='A'";
        /** construyendo el query */
        String nombreTabla = "MATR" + perAcad;
        String query = "UPDATE " + nombreTabla + " SET ";
        if (notpar != null && !"".equals(notpar))
        {
            flag = true;
            query += "NOTPAR=?,";
            if (recpar != null && !"".equals(notpar))
            {
                query += "RECPAR=?,";
            }
            query += "RECLAMP=?,";
        }
        if (notfin != null && !"".equals(notfin))
        {
            flag = true;
            query += "NOTFIN=?,";
            if (recfin != null && !"".equals(recfin))
            {
                query += "RECFIN=?,";
            }
            query += "RECLAMF=?,";
        }
        if (notsus != null && !"".equals(notsus))
        {
            flag = true;
            query += "NOTSUS=?,";
            if (recsus != null && !"".equals(recsus))
            {
                query += "RECSUS=?,";
            }
            query += "RECLAMS=?,";
        }
        if (flag = true)
        {
            query = query.substring(0, query.length() - 1);
            query += ",PROM=?,PROMNUM=? ";
            query += " WHERE CODALU=? and CODCUR=? and SECCION=?";
        }
        else
        {
            System.out.println("NO hay registrado ninguna nota..todas son nulas");
            return n;
        }
        int i = 1;
        try
        {
            c = transaction.getConnection();
            // st=c.createStatement();
            // st.executeUpdate(kuery);
            statement = c.prepareStatement(query);
            if (notpar != null && !"".equals(notpar))
            {
                flag = true;
                statement.setInt(i++, Integer.parseInt(notpar));
                if (recpar != null && !"".equals(notpar))
                {
                    statement.setInt(i++, Integer.parseInt(recpar));
                    statement.setBoolean(i++, true);
                }
                else
                {
                    statement.setBoolean(i++, false);
                }
            }
            if (notfin != null && !"".equals(notfin))
            {
                flag = true;
                statement.setInt(i++, Integer.parseInt(notfin));
                if (recfin != null && !"".equals(recfin))
                {
                    statement.setInt(i++, Integer.parseInt(recfin));
                    statement.setBoolean(i++, true);
                }
                else
                {
                    statement.setBoolean(i++, false);
                }
            }
            if (notsus != null && !"".equals(notsus))
            {
                flag = true;
                statement.setInt(i++, Integer.parseInt(notsus));
                if (recsus != null && !"".equals(recsus))
                {
                    statement.setInt(i++, Integer.parseInt(recsus));
                    statement.setBoolean(i++, true);
                }
                else
                {
                    statement.setBoolean(i++, false);
                }
            }
            statement.setDouble(i++, Double.parseDouble(prom));
            statement.setDouble(i++, Double.parseDouble(promnum));
            statement.setString(i++, codAlumno);
            statement.setString(i++, cursoId);
            statement.setString(i++, seccion);
            n = statement.executeUpdate();
            if (n == 0)
            {
                System.out.println("No se pudo realizar el registro:" + cursoId + " \n" + seccion + "\n " + notfin
                        + " \n" + recfin + " \n" + notpar + " \n" + recpar + "\n " + notsus + " \n" + recsus + " \n"
                        + prom + "\n " + promnum);
                return n;
            }
            System.out.println("Se realizó con exito el registro:" + cursoId + " \n" + seccion + "\n " + notfin + " \n"
                    + recfin + " \n" + notpar + " \n" + recpar + "\n " + notsus + " \n" + recsus + " \n" + prom + "\n "
                    + promnum);
            // throw new TransactionException("No se pudo crear el usuario");*/
        }
        catch (TransactionException e1)
        {
            e1.printStackTrace();
            throw new TransactionException("Error al sincronizar");
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            System.out.println("error al sincronizar" + e);
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
        return n;
    }
}
