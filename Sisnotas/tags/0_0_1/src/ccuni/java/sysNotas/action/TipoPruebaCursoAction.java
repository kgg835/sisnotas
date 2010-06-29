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

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionForm;
import org.apache.struts.validator.DynaValidatorForm;

import java.util.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class TipoPruebaCursoAction extends Action {
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		Log log = LogFactory.getLog(TipoPruebaCursoAction.class);
		// get the session object
		HttpSession session = request.getSession();
		// get the user object
		UsuarioTO user = (UsuarioTO) session.getAttribute("usuario");
		String action = request.getParameter("action");
		// viene de addprueba
		if (action != null) {
			if (action.equals("addPrueba")) {
				CursoManager cm = new CursoManager(getDataSource(request,
						"sisNotas"));
				ArrayList<TipoPruebaTO> tiposDePrueba = cm
						.obtenerTiposDePrueba();
				// obtengo los disponibles
				CursoTO curso = (CursoTO) session.getAttribute("paramCurso");
				ArrayList<TipoPruebaTO> tiposUsados = curso.getTiposDePrueba();
				ArrayList<TipoPruebaTO> tiposPruebaDisponibles = new ArrayList<TipoPruebaTO>();
				if (tiposUsados != null) {
					for (int i = 0; i < tiposUsados.size(); i++) {
						TipoPruebaTO tp = tiposUsados.get(i);
						if (tiposDePrueba.contains(tp)) {
							tiposDePrueba.remove(tp);
						}
					}
					for (int i = 0; i < tiposDePrueba.size(); i++) {
						if (!tiposDePrueba.get(i).isExamen()) {
							tiposPruebaDisponibles.add(tiposDePrueba.get(i));
						}
					}
				}
				session.setAttribute("tiposDePruebaDisponibles",
						tiposPruebaDisponibles);
				session.removeAttribute("pruebaParaEditar");
				return (mapping.findForward("addPrueba"));
			} else if (action.equals("editarPrueba")) {
				String codTipPba = request.getParameter("tipPba");
				CursoTO curso = (CursoTO) session.getAttribute("paramCurso");
				TipoPruebaTO to = curso.getTipoPrueba(codTipPba);
				session.setAttribute("pruebaParaEditar", to);
				((DynaValidatorForm) form).set("numeropruebas", to
						.getCursoTipoPrueba().getNumero());
				((DynaValidatorForm) form).set("eliminados", to
						.getCursoTipoPrueba().getNumelim());
				((DynaValidatorForm) form).set("pesoprueba", to
						.getCursoTipoPrueba().getPeso());
				return (mapping.findForward("editarPrueba"));
			} else if (action.equals("savePrueba")) {
				CursoTO curso = (CursoTO) session.getAttribute("paramCurso");
				// obtengo del combo
				String codTipoPrueba = request.getParameter("tipoprueba");
				// obtengo lo demas
				int numero = (Integer) ((DynaValidatorForm) form)
						.get("numeropruebas");
				int numelim = (Integer) ((DynaValidatorForm) form)
						.get("eliminados");
				int peso = (Integer) ((DynaValidatorForm) form)
						.get("pesoprueba");
				// obtengo los parametros
				TipoPruebaTO to = new TipoPruebaTO();
				CursoTipoPruebaTO to2 = new CursoTipoPruebaTO();
				to.setCodTipPba(codTipoPrueba);
				to2.setNumero(numero);
				to2.setNumelim(numelim);
				to2.setPeso(peso);
				to2.setLogdel(Constantes.ACTIVO);
				to.setCursoTipoPrueba(to2);
				// lleno el TO
				CursoManager cm = new CursoManager(getDataSource(request,
						"sisNotas"));
				TipoPruebaTO to3 = cm.find(curso.getCursoId(), curso
						.getSeccion(), to);
				ServletContext sc = this.getServlet().getServletContext();
				ParametrosTO pto = (ParametrosTO) sc
						.getAttribute("parametrosSistema");
				if (to3 == null) {
					log.info("No existe en la bd");
					cm.addTipoPrueba(curso.getCursoId(), curso.getSeccion(),
							to, user.getCodUsr(), pto.getPeracad());
				} else if (to3.getCursoTipoPrueba().getLogdel() == 0) {
					log.info("solo activo el tipo prueba, ya que existe");
					cm.updateTipoPrueba(curso.getCursoId(), curso.getSeccion(),
							to, user.getCodUsr(), pto.getPeracad());
				}
				return (mapping.findForward("success"));
			} else if (action.equals("updatePrueba")) {
				CursoTO curso = (CursoTO) session.getAttribute("paramCurso");
				TipoPruebaTO lastto = (TipoPruebaTO) session
						.getAttribute("pruebaParaEditar");
				String codTipoPrueba = lastto.getCodTipPba();
				int numero = ((Integer) ((DynaValidatorForm) form)
						.get("numeropruebas")).intValue();
				int numelim = ((Integer) ((DynaValidatorForm) form)
						.get("eliminados")).intValue();
				int peso = ((Integer) ((DynaValidatorForm) form)
						.get("pesoprueba")).intValue();
				// obtengo los parametros
				TipoPruebaTO to = new TipoPruebaTO();
				CursoTipoPruebaTO to2 = new CursoTipoPruebaTO();
				to.setCodTipPba(codTipoPrueba);
				to2.setNumero(numero);
				to2.setNumelim(numelim);
				to2.setPeso(peso);
				to2.setLogdel(Constantes.ACTIVO);
				to.setCursoTipoPrueba(to2);
				// lleno el TO
				CursoManager cm = new CursoManager(getDataSource(request,
						"sisNotas"));
				// obtengo los parametros del sistema
				ServletContext sc = this.getServlet().getServletContext();
				ParametrosTO pto = (ParametrosTO) sc
						.getAttribute("parametrosSistema");
				cm.updateTipoPrueba(curso.getCursoId(), curso.getSeccion(), to,
						user.getCodUsr(), pto.getPeracad());
				session.removeAttribute("pruebaParaEditar");
				return (mapping.findForward("success"));
			} else if (action.equals("cancel")) {
				session.removeAttribute("tiposDePruebaDisponibles");
				session.removeAttribute("pruebaParaEditar");
				return (mapping.findForward("success"));
			}
		} else {
			return (mapping.findForward("success"));
		}
		return (mapping.findForward("success"));
	}
}
