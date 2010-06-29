
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@ page contentType="text/html;charset=windows-1252"%>
<%@ include file="/taglibs.jsp"%>
<%@ page import="ccuni.java.sysNotas.domain.dto.*"%>
<html>

<head>
<meta http-equiv="Content-Type"
	content="text/html; charset=windows-1252" />
 <title>listadoCurso</title>
      <script language="JavaScript" type="text/javascript">
       function llenar(cursoId,seccion,perAcad){
         document.forms[0].cursoId.value=cursoId;
         document.forms[0].seccion.value=seccion;
         document.forms[0].perAcad.value=perAcad;      
       }
     
     
      </script>
 <link href="style/style.css" rel="stylesheet" type="text/css" />
</head>
<body>
<div id="header">
<%@ include file="../header.jsp"%>
</div>
<div id="navcontainer">
<%@ include file="listamenu.jsp"%>
</div>
<div id="center">
<div id="centrado" style="margin-top: 20px;">
<html:form action="/listaEvaluaciones.do">
	<input type="hidden" name="accion" />
	<input type="hidden" name="tipo" value="<c:out value='${param.tipo}'/>"/>
	<input type="hidden" name="num" value="<c:out value='${param.num}'/>"/>	
	
	<input type="hidden" name="auxEstado" value='<c:out value="${auxiEstado}"/>'/>	
	
		
		<c:out value='${param.tipo} ${param.num} '/>
		<html:select property="estado">			
				<html:options property="estados"/>
		</html:select>
		<html:errors property="errorCambioEstado"/>
	<br>
		<input class="btn" type="submit" value="save"
			onclick="javascript:document.forms[0].accion.value='save';"/>
		<input class="btn" type="submit" value="cancel"
			onclick="javascript:document.forms[0].accion.value='cancel';"/>
	
		
</html:form>

</div>



</div>
 


<div id="footer"><%@ include file="../footer.jsp"%>
</div>


</body>

</html>
