package ccuni.java.sysNotas.implDao;

import ccuni.java.sysNotas.dao.DocenteDAO;
import ccuni.java.sysNotas.dao.*;
import ccuni.java.sysNotas.dao.TransactionException;
import ccuni.java.sysNotas.domain.dto.*;
import java.util.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.sql.*;

public class ClipperDocenteDAO implements DocenteDAO {
	private Transaction transaction = null;

	Log log = LogFactory.getLog(ClipperDocenteDAO.class);

	public Transaction getTransaction() {
		return transaction;
	}

	public ClipperDocenteDAO() {
		super();
	}

	public ClipperDocenteDAO(Transaction t) {
		this.transaction = t;
	}

	public ArrayList<DocenteTO> selectAllDocentes() throws TransactionException {
		Connection c = null;
		Statement statement = null;
		ResultSet result = null;
		int i = 1;
		DocenteTO to = null;
		try {
			c = transaction.getConnection();
			statement = c.createStatement();

			// obtiene todos los docentes que tengan definido su codigo
			result = statement
					.executeQuery("select codigo, nombres, apellidos from docentes where codigo is not null order by apellidos");
			ArrayList<DocenteTO> listaDocentes = new ArrayList<DocenteTO>();
			while (result.next()) {
				to = new DocenteTO();
				to.setCodDoc(result.getString("codigo"));
				to.setNombres(result.getString("nombres"));
				to.setApellidos(result.getString("apellidos"));
				listaDocentes.add(to);
			}
			return listaDocentes;
		} catch (SQLException e1) {
			log.error("Error al consultar los docentes");
			e1.printStackTrace();
			throw new TransactionException(e1);
		} catch (TransactionException e) {
			log.error("Error al consultar los docentes");
			e.printStackTrace();
			throw new TransactionException(e);
		}
	}

	public int eliminar() throws TransactionException {
		Connection c = null;
		Statement statement = null;
		int n;
		DocenteTO to = null;
		try {
			c = transaction.getConnection();
			statement = c.createStatement();
			n = statement
					.executeUpdate("delete from docentes where codigo is null");
		} catch (SQLException e1) {
			log.error("Error al eliminar los docentes sin codigo");
			e1.printStackTrace();
			throw new TransactionException(e1);
		} catch (TransactionException e) {
			log.error("Error al eliminar los docentes sin codigo");
			e.printStackTrace();
			throw new TransactionException(e);
		}
		return n;
	}

	public DocenteTO find(String codDocente) throws TransactionException {

		Connection con = null;
		ResultSet rs = null;
		PreparedStatement st = null;
		DocenteTO to = null;

		try {
			con = transaction.getConnection();

			st = con
					.prepareStatement("select * from docentes where codigo = ?");
			st.setString(1, codDocente);

			rs = st.executeQuery();
			if (rs.next()) {
				to = new DocenteTO();
				
				to.setCodDoc(rs.getString("CODIGO"));
				to.setNombres(rs.getString("NOMBRES"));
				to.setApellidos(rs.getString("APELLIDOS"));
			}

		} catch (SQLException e) {
			log.error("Error al consultar el docente de codigo" + codDocente);
			e.printStackTrace();
			throw new TransactionException(
					"Error al consultar el docente de codigo" + codDocente);
		}
		return to;
	}

}
