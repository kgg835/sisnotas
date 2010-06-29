package ccuni.java.sysNotas.logic;

import java.util.ArrayList;

import ccuni.java.sysNotas.dao.ClipperTransaction;
import ccuni.java.sysNotas.dao.DAOFactory;
import ccuni.java.sysNotas.dao.DocenteDAO;
import ccuni.java.sysNotas.dao.TransactionException;
import ccuni.java.sysNotas.domain.dto.DocenteTO;

public class DocenteManager
{
    public DocenteManager()
    {
        // constructor por defecto
    }

    public ArrayList selectAllDocentes() throws TransactionException
    {
        ArrayList docentes = null;
        ClipperTransaction t = new ClipperTransaction();
        try
        {
            DAOFactory clipperFactory = DAOFactory.getDAOFactory(DAOFactory.CLIPPER);
            DocenteDAO docenteDAO = clipperFactory.getDocenteDAO(t);
            docentes = docenteDAO.selectAllDocentes();
        }
        catch (TransactionException te)
        {
            // log
            te.printStackTrace();
            throw new TransactionException("Error al consultar los docentes", te);
        }
        finally
        {
            t.close();
        }
        return docentes;
    }

    public DocenteTO find(String codDocente) throws TransactionException
    {
        ClipperTransaction t = new ClipperTransaction();
        DocenteTO to = null;
        try
        {
            DAOFactory clipperFactory = DAOFactory.getDAOFactory(DAOFactory.CLIPPER);
            DocenteDAO docenteDAO = clipperFactory.getDocenteDAO(t);
            to = docenteDAO.find(codDocente);
        }
        catch (TransactionException te)
        {
            // log
            te.printStackTrace();
            throw new TransactionException("Error al buscar el docente");
        }
        finally
        {
            t.close();
        }
        return to;
    }

    public int delete() throws TransactionException
    {
        ClipperTransaction t = new ClipperTransaction();
        int n;
        try
        {
            DAOFactory clipperFactory = DAOFactory.getDAOFactory(DAOFactory.CLIPPER);
            DocenteDAO docenteDAO = clipperFactory.getDocenteDAO(t);
            n = docenteDAO.eliminar();
        }
        catch (TransactionException te)
        {
            // log
            te.printStackTrace();
            throw new TransactionException("Error al eliminar docentes");
        }
        finally
        {
            t.close();
        }
        return n;
    }
    /*
     * public static void main(String[] args) throws Exception { DocenteManager
     * dm = new DocenteManager(); System.out.println(dm.delete()); ArrayList
     * list= dm.selectAllDocentes(); DocenteTO to; for(int i=0;
     * i<list.size();i++){ to=(DocenteTO)list.get(i);
     * System.out.println(to.getCodDoc()); } }
     */
}
