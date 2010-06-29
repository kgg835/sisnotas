l administra<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@ page contentType="text/html;charset=windows-1252"%>
<%@ include file="/taglibs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type"
	content="text/html; charset=windows-1252" />
<title>listadoCurso</title>
<script language="JavaScript" type="text/javascript">
function validarSave(form)
{
	//si existe el input nombre lo valido
	if(validarCampo(form.codigo)&&validarCampo(form.nombre)){
		form.action.value='saveTipoPrueba';
		form.submit();
	}
}

function validarCampo(campo){
	var RegExPattern = /^[a-zA-Z\s_А-За-з]{1,40}$/;
	if(campo.value.length==0){
		alert("Debe llenar el campo");
		campo.focus();
		return false;
	}
	if (!campo.value.match(RegExPattern)) { 
    	 alert("El campo no debe contener numeros"); 
    	 campo.focus(); 
    	 return false;
    }
    return true;
}


function validarUpdate(form){
	//si existe el input nombre lo valido
	//if(!validarCampo(form.codigo))
		//return false;
	//valida el campo nombre
	if(!validarCampo(form.nombre))
		return false;
		
	form.action.value='updateTipoPrueba';
	form.codTipPba.value='<c:out value="${tipopruebaAmodificar.codTipPba}" />';
	form.submit();
}
</script>
<link href="style/style.css" rel="stylesheet" type="text/css" />
</head>
<body>
<div id="header"><%@ include file="../header.jsp"%></div>
<div id="navcontainer"><%@ include file="/paginas/listamenu.jsp"%></div>
<div id="center">
<fmt:setBundle basename="ApplicationResources" /> 
<p class="info"><fmt:message
	key="tipopruebapage.instrucciones" /></p>
<div id="centrado">
<form action="tipoPruebaAction.do"><input name="action" type="hidden"> <input
	name="codTipPba" type="hidden">
<table>
	<caption><fmt:message key="tipoprueba.tipodeprueba" /></caption>
	<tr>
		<th><fmt:message key="tipoprueba.codigo" /></th>
		<td><c:choose>
			<c:when test="${tipopruebaAmodificar != null}">
				<c:out value="${tipopruebaAmodificar.codTipPba}" />
			</c:when>
			<c:otherwise>
				<input type="text" name="codigo" maxlength="3">
			</c:otherwise>
		</c:choose></td>
	</tr>
	<tr>
		<th><fmt:message key="tipoprueba.nombre" /></th>
		<td><input type="text" name="nombre" value="<c:out value="${tipopruebaAmodificar.nombre}" />"></td>
	</tr>
</table>
</div>
<div class="btnCentro" id="btnCentroTipoPrueba">
<input type="submit" value="<fmt:message key="tipoprueba.cancel"/>"
	class="btn"
	onclick="javascript:document.forms[0].action.value='cancel';">
	 <c:choose>
	<c:when test="${tipopruebaAmodificar == null}">
		<input type="button" value="<fmt:message key="tipoprueba.save"/>"
			class="btn" onclick="validarSave(this.form);">
	</c:when>
	<c:otherwise>
		<input type="button" value="<fmt:message key="tipoprueba.update"/>"
			class="btn"
			onclick="validarUpdate(this.form);">
	</c:otherwise>
</c:choose>
</div>
</form>
</div>
<div id="footer"><%@ include file="../footer.jsp"%></div>
</body>
</html>
