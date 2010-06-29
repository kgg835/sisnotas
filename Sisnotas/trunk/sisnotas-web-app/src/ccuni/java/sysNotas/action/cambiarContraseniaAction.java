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

import ccuni.java.sysNotas.domain.dto.UsuarioTO;
import ccuni.java.sysNotas.logic.UsuarioManager;

public class cambiarContraseniaAction extends Action{
	

	public ActionForward execute(   ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception{
		
		UsuarioManager um = new UsuarioManager(getDataSource(request, "sisNotas"));
		
		
		UsuarioTO user = null;
		user = (UsuarioTO)request.getSession().getAttribute("usuario");
		if(user.getPassword().equals((String)((DynaValidatorForm)form).get("password")))
		{
			if( ((String)((DynaValidatorForm)form).get("newpassword")).equals((String)((DynaValidatorForm)form).get("newpassword2")))
			{
				um.cambiarPasswordUsuario(user.getUserName(),(String)((DynaValidatorForm)form).get("newpassword"));
				user.setPassword((String)((DynaValidatorForm)form).get("newpassword"));
				request.getSession().setAttribute("usuario",user);
				return (mapping.findForward("correcto"));
	     	}
			else
			{    ActionErrors errors = new ActionErrors();
            ActionError error = new ActionError("error.cambiarPassword.invalidNewPassword");
            errors.add("cambiarPassword2",error);
            saveErrors(request,errors);
				return (mapping.findForward("incorrecto"));
			}	
	    }
		else
		{   ActionErrors errors = new ActionErrors();
        ActionError error = new ActionError("error.cambiarPassword.invalidPassword");
        errors.add("password",error);
        saveErrors(request,errors);
			return (mapping.findForward("incorrecto"));
		}
		
		
   
		
}
}
		