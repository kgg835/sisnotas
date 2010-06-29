package ccuni.java.sysNotas.action;

import ccuni.java.sysNotas.constantes.Constantes;
import ccuni.java.sysNotas.domain.dto.CursoTO;
import ccuni.java.sysNotas.domain.dto.ParametrosTO;
import ccuni.java.sysNotas.domain.dto.TipoPruebaTO;
import ccuni.java.sysNotas.domain.dto.UsuarioTO;
import ccuni.java.sysNotas.logic.CursoManager;
import ccuni.java.sysNotas.logic.DocenteManager;
import ccuni.java.sysNotas.logic.ParametrosManager;
import ccuni.java.sysNotas.logic.TipoPruebaManager;

import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.*;
import org.apache.struts.util.LabelValueBean;

public class TipoPruebaAction extends Action {

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

//		 get the session object
		HttpSession session = request.getSession();
		// get the user object
		UsuarioTO user = (UsuarioTO) session.getAttribute("usuario");
		
		String action = request.getParameter("action");
		
		if(action.equals("list")){
			TipoPruebaManager tpm = new TipoPruebaManager(getDataSource(request,"sisNotas"));
			ArrayList<TipoPruebaTO> list = tpm.selectAll();
			session.setAttribute("listatiposprueba",list);
			return (mapping.findForward("list"));
		}
		else if(action.equals("add")){
			return (mapping.findForward("add"));
		}
		else if(action.equals("del")){
			String codTipPba = request.getParameter("cod");
			TipoPruebaManager tpm = new TipoPruebaManager(getDataSource(request,"sisNotas"));
			tpm.delete(codTipPba, user.getCodUsr());
			ArrayList<TipoPruebaTO> list = tpm.selectAll();
			session.setAttribute("listatiposprueba",list);
			return (mapping.findForward("list"));
		}
		else if(action.equals("ok")){
			session.removeAttribute("listatiposprueba");
			return (mapping.findForward("ok"));
		}
		else if(action.equals("edit")){
			String codtippba = request.getParameter("cod");
			TipoPruebaManager tpm = new TipoPruebaManager(getDataSource(request,"sisNotas"));
			TipoPruebaTO to = tpm.find(codtippba);
			request.setAttribute("tipopruebaAmodificar",to);
			return (mapping.findForward("edit"));
		}
		else if(action.equals("updateTipoPrueba")){
			String codTipPba = request.getParameter("codTipPba");
			//actualizo el tipo de prueba
			String nombre = request.getParameter("nombre");
			TipoPruebaManager tpm = new TipoPruebaManager(getDataSource(request,"sisNotas"));
			TipoPruebaTO to = new TipoPruebaTO();
			to.setCodTipPba(codTipPba);
			to.setNombre(nombre);
			to.setLogdel(Constantes.ACTIVO);
			tpm.update(to, user.getCodUsr());
			ArrayList<TipoPruebaTO> list = tpm.selectAll();
			session.setAttribute("listatiposprueba",list);
			return (mapping.findForward("list"));
		}
		else if(action.equals("saveTipoPrueba")){
			String codTipPba = request.getParameter("codigo");
			String nombre = request.getParameter("nombre");
			TipoPruebaTO to = new TipoPruebaTO();
			to.setCodTipPba(codTipPba);
			to.setNombre(nombre);
			to.setLogdel(Constantes.ACTIVO);
			TipoPruebaManager tpm = new TipoPruebaManager(getDataSource(request,"sisNotas"));
			TipoPruebaTO to2=  tpm.find(codTipPba);
			if(to2==null){
				tpm.insert(to, user.getCodUsr());
			}
			else{
				tpm.update(to,user.getCodUsr());
			}
			ArrayList<TipoPruebaTO> list = tpm.selectAll();
			session.setAttribute("listatiposprueba",list);
			return (mapping.findForward("list"));
		}
		else if(action.equals("cancel")) {
			//se elimknan objetos de la sesion
			return (mapping.findForward("list"));
		}
		else if(action.equals("finalizarAdmin")){
			//se finaliza la administracion
			return (mapping.findForward("finalizarAdmin"));
		}
		else if(action.equals("aceptarFinAdminPruebas")){
			//se debe de actualizar el contexto
			ServletContext sc = this.getServlet().getServletContext();
			ParametrosTO pto = (ParametrosTO)sc.getAttribute("parametrosSistema");
			pto.setTipos_prueba_definidos(1);
			ParametrosManager pm = new ParametrosManager(getDataSource(request,"sisNotas"));
			pm.updateParametros(pto);
			sc.setAttribute("parametrosSistema",pto);
			return (mapping.findForward("list"));
		}
		else if(action.equals("cancelFinAdminPruebas")){
			return (mapping.findForward("list"));
		}
		return null;
	}
}
