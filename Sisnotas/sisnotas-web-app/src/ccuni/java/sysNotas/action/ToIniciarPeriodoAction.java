package ccuni.java.sysNotas.action;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ccuni.java.sysNotas.domain.dto.ParametrosTO;

public class ToIniciarPeriodoAction extends Action{

	public ActionForward execute(   ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
{  String perAcad = null;
   String p1 = null;
   String p2 = null;
   String q1 = null;
   String p3 = null;
   String q2 = null;
   String q3 = null;
		
		ServletContext sc = this.getServlet().getServletContext();
		ParametrosTO parametrosTO = (ParametrosTO)sc.getAttribute("parametrosSistema");
		 perAcad = parametrosTO.getPeracad();
		 p1 = perAcad.substring(2);
		 p2 = perAcad.substring(1,2);
		 p3 = perAcad.substring(0,1);
		 if(p1.equals("1")){
			 q1 = "2";
			 q2 = p2;
			 q3 = p3;
			 
		 }
		 if(p1.equals("2")){
			 q1 = "3";
			 q2 = p2;
			 q3 = p3;
		 }
		 if(p1.equals("3")){
			 q1 = "1";
			 if(p2.equals("0")){
				 q2 = "1";
				 q3 = p3;
			 }
			 if(p2.equals("1")){
				 q2 = "2";
				 q3 = p3;
			 }
			 if(p2.equals("2")){
				 q2 = "3";
				 q3 = p3;
			 }
			 if(p2.equals("3")){
				 q2 = "4";
				 q3 = p3;
			 }
			 if(p2.equals("4")){
				 q2 = "5";
				 q3 = p3;
			 }
			 if(p2.equals("5")){
				 q2 = "6";
				 q3 = p3;
			 }
			 if(p2.equals("6")){
				 q2 = "7";
				 q3 = p3;
			 }
			 if(p2.equals("7")){
				 q2 = "8";
				 q3 = p3;
			 }
			 if(p2.equals("8")){
				 q2 = "9";
				 q3 = p3;
			 }
			 if(p2.equals("9")){
				 q2 = "0";
				 if(p3.equals("0")){
					 q3 ="1";
				 }
				 if(p3.equals("1")){
					 q3 ="2";
				 }
				 if(p3.equals("2")){
					 q3 ="3";
				 }
				 if(p3.equals("3")){
					 q3 ="4";
				 }
				 if(p3.equals("4")){
					 q3 ="5";
				 }
				 if(p3.equals("5")){
					 q3 ="6";
				 }
				 if(p3.equals("6")){
					 q3 ="7";
				 }
				 if(p3.equals("7")){
					 q3 ="8";
				 }
				 if(p3.equals("8")){
					 q3 ="9";
				 }
				 
			 }
			 
		 }
		 
		 
		 
		
		String newPerAcad = q3 + q2 + q1;
		String nombrePerAcad = "20"+q3+q2+"-"+q1;
		System.out.println(newPerAcad);
		System.out.println(nombrePerAcad);
		
		request.setAttribute("newPerAcad",newPerAcad);
		request.setAttribute("nombrePerAcad",nombrePerAcad);
		 return (mapping.findForward("correcto"));
}
}
