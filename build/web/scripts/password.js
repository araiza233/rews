$(document).ready(function() {
	$('#jsActual').focus();
	opciones2 = { autoOpen: false, modal: true, show: { effect: "blind", duration: 500 }, hide: { effect: "fadeOut", duration: 2000 },
				width:300,height:100,minWidth:300,minHeight:300,maxWidth:300,maxHeight:300, position: ["center", "center"]}
	$("#gettingInfo").dialog(opciones2);
		
	$("#btnPassword").click(function(){
            passwd = $("#jsActual").val();
            pass1 = $("#jsNueva1").val();
            pass2 = $("#jsNueva2").val();
            if(passwd==''){
                alertusca("Por favor capture su contraseña actual");
                $('#jsActual').focus();
            }else{
                if(pass1==''){
                    alertusca("Capture nueva contraseña");
                    $('#jsNueva1').focus();
                }else if(pass2==''){
                    alertusca("Por favor repita su nueva contraseña");
                    $('#jsNueva2').focus();
                }else if (pass2!=pass1){
                    alertusca("Por favor verifique que sean iguales las nuevas contraseñas");
                    $('#jsNueva1').focus();
                }else{
                    alertusca("Enviar ajax");
                    modificarPass();
                }
            }
	});
	function modificarPass(){
            $("#form1").submit();
	}//fin function modificarPass()
	function alertusca(msg){
		$("#gettingInfo").html("");
		$("#gettingInfo").append(msg);
		$("#gettingInfo").dialog("open");
	}
	$('#jsActual').focus(function(){
		$(this).css( "border", "5px solid red");
	});
	$('#jsActual').focusout(function(){
		$(this).css( "border", "1px solid black");
	});
	$('#jsNueva1').focus(function(){
		$(this).css( "border", "5px solid red");
	});
	$('#jsNueva1').focusout(function(){
		$(this).css( "border", "1px solid black");
	});
	$('#jsNueva2').focus(function(){
		$(this).css( "border", "5px solid red");
	});
	$('#jsNueva2').focusout(function(){
		$(this).css( "border", "1px solid black");
	});
});//end doc ready