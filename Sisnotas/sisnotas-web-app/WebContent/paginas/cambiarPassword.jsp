<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@ page contentType="text/html;charset=windows-1252"%>
<%@ include file="/taglibs.jsp"%>
<html>
<head>
<fmt:setBundle basename="ApplicationResources"/>

<meta http-equiv="Content-Type"
	content="text/html; charset=windows-1252" />
<title><fmt:message key="cambiarPassword.titulo"/></title>
<link href="style/style.css" rel="stylesheet" type="text/css" />

</head>
<body>
<div id="header"><%@ include file="../header.jsp"%></div>
<div id="navcontainer"><%@ include file="/paginas/listamenu.jsp"%></div>
<div id="center">
<div id="centrado">
<html:form action="cambiarContrasenia.do" focus="password">
<html:errors property="cambiarPassword2" />
<table>
<caption>Cambiar Password</caption>
	<tr>
	  <td align="rigth"><fmt:message key="cambiarPassword.password"/></td>
	  <td align="left"> <html:password property="password" size="15" redisplay="false"/>
	  <html:errors property="password" /></td>
	 </tr>
	 <tr>
	  <td align="rigth"><fmt:message key="cambiarPassword.newpassword"/></td>
	  <td align="left"> <html:password property="newpassword" size="15" redisplay="false"/>
	  <html:errors property="newpassword" /></td>
	 </tr>
	 <tr>
	  <td align="rigth"><fmt:message key="cambiarPassword.confirmePassword"/></td>
	  <td align="left"> <html:password property="newpassword2" size="15" redisplay="false"/>
	  <html:errors property="newpassword2" /></td>
	 </tr>
	 
</table>
        <div align="middle" ><input class="btn" type="submit"
		value="<fmt:message key="cambiarContrasenia.boton.actualizar"/>"></input></div>
</html:form>
</div>
</div>
<div id="footer"><%@ include file="../footer.jsp"%></div>
</body>
</html>
