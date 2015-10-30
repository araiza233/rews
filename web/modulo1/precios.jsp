<%@ taglib prefix="s" uri="/struts-tags" %>

<h1>Precio actual seleccionado: <s:property value="precioSeleccionado" /></h1>
<br>
<h3>
<s:form action="precios">
	
  <s:radio label="Precios" name="precioSeleccionado" list="precioOpciones" value="getDefaultPrecioValue" />
  <input type="hidden" name="opcion" value="2"/>
  <s:submit value="Modificar" />
 </h3>
</s:form>