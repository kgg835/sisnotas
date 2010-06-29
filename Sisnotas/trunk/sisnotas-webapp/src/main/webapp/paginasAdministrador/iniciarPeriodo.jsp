<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@ page contentType="text/html;charset=windows-1252"%>
<%@ include file="/taglibs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type"
	content="text/html; charset=windows-1252" />
<title>Iniciar Periodo Académico</title>


<link href="style/style.css" rel="stylesheet" type="text/css" />

</head>
<body>
<div id="header"><%@ include file="../header.jsp"%></div>

<div id="navcontainer"><%@ include file="/paginas/listamenu.jsp"%></div>
	
	<div id="center">
<form action="iniciarPeriodo.do">
  <p>&nbsp;</p>
  <p>&nbsp;		</p>
  <table width="293" border="1">
    <tr>
      <td width="144">Nuevo Periodo Acad&eacute;mico: </td>
      <td><input type="text" name="nombrePerAcad" readonly="true" value="<c:out value="${nombrePerAcad}"/>"/>
      <input type="hidden" name="newPerAcad" value="<c:out value="${newPerAcad}"/>"/></td>
    </tr>
    <tr>
      <td colspan="2" align="center"><input class="btn" type="submit" name="Submit" value="Iniciar"></td>
    </tr>
  </table>
  <p>&nbsp;</p>
  <p>&nbsp;</p>
</form>
</div>
<div id="footer"><%@ include file="../footer.jsp"%></div>

</body>
</html>
