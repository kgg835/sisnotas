<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@ page contentType="text/html;charset=windows-1252"%>
<%@ include file="/taglibs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type"
	content="text/html; charset=windows-1252" />
<title>Menu</title>



<link href="style/style.css" rel="stylesheet" type="text/css" />

</head>
<body>
<div id="header"><%@ include file ="header.jsp" %></div>
<div id="center" style="margin-left: 0px;width: 100%;text-align: center;height: 150px;">
<c:if test="${sessionScope.usuario != null}">
<a style="border: 4px outset;
padding: 2px;
text-decoration: none;
font-size: 18px;
background-color: silver;" href="toMenu.do">Menu</a>
</c:if>
<c:if test="${sessionScope.usuario eq null}">
<H3>Bienvenido. Accede al sistema:</H3>
<a style="border: 4px outset;
padding: 2px;
text-decoration: none;
font-size: 18px;
background-color: silver;" href="toLogin.do">Login</a>
</c:if>
<a style="border: 4px outset;
padding: 2px;
text-decoration: none;
font-size: 18px;
background-color: silver; " href="toConsulta.do">Consultar notas</a>
</div>
<div id="footer"><%@ include file="footer.jsp" %></div>
</body>
</html>
