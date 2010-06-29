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
import ccuni.java.sysNotas.domain.dto.PeriodoTO;
import ccuni.java.sysNotas.logic.ConsultaManager;
import ccuni.java.sysNotas.logic.ToConsultaManager;

public class toConsultaAction extends Action{

	public ActionForward execute(   ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
{  
		ArrayList<String> alC = new ArrayList<String>();
		ArrayList<PeriodoTO> alP = new ArrayList<PeriodoTO>();
		String perAcad = null;
		
		ServletContext sc = this.getServlet().getServletContext();
		ParametrosTO parametrosTO = (ParametrosTO)sc.getAttribute("parametrosSistema");
		 perAcad = parametrosTO.getPeracad();
		
		ToConsultaManager toConsultaManager = new ToConsultaManager(getDataSource(request,"sisNotas"));
		if(parametrosTO.getInicio_periodo() == 1){
		alC = toConsultaManager.getAllCursos(perAcad);
		}
		if(parametrosTO.getInicio_periodo() == 0){
			alC = toConsultaManager.getAllCursosHist(perAcad);
		}
		alP = toConsultaManager.getAllPerAcad();
		
		request.setAttribute("cursos",alC);
		request.setAttribute("ciclos",alP);
		request.setAttribute("cicloU",perAcad);
	
		
		return (mapping.findForward("correcto"));
}
}
