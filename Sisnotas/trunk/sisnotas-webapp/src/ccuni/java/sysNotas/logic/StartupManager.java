package ccuni.java.sysNotas.logic;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionServlet;
import org.apache.struts.action.PlugIn;
import org.apache.struts.config.ModuleConfig;
import ccuni.java.sysNotas.domain.dto.*;
import ccuni.java.sysNotas.dao.*;

import java.util.*;

public class StartupManager implements PlugIn {
	ServletContext sc;

	public void init(ActionServlet arg0, ModuleConfig arg1)
			throws ServletException {
		// save the servlet context for shutdown needs
		sc = arg0.getServletContext();

		// define the lists and place in the application context
		// set up the years
		// log.info("Initializing years.");
		// obteno la lista de tipos de prueba disponibles  -- YA NO
		ArrayList<TipoPruebaTO> tiposDePrueba = new ArrayList<TipoPruebaTO>();
		ArrayList<TipoPruebaTO> examenes = new ArrayList<TipoPruebaTO>();
		DataSource ds = (DataSource) sc.getAttribute("sisNotas");
		CursoManager cm = new CursoManager(ds);
		try {
			tiposDePrueba = cm.obtenerTiposDePrueba();
			for(int i=0; i<tiposDePrueba.size();i++){
				if(tiposDePrueba.get(i).isExamen()){
					examenes.add(tiposDePrueba.get(i));
				}
			}
		} catch (TransactionException te) {
			// TODO: handle exception
			te.printStackTrace();
		}
		sc.setAttribute("tiposDeExamen", examenes);

		// obtengo los docentes de la bd clipper
		/*ArrayList list = null;
		try {
			DocenteManager dm = new DocenteManager();
			list = dm.selectAllDocentes();
		} catch (TransactionException e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		sc.setAttribute("docentes", list);*/

		ParametrosTO to = null;
		try {
			ParametrosManager pm = new ParametrosManager(ds);
			to = pm.getParametros();
		} catch (TransactionException e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		sc.setAttribute("parametrosSistema", to);

		PruebaManager pm = new PruebaManager(ds);
		ArrayList<String> estados = new ArrayList<String>();
		try {
			estados = pm.obtenerEstadosPrueba();
		} catch (TransactionException te) {
			// TODO: handle exception
			te.printStackTrace();
		}

		sc.setAttribute("estados", estados);
		HashMap<String, Integer> estadoMap = new HashMap<String, Integer>();
		for (int i = 0; i < estados.size(); i++) {
			estadoMap.put(estados.get(i), i + 1);

		}
		sc.setAttribute("estadoMap", estadoMap);

	}

	/**
	 * releases resources at application shutdown
	 */
	public void destroy() {
		// shut down the dbcp
		// log.info("Shutting down the DataSource connection pool.");
		DataSource ds = (DataSource) sc.getAttribute("sisNotas");
		ds = null;
		sc.removeAttribute("sisNotas");

		// final message
		// log.info("MusicCollection.com has shutdown.");
	}
}
