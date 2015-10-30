<script src="scripts/crearUser.min.js"> </script>
<link href="styles/tables.css" rel="stylesheet" type="text/css" />
<style type="text/css">
<!--                                 
#wrapper {
width: 600px;
margin: 0 auto;
text-align: center;
background:#00FF00;
align:center
}
body {
	text-align: center;
	background-color: #CCCCCC;
}
#content {
width: 650px;
float: center;
align:center
}
#content h1, #content h2, #content p {
padding-right: 20px;
}
.mensajesError {
	color: #FF0000;
	font-weight: bold;
}
-->
</style>
	<div id="gettingInfo" title="Mensaje">
	</div>
	<div id="wrapper" align="center">
		<form id="form1" action="crearUser" method="post">
		<div id="content">
			<table class="hovertable" width="100%">
			  <tr>
				<th colspan="3"><div align="center">Creacion de nuevo usuario, perfil vendedor</div></th>
			  </tr>
              <tr>
				<th align="right">
					Alias:
				</th>
				<th align="left">
					<input type="text" name="id_usuario" id="jsId_usuario"/>
				</th>
				<th align="left">
					<div id="msjAlias" class="mensajesError"> </div>
				</th>
			  </tr>
			  <tr>
				<th align="right">
					Nombre del usuario:
				</th>
				<th align="left">
					<input type="text" name="nombre" id="jsNombre"/>
				</th>
				<th align="left">
					<div id="msjNombre" class="mensajesError"> </div>
				</th>
			  </tr>
			  <tr>
				<th align="right">
					Direccion:
				</th>
				<th align="left">
					<textarea name="direccion" id="jsDireccion" cols="30" rows="2"></textarea>
				</th>
				<th align="left">
					<div id="msjDireccion" class="mensajesError"> </div>
				</th>
              </tr>
			  <tr>
				<th align="right">
					Telefono:
				</th>
				<th align="left">
					<input type="text" name="telefono" id="jsTelefono"/>
				</th>
				<th align="left">
					<div id="msjTelefono" class="mensajesError"> </div>
				</th>
			  </tr>
			  <tr>
				<th align="right">
					Contraseña:
				</th>
				<th align="left">
					<input type="password" name="password" id="jsPassword" />
				</th>
				<th align="left">
					<div id="msjPassword" class="mensajesError"> </div>
				</th>
			  </tr>
			  <tr>
				<th align="right">
					Repita contraseña:
				</th>
				<th align="left">
					<input type="password" name="password2" id="jsPassword2"/>
				</th>
				<th align="left">
					<div id="msjPassword2" class="mensajesError"> </div>
				</th>
			  </tr>
			  <tr>
				<th align="center" colspan="3">
					<input type="submit" value="Crear usuario" id="btnCrearUser">
					<input type="hidden" name="id_rol" value="2"/>
					<input type="hidden" name="mostrar" value="2"/>
				</th>
			  </tr>
            </table>
		</div>
		</form>
	</div>