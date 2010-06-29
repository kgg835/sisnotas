<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@ page contentType="text/html;charset=windows-1252"%>
<%@ include file="/taglibs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type"
	content="text/html; charset=windows-1252" />
<title>listadoCurso</title>
<link href="style/style.css" rel="stylesheet" type="text/css" />
</head>
<body>
<div id="header"><%@ include file="../header.jsp"%></div>
<div id="navcontainer"><%@ include file="/paginas/listamenu.jsp"%></div>
<div id="center">
<div id="centrado"><fmt:setBundle basename="ApplicationResources" />
<form action="usuarioAction.do"><input type="hidden" name="action" /> <input
	type="hidden" name="coduser" />
<table class="info" style="padding: 0;">
	<caption><fmt:message key="userPage.editarUsuario" /></caption>
	<tbody>
	<tr>
		<th><fmt:message key="userPage.username" /></th>
		<td><c:out value="${usuarioview.userName}" /></td>
	</tr>
	<tr>
		<th><fmt:message key="userPage.apellidosNombres" /></th>
		<td><c:out value="${usuarioview.apellidos}" /> , <c:out
			value="${usuarioview.nombres}" /></td>
	</tr>
	<tr>
		<th><fmt:message key="userPage.tipo" /></th>
		<td><c:out value="${usuarioview.nombreGrupoUsuario}" /></td>
	</tr>
	<c:choose>
		<c:when test="${cheked != null}">
			<tr>
				<td colspan="2"><input type="checkbox" name="editarpass" checked="checked"
					onclick="javascript:document.forms[0].action.value='cambiarpass';javascript:document.forms[0].submit();">
				<fmt:message key="userPage.cambiarpass" /></td>
			</tr>
			<tr>
				<th><fmt:message key="userPage.password" /></th>
				<td><html:password property="password" redisplay="false" /> <html:errors
					property="password" /></td>
			</tr>
			<tr>
				<th><fmt:message key="userPage.password2" /></th>
				<td><html:password property="password2" redisplay="false" /></td>
			</tr>
		</c:when>
		<c:otherwise>
			<tr>
				<td colspan="2"><input type="checkbox" name="editarpass"
					onclick="javascript:document.forms[0].action.value='cambiarpass';javascript:document.forms[0].submit();">
				<fmt:message key="userPage.cambiarpass" /></td>
			</tr>
		</c:otherwise>
	</c:choose>
	<tr>
		<td colspan="2"><c:if test="${usuarioview.logDel==1}">
			<input type="radio" name="estado" value="1" checked="checked" />
		</c:if> <c:if test="${usuarioview.logDel==0}">
			<input type="radio" name="estado" value="1" />
		</c:if><fmt:message key="userPage.activo" /></td>
	</tr>
	<tr>
		<td colspan="2"><c:if test="${usuarioview.logDel==1}">
			<input type="radio" name="estado" value="0" />
		</c:if> <c:if test="${usuarioview.logDel==0}">
			<input type="radio" name="estado" value="0" checked="checked" />
		</c:if><fmt:message key="userPage.inactivo" /></td>
	</tr>
	</tbody>
</table>
<div class="btnCentro">
<input class="btn" type="submit"
	onclick="javascript:document.forms[0].action.value='cancel';"
	value="<fmt:message key="userPage.cancel"/>"></input> <input class="btn"
	type="submit"
	onclick="javascript:document.forms[0].action.value='update';javascript:document.forms[0].coduser.value='<c:out value="${usuarioview.codUsr}" />';"
	value="<fmt:message key="userPage.update"/>"></input>
	</div></form>
</div>
</div>
<div id="footer"><%@ include file="../footer.jsp"%></div>
</body>
</html>

