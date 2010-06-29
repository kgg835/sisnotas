package ccuni.java.sysNotas.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ccuni.java.sysNotas.logic.UsuarioManager;

public class ListarUsuariosHabilitadosAction extends Action
{
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception
    {
        // get the session object
        HttpSession session = request.getSession(true);
        // get the user object
        // UsuarioTO user = (UsuarioTO)session.getAttribute("user");
        UsuarioManager um = new UsuarioManager(getDataSource(request, "sisNotas"));
        // ArrayList usuarios = um.obtenerUsuariosHabilitados();
        // session.setAttribute("listaUsuariosHabilitados", usuarios);
        return (mapping.findForward("success"));
    }
}
