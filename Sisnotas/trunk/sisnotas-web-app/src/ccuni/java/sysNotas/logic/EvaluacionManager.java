package ccuni.java.sysNotas.logic;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Types;
import java.util.ArrayList;

import javax.sql.DataSource;

import ccuni.java.sysNotas.dao.ClipperTransaction;
import ccuni.java.sysNotas.dao.DAOFactory;
import ccuni.java.sysNotas.dao.EvaluacionDAO;
import ccuni.java.sysNotas.dao.MatriculaDAO;
import ccuni.java.sysNotas.dao.MysqlTransaction;
import ccuni.java.sysNotas.dao.PruebaDAO;

import ccuni.java.sysNotas.dao.TransactionException;
import ccuni.java.sysNotas.domain.dto.EvaluacionTO;
import ccuni.java.sysNotas.domain.dto.PruebaTO;
import ccuni.java.sysNotas.domain.dto.UsuarioTO;

public class EvaluacionManager {
    private DataSource dataSource;
    public EvaluacionManager(DataSource dataSource) {
        this.dataSource= dataSource;
    }
    public ArrayList obtieneEvaluaciones (PruebaTO prueba) throws TransactionException{
    	 MysqlTransaction t = new MysqlTransaction(dataSource);
         ArrayList p=new ArrayList();
         
         
         
         try {
                 DAOFactory mysqlFactory = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
                 EvaluacionDAO evalDAO = mysqlFactory.getEvaluacionDAO(t);                  
                 p=evalDAO.selectxPrueba(prueba);
                 t.commit();
         } catch (TransactionException e) {
                 t.rollback();
                 throw new TransactionException(e.getMessage());
         } finally {
                 t.close();
         }    
         return p;
    }
    public void registraEvaluaciones(String username,EvaluacionTO evaluacion,PruebaTO prueba,String[] codigos,String[] nombres,String[] notas,String[] notasRec) throws TransactionException{
    	 MysqlTransaction t = new MysqlTransaction(dataSource);
         
         try {
                 DAOFactory mysqlFactory = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
                 EvaluacionDAO evalDAO = mysqlFactory.getEvaluacionDAO(t);   
                 
                 //llena todas las evaluaciones
                 for (int i = 0; i < nombres.length; i++) {				
         			
     				evaluacion.setCodAlum(codigos[i]);    
     				evaluacion.setNombre(nombres[i]);
     				

     				if (prueba.getEstado().getAccion().equalsIgnoreCase("save")) {
     					if(notas[i]==null || notas[i].equalsIgnoreCase("") ||notas[i].equalsIgnoreCase("NP")) {
								evaluacion.setNota("0");
								evaluacion.setCodcon("1");
     					}else if(notas[i].equalsIgnoreCase("0A")){
	     						evaluacion.setNota("0");
								evaluacion.setCodcon("3");
     					}else {
							evaluacion.setNota(notas[i]);
     					    evaluacion.setCodcon("2");
     					}
     					
     					evaluacion.setUsrins(username);
     					evalDAO.insert(evaluacion);
     				} else { 
     					/** must need to update existing registro*/
     						if(prueba.getEstado().getDescripcion().equalsIgnoreCase("modificar registro")){
     							
		     						if(notas[i]==null || notas[i].equalsIgnoreCase("") ||notas[i].equalsIgnoreCase("NP") ) {
		    								evaluacion.setNota("0");
		    								evaluacion.setCodcon("1");
		         					}else if(notas[i].equalsIgnoreCase("0A")){
		    	     						evaluacion.setNota("0");
		    								evaluacion.setCodcon("3");
		         					}else {
			    							evaluacion.setNota(notas[i]);
			         					    evaluacion.setCodcon("2");
		         					}
	     							evaluacion.setUsrins(username);	
	     				/** fin de actualizar notas   */
	     							
     						}else{/** registro virtual y actualizacion reclamo de notas*/
     							
     							//reclamos 
	     							
	     							
	     							if(notasRec[i]==null || notasRec[i].equalsIgnoreCase(""))
	     								continue;/**si no hay reclamos pasa al siguienet registro paa ver si hay reclamo o no*/
	     							
	     							evaluacion.setUsrupd(username);
		     						evaluacion.setLognotrec("1");
	     							if(notasRec[i].equalsIgnoreCase("0A")){
	     								evaluacion.setNotaRec("0");
	     								evaluacion.setCodcon("3");
	     							}else{
	     								evaluacion.setNotaRec(notasRec[i]);
	     								evaluacion.setCodcon("2");
	     							}
	     							
	     							
	     								
     						}
     						/**fin de actualizacion de reclamos de notas*/
     						evalDAO.update(evaluacion);
     				}

     			}
                 
                 //modifica tabla pruebas actualiza el estado del aprueba
                 PruebaDAO pruebaDAO =mysqlFactory.getPruebaDAO(t);
                 
                 if (prueba.getEstado().getAccion().equalsIgnoreCase("save"))  prueba.setUsrins(username);                 	                	 
                 else {
                	 if(prueba.getEstado().getDescripcion().equalsIgnoreCase("modificar registro"))
                		 prueba.setUsrins(username); 
                	 else
                		 prueba.setUsrupd(username);                		 
                	
                 }
                 
                 int num=1;                
                 if(prueba.getAccionId()==5 || prueba.getAccionId()==2) num=2;
                 //ACTUALIZA EL ESTADO DE LA PRUEBA
                 prueba.setAccionId(prueba.getAccionId()+num);                 
                 pruebaDAO.update(prueba);
                
                 t.commit();
         } catch (TransactionException e) {
                 t.rollback();
                 throw new TransactionException(e.getMessage());
         } finally {
                 t.close();
         }    
    
    	
    }

    
    
    public void obtieneEvaluaciones(PruebaTO prueba,int accionId,ArrayList<String> listAlumno,ArrayList<String> listNombres,ArrayList<String> listNota,ArrayList<String> listNotaRec,ArrayList <String> condicion) throws TransactionException{
   	  
   	 
   	    if(accionId==2){//registro de notas la priemra vez
   	    	ClipperTransaction t = new ClipperTransaction();
   	        
   	        try {
   	                DAOFactory clipperFactory = DAOFactory.getDAOFactory(DAOFactory.CLIPPER);
   	                MatriculaDAO matDAO = clipperFactory.getMatriculaDAO(t);                  
   	                matDAO.getAlumnos(prueba,listAlumno,listNombres,listNota,listNotaRec);
   	             
   	        } catch (TransactionException e) {
   	             
   	        	e.printStackTrace();
   	            throw new TransactionException("Error al consultar los alumnos", e);
   	        } finally {
   	                t.close();
   	        } 
   	    	
   	    }
   	    else{   
   	    	MysqlTransaction t = new MysqlTransaction(dataSource); 
	        try {
	        	    
	                DAOFactory mysqlFactory = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
	                EvaluacionDAO evalDAO = mysqlFactory.getEvaluacionDAO(t);                  
	                evalDAO.select(prueba,listAlumno,listNombres,listNota,listNotaRec,condicion);
	              
	        } catch (TransactionException e) {
	               
	                throw new TransactionException(e.getMessage());
	        } finally {
	                t.close();
	        }    
   	    }
        
   }
    public boolean verificaRegistro (PruebaTO prueba) throws TransactionException{
    	 MysqlTransaction t = new MysqlTransaction(dataSource);  
         boolean flag=false;
         
         try {
                 DAOFactory mysqlFactory = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
                 EvaluacionDAO evalDAO = mysqlFactory.getEvaluacionDAO(t);                  
                 flag=evalDAO.existeRegistro(prueba);
                
         } catch (TransactionException e) {
                
                 throw new TransactionException(e.getMessage());
         } finally {
                 t.close();
         }    
        
    	return flag;
    }
}
