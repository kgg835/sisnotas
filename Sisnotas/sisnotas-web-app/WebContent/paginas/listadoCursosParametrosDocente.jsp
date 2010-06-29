<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@ page contentType="text/html;charset=windows-1252"%>
<%@ include file="/taglibs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type"
	content="text/html; charset=windows-1252" />
<title>Listado de Cursos</title>
<link href="style/style2.css" rel="stylesheet" type="text/css" />
</head>
<body>
<div id="header"><%@ include file="../header.jsp"%></div>
<div id="navcontainer"><%@ include file="/paginas/listamenu.jsp"%></div>
<div id="center"><fmt:setBundle basename="ApplicationResources" />
<h2><fmt:message key="listCursosParam.title" /></h2>
<div id="centrado"><c:choose>
	<c:when test="${not empty cursos}">
		<layout:pager maxPageItems="20">
			<layout:collection id="c" name="cursos" styleClass="formStruts">
				<layout:collectionItem property="cursoId"
					title="listCursosParam.codigo" />
				<layout:collectionItem property="nombre"
					title="listCursosParam.nombrecurso" />
				<layout:collectionItem property="seccion"
					title="listCursosParam.seccion" />
				<layout:collectionItem title="listCursosParam.acciones">
					<c:if test="${parametrosSistema.tipos_prueba_definidos==1}">
						<c:choose>
							<c:when test="${c.paramExist==true}">
								<html:button styleClass="button" property="envia"
									onclick="javascript:location.href='parametrosAction.do?action=verParametros&codCur=${c.cursoId}&secc=${c.seccion}';">
									<fmt:message key="listCursosParam.view/edit" />
								</html:button>
								<!--  <layout:link
									href="parametrosAction.do?action=verParametros&codCur=${c.cursoId}&secc=${c.seccion}">
									<fmt:message key="listCursosParam.view/edit" />
								</layout:link>-->
							</c:when>
							<c:otherwise>
								<!--<layout:link
									href="parametrosAction.do?action=verParametros&codCur=${c.cursoId}&secc=${c.seccion}">
									<fmt:message key="listCursosParam.crear" />
								</layout:link>-->
								<html:button styleClass="button" property="envia"
									onclick="javascript:location.href='parametrosAction.do?action=verParametros&codCur=${c.cursoId}&secc=${c.seccion}';">
									<fmt:message key="listCursosParam.crear" />
								</html:button>
							</c:otherwise>
						</c:choose>
					</c:if>
				</layout:collectionItem>
				<layout:collectionItem title="listCursosParam.estado">
					<c:choose>
						<c:when test="${c.param_definidos==1}">
							<p>Definido</p>
						</c:when>
						<c:otherwise>
							<p>No definido</p>
						</c:otherwise>
					</c:choose>
				</layout:collectionItem>
			</layout:collection>
		</layout:pager>
	</c:when>
	<c:otherwise>
		<p>No hay cursos asociados</p>
	</c:otherwise>
</c:choose></div>
</div>
<div id="footer"><%@ include file="../footer.jsp"%></div>
</body>
</html>
