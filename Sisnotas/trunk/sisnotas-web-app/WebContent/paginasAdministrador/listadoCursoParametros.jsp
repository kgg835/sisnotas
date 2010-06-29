<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@ page contentType="text/html;charset=windows-1252"%>
<%@ include file="/taglibs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type"
	content="text/html; charset=windows-1252" />
<title>Listado de Cursos</title>
<script language="JavaScript" type="text/javascript">
		function confirmarEliminacion(url){ 
		if(confirm("Se eliminará toda la informacion asociada a este curso")){
			location.href=url;}
		}
</script>
<link href="style/style2.css" rel="stylesheet" type="text/css" />
</head>
<body>
<div id="header"><%@ include file="../header.jsp"%></div>
<div id="navcontainer"><%@ include file="/paginas/listamenu.jsp"%></div>
<div id="center"><fmt:setBundle basename="ApplicationResources" />
<div id="centrado">
<h2><fmt:message key="listCursosParam.title" /></h2>

<c:choose>
	<c:when test="${parametrosSistema.parametros_cursos==1}">
		<p class="info">Ya estan definidos los docentes responsables para
		todos los cursos</p>
	</c:when>
	<c:otherwise>
		<c:choose>
			<c:when test="${msg1!= null}">
				<p class="info"><c:out value="${msg1}" /></p>
			</c:when>
			<c:otherwise>
				<c:if test="${sessionScope.usuario.codGrpUsr==1 }">
					<p><a class="info"
						href="parametrosAction.do?action=confirmDefParam"><c:out
						value="${msg2}" /></a></p>
				</c:if>
			</c:otherwise>
		</c:choose>
	</c:otherwise>
</c:choose> <c:choose>
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
					<!--boton para eliminar el curso  -->
					<html:button styleClass="button" property="envia"
								onclick="confirmarEliminacion('parametrosAction.do?action=deleteCurso&codCur=${c.cursoId}&secc=${c.seccion}');">
							<fmt:message key="listCursosParam.delete" />
					</html:button>

				</layout:collectionItem>
			</layout:collection>
		</layout:pager>
	</c:when>
	<c:otherwise>
		<p>No hay cursos registrados en este momento</p>
	</c:otherwise>
</c:choose></div>
</div>
<div id="footer"><%@ include file="../footer.jsp"%></div>
</body>
</html>
