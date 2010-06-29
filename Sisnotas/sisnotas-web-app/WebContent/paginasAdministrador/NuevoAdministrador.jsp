<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@ page contentType="text/html;charset=windows-1252"%>
<%@ include file="/taglibs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type"
	content="text/html; charset=windows-1252" />
<title>Nuevo Administrador</title>
<link href="style/style.css" rel="stylesheet" type="text/css" />
</head>
<body>
<div id="header"><%@ include file="../header.jsp"%></div>
<div id="navcontainer"><%@ include file="/paginas/listamenu.jsp"%></div>
<div id="center"><fmt:setBundle basename="ApplicationResources" />
<h2>Nuevo administrador</h2>
<div id="centrado"><html:errors property="regAdmin" />
<html:form
	action="/registroAdmin.do">
	<table>
		<tr>
			<th><fmt:message key="regAdmin.username" /></th>
			<td><html:text property="username" /> <html:errors
				property="username" /></td>
		</tr>
		<tr>
			<th><fmt:message key="regAdmin.password" /></th>
			<td><html:password property="password" redisplay="false" /> <html:errors
				property="password" /></td>
		</tr>
		<tr>
			<th><fmt:message key="regAdmin.confimPass" /></th>
			<td><html:password property="password2" redisplay="false" /></td>
		</tr>
		<tr>
			<th><fmt:message key="regAdmin.nombres" /></th>
			<td><html:text property="nombres" /> <html:errors property="nombres" />
			</td>
		</tr>
		<tr>
			<th><fmt:message key="regAdmin.apellidos" /></th>
			<td><html:text property="apellidos" /> <html:errors
				property="apellidos" /></td>
		</tr>
	</table>
	<div class="btnCentro">
	<input class="btn" type="submit"
		value="<fmt:message key="regAdmin.submit" />"></input></div>
</html:form></div>
</div>
<div id="footer"><%@ include file="../footer.jsp"%></div>
</body>
</html>

