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
function validarUpdate(form)
{
	if(validarCampos(form)==0){
		return 0;
	}
	else{
		form.action.value='updatePrueba';
		form.submit();
	}
}

function validarCampos(form){
	var numero = form.numeropruebas;
	var eliminados= form.eliminados;
	var peso = form.pesoprueba;
	
	if(numero.value.length==0){
		alert("Debe escribir el numero de pruebas");
		numero.focus(); return 0;
	}
	if(eliminados.value.length==0){
		alert("Debe escribir el numero de pruebas que se eliminan");
		eliminados.focus(); return 0;
	}
	if(peso.value.length==0){
		alert("Debe escribir el peso de la prueba");
		peso.focus(); return 0;
	}
	//valido si son numeros validos
	if(!validarEntero(numero.value)){
		alert("El numero de pruebas debe ser un numero");
		numero.focus();
		numero.value="";
		return 0;
	}
	else{
		if(numero.value<=0){
		alert("El numero de pruebas debe ser mayor a cero");
		numero.focus();
		numero.value="";
		return 0;
		}
	}
	if(!validarEntero(eliminados.value)){
		alert("El numero de pruebas que se eliminan debe ser un numero");
		eliminados.focus();
		eliminados.value="";
		return 0;
	}
	else{
		if(eliminados.value<0){
		alert("El numero de pruebas eliminadas debe ser mayor o igual a cero");
		eliminados.focus();
		eliminados.value="";
		return 0;
		}
	}
	if(!validarEntero(peso.value)){
		alert("El peso debe ser un numero");
		peso.focus();
		peso.value="";
		return 0;
	}
	else{
		if(peso.value<=0){
		alert("El peso de la prueba debe ser mayor a cero");
		peso.focus();
		peso.value="";
		return 0;
		}
	}
	//
	if(eliminados.value>=numero.value){
		alert("El numero de pruebas que se eliminan debe ser menor al numero de pruebas que se toman");
		eliminados.focus();
		eliminados.value="";
		return 0;
	}
}

function validarSave(form){
		//valido combo
		var cmb = form.tipoprueba;
		if (cmb.options[cmb.selectedIndex].value == ""){
			alert("Debe seleccionar un tipo de prueba");
			cmb.focus(); return 0;
		}
		if(validarCampos(form)==0){
			return 0;
		}
		else{
			form.action.value='savePrueba';
			form.submit();
			return 0;
		}
	}

function validarEntero(v){
      //intento convertir a entero.
     //si era un entero no le afecta, si no lo era lo intenta convertir
     var valor = parseInt(v);

      //Compruebo si es un valor numérico
      if (isNaN(valor)) {
            //entonces (no es numero) devuelvo el valor cadena vacia
            return false;
      }else{
            //En caso contrario (Si era un número) devuelvo el valor
            return true;
      }
}
</script>
<link href="style/style.css" rel="stylesheet" type="text/css" />
</head>
<body onload="javascript:window.history.forward()">
<div id="header"><%@ include file="../header.jsp"%></div>
<div id="navcontainer"><%@ include file="/paginas/listamenu.jsp"%></div>
<div id="center"><fmt:setBundle basename="ApplicationResources" />
<p class="info"><fmt:message key="addprueba.text" /></p>
<%@ include file="infoCurso.jsp"%>
<div id="centrado"><html:form action="tipoPruebaCursoAction.do">
	<input type="hidden" name="action">
	<table>
		<tr>
			<th><fmt:message key="addprueba.tipoprueba" /></th>
			<td><c:choose>
				<c:when test="${pruebaParaEditar == null}">
					<select name="tipoprueba">
						<option value="">Selecciona</option>
						<c:forEach items="${tiposDePruebaDisponibles}" var="tp">
							<option value="<c:out value="${tp.codTipPba}" />"><c:out
								value="${tp.nombre}" /></option>
						</c:forEach>
					</select>
				</c:when>
				<c:otherwise>
					<c:out value="${pruebaParaEditar.nombre}" />
				</c:otherwise>
			</c:choose></td>
		</tr>
		<tr>
			<th><fmt:message key="addprueba.numero" /></th>
			<td><html:text property="numeropruebas" maxlength="2" /> <html:errors
				property="numeropruebas" /></td>
		</tr>
		<tr>
			<th><fmt:message key="addprueba.eliminados" /></th>
			<td><html:text property="eliminados" maxlength="2" /><html:errors
				property="eliminados" /></td>
		</tr>
		<tr>
			<th><fmt:message key="addprueba.peso" /></th>
			<td><html:text property="pesoprueba" maxlength="2" /> <html:errors
				property="pesoprueba" /></td>
		</tr>
	</table>
	<div class="btnCentro">
	<input type="submit" value="<fmt:message key="addprueba.cancel"/>"
		class="btn"
		onclick="javascript:document.forms[0].action.value='cancel';">
	<c:choose>
		<c:when test="${pruebaParaEditar == null}">
			<input type="button" value="<fmt:message key="addprueba.save"/>"
				class="btn" onclick="validarSave(this.form);">
		</c:when>
		<c:otherwise>
			<input type="button" value="<fmt:message key="addprueba.update"/>"
				class="btn" onclick="validarUpdate(this.form);">
		</c:otherwise>
	</c:choose>
	</div>
</html:form></div>
</div>
<div id="footer"><%@ include file="../footer.jsp"%></div>
</body>
</html>

