<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@ page contentType="text/html;charset=windows-1252"%>
<%@ include file="/taglibs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type"
	content="text/html; charset=windows-1252" />
<title>Actualizar notas</title>


<link href="style/style.css" rel="stylesheet" type="text/css" />

</head>
<body>
<div id="header"><%@ include file="../header.jsp"%></div>

<div id="navcontainer"><%@ include file="/paginas/listamenu.jsp"%></div>

<div id="center">
<div id="centrado">


		<c:if test="${not empty listaFaltantes}">
			<span style="font-size: 20px;color: red;">Lista de alumnos que no se actualizaron sus notas </span>
			<br>
			<c:forEach var="row" items="${listaFaltantes}">
			      <c:out value="${row}"/><br>			
			</c:forEach>			
		</c:if>
		<c:if test="${empty listaFaltantes}">
			<span style="font-size: 20px;color: red;">Se realizó con éxito la sincronización con clipper</span>						
		</c:if>




</div>
</div>
<div id="footer"><%@ include file="../footer.jsp"%></div>

</body>
</html>
