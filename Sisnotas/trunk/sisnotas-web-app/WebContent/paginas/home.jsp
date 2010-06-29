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
<div id="header"><%@ include file="../header.jsp"%></div>

<div id="navcontainer"><%@ include file="listamenu.jsp"%></div>
	
	<div id="center">
		<div id="centrado">
		<p>Bienvenido!!!, tus datos son:</p>
		<p>Usuario: <c:out value="${sessionScope.usuario.codUsr}"/></p>
		<p>Nombre Completo: <c:out value="${sessionScope.usuario.nombreCompleto}"/></p>
		
		<c:if test="${not empty final}">
			<p style="font-size: 15px;color:red; ">Se realizo con exito el cierre de periodo academico</p>
		</c:if>
		</div>
	</div>

<div id="footer"><%@ include file="../footer.jsp"%></div>

</body>
</html>
