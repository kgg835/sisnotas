<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@ page contentType="text/html;charset=windows-1252"%>
<%@ include file="/taglibs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type"
	content="text/html; charset=windows-1252" />
<title>Consulta de Usuarios</title>
<link href="style/style2.css" rel="stylesheet" type="text/css" />
</head>
<body>
<div id="header"><%@ include file="../header.jsp"%></div>
<div id="navcontainer"><%@ include file="/paginas/listamenu.jsp"%></div>
<div id="center"><fmt:setBundle basename="ApplicationResources" />
<h2><fmt:message key="consultaUser.titulo" /></h2>
<div id="centrado">
<form action="editarUsuario.do"><c:choose>
	<c:when test="${not empty listaUsuarios}">
		<layout:pager maxPageItems="20">
			<layout:collection id="u" name="listaUsuarios"
				styleClass="formStruts">
				<layout:collectionItem property="userName"
					title="consultaUser.username" />
				<layout:collectionItem property="nombreCompleto"
					title="consultaUser.nombresApellidos" />
				<layout:collectionItem property="nombreGrupoUsuario"
					title="consultaUser.tipo" />
				<layout:collectionItem property="nombreEstado"
					title="consultaUser.tipo" />
				<layout:collectionItem title="consultaUser.acciones">
					<center><layout:link
						href="usuarioAction.do?action=view&codUser=${u.codUsr}">
						<fmt:message key="consultaUser.editar" />
					</layout:link></center>
				</layout:collectionItem>
			</layout:collection>
		</layout:pager>
	</c:when>
	<c:otherwise>
		<p class="info"><fmt:message key="consultaUser.Nohayusuarios"></fmt:message></p>
	</c:otherwise>
</c:choose></form>
</div>
</div>
<div id="footer"><%@ include file="../footer.jsp"%></div>
</body>
</html>
