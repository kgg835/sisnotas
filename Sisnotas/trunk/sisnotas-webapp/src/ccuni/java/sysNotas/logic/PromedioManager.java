package ccuni.java.sysNotas.logic;

import java.util.ArrayList;

import javax.sql.DataSource;

import ccuni.java.sysNotas.dao.*;

public class PromedioManager {
	
	private DataSource dataSource;

	public PromedioManager(DataSource dataSource) {
		super();
		// TODO Auto-generated constructor stub
		this.dataSource = dataSource;
	}
	
	public ArrayList<String> calcularPromedio(String perAcad) throws TransactionException{
		MysqlTransaction t = new MysqlTransaction(dataSource);
		ArrayList<String> al = new ArrayList<String>();
		try {
			DAOFactory mysqlFactory = DAOFactory
					.getDAOFactory(DAOFactory.MYSQL);
			PromedioDAO promedioDAO = mysqlFactory.getPromedioDAO(t);
			al = promedioDAO.calcularPromedio(perAcad);
			t.commit();
		} catch (TransactionException e) {
			e.printStackTrace();
			throw new TransactionException("Error de base de datos");
		} finally {
			t.close();
		}
		return al;
		
	}

}
