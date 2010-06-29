<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@ page contentType="text/html;charset=windows-1252"%>
<%@ include file="/taglibs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type"
	content="text/html; charset=windows-1252" />
<title>Menu</title>


<link href="style/style.css" rel="stylesheet" type="text/css" />

</head>
<body>
<div id="header"><%@ include file="../header.jsp"%></div>

<div id="navcontainer"><%@ include file="/paginas/listamenu.jsp"%></div>

<div id="center">
<%-- <form action="promedio.do"><input type="submit" value="Calcular Periodo" />
</form>--%>
<div id="centrado" style="margin-left: 40px;margin-top: 50px" >

<html:form action="/toTerminarPeriodo.do" method="get">
<input type="hidden" name="accion" value="previoTerminar"/>
<span style="font-size: 15px;"> Ingrese algunos detalles del cierre del Periodo Academico<br></span>
<html:textarea property="detalle" style="width: 90%; height:100px;" /><br>
<input class="btn" type="submit" value="Terminar Periodo" />
</html:form>




</div>
</div>

<div id="footer"><%@ include file="../footer.jsp"%></div>

</body>
</html>



