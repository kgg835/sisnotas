<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@ page contentType="text/html;charset=windows-1252"%>
<%@ include file="/taglibs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type"
	content="text/html; charset=windows-1252" />
<title>listadoCurso</title>
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
</script>
<link href="style/style.css" rel="stylesheet" type="text/css" />
</head>
<body>
<div id="header"><%@ include file="../header.jsp"%></div>
<div id="navcontainer"><%@ include file="/paginas/listamenu.jsp"%></div>
<div id="center">
<h2>Registrar Docentes</h2>
<div id="centrado"><fmt:setBundle basename="ApplicationResources" />
<p class="info">
<fmt:message
	key="listDoc.info" />
</p>
	<html:form action="migrarDocentes.do"
	method="post">
	<input type="hidden" name="action">
	<html:errors property="docentes" />
	<table>
		<caption><fmt:message key="listDoc.title" /></caption>
		<thead>
		<tr>
			<th><input name="chkdoc" type="checkbox" onclick="selectAll(this);" /></th>
			<th><fmt:message key="listDoc.codigo" /></th>
			<th><fmt:message key="listDoc.nombresApell" /></th>
		</tr>
		</thead>
		<c:choose>
			<c:when test="${not empty listaDocentes}">
				<c:forEach items="${listaDocentes}" var="d" varStatus="status">
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
					<td><input name="docentes" type="checkbox"
						value="<c:out value="${d.codDoc}"/>" /></td>
					<td><c:out value="${d.codDoc}" /></td>
					<td><c:out value="${d.apellidos}" />, <c:out value="${d.nombres}" /></td>
					</tr>
					</tbody>
				</c:forEach>
			</c:when>
			<c:otherwise>
				<tbody>
				<tr>
					<td colspan="3"><fmt:message key="listDoc.docnoexist"></fmt:message></td>
				</tr>
				</tbody>
			</c:otherwise>
		</c:choose>
	</table>
	<div class="btnCentro">
	<html:cancel styleClass="btn"
		onclick="bCancel=true;javascript:document.forms[0].action.value='cancelar'; ">
		<fmt:message key="listDoc.cancelar" />"</html:cancel>
	<input class="btn" type="submit"
		value="<fmt:message key="listDoc.registrar" />"
		onclick="javascript:document.forms[0].action.value='registrar';" /></div>
</html:form></div>
</div>
<div id="footer"><%@ include file="../footer.jsp"%></div>
</body>
</html>

