<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@ page contentType="text/html;charset=windows-1252"%>
<%@ include file="/taglibs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type"
	content="text/html; charset=windows-1252" />
<title>Consulta de notas</title>


<link href="style/style.css" rel="stylesheet" type="text/css" />

</head>
<body>
<div id="header"><%@ include file="../header.jsp"%></div>


	
<div id="center" style="margin-left: 0;width: 100%;">
<div id="centrado">
<form name="form1" method="post" action="consultar.do">
  <table >
    <tr>
    <th>Ciclo:</th>
      <td><select name="ciclo" onchange ="submit()">
      
          <c:forEach items="${ciclos}" var="c">    
         
           <c:choose>
              <c:when test="${c.peracad eq cicloU}">
                 <option value="<c:out value="${c.peracad}"/>" selected="true"><c:out value="${c.nombreperacad}"/></option> 
              </c:when>
              <c:otherwise>
                 <option value="<c:out value="${c.peracad}"/>"><c:out value="${c.nombreperacad}"/></option>
              </c:otherwise>
           </c:choose>
          </c:forEach>
          <c:if test="${parametrosSistema.inicio_periodo eq 1 }">
          <c:if test="${parametrosSistema.peracad eq cicloU }">
          <option value="<c:out value="${parametrosSistema.peracad}" />" selected="true"><c:out value="${parametrosSistema.nombre_periodo}"/></option>
          </c:if>
          <c:if test="${parametrosSistema.peracad != cicloU }">
          <option value="<c:out value="${parametrosSistema.peracad}" />" ><c:out value="${parametrosSistema.nombre_periodo}"/></option>
          </c:if>
          </c:if>
          </select>
         
           </td>
      <th>CODCUR:</th>
      <td><select name="codCur" onchange ="submit()">
      <option value="">--seleccione--</option>
      <c:forEach items="${cursos}" var="c">
      <c:choose>
      <c:when test="${c eq cursoU}">
       <option value="<c:out value="${c}"/>" selected="true"><c:out value="${c}"/></option> 
       </c:when>
       <c:otherwise>
       <option value="<c:out value="${c}"/>"><c:out value="${c}"/></option>
       </c:otherwise>
       </c:choose>
      </c:forEach>
      </select>
      </td>
      <th>SECCION:</th>
      <td><select name="seccion" onchange ="submit()">
       <option value="">--seleccione--</option>
      <c:forEach items="${secciones}" var="s">
      <c:choose>
      <c:when test="${s eq seccionU}">
       <option value="<c:out value="${s}"/>" selected="true"><c:out value="${s}"/></option> 
       </c:when>
       <c:otherwise>
       <option value="<c:out value="${s}"/>"><c:out value="${s}"/></option>
       </c:otherwise>
       </c:choose>
      </c:forEach>
        </select>
      </td>
      
    </tr>
  </table>
  <p>&nbsp;</p>
  
 <c:if test="${not empty TipoPrueba }">
   
  <table>
    <thead>
    <tr>
    
      <th  width="800">ALUMNO/PRUEBA</th>
      <c:forEach items="${TipoPrueba}" var="a" varStatus="num">
      <th width="10"><c:out value="${a}"/></th>
      <c:set var="i" value="${num.count }"/>
      </c:forEach>
    </tr>
    </thead>
    <c:set var="j" value="${1}"/>
    <c:set var="k" value="${i}"/>
    <tbody>
    <c:forEach items="${Alumno}" var="a">
    <tr>
    	<td ><c:out value="${a}" /></td>
    	<c:forEach items="${Nota}" var="n" varStatus="num">
    	<c:choose><c:when test="${num.count <= k && num.count >= j }">
    	<td ><c:out value="${n}"/></td>
    	</c:when>
    	</c:choose>
    	</c:forEach>
    	<c:set var="j" value="${j+i}"/>
    	<c:set var="k" value="${k+i}"/>
    </tr>
  </c:forEach>
  </tbody>
  </table>
 </c:if>
</form>
</div>
</div>
<div id="footer"><%@ include file="../footer.jsp"%></div>

</body>
</html>
