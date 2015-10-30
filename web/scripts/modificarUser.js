$(document).ready(function() {
	opciones2 = { autoOpen: false, modal: true, show: { effect: "blind", duration: 500 }, hide: { effect: "fadeOut", duration: 2000 },
				width:550,height:500,minWidth:300,minHeight:300,maxWidth:300,maxHeight:300, position: ["center", "center"]}
	$("#gettingInfo").dialog(opciones2);
	$("#password1").focus(function(){
		$(this).css( "border", "5px solid red");
	});
	$("#password2").focus(function(){
		$(this).css( "border", "5px solid red");
	});
	$("#password2").focusout(function(){
		$(this).css( "border", "1px solid black");
		$(this).parent().parent().css("backgroundColor","#c3dde0");
	});
	$("#password1").focusout(function(){
		$(this).css( "border", "1px solid black");
		$(this).parent().parent().css("backgroundColor","#c3dde0");
	});
	
	$("#txtNombre").focus(function(){
		$(this).css( "border", "5px solid red");
	});
	$("#txtNombre").focusout(function(){
		$(this).css( "border", "1px solid black");
		$(this).parent().parent().css("backgroundColor","#c3dde0");
	});
	$("#txtDireccion").focus(function(){
		$(this).css( "border", "5px solid red");
	});
	$("#txtDireccion").focusout(function(){
		$(this).css( "border", "1px solid black");
		$(this).parent().parent().css("backgroundColor","#c3dde0");
	});
	$("#txtTelefono").focus(function(){
		$(this).css( "border", "5px solid red");
	});
	$("#txtTelefono").focusout(function(){
		$(this).css( "border", "1px solid black");
		$(this).parent().parent().css("backgroundColor","#c3dde0");
	});
	$(document).on('click', '.botonsuco',function(e){
		idUsuario = $.trim($(this).parent().prev().prev().prev().prev().text());
		$("#gettingInfo").dialog("open");
		$("#usuarioCambiar").html('<h1>'+idUsuario+'</h1>');
		$("#id_usuario").val(idUsuario);
		$("#txtNombre").val($.trim($(this).parent().prev().prev().prev().text()));
		$("#txtDireccion").val($.trim($(this).parent().prev().prev().text()));
		$("#txtTelefono").val($.trim($(this).parent().prev().text()));
		$("#txtNombre").focus();
	});
	$("#btnCambiar").click(function(){
		p1 = $.trim($("#password1").val());
		p2 = $.trim($("#password2").val());
		if(p1=='' && p2=='' || p1==p2){
			nombre = $.trim($("#txtNombre").val());
			dir = $.trim($("#txtDireccion").val());
			tel = $.trim($("#txtTelefono").val());
			if (nombre==''){
				limpiarMensajes();
				$("#divNombre").html("Por favor capture un valor");
				$("#txtNombre").focus();
			}else{
				if (dir==''){
					limpiarMensajes();
					$("#divDir").html("Por favor capture un valor");
					$("#txtDireccion").focus();
				}else{
					if (tel==''){
						limpiarMensajes();
						$("#divTel").html("Por favor capture un valor");
						$("#txtTelefono").focus();
					}else{
						$("#form1").submit();
					}
				}
			}
		}else if (!p1=='' || !p2==''){
			if (p1!=p2){
				limpiarMensajes();
				$("#msgPasswd1").html("<div class='msgCampos'>Por favor capture la misma contraseña</div>");
				$("#msgPasswd2").html("<div class='msgCampos'>Por favor capture la misma contraseña</div>");
				$("#password1").focus();
			}
		}
	});
	$("#txtNombre").keypress(function(event){
		e = event.which;
		if(e==10||e==13){
			$("#txtDireccion").focus();
		}
	});
	$("#txtDireccion").keypress(function(event){
		e = event.which;
		if(e==10||e==13){
			$("#txtTelefono").focus();
		}
	});
	$("#txtTelefono").keypress(function(event){
		e = event.which;
		if(e==10||e==13){
			$("#password1").focus();
		}
	});
	$("#password1").keypress(function(event){
		e = event.which;
		if(e==10||e==13){
			$("#password2").focus();
		}
	});
	$("#password2").keypress(function(event){
		e = event.which;
		if(e==10||e==13){
			$("#btnCambiar").click();
		}
	});
	function hasBlankSpace(inputString){
		var findme = " ";
		if ( inputString.indexOf(findme) > -1 ) {
			return true;
		}else {
			return false;
		}
	}//Funcion que detecta que el alias de usuario no sea de mas de una sola palabra
	function limpiarMensajes(){
		$("#divTel").html("");
		$("#divDir").html("");
		$("#divNombre").html("");
		$("#msgPasswd1").html("");
		$("#msgPasswd2").html("");
	}
 });//end doc ready