package ccuni.java.sysNotas.action;

import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ccuni.java.sysNotas.constantes.Constantes;
import ccuni.java.sysNotas.domain.dto.CursoTO;
import ccuni.java.sysNotas.domain.dto.CursoTipoPruebaTO;
import ccuni.java.sysNotas.domain.dto.ParametrosTO;
import ccuni.java.sysNotas.domain.dto.TipoPruebaTO;
import ccuni.java.sysNotas.domain.dto.UsuarioTO;
import ccuni.java.sysNotas.logic.CursoManager;
import ccuni.java.sysNotas.logic.CursoSeccionManager;
import ccuni.java.sysNotas.logic.ParametrosManager;
import ccuni.java.sysNotas.logic.PruebaManager;
import ccuni.java.sysNotas.logic.UsuarioManager;

public class ParametrosCursoAction extends Action
{
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception
    {
        Log log = LogFactory.getLog(ParametrosCursoAction.class);
        // get the session object
        HttpSession session = request.getSession();
        // get the user object
        UsuarioTO user = (UsuarioTO) session.getAttribute("usuario");
        String action = request.getParameter("action");
        if (action != null)
        {
            if (action.equals("verParametros"))
            {
                String codCurso = request.getParameter("codCur");
                String seccion = request.getParameter("secc");
                CursoManager cm = new CursoManager(getDataSource(request, "sisNotas"));
                CursoTO curso = null;
                if (codCurso != null && seccion != null)
                {
                    log.info("obtengo todos los parametros");
                    curso = cm.obtenerParametros(codCurso, seccion);
                }
                else
                {
                    CursoTO to = (CursoTO) session.getAttribute("paramCurso");
                    curso = cm.obtenerParametros(to.getCursoId(), to.getSeccion());
                }
                log.info("obtengo los examenes disponibles");
                session.setAttribute("paramCurso", curso);
                ServletContext sc = this.getServlet().getServletContext();
                ArrayList<TipoPruebaTO> examenes = new ArrayList<TipoPruebaTO>((ArrayList<TipoPruebaTO>) sc
                        .getAttribute("tiposDeExamen"));
                ArrayList<TipoPruebaTO> list = curso.getExamenes();
                for (int i = 0; i < list.size(); i++)
                {
                    if (examenes.contains(list.get(i)))
                    {
                        examenes.remove(list.get(i));
                    }
                }
                // se colocan los doentes registrados hasta este momento
                UsuarioManager um = new UsuarioManager(getDataSource(request, "sisNotas"));
                ArrayList docentes = um.selectAllDocentes(); // sc.getAttribute("docentes");
                request.setAttribute("docentes", docentes);
                session.setAttribute("examenesDisponibles", examenes);
                return (mapping.findForward("verParametros"));
            }
            else if (action.equals("addPrueba"))
            {
                log.info("solo redirecciono");
                return (mapping.findForward("addPrueba"));
            }
            else if (action.equals("eliminarTipoPrueba"))
            {
                String codTipPba = request.getParameter("tipPba");
                CursoManager cm = new CursoManager(getDataSource(request, "sisNotas"));
                CursoTO curso = (CursoTO) session.getAttribute("paramCurso");
                cm.eliminarTipoPrueba(curso.getCursoId(), curso.getSeccion(), codTipPba, user.getCodUsr());
                return (mapping.findForward("toVerParametros"));
            }
            else if (action.equals("aceptarParam"))
            {
                String codDoc = request.getParameter("cmbdocentes");
                log.info("si es que existe el combo docente");
                if (codDoc != null)
                {// actualizar el curso
                    CursoManager cm = new CursoManager(getDataSource(request, "sisNotas"));
                    CursoTO curso = (CursoTO) session.getAttribute("paramCurso");
                    cm.updateDocente(codDoc, curso.getCursoId(), curso.getSeccion());
                }
                log.info("elimino las variables de sesion asociadas al curso");
                session.removeAttribute("paramCurso");
                return (mapping.findForward("aceptarParam"));
            }
            else if (action.equals("editarDocente"))
            {
                log.info("colocar los docentes en el request y enviar");
                // ServletContext sc = this.getServlet().getServletContext();
                UsuarioManager um = new UsuarioManager(getDataSource(request, "sisNotas"));
                CursoTO curso = (CursoTO) session.getAttribute("paramCurso");
                curso.setDocenteResp(null);
                session.setAttribute("paramCurso", curso);
                ArrayList docentes = um.selectAllDocentes(); // sc.getAttribute("docentes");
                request.setAttribute("docentes", docentes);
                return (mapping.findForward("editarDocente"));
            }
            else if (action.equals("aceptarDocente"))
            {
                String codDoc = request.getParameter("cmbdocentes");
                if (codDoc != null)
                {
                    if (!codDoc.equals(""))
                    {
                        CursoManager cm = new CursoManager(getDataSource(request, "sisNotas"));
                        CursoTO curso = (CursoTO) session.getAttribute("paramCurso");
                        cm.updateDocente(codDoc, curso.getCursoId(), curso.getSeccion());
                        return (mapping.findForward("toVerParametros"));
                    }
                }
            }
            else if (action.equals("confirmDefParam"))
            {
                return (mapping.findForward("confirmDefParam"));
            }
            else if (action.equals("aceptarDefParam"))
            {
                log.info("cambiar los parametros");
                ParametrosManager pm = new ParametrosManager(getDataSource(request, "sisNotas"));
                ParametrosTO to = pm.getParametros();
                to.setParametros_cursos(Constantes.PARAM_DEF);
                pm.updateParametros(to);
                log.info("actualizar el contexto");
                ServletContext sc = this.getServlet().getServletContext();
                sc.setAttribute("parametrosSistema", to);
                log.info("vuelvo al home");
                return (mapping.findForward("aceptarDefParam"));
            }
            else if (action.equals("cancelDefParam"))
            {
                log.info("vuelvo al lista cursos parametros");
                return (mapping.findForward("cancelDefParam"));
            }
            else if (action.equals("addExamen"))
            {
                String codEx = request.getParameter("examen");
                log.info("add examen");
                ServletContext sc = this.getServlet().getServletContext();
                ParametrosTO pto = (ParametrosTO) sc.getAttribute("parametrosSistema");
                CursoManager cm = new CursoManager(getDataSource(request, "sisNotas"));
                log.info("armo el to");
                TipoPruebaTO to = new TipoPruebaTO();
                to.setCodTipPba(codEx);
                CursoTipoPruebaTO ctp = new CursoTipoPruebaTO();
                ctp.setLogdel(Constantes.ACTIVO);
                to.setCursoTipoPrueba(ctp);
                CursoTO curso = (CursoTO) session.getAttribute("paramCurso");
                log.info("busco si existe en la bd");
                TipoPruebaTO to2 = cm.find(curso.getCursoId(), curso.getSeccion(), to);
                if (to2 == null)
                {
                    log.info("inserto");
                    cm.addTipoPrueba(curso.getCursoId(), curso.getSeccion(), to, user.getCodUsr(), pto.getPeracad());
                }
                else if (to2.getCursoTipoPrueba().getLogdel() == 0)
                {
                    log.info("solo activo el tipo prueba");
                    cm.updateTipoPrueba(curso.getCursoId(), curso.getSeccion(), to, user.getCodUsr(), pto.getPeracad());
                }
                log.info("vuelve a parametroscurso");
                return (mapping.findForward("toVerParametros"));
            }
            else if (action.equals("definidosParametrosCurso"))
            {
                // redirecciono a una pagina de confirmacion
                return (mapping.findForward("confirmDefParamCurso"));
            }
            else if (action.equals("aceptarDefinidosParametrosCurso"))
            {
                // actualiza el curso
                CursoManager cm = new CursoManager(getDataSource(request, "sisNotas"));
                CursoTO to = (CursoTO) session.getAttribute("paramCurso");
                cm.actualizarEstado(to.getCursoId(), to.getSeccion(), 1);
                return (mapping.findForward("toVerParametros"));
            }
            else if (action.equals("cancelDefParamCurso"))
            {
                return (mapping.findForward("toVerParametros"));
            }
            else if (action.equals("aceptarParamCurso"))
            {
                log.info("elimino las variables de sesion asociadas al curso");
                session.removeAttribute("paramCurso");
                return (mapping.findForward("aceptarParamCurso"));
            }
            else if (action.equals("deleteCurso"))
            {
                String codCurso = request.getParameter("codCur");
                String seccion = request.getParameter("secc");
                CursoSeccionManager csm = new CursoSeccionManager(getDataSource(request, "sisNotas"));
                csm.eliminarCursoSeccion(codCurso, seccion);
                // tambien eliminar las parcticas que se hayan ingresado
                // se mostrarà una ventana de confirmacion javascript
                PruebaManager pm = new PruebaManager(getDataSource(request, "sisNotas"));
                pm.eliminaPruebasCursoSeccion(codCurso, seccion);
                return (mapping.findForward("deleteCurso"));
            }
        }
        else
        {
            return (mapping.findForward("aceptarParam"));
        }
        return (mapping.findForward("aceptarParam"));
    }
}
