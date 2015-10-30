$(document).ready(function() {
	var dataComplete = new Array();
	var anularSeleccion = 777;
	var currentPiezas = 1;
	opScanner = {
		onComplete:function(){
			caja = $(this);
			caja.val(currentPiezas);
			calcular();
			setTimeout(function(){$("#autocompletar").focus();
									},200);
		},
		preventDefault:false
	};
	setTimeout(function(){$("#autocompletar").focus();}, 500);
	$("#autocompletar").focus(function(){
		$(this).css("border", "5px solid red");
	});
	$("#autocompletar").scannerDetection(function(){
		traerInfo(true);
	});
	var borrar = false;
	var consecutivo = 0;
	var enters = 0;
	$("#autocompletar").focus();
	$("#autocompletar").val("");
	opciones = { autoOpen: false, modal: true, show: { effect: "blind", duration: 500 }, hide: { effect: "explode", duration: 500 },
				close:function(event, ui){
					if(enters==777){
						nuevaVenta();
					}
				},
				width:250,height:200,minWidth:200,minHeight:200,maxWidth:200,maxHeight:200, position: ["right", "top"]};
	opciones2 = { autoOpen: false, modal: true, show: { effect: "blind", duration: 500 }, hide: { effect: "fadeOut", duration: 500 },
				width:300,height:300,minWidth:300,minHeight:300,maxWidth:300,maxHeight:300, position: ["center", "center"]};
	$("#gettingInfo").dialog(opciones2);
	$("#getPago").dialog(opciones);
	imagesOpciones = { autoOpen: false, modal: true, show: { effect: "blind", duration: 500 }, hide: { effect: "fadeOut", duration: 500 },
				close:function(event, ui){$("#autocompletar").focus();},
				width:700,height:700,minWidth:700,minHeight:700,maxWidth:700,maxHeight:700, position: ["center", "center"]};
	$("#getImage").dialog(imagesOpciones);
	
	function arreglo(){
		var dataArray = new Array();
		$("#nombree").val($("#autocompletar").val());
		var formInput=$("#myform2").serialize();
		$.getJSON('ajax/buscarProducto', formInput, function(data){
			if(data.resultado=='Ok'){
				signo='$';
				if(data.productoList.length>0){
					for(i=0; i<data.productoList.length; i++){
						//dataArray[i]=data.productoList[i].nombre+'|'+signo+data.productoList[i].precio+'|'+data.productoList[i].unidadDeVenta+'|'+data.productoList[i].codigo+'|'+$.trim(data.productoList[i].descripcion)+'|'+data.productoList[i].idProducto;
						dataComplete[i]=data.productoList[i].nombre+'|'+signo+data.productoList[i].precio+'|'+data.productoList[i].unidadDeVenta+'|'+data.productoList[i].codigo+'|'+$.trim(data.productoList[i].descripcion)+'|'+data.productoList[i].idProducto+'|'+signo+data.productoList[i].precio_mayoreo+'|'+signo+data.productoList[i].precio_especial;
						dataArray[i]=data.productoList[i].nombre;
					}
				}else{
					nada++;
				}
			}else{
				$("#gettingInfo").html("");
				$("#gettingInfo").append("<p>"+data.resultado+"</p>");
				dataArray[0] = data.resultado;
			}
		});//END getJSON
		return dataArray;
	}
	$("#autocompletar").keypress(function(event){
		producto = $("#autocompletar").val();
		e = event.which;
		if(e==10||e==13){
			if(producto==''){
				secuencia = $(".encabezado").next().find('td').eq(0).text();
				if(validarSoloNumeros(secuencia)){
					consecutivo = secuencia;
					$("#tot"+secuencia).focus();
				}
				return false;
			}else{
				var yaCargado = preCargar(producto);
				if(yaCargado==false){
					traerProductoPorCodigo(producto);
				}
			}
		}else if(e==8){
			//$("#autocompletar").val("");
		}
		if(anularSeleccion ==1){
			setTimeout(function(){producto = $("#autocompletar").val();}, 250);
			setTimeout(function(){preCargar(producto);},250);
			anularSeleccion = 777;
		}
	});
	$("#autocompletar").keyup(function(event){	});
	$("#autocompletar").autocomplete({
		source: arreglo()
	});
	$("#autocompletar").keydown(function(event){});
	function calcular(){
		total = parseFloat($("#tot"+consecutivo).val())*parseFloat($("#precio"+consecutivo).text().slice(1,$("#precio"+consecutivo).text().length));
		if (isNaN(total)){
			nada++;
		}else{
			$("#res"+consecutivo).html("$"+formatNum(redondear(total),2));
			calcularGranTotal();
		}
	}
	function volverABuscar(){
		$("#autocompletar").focus();
		setTimeout(function(){$("#autocompletar").val("");},200);
	}
	function guardarVenta(){
		total = validarPrimero($("#jsTotal").text())
		$("#jsTotalVenta").val(total);
		var detalles ='';
		var precioUnitario;
		var seguimiento;
		$(".productosVenta").each(function(i){
			sgto = $(this).find('td').eq(0).text();
			idProducto = $(this).find('td').eq(1).find('div').eq(0).text();
			cantidad = validarUltimo($("#tot"+sgto).val());
			total = validarPrimero($(this).find('td').eq(4).text());
			precioUnitario = validarPrimero($(this).find('td').eq(3).text());
			detalles+=idProducto+';'+cantidad+';'+total+';'+precioUnitario+'@';
		});
		$("#jsDetalles").val(detalles);
		var formInput=$("#form3").serialize();
		$.getJSON('ajax/guardarVenta', formInput, function(data){
			if(data.resultado=='Ok'){
				imprimirTicket();
			}else{
				$("#getPago").dialog("close");
				$("#gettingInfo").html("");
				$("#gettingInfo").append("<p>"+data.resultado+"</p>");
				$("#gettingInfo").dialog("open");
			}
		});//END getJSON*/
	}
	$("#jsPago2").keypress(function(e){
		if(e.which==10 || e.which==13){
			if($(this).val()==''){
				nada++;
			}else{
				if($("#jsPago2").val()==''){
					$("#jsPago2").focus();
				}else{
					pago = parseFloat($("#jsPago2").val());
					deuda = parseFloat($("#jsTotal").text().slice(1,$("#jsTotal").text().length));
					if(pago>=deuda){
						if(enters==0){
							$("#msgPago").html("");
							$("#jsPago2").css({"border-color":"#000000"});
							$("#msgCambio").html("");
							cambio = pago-deuda;
							$("#msgCambio").html("Cambio: $"+formatNum(cambio,2));
							$("#jsPago").val(pago);
							$("#jsCambio").val(cambio);
							guardarVenta();//Se manda llamar a metodo que guarda
							//$("#jsPago2").prop('disabled',true);
							enters = 777;
						}else{
							$("#jsPago2").focus();
							$("#jsPago2").css({"border-color":"#FF0000"});
							$("#jsPago2").css({"border":"2px"});
							$("#msgPago").html("La venta ya se ha guardado");
						}
					}else{
						$("#jsPago2").focus();
						$("#jsPago2").css({"border-color":"#FF0000"});
						$("#jsPago2").css({"border":"2px"});
						$("#msgPago").html("El pago debe ser mayor al total de la venta");
						$("#msgCambio").html("");
					}
				}
			}
		}
	});
	$("#jsPago2").keyup(function(){
		borrar = validarPrecio($(this).val());
		if(borrar==false){
			longitud=$(this).val().length-1;
			$(this).val($(this).val().slice(0, longitud));
			borrar =  true;
		}
	});
	function validarUltimo(gramos){
		borrare = validarPrecio(gramos);
		if(borrare==false){
			gramos = gramos.slice(0, -1);
		}
		return gramos;
	}
	function validarPrimero(totalisimo){
		totalisimo = totalisimo.slice(1, totalisimo.length);
		return totalisimo;
	}
	function getIds(idBuscar){
		var aAgregar = parseInt($.trim(idBuscar));
		var enTabla = 0 ;
		res = true;
		$(".productoId").each( function(i){
			enTabla = parseInt($(this).text());
			if(enTabla == aAgregar){
				res = false;
				/*Nuevo codigo para incrementar una unidad el producto que ya se encontraba en la venta*/
				indice=$(this).parent().prev().text();
				nuevoValor=parseFloat($("#tot"+(indice)).val());
				nuevoValor=nuevoValor+1;
				$("#tot"+(indice)).val(nuevoValor);
				$("#autocompletar").focus();
				$("#autocompletar").val("");
				total = parseFloat($("#tot"+indice).val())*parseFloat($("#precio"+indice).text().slice(1,$("#precio"+indice).text().length));
				$("#res"+indice).html("$"+formatNum(total,2));
				calcularGranTotal();
			}
		});
		return res;
	}
	function calcularGranTotal(){
		granTotal = 0;
		$(".resultado").each( function(i){
			longitud = $(this).text().length;
			granTotal +=parseFloat($(this).text().slice(1,longitud));
		});
		$("#jsGranTotal").html("<strong><h1>$"+formatNum(granTotal,2)+"</h1><strong>");
		$("#jsGranTotal2").html("<strong><h1 style='color:#FF0000'>$"+formatNum(granTotal,2)+"<strong></h1>");
		if (isNaN(granTotal)){
			nada++;
		}else{
			$("#jsTotal").html("$"+formatNum(granTotal,2));
		}
	}
	function formatNum(expr,decplaces) {
		var str = (Math.round(parseFloat(expr) * Math.pow(10,decplaces))).toString();
		while (str.length <= decplaces) {
			str = "0" + str;
		}
		var decpoint = str.length - decplaces;
		return str.substring(0,decpoint) + "." + 
		str.substring(decpoint,str.length);
	}
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
		if(cadena.length==0){
			return false;
		}
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
			return true;
		}else{
			return false;
		}
	}
	function validarCodigo(cadena){
		var nada=0;
		var resultado = true;
		if(cadena.length==0){
			return false;
		}
		for(i=0;i<cadena.length;i++){
			ascii = cadena.charCodeAt(i);
			if((ascii>=48 && ascii<=57 || ascii>=65 && ascii<=90 || ascii>=97 && ascii<=122 || ascii==45)){
				nada++;
			}else{
				resultado = false;
			}
		}
		return resultado;
	}
	function validarSoloNumeros(cadena){
		var nada=0;
		if(cadena.length==0){
			return false;
		}
		for(i=0;i<cadena.length;i++){
			ascii = cadena.charCodeAt(i);
			//alert('ascii: '+ascii);
			if((ascii>=48 && ascii<=57)){
				nada++;
			}else{
				return false;
			}
		}
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
		return RESULTADO;
	}//FIN function --validarProducto--
//FIN FUNCIONES PARA HACER VALIDACIONES
/*--------------------------------------------------------------------------
INICIO FUNCIONES PARA VALIDAR QUE SE INTRODUZCAN UNIDADES Y GRAMOS
---------------------------------------------------------------------------*/	
/*$(document).on('keypress', '.kilogramos',function(){
				alert('You have clicked the kilogramos button'); 
			});*/
	$(document).on('keypress', '.kilogramos',function(e){
		if(e.which==98 || e.which==66){//b=98 & B=66, Borrar el registro de la tabla 
			$(this).parent().parent().fadeOut(200,function(){$(this).remove();});
			volverABuscar();
			setTimeout(function(){
				//alert('calcularGranTotal cuando se borra articulo');
				calcularGranTotal();}
			, 750);
		}else if(e.which==99 || e.which==67){//| c=99 & C=67 Pasar a caja de Pago
			if($(this).val().length>0){
				$("#getPago").dialog("open");
				$("#jsPago2").val("");
				$("#msgPago").html("");
				$("#msgCambio").html("");
				$("#jsPago2").prop('disabled',false);
				$("#jsPago2").focus();
				enters=0;
				return false;
			}
		}else if(e.which==10 || e.which==13){//Presiona Enter
			if($(this).val().length>0){
				secuencia = $(this).parent().parent().next().find('td').eq(0).text();
				if(validarSoloNumeros(secuencia)){
					$("#tot"+secuencia).focus();
					consecutivo = secuencia;
					//alert('consecutivo: '+consecutivo);
				}else{
					$("#autocompletar").val("");
					$("#autocompletar").focus();
				}
			}
		}else if(e.which==110 || e.which==78){//110 Y 78 SON n y N para comenzar una nueva venta
			nuevaVenta();
		}else if(e.which==73 || e.which==105){//73 Y 105 SON I e i para imprimir el ultimo ticket de venta
			imprimirTicket();
			return false;
		}else if(e.which==65 || e.which==97){//41 Y 97 SON A y a para actualizar el producto seleccionado
			actualizar($(this));
			return false;
		}
	});
	
	$(document).on('keyup', '.kilogramos',function(e){
		borrar = validarPrecio($(this).val());
		if(borrar==false){
			longitud=$(this).val().length-1;
			$(this).val($(this).val().slice(0, longitud));
			borrar =  true;
		}else{
			calcular($(this));
		}
	});
	$(document).on('keyup', '.pieza',function(e){
		borrar = validarSoloNumeros($(this).val());
		if(borrar==false){
			longitud=$(this).val().length-1;
			$(this).val($(this).val().slice(0, longitud));
			borrar =  true;
		}else{
			calcular($(this));
		}
	});
	$(document).on('keypress', '.pieza',function(e){
		if(e.which==98 || e.which==66){//b=98 & B=66, Borrar el registro de la tabla 
			$(this).parent().parent().fadeOut(200,function(){$(this).remove();});
			volverABuscar();
			calcularGranTotal();
		}else if(e.which==99 || e.which==67){//| c=99 & C=67 Pasar a caja de Pago
			if($(this).val().length>0){
				$("#getPago").dialog("open");
				$("#jsPago2").val("");
				$("#msgPago").html("");
				$("#msgCambio").html("");
				$("#jsPago2").focus();
				enters=0;
				return false;
			}
		}else if(e.which==10 || e.which==13){//Presiona Enter
			if($(this).val().length>0){
				secuencia = $(this).parent().parent().next().find('td').eq(0).text();
				if(validarSoloNumeros(secuencia)){
					$("#tot"+secuencia).focus();
					consecutivo = secuencia;
				}else{
					$("#autocompletar").val("");
					$("#autocompletar").focus();
				}
			}else{
				$(this).val("1");
				secuencia = $(this).parent().parent().next().find('td').eq(0).text();
				if(validarSoloNumeros(secuencia)){
					$("#tot"+secuencia).focus();
					consecutivo = secuencia;
					//alert('consecutivo: '+consecutivo);
				}else{
					$("#autocompletar").val("");
					$("#autocompletar").focus();
				}
			}
		}else if(e.which==110 || e.which==78){//110 Y 78 SON n y N para comenzar una nueva venta
			nuevaVenta();
		}else if(e.which==73 || e.which==105){//49 Y 69 SON I e i para imprimir el ultimo ticket de venta
			imprimirTicket();
			return false;
		}else if(e.which==65 || e.which==97){//41 Y 97 SON A y a para actualizar el producto seleccionado
			actualizar($(this));
			return false;
		}
	});
		
/*--------------------------------------------------------------------------
FIN FUNCIONES PARA VALIDAR QUE SE INTRODUZCAN UNIDADES Y GRAMOS
---------------------------------------------------------------------------*/			
/*------------------------------------------------------------
INICIO FUNCIONES PARA RESALTAR EL RENGLON DEL ARTICULO EN DONDE SE ENCUENTRE EL FOCOS
----------------------------------------------------*/
		$(document).on('focusout', '.nada',function(){
			$(this).css( "border", "1px solid black");
		});
		$(document).on('focus', '.nada',function(){
			$(this).css( "border", "5px solid red");
		});
		$(document).on('focus', '.pieza',function(){
			$(this).css( "border", "5px solid red");
			$(this).parent().parent().css("backgroundColor","#ffff66");
			var tmp = $(this).val();
			$(this).val('');
			$(this).val(tmp);
			currentPiezas = tmp;
		});
		$(document).on('focusout', '.pieza',function(){
			$(this).css( "border", "1px solid black");
			$(this).parent().parent().css("backgroundColor","#c3dde0");
		});
		$(document).on('focus', '.kilogramos',function(){
			$(this).css( "border", "5px solid red");
			$(this).parent().parent().css("backgroundColor","#ffff66");
			var tmp = $(this).val();
			$(this).val('');
			$(this).val(tmp);
			currentPiezas = tmp;
		});
		$(document).on('focusout', '.kilogramos',function(){
			$(this).css( "border", "1px solid black");
			$(this).parent().parent().css("backgroundColor","#c3dde0");
		});
		$(document).on('autocompleteselect', '.nada',function(event, ui){
			producto = $("#autocompletar").val();
			if(producto==''){
				nada++;
			}else{
				//setTimeout(function(){preCargar($(ui)[0].item.value);},250);
				anularSeleccion = 1;
				$("#autocompletar").keypress();
			}//fin else si producto es diferente de ''
		});
/*------------------------------------------------------------
FIN FUNCIONES PARA RESALTAR EL RENGLON DEL ARTICULO EN DONDE SE ENCUENTRE EL FOCOS
----------------------------------------------------*/			
/*
Nueva funcion para traer articulos por codigo de barras--12 de Diciembre de 2013
*/
	function traerProductoPorCodigo(codigoDeBarras){
		codigoValido = validarCodigo(codigoDeBarras);
		if(codigoValido){
			traerInfo(false);
		}//fin IF si el codigo esta formado unicamente de numeros
		else{//Aqui se mostrarian las opciones para dar de alta un nuevo articulo
			capturarNewProduct(false);
		}
	}//fin function traerProductoPorCodigo
	function traerInfo(scanner){
		var dataArray = new Array();
		$("#nombree").val($("#autocompletar").val());
		var formInput=$("#myform2").serialize();
		$.getJSON('ajax/buscarProductoCodigo', formInput, function(data){
			if(data.resultado=='Ok'){
				signo='$';
				if(data.productoList.length>0){
					for(i=0; i<data.productoList.length; i++){
						dataArray[i]=data.productoList[i].nombre+'|'+signo+data.productoList[i].precio+'|'+data.productoList[i].unidadDeVenta+'|'+data.productoList[i].codigo+'|'+$.trim(data.productoList[i].descripcion)+'|'+data.productoList[i].idProducto+'|'+signo+data.productoList[i].precio_mayoreo+'|'+signo+data.productoList[i].precio_especial;
					}
					clipArray = dataArray[0].split("|");
					if(clipArray.length==8){//antes =6
						if(getIds(clipArray[5])){//Si el articulo NO se encuentra EN LA TABLA
							$("#left").html(mostrarInfoProd(clipArray));
							imagen = traerImagen(clipArray[3]);
							$("#rigth").html(imagen);
							consecutivo = $(".granTotal").prev().find('td:first').text();
							consecutivo = $(".granTotal").prev().find('td:first').text();
							if(consecutivo=='#'){
								consecutivo = 1;
							}else{
								consecutivo++;
							}
							
							$(".granTotal").before(agregarRenglon(consecutivo,clipArray));
							$(".granTotal").prev().fadeOut().fadeIn();
							//setTimeout(function(){$("#tot"+consecutivo).focus();},1000);
							$("#tot"+consecutivo).scannerDetection(opScanner);
							if(scanner==false){
								setTimeout(
									function(){
										$("#tot"+consecutivo).focus();
										$("#tot"+consecutivo).val("");
										$("#autocompletar").val("");
									},300);
									setTimeout(
									function(){
										$("#tot"+consecutivo).focus();
										$("#tot"+consecutivo).val("");
										$("#autocompletar").val("");
									},900);
							}else{
								$("#tot"+consecutivo).val("1");
								setTimeout(
									function(){
										$("#autocompletar").focus();
										$("#autocompletar").val("");
									}
									,250);
							}
							calcular();
						}else{//fin if getId SI EL ARTICULO SI SE ENCONTRABA EN LA TABLA
							/*$("#gettingInfo").dialog("open");
							$("#gettingInfo").html("<h1>Articulo ya incluido en la venta</h1>");
							setTimeout("$('#gettingInfo').dialog('close')", 2000);
							volverABuscar();*/
							setTimeout(function(){$("#autocompletar").focus();$("#autocompletar").val("");},250);
						}
					}
				}else{
					codigou = $("#autocompletar").val();
					if(validarSoloNumeros(codigou)){
						capturarNewProduct(true);
					}else{
						capturarNewProduct(false);
					}
				}
			}else{
				alert('Error: '+data.resultado);
			}
		});//END getJSON
	}//fin funtion traerInfo
	/**FUNCION PARA OBTENER LAS IMAGENES DE LOS PRODUCTOS*/
	function traerImagen(codigo){
	   imagen = "images/imagesProducts/"+codigo+".jpg";
	   var etsiste = urlExists(imagen);
	   if (etsiste==200){
			res = '<img src="'+imagen+'" class="imagensusca" width="100" height="90" title="Ver imagen completa" style="cursor: pointer;"/>';
			$("#getImage").html("");
			$("#getImage").html("<img src='"+imagen+"' width='675' height='675'/>");
	   }else{
			res = '<img src="images/notAvailable.jpg" width="100" height="90"';
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
		//alert('Mostrar imagen');
		$("#getImage").dialog("open");
	});
	function limpiarCampos(){
		$("#jsId").val("");
		$("#jsNombre").val("");
		$("#jsPrecio").val("");
		$("#jsDescripcion").val("");
		$("#jsCodigo").val("");
	}
/****************
CODIGO PARA DAR FUNCIONALIDAD A LA NUEVA ALTA DEL ARTICULO DESDE LA PANTALLA PRINCIPAL
*********************/
	function crear(){
		if (validarProducto()){
			$("#showData").dialog("close");
			$("#showGuardar").dialog("open");
			var formInput=$("#myform").serialize();
			$.getJSON('ajax/guardar',formInput,function(data){
				if(data.resultado=='Ok'){
					$("#autocompletar").val('');
					$("#autocompletar").autocomplete();
					setTimeout(function(){
						$("#autocompletar").val(data.codigo);
						traerInfo(true);
						$("#showGuardar").dialog("close");
					} ,300);
				}else{
					alert('Error: '+data.resultado);
				}
			});//END getJSON
		}
	}//fin function crear
	opciones = {autoOpen: false, modal: true, show: { effect: "blind", duration: 1000 }, 
		hide: { effect: "explode", duration: 1000 },
		close:function(event, ui){$("#autocompletar").focus();},
			width:750,height:275,minWidth:300,minHeight:300,maxWidth:450,maxHeight:450
	};
	opcionesActualizar = {
		autoOpen: false, modal: true, 
		show: { effect: "blind", duration: 1000 }, 
		hide: { effect: "explode", duration: 1000 },
		width:750,height:275,minWidth:300,minHeight:300,maxWidth:450,maxHeight:450
	};
	$("#showData").dialog(opciones);
	$("#showGuardar").dialog(opciones);
	$("#showActualizar").dialog(opcionesActualizar);
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
		return RESULTADO;
	}//FIN function --validarProducto--
	function emptyField(cadena){
		if(cadena=='' || cadena==null){
			return false;
		}else{
			return true
		}
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
/*
CODIGO PARA HACER QUE RESALTE DE COLOR ROJO EL BORDE DE LA CAJA DE TEXTO
*/	
	$('#jsNombre').focus(function(){
		$(this).css( "border", "5px solid red");
	});
	$('#jsNombre').focusout(function(){
		$(this).css( "border", "1px solid black");
	});
	$('#jsPrecio').focus(function(){
		$(this).css( "border", "5px solid red");
	});
	$('#jsPrecio').focusout(function(){
		$(this).css( "border", "1px solid black");
	});
	$('#jsDescripcion').focus(function(){
		$(this).css( "border", "5px solid red");
	});
	$('#jsDescripcion').focusout(function(){
		$(this).css( "border", "1px solid black");
	});
	$('#jsCodigo').focus(function(){
		$(this).css( "border", "5px solid red");
	});
	$('#jsCodigo').focusout(function(){
		$(this).css( "border", "1px solid black");
	});
/*codigo cuando se presiona enter en los campos cuando se va a dar de alta un nuevo articulo*/
	$("#jsNombre").keypress(function(event){
		e = event.which;
		if(e==10||e==13){
			$('#jsPrecio').focus();
		}
	});
	$("#jsPrecio").keypress(function(event){
		e = event.which;
		if(e==10||e==13){
			crear();
		}
	});
/********************
FIN CODIGO QUE DA FUNCIONALIDAD A LA PANTALLA PRINCIPAL
*********************/
	function mostrarInfoProd(datosArreglo){
		htmlX="<p class='descripcion'>Producto: <span class='msgs'><br />";
		htmlX+=datosArreglo[0]+"</span><br />Precio: <span class='msgs'><br />"+datosArreglo[1]+"</span>";
		htmlX+="<br /><strong>Unidad de venta: </strong><br /><span class='msgs'>"+datosArreglo[2];
		htmlX+="<br /></span><strong>Codigo: </strong><br /><span class='msgs'>"+datosArreglo[3]+"</span>";
		htmlX+="<br /><strong>Descripcion: </strong><br /><span class='msgs'>"+datosArreglo[4]+"</span>";
		return htmlX;
	}
	function preCargar(pro){
		productoEncontrado='no';
		for(i=0;i<dataComplete.length;i++){
			clipArray = dataComplete[i].split("|");
			if(clipArray.length==8){
			   if($.trim(clipArray[0])==$.trim(pro)){
					cargarProducto(dataComplete[i]);
					i=dataComplete.length;
					return true;
			   }
			}
		}
		return false;
	}
	function cargarProducto(producto){
		clipArray = producto.split("|");
		if(clipArray.length==8){
			if(getIds(clipArray[5])){//Si el articulo NO se encuentra EN LA TABLA
				$("#left").html(mostrarInfoProd(clipArray));
				imagen = traerImagen(clipArray[3]);
				$("#rigth").html(imagen);
				consecutivo = $(".granTotal").prev().find('td:first').text();
				if(consecutivo=='#'){
					consecutivo = 1;
				}else{
					consecutivo++;
				}
				$(".granTotal").before(agregarRenglon(consecutivo,clipArray));
				$(".granTotal").prev().fadeOut().fadeIn();
				$("#tot"+consecutivo).val("");
				setTimeout(function(){$("#tot"+consecutivo).focus();},700);
				calcular();
			}else{//fin if getId SI EL ARTICULO SI SE ENCONTRABA EN LA TABLA
				/*$("#gettingInfo").dialog("open");
				$("#gettingInfo").html("<h1>Articulo ya incluido en la venta</h1>");
				setTimeout("$('#gettingInfo').dialog('close')", 2000);
				volverABuscar();*/
			}
		}
	}
	function capturarNewProduct(reutilizar){
		$("#showData").dialog("open");
		//alert('darDeAlta: Dar de alta nuevo articulo');
		limpiarCampos();
		if(reutilizar==false){
			$("#jsNombre").val($("#autocompletar").val());
		}else{
			$("#jsCodigo").val(codigou);
		}
		$(".campos").css("border","1px solid black");
		$(".msgCampos").html("");
	}//fin function capturarNewProduct
	$("#autocompletar").autocomplete();
	function  agregarRenglon(secuencia,arreglusco){
		precioAmostrar=$.trim($("#precio").text());
		if(precioAmostrar=="publico"){
			addRow= "<tr class='productosVenta'><td>"+secuencia+"</td><td><div class='productoId' style='display:none'>"+arreglusco[5]+"</div><div>"+arreglusco[0]+"</div></td><td><input type='text' size='6' maxlength='6' class='"+arreglusco[2]+"' id='tot"+secuencia+"'>"+arreglusco[2]+"</td>";
			addRow+="<td><div id='precio"+secuencia+"'>"+arreglusco[1]+"</div></td><td><div id='res"+secuencia+"' class='resultado'></div></td></tr>";
		}else if(precioAmostrar=="mayoreo"){
			addRow= "<tr class='productosVenta'><td>"+secuencia+"</td><td><div class='productoId' style='display:none'>"+arreglusco[5]+"</div>"+arreglusco[0]+"</td><td><input type='text' size='6' maxlength='6' class='"+arreglusco[2]+"' id='tot"+secuencia+"'>"+arreglusco[2]+"</td>";
			addRow+="<td><div id='precio"+secuencia+"'>"+arreglusco[6]+"</div></td><td><div id='res"+secuencia+"' class='resultado'></div></td></tr>";
		}else if(precioAmostrar=="especial"){
			addRow= "<tr class='productosVenta'><td>"+secuencia+"</td><td><div class='productoId' style='display:none'>"+arreglusco[5]+"</div>"+arreglusco[0]+"</td><td><input type='text' size='6' maxlength='6' class='"+arreglusco[2]+"' id='tot"+secuencia+"'>"+arreglusco[2]+"</td>";
			addRow+="<td><div id='precio"+secuencia+"'>"+arreglusco[7]+"</div></td><td><div id='res"+secuencia+"' class='resultado'></div></td></tr>";
		}
		return addRow;
	}
	function validarSoloLetras(cadena){
		var nada=0;
		if(cadena.length==0){
			return false;
		}
		for(i=0;i<cadena.length;i++){
			ascii = cadena.charCodeAt(i);
			//alert('ascii: '+ascii);
			if(ascii>=65 && ascii<=90 || ascii>=97 && ascii<=122){
				nada++;
			}else{
				return false;
			}
		}
		return true;
	}
	$("#autocompletar" ).autocomplete({
	  focus: function( event, ui ) {previus(ui.item.value);}
	});
	function previus(pro){
		for(i=0;i<dataComplete.length;i++){
			clipArray = dataComplete[i].split("|");
			if(clipArray.length==8){
			   if($.trim(clipArray[0])==$.trim(pro)){
					$("#itemName").html(clipArray[1]+': '+clipArray[0]);
					i=dataComplete.length;
					return true;
			   }
			}
		}
		return false;
	}//fin previus
	function imprimirTicket(){
		var myWindow = window.open('imprimirTicket',"Ticket", "width=200, height=100");
		setTimeout(function(){myWindow.close();},1500);
	}//fin function imprimirTicket
	function actualizar(current){
		idProducto = current.parent().parent().find('td').eq(1).find('div').eq(0).text();
		nombreProducto = current.parent().parent().find('td').eq(1).find('div').eq(1).text();
		precio = current.parent().parent().find('td').eq(3).find('div').eq(0).text();
		$("#jsNombreActualizar").val(nombreProducto);
		$("#jsPrecioActualizar").val(validarPrimero(precio));
		$("#jsIdActualizar").val(idProducto);
		$("#jsCantidadActualizar").val('0');
		$("#showActualizar").dialog("open");
	}//fin function actualizar
	$("#jsNombreActualizar").keypress(function(event){
		e = event.which;
		if(e==10||e==13){
			$("#jsPrecioActualizar").focus();
		}
	});
	$("#jsPrecioActualizar").keypress(function(event){
		e = event.which;
		if(e==10||e==13){
			$("#jsCantidadActualizar").focus();
		}
	});
	$("#jsCantidadActualizar").keypress(function(event){
		e = event.which;
		if(e==10||e==13){
			actualizar2();
		}
	});
	function actualizar2(){
		if (validarProductoActualizar()){
			$("#showActualizar").dialog("close");
			$("#showGuardar").dialog("open");
			var formInput=$("#myformActualizar").serialize();
			$.getJSON('ajax/actualizar',formInput,function(data){
				if(data.resultado=='Registro guardado exitosamente'){
					$("#autocompletar").val('');
					idProducto = $("#jsIdActualizar").val();
					eliminarRenAct(idProducto);
					setTimeout(function(){
						$("#autocompletar").val(data.codigo);
						traerInfo(false);
						$("#showGuardar").dialog("close");
						setTimeout(function(){$("#tot"+consecutivo).focus();},700);
					} ,300);
					setTimeout(function(){$("#tot"+consecutivo).focus();},2500);
				}else{
					$("#showGuardar").dialog("close");
					alert('Error: '+data.resultado);
				}
			});//END getJSON
		}
	}//fin function actualizar2
	function validarProductoActualizar(){
//Validacion de NOMBRE msgNombre-jsNombreActualizar
		var RESULTADO = true;
		valNombre = $("#jsNombreActualizar").val();
		if(emptyField(valNombre)){
			isNombre = validarNombre(valNombre);
			if(isNombre==false){
				$("#msgNombreActualizar").html("");
				$("#msgNombreActualizar").html("<p>Por favor capture unicamente letras y numeros</p>");
				$("#jsNombreActualizar").css("border","1px solid red");
				$("#jsNombreActualizar").focus();
				RESULTADO = false;
			}else{
				$("#msgNombreActualizar").html("");
				$("#jsNombreActualizar").css("border","1px solid black");
			}
		}else{
			$("#msgNombreActualizar").html("");
			$("#msgNombreActualizar").html("<p>Por favor capture un nombre</p>");
			$("#jsNombreActualizar").css("border","1px solid red");
			$("#jsNombreActualizar").focus();
			RESULTADO = false;
		}
//Validacion de precio msgPrecio-jsPrecio
		valPrecio = $("#jsPrecioActualizar").val();
		if(emptyField(valPrecio)){
			isPrecio = validarPrecio(valPrecio);
			if(isPrecio==false){
				$("#msgPrecioActualizar").html("");
				$("#msgPrecioActualizar").html("<p>Por favor capture unicamente valores numericos</p>");
				$("#jsPrecioActualizar").css("border","1px solid red");
				$("#jsPrecioActualizar").focus();
				RESULTADO = false;
			}else{
				$("#msgPrecioActualizar").html("");
				$("#jsPrecioActualizar").css("border","1px solid black");
			}
		}else{
			$("#msgPrecioActualizar").html("");
			$("#msgPrecioActualizar").html("<p>Por favor capture un precio</p>");
			$("#jsPrecioActualizar").css("border","1px solid red");
			$("#jsPrecioActualizar").focus();
			RESULTADO = false;
		}
//Validacion cantidad: jsCantidadActualizar-msgCantidadActualizar
		valCantidad = $("#jsCantidadActualizar").val();
		if(emptyField(valCantidad)){
			isCodigo = validarPrecio(valCantidad);
			if(isCodigo==false){
				$("#msgCantidadActualizar").html("");
				$("#msgCantidadActualizar").html("<p>Por favor capture unicamente numeros</p>");
				$("#jsCantidadActualizar").css("border","1px solid red");
				$("#jsCantidadActualizar").focus();
				RESULTADO = false;
			}else{
				$("#msgCantidadActualizar").html("");
				$("#jsCantidadActualizar").css("border","1px solid black");
			}
		}else{
			$("#msgCantidadActualizar").html("");
			$("#msgCantidadActualizar").html("<p>Por favor capture un codigo</p>");
			$("#jsCantidadActualizar").css("border","1px solid red");
			$("#jsCantidadActualizar").focus();
			RESULTADO = false;
		}
		return RESULTADO;
	}//FIN function --validarProductoActualizar--
	$(document).on('focus', '.actualizar',function(){
		$(this).css( "border", "5px solid red");
		$(this).parent().parent().css("backgroundColor","#ffff66");
		var tmp = $(this).val();
		$(this).val('');
		$(this).val(tmp);
	});
	$(document).on('focusout', '.actualizar',function(){
		$(this).css( "border", "1px solid black");
		$(this).parent().parent().css("backgroundColor","#c3dde0");
	});
	function eliminarRenAct(idProduct){
		$(".productosVenta").each(function(i){
			idProducto = $(this).find('td').eq(1).find('div').eq(0).text();
			if(idProduct==idProducto){
				$(this).remove();
			}
		});
	}
	function redondear(n){
		var decimal = n - Math.floor(n)
		n = n - decimal;
		if(decimal<=0.25){
			decimal = 0;
		}else if(decimal>0.25 && decimal<0.5){
			decimal = 0.5;
		}else if (decimal==0.5){
			decimal = 0.5;
		}else if(decimal >0.5 && decimal <=0.75){
			decimal = 0.5;
		}else if(decimal>0.75){
			decimal = 1;
		}
		n = n + decimal;
		return n;
	}
	function nuevaVenta(){
		$(".productosVenta").each( function(i){
			$(this).fadeOut(1000,function(){$(this).remove();});
		});
		volverABuscar();
		$("#jsGranTotal").html("");
		$("#jsGranTotal2").html("");
	}
 });//end doc ready       