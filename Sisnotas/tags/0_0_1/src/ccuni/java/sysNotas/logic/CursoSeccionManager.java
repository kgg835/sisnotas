package ccuni.java.sysNotas.logic;

import java.util.List;
import java.util.ArrayList;

import javax.sql.DataSource;

import org.apache.commons.dbcp.datasources.PerUserPoolDataSource;

import ccuni.java.sysNotas.dao.ClipperTransaction;
import ccuni.java.sysNotas.dao.CursoSeccionDAO;
import ccuni.java.sysNotas.dao.DAOFactory;
import ccuni.java.sysNotas.dao.DocenteDAO;
import ccuni.java.sysNotas.dao.MysqlTransaction;
import ccuni.java.sysNotas.dao.TransactionException;
import ccuni.java.sysNotas.dao.UsuarioDAO;
import ccuni.java.sysNotas.domain.dto.*;

public class CursoSeccionManager {
	private DataSource dataSource;

	public CursoSeccionManager() {

	}

	public CursoSeccionManager(DataSource dataSource) {
		super();
		// TODO Auto-generated constructor stub
		this.dataSource = dataSource;
	}

	public ArrayList<CursoTO> obtenerCursosSeccion(String perAcad)
			throws TransactionException {
		ClipperTransaction t = new ClipperTransaction();
		ArrayList<CursoTO> list = null;
		try {

			DAOFactory clipperFactory = DAOFactory
					.getDAOFactory(DAOFactory.CLIPPER);
			CursoSeccionDAO cursoSeccionDao = clipperFactory
					.getCursoSeccionDAO(t);
			list = cursoSeccionDao.obtenerCursosSeccion(perAcad);
		} catch (TransactionException te) {
			// log
			te.printStackTrace();
			throw new TransactionException("Error al buscar el docente");
		} finally {
			t.close();
		}
		return list;
	}

	public int insert(CursoTO to, String perAcad) throws TransactionException {
		MysqlTransaction t = new MysqlTransaction(dataSource);
		int n = 0;
		try {
			DAOFactory mysqlFactory = DAOFactory
					.getDAOFactory(DAOFactory.MYSQL);
			CursoSeccionDAO cursoSeccionDAO = mysqlFactory
					.getCursoSeccionDAO(t);

			n = cursoSeccionDAO.insert(to, perAcad);
			if (n != 0)
				t.commit();
		} catch (TransactionException e) {
			e.printStackTrace();
			t.rollback();
			throw new TransactionException("Error al registrar usuario");

		} finally {
			t.close();
		}
		return n;
	}

	public int registrarCursosSeccion(List list, String perAcad)
			throws TransactionException {
		MysqlTransaction t = new MysqlTransaction(dataSource);
		int n = 0;
		try {
			DAOFactory mysqlFactory = DAOFactory
					.getDAOFactory(DAOFactory.MYSQL);
			CursoSeccionDAO cursoSeccionDAO = mysqlFactory
					.getCursoSeccionDAO(t);

			n = cursoSeccionDAO.registrarCursosSeccion(list, perAcad);
			if (n == list.size())
				t.commit();
		} catch (TransactionException e) {
			e.printStackTrace();
			t.rollback();
			throw new TransactionException("Error al registrar usuario");

		} finally {
			t.close();
		}
		return n;
	}

	public ArrayList<CursoTO> obtenerCursosSeccion()
			throws TransactionException {
		MysqlTransaction t = new MysqlTransaction(dataSource);
		ArrayList<CursoTO> list = null;
		try {
			DAOFactory mysqlFactory = DAOFactory
					.getDAOFactory(DAOFactory.MYSQL);
			CursoSeccionDAO cursoSeccionDAO = mysqlFactory
					.getCursoSeccionDAO(t);

			list = cursoSeccionDAO.obtenerCursosSeccion();

		} catch (TransactionException e) {
			e.printStackTrace();
			throw new TransactionException("Error al registrar usuario");

		} finally {
			t.close();
		}
		return list;
	}
	
	public List obtenerCursosNoRegistrados(String perAcad) throws TransactionException {
		CursoTO to = null;
		List cursosClipper = null;
		List cursosMysql = null;
		List list = null;
		try {
			cursosClipper = obtenerCursosSeccion(perAcad);
			cursosMysql = obtenerCursosSeccion();

			for (int i = 0; i < cursosClipper.size(); i++) {
				to = (CursoTO) cursosClipper.get(i);
				if (!cursosMysql.contains(to)) {
					if (list == null) {
						list = new ArrayList();
						list.add(to);
					} else
						list.add(to);
				}
			}
		} catch (TransactionException e) {
			e.printStackTrace();
			throw new TransactionException("Error consultando cursos no registrados", e);
		}
		return list;
	}
	
	public int eliminarCursoSeccion(String codCurso, String seccion) throws TransactionException{
		MysqlTransaction t = new MysqlTransaction(dataSource);
		CursoTO to = null;
		int n = 0;
		try {
			DAOFactory mysqlFactory = DAOFactory
					.getDAOFactory(DAOFactory.MYSQL);
			CursoSeccionDAO cursoSeccionDAO = mysqlFactory
					.getCursoSeccionDAO(t);
			to = new CursoTO();
			to.setCursoId(codCurso);
			to.setSeccion(seccion);
			n = cursoSeccionDAO.delete(to);
			if (n == 1)
				t.commit();
		} catch (TransactionException e) {
			e.printStackTrace();
			t.rollback();
			throw new TransactionException("Error al registrar usuario");

		} finally {
			t.close();
		}
		return n;
		
	}
	
	public List listaCursosSinPromedio() throws TransactionException{
		MysqlTransaction t = new MysqlTransaction(dataSource);
		List list = null;
		try {
			DAOFactory mysqlFactory = DAOFactory
					.getDAOFactory(DAOFactory.MYSQL);
			CursoSeccionDAO cursoSeccionDAO = mysqlFactory
					.getCursoSeccionDAO(t);

			list = cursoSeccionDAO.listaCursosSinPromedio();

		} catch (TransactionException e) {
			e.printStackTrace();
			throw new TransactionException("Error al registrar usuario");

		} finally {
			t.close();
		}
		return list;
	}
	


}
