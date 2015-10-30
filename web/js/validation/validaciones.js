$(document).ready(function(){
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
		for(i=0;i<cadena.length;i++){
			ascii = cadena.charCodeAt(i);
			if((ascii>=48 && ascii<=57)){
				nada++;
			}else{
				return false;
			}
		}
		return true;
	}
	function validarNombre(cadena){
		var nada=0;
		for(i=0;i<cadena.length;i++){
			ascii = cadena.charCodeAt(i);
			if((ascii>=48 && ascii<=57)||(ascii>=65 && ascii<=90)||(ascii>=97 && ascii<=122)||(ascii==32)||(ascii==209)||(ascii==241)){
				nada++
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
/*
		valNombre = $("#jsNombre").val();
		if(emptyField(valNombre)){
			isNombre = validarNombre(valNombre);
			if(isNombre==false){
				$("#msgNombre").html("");
				$("#msgNombre").html("<p>Por favor capture unicamente letras y numeros</p>");
				$("#jsNombre").css("border","1px solid red");
			}else{
				$("#msgNombre").html("");
				$("#jsNombre").css("border","1px solid black");
			}
		}else{
			$("#msgNombre").html("");
			$("#msgNombre").html("<p>Por favor capture un nombre</p>");
			$("#jsNombre").css("border","1px solid red");
		}
//Validacion de precio msgPrecio-jsPrecio
		valPrecio = $("#jsPrecio").val();
		if(emptyField(valPrecio)){
			isPrecio = validarPrecio(valPrecio);
			if(isPrecio==false){
				$("#msgPrecio").html("");
				$("#msgPrecio").html("<p>Por favor capture unicamente valores numericos</p>");
				$("#jsPrecio").css("border","1px solid red");
			}else{
				$("#msgPrecio").html("");
				$("#jsPrecio").css("border","1px solid black");
			}
		}else{
			$("#msgPrecio").html("");
			$("#msgPrecio").html("<p>Por favor capture un precio</p>");
			$("#jsPrecio").css("border","1px solid red");
		}
//Validacion descripcion: jsDescripcion-msgDescripcion
		valDescripcion = $("#jsDescripcion").val();
		if(emptyField(valDescripcion)){
			isDescripcion = validarNombre(valDescripcion);
			if(isDescripcion==false){
				$("#msgDescripcion").html("");
				$("#msgDescripcion").html("<p>Por favor capture unicamente letras y numeros</p>");
				$("#jsDescripcion").css("border","1px solid red");
			}else{
				$("#msgDescripcion").html("");
				$("#jsDescripcion").css("border","1px solid black");
			}
		}else{
			$("#msgDescripcion").html("");
			$("#msgDescripcion").html("<p>Por favor capture un nombre</p>");
			$("#jsDescripcion").css("border","1px solid red");
		}
//Validacion codigo: jsCodigo-msgCodigo
		valCodigo = $("#jsCodigo").val();
		if(emptyField(valCodigo)){
			isCodigo = validarCodigo(valCodigo);
			if(isCodigo==false){
				$("#msgCodigo").html("");
				$("#msgCodigo").html("<p>Por favor capture unicamente letras y numeros</p>");
				$("#jsCodigo").css("border","1px solid red");
			}else{
				$("#msgCodigo").html("");
				$("#jsCodigo").css("border","1px solid black");
			}
		}else{
			$("#msgCodigo").html("");
			$("#msgCodigo").html("<p>Por favor capture un nombre</p>");
			$("#jsCodigo").css("border","1px solid red");
		}
		return true;
		*/
		alert('Validar producto');
	}//FIN function --validarProducto--
});

