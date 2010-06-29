
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@ page contentType="text/html;charset=windows-1252"%>
<%@ include file="/taglibs.jsp"%>
<%@ page import="ccuni.java.sysNotas.domain.dto.*"%>
<html>

<head>
<meta http-equiv="Content-Type"
	content="text/html; charset=windows-1252" />
<title>listado de Cursos</title>
<link href="style/style2.css" rel="stylesheet" type="text/css" />
<script language="JavaScript" type="text/javascript">
	
		function envia(url){ 
		
		location.href=url;
		
		
		}
</script>
</head>
<body>
<div id="header"><%@ include file="../header.jsp"%></div>
<div id="navcontainer"><%@ include file="listamenu.jsp"%></div>
<div id="center">
<div class="centrado">
<c:if test="${empty CURSOS}">
<br>

No hay cursos definidos para este docente


</c:if>
<h2>Listado de Cursos</h2>
<c:if test="${not empty CURSOS}">
<layout:pager maxPageItems="20">
	<layout:collection id="lista" name="CURSOS" styleClass="formStruts">
		<layout:collectionItem property="cursoId" title="codigo" />
		<layout:collectionItem property="seccion" title="seccion" />
		<layout:collectionItem property="nombre" title="nombre"  />
		<layout:collectionItem title="news.modification">
			<center>
			<%-- <layout:link href="listaEvaluaciones.do?accion2=listar&cursoId=${lista.cursoId}&seccion=${lista.seccion}&perAcad=${lista.perAcad}&nombreCurso=${lista.nombre}" >
				<img  src="imagenes/images.jpg" border="0">
			</layout:link>--%>
			<c:if test="${lista.param_definidos==1}">
			<html:button styleClass="button" property="envia" onclick="javascript:location.href='listaEvaluaciones.do?accion2=listar&cursoId=${lista.cursoId}&seccion=${lista.seccion}&perAcad=${lista.perAcad}&nombreCurso=${lista.nombre}';">
			ver</html:button>
			</c:if>
			<c:if test="${lista.param_definidos==0}">
			xx
			</c:if>
			</center>
		</layout:collectionItem>
		<layout:collectionItem title="estado" >		
			<c:if test="${lista.estado==1 }">
			<font > No hay pendientes </font>
			</c:if>
			<c:if test="${lista.estado==0 and lista.param_definidos==1}">
			<font color="red" size="4"> Tiene Pendientes </font>
			</c:if>
			<c:if test="${lista.estado==0 and lista.param_definidos==0}">
			<font color="red" size="4">Falta Def. Parámetros </font>
			</c:if>			
		
		</layout:collectionItem>
	</layout:collection>
</layout:pager> 
</c:if>
</div>
</div>
<div id="footer"><%@ include file="../footer.jsp"%></div>
</body>
</html>
