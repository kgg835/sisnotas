package ccuni.java.sysNotas.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ccuni.java.sysNotas.domain.dto.CursoTO;
import ccuni.java.sysNotas.logic.CursoManager;
import ccuni.java.sysNotas.logic.CursoSeccionManager;

public class ListaCursosParametrosAction extends Action
{
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception
    {
        HttpSession session = request.getSession();
        Log log = LogFactory.getLog(ListaCursosParametrosAction.class);
        /*
         * ServletContext sc = this.getServlet().getServletContext();
         * ParametrosTO pto = (ParametrosTO)
         * sc.getAttribute("parametrosSistema"); ArrayList<CursoTO> cursos; if
         * (pto.getMigracion_cursos() == Constantes.NO_MIGRADO) { log.info("aun
         * no se migan los cursos, se migrará"); // se obtienen de la bd clipper
         * CursoSeccionManager csm = new CursoSeccionManager(); cursos =
         * csm.obtenerCursosSeccion(pto.getPeracad()); // se escriben a mysql
         * CursoSeccionManager csm2 = new CursoSeccionManager(getDataSource(
         * request, "sisNotas")); csm2.migrarCursosSeccion(cursos,
         * pto.getPeracad());
         * 
         * log.info("se actualializa el parametro en la bd");
         * pto.setMigracion_cursos(Constantes.MIGRADO); ParametrosManager pm =
         * new ParametrosManager(getDataSource(request, "sisNotas"));
         * pm.updateParametros(pto); log.info("actualizo en el contexto");
         * sc.setAttribute("parametrosSistema", pto);
         * 
         * log.info("aun no tienen los parametros"); for (int i = 0; i <
         * cursos.size(); i++) { cursos.get(i).setParamExist(false); }
         * log.info("se pone los cursos en la session");
         * session.setAttribute("cursos", cursos); return
         * (mapping.findForward("success")); } else {
         */
        log.info("se lee de mysql");
        CursoSeccionManager csm = new CursoSeccionManager(getDataSource(request, "sisNotas"));
        List cursos = csm.obtenerCursosSeccion();
        CursoManager cm = new CursoManager(getDataSource(request, "sisNotas"));
        log.info(" determino si tienen los parameros establecidos");
        boolean todosDefinidos = true;
        for (int i = 0; i < cursos.size(); i++)
        {
            boolean v = cm
                    .existeDocente(((CursoTO) cursos.get(i)).getCursoId(), ((CursoTO) cursos.get(i)).getSeccion());
            ((CursoTO) cursos.get(i)).setParamExist(v);
            String msg;
            if (v == false && todosDefinidos)
            {
                msg = "Existen cursos que aun no tienen definido el docente responsable, debe definirlo para todos los cursos antes "
                        + "de empezar con el registro de notas";
                request.setAttribute("msg1", msg);
                todosDefinidos = false;
            }
        }
        log.info("si todos los cursos tienen los parametros definidos");
        if (todosDefinidos && cursos.size() > 0)
        {
            String msg = "Todos los cursos tienen definido el docente responsable, acabo de definir los docentes responsables para todos los cursos?";
            request.setAttribute("msg2", msg);
        }
        log.info("se pone los cursos en la session");
        session.setAttribute("cursos", cursos);
        return (mapping.findForward("success"));
    }
}
