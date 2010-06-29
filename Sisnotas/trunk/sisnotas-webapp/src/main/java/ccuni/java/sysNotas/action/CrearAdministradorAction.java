package ccuni.java.sysNotas.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ccuni.java.sysNotas.actionForm.AdministradorForm;
import ccuni.java.sysNotas.constantes.Constantes;
import ccuni.java.sysNotas.domain.dto.UsuarioTO;
import ccuni.java.sysNotas.logic.UsuarioManager;

public class CrearAdministradorAction extends Action
{
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception
    {
        Log log = LogFactory.getLog(CrearAdministradorAction.class);
        if (((AdministradorForm) form).getPassword().equals(((AdministradorForm) form).getPassword2()))
        {
            HttpSession session = request.getSession();
            // objeto user de la sesion
            UsuarioTO user = (UsuarioTO) session.getAttribute("usuario");
            // sesion
            UsuarioTO admin = new UsuarioTO();
            UsuarioManager userManager = new UsuarioManager(getDataSource(request, "sisNotas"));
            admin.setCodGrpUsr(Constantes.ADMINISTRADORES);
            admin.setUserName(((AdministradorForm) form).getUsername());
            admin.setPassword(((AdministradorForm) form).getPassword());
            admin.setNombres(((AdministradorForm) form).getNombres());
            admin.setApellidos(((AdministradorForm) form).getApellidos());
            admin.validate();
            if (userManager.userNameExist(admin.getUserName()))
            {
                log.error("El usuario ya existe");
                ActionErrors errors = new ActionErrors();
                errors.add("username", new ActionError("error.regAdmin.username.userExist"));
                saveErrors(request, errors);
                return (mapping.findForward("fallo"));
            }
            // registro al nuevo administrador
            userManager.registrarUsuario(admin, user.getCodUsr());
            request.setAttribute("admin", admin);
            return (mapping.findForward("success"));
        }
        else
        {
            ActionErrors errors = new ActionErrors();
            errors.add("password", new ActionError("error.password.confirmPass"));
            log.error("Las contraseñas no coinciden");
            saveErrors(request, errors);
            return (mapping.findForward("fallo"));
        }
    }
}
