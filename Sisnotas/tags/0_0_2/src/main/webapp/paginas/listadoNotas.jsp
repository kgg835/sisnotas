<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@ page contentType="text/html;charset=windows-1252"%>
<%@ include file="/taglibs.jsp"%>
<%@ page import="ccuni.java.sysNotas.domain.dto.*"%>
<fmt:setBundle basename="ApplicationResources" />
<html>

<head>
<meta http-equiv="Content-Type"
	content="text/html; charset=windows-1252" />
<title>listado de Notas</title>
<script language="JavaScript" type="text/javascript">
	 
		function verifica(id){
		     
		        
				
				s = document.getElementById(id).value;				
				if (s.length == 0 ) return true;	
				
				if(s =="0A" || s =="0a" || s=="NP") return true;
				
				var flag=true;
				for (var n = 0; n < s.length; n++)
				    if (s.substring(n, n+1) < "0" || s.substring(n, n+1) > "9")
				      flag=false;
				
				if(flag){//si es numero
					valor=parseInt(document.getElementById(id).value);
					if(valor>=0 &&valor<=20)  return true;
				}		
											
				
				alert("valores permitidos de 0 a 20,0A");
				
				
				
				if(window.event) // IE
				{
					document.getElementById(id).select();
					document.getElementById(id).focus();
				}else{				
					document.getElementById(id).value="";				
				}
				
				return false;
		  
			
		}
		function noEnter(e){ 
		var key
			if(window.event) // IE
			{
				key = e.keyCode
			}
			else if(e.which) // Netscape/Firefox/Opera
			{
				key = e.which
			}
			return(key!=13)		
		
		}
		function confirma(){ 
			
		if(confirm('¿Estas seguro de registra las notas?'))
			document.forms[0].submit();
		}
</script>

<link href="style/style.css" rel="stylesheet" type="text/css" />
<link href="style/style_printer.css" rel="stylesheet" type="text/css" media="print"> 

</head>
<c:choose>
	<!--logins viene de exampleresponse.jsp coko parameter-->
	<c:when test="${param.accionId == 2 or param.accionId == 3 }">
		<c:set var="notaDisabled" value="false" scope="page" />
		<c:set var="notaRecDisabled" value="true" scope="page" />
	</c:when>
	<c:otherwise>
		<c:set var="notaDisabled" value="true" scope="page" />
		<c:set var="notaRecDisabled" value="false" scope="page" />
	</c:otherwise>
</c:choose>
<body>
<div id="header"><%@ include file="../header.jsp"%></div>
<div id="navcontainer"><%@ include file="listamenu.jsp"%></div>
<div id="center">
<div id="centrado" style="margin-left: 60px;">
<h2> Nombre  :<c:out value="${param.nombreCurso}"/></h2>
<h2>Codigo   :<c:out value="${param.cursoId} - ${param.seccion}"/></h2>


<html:form action="/nota.do" method="get" >
	<html:hidden property="accion" />
	<html:hidden property="cursoId" />
	<html:hidden property="seccion" />
	<html:hidden property="perAcad" />
	<table >
	    <thead>
		<tr>
			<th>Nro</th>
			<th>codigo</th>
			<th>Alumno</th>
			<th>
			<c:choose>
			    <c:when test="${param.tipo=='EF' or param.tipo=='ES' or param.tipo=='EP' }">
			     <c:out value='${param.tipo}' />
			    </c:when>
			    <c:otherwise>
			    <c:out value='${param.tipo}' /> <c:out value='${param.num}' />
			    </c:otherwise>			  
			</c:choose>		
			</th>
			<logic:present name="condicion">
				<th>REC</th>
			</logic:present>
		</tr>
		</thead>
		<!-- logic:iterate id="student" name="listNombres" indexId="i"-->
		<tbody>
		<c:forEach var="i" begin="0" end="${cantidad}" step="1">
			<tr>
				<td><c:out value="${i}" /></td>
				<td><html:text styleClass="fieldText" property="codigos[${i}]" size="9" disabled="true" /></td>
				<td><html:text styleClass="fieldText" property="nombres[${i}]" size="60"
					disabled="true" /></td>
				<td><html:text styleClass="fieldText" property="notas[${i}]" styleId="notas[${i}]"  size="1" disabled="${notaDisabled}" maxlength="2"  onblur="verifica('notas[${i}]')" onkeypress="return noEnter(event)" /></td>
				<logic:present name="condicion">
					<c:choose>
						<c:when test="${condicion[i]=='NP'}">
							<td><html:text styleClass="fieldText" property="notasRec[${i}]"  size="1" maxlength="2" disabled="true" /></td>
						</c:when>
						<c:otherwise>
							<td><html:text styleClass="fieldText" property="notasRec[${i}]" styleId="notasRec[${i}]" size="1" maxlength="2"  onblur="verifica('notasRec[${i}]')" onkeypress="return noEnter(event)"/></td>
						</c:otherwise>
					</c:choose>
				</logic:present>
			</tr>
		</c:forEach>
		</tbody>
		<!--  /logic:iterate-->


	</table>




<%--<fmt:message key="nota.button.cancel" />--%>
	<html:button property="aceptar" styleClass="btn" value="${param.descripcion}" onclick="confirma();"/>
	<html:submit styleClass="btn" value="cancel"
		onclick="javascript:document.forms[0].accion.value='cancel';">
		
	</html:submit>
</html:form>


</div>
</div>
<div id="footer"><%@ include file="../footer.jsp"%></div>


</body>

</html>


