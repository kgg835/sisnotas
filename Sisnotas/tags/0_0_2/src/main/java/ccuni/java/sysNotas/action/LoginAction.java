package ccuni.java.sysNotas.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.DynaValidatorForm;

import ccuni.java.sysNotas.domain.dto.UsuarioTO;
import ccuni.java.sysNotas.logic.UsuarioManager;

public class LoginAction extends Action
{
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception
    {
        UsuarioManager um = new UsuarioManager(getDataSource(request, "sisNotas"));
        UsuarioTO user;
        user = um.validarUsuario((String) ((DynaValidatorForm) form).get("usuario"),
                (String) ((DynaValidatorForm) form).get("password"));
        if (user != null)
        {
            request.getSession().setAttribute("usuario", user);
            return (mapping.findForward("loginCorrecto"));
        }
        else
        {
            ActionErrors errors = new ActionErrors();
            ActionError error = new ActionError("error.login.invalid");
            errors.add("login", error);
            saveErrors(request, errors);
            return (mapping.findForward("loginFallado"));
        }
    }
}
