package ccuni.java.sysNotas.logic;

import java.util.List;

import javax.sql.DataSource;

import ccuni.java.sysNotas.dao.DAOFactory;
import ccuni.java.sysNotas.dao.EvaluacionDAO;
import ccuni.java.sysNotas.dao.MysqlTransaction;
import ccuni.java.sysNotas.dao.SincronizaDAO;
import ccuni.java.sysNotas.dao.TransactionException;

public class SincronizaManager {
	private DataSource dataSource;

	public SincronizaManager(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	public List sincronizarMysqltoClipper(String perAcad)throws Exception{
   	 MysqlTransaction t = new MysqlTransaction(dataSource);
     List listaFaltantes=null;
     try {
             DAOFactory mysqlFactory = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
             SincronizaDAO sincronizaDAO = mysqlFactory.getSincronizaDAO(t);             
             listaFaltantes=sincronizaDAO.sincronizarMysqltoClipper(perAcad);             
     }catch (Exception te) {
    	 System.out.println("error al instanciar sincronizaDAO ");    	
			te.printStackTrace();			
		} finally {
			t.close();
		}
		
		return listaFaltantes;
	}

	

}
