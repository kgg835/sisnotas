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
<div id="head"><%@ include file="../header.jsp"%></div>
<div id="navcontainer"><%@ include file="/paginas/listamenu.jsp"%></div>
<div id="center"><fmt:setBundle basename="ApplicationResources" />
<div>
<h2><fmt:message key="reportRegAdmin.exito" /></h2>
<p><fmt:message key="reportRegAdmin.datosReg" /></p>
<table>
	<tr>
		<th><fmt:message key="reportRegAdmin.username" /></th>
		<td><c:out value="${admin.userName}" /></td>
	</tr>
	<tr>
		<th><fmt:message key="reportRegAdmin.password" /></th>
		<td><c:out value="${admin.password}" /></td>
	</tr>
</table>
<a href="toHome.do">Volver</a>
</div>
</div>
<div><%@ include file="../footer.jsp"%></div>
</body>
</html>
