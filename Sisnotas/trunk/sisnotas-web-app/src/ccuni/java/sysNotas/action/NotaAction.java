package ccuni.java.sysNotas.action;

import java.io.IOException;
import java.util.ArrayList;


import javax.servlet.RequestDispatcher;
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
import org.apache.struts.util.LabelValueBean;
  


import ccuni.java.sysNotas.domain.dto.EstadoPrueba;
import ccuni.java.sysNotas.domain.dto.EvaluacionTO;
import ccuni.java.sysNotas.domain.dto.PruebaTO;
import ccuni.java.sysNotas.domain.dto.UsuarioTO;
import ccuni.java.sysNotas.logic.CursoManager;
import ccuni.java.sysNotas.logic.EvaluacionManager;
import ccuni.java.sysNotas.logic.PruebaManager;


public class NotaAction extends Action{


 
    public ActionForward execute(ActionMapping mapping, ActionForm form, 
                                 HttpServletRequest request, 
                                 HttpServletResponse response) throws IOException, 
                                                                      ServletException, 
                                                                      Exception 
    {
        
    	
    	
    	DynaActionForm dyna = (DynaActionForm)form;
    	
    	
    	
    	
    	String accion=(String)dyna.get("accion");
    	String cursoId=(String)dyna.get("cursoId");
    	String seccion=(String)dyna.get("seccion");
    	String perAcad=(String)dyna.get("perAcad");
    	String tip=(String)dyna.get("tipo");
    	String num=(String)dyna.get("num");
    	String accionId=(String)dyna.get("accionId");
    	String descripcion=(String)dyna.get("descripcion");
    	
    	
    	PruebaTO prueba = new PruebaTO();    
    	prueba.setCodCur(cursoId);
    	prueba.setSeccion(seccion);
    	prueba.setPerAcad(perAcad);
    	prueba.setCodtippba(tip);
    	prueba.setNumtippba(Integer.parseInt(num));
    	EstadoPrueba e=new EstadoPrueba();
    	e.setCodigo(Integer.parseInt(accionId));
    	e.setAccion(accion);
    	e.setDescripcion(descripcion);
    	prueba.setEstado(e);
    	prueba.setAccionId(Integer.parseInt(accionId));
    
    	
    	String accion2 = (String) request.getParameter("accion2");	// viene de lsitado evaluaciones.jsp
		if(accion2!=null)
			if(!accion2.equals(""))
				accion = accion2;	// replace
//    	lista las notas del curso y la prueba respectiva
    	if (accion.equalsIgnoreCase("listar")) {
			ArrayList<String> listAlumno = new ArrayList<String>();
			ArrayList<String> listNota = new ArrayList<String>();
			ArrayList<String> listNotaRec = new ArrayList<String>();
			ArrayList<String> condicion = new ArrayList<String>();
			ArrayList<String> listNombres = new ArrayList<String>();

			EvaluacionManager evalManager = new EvaluacionManager(
					getDataSource(request, "sisNotas"));
			evalManager.obtieneEvaluaciones(prueba, Integer.parseInt(accionId),
					listAlumno,listNombres, listNota, listNotaRec,condicion);

			/**el priemr registro cuandop va a la vase de datos clipper si es q no hay alumnos en l aseccion*/
			ActionErrors errors = new ActionErrors();
			if (listAlumno.size() == 0) {
				errors.add("errorNota", new ActionError("error.nota.action"));
				saveErrors(request, errors);
				return (mapping.findForward("failed"));
			}
			/** fin  */
			
			request.setAttribute("cantidad", listAlumno.size() - 1);

			dyna.set("codigos", listAlumno.toArray(new String[0]));
			dyna.set("notas", listNota.toArray(new String[0]));
			dyna.set("notasRec", listNotaRec.toArray(new String[0]));
			dyna.set("nombres", listNombres.toArray(new String[0]));
			
			/**PARA DIBUJAR LA NOTA RECLAMO*/
			if(accionId.equalsIgnoreCase("5")||accionId.equalsIgnoreCase("6")||accionId.equalsIgnoreCase("7"))
				request.setAttribute("condicion",condicion);
			/**FIN*/
			
			String media=(String)request.getParameter("media");
			
			/**manda version para imprimir*/
			if(media!=null)
				if(!media.equals(""))
					if(media.equals("print")){
						request.setAttribute("logrec",(String)request.getParameter("logrec"));
						return mapping.findForward("print");
					}
			/** fin*/
			
			

			return mapping.findForward("new");
			
			
		}	
    	
    	
    	
    	if(accion.equalsIgnoreCase("save") || accion.equalsIgnoreCase("update")){   		
    		/**si es q ya se registro la prueba
    		 * dos usuarios no pueden registarr un misma prueba
    		 * para eso este pedazo de codigo
    		*/
    		if(accion.equalsIgnoreCase("save")){
    			EvaluacionManager em=new EvaluacionManager(getDataSource(request,"sisNotas"));
	    		if(em.verificaRegistro(prueba)){//entra si existe 
	    			ActionErrors errors=new ActionErrors();
	    			errors.add("errorIntegridad",new ActionError("error.registro.integridad"));
	    			saveErrors(request,errors);
	    			return (mapping.findForward("success"));
	    		}
    		}
    		/**fin de validacion*/
    		
    		EvaluacionManager em=new EvaluacionManager(getDataSource(request,"sisNotas"));
    		CursoManager cm=new CursoManager(getDataSource(request,"sisNotas"));
    		EvaluacionTO evaluacion = new EvaluacionTO();
		
			evaluacion.setCodCur(cursoId);
			evaluacion.setSeccion(seccion);
			evaluacion.setPeracad(perAcad);
			evaluacion.setCodTipPba(tip);
			evaluacion.setNumtippba(Integer.parseInt(num));
			String username=((UsuarioTO) request.getSession().getAttribute("usuario")).getUserName();

		//setear valores de prueba  key
			em.registraEvaluaciones(username,evaluacion,prueba,(String[])dyna.get("codigos"),(String[])dyna.get("nombres"),(String[])dyna.get("notas"),(String[])dyna.get("notasRec"));
			//actualiza estado del curso y calcula promedio del curso si no hay pruebas pendientes
			cm.verificaPruebasCurso(prueba.getCodCur(),prueba.getSeccion());
			
		
    		
    		
    	}
    	if (accion.equalsIgnoreCase("cancel")){
    		
    		/*ServletContext sc = this.getServlet().getServletContext();
    		RequestDispatcher dispatcher = sc.getRequestDispatcher("/paginas/home.jsp");
    		dispatcher.forward(request, response);*/


    		return(mapping.findForward("cancel"));
    		
    	}

    	return (mapping.findForward("success"));
    	
    	
           	
    
       
    }

	
	
}