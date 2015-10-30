<script src="scripts/logon.min.js"> </script>
<link href="styles/tables.css" rel="stylesheet" type="text/css" />
<style type="text/css">
<!--
.eliminarBoton {
	font-family: Verdana, Arial, Helvetica, sans-serif;
	font-weight: bold;
}
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
	width: 100%;
	float: center;
	align:center
}
#content h1, #content h2, #content p {
	padding-right: 20px;
}
.msgCampos {color: #FF0000;font-size: small;}
-->
</style>
	
	<div id="wrapper">
		<div id="content">
			<table class="hovertable" width="100%" height="100%">
			  <tr>
				<th colspan="3"><div align="center">Por favor registre su informacion</div></th>
			  </tr>
			  <tr class="encabezado">
				<th width="30%">Usuario: </th>
				<th width="30%"><input type="text" size="20" id="user"></th>
				<th width="40%"><div id="userMessage"></div></th>
			  </tr>
			  <tr class="encabezado">
				<th width="30%">Contraseña: </th>
				<th width="30%"><input type="password" size="20" id="passwd"></th>
				<th width="40%"><div id="userPasswd"></div></th>
			  </tr>
			  <tr class="encabezado">
				<th colspan="3">
					<div align="center">
						<input type="button" value="Entrar al sistema" id="entrar">
					</div>
				</th>
			  </tr>
			</table>
		</div>
	</div>
	
	<div style="display:none">
		<form id="form3" action="loguearse" method="post">
			<input name="user" type="hidden" id="jsUser"/>
			<input name="password" type="hidden" id="jsPassword"/>
		</form>
	</div>