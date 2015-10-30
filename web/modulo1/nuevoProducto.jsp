<script type="text/javascript" src="scripts/nuevoProducto.min.js"> </script>
<form id="myform">
	<table width="200" border="1">
	  <tr>
		<td>Nombre producto: </td>
		<td colspan="2"><input name="nombre" type="text" id="jsNombre"/></td>
	  </tr>
	  <tr>
		<td>Precio: $</td>
		<td colspan="2"><input name="precio" type="text" id="jsPrecio"/></td>
	  </tr>
	  <tr>
		<td>Descripcion: </td>
		<td colspan="2"><textarea name="descripcion" id="jsDescripcion" cols="30" rows="3"></textarea></td>
	  </tr>
	  <tr>
		<td>Codigo de barras: </td>
		<td colspan="2"><input name="codigo" type="text" id="jsCodigo"/></td>
	  </tr>
	  <tr>
		<td>Unidad de venta: </td>
		<td>
			<input name="radiobutton" type="radio" value="radiobutton" checked="checked" />
		</td>
		<td>
			<input name="radiobutton" type="radio" value="radiobutton" checked="checked" />
		</td>
	  </tr>
	</table>
</form>
<div id="datepicker"></div>