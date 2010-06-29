package ccuni.java.sysNotas.implDao;

import ccuni.java.sysNotas.dao.Transaction;
import ccuni.java.sysNotas.dao.TransactionException;
import ccuni.java.sysNotas.dao.UsuarioDAO;
import ccuni.java.sysNotas.domain.dto.*;
import ccuni.java.sysNotas.constantes.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

public class MysqlUsuarioDAO implements UsuarioDAO {
	private Transaction transaction = null;

	Log log = LogFactory.getLog(MysqlUsuarioDAO.class);

	public Transaction getTransaction() {
		return transaction;
	}

	public MysqlUsuarioDAO() {
		super();
	}

	public MysqlUsuarioDAO(Transaction t) {
		this.transaction = t;
	}

	/*----------------------------METODOS DE LUIS--------------------------*/

	public int insert(UsuarioTO usuario, String codAdmin)
			throws TransactionException {

		Connection c = null;
		PreparedStatement statement = null;
		int i = 1;

		try {
			c = transaction.getConnection();

			statement = c
					.prepareStatement("insert into usuario (codusr,codgrpusr, nombres,apellidos,username,password,usrins, dteins, usrupd, dteupd)  values (?, ?, ?,?, ?, ?, ?,  NOW(), ?, NOW())");
			statement.setString(i++, usuario.getCodUsr());
			statement.setString(i++, usuario.getCodGrpUsr());
			statement.setString(i++, usuario.getNombres());
			statement.setString(i++, usuario.getApellidos());
			statement.setString(i++, usuario.getUserName());
			statement.setString(i++, usuario.getPassword());
			statement.setString(i++, codAdmin);
			statement.setString(i++, codAdmin);

			int n = statement.executeUpdate();
			if (n == 0)
				return -1;
			// throw new TransactionException("No se pudo crear el usuario");

		} catch (SQLException e1) {
			log.error("Error al insertar usuario " + usuario.getCodUsr());
			e1.printStackTrace();
			throw new TransactionException("Error al insertar usuario");
		} finally {
			try {
				if (statement != null)
					statement.close();
			} catch (SQLException e) {
			}
			;
		}
		return 1;
	}

	public ArrayList selectUsuariosHabilitados() throws TransactionException {
		Connection con = null;
		Statement stm = null;
		ResultSet result = null;
		ArrayList list = null;
		UsuarioTO to = null;
		try {
			con = transaction.getConnection();
			stm = con.createStatement();
			result = stm.executeQuery("SELECT * FROM USUARIO WHERE LOGDEL=1");

			list = new ArrayList();
			while (result.next()) {
				to = new UsuarioTO();
				to.setCodUsr(result.getString("CODUSR"));
				to.setCodGrpUsr(result.getString("CODGRPUSR"));
				to.setNombres(result.getString("NOMBRES"));
				to.setApellidos(result.getString("APELLIDOS"));
				to.setCodDoc(result.getString("CODDOC"));
				to.setUserName(result.getString("USERNAME"));
				to.setPassword(result.getString("PASSWORD"));
				list.add(to);
			}

		} catch (SQLException e1) {
			e1.printStackTrace();
			log.error("Error al consultar usuarios habilitados");
			throw new TransactionException(e1);
		}
		return list;
	}

	/*----------------------------FIN METODOS DE LUIS--------------------------*/

	public int deleteAll(String codGrpUsr) throws TransactionException {
		// Implement delete customer here
		// Return true on success, false on failure
		Connection con = null;
		PreparedStatement st = null;
		int n = 0;
		try {
			con = transaction.getConnection();
			st = con
					.prepareStatement("delete from usuario where codgrpusr = ?");
			st.setString(1, codGrpUsr);
			n = st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			log.error("Error eliminando usuarios");
			throw new TransactionException("Error al buscar el usuario");
		}
		return n;
	}

	public UsuarioTO find(String codUsuario) throws TransactionException {
		Connection con = null;
		ResultSet rs = null;
		PreparedStatement st = null;
		UsuarioTO user = null;

		try {
			con = transaction.getConnection();

			st = con.prepareStatement("select * from usuario where codusr = ?");
			st.setString(1, codUsuario);

			rs = st.executeQuery();
			if (rs.next()) {
				user = new UsuarioTO();
				user.setCodUsr(rs.getString("CODUSR"));
				user.setUserName(rs.getString("USERNAME"));
				user.setCodGrpUsr(rs.getString("CODGRPUSR"));
				user.setCodDoc(rs.getString("CODDOC"));
				user.setNombres(rs.getString("NOMBRES"));
				user.setApellidos(rs.getString("APELLIDOS"));
				user.setPassword(rs.getString("PASSWORD"));
				user.setLogDel(rs.getInt("LOGDEL"));
				st = con
						.prepareStatement("select nombre from grupo_usuarios where CODGRPUSR= ?");
				st.setString(1, user.getCodGrpUsr());
				rs = st.executeQuery();
				if (rs.next()) {
					user.setNombreGrupoUsuario(rs.getString("nombre"));
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
			log.error("Error al buscar el usuario "+codUsuario);
			throw new TransactionException("Error al buscar el usuario");
		}

		return user;
	}

	public boolean update(UsuarioTO usuario) {
		return true;
	}

	public ArrayList<UsuarioTO> selectAllUsuarios(String codGrpUsr)
			throws TransactionException {

		Connection c = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		UsuarioTO to = null;
		ArrayList<UsuarioTO> list = null;
		try {

			c = transaction.getConnection();

			if (codGrpUsr.equals(Constantes.ADMINISTRADOR_SISTEMA)) {
				// query para el master

				st = c
						.prepareStatement("select CODUSR, t1.CODGRPUSR, NOMBRES, APELLIDOS,"
								+ "CODDOC,USERNAME, PASSWORD, LOGDEL, "
								+ "t2.NOMBRE from usuario as t1 join "
								+ "grupo_usuarios AS t2 on (t1.codgrpusr =t2.codgrpusr) "
								+ "where t1.codgrpusr=? or t1.codgrpusr=? order by logdel,codgrpusr,apellidos");
				st.setString(1, Constantes.ADMINISTRADORES);
				st.setString(2, Constantes.DOCENTES);
			} else {
				// query para el admin
				st = c
						.prepareStatement("select CODUSR, t1.CODGRPUSR, NOMBRES, APELLIDOS,"
								+ "CODDOC,USERNAME, PASSWORD, LOGDEL, "
								+ "t2.NOMBRE from usuario as t1 join "
								+ "grupo_usuarios AS t2 on (t1.codgrpusr =t2.codgrpusr) "
								+ "where t1.codgrpusr=? order by logdel, apellidos, nombres");
				st.setString(1, Constantes.DOCENTES);
			}
			rs = st.executeQuery();
			list = new ArrayList<UsuarioTO>();
			while (rs.next()) {
				to = new UsuarioTO();
				to.setCodUsr(rs.getString("CODUSR"));
				to.setCodGrpUsr(rs.getString("CODGRPUSR"));
				to.setNombres(rs.getString("NOMBRES"));
				to.setApellidos(rs.getString("APELLIDOS"));
				to.setCodDoc(rs.getString("CODDOC"));
				to.setUserName(rs.getString("USERNAME"));
				to.setPassword(rs.getString("PASSWORD"));
				to.setLogDel(rs.getInt("LOGDEL"));
				to.setNombreGrupoUsuario(rs.getString("NOMBRE"));
				list.add(to);
			}
		} catch (SQLException e1) {
			log.error("Error al seleccionar todos los usuarios");
			e1.printStackTrace();
			throw new TransactionException(e1);
		}
		return list;
	}

	public UsuarioTO validar(String usuario, String password)
			throws TransactionException {
		UsuarioTO user = null;
		Connection c = null;
		PreparedStatement statement = null;
		ResultSet rs = null;

		try {
			c = transaction.getConnection();

			statement = c
					.prepareStatement("select username,password,codgrpusr,codusr,nombres,apellidos from usuario where username = ? and password = ? and logdel = 1");
			statement.setString(1, usuario);
			statement.setString(2, password);

			rs = statement.executeQuery();
			if (rs.next()) {

				user = new UsuarioTO();
				user.setUserName(rs.getString(1));
				user.setPassword(rs.getString(2));
				user.setCodGrpUsr(rs.getString(3));
				user.setCodUsr(rs.getString(4));
				user.setNombres(rs.getString(5));
				user.setApellidos(rs.getString(6));
			}

		} catch (SQLException e1) {

			throw new TransactionException(e1);
		} finally {
			try {
				if (rs != null)
					rs.close();
			} catch (SQLException e) {
				System.out.println("may");
			}
			;
			try {
				if (statement != null)
					statement.close();
			} catch (SQLException e) {
				System.out.println("may2");
			}
			;

		}

		return user;

	}

	public int modificarEstado(int estado, String codUsr)
			throws TransactionException {
		Connection c = null;
		PreparedStatement st = null;
		int n;
		try {
			c = transaction.getConnection();

			st = c
					.prepareStatement("update usuario set logdel=? where codusr=?");
			st.setInt(1, estado);
			st.setString(2, codUsr);
			n = st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			log.error("Error al cambiando estado  al usuario");
			throw new TransactionException("Error al cambiando estado  al usuario");
		}
		return n;
	}

	public void cambiarPasswordUsuario(String userName, String password)
			throws Exception {
		Connection c = null;
		PreparedStatement statement = null;

		try {
			c = transaction.getConnection();

			statement = c
					.prepareStatement("update usuario set password = ? where username = ?");
			statement.setString(1, password);
			statement.setString(2, userName);
			statement.executeUpdate();
			System.out.println("died");

		} catch (SQLException e1) {
			e1.printStackTrace();
			throw new TransactionException(e1);
		} finally {

			try {
				if (statement != null)
					statement.close();
			} catch (SQLException e) {

			}

		}

	}

	public ArrayList<UsuarioTO> selectAllDocentes() throws TransactionException {
		Connection c = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		UsuarioTO to = null;
		ArrayList<UsuarioTO> list = null;
		try {
			c = transaction.getConnection();
			// listo todos los docentes
			st = c
					.prepareStatement("select CODUSR, t1.CODGRPUSR, NOMBRES, APELLIDOS,CODDOC,USERNAME, PASSWORD, LOGDEL, "
							+ "t2.NOMBRE from usuario as t1 join grupo_usuarios AS t2 on (t1.codgrpusr =t2.codgrpusr) where t1.codgrpusr=?");
			st.setString(1, Constantes.DOCENTES);
			rs = st.executeQuery();
			list = new ArrayList<UsuarioTO>();

			while (rs.next()) {
				to = new UsuarioTO();
				to.setCodUsr(rs.getString("CODUSR"));
				to.setCodGrpUsr(rs.getString("CODGRPUSR"));
				to.setNombres(rs.getString("NOMBRES"));
				to.setApellidos(rs.getString("APELLIDOS"));
				to.setCodDoc(rs.getString("CODDOC"));
				to.setUserName(rs.getString("USERNAME"));
				to.setPassword(rs.getString("PASSWORD"));
				to.setLogDel(rs.getInt("LOGDEL"));
				to.setNombreGrupoUsuario(rs.getString("NOMBRE"));
				list.add(to);
			}
		} catch (SQLException e1) {
			log.error("Error al seleccionar todos los docentes");
			e1.printStackTrace();
			throw new TransactionException(
					"Error al seleccionar todos los docentes", e1);
		}
		return list;
	}

}
