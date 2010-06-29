<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@ page contentType="text/html;charset=windows-1252"%>
<%@ include file="/taglibs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type"
	content="text/html; charset=windows-1252" />
<title>Sincronizar notas</title>


<link href="style/style.css" rel="stylesheet" type="text/css" />

</head>
<body>
<div id="header"><%@ include file="../header.jsp"%></div>

<div id="navcontainer"><%@ include file="/paginas/listamenu.jsp"%></div>

<div id="center">
<div id="centrado">

<form action="toActualizarNotas.do?parametro=sincronizar" >
<div>Esta acción sincronizará las notas almacenada en MYSQL a las tablas del Clipper</div>
<div><input class="btn" type="submit" value="Sincronizar notas" /></div>
</form>





</div>
</div>
<div id="footer"><%@ include file="../footer.jsp"%></div>

</body>
</html>
