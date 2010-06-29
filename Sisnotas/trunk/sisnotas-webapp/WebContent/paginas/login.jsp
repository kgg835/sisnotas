<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@ page contentType="text/html;charset=windows-1252"%>
<%@ include file="/taglibs.jsp" %>

<html:html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=windows-1252"/>
    <fmt:setBundle basename="ApplicationResources"/>
    <title>
      <fmt:message key="login.titulo"/>
    </title>
    <style type="text/css">
  	/*para todos los links*/
#header a:link {
	color: #FF0000
}

#header a:visited {
	color: #00FF00
}

#header a:hover {
	font-size: 150%
}


body {
	margin: 0; /* without margins the page would be stuck to the sides*/
	font-family: verdana, arial, sans-serif;
	/* base font is defined in the page */
	font-size: 12px; /* size font is defined in the page */
}

#navcontainer {
	position: absolute; /*  truco */
	left: 0;
	width: 180px;
	border-right: 8px solid #000;
	padding: 0 0 1em 0;
	margin-bottom: 1em;
	font-family: Verdana, Lucida, Geneva, Helvetica, Arial, sans-serif;
	background-color: #90bade;
	color: #333;
}

#navcontainer ul {
	list-style: none;
	margin: 0;
	padding: 0;
	border: none;
}

#navcontainer li {
	border-bottom: 1px solid #90bade;
	margin: 0;
}

#navcontainer li a {
	display: block;
	padding: 1px 1px 1px 0.5em;
	border-left: 10px solid #1958b7;
	border-right: 10px solid #508fc4;
	background-color: #2175bc;
	color: #fff; /*color de letra*/
	text-decoration: none;
	width: 100%;
	font-size: 11px;
	font-family: cursive;
}

html>body #navcontainer li a {
	width: auto;
}

#navcontainer li a:hover {
	border-left: 10px solid #1c64d1;
	border-right: 10px solid #5ba3e0;
	background-color: #2586d7;
	color: #fff;
}

#center {
	
	/* the centerff block is placed according to the container block's width */
	width: 100%;
	text-align: center;
	background-color: #B7D3F0;
	border-bottom: 2px solid #FFCA5E;
}



h1 {
	font-size: 200%;
	font-family: Verdana, Lucida, Geneva, Helvetica, Arial, sans-serif;
	text-align: center;
}

h2 {
	font: 1.8em/ 1.8em Arial, Helvetica, sans-serif;
	text-align: center;
	text-indent: 10px;
	height: 30px;
	color: #FFAA00;
}

ul,li {
	list-style-type: none; /* to minimize problems */
	margin: 0;
	padding: 0;
	line-height: 30px; /* extra space */
}



.info {
	padding: 1em;
	margin: 1em;
	border-width: 0.2ex;
}

#footer {
	font-family: Arial, Helvetica, sans-serif;
	font-size: 13px;
	color: green;
	width: 600px;
	margin-left: 200px;
	
	text-align: center;
}

#header {
	font-family: Arial, Helvetica, sans-serif;
	color: red;
	width: 100%;
	border-top: 2px solid #FFCA5E;
	background: url(imagenes/marble.jpg);
	height: 89px;
	border-bottom: 2px solid #FFCA5E;
}

#header span {
	display: block;
	text-align: right;
}

#logo1 {
	position: absolute;
}

#logof {
	margin-top: 0;
	text-align: right;
}

input.btn {
	margin: 1em;
	padding: .3ex;
	color: #fff;
	background-color: #005e21;
	font-family: helvetica, tahoma, arial, verdana, sans-serif;
	font-size: 1.5ex;
	border-width: 0;
}
input.button {
	margin: 1em;
	padding: .3ex;
	color: #fff;
	
	background-image: url(imagenes/images.jpg);	
	font-family: helvetica, tahoma, arial, verdana, sans-serif;
	font-size: 1.5ex;
	border-width: 0;
}

.info {
	padding: 1em;
	margin: 1em;
}

fieldset {
	border: 1;
	padding: 10px;
}

legend{
	font-family: "Trebuchet MS", Vendana, Arial, sans-serif;
	font-size: 14pt;
	font-weight: bold;
	border: 1;
	margin-top:0;
	padding-top:0;
	margin-bottom: 0;
	padding-bottom: 0;
	color: #98bb79;
}

.info_bold {
	font-weight:bold;
	font-weight: normal;
	margin: 1em;
}


#centrado{
	margin-left: 200px;
}

    </style>
  </head>
  <body>
<div id="header">  <%@ include file ="/header.jsp" %></div>
<div id="center">
 
  <html:errors property="login"/>
  <html:form action="login.do" focus="usuario">
  <table cellspacing="3" cellpadding="2" border="0" width="100%">
      <tr>
        <td colspan="2" align="center">
          <h1><fmt:message key="login.texto"/></h1>
        </td>
      </tr>
      <tr>
        <td align="right" width="52%">
          <fmt:message key="login.usuario"/>
        </td>
        <td align="left" width="48%"><html:text property="usuario" size="15"/>
        <html:errors property="usuario" /></td>
      </tr>
      <tr>
        <td align="right" width="52%">
          <fmt:message key="login.password"/>
        </td>
        <td align="left" width="48%">
        <html:password property="password" size="15" redisplay="false"/>
        <html:errors property="password" /></td>
      </tr>
      <tr>
        <td colspan="2" align="center">
        <html:submit styleClass="btn">
              <fmt:message key="login.boton.login"/>
        </html:submit>
        </td>
      </tr>
    </table>
    </html:form>
    
     </div>
<div id="footer">
    <%@ include file="/footer.jsp" %></div>
    </body>
</html:html>
