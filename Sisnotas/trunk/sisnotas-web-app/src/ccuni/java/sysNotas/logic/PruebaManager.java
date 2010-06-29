package ccuni.java.sysNotas.logic;

import ccuni.java.sysNotas.dao.DAOFactory;
import ccuni.java.sysNotas.dao.EstadoPruebaDAO;
import ccuni.java.sysNotas.dao.MysqlTransaction;
import ccuni.java.sysNotas.dao.PruebaDAO;
import ccuni.java.sysNotas.dao.PruebaLogicDAO;
import ccuni.java.sysNotas.dao.Transaction;
import ccuni.java.sysNotas.dao.TransactionException;
import ccuni.java.sysNotas.dao.UsuarioDAO;

import ccuni.java.sysNotas.domain.dto.CursoTO;

import ccuni.java.sysNotas.domain.dto.PruebaTO;

import java.util.ArrayList;

import javax.sql.DataSource;

import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMessage;

public class PruebaManager {
	private DataSource dataSource=null;
    public PruebaManager(DataSource dt) {
        dataSource=dt;
    }
    public CursoTO obtienePruebasxCurso(CursoTO curso)throws Exception{
        MysqlTransaction t = new MysqlTransaction(dataSource);
        
        try {
                DAOFactory mysqlFactory = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
                PruebaDAO pruebaDAO = mysqlFactory.getPruebaDAO(t);                  
                curso=pruebaDAO.selectAllPrueba(curso);
               
        } catch (TransactionException e) {
              
                throw new Exception(e.getMessage());
        } finally {
                t.close();
        }    
        return curso;
 
    }
    public PruebaTO obtienePrueba(PruebaTO p)throws Exception{
    	MysqlTransaction t = new MysqlTransaction(dataSource);
        
        try {
                DAOFactory mysqlFactory = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
                PruebaDAO pruebaDAO = mysqlFactory.getPruebaDAO(t);                  
                p=pruebaDAO.find(p);               
                t.commit();
        } catch (TransactionException e) {
                t.rollback();
                throw new Exception(e.getMessage());
        } finally {
                t.close();
        }    
        return p;
 
    }
    public ArrayList<String> obtenerEstadosPrueba() throws TransactionException{
    	MysqlTransaction t = new MysqlTransaction(dataSource);
    	ArrayList<String> p=new ArrayList<String>();
        try {
                DAOFactory mysqlFactory = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
                EstadoPruebaDAO estadoDAO = mysqlFactory.getEstadoPruebaDAO(t);                  
                p=estadoDAO.selectAll();               
                
        } catch (TransactionException e) {                
                throw new TransactionException(e.getMessage());
        } finally {
                t.close();
        }    
        return p;
    }
    public boolean verificaCambioEstado(PruebaTO p)throws Exception{
    	int cod_actual=p.getAccionId();
    	int cod_nuevo=p.getNuevoEstado();
    	switch(cod_actual){
    		case 1: if(cod_nuevo==2) return true;break;
    		case 2: if(cod_nuevo==1) return true;break;
    		case 3: if(cod_nuevo==4 || cod_nuevo==5 ) return true;break;
    		case 4: if(cod_nuevo==3 || cod_nuevo==5 || cod_nuevo==7) return true;break;
    		case 5: if(cod_nuevo==4 || cod_nuevo==7) return true;break;
    		case 6: if(cod_nuevo==7) return true;break;
    		case 7: if(cod_nuevo==6) return true;break;
    	}
        
        
        return false;
 
    }
    public int actualizaEstado(PruebaTO p)throws Exception{
    	MysqlTransaction t = new MysqlTransaction(dataSource);
        int n;
        try {
                DAOFactory mysqlFactory = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
                PruebaDAO pruebaDAO = mysqlFactory.getPruebaDAO(t);                  
                n=pruebaDAO.update(p);               
                t.commit();
        } catch (TransactionException e) {
                t.rollback();
                throw new Exception(e.getMessage());
        } finally {
                t.close();
        }    
        return n;
 
    }
    
    public int eliminaPruebasCursoSeccion(String codigoCurso, String seccion )throws Exception{
    	MysqlTransaction t = new MysqlTransaction(dataSource);
        int n;
        try {
                DAOFactory mysqlFactory = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
                PruebaDAO pruebaDAO = mysqlFactory.getPruebaDAO(t);                  
                n=pruebaDAO.delete(codigoCurso, seccion);
                if(n!=0)
                	t.commit();
        } catch (TransactionException e) {
                t.rollback();
                throw new Exception(e.getMessage());
        } finally {
                t.close();
        }    
        return n;
    }
   
}