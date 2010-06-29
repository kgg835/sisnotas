<%@ include file="/taglibs.jsp" %>



 	<a href="Welcome.jsp"><img  id="logo1" src="imagenes/Quimica.jpg"></a>	
 	<c:if test="${sessionScope.usuario != null}">
 	
 	<span id="logof">
		<c:if  test="${parametrosSistema.inicio_periodo==1}">	
		Periodo <c:out value="${parametrosSistema.nombre_periodo}"/>
		</c:if>
		<c:out value=" Bienvenido  ${sessionScope.usuario.userName}"/> <br>		

		<a href="logOff.do">Terminar sesión</a>
	</span>
	<a href="logOff.do">Terminar sesión</a>
	</c:if>
