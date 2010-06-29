package ccuni.java.sysNotas.logic;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Types;
import java.util.ArrayList;

import javax.sql.DataSource;

import ccuni.java.sysNotas.dao.*;
import ccuni.java.sysNotas.domain.dto.CursoTO;
import ccuni.java.sysNotas.domain.dto.DocenteTO;
import ccuni.java.sysNotas.domain.dto.PruebaTO;
import ccuni.java.sysNotas.domain.dto.TipoPruebaTO;
import ccuni.java.sysNotas.domain.dto.UsuarioTO;

public class CursoManager {
	private DataSource dataSource;

	public CursoManager(DataSource dataSource) {
		super();
		// TODO Auto-generated constructor stub
		this.dataSource = dataSource;
	}

	public boolean existenParametrosCalificacion(String codigoCurso,
			String seccion) throws TransactionException {
		MysqlTransaction t = new MysqlTransaction(dataSource);
		boolean val = false;
		try {
			DAOFactory mysqlFactory = DAOFactory
					.getDAOFactory(DAOFactory.MYSQL);
			CursoDAO cursoDAO = mysqlFactory.getCursoDAO(t);
			val = cursoDAO.existenParametrosCalificacion(codigoCurso, seccion);
		} catch (TransactionException e) {
			e.printStackTrace();
			throw new TransactionException("Error de base de datos");
		} finally {
			t.close();
		}
		return val;
	}

	public CursoTO obtenerParametros(String codigoCurso, String seccion)
			throws TransactionException {
		MysqlTransaction t = new MysqlTransaction(dataSource);
		CursoTO to = null;
		try {
			DAOFactory mysqlFactory = DAOFactory
					.getDAOFactory(DAOFactory.MYSQL);
			CursoDAO cursoDAO = mysqlFactory.getCursoDAO(t);
			to = cursoDAO.obtenerParametros(codigoCurso, seccion);
		} catch (TransactionException e) {
			e.printStackTrace();
			throw new TransactionException("Error de base de datos");
		} finally {
			t.close();
		}
		return to;
	}

	public ArrayList<PruebaTO> obtenerParametros(String codigoCurso,
			String seccion, String codTipoPrueba) throws TransactionException {
		MysqlTransaction t = new MysqlTransaction(dataSource);
		ArrayList<PruebaTO> list = null;
		try {
			DAOFactory mysqlFactory = DAOFactory
					.getDAOFactory(DAOFactory.MYSQL);
			CursoDAO cursoDAO = mysqlFactory.getCursoDAO(t);
			list = cursoDAO.obtenerParametros(codigoCurso, seccion,
					codTipoPrueba);
		} catch (TransactionException e) {
			e.printStackTrace();
			throw new TransactionException("Error de base de datos");
		} finally {
			t.close();
		}
		return list;
	}

	public Integer updateParametros(String codigoCurso, String seccion,
			ArrayList<PruebaTO> pruebas) throws TransactionException {
		MysqlTransaction t = new MysqlTransaction(dataSource);
		Integer num = null;
		try {
			DAOFactory mysqlFactory = DAOFactory
					.getDAOFactory(DAOFactory.MYSQL);
			CursoDAO cursoDAO = mysqlFactory.getCursoDAO(t);
			num = cursoDAO.updatePruebas(codigoCurso, seccion, pruebas);
			if (num != null)
				t.commit();
		} catch (TransactionException e) {
			e.printStackTrace();
			throw new TransactionException("Error de base de datos");
		} finally {
			t.close();
		}
		return num;
	}

	public ArrayList<TipoPruebaTO> obtenerTiposDePrueba()
			throws TransactionException {
		MysqlTransaction t = new MysqlTransaction(dataSource);
		ArrayList<TipoPruebaTO> tiposDePrueba = new ArrayList<TipoPruebaTO>();
		try {
			DAOFactory mysqlFactory = DAOFactory
					.getDAOFactory(DAOFactory.MYSQL);
			CursoDAO cursoDAO = mysqlFactory.getCursoDAO(t);
			tiposDePrueba = cursoDAO.obtenerTiposDePrueba();
		} catch (TransactionException e) {
			e.printStackTrace();
			throw new TransactionException("Error de base de datos");
		} finally {
			t.close();
		}
		return tiposDePrueba;
	}

	public Integer addTipoPrueba(String codCur, String secc, TipoPruebaTO to,
			String codUser, String perAcad) throws TransactionException {
		MysqlTransaction t = new MysqlTransaction(dataSource);
		Integer num = null;
		try {
			DAOFactory mysqlFactory = DAOFactory
					.getDAOFactory(DAOFactory.MYSQL);
			CursoDAO cursoDAO = mysqlFactory.getCursoDAO(t);
			num = cursoDAO.addTipoPrueba(codCur, secc, to, codUser, perAcad);
			if (num != null)
				t.commit();
		} catch (TransactionException e) {
			e.printStackTrace();
			throw new TransactionException("Error de base de datos");
		} finally {
			t.close();
		}
		return num;
	}

	public Integer updateTipoPrueba(String codCur, String secc,
			TipoPruebaTO to, String codUser, String perAcad)
			throws TransactionException {
		MysqlTransaction t = new MysqlTransaction(dataSource);
		Integer num = null;
		try {
			DAOFactory mysqlFactory = DAOFactory
					.getDAOFactory(DAOFactory.MYSQL);
			CursoDAO cursoDAO = mysqlFactory.getCursoDAO(t);
			num = cursoDAO.updateTipoPrueba(codCur, secc, to, codUser, perAcad);
			if (num != null)
				t.commit();
		} catch (TransactionException e) {
			e.printStackTrace();
			throw new TransactionException("Error de base de datos");
		} finally {
			t.close();
		}
		return num;
	}

	public Integer eliminarTipoPrueba(String codCur, String secc,
			String codTipPba, String codUser) throws TransactionException {
		MysqlTransaction t = new MysqlTransaction(dataSource);
		Integer num = null;
		try {
			DAOFactory mysqlFactory = DAOFactory
					.getDAOFactory(DAOFactory.MYSQL);
			CursoDAO cursoDAO = mysqlFactory.getCursoDAO(t);
			num = cursoDAO.eliminarTipoPrueba(codCur, secc, codTipPba, codUser);
			if (num != null)
				t.commit();
		} catch (TransactionException e) {
			e.printStackTrace();
			throw new TransactionException("Error de base de datos");
		} finally {
			t.close();
		}
		return num;
	}

	public TipoPruebaTO find(String codCur, String secc, TipoPruebaTO tipoprueba)
			throws TransactionException {
		MysqlTransaction t = new MysqlTransaction(dataSource);
		TipoPruebaTO to = null;
		try {
			DAOFactory mysqlFactory = DAOFactory
					.getDAOFactory(DAOFactory.MYSQL);
			CursoDAO cursoDAO = mysqlFactory.getCursoDAO(t);
			to = cursoDAO.find(codCur, secc, tipoprueba);
		} catch (TransactionException e) {
			e.printStackTrace();
			throw new TransactionException("Error de base de datos");
		} finally {
			t.close();
		}
		return to;
	}

	public int updateDocente(String codDoc, String codCur, String seccion)
			throws TransactionException {
		MysqlTransaction t = new MysqlTransaction(dataSource);
		int n = 0;
		try {
			DAOFactory mysqlFactory = DAOFactory
					.getDAOFactory(DAOFactory.MYSQL);
			CursoDAO cursoDAO = mysqlFactory.getCursoDAO(t);
			n = cursoDAO.updateDocente(codDoc, codCur, seccion);
			if (n != 0)
				t.commit();
		} catch (TransactionException e) {
			e.printStackTrace();
			throw new TransactionException("Error de base de datos");
		} finally {
			t.close();
		}
		return n;
	}

	/*
	 * public int insertDocente(String codDoc, String codCur, String
	 * seccion)throws TransactionException { MysqlTransaction t = new
	 * MysqlTransaction(dataSource); int n = 0; try { DAOFactory mysqlFactory =
	 * DAOFactory .getDAOFactory(DAOFactory.MYSQL); CursoDAO cursoDAO =
	 * mysqlFactory.getCursoDAO(t); n = cursoDAO.insertDocente(codDoc,
	 * codCur,seccion); if(n!=0) t.commit(); } catch (TransactionException e) {
	 * e.printStackTrace(); throw new TransactionException("Error de base de
	 * datos"); } finally { t.close(); } return n; <<<<<<<
	 * CursoManager.java }
	 */

	public ArrayList<CursoTO> obtieneCursos(UsuarioTO usuario, String perAcad)
			throws TransactionException {
		MysqlTransaction t = new MysqlTransaction(dataSource);
		ArrayList<CursoTO> cursos = null;
		try {
			DAOFactory mysqlFactory = DAOFactory
					.getDAOFactory(DAOFactory.MYSQL);
			CursoDAO cursoDAO = mysqlFactory.getCursoDAO(t);
			cursos = cursoDAO.selectCursos(usuario, perAcad);
		} catch (TransactionException e) {
			e.printStackTrace();
			throw new TransactionException("Error al obtener cursos x docentes");
		} finally {
			t.close();
		}
		return cursos;
	}
	
	public int actualizarEstado(String cursoId,String seccion, int estado) throws TransactionException{
		MysqlTransaction t = new MysqlTransaction(dataSource);
		int n = 0;
		try {
			DAOFactory mysqlFactory = DAOFactory
					.getDAOFactory(DAOFactory.MYSQL);
			CursoDAO cursoDAO = mysqlFactory.getCursoDAO(t);
			n = cursoDAO.actualizarEstado(cursoId, seccion, estado);
			if (n != 0)
				t.commit();
		} catch (TransactionException e) {
			e.printStackTrace();
			throw new TransactionException("Error de base de datos");
		} finally {
			t.close();
		}
		return n;
	}
	
	public boolean existeDocente(String codigoCurso,
			String seccion) throws TransactionException {
		MysqlTransaction t = new MysqlTransaction(dataSource);
		boolean val = false;
		try {
			DAOFactory mysqlFactory = DAOFactory
					.getDAOFactory(DAOFactory.MYSQL);
			CursoDAO cursoDAO = mysqlFactory.getCursoDAO(t);
			val = cursoDAO.existeDocente(codigoCurso, seccion);
		} catch (TransactionException e) {
			e.printStackTrace();
			throw new TransactionException("Error de base de datos");
		} finally {
			t.close();
		}
		return val;
	}
	/***JMARTINEZ 29/08/2007  */
    /**VERIFICA TODAS LAS PRUEBAS DE DICHO CURSO
     * SI NO HAY PENDIENTES ACTUALIZA ESTADO DEL CURSO
    **/    
   	 
    public void verificaPruebasCurso(String cursoId,String seccion) throws TransactionException{
    	int indicador=-1;
    	MysqlTransaction t = new MysqlTransaction(dataSource); 
		Connection con=null;
		try {
			con = t.getConnection();
			CallableStatement proc = con.prepareCall("{call VERIFICA_ESTADO_PRUEBAS(?,?,?)}");			
			proc.setString(1,cursoId);
			proc.setString(2,seccion);
			proc.registerOutParameter(3,Types.INTEGER);
			proc.execute();
			indicador=proc.getInt(3);//devuelve 1-ok
			if(indicador==1){
				System.out.println( "Se actualizó el estado del curso seccion y se calculo promedio de las practicas del curso.");
            }else{
           	 	System.out.println(" NO se pudo calcular promedio");	 
            }
			t.commit();
			
		}catch (Exception e) {
			e.printStackTrace();
			t.rollback();
	        throw new TransactionException("Error al verificaPruebasCurso y el calculo de promedio", e);
		} finally {
			t.close();
		}
    	
    	
    	
    }
    public void actualizaEstadoCurso(String cursoId,String seccion)throws TransactionException {
    	MysqlTransaction t = new MysqlTransaction(dataSource); 
		Connection con=null;
		try {
			con = t.getConnection();
			CallableStatement proc = con.prepareCall("{call ACTUALIZA_ESTADO_CURSO(?,?)}");			
			proc.setString(1,cursoId);
			proc.setString(2,seccion);			
			proc.execute();	
			t.commit();
			
		}catch (Exception e) {
			e.printStackTrace();
	        throw new TransactionException("Error al actualizar el estado del curso-seccion", e);
		} finally {
			t.close();
		}
    }
    
    /**ACA ENTRA TU PARTE. YO LLAMO A TU PROCEDURE CALCULA PROMEDIO CURSO SECCION*/
    public void calculaPromedioCurso(String cursoId,String seccion)throws TransactionException {
    	MysqlTransaction t = new MysqlTransaction(dataSource); 
		Connection con=null;
		try {
			con = t.getConnection();
			CallableStatement proc = con.prepareCall("{call CALCULA_PROMEDIO_CURSO_SECCION(?,?)}");			
			proc.setString(1,cursoId);
			proc.setString(2,seccion);			
			proc.execute();	
			t.commit();
			
		}catch (Exception e) {
			e.printStackTrace();
	        throw new TransactionException("Error al CALCULAR PROMEDIO del curso-seccion", e);
		} finally {
			t.close();
		}
    	
    }
	
}
