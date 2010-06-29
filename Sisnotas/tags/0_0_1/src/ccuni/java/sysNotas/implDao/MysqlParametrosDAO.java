package ccuni.java.sysNotas.implDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import ccuni.java.sysNotas.constantes.Constantes;
import ccuni.java.sysNotas.dao.ParametrosDAO;
import ccuni.java.sysNotas.dao.Transaction;
import ccuni.java.sysNotas.dao.TransactionException;
import ccuni.java.sysNotas.domain.dto.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class MysqlParametrosDAO implements ParametrosDAO {
	private Transaction transaction = null;
	Log log = LogFactory.getLog(MysqlParametrosDAO.class);
	public Transaction getTransaction() {
		return transaction;
	}

	public void setTransaction(Transaction transaction) {
		this.transaction = transaction;
	}

	public MysqlParametrosDAO(Transaction transaction) {
		super();
		// TODO Auto-generated constructor stub
		this.transaction = transaction;
	}

	public ParametrosTO getParametros() throws TransactionException {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		ParametrosTO to = null;
		try {
			con = transaction.getConnection();
			st = con.createStatement();
			rs = st.executeQuery("SELECT * FROM PARAMETROS_SISTEMA");
			if (rs.next()) {
				to = new ParametrosTO();
				to.setMigracion_docentes(rs.getInt(1));
				to.setPeracad(rs.getString(2));
				to.setMigracion_cursos(rs.getInt(3));
				to.setParametros_cursos(rs.getInt("PARAMETROS_CURSOS"));
				to.setInicio_periodo(rs.getInt("INICIO_PERIODO"));
				to.setTipos_prueba_definidos(rs
						.getInt("TIPOS_PRUEBA_DEFINIDOS"));
				to.setNombre_periodo(rs.getString("NOMBRE_PERIODO"));
			}

		} catch (SQLException e1) {
			log.error("Error al obtener los parametros del sistema");
			throw new TransactionException(e1);
		}
		return to;
	}

	public int updateParametros(ParametrosTO to) throws TransactionException {
		Connection con = null;
		PreparedStatement st = null;
		int n = 0;
		try {
			con = transaction.getConnection();
			st = con
					.prepareStatement("UPDATE PARAMETROS_SISTEMA SET MIGRACION_DOCENTES=?, PERACAD=?, MIGRACION_CURSOS=?,PARAMETROS_CURSOS=?,INICIO_PERIODO=?,TIPOS_PRUEBA_DEFINIDOS=?,NOMBRE_PERIODO=? ");
			st.setInt(1, to.getMigracion_docentes());
			st.setString(2, to.getPeracad());
			st.setInt(3, to.getMigracion_cursos());
			st.setInt(4, to.getParametros_cursos());
			st.setInt(5, to.getInicio_periodo());
			st.setInt(6, to.getTipos_prueba_definidos());
			st.setString(7,to.getNombre_periodo());
			n = st.executeUpdate();
		} catch (SQLException e1) {
			e1.printStackTrace();
			log.error("Error al actualizar los parametros del sistema");
			throw new TransactionException(e1);
		}
		return n;
	}

}
