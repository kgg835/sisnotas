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

import com.sun.org.apache.xpath.internal.operations.Gte;

import java.util.*;

import javax.servlet.http.HttpSession;

public class RegistroDocentesAction extends Action {
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// get the session object
		HttpSession session = request.getSession();
		// get the user object
		// UsuarioTO user = (UsuarioTO)session.getAttribute("usuario");

		ParametrosManager pm = new ParametrosManager(getDataSource(request,
				"sisNotas"));
		ParametrosTO to = pm.getParametros();
		if (to.getMigracion_docentes() == Constantes.NO_MIGRADO) {
			// si es la primera vez que va a registrar docentes
			return (mapping.findForward("migrar"));
		} else {
			return (mapping.findForward("registrar"));
		}
	}
}
