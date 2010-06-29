package ccuni.java.sysNotas.action;

import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ccuni.java.sysNotas.constantes.Constantes;
import ccuni.java.sysNotas.domain.dto.CursoTO;
import ccuni.java.sysNotas.domain.dto.ParametrosTO;
import ccuni.java.sysNotas.domain.dto.UsuarioTO;
import ccuni.java.sysNotas.logic.CursoManager;

public class ListaCursosParametrosxDocenteAction extends Action
{
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception
    {
        UsuarioTO usuario = (UsuarioTO) request.getSession().getAttribute("usuario");
        ServletContext sc = this.getServlet().getServletContext();
        ParametrosTO parametros = (ParametrosTO) sc.getAttribute("parametrosSistema");
        if (parametros.getParametros_cursos() == Constantes.PARAM_NO_DEF)
        {
            request.getSession().setAttribute("PARAM", Constantes.PARAM_NO_DEF);
            return (mapping.findForward("success"));
        }
        // si estan definidos
        ArrayList<CursoTO> cursos;
        CursoManager cm = new CursoManager(getDataSource(request, "sisNotas"));
        cursos = cm.obtieneCursos(usuario, parametros.getPeracad());
        boolean v;
        for (int i = 0; i < cursos.size(); i++)
        {
            v = cm.existenParametrosCalificacion(cursos.get(i).getCursoId(), cursos.get(i).getSeccion());
            cursos.get(i).setParamExist(v);
        }
        // determino si tienen los parametros
        request.setAttribute("cursos", cursos);
        return (mapping.findForward("success"));
    }
}
