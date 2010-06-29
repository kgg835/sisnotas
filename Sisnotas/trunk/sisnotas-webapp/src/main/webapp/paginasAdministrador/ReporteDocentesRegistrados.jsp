<%@ include file="/taglibs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type"
	content="text/html; charset=windows-1252" />
<title>listadoCurso</title>
<link href="style/style.css" rel="stylesheet" type="text/css" />
<link href="style/style_printer.css" rel="stylesheet" type="text/css" media="print"> 
</head>
<body>
<div id="header"><%@ include file="../header.jsp"%></div>
<div id="navcontainer"><%@ include file="/paginas/listamenu.jsp"%></div>
<div id="center">
<a class="info" style="text-align: center; display: block;" href="toHome.do">Volver</a>
<div><fmt:setBundle basename="ApplicationResources" />
	<c:out value="${UsuariosYaExisten}" /> 
	<c:if test="${not empty listaDocentesRegistrados}">
	<p class="info"><fmt:message key="ReporteDocReg.listaDoc" /></p>
		<c:forEach items="${listaDocentesRegistrados}" var="doc"
			varStatus="status">
			<c:choose>
				<c:when test="${(status.count % 2) == 0}">
					<table id="">
				</c:when>
				<c:otherwise>
					<table id="">
				</c:otherwise>
			</c:choose>
								<tr>
									<th><fmt:message key="ReporteDocReg.codusuario" /></th>
									<td><c:out value="${doc.codDoc}" /></td>
								</tr>
								<tr>
									<th><fmt:message key="ReporteDocReg.nombre" /></th>
									<td><c:out value="${doc.apellidos}" /> <c:out
										value="${doc.nombres}" /></td>
								</tr>
								<tr>
									<th><fmt:message key="ReporteDocReg.username" /></th>
									<td><c:out value="${doc.userName}" /></td>
								</tr>
								<tr>
									<th><fmt:message key="ReporteDocReg.password" /></th>
									<td><c:out value="${doc.password}" /></td>
								</tr>
					</table>
			</c:forEach>
			<div class="btnCentro">
			<input type="button" class="btn "value="Imprimir" onclick="window.print();">
			</div>
		</c:if>
							<!-- volver al home -->
							<a class="info" style="text-align: center; display: block;" href="toHome.do">Volver</a>
							</div>
							</div>
							<div><%@ include file="../footer.jsp"%></div>
</body>
<div id="footer"><%@ include file="../footer.jsp"%></div>
</html>
