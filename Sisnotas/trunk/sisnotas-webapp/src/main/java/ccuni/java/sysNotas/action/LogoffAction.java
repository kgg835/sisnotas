package ccuni.java.sysNotas.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ccuni.java.sysNotas.domain.dto.UsuarioTO;

public class LogoffAction extends Action
{
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws IOException, ServletException
    {
        HttpSession session = request.getSession();
        UsuarioTO user = (UsuarioTO) session.getAttribute("usuario");
        session.invalidate();
        return (mapping.findForward("correcto"));
    }
}
