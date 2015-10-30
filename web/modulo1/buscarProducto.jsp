<%@taglib uri="/struts-tags" prefix="s" %>
<script src="scripts/buscarProducto.min.js"> </script>
<script src="scripts/jquery.scannerdetection.js"> </script>
<link href="styles/tables.css" rel="stylesheet" type="text/css" />
<style type="text/css">
<!--
.eliminarBoton {
	font-family: Verdana, Arial, Helvetica, sans-serif;
	font-weight: bold;
}
.msgCampos {color: #FF0000;font-size: small;}
.trclass:hover { background: #00B25C;}
.trclass:hover td { background: transparent; }
		
-->
</style>
    <s:if test="%{#session.mostrarGuardar==null}">
            <input type="text" id="mostrarGuardar" value="no" style="display:none">
	</s:if>
	<s:else>
		<s:if test="%{#session.mostrarGuardar=='si'}">
			<input type="text" id="mostrarGuardar" value="si" style="display:none">
		</s:if>
		<s:else>
			<input type="text" id="mostrarGuardar" value="no" style="display:none">
		</s:else>
	</s:else>
	<div id="gettingInfo" title="Obteniedo informacion">
		<p>Espere mientras se obtiene la informacion de los productos</p>
	</div>
	<div id="gettingImageInfo" title="Obteniedo informacion">
		<p>Subiendo imagen</p>
	</div>
	<div id="getImage" title="Imagen completa">
	</div>
	<form id="myform2">
		<table width="400" border="0">
		  <tr>
		    <td align="right">
			  <img src='images/addRecord.png' width='20' height='20' id='agregarRecord' alt='nuevo' title='Dar de alta nuevo producto' style='cursor: pointer;'/>
			</td>
			<td>
			  <p>Agregar nuevo producto</p>
			</td>
		  </tr>
		  <tr>
			<td align="right">Nombre producto: </td>
			<td align="left">
				<input name="nombre" type="text" id="autocompletar2" size="20"/>
			</td>
		  </tr>
		</table>
		<div id="dynamicTable"></div>
	</form>
	
	<div id="showData" title="Datos del registro">
	<form id="myform">
		<table width="625" border="0">
		  <tr>
			<td colspan="3"><div id="titulo"></div></td>
			<td rowspan="7"><div id="rigth"></div></td>
		  </tr>
		  <tr>
			<td width="200" align="right">Nombre producto: </td>
			<td width="125">
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
			<td align="right">Descripcion: </td>
			<td>
				<textarea name="descripcion" id="jsDescripcion" cols="30" rows="3" class="campos">
				</textarea>
			</td>
			<td>
				<div id="msgDescripcion" class="msgCampos"></div>
			</td>
		  </tr>
		  <tr>
			<td align="right">Codigo de barras: </td>
			<td>
				<input name="codigo" type="text" id="jsCodigo" class="campos"/>
			</td>
			<td>
				<div id="msgCodigo" class="msgCampos"></div>
			</td>
		  </tr>
          <tr>
			<td align="right">Numero de piezas: </td>
			<td>
				<input name="piezas" type="text" id="jsPiezas" class="campos"/>
			</td>
			<td>
				<div id="msgPiezas" class="msgCampos"></div>
			</td>
		  </tr>
		  <tr>
			<td align="right">Unidad de venta: </td>
			<td colspan="2">
				<input name="unidaddeventa" type="radio" value="kilogramos" id="jsUnidaddeventa" checked="checked"/>Gramos
				<input name="unidaddeventa" type="radio" value="pieza" id="jsUnidaddeventa"/>Pieza
			</td>
		  </tr>
		  <tr>
			<td align="right">Imagen del producto: </td>
			<td colspan="2">
				<input type="file" name="upload" id="upload"/>
			</td>
		  </tr>
		</table>
	</form>
	</div>