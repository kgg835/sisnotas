<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@ page contentType="text/html;charset=windows-1252"%>
<%@ include file="/taglibs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type"
	content="text/html; charset=windows-1252" />
<title>listadoCurso</title>
<script language="JavaScript" type="text/javascript">
function selectAll(chkbox)
{
for (var i=0;i < document.forms[0].elements.length;i++)
{
var elemento = document.forms[0].elements[i];
if (elemento.type == "checkbox")
{
elemento.checked = chkbox.checked;
}
}
}   
</script>
<link href="style/style.css" rel="stylesheet" type="text/css" />
</head>
<body>
<div id="header"><%@ include file="../header.jsp"%></div>
<div id="navcontainer"><%@ include file="/paginas/listamenu.jsp"%></div>
<div id="center">

		<div id="centrado" style="margin-left: 40px;margin-top: 50px">
		<c:if test="${not empty promedioFaltantes}">
			<span style="font-size: 20px;color: red;">Faltan registrar algunas practicas en algunos cursos</span>
			<br>
			<c:forEach var="row" items="${promedioFaltantes}">
			      <c:out value="${row.cursoId} ${row.seccion} ${row.nombre}"/><br>			
			</c:forEach>
				
		</c:if>
		<html:form action="/toTerminarPeriodo.do" method="get">
		<input type="hidden" name="accion"/>				
		<input type="hidden" name="detalle"/>		
		<span style="font-size: 15px;">Esta seguro de finalizar el periodo academico
		<br>Desea Continuar?</span><br>
		<input class="btn" type="submit" value="Aceptar" onclick="javascript:document.forms[0].accion.value='terminar';javascript:document.forms[0].detalle.value='<c:out value="${param.detalle}"/>';"/>
		<input class="btn" type="submit" value="Cancelar" onclick="javascript:document.forms[0].accion.value='cancel';" />
		</html:form>
		</div>
	</div>
<div id="footer"><%@ include file="../footer.jsp"%></div>
</body>
</html>

