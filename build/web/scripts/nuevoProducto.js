$(document).ready(function() {
	var arrayValoresAutocomplementar=["Alabama", "Albacete", "Alicante", "Zacatecas", "Aguascalientes","Arturo", "Araña","Aguacate","Aguacate","Burro","Bebe", "Blusa","Biberon","Bicicleta","Casa","Casado","Cazar", "Calzon", "Corazon","Zebra","Sopa", "Sal", "Sol","Sillon","Ernesto","Eduardo", "Estufa", "Edredon","Estupido","Estopa"];
	var buttonpressed;
	$('.submitbutton').click(function() {
          buttonpressed = $(this).attr('value')
    });
	
	$("#myform").submit(function(){//myform
		if(buttonpressed=='Guardar'){
			var formInput=$(this).serialize();
			$.getJSON('ajax/guardar',formInput,function(data){
				alert(data.resultado);
				
			});//END getJSON
		}
		return false;
	});//end myform	
	$('#datepicker').datepicker({
           inline: true,   
           showOtherMonths: true,   
           dayNamesMin: ['Dom', 'Lun', 'Mar', 'Mie', 'Jue', 'Vie', 'Sab'],   
    });//end datePicker
	$("#autoc1").autocomplete({
		source:function(request, response){
					var formInput=$("#myform").serialize();
					$.getJSON('ajax/buscarProducto', formInput, function(data){
						alert('Resuiltado: '+data.resultado);
					})//END getJSON
				}
		});//END autocomplete
 });//end doc ready