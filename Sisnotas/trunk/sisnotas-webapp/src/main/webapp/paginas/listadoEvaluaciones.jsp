<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@ page contentType="text/html;charset=windows-1252"%>
<%@ include file="/taglibs.jsp"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="ccuni.java.sysNotas.domain.dto.*"%>
<html>

<head>
<meta http-equiv="Content-Type"
	content="text/html; charset=windows-1252" />
 <title>listado de Evaluaciones</title>
     <script language="JavaScript" type="text/javascript">
    var req;
    var which;

 function submitData(nombreCurso,accionId,accion,label,draw,cursoId,seccion,perAcad,tipo,num) {
  
   
       retrieveURL("verificaEvaluacion.do?nombreCurso="+nombreCurso+"&accionId="+accionId+"&accion="+accion+"&label=" + escape(label)+"&draw="+escape(draw)+"&cursoId="+cursoId+"&seccion="+seccion+"&perAcad="+perAcad+"&tipo="+tipo+"&num="+num);


  }

  function retrieveURL(url) {
    if (window.XMLHttpRequest) { // Non-IE browsers
      req = new XMLHttpRequest();
      req.onreadystatechange = processStateChange;
      try {
        req.open("GET", url, true);
      } catch (e) {
        alert(e);
      }
      req.send(null);
    } else if (window.ActiveXObject) { // IE
      req = new ActiveXObject("Microsoft.XMLHTTP");
      if (req) {
        req.onreadystatechange = processStateChange;
        req.open("GET", url, true);
        req.send();
      }
    }
  }

  function processStateChange() {
    if (req.readyState == 4) { // Complete
      if (req.status == 200) { // OK response
        document.getElementById("contenido").innerHTML = req.responseText;
      } else {
        alert("Problem: " + req.statusText);
      }
    }
  }

  
  </script>
 <link href="style/style.css" rel="stylesheet" type="text/css" />
</head>
<body>
<div id="header">
<%@ include file="../header.jsp"%>
</div>
<div id="navcontainer">
<%@ include file="listamenu.jsp"%>
</div>
<div id="center">
<div id="centrado" style="margin-left:30px; text-align: center;">

<h2><c:out value="${curso.nombre}"/><br><c:out value="${curso.cursoId} - ${curso.seccion}"/></h2>


<c:if test="${empty curso.pruebas}">
<br>
No hay pruebas definidas para este curso
</c:if>
<html:errors property="errorNota"/>
<c:if test="${ not empty curso.pruebas}">
<html:form action="/nota.do" method="get">

	<input type="hidden" name="cursoId"/>
	<input type="hidden" name="seccion"/>
	<input type="hidden" name="perAcad"/>
	<input type="hidden" name="nombreCurso"/>
	<input type="hidden" name="tipo" />
	<input type="hidden" name="num" />
	<input type="hidden" name="action" />
	<input type="hidden" name="draw" value="0" />
	

	<logic:present name="curso">
	    <table >
	    <caption ></caption>
	    <thead>
	    	<tr>	    		
	    		<th >Prueba</th>	    		
	    		<th >Estado de la prueba</th>
	    			
				<c:if test="${usuario.codGrpUsr =='1' or usuario.codGrpUsr =='2'}">  								    
				    <th></th>
				</c:if>	
				
				
	    	</tr>
	    <thead>
		<tbody>
		<c:forEach var="row" items="${curso.pruebas}">
		   <tr>
		   		<td>
		 	 
		  		<c:if test="${usuario.codGrpUsr =='3' or usuario.codGrpUsr =='1'}">		  		
					<input type="radio" name="opcion" 
						onclick="submitData('<c:out value='${curso.nombre}'/>','<c:out value='${row.estado.codigo}'/>','<c:out value='${row.estado.accion}'/>','<c:out value='${row.estado.descripcion}' />','<c:out value='${row.estado.draw}' />','<c:out value="${row.codCur}"/>','<c:out value="${row.seccion}"/>','<c:out value="${row.perAcad}"/>','<c:out value="${row.codtippba}"/>','<c:out value="${row.numtippba}"/>');"
						value='<c:out value='${row.numtippba}'/>'  />
				
				</c:if>
				
				
				<c:choose>
			    <c:when test="${row.codtippba=='EF' or row.codtippba=='ES' or row.codtippba=='EP' }">
			     <c:out value='${row.codtippba}' /> 
			    </c:when>
			    <c:otherwise>
			    <c:out value='${row.codtippba} ${row.numtippba}' /> 
			    </c:otherwise>			  
			    </c:choose>
			    	
				</td>
				<td >
					<c:out value='${row.estado.descripcion}' />
				</td>
				<td >
				<c:if test="${usuario.codGrpUsr =='1' or usuario.codGrpUsr =='2'}">  			    
					<html:link action="listaEvaluaciones.do?accion=view&auxEstado=${row.estado.descripcion}&num=${row.numtippba}&tipo=${row.codtippba}" >editar</html:link>									 
				</c:if>				  
				<c:if test="${row.estado.codigo=='4' or row.estado.codigo=='7'}">								    
					<html:button styleClass="button" property="envia" onclick="javascript:location.href='nota.do?accion2=listar&media=print&logrec=${row.logRec}&accionId=${row.estado.codigo}&nombreCurso=${curso.nombre}&accion=${row.estado.accion}&descripcion=${row.estado.descripcion}&cursoId=${curso.cursoId}&seccion=${curso.seccion}&perAcad=${curso.perAcad}&tipo=${row.codtippba}&num=${row.numtippba}';">
					ver</html:button>										 
				</c:if>
				</td>
				
			
			</tr>
		</tbody>
		</c:forEach>
		
		</table>
	</logic:present>

   
		<span id="contenido"> </span>
		<html:errors property="errorIntegridad"/>
		
	
		
   
</html:form>
</c:if>


 
</div>
</div>

<div id="footer"><%@ include file="../footer.jsp"%>



</body>

</html>

