<%@ include file="/taglibs.jsp"%>
<%@ page import="ccuni.java.sysNotas.domain.dto.*"%>
<%@ page import="ccuni.java.sysNotas.logic.CursoSeccionManager"%>
<%@ page import="java.util.*"%>
<ul id="menu">
	<c:if test="${sessionScope.usuario.codGrpUsr==1}">
	    <c:if  test="${parametrosSistema.inicio_periodo==0}">	
		<li><a href="toIniciarPeriodo.do">Iniciar Nuevo Periodo Académico</a></li>
		</c:if>
		<li><html:link page="/registrarDocentes.do">Registrar Docentes</html:link></li>
		<li><html:link page="/toRegAdmin.do">Crear Administrador</html:link></li>
		<li><html:link page="/consultaUsuarios.do">Consultar usuarios</html:link></li>
		<li><a href="toCambiarPassword.do">Cambiar password</a></li>
		<c:if  test="${parametrosSistema.inicio_periodo==1}">	
		<c:if  test="${parametrosSistema.parametros_cursos==1}">
		<li><a href="listaCursos.do"><bean:message key="menu.admin.cursos" /></a></li>	
		</c:if>	
		<li><html:link page="/registrarCursos.do?metodo=listarCursosNoRegistrados">Registrar cursos</html:link></li>
		<c:if  test="${parametrosSistema.migracion_docentes==1}">	
		<li><html:link page="/listaCursosParametros.do">Administrar Cursos</html:link></li>	
		</c:if>	
		<li><html:link page="/tipoPruebaAction.do?action=list">Administrar tipos de prueba</html:link></li>
		<li><html:link page="/toActualizarNotas.do?parametro=inicio">Sincronizar Notas</html:link></li>
		<li><html:link page="/toTerminarPeriodo.do">Terminar Periodo</html:link></li>
		</c:if>

	</c:if>
	<c:if test="${sessionScope.usuario.codGrpUsr==2 }">
		<li><a href="toCambiarPassword.do">Cambiar password</a></li>
		<li><html:link page="/registrarDocentes.do">Registrar Docentes</html:link></li>		
		<li><html:link page="/consultaUsuarios.do">Consultar usuarios</html:link></li>
		<c:if  test="${parametrosSistema.inicio_periodo==1}">
		<c:if  test="${parametrosSistema.parametros_cursos==1}">	
		<li><a href="listaCursos.do"><bean:message key="menu.admin.cursos" /></a></li>
		</c:if>	
		<li><html:link page="/RegistrarCursos.do?metodo=listarCursosNoRegistrados">Registrar cursos</html:link></li>
		<c:if  test="${parametrosSistema.migracion_docentes==1}">	
		<li><html:link page="/listaCursosParametros.do">Administrar Cursos</html:link></li>	
		</c:if>	
		<li><html:link page="/tipoPruebaAction.do?action=list">Administrar tipos de prueba</html:link></li>		
		</c:if>
	</c:if>
	<c:if test="${sessionScope.usuario.codGrpUsr==3 }">
		<li><a href="toCambiarPassword.do">Cambiar password</a></li>	
		
		<c:if  test="${parametrosSistema.inicio_periodo==1}">
		<c:if  test="${parametrosSistema.parametros_cursos==1}">
		<li><a href="listaCursos.do"><bean:message key="menu.admin.cursos" /></a></li>	
		</c:if>	
		</c:if>
		<c:if  test="${parametrosSistema.inicio_periodo==1 and parametrosSistema.parametros_cursos==1}">	
		<li><a href="listaCursosParametrosDocente.do">Administrar Cursos</a></li>
		</c:if>
	</c:if>
	<!-- falata pal docente -->
</ul>

