package ccuni.java.sysNotas.logic;

import ccuni.java.sysNotas.dao.ClipperTransaction;
import ccuni.java.sysNotas.dao.DAOFactory;
import ccuni.java.sysNotas.dao.InsertarResultadoDAO;
import ccuni.java.sysNotas.dao.TransactionException;

public class ManagerInsertarResultado {
	public ManagerInsertarResultado() {
		
	}
	
	 public int insertar(String cursoId ,String seccion ,String codAlumno,String perAcad,String notfin,String recfin ,String notpar ,String recpar ,String notsus ,String recsus ,String prom ,String promnum) throws TransactionException{
		 ClipperTransaction t = new ClipperTransaction();
		 int ind=-1;
		 try {

				DAOFactory clipperFactory = DAOFactory
						.getDAOFactory(DAOFactory.CLIPPER);
				InsertarResultadoDAO insertarResultadoDAO = clipperFactory.getInsertarResultadoDAO(t);
				ind=insertarResultadoDAO.insertIntoMatriClipper( cursoId , seccion,codAlumno ,perAcad, notfin,recfin , notpar , recpar , notsus , recsus , prom , promnum);
		 } catch (TransactionException te) {
				te.printStackTrace();
				throw new TransactionException("Error al insertar registro en clipper",te);
			} finally {
				t.close();
			}
		return ind;
	}
}
