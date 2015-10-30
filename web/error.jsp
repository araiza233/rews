<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib uri="/struts-tags" prefix="html" %>
<html>
<head>
</head>
<body>
	<div align="center">
		<div style="text-align:center;">
			<fieldset style="height:400px;">
				<legend>Error</legend>
					<table border="0" width="100%" align="center" cellpadding="2">
						<tr>
							<td align="left" >
                                                                <%=session.getAttribute("mensajeError")%>
							</td>
						</tr>
					</table>
			</fieldset>
		</div>
    </div>
</body>
</html>