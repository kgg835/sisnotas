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
<link href="style/style.css" rel="stylesheet" type="text/css" />
<link href="style/style_printer.css" rel="stylesheet" type="text/css" media="print"> 

</head>
<body>
<div id="header"><%@ include file="../header.jsp"%></div>
<div id="navcontainer"><%@ include file="listamenu.jsp"%></div>
<div id="center">
<div id="centrado" style="margin-left: 60px;">
<h2> Nombre  :<c:out value="${param.nombreCurso}"/></h2>
<h2>Codigo   :<c:out value="${param.cursoId} - ${param.seccion}"/></h2>


<html:form action="/nota.do">
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
			<c:if test="${logrec==1 }">	
			<th>REC</th>
			</c:if>
		</tr>
		</thead>
	
		<tbody>
		<c:forEach var="i" begin="0" end="${cantidad}" step="1">
			<tr>
				<td><c:out value="${i}" /></td>
				<td><html:text styleClass="fieldText" property="codigos[${i}]" size="9" disabled="true" /></td>
				<td><html:text styleClass="fieldText" property="nombres[${i}]" size="60" disabled="true" /></td>
				<td><html:text styleClass="fieldText" property="notas[${i}]" styleId="notas[${i}]"  size="1" disabled="true" maxlength="2"/></td>
				<c:if test="${logrec==1 }">				
				<td><html:text styleClass="fieldText" property="notasRec[${i}]"  size="1" maxlength="2" disabled="true" /></td>				
				</c:if>
			</tr>
		</c:forEach>
		</tbody>



	</table>





	<html:submit styleClass="btn"  value="Aceptar" onclick="javascript:document.forms[0].accion.value='cancel';"/>
	<html:button styleClass="btn" property="imprimir"   onclick="javascript:window.print()" value="Imprimir"/>
	
</html:form>


</div>
</div>
<div id="footer"><%@ include file="../footer.jsp"%></div>


</body>

</html>


