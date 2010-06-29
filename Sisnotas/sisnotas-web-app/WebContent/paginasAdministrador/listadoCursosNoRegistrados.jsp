<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@ page contentType="text/html;charset=windows-1252"%>
<%@ include file="/taglibs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type"
	content="text/html; charset=windows-1252" />
<title>Registro Cursos</title>
<script language="JavaScript" type="text/javascript">
function selectAll(chkbox)
{
for (var i=0;i < document.forms[0].elements.length;i++)
{
var elemento = document.forms[0].elements[i];
if (elemento.type == "checkbox")
{
elemento.checked = chkbox.checked;
}
}
}
function validarCheckBox(){
	for (var i=0;i < document.forms[0].elements.length;i++)
	{
	var elemento = document.forms[0].elements[i];
	if (elemento.name == "cursos")
	{
		if(elemento.checked = chkbox.checked){
			return true;
		}
	}
	}
	return false;
}
</script>
<link href="style/style.css" rel="stylesheet" type="text/css" />
</head>
<body>
<div id="header"><%@ include file="../header.jsp"%></div>
<div id="navcontainer"><%@ include file="/paginas/listamenu.jsp"%></div>
<div id="center">
<h2>Registrar Cursos</h2>
<div id="centrado"><fmt:setBundle basename="ApplicationResources" />
<p class="info">
<fmt:message
	key="listCurso.info" />
</p>
	<form action="registrarCursos.do?metodo=registrarCursosSeleccionados"
	method="post">
	<input type="hidden" name="action">
	<table>
		<caption><fmt:message key="listCurso.noregistrados" /></caption>
		<thead>
		<tr>
			<th><input name="chkdoc" type="checkbox" onclick="selectAll(this);" /></th>
			<th><fmt:message key="listCurso.codigo" /></th>
			<th><fmt:message key="listCurso.nombre" /></th>
			<th><fmt:message key="listCurso.seccion" /></th>
		</tr>
		</thead>
		<c:choose>
			<c:when test="${not empty cursosNoRegistrados}">
				<c:forEach items="${cursosNoRegistrados}" var="c" varStatus="status">
					<c:choose>
						<c:when test="${(status.count % 2) == 0}">
							<tbody>
							<tr>
						</c:when>
						<c:otherwise>
							<tbody>
							<tr>
						</c:otherwise>
					</c:choose>
					<td><input name="cursos" type="checkbox"
						value="<c:out value="${c.cursoId},${c.nombre},${c.seccion},${c.perAcad}"/>" /></td>
					<td><c:out value="${c.cursoId}" /></td>
					<td><c:out value="${c.nombre}" /></td>
					<td><c:out value="${c.seccion}" /></td>
					</tr>
					</tbody>
				</c:forEach>
			</c:when>
			<c:otherwise>
				<tbody>
				<tr>
					<td colspan="4"><fmt:message key="listDoc.docnoexist"></fmt:message></td>
				</tr>
				</tbody>
			</c:otherwise>
		</c:choose>
	</table>
	<div class="btnCentro">
	<input class="btn" type="submit" value="<fmt:message key="listDoc.cancelar" />"
		onclick="javascript:document.forms[0].action.value='cancelar';"/>
	<input class="btn" type="submit"
		value="<fmt:message key="listDoc.registrar"/>"
		onclick="javascript:document.forms[0].action.value='registrar';" /></div>
</form></div>
</div>
<div id="footer"><%@ include file="../footer.jsp"%></div>
</body>
</html>

