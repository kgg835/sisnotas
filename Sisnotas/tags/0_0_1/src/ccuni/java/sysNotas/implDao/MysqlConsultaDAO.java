package ccuni.java.sysNotas.implDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import ccuni.java.sysNotas.dao.ConsultaDAO;
import ccuni.java.sysNotas.dao.PromedioDAO;
import ccuni.java.sysNotas.dao.Transaction;
import ccuni.java.sysNotas.dao.TransactionException;

public class MysqlConsultaDAO implements ConsultaDAO{
private Transaction transaction = null;
	
	public Transaction getTransaction() {
		return transaction;
	}

	public void setTransaction(Transaction transaction) {
		this.transaction = transaction;
	}
	
	
	public MysqlConsultaDAO(Transaction transaction) {
		super();
		// TODO Auto-generated constructor stub
		this.transaction = transaction;
	}
	
	public ArrayList<String> getAllTipoPrueba(String codCur,String seccion,String perAcad) throws TransactionException{
		Connection con = null;
		PreparedStatement stTp = null;
		ResultSet rsTp = null;
		ArrayList<String> alTp = new ArrayList<String>();
		try {con = transaction.getConnection();
		stTp = con.prepareStatement("SELECT concat(codtippba,numtippba) as tipoprueba from evaluacion where codcur = ? and seccion=? and peracad = ? group by tipoprueba order by codtippba desc,numtippba");
		stTp.setString(1,codCur);
		stTp.setString(2,seccion);
		stTp.setString(3,perAcad);
		rsTp = stTp.executeQuery();
		while(rsTp.next()){
			String x = null;
			String y = null;
			if(rsTp.getString("tipoprueba").equals("EF1")||rsTp.getString("tipoprueba").equals("EP1")||rsTp.getString("tipoprueba").equals("ES1")){
				y = rsTp.getString("tipoprueba");
				x = y.substring(0,2);
			}
			else{ x = rsTp.getString("tipoprueba");
			}
			
			alTp.add(x);
			
		}
		
		} catch (SQLException e1) {
			throw new TransactionException(e1);
		}
		
		return alTp;
	}
	
	public ArrayList<String> getAllTipoPruebaHist(String codCur,String seccion,String perAcad) throws TransactionException{
		Connection con = null;
		PreparedStatement stTpH = null;
		ResultSet rsTpH = null;
		ArrayList<String> alTpH = new ArrayList<String>();
		try {con = transaction.getConnection();
		stTpH = con.prepareStatement("SELECT concat(codtippba,numtippba) as tipoprueba from hist_evaluacion where codcur = ? and seccion=? and peracad = ? group by tipoprueba order by codtippba desc,numtippba");
		stTpH.setString(1,codCur);
		stTpH.setString(2,seccion);
		stTpH.setString(3,perAcad);
		rsTpH = stTpH.executeQuery();
		while(rsTpH.next()){
			String x = null;
			String y = null;
		
			if(rsTpH.getString("tipoprueba").equals("EF1")||rsTpH.getString("tipoprueba").equals("EP1")||rsTpH.getString("tipoprueba").equals("ES1")){
				y = rsTpH.getString("tipoprueba");
				x = y.substring(0,2);
				}
			else{
				x = rsTpH.getString("tipoprueba");
			}
			alTpH.add(x);
			
		}
		
		} catch (SQLException e1) {
			throw new TransactionException(e1);
		}
		
		return alTpH;
	}
	public ArrayList<String> getAllAlumno(String codCur,String seccion,String perAcad) throws TransactionException{
		Connection con = null;
		PreparedStatement stAl = null;
		ResultSet rsAl = null;
		ArrayList<String> alAl = new ArrayList<String>();
		try{
			con = transaction.getConnection();
			stAl = con.prepareStatement("select nombre from evaluacion where peracad=? and codcur = ? and seccion = ? group by nombre");
			stAl.setString(1,perAcad);
			stAl.setString(2,codCur);
			stAl.setString(3,seccion);
			rsAl = stAl.executeQuery();
			while(rsAl.next()){
				alAl.add(rsAl.getString("nombre"));
			}
			
}
		 catch(SQLException e1) {
			 
				throw new TransactionException(e1);
			}
		
		return alAl;
	}
	
	public ArrayList<String> getAllAlumnoHist(String codCur,String seccion,String perAcad) throws TransactionException{
		Connection con = null;
		PreparedStatement stAlH = null;
		ResultSet rsAlH = null;
		ArrayList<String> alAlH = new ArrayList<String>();
		try{
			con = transaction.getConnection();
			stAlH = con.prepareStatement("select nombre from hist_evaluacion where peracad=? and codcur = ? and seccion = ? group by nombre");
			stAlH.setString(1,perAcad);
			stAlH.setString(2,codCur);
			stAlH.setString(3,seccion);
			rsAlH = stAlH.executeQuery();
			while(rsAlH.next()){
				alAlH.add(rsAlH.getString("nombre"));
			}
			
}
		 catch(SQLException e1) {
			 
				throw new TransactionException(e1);
			}
		
		return alAlH;
	}
	
	public ArrayList<String> getAllPrueba(String codCur,String seccion,String perAcad) throws TransactionException{
		Connection con = null;
		PreparedStatement stP = null;
		ResultSet rsP = null;
		ArrayList<String> alP = new ArrayList<String>();
		try{con = transaction.getConnection();
			 stP = con.prepareStatement("select notfin,concat(codtippba,numtippba) as prueba,codcon from evaluacion where peracad=? and codcur = ? and seccion = ? order by nombre,codtippba desc, numtippba ");
			 stP.setString(1,perAcad);
			 stP.setString(2,codCur);
			 stP.setString(3,seccion);
			 rsP = stP.executeQuery();
			 while(rsP.next()){
				   if(rsP.getInt("codcon")== 2){
					alP.add(rsP.getString("notfin"));
				   }
				   if(rsP.getInt("codcon")== 1){
						alP.add("NP");
					   }
				   
				   if(rsP.getInt("codcon")== 3){
						alP.add("0A");
					   }
				}
			 
		}catch(SQLException e1) {
			throw new TransactionException(e1);
		}
		
		return alP;
	}
	
	public ArrayList<String> getAllPruebaHist(String codCur,String seccion,String perAcad) throws TransactionException{
		Connection con = null;
		PreparedStatement stPH = null;
		ResultSet rsPH = null;
		ArrayList<String> alPH = new ArrayList<String>();
		try{con = transaction.getConnection();
			 stPH = con.prepareStatement("select notfin,concat(codtippba,numtippba) as prueba,codcon from hist_evaluacion where peracad=? and codcur = ? and seccion = ? order by nombre,codtippba desc, numtippba ");
			 stPH.setString(1,perAcad);
			 stPH.setString(2,codCur);
			 stPH.setString(3,seccion);
			 rsPH = stPH.executeQuery();
			 while(rsPH.next()){
				   if(rsPH.getInt("codcon")== 2){
					alPH.add(rsPH.getString("notfin"));
				   }
				   if(rsPH.getInt("codcon")== 1){
						alPH.add("NP");
					   }
				   
				   if(rsPH.getInt("codcon")== 3){
						alPH.add("0A");
					   }
				}
			 
		}catch(SQLException e1) {
			throw new TransactionException(e1);
		}
		
		return alPH;
	}


}
