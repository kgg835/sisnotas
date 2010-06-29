<%@ page language="java"%>
<%@ include file="/taglibs.jsp"%>
<c:if test="${requestScope.draw==1}">
<input type="button" class="btn" name="envia"    value='<c:out value='${greeting}'/>' onclick="javascript:location.href='nota.do?accion2=listar&accionId=<c:out value='${prueba.estado.codigo}'/>&nombreCurso=<c:out value='${param.nombreCurso}'/>&accion=<c:out value='${prueba.estado.accion}'/>&descripcion=<c:out value='${prueba.estado.descripcion}'/>&cursoId=<c:out value='${prueba.codCur}'/>&seccion=<c:out value='${prueba.seccion}'/>&perAcad=<c:out value='${prueba.perAcad}'/>&tipo=<c:out value='${prueba.codtippba}'/>&num=<c:out value='${prueba.numtippba}'/>';"/>
</c:if>

