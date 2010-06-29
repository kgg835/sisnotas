package ccuni.java.sysNotas.logic;

import ccuni.java.sysNotas.dao.ClipperTransaction;
import ccuni.java.sysNotas.dao.DAOFactory;
import ccuni.java.sysNotas.dao.MatriculaDAO;
import ccuni.java.sysNotas.dao.MysqlTransaction;
import ccuni.java.sysNotas.dao.PruebaDAO;
import ccuni.java.sysNotas.dao.TransactionException;
import ccuni.java.sysNotas.domain.dto.CursoTO;

import java.util.ArrayList;

import javax.sql.DataSource;

public class MatriculaManager {
    
    public MatriculaManager() {
       
    }
    public String[] obtieneAlumnosxCurso(CursoTO curso) throws TransactionException{
    	
        
        
        ClipperTransaction t = new ClipperTransaction();
        String[] p=null;
        try {
                DAOFactory clipperFactory = DAOFactory.getDAOFactory(DAOFactory.CLIPPER);
                MatriculaDAO matDAO = clipperFactory.getMatriculaDAO(t);                  
                p=matDAO.getAlumnosxCurso(curso);
             
        } catch (TransactionException e) {
             
        	e.printStackTrace();
            throw new TransactionException("Error al consultar los alumnos", e);
        } finally {
                t.close();
        }    
        return p;
    }
}
