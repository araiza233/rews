$(document).ready(function() {
	limpiarMensajes(1);
	opciones2 = { autoOpen: false, modal: true, show: { effect: "blind", duration: 500 }, hide: { effect: "fadeOut", duration: 2000 },
				width:300,height:300,minWidth:300,minHeight:300,maxWidth:300,maxHeight:300, position: ["center", "center"]}
	$("#gettingInfo").dialog(opciones2);
	$('#jsId_usuario').focus(function(){
		$(this).css( "border", "5px solid red");
	});
	$('#jsId_usuario').focusout(function(){
		$(this).css( "border", "1px solid black");
	});
	$('#jsNombre').focus(function(){
		$(this).css( "border", "5px solid red");
	});
	$('#jsNombre').focusout(function(){
		$(this).css( "border", "1px solid black");
	});
	$('#jsDireccion').focus(function(){
		$(this).css( "border", "5px solid red");
	});
	$('#jsDireccion').focusout(function(){
		$(this).css( "border", "1px solid black");
	});
	$('#jsTelefono').focus(function(){
		$(this).css( "border", "5px solid red");
	});
	$('#jsTelefono').focusout(function(){
		$(this).css( "border", "1px solid black");
	});
	$('#jsPassword').focus(function(){
		$(this).css( "border", "5px solid red");
	});
	$('#jsPassword').focusout(function(){
		$(this).css( "border", "1px solid black");
	});
	$('#jsPassword2').focus(function(){
		$(this).css( "border", "5px solid red");
	});
	$('#jsPassword2').focusout(function(){
		$(this).css( "border", "1px solid black");
	});
	setTimeout(function(){$('#jsId_usuario').focus();},700);
	$("#btnCrearUser").click(function(){
		limpiarMensajes(2);
		res = false;
		if(emptyField($("#jsId_usuario").val())){
			if(emptyField($("#jsNombre").val())){
				if(emptyField($("#jsDireccion").val())){
					if(emptyField($("#jsTelefono").val())){
						if(emptyField($("#jsPassword").val())){
							if(emptyField($("#jsPassword2").val())){
								if($("#jsPassword2").val()==$("#jsPassword").val()){
									res = true;
								}else{
									$("#jsPassword").focus();
									$("#msjPassword").html("Capture la misma contraseña");
									$("#msjPassword2").html("Capture la misma contraseña");
								}
							}else{
								$("#jsPassword2").focus();
								$("#msjPassword2").html("Por favor capture el nombre del usuario");
							}
						}else{
							$("#jsPassword").focus();
							$("#msjPassword").html("Por favor capture el nombre del usuario");
						}
				
					}else{
						$("#jsTelefono").focus();
						$("#msjTelefono").html("Por favor capture el nombre del usuario");
					}
				}else{
					$("#jsDireccion").focus();
					$("#msjDireccion").html("Por favor capture el nombre del usuario");
				}
			}else{
				$("#jsNombre").focus();
				$("#msjNombre").html("Por favor capture el nombre del usuario");
			}
		}else{
			$("#jsId_usuario").focus();
			$("#msjAlias").html("Por favor capture un alias de usuario");
		}
		return res;
	});
	function emptyField(cadena){
		if(cadena=='' || cadena==null){
			return false;
		}else{
			return true
		}
	}
	function limpiarMensajes(todo){
		if(todo==1){
			$("#jsId_usuario").val('');
			$("#jsNombre").val('');
			$("#jsDireccion").val('');
			$("#jsTelefono").val('');
			$("#jsPassword").val('');
			$("#jsPassword2").val('');
		}
		$("#msjAlias").html("");
		$("#msjNombre").html("");
		$("#msjDireccion").html("");
		$("#msjTelefono").html("");
		$("#msjPassword").html("");
		$("#msjPassword2").html("");
		
		
	}
 });//end doc ready