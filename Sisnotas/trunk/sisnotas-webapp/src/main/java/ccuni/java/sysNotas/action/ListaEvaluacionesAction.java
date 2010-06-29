package ccuni.java.sysNotas.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionForm;

import ccuni.java.sysNotas.domain.dto.CursoTO;
import ccuni.java.sysNotas.domain.dto.PruebaTO;
import ccuni.java.sysNotas.logic.CursoManager;
import ccuni.java.sysNotas.logic.PruebaManager;

public class ListaEvaluacionesAction extends Action
{
    /**
     * This is the main action called from the Struts framework.
     * 
     * @param mapping
     *            The ActionMapping used to select this instance.
     * @param form
     *            The optional ActionForm bean for this request.
     * @param request
     *            The HTTP Request we are processing.
     * @param response
     *            The HTTP Response we are processing.
     */
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws IOException, ServletException, Exception
    {
        DynaActionForm dyna = (DynaActionForm) form;
        String accion = (String) dyna.get("accion");
        String accion2 = (String) request.getParameter("accion2");
        // if((accion2 == null)|(action.equals("")))
        if (accion2 != null)
            if (!accion2.equals(""))
                accion = accion2; // replace vien cursoListado.jsp
        if (accion.equalsIgnoreCase("listar"))
        {
            CursoTO curso = new CursoTO();
            curso.setCursoId((String) dyna.get("cursoId"));
            curso.setPerAcad((String) dyna.get("perAcad"));
            curso.setSeccion((String) dyna.get("seccion"));
            curso.setNombre((String) dyna.get("nombreCurso"));
            PruebaManager p = new PruebaManager(getDataSource(request, "sisNotas"));
            curso = p.obtienePruebasxCurso(curso);
            request.setAttribute("curso", curso);
            return (mapping.findForward("listar"));
        }
        if (accion.equalsIgnoreCase("view"))
        {
            ServletContext sc = this.getServlet().getServletContext();
            ((DynaActionForm) form).set("estado", (String) dyna.get("auxEstado"));
            request.setAttribute("auxiEstado", (String) dyna.get("auxEstado"));
            ((DynaActionForm) form).set("estados", (ArrayList) sc.getAttribute("estados"));
            return (mapping.findForward("editar"));
        }
        if (accion.equalsIgnoreCase("cancel"))
        {
            return (mapping.findForward("cancel"));
        }
        if (accion.equalsIgnoreCase("save"))
        {
            PruebaTO prueba = new PruebaTO();
            prueba.setCodCur((String) dyna.get("cursoId"));
            prueba.setSeccion((String) dyna.get("seccion"));
            prueba.setPerAcad((String) dyna.get("perAcad"));
            prueba.setNumtippba(Integer.parseInt((String) dyna.get("num")));
            prueba.setCodtippba((String) dyna.get("tipo"));
            ServletContext sc = this.getServlet().getServletContext();
            prueba.setAccionId((Integer) ((HashMap) sc.getAttribute("estadoMap")).get((String) dyna.get("auxEstado")));
            prueba.setNuevoEstado((Integer) ((HashMap) sc.getAttribute("estadoMap")).get((String) dyna.get("estado")));
            PruebaManager pm = new PruebaManager(getDataSource(request, "sisNotas"));
            CursoManager cm = new CursoManager(getDataSource(request, "sisNotas"));
            /**
             * si es q ya se registro la prueba no pueden registrar uan prueba
             * dos veces una misma prueba para eso este pedazo de codigo
             */
            if (pm.verificaCambioEstado(prueba))
            {// si es posible el cambio de estado
                prueba.setAccionId(prueba.getNuevoEstado());
                pm.actualizaEstado(prueba);
                cm.verificaPruebasCurso(prueba.getCodCur(), prueba.getSeccion());
            }
            else
            {
                ActionErrors errors = new ActionErrors();
                errors.add("errorCambioEstado", new ActionError("error.cambio.estado"));
                saveErrors(request, errors);
                // ((DynaActionForm)form).set("estado",(String)dyna.get("estado"));
                return (mapping.findForward("failure"));// tiene q volver a
                                                        // editar
            }
        }
        return (mapping.findForward("success"));
    }
}