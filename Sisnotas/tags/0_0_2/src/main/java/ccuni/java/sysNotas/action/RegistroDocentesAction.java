package ccuni.java.sysNotas.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ccuni.java.sysNotas.constantes.Constantes;
import ccuni.java.sysNotas.domain.dto.ParametrosTO;
import ccuni.java.sysNotas.logic.ParametrosManager;

public class RegistroDocentesAction extends Action
{
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception
    {
        // get the session object
        HttpSession session = request.getSession();
        // get the user object
        // UsuarioTO user = (UsuarioTO)session.getAttribute("usuario");
        ParametrosManager pm = new ParametrosManager(getDataSource(request, "sisNotas"));
        ParametrosTO to = pm.getParametros();
        if (to.getMigracion_docentes() == Constantes.NO_MIGRADO)
        {
            // si es la primera vez que va a registrar docentes
            return (mapping.findForward("migrar"));
        }
        else
        {
            return (mapping.findForward("registrar"));
        }
    }
}
