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
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;


import com.sun.org.apache.xpath.internal.operations.Gte;

import java.util.*;

public class RegistrarCursosAction extends DispatchAction {
	/*public ActionForward iniciarAdministrarCursos(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		ParametrosManager pm = new ParametrosManager(getDataSource(request,
				"sisNotas"));
		ParametrosTO to = pm.getParametros();
		if (to.getMigracion_cursos() == Constantes.NO_MIGRADO) {
			// si es la primera vez que va a registrar cursos listado de todos
			// los cursos existententes en clipper
			return (mapping.findForward("listadoCursosNoRegistrados"));
		} else {
			return (mapping.findForward(""));
		}
	}*/
	public ActionForward listarCursosNoRegistrados(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		//HttpSession session = request.getSession();
		CursoSeccionManager csm = new CursoSeccionManager(getDataSource(
				request, "sisNotas"));
		// seleccionar los cursos no registrados
		ParametrosManager pm = new ParametrosManager(getDataSource(request,
				"sisNotas"));
		List cursosNoRegistrados= null;
		ParametrosTO to = pm.getParametros();
		cursosNoRegistrados = csm.obtenerCursosNoRegistrados(to.getPeracad());
		request.setAttribute("cursosNoRegistrados",cursosNoRegistrados);
		return (mapping.findForward("listadoCursosNoRegistrados"));
	}

	public ActionForward registrarCursosSeleccionados(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		String action= request.getParameter("action");
		if(action!=null && action.equals("registrar")){
			String[] listCursosSeccion = request.getParameterValues("cursos");
			ServletContext sc = this.getServlet().getServletContext();
			CursoSeccionManager csm= new CursoSeccionManager(getDataSource(
					request, "sisNotas"));
			ParametrosTO pto = (ParametrosTO) sc.getAttribute("parametrosSistema");
			CursoTO to = null;
			List list = new ArrayList<CursoTO>();
			for(int i=0; i<listCursosSeccion.length;i++){
				String c = listCursosSeccion[i];
				to = new CursoTO();
				StringTokenizer tokens=new StringTokenizer(c,","); 
				to.setCursoId(tokens.nextToken());
				to.setNombre(tokens.nextToken());
				to.setSeccion(tokens.nextToken());
				to.setPerAcad(tokens.nextToken());
				list.add(to);
			}
			csm.registrarCursosSeccion(list, pto.getPeracad());
			
			return (mapping.findForward("administrarCursos"));
		}
		else if(action!=null && action.equals("cancelar")){
			return (mapping.findForward("home"));
		}
		// seleccionar los cursos no registrados
		//
		return (mapping.findForward("administrarCursos"));
	}

}
