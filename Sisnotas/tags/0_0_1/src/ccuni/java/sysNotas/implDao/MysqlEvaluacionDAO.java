package ccuni.java.sysNotas.implDao;

import ccuni.java.sysNotas.dao.EvaluacionDAO;
import ccuni.java.sysNotas.dao.Transaction;
import ccuni.java.sysNotas.dao.TransactionException;
import ccuni.java.sysNotas.domain.dto.EvaluacionTO;
import ccuni.java.sysNotas.domain.dto.PruebaTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

public class MysqlEvaluacionDAO implements EvaluacionDAO{
	 private Transaction transaction=null;
	    public MysqlEvaluacionDAO(Transaction t) {
	    this.transaction=t;
	    }
	   
	    public  Transaction getTransaction() {
	       return transaction;
	    }
	    
	    
    public int insert(EvaluacionTO t)throws TransactionException {
    	Connection c = null;
		PreparedStatement statement = null;
		int i = 1;

		try {
			c = transaction.getConnection();
		
			statement = c
					.prepareStatement("insert into EVALUACION (CODTIPPBA,CODALUM,NOMBRE, CODCUR,SECCION,PERACAD,NOTA,NOTFIN, NUMTIPPBA,LOGDEL, USRINS,DTEINS,LOGNOTREC,CODCON)  values (?, ?, ?,?, ?,?,?, ?, ?, 1, ?, CURRENT_TIMESTAMP(),0,?)");
			statement.setString(i++,t.getCodTipPba() );
			statement.setString(i++,t.getCodAlum() );
			statement.setString(i++,t.getNombre() );
			statement.setString(i++,t.getCodCur() );
			statement.setString(i++,t.getSeccion());
			statement.setString(i++,t.getPeracad() );			
			statement.setInt(i++,Integer.parseInt(t.getNota()));
			statement.setInt(i++,Integer.parseInt(t.getNota()));
			statement.setInt(i++,t.getNumtippba() );
			statement.setString(i++,t.getUsrins() );
			statement.setString(i++,t.getCodcon());
			

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

    public boolean delete(String t) {
        return false;
    }

    public EvaluacionTO find(String t) {
        return null;
    }

    public int update(EvaluacionTO t) throws TransactionException{
    	Connection c = null;
		PreparedStatement statement = null;
		int i = 1;
		int n;

		
		
		//formando el query                 
	    String query="UPDATE EVALUACION  SET ";
	    if(t.getLognotrec()!=null)	    query+="LOGNOTREC=?,";
	    if(t.getCodcon()!=null)	        query+="CODCON=?,";
	    if(t.getNota()!=null)	    	query+="NOTA=?,NOTFIN=?,";
	    if(t.getUsrins()!=null)	    	query+="USRINS=?,DTEINS=CURRENT_TIMESTAMP()";	    
	    if(t.getNotaRec()!=null)	    query+="NOTREC=?,NOTFIN=?,"; 
	    if(t.getUsrupd()!=null)	    	query+="USRUPD=?,DTEUPD=CURRENT_TIMESTAMP()";
	    
	    
	    
	    query+=" WHERE CODTIPPBA=? and CODALUM=? and  CODCUR=? and SECCION=? and PERACAD=? and NUMTIPPBA=? and LOGDEL=1";
		
	    try {
			c = transaction.getConnection();
		
			statement = c.prepareStatement(query);
			if(t.getLognotrec()!=null)	statement.setString(i++,t.getLognotrec() );
			if(t.getCodcon()!=null)		statement.setString(i++,t.getCodcon() );
			if(t.getNota()!=null){
				statement.setInt(i++,Integer.parseInt(t.getNota()) );
				statement.setInt(i++,Integer.parseInt(t.getNota()) );				
			}
			if(t.getUsrins()!=null)	    statement.setString(i++,t.getUsrins() );
			if(t.getNotaRec()!=null) {
				statement.setInt(i++,Integer.parseInt(t.getNotaRec()) );
				statement.setInt(i++,Integer.parseInt(t.getNotaRec()) );
			}
			if(t.getUsrupd()!=null)     statement.setString(i++,t.getUsrupd() );
			
		    
			
			statement.setString(i++,t.getCodTipPba());
			statement.setString(i++,t.getCodAlum() );
			statement.setString(i++,t.getCodCur());
			statement.setString(i++,t.getSeccion() );
			statement.setString(i++,t.getPeracad() );
			statement.setInt(i++,t.getNumtippba() );

			 n = statement.executeUpdate();
			if (n == 0)
				return -1;
			// throw new TransactionException("No se pudo crear el usuario");

		} catch (SQLException e1) {
			e1.printStackTrace();
			throw new TransactionException("Error al actualizar  las notas evaluacion");
		} finally {
			try {
				if (statement != null)
					statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}

		return n;
    }
 
    public Collection selectAllEvaluacion() {
        return null;
    }
    public void select(PruebaTO prueba,ArrayList<String> listAlumno,ArrayList<String> listNombres,ArrayList<String> listNota,ArrayList<String> listNotaRec,ArrayList <String> condicion) throws TransactionException{
    	Connection c;
        PreparedStatement statement = null;
        ResultSet rs=null;        
        int i = 1;

        try {
                c = transaction.getConnection();

                statement =
                        c.prepareStatement(
                                " select * from EVALUACION,CONDICION  WHERE EVALUACION.CODCON=CONDICION.CODCON and CODCUR=? and SECCION=? and PERACAD=? and CODTIPPBA=? and NUMTIPPBA=? and LOGDEL=1 ORDER BY NOMBRE");
                statement.setString(i++, prueba.getCodCur());
                statement.setString(i++, prueba.getSeccion());
                statement.setString(i++, prueba.getPerAcad());
                statement.setString(i++, prueba.getCodtippba());
                statement.setInt(i++, prueba.getNumtippba());
                 

                rs= statement.executeQuery();
                if(rs==null){
                	System.out.println("result set nulo");                }
                
                while(rs.next()){
                    
                    listAlumno.add(rs.getString("CODALUM"));   
                    if(rs.getString("NOMCOND").equalsIgnoreCase("P"))
                    	listNota.add(rs.getString("NOTA"));
                    else
                    	listNota.add(rs.getString("NOMCOND"));
                    
                    listNotaRec.add(rs.getString("NOTREC"));    
                    condicion.add(rs.getString("NOMCOND"));
                    listNombres.add(rs.getString("NOMBRE"));
                    
                }
                     
                statement.close();
                rs.close();
                

        } catch (SQLException e1) {

                throw new TransactionException(e1);

        }
    }
    public ArrayList selectxPrueba(PruebaTO prueba) throws TransactionException{
    	Connection c;
        PreparedStatement statement = null;
        ResultSet rs=null;
        ArrayList<EvaluacionTO> evaluaciones=null;
        int i = 1;

        try {
                c = transaction.getConnection();

                statement =
                        c.prepareStatement(
                                " select * from EVALUACION  WHERE CODCUR=? and SECCION=? and PERACAD=? and CODTIPPBA=? and NUMTIPPBA=? and LOGDEL=1");
                statement.setString(i++, prueba.getCodCur());
                statement.setString(i++, prueba.getSeccion());
                statement.setString(i++, prueba.getPerAcad());
                statement.setString(i++, prueba.getCodtippba());
                statement.setInt(i++, prueba.getNumtippba());
                 

                rs= statement.executeQuery();
                if(rs==null){
                	System.out.println("result set nulo");
                }
                evaluaciones=new ArrayList<EvaluacionTO>();
                while(rs.next()){
                    EvaluacionTO p=new EvaluacionTO();
                                     
                    p.setCodTipPba(rs.getString("CODTIPPBA"));
                    p.setCodAlum(rs.getString("CODALUM")); 
                    p.setCodCur(rs.getString("CODCUR"));
                    p.setSeccion(rs.getString("SECCION"));                    
                    p.setPeracad(rs.getString("PERACAD"));
                    p.setNota(rs.getString("NOTA"));
                    p.setNotaRec(rs.getString("NOTREC"));
                    p.setNumtippba(rs.getInt("NUMTIPPBA"));
                    p.setUsrins(rs.getString("USRINS"));
                    p.setUsrupd(rs.getString("USRUPD"));
                    
                    
                    
                    evaluaciones.add(p);
                    
                    
                }
                     
                statement.close();
                rs.close();
                return evaluaciones;

        } catch (SQLException e1) {

                throw new TransactionException(e1);

        }
    }

	public boolean existeRegistro(PruebaTO prueba) throws TransactionException {
		Connection c;
        PreparedStatement statement = null;
        ResultSet rs=null;
        boolean flag=false;
        
        int i = 1;

        try {
                c = transaction.getConnection();

                statement =
                        c.prepareStatement(
                                " select * from EVALUACION  WHERE CODCUR=? and SECCION=? and PERACAD=? and CODTIPPBA=? and NUMTIPPBA=? and LOGDEL=1");
                statement.setString(i++, prueba.getCodCur());
                statement.setString(i++, prueba.getSeccion());
                statement.setString(i++, prueba.getPerAcad());
                statement.setString(i++, prueba.getCodtippba());
                statement.setInt(i++, prueba.getNumtippba());
                 

                rs= statement.executeQuery();
                if(rs.next()) flag=true;
                     
                statement.close();
                rs.close();
                

        } catch (SQLException e1) {

                throw new TransactionException(e1);

        }
		return flag;
	}
    
}

  
