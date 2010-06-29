package ccuni.java.sysNotas.action;

import ccuni.java.sysNotas.domain.dto.*;
import ccuni.java.sysNotas.constantes.*;
import ccuni.java.sysNotas.logic.*;
import ccuni.java.sysNotas.dao.*;
import ccuni.java.sysNotas.implDao.*;
import ccuni.java.sysNotas.actionForm.*;

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

public class ListarDocentesAction extends Action {
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// get the session object
		HttpSession session = request.getSession();
		// get the user object
		// UsuarioTO user = (UsuarioTO)session.getAttribute("usuario");
		String action = request.getParameter("action");
		if (action.equals("aceptar")) {
			DocenteManager docManager = new DocenteManager();
			ArrayList list = docManager.selectAllDocentes();
			session.setAttribute("listaDocentes", list);
			return (mapping.findForward("aceptar"));
		}
		else{//cancelar
			return (mapping.findForward("cancelar"));
		}
	}
}
