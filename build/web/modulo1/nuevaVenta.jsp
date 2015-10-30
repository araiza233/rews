<script src="scripts/nuevaVenta.min.js"> </script>
<script src="scripts/jquery.scannerdetection.js"> </script>
<link href="styles/tables.css" rel="stylesheet" type="text/css" />
<style type="text/css">
<!--
 .ui-autocomplete {
  max-height: 300px;
  overflow-y: auto;
  /* prevent horizontal scrollbar */
  overflow-x: hidden;
  }
  /* IE 6 doesn't support max-height
  * we use height instead, but this forces the menu to always be this tall
  */
  * 
  html .ui-autocomplete {
    height: 300px;
  }
.eliminarBoton {
	font-family: Verdana, Arial, Helvetica, sans-serif;
	font-weight: bold;
}
.msgCampos {color: #FF0000;font-size: small;}
.msgs {color: #FF0000;}
.descripcion{
	font-size: 14px;
	font-weight: bold;
}
.kilogramos{
	font-size: 15px;
	font-weight: bold;
	color: #777;
	height: 20px;
	padding-left: 10px;
	text-decoration: none;
	background-repeat: repeat-x;
	border:1px solid #777;
	border-radius:5px
}
.pieza{
	font-size: 15px;
	font-weight: bold;
	color: #777;
	height: 20px;
	padding-left: 10px;
	text-decoration: none;
	background-repeat: repeat-x;
	border:1px solid #777;
	border-radius:5px
}
.pago{
	font-size: 20px;
	font-weight: bold;
	border:1;
}
#rigth {
	width: 100%;
	float: left;
}
#left {
	width: 100%;
	float: left;
	padding-top: 20px;
	padding-bottom: 20px;
}
#wrapper {
	width: 100%;
	margin: 0 auto;
	text-align: left;
	background:#00FF00;
	height: 100%;
}
body {
	text-align: center;
	background-color: #CCCCCC;
}
#content {
	width: 75%;
	float: right;
}
#content h1, #content h2, #content p {
	padding-right: 20px;
}
#mainNav {
	width: 20%;
	float: left;
	padding-top: 20px;
	padding-bottom: 20px;
}
#footer {
	clear: both;
}

#mainNav li {
	padding-left: 20px;
	padding-right: 20px;
}
.divxs{
    width:100%;
    border:1px solid black;
	float:center;
}
.serviceBox1, .serviceBox2, .serviceBox3, .serviceBox4 {
    float:left;
    width:15%;
}
-->
</style>
	<div id="gettingInfo" title="Obteniedo informacion" class="pago">
		<p>Espere mientras se obtiene la informacion de los productos</p>
	</div>
	<div id="getPago" title="Pago!">
		<table border="0">
			<tr><td class="pago">
				<div id="jsTotal"></div>
				</td>
			</tr>
			<tr>
				<td>
				Va a pagar con:<input type="text" maxlength="10" size="8" id="jsPago2"/><div id="msgPago" class="msgCampos"></div>
				</td>
			</tr>
			<tr>
				<td class="pago">
					<div id="msgCambio"></div>
				</td>
			</tr>
		</table>
	</div>
	<div id="getImage" title="Imagen completa" class="pago">
	</div>
	<div id="wrapper">
		<div id="content">
			<table class="hovertable" width="100%" height="100%">
			  <tr>
				<th colspan="6" align="center">
					<div class="divxs">
						<div class="" style="float:left;">Detalles de venta (Precio:</div>
						<div class="" id="precio" style="float:left; width:auto;"><%=session.getAttribute("precios")%></div>
						<div class="" style="float:left">)</div>
					</div>
				</th>
			  </tr>
			  <tr class="encabezado">
				<th width="5%">#</th>
				<th width="40%">Producto</th>
				<th width="35%">Pieza/Gramos</th>
				<th width="10%">Precio</th>
				<th width="10%">Total</th>
			  </tr>
			  <tr class="granTotal">
				<th colspan="4"><div align="right">Gran total </div></th>
				<th><div id="jsGranTotal"></div></th>
			  </tr>
			  <tr>
				<td colspan="5" class="msgCampos">
					Enter = Cambiar de articulo<br>
					C = Cobrar<br>
					B = Borrar articulo de la venta<br>
					N = Nueva venta<br>
					I = Imprimir ultima venta<br>
					A = Actualizar
				</td>
			  </tr>
			</table>
		</div>
		<div id="mainNav">
			<table class="hovertable" width="250px">
				<tr>
					<div id="itemName"></div><br>
					<th width="100px" colspan="2" align="left">Nombre producto: </th>
				</tr>
				<tr>
					<td colspan="2" width="100px" align="left">
						<input type="text" maxlength="40" id="autocompletar" size="40" border="1" class="nada"/>
						<form id="myform2">
							<input name="nombre" type="hidden" id="nombree"/>
						</form>
					</td>
				</tr>
				<tr>
					<td colspan="2" align="left">
						<div id="detallesProducto">
							<div id="left"> </div>
							<div id="rigth"> </div>
						</div>
					</td>
				</tr>
				<tr>
					<th><div align="right">Gran total </div></th>
					<th><div id="jsGranTotal2"></div></th>
				</tr>
			</table>
		</div>
	</div>
	
	<div style="display:none">
		<form id="form3">
			<input name="totalVenta" type="hidden" id="jsTotalVenta"/>
			<input name="usuario" type="hidden" id="jsUsuario" value="<%=session.getAttribute("usuario")%>"/>
			<input name="productos" value="1" type="hidden" id="jsDetalles"/>
			<input name="pago" value="1" type="hidden" id="jsPago"/>
			<input name="cambio" value="1" type="hidden" id="jsCambio"/>
		</form>
	</div>
	<div id="showData" title="Datos del registro">
		<form id="myform">
			<table width="700" border="1">
			  <tr>
				<td colspan="3"><h1>Dar de alta articulo</h1></td>
			  </tr>
			  <tr>
				<td width="200" align="right">Nombre producto: </td>
				<td width="150">
					<input name="nombre" type="text" id="jsNombre" class="campos"/>
					<input name="idProducto" type="hidden" id="jsId"/>
					<input name="usuario" value="<%=session.getAttribute("usuario")%>" type="hidden" id="jsUsuario"/>
					<input name="totalVenta" type="hidden" id="jsTotalVenta"/>
				</td>
				<td width="300">
					<div id="msgNombre" class="msgCampos"></div>
				</td>
			  </tr>
			  <tr>
				<td align="right">Precio: $</td>
				<td>
					<input name="precio" type="text" id="jsPrecio" class="campos"/>
				</td>
				<td>
					<div id="msgPrecio" class="msgCampos"></div>
				</td>
			  </tr>
			  <tr>
				<td>
					<input name="descripcion" type="hidden" id="jsDescripcion" value="---"/>
					<input name="codigo" type="hidden" id="jsCodigo"/>
					<input name="unidaddeventa" type="hidden" value="kilogramos" id="jsUnidaddeventa" />
				</td>
			  </tr>
			</table>
		</form>
	</div>
	<div id="showGuardar" title="Cargando">
		<img src="images/loading.gif"/>
	</div>
	<div id="showActualizar" title="Actualizar registro">
		<form id="myformActualizar">
			<table width="700" border="1">
			  <tr>
				<td colspan="3"><h1>Actualizar articulo</h1></td>
			  </tr>
			  <tr>
				<td width="200" align="right">Nombre producto: </td>
				<td width="150">
					<input name="nombre" type="text" id="jsNombreActualizar" class="actualizar"/>
					<input name="idProducto" type="hidden" id="jsIdActualizar"/>
					<input name="usuario" value="<%=session.getAttribute("usuario")%>" type="hidden" id="jsUsuarioActualizar"/>
				</td>
				<td width="300">
					<div id="msgNombreActualizar" class="msgCampos"></div>
				</td>
			  </tr>
			  <tr>
				<td align="right">Precio: $</td>
				<td>
					<input name="precio" type="text" id="jsPrecioActualizar" class="actualizar"/>
				</td>
				<td>
					<div id="msgPrecioActualizar" class="msgCampos"></div>
				</td>
			  </tr>
			  <tr>
				<td align="right">Piezas a agregar: </td>
				<td>
					<input name="piezas" type="text" id="jsCantidadActualizar" class="actualizar" value="0"/>
				</td>
				<td>
					<div id="msgCantidadActualizar" class="msgCampos"></div>
				</td>
			  </tr>
			</table>
		</form>
	</div>