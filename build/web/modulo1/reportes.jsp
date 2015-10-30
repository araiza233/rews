<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<script src="scripts/reportes.js"> </script>
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
-->
</style>
	<div id="gettingInfo" title="Mensaje">
	</div>
	<div id="wrapper" align="center">
		<div id="content">
			<table class="hovertable" width="100%">
			  <tr>
				<th colspan="2"><div align="center">Generacion de reportes</div></th>
			  </tr>
			  <tr>
				  <th colspan="2">
					<select id="selectReporte" name="selectReporte">
					  <option value="1" selected="selected">Reporte detallado de ventas</option>
					  <option value="2">Reporte de abasto</option>
					  <option value="3">Reporte de inventario</option>
					</select>
				  </th>
			  </tr><strong>
			  <div id="usuarios">
                            <s:select name="usuario" key="Selecciona el usuario" list="usuariosList" listKey="id_usuario" listValue="nombre" />
			  </div></strong>
                          <tr id="fechas">
				<th width="50%">Fecha de inicio</th>
				<th width="50%">Fecha final</th>
			  </tr>
			  <tr id="fechas2">
				<td align="center">
					<input type="text" name="fechaInicial" id="fechaInicial">
				</td>
				<td align="center">
					<input type="text" name="fechaFinal" id="fechaFinal">
				</td>
			  </tr>
                          <tr>
                              <td colspan="2">
                              Articulos:  <input type="text" id="items" size="30"/>
                              </td>
                          </tr>
                          <tr id="articulos">
				<td align="center" colspan="2">
                                    <table id="itemList" border="1" width="100%">
					<tr>
						<td>Id</td>
						<td>Articulos</td>
						<td>Accion</td>
					</tr>
					<tr id="todos">
						<td>9999999999</td>
						<td>Todos</td>
						<td>&nbsp;</td>
					</tr>
					<tr class="granTotal" style="display:none">
						<td colspan="3">&nbsp;</td>
					</tr>
                                    </table>
				</td>
			  </tr>
			  <tr>
				<td colspan="2" align="center">
					<input type="button" value="Generar reporte" id="crearReporte">
				</td>
			  </tr>
			</table>
		</div>
                <form id="myform2">
                    <input name="nombre" type="hidden" id="nombree"/>
                </form>
	</div>