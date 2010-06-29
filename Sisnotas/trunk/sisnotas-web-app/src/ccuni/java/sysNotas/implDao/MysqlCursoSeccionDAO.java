package ccuni.java.sysNotas.implDao;

import java.sql.*;

import ccuni.java.sysNotas.constantes.Constantes;
import ccuni.java.sysNotas.dao.CursoSeccionDAO;
import ccuni.java.sysNotas.dao.Transaction;
import ccuni.java.sysNotas.dao.TransactionException;
import ccuni.java.sysNotas.domain.dto.CursoTO;
import ccuni.java.sysNotas.domain.dto.DocenteTO;
import ccuni.java.sysNotas.domain.dto.PruebaTO;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import java.util.*;

public class MysqlCursoSeccionDAO implements CursoSeccionDAO {
	private Transaction transaction = null;
	Log log = LogFactory.getLog(MysqlCursoSeccionDAO.class);

	public Transaction getTransaction() {
		return transaction;
	}

	public void setTransaction(Transaction transaction) {
		this.transaction = transaction;
	}

	public MysqlCursoSeccionDAO(Transaction transaction) {
		super();
		// TODO Auto-generated constructor stub
		this.transaction = transaction;
	}

	public ArrayList<CursoTO> obtenerCursosSeccion()
			throws TransactionException {
		Connection c = null;
		Statement st = null;
		ResultSet rs = null;
		CursoTO to = null;
		DocenteTO to2 = null;
		ArrayList<CursoTO> list = null;
		try {
			c = transaction.getConnection();
			st = c.createStatement();
			rs = st.executeQuery("SELECT * FROM CUR_SECC_DOCENTE");
			list = new ArrayList<CursoTO>();
			while (rs.next()) {
				to = new CursoTO();
				to.setCursoId(rs.getString("CODCUR"));
				to.setSeccion(rs.getString("SECCION"));
				to.setPerAcad(rs.getString("PERACAD"));
				to2 = new DocenteTO();
				to2.setCodDoc(rs.getString("CODDOC"));
				to.setDocenteResp(to2);
				to.setNombre(rs.getString("NOMCUR"));
				list.add(to);
			}
		} catch (SQLException e1) {
			log.error("Error consultando cursos seccion");
			throw new TransactionException(e1);
		} catch (TransactionException e) {
			throw new TransactionException(e);
		}
		return list;
	}

	public ArrayList<CursoTO> obtenerCursosSeccion(String perAcad)
			throws TransactionException {
		return null;
	}

	public int deleteAll() throws TransactionException {
		return 0;
	}

	public int registrarCursosSeccion(List list, String perAcad)
			throws TransactionException {
		int n = 0;
		try {
			for (int i = 0; i < list.size(); i++) {
				n += this.insert((CursoTO)list.get(i), perAcad);
			}
		} catch (TransactionException e1) {
			log.error("Error en la migracion de cursos seccion");
			e1.printStackTrace();
			throw new TransactionException("Error en la migracion de cursos seccion",
					e1);
		}
		return n;
	}

	public int insert(CursoTO to, String perAcad) throws TransactionException {
		Connection con = null;
		PreparedStatement st = null;
		int n;
		try {
			con = transaction.getConnection();

			st = con
					.prepareStatement("INSERT INTO CUR_SECC_DOCENTE(CODCUR, SECCION, PERACAD, NOMCUR) "
							+ "VALUES(?,?,?,?)");
			st.setString(1, to.getCursoId());
			st.setString(2, to.getSeccion());
			st.setString(3, perAcad);
			st.setString(4, to.getNombre());
			n = st.executeUpdate();
		} catch (SQLException e1) {
			log.error("Error al insertar el curso "+to.getCursoId());
			e1.printStackTrace();
			throw new TransactionException("Error al obtener los parametros",
					e1);
		}
		return n;
	}
	
	public int delete(CursoTO to) throws TransactionException {
		Connection con = null;
		PreparedStatement st = null;
		int n;
		try {
			con = transaction.getConnection();
			st = con
					.prepareStatement("DELETE FROM CUR_SECC_DOCENTE WHERE CODCUR=? AND SECCION=?");
			//st.setInt(1, Constantes.INACTIVO);
			st.setString(1, to.getCursoId());
			st.setString(2, to.getSeccion());
			//tambien se tiene que eloiminar las notas de las parcticas ingresadas
			n = st.executeUpdate();
		} catch (SQLException e1) {
			log.error("Error al eliminar el curso "+to.getCursoId());
			e1.printStackTrace();
			throw new TransactionException("Error al eliminar el curso",
					e1);
		}
		return n;
	}
	public List listaCursosSinPromedio() throws TransactionException{
		Connection con = null;
		CallableStatement st = null;
		List<CursoTO> list = new ArrayList<CursoTO>();
		ResultSet rs=null;
		CursoTO to=null;
		try {
			con = transaction.getConnection();
			st = con.prepareCall("call obtenerCursosSinPromedio()");
			//st.setInt(1, Constantes.INACTIVO);
			//st.setString(1, to.getCursoId());
			//st.setString(2, to.getSeccion());
			//tambien se tiene que eloiminar las notas de las parcticas ingresadas
			rs = st.executeQuery();
			while (rs.next()) {
				to = new CursoTO();
				to.setCursoId(rs.getString("CODCUR"));
				to.setSeccion(rs.getString("SECCION"));
				//to.setPerAcad(rs.getString("PERACAD"));
				to.setNombre(rs.getString("NOMCUR"));
				list.add(to);
			}
			
			
		} catch (SQLException e1) {
			//log.error("Error al eliminar el curso "+to.getCursoId());
			e1.printStackTrace();
			throw new TransactionException("Error al eliminar el curso",
					e1);
		}
		return list;
	}
}
