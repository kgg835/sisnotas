package ccuni.java.sysNotas.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.DynaValidatorForm;

import ccuni.java.sysNotas.constantes.Constantes;
import ccuni.java.sysNotas.domain.dto.UsuarioTO;
import ccuni.java.sysNotas.logic.UsuarioManager;

public class UsuarioAction extends Action
{
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception
    {
        // get the session object
        HttpSession session = request.getSession();
        // get the user object
        UsuarioTO user = (UsuarioTO) session.getAttribute("usuario");
        UsuarioManager um = new UsuarioManager(getDataSource(request, "sisNotas"));
        UsuarioTO userview;
        String action = request.getParameter("action");
        if (action.equals("view"))
        {
            String codUsr = request.getParameter("codUser");
            userview = um.find(codUsr);
            session.setAttribute("usuarioview", userview);
            return (mapping.findForward("verUsuario"));
        }
        else if (action.equals("update"))
        {
            String codUser = request.getParameter("coduser");
            String estado = request.getParameter("estado");
            um.modificarEstado(Integer.parseInt(estado), codUser);
            String pass = (String) ((DynaValidatorForm) form).get("password");
            String pass2 = (String) ((DynaValidatorForm) form).get("password2");
            String chk = request.getParameter("editarpass");
            if (chk != null)
            {
                ActionErrors errors = new ActionErrors();
                if (pass == null || pass.equals(""))
                {
                    ActionError error = new ActionError("error.password.required");
                    errors.add("password", error);
                }
                if (pass.length() > 0 && pass.length() < Constantes.LONG_CLAVE)
                {
                    ActionError error = new ActionError("error.password.minlength");
                    errors.add("password", error);
                }
                if (errors.isEmpty())
                {
                    if (pass.equals(pass2))
                    {
                        um.cambiarPasswordUsuario(codUser, pass);
                        return (mapping.findForward("update"));
                    }
                    else
                    {
                        ActionError error = new ActionError("error.password.confirmPass");
                        errors.add("password", error);
                        saveErrors(request, errors);
                        request.setAttribute("cheked", "cheked");
                        return (mapping.findForward("verUsuario"));
                    }
                }
                else
                {
                    saveErrors(request, errors);
                    request.setAttribute("cheked", "cheked");
                    return (mapping.findForward("verUsuario"));
                }
            }
            else
            {
                um.cambiarPasswordUsuario(codUser, pass);
                // elimino objetos de la sesion
                session.removeAttribute("usuarioview");
                return (mapping.findForward("update"));
            }
        }
        // falta eliminar datos de la sesion
        else if (action.equals("cancel"))
        {
            // elimino objetos de la sesion
            session.removeAttribute("usuarioview");
            return (mapping.findForward("cancel"));
        }
        else if (action.equals("cambiarpass"))
        {
            String chk = request.getParameter("editarpass");
            if (chk != null)
            {
                // marco la opcion
                request.setAttribute("cheked", "cheked");
                // ((DynaActionForm)form).set("editarpass","cualquier cosa");
                // ((DynaValidatorForm)form).set("editarpass",true);
                return (mapping.findForward("verUsuario"));
            }
            else
            {
                return (mapping.findForward("verUsuario"));
            }
        }
        return (mapping.findForward("cancel"));
    }
}
