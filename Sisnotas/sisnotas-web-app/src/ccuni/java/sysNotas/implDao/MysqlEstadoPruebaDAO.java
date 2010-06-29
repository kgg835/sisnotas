package ccuni.java.sysNotas.implDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import ccuni.java.sysNotas.dao.EstadoPruebaDAO;
import ccuni.java.sysNotas.dao.Transaction;
import ccuni.java.sysNotas.dao.TransactionException;
import ccuni.java.sysNotas.domain.dto.EstadoPruebaTO;
import ccuni.java.sysNotas.domain.dto.PruebaTO;

public class MysqlEstadoPruebaDAO implements EstadoPruebaDAO{

	private Transaction transaction=null;
    public MysqlEstadoPruebaDAO(Transaction t) {
    this.transaction=t;
    }
   
    public  Transaction getTransaction() {
       return transaction;
    }
	public int insert(PruebaTO t) throws TransactionException {
		// TODO Auto-generated method stub
		return 0;
	}

	public boolean delete(String t) {
		// TODO Auto-generated method stub
		return false;
	}

	public EstadoPruebaTO find(EstadoPruebaTO p) throws TransactionException {
		// TODO Auto-generated method stub
		return null;
	}

	public int update(EstadoPruebaTO p) throws TransactionException {
		// TODO Auto-generated method stub
		return 0;
	}

	public ArrayList<String> selectAll() throws TransactionException {
		Connection c;
        PreparedStatement statement = null;
        ResultSet rs=null;
        ArrayList<String> p=new ArrayList<String>();
     
        
        try {
                c = transaction.getConnection();

                statement =
                        c.prepareStatement(
                                " select descripcion from ESTADO_PRUEBA ");
               
                

                rs= statement.executeQuery();               
                      
                while(rs.next()){               	
                    p.add(rs.getString("descripcion"));
                
                }
                              
      
                statement.close();
                rs.close();
               

        } catch (SQLException e1) {

                throw new TransactionException(e1);

        }
		return p;
	}

	

}
