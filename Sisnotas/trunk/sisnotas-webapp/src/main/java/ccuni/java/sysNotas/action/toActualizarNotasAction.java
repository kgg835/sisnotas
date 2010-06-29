package ccuni.java.sysNotas.action;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ccuni.java.sysNotas.domain.dto.ParametrosTO;
import ccuni.java.sysNotas.logic.SincronizaManager;

public class toActualizarNotasAction extends Action
{
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception
    {
        List listaFaltantes = null;
        String param = (String) request.getParameter("parametro");
        if ("inicio".equals(param))
        {
            return (mapping.findForward("inicio"));
        }
        else
        {
            ServletContext sc = this.getServlet().getServletContext();
            SincronizaManager sm = new SincronizaManager(getDataSource(request, "sisNotas"));
            ParametrosTO parametros = (ParametrosTO) sc.getAttribute("parametrosSistema");
            listaFaltantes = sm.sincronizarMysqltoClipper(parametros.getPeracad());
            request.setAttribute("listaFaltantes", listaFaltantes);
            return (mapping.findForward("correcto"));
        }
    }
}
