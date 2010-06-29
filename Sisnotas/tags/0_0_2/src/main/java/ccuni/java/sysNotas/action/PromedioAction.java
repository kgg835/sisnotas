package ccuni.java.sysNotas.action;

import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ccuni.java.sysNotas.domain.dto.ParametrosTO;
import ccuni.java.sysNotas.logic.PromedioManager;

public class PromedioAction extends Action
{
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception
    {
        ArrayList<String> al = new ArrayList<String>();
        ServletContext sc = this.getServlet().getServletContext();
        ParametrosTO parametrosTO = (ParametrosTO) sc.getAttribute("parametrosSistema");
        PromedioManager pm = new PromedioManager(getDataSource(request, "sisNotas"));
        al = pm.calcularPromedio(parametrosTO.getPeracad());
        request.setAttribute("notas", al);
        return (mapping.findForward("correcto"));
    }
}
