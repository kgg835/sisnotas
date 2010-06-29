<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@ page contentType="text/html;charset=windows-1252"%>
<%@ include file="/taglibs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type"
	content="text/html; charset=windows-1252" />
	
	<fmt:setBundle basename="ApplicationResources" />
<title>Grave error del sistema</title>


<link href="style/style.css" rel="stylesheet" type="text/css" />

</head>
<body>
<div id="header"><%@ include file="../header.jsp"%></div>

<div id="navcontainer"><%@ include file="listamenu.jsp"%></div>
	
	<div id="center">
		<div id="centrado">
		<ul><li><fmt:message key="error.RuntimeException"/></li></ul>
		</div>
	</div>

<div id="footer"><%@ include file="../footer.jsp"%></div>

</body>
</html>
