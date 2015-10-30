$(document).ready(function() {
	var cargarCodigo =777;
	$("#autocompletar2").scannerDetection(function(){
		cargarCodigo =999;
		$("#autocompletar2").keyup();
	});
	$("#autocompletar2").focus(function(){
		$(this).css( "border", "5px solid red");
	});
	$("#autocompletar2").focusout(function(){
			$(this).css( "border", "1px solid black");
	});
	function limpiarCampos(){
		$("#jsId").val("");
		$("#jsNombre").val("");
		$("#jsPrecio").val("");
		$("#jsDescripcion").val("");
		$("#jsCodigo").val("");
        $("#jsPiezas").val("");
	}
	//91586-20 DENUNCIA CIUDADANA
	$("#dynamicTable").on("click",".eliminarBoton",function(){
		$("#jsId").val($(this).closest('tr').find('td:first').text());
		$("#jsCodigo").val($(this).closest('tr').find('td').eq(1).text());
		$("#jsNombre").val($(this).closest('tr').find('td').eq(2).text());
		$("#jsPrecio").val($(this).closest('tr').find('td').eq(3).text());
		descripcion = $(this).closest('tr').find('td').eq(4).text();
		if(descripcion=='')
			descripcion="-";
		$("#jsDescripcion").val(descripcion);
        $("#jsPiezas").val($(this).closest('tr').find('td').eq(6).text());
		if ($(this).attr('alt')=='editar'){
			opciones = { autoOpen: false, modal: true, show: { effect: "blind", duration: 1000 }, hide: { effect: "explode", duration: 1000 },
				width:700,height:450,minWidth:300,minHeight:300,maxWidth:450,maxHeight:300,buttons:{ "Editar registro": editar, "Cancel": cancel }
			}
			$("#titulo").html("<h3>Editar articulo existente</h3>");
		}
		$("#showData").dialog(opciones);
		$("#showData").dialog({
			close:function(event, ui){$("#autocompletar2").focus();}
		});
		$("#showData").dialog("open");
		$(".campos").css("border","1px solid black");
		$(".msgCampos").html("");
		/*Nuevo codigo agregado para mostrar la imagen del prolductpo si esta ecxiste cuando se va modificar una foto ya existente a 
		un producto ya dado de alta, 26 de Diciembre 2013*/
		$("#rigth").html(traerImagen($("#jsCodigo").val()));
		$("#rigth").show(5000);
	});
	$("#agregarRecord").click(function(){
            var opciones;
            $("#titulo").html("");
            opciones = { autoOpen: false, modal: true, show: { effect: "blind", duration: 1000 }, hide: { effect: "explode", duration: 1000 },
                    width:700,height:450,minWidth:300,minHeight:300,maxWidth:450,maxHeight:450,buttons:{ "Crear registro": crear, "Cancel": cancel }
            };
            limpiarCampos();
            $("#titulo").html("<h3>Dar de alta articulo</h3>");
            $("#showData").dialog(opciones);
            $("#showData").dialog({
                close:function(event, ui){$("#autocompletar2").focus();}
            });
            $("#showData").dialog("open");
            $(".campos").css("border","1px solid black");
            $(".msgCampos").html("");
            $("#rigth").hide();
	});
	var editar = function(){
		if (validarProducto()){
			var formInput=$("#myform").serialize();
			$.getJSON('ajax/editarProducto',formInput,function(data){
				alert(data.resultado);
				subirImagen($("#upload").val());
			});//END getJSON
		}
	}
	var crear = function(){
		if (validarProducto()){
			var formInput=$("#myform").serialize();
			$.getJSON('ajax/guardar',formInput,function(data){
				if(data.resultado=='Ok'){
					alert('Registro guardado exitosamente');
					subirImagen($("#upload").val());
				}else{
					alert('Error: '+data.resultado);
				}
			});//END getJSON
		}
	}
	var cancel = function() {
		$("#showData").dialog("close");
		$("#autocompletar2").focus();
	}
	var dialogOpts2 = {autoOpen: false, modal: true, show: { effect: "blind", duration: 5 }, hide: { effect: "explode", duration: 1000 } }
	imagesOpciones = { autoOpen: false, modal: true, show: { effect: "blind", duration: 500 }, hide: { effect: "fadeOut", duration: 2000 },
				width:700,height:700,minWidth:700,minHeight:700,maxWidth:700,maxHeight:700, position: ["center", "center"]}	
	$("#gettingInfo").dialog(dialogOpts2);
	$("#showData").dialog(dialogOpts2);
	$("#getImage").dialog(imagesOpciones);
	$("#gettingImageInfo").dialog(dialogOpts2);
	$("#autocompletar2").keyup(function(){
		if(cargarCodigo ==999){
			cargarCodigo = 777;
			setTimeout(recargar(), 5000);
			
		}else{
			var formInput=$("#myform2").serialize();
			$.getJSON('ajax/buscarProducto', formInput, function(data){
				if(data.resultado=='Ok'){
					if(data.productoList.length>0){
						$("#dynamicTable").html("");
						var htmlTable = "<table class='hovertable' id='tablusca'>";
						htmlTable +="<tr>";
						htmlTable +="<th colspan='8'>LISTA DE PRODUCTOS</th>";						
						htmlTable +="</tr>";
						htmlTable +="<tr>";
						htmlTable +="<th style='display: none;'>Id</th>";
						htmlTable +="<th>Codigo</th>";
						htmlTable +="<th>Nombre</th>";
						htmlTable +="<th>Precio</th>";
						htmlTable +="<th>Descripcion</th>";
						htmlTable +="<th>Unidad</th>";
						htmlTable +="<th>Piezas</th>";
						htmlTable +="<th>Editar</th>";
						//htmlTable +="<td>Eliminar</td>";
						htmlTable +="</tr>";
						cuantosRegistros = data.productoList.length;
						if(cuantosRegistros>100)
							cuantosRegistros= 100;
						for(i=0; i<cuantosRegistros; i++){
							htmlTable +="<tr class='trclass'>";
							htmlTable +="<td style='display: none;'>"+data.productoList[i].idProducto+"</td>";
							htmlTable +="<td>"+data.productoList[i].codigo+"</td>";
							htmlTable +="<td>"+data.productoList[i].nombre+"</td>";
							htmlTable +="<td>"+data.productoList[i].precio+"</td>";
							htmlTable +="<td>"+$.trim(data.productoList[i].descripcion)+"</td>";
							htmlTable +="<td>"+data.productoList[i].unidadDeVenta+"</td>";
								if(isNaN(data.productoList[i].piezas)){
									htmlTable +="<td>0</td>";
								}else{
									htmlTable +="<td>"+data.productoList[i].piezas+"</td>";
								}
							htmlTable +="<td> <img src='images/editRecord.png' width='20' height='20' class='eliminarBoton' alt='editar' title='Editar: "+data.productoList[i].nombre+"' style='cursor: pointer;'/></td> ";							
							htmlTable +="</tr>";
						}
						htmlTable +="</table>";
						$("#dynamicTable").html(htmlTable);
					}else{
						$("#dynamicTable").html("");
					}
				}else{
					$("#gettingInfo").dialog("open");
					$("#gettingInfo").html("");
					$("#gettingInfo").append("<p>"+data.resultado+"</p>");
				}
			});//END getJSON
		}
	});		
//FUNCIONES PARA HACER VALIDACIONES
/*
Funcion que permite unicamente letras,numeros y espacios
ASCII CODE:
48:0-57:9
65:A-90:Z
97 : a - 122 : z
32 : Space
209 : Ñ
241 : ñ
46 : .
*/
	function validarPrecio(cadena){
		var nada=0;
		for(i=0;i<cadena.length;i++){
			ascii = cadena.charCodeAt(i);
			if((ascii>=48 && ascii<=57)||(ascii==46)){
				if(ascii==46){
					nada++
					if(nada>1){
						return false;
					}
				}
			}else{
				return false;
			}
		}
		if(!isNaN(cadena)){
			//alert('cadena*2= '+parseFloat(cadena)*2);
			return true;
		}else{
			return false;
		}
	}
	function validarCodigo(cadena){
		var nada=0;
		if(cadena.length==0){
			return false;
		}
		for(i=0;i<cadena.length;i++){
			ascii = cadena.charCodeAt(i);
			//if((ascii>=48 && ascii<=57)){
			if((ascii>=48 && ascii<=57 || ascii>=65 && ascii<=90 || ascii>=97 && ascii<=122 || ascii==45)){
				nada++;
			}else{
				return false;
			}
		}
		return true;
	}
	function validarNombre(cadena){
		/*var nada=0;
		for(i=0;i<cadena.length;i++){
			ascii = cadena.charCodeAt(i);
			if((ascii>=48 && ascii<=57)||(ascii>=65 && ascii<=90)||(ascii>=97 && ascii<=122)||(ascii==32)||(ascii==209)||(ascii==241)||(ascii==46)){
				nada++
			}else{
				return false;
			}
		}*/
		return true;
	}
	function emptyField(cadena){
		if(cadena=='' || cadena==null){
			return false;
		}else{
			return true
		}
	}
	function validarProducto(){
//Validacion de NOMBRE msgNombre-jsNombre
		var RESULTADO = true;
		valNombre = $("#jsNombre").val();
		if(emptyField(valNombre)){
			isNombre = validarNombre(valNombre);
			if(isNombre==false){
				$("#msgNombre").html("");
				$("#msgNombre").html("<p>Por favor capture unicamente letras y numeros</p>");
				$("#jsNombre").css("border","1px solid red");
				RESULTADO = false;
			}else{
				$("#msgNombre").html("");
				$("#jsNombre").css("border","1px solid black");
			}
		}else{
			$("#msgNombre").html("");
			$("#msgNombre").html("<p>Por favor capture un nombre</p>");
			$("#jsNombre").css("border","1px solid red");
			RESULTADO = false;
		}
//Validacion de precio msgPrecio-jsPrecio
		valPrecio = $("#jsPrecio").val();
		if(emptyField(valPrecio)){
			isPrecio = validarPrecio(valPrecio);
			if(isPrecio==false){
				$("#msgPrecio").html("");
				$("#msgPrecio").html("<p>Por favor capture unicamente valores numericos</p>");
				$("#jsPrecio").css("border","1px solid red");
				RESULTADO = false;
			}else{
				$("#msgPrecio").html("");
				$("#jsPrecio").css("border","1px solid black");
			}
		}else{
			$("#msgPrecio").html("");
			$("#msgPrecio").html("<p>Por favor capture un precio</p>");
			$("#jsPrecio").css("border","1px solid red");
			RESULTADO = false;
		}
//Validacion descripcion: jsDescripcion-msgDescripcion
		valDescripcion = $("#jsDescripcion").val();
		if(emptyField(valDescripcion)){
			isDescripcion = validarNombre(valDescripcion);
			if(isDescripcion==false){
				$("#msgDescripcion").html("");
				$("#msgDescripcion").html("<p>Por favor capture unicamente letras y numeros</p>");
				$("#jsDescripcion").css("border","1px solid red");
				RESULTADO = false;
			}else{
				$("#msgDescripcion").html("");
				$("#jsDescripcion").css("border","1px solid black");
			}
		}else{
			$("#msgDescripcion").html("");
			$("#msgDescripcion").html("<p>Por favor capture un nombre</p>");
			$("#jsDescripcion").css("border","1px solid red");
			RESULTADO = false;
		}
//Validacion codigo: jsCodigo-msgCodigo
		valCodigo = $("#jsCodigo").val();
		if(emptyField(valCodigo)){
			isCodigo = validarCodigo(valCodigo);
			if(isCodigo==false){
				$("#msgCodigo").html("");
				$("#msgCodigo").html("<p>Por favor capture unicamente numeros</p>");
				$("#jsCodigo").css("border","1px solid red");
				RESULTADO = false;
			}else{
				$("#msgCodigo").html("");
				$("#jsCodigo").css("border","1px solid black");
			}
		}else{
			$("#msgCodigo").html("");
			$("#msgCodigo").html("<p>Por favor capture un codigo</p>");
			$("#jsCodigo").css("border","1px solid red");
			RESULTADO = false;
		}
//Validacion PIEZAS: jsPiezas-msgPiezas
		valPiezas = $("#jsPiezas").val();
		if(emptyField(valPiezas)){
			isPiezas = validarCodigo(valPiezas);
			if(isPiezas==false){
				$("#msgPiezas").html("");
				$("#msgPiezas").html("<p>Por favor capture unicamente numeros</p>");
				$("#msgPiezas").css("border","1px solid red");
				RESULTADO = false;
			}else{
				$("#msgPiezas").html("");
				$("#jsPiezas").css("border","1px solid black");
			}
		}else{
			$("#msgPiezas").html("");
			$("#msgPiezas").html("<p>Por favor capture un codigo</p>");
			$("#jsPiezas").css("border","1px solid red");
			RESULTADO = false;
		}                
		return RESULTADO;
	}//FIN function --validarProducto--
//FIN FUNCIONES PARA HACER VALIDACIONES
//INICIO FUNCIONES PARA VALIDAR SI EL PRODUCTO YA TIENE UNA IMAGEN REGISTRADA
	function traerImagen(codigo){
	   imagen = "images/imagesProducts/"+codigo+".jpg";
	   var etsiste = urlExists(imagen);
	   $("#rigth").html("");
	   if (etsiste==200){
			res = '<tr><td colspan="3"><img src="'+imagen+'" class="imagensusca" width="100" height="90" title="Ver imagen completa" style="cursor: pointer;"/>';
			res+='</tr></td>';
			$("#getImage").html("");
			$("#getImage").html("<img src='"+imagen+"' width='675' height='675'/>");
	   }else{
			res = '<tr><td colspan="3"><img src="images/notAvailable.jpg" width="100" height="90"/></td></tr>';
	   }
	   return res;
	}
	function urlExists(testUrl) {
	 var http = jQuery.ajax({
		type:"HEAD",
		url: testUrl,
		async: false
	  })
	  return http.status;
		  // this will return 200 on success, and 0 or negative value on error
	}
	
	$("#rigth").on("click",".imagensusca",function(){
		//alert('Mostrar imagen engrandecida');
		$("#getImage").dialog("open");
	});
	function subirImagen(imagenAsubir){
		if(imagenAsubir==''){
			nada++;
			
		}else{//Si el usuario selecciono una imagen se modifica el action de la forma para que se vaya a subir la imagen en el ACTION
			$("#myform").attr("action","doUpload");
			$("#myform").attr("method","post");
			$("#myform").attr("enctype", "multipart/form-data");
			//submit the form
			$("#gettingImageInfo").dialog();
			$("#gettingImageInfo").dialog("open");
			$("#myform").submit();
		}
	}
	function mostrarGuardarRes(){
		mostrar = $("#mostrarGuardar").val();
		if (mostrar=="si"){
			var opciones;
			$("#titulo").html("");
			opciones = { autoOpen: false, modal: true, show: { effect: "blind", duration: 1000 }, hide: { effect: "explode", duration: 1000 },
					width:700,height:450,minWidth:300,minHeight:300,maxWidth:450,maxHeight:450,buttons:{ "Crear registro": crear, "Cancel": cancel }
			};
			limpiarCampos();
			$("#titulo").html("<h3>Dar de alta articulo</h3>");
			$("#showData").dialog(opciones);
			$("#showData").dialog({
				close:function(event, ui){$("#autocompletar2").focus();}
			});
			$("#showData").dialog("open");
			$(".campos").css("border","1px solid black");
			$(".msgCampos").html("");
			$("#rigth").hide();
		}else{
			$("#autocompletar2").focus();
			$("#autocompletar2").val("");
		}
	}
	mostrarGuardarRes();
	function recargar(){
		
	}
//FIN FUNCIONES SI EL PRODUCTO YA TIENE UNA IMAGEN REGISTRADA QUE SE VA EDITAR	
 });//end doc ready