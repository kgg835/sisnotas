package ccuni.java.sysNotas.implDao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import ccuni.java.sysNotas.constantes.Constantes;
import ccuni.java.sysNotas.dao.CursoSeccionDAO;
import ccuni.java.sysNotas.dao.Transaction;
import ccuni.java.sysNotas.dao.TransactionException;
import ccuni.java.sysNotas.domain.dto.*;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ClipperCursoSeccionDAO implements CursoSeccionDAO {
	private Transaction transaction = null;
	Log log = LogFactory.getLog(ClipperCursoSeccionDAO.class);

	public Transaction getTransaction() {
		return transaction;
	}

	public void setTransaction(Transaction transaction) {
		this.transaction = transaction;
	}

	public ClipperCursoSeccionDAO(Transaction transaction) {
		super();
		// TODO Auto-generated constructor stub
		this.transaction = transaction;
	}

	public ArrayList<CursoTO> obtenerCursosSeccion(String perAcad)
			throws TransactionException {
		Connection c = null;
		Statement st = null;
		PreparedStatement st2 = null;
		ResultSet rs = null;
		ResultSet rs2 = null;
		CursoTO to = null;
		ArrayList<CursoTO> list = null;
		try {
			c = transaction.getConnection();
			st = c.createStatement();
			// obtiene todos los docentes que tengan definido su codigo
			rs = st.executeQuery("SELECT CODCUR, SECCION FROM "
					+ Constantes.TB_SECCION + perAcad + " ORDER BY CODCUR, SECCION");
			list = new ArrayList<CursoTO>();
			while (rs.next()) {
				to = new CursoTO();
				to.setCursoId(rs.getString("CODCUR"));
				to.setSeccion(rs.getString("SECCION"));
				to.setPerAcad(perAcad);
				st2 = c.prepareStatement("SELECT NOMCUR FROM "
						+ Constantes.TB_CURRICULA + " WHERE CODCUR=?");
				st2.setString(1, to.getCursoId());
				rs2 = st2.executeQuery();
				if (rs2.next()) {
					to.setNombre(rs2.getString("NOMCUR"));
				}
				list.add(to);
			}
		} catch (SQLException e1) {
			log.error("Error SQL, Error al obtener los cursos seccion");
			e1.printStackTrace();
			
			throw new TransactionException(e1);
			
		} catch (TransactionException e) {
			log.error("Error de la transaccion, al obtener los cursos seccion");
			e.printStackTrace();
			throw new TransactionException(e);
			
		}
		return list;
	}

	public int insert(CursoTO to, String perAcad) throws TransactionException {
		return 0;
	}
	

	public int registrarCursosSeccion(List list, String perAcad)
			throws TransactionException {
		return 0;
	}
	public ArrayList<CursoTO> obtenerCursosSeccion()
	throws TransactionException {
		return null;
	}
	public int delete(CursoTO to) throws TransactionException {
		return 0;
	}

	public List listaCursosSinPromedio() throws TransactionException {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
