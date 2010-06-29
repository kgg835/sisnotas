package ccuni.java.sysNotas.implDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import ccuni.java.sysNotas.constantes.Constantes;
import ccuni.java.sysNotas.dao.CursoDAO;
import ccuni.java.sysNotas.dao.Transaction;
import ccuni.java.sysNotas.dao.TransactionException;
import ccuni.java.sysNotas.domain.dto.CursoTO;
import ccuni.java.sysNotas.domain.dto.CursoTipoPruebaTO;
import ccuni.java.sysNotas.domain.dto.DocenteTO;
import ccuni.java.sysNotas.domain.dto.PruebaTO;
import ccuni.java.sysNotas.domain.dto.TipoPruebaTO;
import ccuni.java.sysNotas.domain.dto.UsuarioTO;
import ccuni.java.sysNotas.logic.DocenteManager;

public class MysqlCursoDAO implements CursoDAO
{
    private Transaction transaction = null;

    Log log = LogFactory.getLog(MysqlCursoDAO.class);

    public Transaction getTransaction()
    {
        return transaction;
    }

    public void setTransaction(Transaction transaction)
    {
        this.transaction = transaction;
    }

    public MysqlCursoDAO(Transaction transaction)
    {
        // TODO Auto-generated constructor stub
        this.transaction = transaction;
    }

    public boolean existeDocente(String codigoCurso, String seccion) throws TransactionException
    {
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        boolean r = false;
        try
        {
            con = transaction.getConnection();
            st = con.prepareStatement("SELECT  T1.CODDOC FROM CUR_SECC_DOCENTE AS T1 "
                    + "WHERE T1.CODCUR=? AND T1.SECCION=?");
            st.setString(1, codigoCurso);
            st.setString(2, seccion);
            // st.setInt(3, Constantes.ACTIVO);
            rs = st.executeQuery();
            if (rs.next())
            {
                if (rs.getString("CODDOC") != null)
                    r = true;
            }
        }
        catch (SQLException e1)
        {
            e1.printStackTrace();
            log.error("Error al consultar los parametros, curso " + codigoCurso + " seccion " + seccion);
            throw new TransactionException(e1);
        }
        return r;
    }

    public boolean existenParametrosCalificacion(String codigoCurso, String seccion) throws TransactionException
    {
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        boolean r = false;
        try
        {
            con = transaction.getConnection();
            st = con.prepareStatement("SELECT  T2.CODTIPPBA , T1.CODDOC FROM CUR_SECC_DOCENTE AS T1 "
                    + "LEFT JOIN CURSO_TIPO_PRUEBA AS T2 ON(T1.CODCUR = T2.CODCUR AND T1.SECCION = T2.SECCION) "
                    + "WHERE T1.CODCUR=? AND T1.SECCION=? AND T2.LOGDEL=?");
            st.setString(1, codigoCurso);
            st.setString(2, seccion);
            st.setInt(3, Constantes.ACTIVO);
            rs = st.executeQuery();
            if (rs.next())
            {
                if (rs.getString("CODDOC") != null)
                    r = true;
            }
        }
        catch (SQLException e1)
        {
            throw new TransactionException(e1);
        }
        return r;
    }

    public CursoTO obtenerParametros(String codigoCurso, String seccion) throws TransactionException
    {
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        CursoTO to = new CursoTO();
        TipoPruebaTO tipoPrueba = null;
        CursoTipoPruebaTO cursoTipoPrueba = null;
        to.setCursoId(codigoCurso);
        to.setSeccion(seccion);
        try
        {
            // if (existenParametrosCalificacion(codigoCurso, seccion)) {
            con = transaction.getConnection();
            st = con.prepareStatement("SELECT T1.PARAM_DEFINIDOS, T1.CODCUR, T1.SECCION, T1.NOMCUR, T1.CODDOC, "
                    + "T2.CODTIPPBA, T2.NUMERO, T2.NUMELIM, T2.PESO, T3.NOMBRE, T2.LOGDEL, T3.LOGDEL "
                    + "FROM CUR_SECC_DOCENTE AS T1 LEFT JOIN CURSO_TIPO_PRUEBA "
                    + "AS T2 ON (T1.CODCUR = T2.CODCUR AND T1.SECCION = T2.SECCION)"
                    + " LEFT JOIN TIPO_PRUEBA AS T3 ON(T3.CODTIPPBA=T2.CODTIPPBA)"
                    + " WHERE T1.CODCUR=? AND T1.SECCION=?");
            st.setString(1, codigoCurso);
            st.setString(2, seccion);
            rs = st.executeQuery();
            ArrayList<TipoPruebaTO> tiposDePrueba = new ArrayList<TipoPruebaTO>();
            while (rs.next())
            {
                if (to.getNombre() == null)
                    to.setNombre(rs.getString("NOMCUR"));
                to.setParam_definidos(rs.getInt("PARAM_DEFINIDOS"));
                String codTipPba = rs.getString("CODTIPPBA");
                if (codTipPba != null)
                {
                    if (rs.getInt("T2.LOGDEL") == 1 && rs.getInt("LOGDEL") == 1)
                    {
                        tipoPrueba = new TipoPruebaTO();
                        tipoPrueba.setCodTipPba(rs.getString("CODTIPPBA"));
                        tipoPrueba.setNombre(rs.getString("NOMBRE"));
                        cursoTipoPrueba = new CursoTipoPruebaTO();
                        cursoTipoPrueba.setNumero(rs.getInt("NUMERO"));
                        cursoTipoPrueba.setNumelim(rs.getInt("NUMELIM"));
                        cursoTipoPrueba.setPeso(rs.getInt("PESO"));
                        tipoPrueba.setCursoTipoPrueba(cursoTipoPrueba);
                        tiposDePrueba.add(tipoPrueba);
                    }
                }
                String codDocente = rs.getString("CODDOC");
                if (codDocente != null)
                {
                    DocenteManager dm = new DocenteManager();
                    DocenteTO todoc = dm.find(codDocente);
                    to.setDocenteResp(todoc);
                }
            }
            to.setTiposDePrueba(tiposDePrueba);
            // }
        }
        catch (SQLException e1)
        {
            log.error("Error al obtener los parametros, curso " + codigoCurso + " seccion " + seccion);
            e1.printStackTrace();
            throw new TransactionException("Error al obtener los parametros", e1);
        }
        return to;
    }

    public ArrayList<PruebaTO> obtenerParametros(String codigoCurso, String seccion, String codTipoPrueba)
            throws TransactionException
    {
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        PruebaTO prueba = null;
        ArrayList<PruebaTO> pruebas = null;
        try
        {
            con = transaction.getConnection();
            st = con
                    .prepareStatement("SELECT NUMTIPPBA, PESO FROM PRUEBAS WHERE CODCUR=? AND SECCION=? AND CODTIPPBA=? AND LOGDEL=?");
            st.setString(1, codigoCurso);
            st.setString(2, seccion);
            st.setString(3, codTipoPrueba);
            st.setInt(4, Constantes.ACTIVO);
            rs = st.executeQuery();
            pruebas = new ArrayList<PruebaTO>();
            while (rs.next())
            {
                prueba = new PruebaTO();
                prueba.setNumtippba(rs.getInt("NUMTIPPBA"));
                prueba.setPeso(rs.getInt("PESO"));
                pruebas.add(prueba);
            }
        }
        catch (SQLException e1)
        {
            log.error("Error al obtener los parametros, curso " + codigoCurso + "seccion " + seccion + " tipo prueba "
                    + codTipoPrueba);
            e1.printStackTrace();
            throw new TransactionException("Error al obtener los parametros", e1);
        }
        return pruebas;
    }

    public Integer updatePruebas(String codigoCurso, String seccion, ArrayList<PruebaTO> pruebas)
            throws TransactionException
    {
        // no se usa
        return null;
    }

    public ArrayList<TipoPruebaTO> obtenerTiposDePrueba() throws TransactionException
    {
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        TipoPruebaTO tipoPrueba = null;
        ArrayList<TipoPruebaTO> tiposDePrueba = null;
        try
        {
            con = transaction.getConnection();
            st = con.createStatement();
            rs = st.executeQuery("SELECT CODTIPPBA,NOMBRE FROM TIPO_PRUEBA WHERE LOGDEL=" + Constantes.ACTIVO);
            tiposDePrueba = new ArrayList<TipoPruebaTO>();
            while (rs.next())
            {
                tipoPrueba = new TipoPruebaTO();
                tipoPrueba.setCodTipPba(rs.getString("CODTIPPBA"));
                tipoPrueba.setNombre(rs.getString("NOMBRE"));
                tiposDePrueba.add(tipoPrueba);
            }
        }
        catch (SQLException e1)
        {
            log.error("Error al obtener los tipos de prueba");
            e1.printStackTrace();
            throw new TransactionException("Error al obtener los tipos de prueba", e1);
        }
        return tiposDePrueba;
    }

    public Integer addTipoPrueba(String codCur, String secc, TipoPruebaTO to, String codUser, String perAcad)
            throws TransactionException
    {
        Connection con = null;
        PreparedStatement st = null;
        PreparedStatement st2 = null;
        int n;
        Integer num = null;
        try
        {
            con = transaction.getConnection();
            if (!to.isExamen())
            {
                st = con.prepareStatement("INSERT INTO CURSO_TIPO_PRUEBA(CODCUR, SECCION, CODTIPPBA, "
                        + "NUMERO, NUMELIM, LOGDEL, USRINS, DTEINS, USRUPD, DTEUPD, PESO, PERACAD) "
                        + "VALUES(?, ?, ?,?,?,?,?,NOW(),?,NOW(),?,?)");
                st.setString(1, codCur);
                st.setString(2, secc);
                st.setString(3, to.getCodTipPba());
                st.setInt(4, to.getCursoTipoPrueba().getNumero());
                st.setInt(5, to.getCursoTipoPrueba().getNumelim());
                st.setInt(6, Constantes.ACTIVO);
                st.setString(7, codUser);
                st.setString(8, codUser);
                st.setInt(9, to.getCursoTipoPrueba().getPeso());
                st.setString(10, perAcad);
                n = st.executeUpdate();
                for (int i = 0; i < to.getCursoTipoPrueba().getNumero(); i++)
                {
                    st2 = con
                            .prepareStatement("INSERT INTO PRUEBAS"
                                    + "(CODCUR, CODTIPPBA, NUMTIPPBA, PESO, LOGDEL, USRINS, DTEINS, SECCION, PERACAD, ACCIONID)"
                                    + "VALUES (?,?,?,?,?,?,NOW(),?,?,?)");
                    st2.setString(1, codCur);
                    st2.setString(2, to.getCodTipPba());
                    st2.setInt(3, i + 1);
                    st2.setInt(4, Constantes.PESO_DEFAULT);
                    st2.setInt(5, Constantes.ACTIVO);
                    st2.setString(6, codUser);
                    st2.setString(7, secc);
                    st2.setString(8, perAcad);
                    st2.setInt(9, Constantes.ACCION_DEFAULT);
                    st2.executeUpdate();
                }
            }
            else
            {
                st = con.prepareStatement("INSERT INTO CURSO_TIPO_PRUEBA(CODCUR,SECCION,CODTIPPBA,"
                        + "USRINS,DTEINS,PERACAD) " + "VALUES(?, ?, ?,?,NOW(),?)");
                st.setString(1, codCur);
                st.setString(2, secc);
                st.setString(3, to.getCodTipPba());
                st.setString(4, codUser);
                st.setString(5, perAcad);
                n = st.executeUpdate();
                st2 = con.prepareStatement("INSERT INTO PRUEBAS"
                        + "(CODCUR, CODTIPPBA, NUMTIPPBA, USRINS, DTEINS, SECCION, PERACAD, ACCIONID)"
                        + "VALUES (?,?,?,?,NOW(),?,?,?)");
                st2.setString(1, codCur);
                st2.setString(2, to.getCodTipPba());
                st2.setInt(3, 1);
                st2.setString(4, codUser);
                st2.setString(5, secc);
                st2.setString(6, perAcad);
                st2.setInt(7, Constantes.ACCION_DEFAULT);
                st2.executeUpdate();
            }
            num = new Integer(n);
        }
        catch (SQLException e1)
        {
            log.error("Error al agregar un tipo de prueba al curso " + codCur + " seccion " + secc + " tipo de prueba "
                    + to.getCodTipPba());
            e1.printStackTrace();
            throw new TransactionException("Error al agregar un tipo de prueba al curso", e1);
        }
        return num;
    }

    public Integer updateTipoPrueba(String codCur, String secc, TipoPruebaTO to, String codUser, String perAcad)
            throws TransactionException
    {
        Connection con = null;
        PreparedStatement st = null;
        PreparedStatement st2 = null;
        int n;
        Integer num = null;
        try
        {
            con = transaction.getConnection();
            if (!to.isExamen())
            {
                st = con
                        .prepareStatement("UPDATE CURSO_TIPO_PRUEBA SET NUMERO=?, NUMELIM=?, PESO=?, USRUPD=?, DTEUPD=NOW(), LOGDEL=? WHERE CODCUR=? AND SECCION=? AND CODTIPPBA=?");
                st.setInt(1, to.getCursoTipoPrueba().getNumero());
                st.setInt(2, to.getCursoTipoPrueba().getNumelim());
                st.setInt(3, to.getCursoTipoPrueba().getPeso());
                st.setString(4, codUser);
                st.setInt(5, to.getCursoTipoPrueba().getLogdel());
                st.setString(6, codCur);
                st.setString(7, secc);
                st.setString(8, to.getCodTipPba());
                // elimino las que habian
                st2 = con.prepareStatement("DELETE FROM PRUEBAS WHERE CODTIPPBA=? AND CODCUR=? AND SECCION=?");
                st2.setString(1, to.getCodTipPba());
                st2.setString(2, codCur);
                st2.setString(3, secc);
                st2.executeUpdate();
                // creo las nuevas
                for (int i = 0; i < to.getCursoTipoPrueba().getNumero(); i++)
                {
                    st2 = con
                            .prepareStatement("INSERT INTO PRUEBAS"
                                    + "(CODCUR, CODTIPPBA, NUMTIPPBA, PESO, LOGDEL, USRINS, DTEINS, SECCION, PERACAD, ACCIONID)"
                                    + "VALUES (?,?,?,?,?,?,NOW(),?,?,?)");
                    st2.setString(1, codCur);
                    st2.setString(2, to.getCodTipPba());
                    st2.setInt(3, i + 1);
                    st2.setInt(4, Constantes.PESO_DEFAULT);
                    st2.setInt(5, Constantes.ACTIVO);
                    st2.setString(6, codUser);
                    st2.setString(7, secc);
                    // por ahora
                    st2.setString(8, perAcad);
                    st2.setInt(9, Constantes.ACCION_DEFAULT);
                    st2.executeUpdate();
                }
                n = st.executeUpdate();
            }
            else
            {
                st = con
                        .prepareStatement("UPDATE CURSO_TIPO_PRUEBA SET USRUPD=?, DTEUPD=NOW(), LOGDEL=? WHERE CODCUR=? AND SECCION=? AND CODTIPPBA=?");
                st.setString(1, codUser);
                st.setInt(2, to.getCursoTipoPrueba().getLogdel());
                st.setString(3, codCur);
                st.setString(4, secc);
                st.setString(5, to.getCodTipPba());
                n = st.executeUpdate();
                st = con.prepareStatement("UPDATE PRUEBAS SET LOGDEL=? WHERE CODCUR=? AND SECCION=? AND CODTIPPBA=?");
                st.setInt(1, Constantes.ACTIVO);
                st.setString(2, codCur);
                st.setString(3, secc);
                st.setString(4, to.getCodTipPba());
                st.executeUpdate();
            }
            num = new Integer(n);
        }
        catch (SQLException e1)
        {
            log.error("Error al actualizar un tipo de prueba al curso " + codCur + " seccion " + secc
                    + " tipo de prueba " + to.getCodTipPba());
            e1.printStackTrace();
            throw new TransactionException("Error al actualizar el tipos de prueba", e1);
        }
        return num;
    }

    public Integer eliminarTipoPrueba(String codCur, String secc, String codTipPba, String codUser)
            throws TransactionException
    {
        Connection con = null;
        PreparedStatement st = null;
        PreparedStatement st2 = null;
        int n;
        Integer num = null;
        try
        {
            con = transaction.getConnection();
            st = con.prepareStatement("UPDATE CURSO_TIPO_PRUEBA SET LOGDEL=?, USRUPD=?, DTEUPD=NOW()"
                    + " WHERE CODCUR=? AND SECCION=? AND CODTIPPBA=?");
            st.setInt(1, Constantes.INACTIVO);
            st.setString(2, codUser);
            st.setString(3, codCur);
            st.setString(4, secc);
            st.setString(5, codTipPba);
            n = st.executeUpdate();
            st2 = con.prepareStatement("UPDATE PRUEBAS SET LOGDEL=? WHERE CODCUR=? AND SECCION=? AND CODTIPPBA=?");
            st2.setInt(1, Constantes.INACTIVO);
            st2.setString(2, codCur);
            st2.setString(3, secc);
            st2.setString(4, codTipPba);
            st2.executeUpdate();
            num = new Integer(n);
        }
        catch (SQLException e1)
        {
            log.error("Error al actualizar un tipo de prueba al curso " + codCur + " seccion " + secc
                    + " tipo de prueba " + codTipPba);
            e1.printStackTrace();
            throw new TransactionException("Error al obtener los tipos de prueba", e1);
        }
        return num;
    }

    public TipoPruebaTO find(String codCur, String secc, TipoPruebaTO tipoprueba) throws TransactionException
    {
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement st = null;
        TipoPruebaTO to = null;
        CursoTipoPruebaTO ctp = null;
        try
        {
            con = transaction.getConnection();
            st = con.prepareStatement("SELECT * FROM CURSO_TIPO_PRUEBA WHERE CODCUR=? AND SECCION=? AND CODTIPPBA=?");
            st.setString(1, codCur);
            st.setString(2, secc);
            st.setString(3, tipoprueba.getCodTipPba());
            rs = st.executeQuery();
            if (rs.next())
            {
                to = new TipoPruebaTO();
                to.setCodTipPba(tipoprueba.getCodTipPba());
                // to.setNombre(rs.getString("NOMBRE"));
                ctp = new CursoTipoPruebaTO();
                ctp.setNumero(rs.getInt("NUMERO"));
                ctp.setNumelim(rs.getInt("NUMELIM"));
                ctp.setPeso(rs.getInt("PESO"));
                ctp.setLogdel(rs.getInt("LOGDEL"));
                to.setCursoTipoPrueba(ctp);
            }
        }
        catch (SQLException e)
        {
            log.error("Buscando el curso " + codCur + " seccion " + secc);
            e.printStackTrace();
            throw new TransactionException("Error al buscar el curso");
        }
        return to;
    }

    public int updateDocente(String codDoc, String codCur, String seccion) throws TransactionException
    {
        Connection con = null;
        PreparedStatement st = null;
        int n = 0;
        try
        {
            con = transaction.getConnection();
            st = con.prepareStatement("UPDATE CUR_SECC_DOCENTE SET CODDOC=? WHERE CODCUR=? AND SECCION=?");
            st.setString(1, codDoc);
            st.setString(2, codCur);
            st.setString(3, seccion);
            n = st.executeUpdate();
        }
        catch (SQLException e)
        {
            log.error("Error al actualizar el docente " + codDoc + " al curso " + codCur);
            e.printStackTrace();
            throw new TransactionException("Error al buscar el usuario");
        }
        return n;
    }

    public int insertDocente(String codDoc, String codCur, String seccion) throws TransactionException
    {
        Connection con = null;
        PreparedStatement st = null;
        int n = 0;
        try
        {
            con = transaction.getConnection();
            st = con.prepareStatement("INSERT INTO CUR_SECC_DOCENTE(CODCUR, SECCION, CODDOC) VALUES(?,?,?)");
            st.setString(3, codDoc);
            st.setString(1, codCur);
            st.setString(2, seccion);
            n = st.executeUpdate();
        }
        catch (SQLException e)
        {
            log.error("Error al insertar docente " + codDoc + " al curso " + codCur);
            e.printStackTrace();
            throw new TransactionException("Error al insertar docente a un curso");
        }
        return n;
    }

    public ArrayList<CursoTO> selectCursos(UsuarioTO usuario, String perAcad) throws TransactionException
    {
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement st = null;
        ArrayList<CursoTO> cursos = null;
        CursoTO curso = null;
        DocenteTO docente = null;
        int i = 1;
        try
        {
            con = transaction.getConnection();
            String query = "SELECT * FROM CUR_SECC_DOCENTE WHERE ";
            if (usuario.getCodGrpUsr().equalsIgnoreCase("3"))
                query += "CODDOC=? and ";
            query += "PERACAD=?";
            st = con.prepareStatement(query);
            if (usuario.getCodGrpUsr().equalsIgnoreCase("3"))
                st.setString(i++, usuario.getCodUsr());
            st.setString(i++, perAcad);
            rs = st.executeQuery();
            cursos = new ArrayList<CursoTO>();
            while (rs.next())
            {
                docente = new DocenteTO();
                curso = new CursoTO();
                curso.setCursoId(rs.getString("CODCUR"));
                curso.setNombre(rs.getString("NOMCUR"));
                docente.setCodDoc(rs.getString("CODDOC"));
                curso.setDocenteResp(docente);
                curso.setSeccion(rs.getString("SECCION"));
                curso.setPerAcad(rs.getString("PERACAD"));
                curso.setParam_definidos(rs.getInt("PARAM_DEFINIDOS"));
                curso.setEstado(rs.getInt("ESTADO"));
                cursos.add(curso);
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            throw new TransactionException("Error buscar cursos x docentes");
        }
        return cursos;
    }

    public int actualizarEstado(String cursoId, String seccion, int estado) throws TransactionException
    {
        Connection con = null;
        PreparedStatement st = null;
        int n = 0;
        try
        {
            con = transaction.getConnection();
            st = con.prepareStatement("UPDATE CUR_SECC_DOCENTE SET PARAM_DEFINIDOS=? WHERE CODCUR=? AND SECCION=?");
            st.setInt(1, estado);
            st.setString(2, cursoId);
            st.setString(3, seccion);
            n = st.executeUpdate();
        }
        catch (SQLException e)
        {
            log.error("Error al actualizar el estado " + estado + " al curso " + cursoId);
            e.printStackTrace();
            throw new TransactionException("Error al actualizar el curso");
        }
        return n;
    }
}
