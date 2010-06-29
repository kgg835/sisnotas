package ccuni.java.sysNotas.action;


import ccuni.java.sysNotas.domain.dto.*;
import ccuni.java.sysNotas.logic.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import java.util.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ConsultarUsuariosAction extends Action {
    public ActionForward execute(ActionMapping mapping, ActionForm form, 
                                 HttpServletRequest request, 
                                 HttpServletResponse response) throws Exception {
        Log log = LogFactory.getLog(ConsultarUsuariosAction.class);
    	// get the session object
        HttpSession session = request.getSession();
        // get the user object
        UsuarioTO user = (UsuarioTO)session.getAttribute("usuario");
        UsuarioManager um = new UsuarioManager(getDataSource(request, "sisNotas"));
        ArrayList<UsuarioTO> list=null;
        list = um.selectAllUsuarios(user.getCodGrpUsr());
        log.info("Seleccionando los usuarios");
        request.setAttribute("listaUsuarios", list);
        log.info("Colocando la lista de usuarios en la sesion");
        return (mapping.findForward("success"));
    }
}
