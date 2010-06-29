package ccuni.java.sysNotas.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ccuni.java.sysNotas.domain.dto.EstadoPrueba;
import ccuni.java.sysNotas.domain.dto.PruebaTO;

public class VerificaEvaluacionAction extends Action
{
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception
    {
        String descripcion = request.getParameter("label");
        String draw = request.getParameter("draw");
        System.out.println(draw);
        int accionId = Integer.parseInt(request.getParameter("accionId"));
        String accion = request.getParameter("accion");
        String tipo = request.getParameter("tipo");
        int num = Integer.parseInt(request.getParameter("num"));
        String cursoId = request.getParameter("cursoId");
        String seccion = request.getParameter("seccion");
        String perAcad = request.getParameter("perAcad");
        // String nombreCurso=request.getParameter("nombreCurso");
        EstadoPrueba estado = new EstadoPrueba();
        estado.setCodigo(accionId);
        estado.setAccion(accion);
        estado.setDescripcion(descripcion);
        PruebaTO prueba = new PruebaTO();
        prueba.setCodCur(cursoId);
        prueba.setSeccion(seccion);
        prueba.setPerAcad(perAcad);
        prueba.setCodtippba(tipo);
        prueba.setNumtippba(num);
        prueba.setEstado(estado);
        request.setAttribute("prueba", prueba);
        request.setAttribute("greeting", descripcion);
        request.setAttribute("draw", draw);
        return (mapping.findForward("default"));
    }
}