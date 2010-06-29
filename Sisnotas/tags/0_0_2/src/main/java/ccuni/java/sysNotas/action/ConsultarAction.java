package ccuni.java.sysNotas.action;

import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.DynaValidatorForm;

import ccuni.java.sysNotas.domain.dto.ParametrosTO;
import ccuni.java.sysNotas.domain.dto.PeriodoTO;
import ccuni.java.sysNotas.logic.ConsultaManager;
import ccuni.java.sysNotas.logic.ToConsultaManager;

public class ConsultarAction extends Action
{
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception
    {
        String codCur = null;
        String seccion = null;
        String perAcad = null;
        String perAcadH = null;
        ArrayList<String> alTp = new ArrayList<String>();
        ArrayList<String> alAl = new ArrayList<String>();
        ArrayList<String> alP = new ArrayList<String>();
        ArrayList<String> alC = new ArrayList<String>();
        ArrayList<String> alS = new ArrayList<String>();
        ArrayList<PeriodoTO> alPer = new ArrayList<PeriodoTO>();
        codCur = (String) ((DynaValidatorForm) form).get("codCur");
        seccion = (String) ((DynaValidatorForm) form).get("seccion");
        perAcadH = (String) ((DynaValidatorForm) form).get("ciclo");
        System.out.println(codCur);
        System.out.println(seccion);
        System.out.println(perAcadH);
        ServletContext sc = this.getServlet().getServletContext();
        ParametrosTO parametrosTO = (ParametrosTO) sc.getAttribute("parametrosSistema");
        perAcad = parametrosTO.getPeracad();
        ConsultaManager pm = new ConsultaManager(getDataSource(request, "sisNotas"));
        ToConsultaManager toConsultaManager = new ToConsultaManager(getDataSource(request, "sisNotas"));
        alPer = toConsultaManager.getAllPerAcad();
        if (perAcad.equals(perAcadH) && parametrosTO.getInicio_periodo() == 1)
        {
            alTp = pm.getAllTipoPrueba(codCur, seccion, perAcad);
            alAl = pm.getAllAlumno(codCur, seccion, perAcad);
            alP = pm.getAllPrueba(codCur, seccion, perAcad);
            alC = toConsultaManager.getAllCursos(perAcad);
            alS = toConsultaManager.getAllSecciones(perAcad, codCur);
        }
        else
        {
            alTp = pm.getAllTipoPruebaHist(codCur, seccion, perAcadH);
            alAl = pm.getAllAlumnoHist(codCur, seccion, perAcadH);
            alP = pm.getAllPruebaHist(codCur, seccion, perAcadH);
            alC = toConsultaManager.getAllCursosHist(perAcadH);
            alS = toConsultaManager.getAllSeccionesHist(perAcadH, codCur);
        }
        request.setAttribute("ciclos", alPer);
        request.setAttribute("cursoU", codCur);
        request.setAttribute("seccionU", seccion);
        request.setAttribute("cicloU", perAcadH);
        request.setAttribute("cursos", alC);
        request.setAttribute("secciones", alS);
        request.setAttribute("TipoPrueba", alTp);
        request.setAttribute("Alumno", alAl);
        request.setAttribute("Nota", alP);
        return (mapping.findForward("correcto"));
    }
}
