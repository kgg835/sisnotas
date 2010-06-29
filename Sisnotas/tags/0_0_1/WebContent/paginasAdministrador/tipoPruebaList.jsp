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
<fmt:setBundle basename="ApplicationResources" /> 
<h2><fmt:message key="tipoprueba.title" /></h2>
<html:form action="tipoPruebaAction.do" method="post">
	<input type="hidden" name="action">
	<c:choose>
	<c:when test="${parametrosSistema.tipos_prueba_definidos == 0}">
		<c:if test="${sessionScope.usuario.codGrpUsr==1}">
		<p class="info"><a href="tipoPruebaAction.do?action=finalizarAdmin">Desea finalizar
		de administrar los tipos de prueba para este periodo académico?, esto es necesario para comenzar con el ingreso de parametros de calificación de los cursos</a></p>
		</c:if>
	</c:when>
	<c:otherwise>
		<p class="info">Ya han sido definidos los tipos de prueba para este periodo académico</p>
	</c:otherwise>
	</c:choose>
	<div id="centrado">
	<table>
		<caption><fmt:message key="tipoprueba.tiposdeprueba" /></caption>
		<thead>
			<tr>
				<th><fmt:message key="tipoprueba.codigo" /></th>
				<th><fmt:message key="tipoprueba.nombre" /></th>
				<th><fmt:message key="tipoprueba.tipo" /></th>
				<th><fmt:message key="tipoprueba.opciones" /></th>
			</tr>
		</thead>
		<tbody>
			<c:choose>
				<c:when test="${not empty listatiposprueba}">
					<c:forEach items="${listatiposprueba}" var="tp" varStatus="status">
						<c:choose>
							<c:when test="${(status.count % 2) == 0}">
								<tr>
							</c:when>
							<c:otherwise>
								<tr>
							</c:otherwise>
						</c:choose>
						<td><c:out value="${tp.codTipPba}" /></td>
						<td><c:out value="${tp.nombre}" /></td>
						<td><c:out value="${tp.nombreTipoPrueba}" /></td>
						<td><c:if test="${parametrosSistema.tipos_prueba_definidos == 0}">
							<c:if test="${tp.examen==false}">
								<a
									href="tipoPruebaAction.do?action=edit&cod=<c:out value="${tp.codTipPba}" />"><fmt:message
									key="tipoprueba.editar" /></a>
								<a
									href="tipoPruebaAction.do?action=del&cod=<c:out value="${tp.codTipPba}" />"><fmt:message
									key="tipoprueba.delete" /></a>
							</c:if>
						</c:if></td>
						</tr>
		</tbody>
		</c:forEach>
		</c:when>
		<c:otherwise>
			<tbody>
				<tr>
					<td colspan="4"><fmt:message key="tipoprueba.vacio"></fmt:message></td>
				</tr>
			</tbody>
		</c:otherwise>
		</c:choose>
	</table>
	<div class="btnCentro">
	<c:if test="${parametrosSistema.tipos_prueba_definidos == 0}">
		<input class="btn" type="submit"
			value="<fmt:message key="tipopruebapage.Add" />"
			onclick="javascript:document.forms[0].action.value='add';" />
	</c:if>
	<input class="btn" type="submit"
		value="<fmt:message key="tipopruebapage.ok" />"
		onclick="javascript:document.forms[0].action.value='ok';" />
		</div>
</html:form></div>
</div>
<div id="footer"><%@ include file="../footer.jsp"%></div>
</body>
</html>
