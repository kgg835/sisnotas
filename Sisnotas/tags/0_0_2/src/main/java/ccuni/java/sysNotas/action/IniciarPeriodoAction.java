package ccuni.java.sysNotas.action;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionForm;

import ccuni.java.sysNotas.domain.dto.ParametrosTO;
import ccuni.java.sysNotas.logic.ParametrosManager;
import ccuni.java.sysNotas.logic.PeriodoManager;

public class IniciarPeriodoAction extends Action
{
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception
    {
        String perAcad = null;
        String nombrePerAcad = null;
        perAcad = (String) ((DynaActionForm) form).get("newPerAcad");
        nombrePerAcad = (String) ((DynaActionForm) form).get("nombrePerAcad");
        PeriodoManager pm = new PeriodoManager(getDataSource(request, "sisNotas"));
        if (pm.PeriodoExiste(perAcad))
        {
            String message = "Debe ingresar un nuevo periodo académico";
            request.setAttribute("message", message);
            return (mapping.findForward("incorrecto"));
        }
        else
        {
            ServletContext sc = this.getServlet().getServletContext();
            ParametrosTO to = null;
            ParametrosManager pm2 = new ParametrosManager(getDataSource(request, "sisNotas"));
            to = pm2.getParametros();
            to.setPeracad(perAcad);
            to.setInicio_periodo(1);
            to.setNombre_periodo(nombrePerAcad);
            pm2.updateParametros(to);
            sc.setAttribute("parametrosSistema", to);
            String message = "El inicio del periodo académico se realizò con èxito";
            request.setAttribute("message", message);
            return (mapping.findForward("correcto"));
        }
    }
}
