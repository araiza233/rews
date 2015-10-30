<script src="scripts/modificarUser.min.js"> </script>
<%@ taglib prefix="s" uri="/struts-tags" %>
<link href="styles/tables.css" rel="stylesheet" type="text/css" />
<style type="text/css">
<!--                                 
#wrapper {
width: 100%;
margin: 0 auto;
text-align: center;
background:#CCCCCC;
align:center
}
body {
	text-align: center;
	background-color: #CCCCCC;
}
#content {
width: 90%;
float: center;
align:center
}
#content h1, #content h2, #content p {
padding-right: 20px;
}
.msgCampos {color: #FF0000;font-size: small;}
-->
</style>
	<div id="gettingInfo" title="Modificar usuario">
		<form id="form1" action="crearUser" method="post">
			<table class="hovertable" width="80%">
				  <tr>
					<th colspan="3"><div align="center">Modificacion de contraseña</div></th>
				  </tr>
				  <tr>
					<td align="right">
						Usuario: 
					</td>
					<td align="left" colspan="2">
						<div id="usuarioCambiar"> </div>
						<input type="hidden" name="id_usuario" id="id_usuario"/>
					</td>
				  </tr>
				  <tr>
					<td align="right">
						Nombre:
					</td>
					<td align="left">
						<input type="text" name="nombre" size="20" id="txtNombre"/>
					</td>
					<td align="left">
						<div id="divNombre" class="msgCampos"> </div>
					</td>
				  </tr>
				  <tr>
					<td align="right">
						Direccion:
					</td>
					<td align="left">
						<input type="text" name="direccion" size="20" id="txtDireccion"/>
					</td>
					<td align="left">
						<div id="divDir" class="msgCampos"> </div>
					</td>
				  </tr>
				  <tr>
					<td align="right">
						Telefono:
					</td>
					<td align="left">
						<input type="text" name="telefono" size="20" id="txtTelefono"/>
					</td>
					<td align="left">
						<div id="divTel" class="msgCampos"> </div>
					</td>
				  </tr>
				  <tr>
					<td align="right">
						Nueva contraseña:
					</td>
					<td align="left">
						<input type="password" size="20" name="password" id="password1"/>
					</td>
					<td align="left">
						<div id="msgPasswd1" class="msgCampos"> </div>
					</td>
				  </tr>
				  <tr>
					<td align="right">
						Repita contraseña:
					</td>
					<td align="left">
						<input type="password" size="20" name="password2" id="password2"/>
					</td>
					<td align="left">
						<div id="msgPasswd2" class="msgCampos"> </div>
					</td>
				  </tr>
				  <tr>
					<td align="center" colspan="3">
						<img src="images/button.jpg" width="100" height="40"  title="Editar" id="btnCambiar" class="botonsuco" style="cursor: pointer;"/>
					</td>
				  </tr>
			</table>
			<input type="hidden" name="mostrar" value="4"/>
		</form>
	</div>
	<div id="wrapper" align="center">
		<div id="content">
			<table class="hovertable" width="100%">
			  <tr>
				<th colspan="5"><div align="center">Modificacion de contraseña</div></th>
			  </tr>
			  <s:if test="%{#session.listaUsuarios==null}">
				<th colspan="5"><div align="center">No hay usuarios registrados</div></th>
			  </s:if>
			  <s:else>
              <tr>
				<th align="center">
					Alias
				</th>
				<th align="center">
					Nombre
				</th>
				<th align="center">
					Direccion
				</th>
				<th align="center">
					Telefono
				</th>
				<th align="center">
					Contraseña
				</th>
			  </tr>
			  <s:iterator value="%{#session.listaUsuarios}" var="item"> <!-- Aqui mi lista que contiene las secciones a mostrar -->  
			  <tr>
				<td align="left">
					<s:property value="%{#item.id_usuario}"/>
				</td>
				<td align="left">
					<s:property value="%{#item.nombre}"/>
				</td>
				<td align="left">
					<s:property value="%{#item.direccion}"/>
				</td>
				<td align="left">
					<s:property value="%{#item.telefono}"/>
				</td>
				<td align="left">
					<s:property value="%{#item.password}"/>
					<img src="images/editRecord.png" width="20" height="20"  title="Editar" id="btnPasswd" class="botonsuco" style="cursor: pointer;"/>
				</td>
              </tr>
			  </s:iterator>
			  </s:else>
            </table>
		</div>
	</div>