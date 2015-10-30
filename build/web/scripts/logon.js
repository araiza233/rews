$(document).ready(function() {
	
	setTimeout(function(){
		$("#user").focus();
		$("#user").val("Admin");
		$("#passwd").val("1");
		$("#passwd").focus();
	}, 500);
	$("#user").focus(function(){
		$(this).css( "border", "5px solid red");
	});
	$("#passwd").focus(function(){
		$(this).css( "border", "5px solid red");
	});
	$('#user').focusout(function(){
		$(this).css( "border", "1px solid black");
	});
	$('#passwd').focusout(function(){
		$(this).css("border", "1px solid black");
	});
	$("#passwd").val("");
	$("#user").val("");
	
	$("#entrar").click(function(){
		if($("#user").val()==''){
				$("#userMessage").html("<div class='msgCampos'>Por favor capture un nombre de usuario</div>");
		}else{
			if($("#passwd").val()==''){
				$("#userPasswd").html("<div class='msgCampos'>Por favor capture una contraseña</div>");
			}else{
				getAccesos();
			}
		}
	});
    
       
	function getAccesos(){
            $("#jsUser").val($("#user").val());
            $("#jsPassword").val($("#passwd").val());
            $("#form3").submit();
	}//fin function getAccesos()
	$("#user").keypress(function(event){
		usuario = $("#user").val();
		e = event.which;
		if(e==10||e==13){
			if(usuario==''){
				nada++;
			}else{
				$("#passwd").focus();
			}
		}
	});
	$("#passwd").keypress(function(event){
		passwd = $("#passwd").val();
		e = event.which;
		if(e==10||e==13){
			if(passwd==''){
				nada++;
			}else{
				$("#entrar").click();
			}
		}
	});
 });//end doc ready