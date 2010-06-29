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
<div><fmt:setBundle basename="ApplicationResources" />
<h2>Confirmacion</h2>
<form action="listarDocentes.do"><input type="hidden" name="action">
<p class="confirm"><fmt:message key="confimarMigracion.info" /></p>
<p class="confirm"><fmt:message key="confimarMigracion.confirm" /></p>
<div id="btnCentroConfirm"><input class="btn" type="submit"
	value="<fmt:message key="confimarMigracion.cancelar"/>"
	onclick="javascript:document.forms[0].action.value='cancelar';"> <input
	class="btn" type="submit"
	value="<fmt:message key="confimarMigracion.aceptar"/>"
	onclick="javascript:document.forms[0].action.value='aceptar';"></div>
</form>
</div>
</div>
<div id="footer"><%@ include file="../footer.jsp"%></div>
</body>
</html>
