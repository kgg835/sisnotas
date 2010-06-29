package ccuni.java.sysNotas.implDao;

import ccuni.java.sysNotas.dao.MatriculaDAO;
import ccuni.java.sysNotas.dao.Transaction;
import ccuni.java.sysNotas.dao.TransactionException;
import ccuni.java.sysNotas.domain.dto.AlumnoTO;
import ccuni.java.sysNotas.domain.dto.CursoTO;
import ccuni.java.sysNotas.domain.dto.PruebaTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ClipperMatriculaDAO implements MatriculaDAO{
    private Transaction transaction = null;

    public Transaction getTransaction() {
        return transaction;
    }
    public ClipperMatriculaDAO(Transaction t) {
        transaction=t;
    }

    

    public ClipperMatriculaDAO() {
        super();
    }

    public String[] getAlumnosxCurso(CursoTO curso) throws TransactionException {
    	Connection c;
        PreparedStatement statement = null;
        ResultSet rs=null;
        String[] listAlumno=null;
        int i = 1;

        try {
                c = transaction.getConnection();
                String nameTableMatricula="MATR"+curso.getPerAcad();

                statement =
                        c.prepareStatement(
                                " select CODALU from "+ nameTableMatricula+"  WHERE CODCUR=? and SECCION=?");
                statement.setString(i++, curso.getCursoId());
                statement.setString(i++, curso.getSeccion());               

                rs= statement.executeQuery();
                listAlumno=new String[50];
                int k=0;
                while(rs.next()){
                    String alumnoId =new String(rs.getString("CODALU"));      
                    listAlumno[k]=alumnoId;    
                    k++;
                }
                
                statement.close();
              
                                  

        } catch (SQLException e1) {
        	throw new TransactionException(e1);
        }
        return listAlumno;  
    }
	public AlumnoTO find(String alumnoId) throws TransactionException {
		// TODO Auto-generated method stub
		return null;
	}
	public void getAlumnos(PruebaTO prueba, ArrayList<String> listAlumno,ArrayList<String> listNombres, ArrayList<String> listNota, ArrayList<String> listNotaRec) throws TransactionException {
		Connection c;
        PreparedStatement statement = null;
        ResultSet rs=null;        
        int i = 1;

        try {
                c = transaction.getConnection();
                String nameTableMatricula="MATR"+prueba.getPerAcad();

                statement =
                        c.prepareStatement(
                                " select a.CODALU ,b.AP,b.AM,b.N from "+ nameTableMatricula+" as a,ALUMNOS as b WHERE a.CODALU=b.CODALU and a.CODCUR=? and a.SECCION=? order by b.AP,b.AM,b.N");
                statement.setString(i++, prueba.getCodCur());
                statement.setString(i++, prueba.getSeccion());               

                rs= statement.executeQuery();
                
                
                while(rs.next()){
                	listAlumno.add(rs.getString("CODALU"));  
                	listNota.add("");
                	listNotaRec.add("");  
                	listNombres.add(rs.getString("AP")+" " +rs.getString("AM")+" "+rs.getString("N"));
                	
                }
                
                
                statement.close();
              
                                  

        } catch (SQLException e1) {
        	throw new TransactionException(e1);
        }
		
	}
}
