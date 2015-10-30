<%-- 
    Document   : index
    Created on : 19/06/2013, 11:48:17 AM
    Author     : Jorge
--%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
		<%
			response.sendRedirect("nuevaVenta");
		%>
		<s:form action="doUpload" method="post" enctype="multipart/form-data">
			<s:file name="upload" label="File"/>
			<s:submit/>
		</s:form>

        <h1>Hello World!</h1>
        <p><a href="<s:url action='struts'/>">Hello World</a></p>
        <br>
        <s:url action="struts" var="strutsLink">
          <s:param name="userName">Bruce Phillips</s:param>
        </s:url>
        <p><a href="${strutsLink}">Hello Bruce Phillips</a></p>
        <hr>
        <p>Get your own personal hello by filling out and submitting this form.</p>

        <s:form action="struts">

          <s:textfield name="userName" label="Your name: " />
		  <s:submit value="Submit" />

        </s:form>
        <p><a href="<s:url action='register'/>">Register</a></p>
        <br>
        <p><a href="<s:url action='userSpringAction'/>">Spring</a></p>
        <br>
        <p><a href="<s:url action='decorator'/>">Decorators</a></p>
        <p><a href="<s:url action='jquery'/>">Using JQuery</a></p>
    </body>
</html>
