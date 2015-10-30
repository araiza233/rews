<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<link rel="stylesheet" href="scripts/css/ui-lightness/jquery-ui-1.10.3.custom.css" />
<script src="scripts/js/jquery-1.10.2.min.js"></script>
<script src="scripts/js/jquery-ui-1.10.3.custom.min.js"></script> 
<link rel="stylesheet" href="css/menu/style.css" type="text/css" media="screen"/>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title><decorator:title default="Sistema de tienditas"/></title>
</head>
<body>
        <!--<s:property value="%{#session.usuario}"/>-->
	<s:if test="%{#session.accesosList==null}">
            
	</s:if>
	<s:else>
            <ul id="nav">
	<s:iterator value="%{#session.accesosList}" var="item"> <!-- Aqui mi lista que contiene las secciones a mostrar -->  

		<s:if test="%{#item.url == 'nuevaVenta'}"> 
			<li>
				<a href="nuevaVenta" target="_blank">Nueva venta</a>
			</li>
		</s:if>
        <s:elseif test="%{#item.url == 'buscarProducto'}">
            <li>
                    <a href="buscarProducto">Productos</a>
            </li>
        </s:elseif> 
        <s:elseif test="%{#item.url == 'reportucos'}">
		<li>
			<a href="mostrarReporte?selectReporte=4">Reportes</a>
		</li>
        </s:elseif>
        <s:elseif test="%{#item.url == 'password'}">
		<li>
			<a href="password">Modificar password</a>
		</li>
        </s:elseif>
		<s:elseif test="%{#item.url == 'crearUser'}">
		<li>
			<a href="crearUser?mostrar=1">Crear Nuevo Usuario</a>
		</li>
        </s:elseif>
		<s:elseif test="%{#item.url == 'modificarUsers'}">
		<li>
			<a href="crearUser?mostrar=3">Modificar usuarios</a>
		</li>
        </s:elseif>
        <s:elseif test="%{#item.url == 'precios'}">
		<!--
		<li>
			<a href="precios?opcion=1">Precios a usar</a>
		</li>
                -->
        </s:elseif>
        <s:elseif test="%{#item.url == 'accesos'}">
		<li>
			<a href="accesos">Salir</a>
		</li>
        </s:elseif>
	</s:iterator>
		
    </ul><br><hr>
	</s:else>
    <decorator:body/>
	<img src="images/footer.jpg"/>
</body>
</html>