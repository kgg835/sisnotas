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
import ccuni.java.sysNotas.domain.dto.UsuarioTO;
import ccuni.java.sysNotas.logic.CursoSeccionManager;
import ccuni.java.sysNotas.logic.PeriodoManager;
import ccuni.java.sysNotas.logic.SincronizaManager;

public class toTerminarPeriodoAction extends Action
{
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception
    {
        String accion = request.getParameter("accion");
        // si es el link a toTerminarPeriodo
        if (accion == null)
        {
            return (mapping.findForward("correcto"));
        }
        if (accion.equalsIgnoreCase("previoTerminar"))
        {
            ServletContext sc = this.getServlet().getServletContext();
            ParametrosTO parametrosTO = (ParametrosTO) sc.getAttribute("parametrosSistema");
            String perAcad = parametrosTO.getPeracad();
            CursoSeccionManager csm = new CursoSeccionManager(getDataSource(request, "sisNotas"));
            List cursosSinPromedio = csm.listaCursosSinPromedio();
            // PromedioManager pm = new
            // PromedioManager(getDataSource(request,"sisNotas"));
            // alNotas = pm.calcularPromedio(perAcad);
            request.setAttribute("promedioFaltantes", cursosSinPromedio);
            // sincronizo luego listo
            SincronizaManager sm = new SincronizaManager(getDataSource(request, "sisNotas"));
            sm.sincronizarMysqltoClipper(perAcad);
            return (mapping.findForward("previofin"));
        }
        if (accion.equalsIgnoreCase("cancel"))
        {
            return (mapping.findForward("cancel"));
        }
        if (accion.equalsIgnoreCase("terminar"))
        {
            ServletContext sc = this.getServlet().getServletContext();
            ParametrosTO parametrosTO = (ParametrosTO) sc.getAttribute("parametrosSistema");
            String perAcad = parametrosTO.getPeracad();
            String detalle = (String) request.getParameter("detalle");
            String username = ((UsuarioTO) request.getSession().getAttribute("usuario")).getUserName();
            PeriodoManager perAcadManager = new PeriodoManager(getDataSource(request, "sisNotas"));
            perAcadManager.finalizaPeriodo(sc, detalle, username);
            request.setAttribute("final", "fin");
        }
        return (mapping.findForward("fin"));
    }
}
