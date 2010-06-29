package ccuni.java.sysNotas.implDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import ccuni.java.sysNotas.dao.PeriodoDAO;
import ccuni.java.sysNotas.dao.Transaction;
import ccuni.java.sysNotas.dao.TransactionException;
import ccuni.java.sysNotas.domain.dto.EvaluacionTO;

public class MysqlPeriodoDAO implements PeriodoDAO{
private Transaction transaction = null;
	
	public Transaction getTransaction() {
		return transaction;
	}

	public void setTransaction(Transaction transaction) {
		this.transaction = transaction;
	}
	
	
	public MysqlPeriodoDAO(Transaction transaction) {
		super();
		// TODO Auto-generated constructor stub
		this.transaction = transaction;
	}
	
	public void iniciarPeriodo(String perAcad,String nombrePerAcad) throws TransactionException{
        Connection con = null;
		
		PreparedStatement st2 = null;
		
		try {
			con = transaction.getConnection();
			st2 = con.prepareStatement("update parametros_sistema set peracad = ?,nombre_periodo= ?, inicio_periodo =?");
			st2.setString(1,perAcad);
			st2.setString(2,nombrePerAcad);
			st2.setInt(3,1);
			st2.executeUpdate();
			
		}catch (SQLException e1) {
			throw new TransactionException(e1);
		}
	}
	
	public boolean PeriodoExiste(String perAcad) throws TransactionException{
		Connection con = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		boolean v = false;
		try {
			con = transaction.getConnection();
			st = con.prepareStatement("select peracad from periodo_academico where peracad=?");
			st.setString(1,perAcad);
			rs = st.executeQuery();
			if(rs.next()){
				v = true;
			}
			
		}catch (SQLException e1) {
			throw new TransactionException(e1);
		}
		
		return v;
	}
	public int insert(String perAcad ,String detalle,String username,String nombre)throws TransactionException {
    	Connection c = null;
		PreparedStatement statement = null;
		int i = 1;

		try {
			c = transaction.getConnection();
		
			statement = c
					.prepareStatement("insert into periodo_academico (PERACAD,DETALLE,USRINS,DTEINS,NOMBREPERACAD)  values (?, ?, ?,CURRENT_TIMESTAMP(),?)");
			statement.setString(i++,perAcad );
			statement.setString(i++,detalle );
			statement.setString(i++,username );
			statement.setString(i++,nombre );
		
			

			int n = statement.executeUpdate();
			if (n == 0)
				return -1;
			// throw new TransactionException("No se pudo crear el usuario");

		} catch (SQLException e1) {			
			e1.printStackTrace();
			throw new TransactionException(e1.getMessage());
		} finally {
			try {
				if (statement != null)
					statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}

		return 1;
	}
}
