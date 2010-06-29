package ccuni.java.sysNotas.action;

import ccuni.java.sysNotas.domain.dto.*;
import ccuni.java.sysNotas.constantes.*;
import ccuni.java.sysNotas.logic.*;
import ccuni.java.sysNotas.dao.*;
import ccuni.java.sysNotas.implDao.*;
import ccuni.java.sysNotas.actionForm.*;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import java.util.*;

import javax.servlet.http.HttpSession;

public class MigrarDocentesAction extends Action {
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		// get the session object
		HttpSession session = request.getSession();

		// get the user object
		UsuarioTO user = (UsuarioTO) session.getAttribute("usuario");
		String[] codigosDoc = request.getParameterValues("docentes");
		// obtener todos los docentes seleccionados
		String action = request.getParameter("action");
		if (action.equals("registrar")) {
			UsuarioManager um = new UsuarioManager(getDataSource(request,
					"sisNotas"));
			DocenteManager dm = new DocenteManager();
			DocenteTO docente;
			ArrayList<DocenteTO> docentes = new ArrayList<DocenteTO>();
			for (int i = 0; i < codigosDoc.length; i++) {
				docente = dm.find(codigosDoc[i]);
				docentes.add(docente);
			}
			//lista de usuarios creados
			ArrayList userCreados=null; 
			// si es la primera vez que se migra elimiar todos los docentes de la tabla usuarios y luego  cambiar el valor del
			// parametro
			ParametrosManager pm = new ParametrosManager(getDataSource(request,
					"sisNotas"));
			ParametrosTO to = pm.getParametros();
			
			if (to.getMigracion_docentes() == Constantes.NO_MIGRADO) {
				//elimino docentes de la tabla usuarios
				um.deleteAll(Constantes.DOCENTES);
				// se registran los usuarios seleccionados
				userCreados = um.registrarDocentes(docentes, user.getUserName());
				// si era la primera vez cambio el valor
				to.setMigracion_docentes(Constantes.MIGRADO);
				pm.updateParametros(to);
				
				ServletContext sc = this.getServlet().getServletContext();
				sc.setAttribute("parametrosSistema",to);
			}
			else{
				// se registran los usuarios seleccionados
//				se tiene que validar si ya existe
				String msg="";
				ArrayList<DocenteTO> docentesValidos= new ArrayList<DocenteTO>();
				for (int i = 0; i < docentes.size(); i++) {
					DocenteTO doc = docentes.get(i);
					UsuarioTO uto = um.find(doc.getCodDoc());
					if(uto!=null){
						msg+="El docente "+doc.getNombreCompleto()+" ya esta registrado\n";
					}
					else
						docentesValidos.add(doc);
				}
				if(docentesValidos.size()>0){
					userCreados = um.registrarDocentes(docentesValidos, user.getUserName());
				}
				request.setAttribute("UsuariosYaExisten",msg);
				//se tiene que validar si ya existe
			}
		
			// se almacenan en la sesion para mostrar el reporte
			request.setAttribute("listaDocentesRegistrados", userCreados);
			// se elimina la lista de docentes de la sesion
			session.removeAttribute("listaDocentes");
			// ir al reporte
			return (mapping.findForward("registrar"));

		} else if (action.equals("cancelar")) {
			// volver a la pagina anterior
			// elimino las variables de session
			session.removeAttribute("listaDocentes");
			return (mapping.findForward("cancelar"));
		}
		return (mapping.findForward("cancelar"));
	}
}
