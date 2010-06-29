<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@ page contentType="text/html;charset=windows-1252"%>
<%@ include file="/taglibs.jsp"%>
<html>
	<head>
		<meta http-equiv="Content-Type"
			content="text/html; charset=windows-1252" />
		<title>Parametros Curso</title>
		<script language="JavaScript" type="text/javascript">
function validar2(form)
{
	var cmb;
	for (var i=0;i < document.forms[0].elements.length;i++)
	{
		var elemento = document.forms[0].elements[i];	
		if (elemento.type == "select-one")
		{
		cmb = elemento;
		}
	}
	if(cmb!= null){
    if (cmb.options[cmb.selectedIndex].value == "")
    {
    alert("Por favor, seleccione una opción válida");
    	cmb.focus(); return true;
    }
    }
    form.action.value='aceptarParam';
    form.submit();
}
function validar3(form)
{
	var cmb;
	for (var i=0;i < document.forms[0].elements.length;i++)
	{
		var elemento = document.forms[0].elements[i];	
		if (elemento.type == "select-one")
		{
		cmb = elemento;
		}
	}
	if(cmb!= null){
    if (cmb.options[cmb.selectedIndex].value == "")
    {
    alert("Por favor, seleccione una opción válida");
    	cmb.focus(); return true;
    }
    }
    form.action.value='aceptarParamCurso';
    form.submit();
}
function validar()
{
	var f = document.forms[0];
	var cmb = f.cmbdocentes;
    if (f.cmbdocentes.options[f.cmbdocentes.selectedIndex].value == "")
    {
    alert("Por favor, seleccione una opción válida");
    	f.cmbdocentes.focus();
    }
    else{
    	f.action.value='aceptarDocente';
    	f.submit();
    }
}     
</script>


		<link href="style/style.css" rel="stylesheet" type="text/css" />

	</head>
	<body>
		<div id="header">
			<%@ include file="../header.jsp"%>
		</div>

		<div id="navcontainer">
			<%@ include file="/paginas/listamenu.jsp"%>
		</div>

		<div id="center">
			<fmt:setBundle basename="ApplicationResources" />
			<div id="parametros">
				<h2>
					Curso
				</h2>
				<html:form action="parametrosAction.do">
					<input type="hidden" name="action" />
					<input type="hidden" name="codigo" />
					<input type="hidden" name="seccion" />
					<c:if
						test="${sessionScope.usuario.codGrpUsr==3 and paramCurso.param_definidos==0 and paramCurso.hayTiposDePruebaDefinidos==true}">
						<a class="info"
							style="font-size: 12px;text-align: center; display: block;"
							href="parametrosAction.do?action=definidosParametrosCurso">¿Acabo
							de definir los parametros de calificacion para este curso?</a>
					</c:if>
					<fieldset>
						<legend>
							<fmt:message key="paramCurso.Info" />
						</legend>
						<%@ include file="infoCurso.jsp"%></fieldset>
					<c:if
						test="${sessionScope.usuario.codGrpUsr==3 or(sessionScope.usuario.codGrpUsr!=3 and parametrosSistema.parametros_cursos==1 )}">

						<fieldset>
							<legend>
								<fmt:message key="paramCurso.pruebasEstablecidas" />
							</legend>
							<table>
								<thead>
									<tr>
										<th>
											<fmt:message key="paramCurso.nombrePrueba" />
										</th>
										<th>
											<fmt:message key="paramCurso.nombreTipoPrueba" />
										</th>
										<th>
											<fmt:message key="paramCurso.cantidad" />
										</th>
										<th>
											<fmt:message key="paramCurso.numEliminados" />
										</th>
										<th>
											<fmt:message key="paramCurso.peso" />
										</th>
										<th>
											<fmt:message key="paramCurso.acciones" />
										</th>
									</tr>
								</thead>
								<c:choose>
									<c:when test="${not empty paramCurso.tiposDePrueba}">
										<c:forEach items="${paramCurso.tiposDePrueba}" var="c"
											varStatus="status">
											<c:choose>
												<c:when test="${(status.count % 2) == 0}">
													<tr>
												</c:when>
												<c:otherwise>
													<tr>
												</c:otherwise>
											</c:choose>
											<td>
												<c:out value="${c.nombre}" />
											</td>
											<td>
												<c:out value="${c.nombreTipoPrueba}" />
											</td>
											<td>
												<c:if test="${c.examen==false}">
													<c:out value="${c.cursoTipoPrueba.numero}" />
												</c:if>
											</td>
											<td>
												<c:if test="${c.examen==false}">
													<c:out value="${c.cursoTipoPrueba.numelim}" />
												</c:if>
											</td>
											<td>
												<c:if test="${c.examen==false}">
													<c:out value="${c.cursoTipoPrueba.peso}" />
												</c:if>
											</td>
											<td>
												<c:if
													test="${paramCurso.param_definidos==0 and sessionScope.usuario.codGrpUsr==3}">
													<a
														href="parametrosAction.do?action=eliminarTipoPrueba&tipPba=<c:out value="${c.codTipPba}" />"><fmt:message
															key="paramCurso.eliminar" />
													</a>
													<c:if test="${c.examen==false}">
														<a
															href="tipoPruebaCursoAction.do?action=editarPrueba&tipPba=<c:out value="${c.codTipPba}" />"><fmt:message
																key="paramCurso.editar" /> </a>
													</c:if>
												</c:if>
											</td>
											</tr>
										</c:forEach>
									</c:when>
									<c:otherwise>
										<tr>
											<td colspan="6">
												<fmt:message key="paramCurso.nohayparametros"></fmt:message>
											</td>
										</tr>
									</c:otherwise>
								</c:choose>
							</table>
						</fieldset>
					</c:if>
					<c:if
						test="${paramCurso.param_definidos==0 and sessionScope.usuario.codGrpUsr==3}">
						<fieldset>
							<legend>
								<fmt:message key="paramCurso.examenes" />
							</legend>
							<c:choose>
								<c:when test="${not empty examenesDisponibles}">
									<c:forEach items="${examenesDisponibles}" var="ex"
										varStatus="status">
										<p id="examenes">
											<label for="examen">
												<c:out value="${ex.nombre}" />
											</label>
											<input type="checkbox" name="examen"
												onclick="javascript:document.forms[0].action.value='addExamen';javascript:document.forms[0].submit();"
												value="<c:out value="${ex.codTipPba}" />">
										</p>
									</c:forEach>
								</c:when>
								<c:otherwise>
									<p class="info">
										No hay examenes disponibles
									</p>
								</c:otherwise>
							</c:choose>
						</fieldset>
					</c:if>
					<c:if test="${sessionScope.usuario.codGrpUsr!=3}">
						<fieldset>
							<legend>
								<fmt:message key="paramCurso.docenteResponsable" />
							</legend>
							<c:choose>
								<c:when test="${paramCurso.docenteResp != null}">
									<table>
										<thead>
											<tr>
												<th>
													<fmt:message key="paramCurso.codigo" />
												</th>
												<th>
													<fmt:message key="paramCurso.apellidosynombres" />
												</th>
												<th>
													<fmt:message key="paramCurso.accionesdocente" />
												</th>
											</tr>
										</thead>
										<tbody>
											<tr>
												<td>
													<c:out value="${paramCurso.docenteResp.codDoc}" />
												</td>
												<td>
													<c:out value="${paramCurso.docenteResp.nombreCompleto}" />
												</td>
												<!-- se va a permitir que se cambie al docente responsable en cualquier momento -->
												<td>
													<a href="parametrosAction.do?action=editarDocente"><fmt:message
															key="paramCurso.editarDocente" />
													</a>
												</td>
											</tr>
										</tbody>
									</table>
								</c:when>
								<c:otherwise>
									<table>
										<thead>
											<tr>
												<th>
													
												<br></th>
												<th>
													
												<br></th>
											</tr>
										</thead>
										<tbody>
											<tr>
												<td>
													<html:select property="cmbdocentes">
														<option value="">
															Seleccione un docente
														</option>
														<html:options collection="docentes" property="codUsr"
															labelProperty="nombreCompleto" />
													</html:select>
													<html:errors property="docentes" />
												</td>
												<td>
													<a href="javascript:validar()"><fmt:message
															key="paramCurso.aceptarDocente" /> </a>
												</td>
											</tr>
										</tbody>
									</table>
								</c:otherwise>
							</c:choose>
						</fieldset>
					</c:if>
					<div class="btnCentro">
						<c:choose>
							<c:when test="${sessionScope.usuario.codGrpUsr==3}">
								<c:if test="${paramCurso.param_definidos==0}">
									<input type="submit"
										value="<fmt:message key="paramCurso.add"/>" class="btn"
										onclick="javascript:document.forms[0].action.value='addPrueba';">
								</c:if>
								<input type="button"
									value="<fmt:message key="paramCurso.aceptar"/>" class="btn"
									onclick="validar3(this.form);">
							</c:when>
							<c:otherwise>
								<input type="button"
									value="<fmt:message key="paramCurso.aceptar"/>" class="btn"
									onclick="validar2(this.form);">
							</c:otherwise>
						</c:choose>
					</div>
				</html:form>
			</div>
		</div>

		<div id="footer">
			<%@ include file="../footer.jsp"%>
		</div>

	</body>
</html>
