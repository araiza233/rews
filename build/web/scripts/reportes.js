$(document).ready(function() {
    $("#items").val("");
	REPORTE_DETALLADO_VENTAS = 1;
	REPORTE_ABASTO = 2;
	REPORTE_INVENTARIO = 3;
	$("#fechaInicial").datepicker({
		dayNamesMin: ['Dom', 'Lun', 'Mar', 'Mie', 'Jue', 'Vie', 'Sab'],
		monthNames: ['Enero', 'Febrero', 'Marzo', 'Abril', 'Mayo', 'Junio', 'Julio','Agosto','Septiembre','Octubre','Noviembre','Diciembre'],  
	});
	$("#fechaFinal").datepicker({
		dayNamesMin: ['Dom', 'Lun', 'Mar', 'Mie', 'Jue', 'Vie', 'Sab'],
		monthNames: ['Enero', 'Febrero', 'Marzo', 'Abril', 'Mayo', 'Junio', 'Julio','Agosto','Septiembre','Octubre','Noviembre','Diciembre'],  
	});
	function validarFecha(Fecha,fechaFinal){
            if((Fecha!=null && Fecha.length>0)&&(fechaFinal!=null && fechaFinal.length>0)){
                var _fechaArray = Fecha.split("/");
                var _fechaArrayFinal = fechaFinal.split("/");
                if(_fechaArray.length==3&&_fechaArrayFinal.length==3){
                        f1_mes = _fechaArray[0];
                        f1_dia = _fechaArray[1];
                        f1_anio = _fechaArray[2];
                        var f1 = new Date(f1_anio, f1_mes-1, f1_dia);


                        f2_mes = _fechaArrayFinal[0];
                        f2_dia = _fechaArrayFinal[1];
                        f2_anio = _fechaArrayFinal[2];
                        var f2 = new Date(f2_anio, f2_mes-1, f2_dia);

                        if(f1 <= f2)
                                return true;
                }
            }
            return false;
	}
	$("#crearReporte").click(function(){
		seleccion = $("#selectReporte").val();
                getIdesSubmit();
		if(REPORTE_DETALLADO_VENTAS==seleccion){
                    if(validarFecha($("#fechaInicial").val(), $("#fechaFinal").val())){
                        /*window.showModalDialog('mostrarReporte?fechaInicial='+$("#fechaInicial").val()+'&fechaFinal='+$("#fechaFinal").val()+'&selectReporte='+$("#selectReporte").val()+'&usuario='+$("#usuario").val()+'&items='+$("#items").val(), 
					"Reporte de Abasto/Ventas", "dialogWidth:950px;dialogHeight:700px");*/
                        var myWindow = window.open('mostrarReporte?fechaInicial='+$("#fechaInicial").val()+'&fechaFinal='+$("#fechaFinal").val()+'&selectReporte='+$("#selectReporte").val()+'&usuario='+$("#usuario").val()+'&items='+$("#items").val(), 
					"Reporte de Abasto/Ventas", "dialogWidth:950px;dialogHeight:700px");
                        setTimeout(function(){myWindow.close();},2000);
		}else{
					$("#gettingInfo").html("<h3>Por favor capture correctamente ambas fechas para generar el reporte de abasto/ventas</h3>");
					$("#gettingInfo").dialog("open");
			}
		}else if(REPORTE_ABASTO==seleccion){
			if(validarFecha($("#fechaInicial").val(), $("#fechaFinal").val())){
					window.showModalDialog('mostrarReporte?fechaInicial='+$("#fechaInicial").val()+'&fechaFinal='+$("#fechaFinal").val()+'&selectReporte='+$("#selectReporte").val()+'&usuario='+$("#usuario").val()+'&items='+$("#items").val(), 
					"Reporte de Abasto/Ventas", "dialogWidth:950px;dialogHeight:700px");
			}else{
					$("#gettingInfo").html("<h3>Por favor capture correctamente ambas fechas para generar el reporte de abasto/ventas</h3>");
					$("#gettingInfo").dialog("open");
			}
		}else if(REPORTE_INVENTARIO==seleccion){
			window.showModalDialog('mostrarReporte?selectReporte='+$("#selectReporte").val()+'&items='+$("#items").val(), 
			"Reporte de Inventario", "dialogWidth:950px;dialogHeight:700px");
		}
	});
	opciones2 = { autoOpen: false, modal: true, show: { effect: "blind", duration: 500 }, hide: { effect: "fadeOut", duration: 2000 },
				width:300,height:300,minWidth:300,minHeight:300,maxWidth:300,maxHeight:300, position: ["center", "center"]};
       $('#selectReporte').change(function() {
            seleccion = $("#selectReporte").val();
            if(REPORTE_DETALLADO_VENTAS==seleccion){
               $("#fechas").show('slow');
               $("#fechas2").show('slow');
               $("#usuarios").show('slow');
               $("#usuarios").show('slow');
            }else if(REPORTE_ABASTO==seleccion){
               $("#fechas").show('slow');
               $("#fechas2").show('slow');
               $("#usuarios").show('slow');
               $("#usuarios").show('slow');
            }else if(REPORTE_INVENTARIO==seleccion){
                $("#fechas").hide('slow');
                $("#fechas2").hide('slow');
                $("#usuarios").hide('slow');
                $("#usuarios").hide('slow');
            }
       });
       $("#gettingInfo").dialog(opciones2);
       function arreglo(){
            var dataArray = new Array();
            $("#nombree").val($("#autocompletar").val());
            var formInput=$("#myform2").serialize();
            $.getJSON('ajax/buscarProducto', formInput, function(data){
                if(data.resultado=='Ok'){
                   if(data.productoList.length>0){
                        for(i=0; i<data.productoList.length; i++){
                                //dataArray[i]=data.productoList[i].nombre+'|'+signo+data.productoList[i].precio+'|'+data.productoList[i].unidadDeVenta+'|'+data.productoList[i].codigo+'|'+$.trim(data.productoList[i].descripcion)+'|'+data.productoList[i].idProducto;
                                dataArray[i]=data.productoList[i].codigo+'|'+data.productoList[i].nombre;
                        }
                    }
                }else{
                    $("#gettingInfo").html("");
                    $("#gettingInfo").append("<p>"+data.resultado+"</p>");
                    dataArray[0] = data.resultado;
                }
            });//END getJSON
            return dataArray;
	}
        $("#items").autocomplete({
            source: arreglo()
        });
        $(document).on('click', '.eliminar', function() {
            $(this).parent().parent().remove();
        });
        function traerProducto(clipArray){
           if(getIds(clipArray[0])){//Si el articulo NO se encuentra EN LA TABLA
                addRow= "<tr class='productosVenta'><td><div class='productoId'>"+clipArray[0]+"</div></td><td>"+clipArray[1]+"</td><td><img src='images/borrar.png' width='80px' height='70px' style='cursor:pointer;' class='eliminar'/></td></tr>";
                $(".granTotal").before(addRow);
                setTimeout(function(){$("#items").focus();$("#items").val("");},250);
                $("#todos").remove();
            }else{
                setTimeout(function(){$("#items").focus();$("#items").val("");},250);
            }
        }//fin function traerProducto
        function getIds(idBuscar){
            var aAgregar = parseInt($.trim(idBuscar));
            var enTabla = 0 ;
            res = true;
            $(".productoId").each( function(i){
                enTabla = parseInt($(this).text());
                if(enTabla == aAgregar){
                    res = false;
                }
            });
            return res;
        }
        function getIdesSubmit(){
            enTabla = '';
            $(".productoId").each( function(i){
                if(enTabla==''){
                        enTabla = "'"+$(this).text()+"'";
                }else{
                        enTabla +=", '"+$(this).text()+"'";
                }
            });
            $("#items").val(enTabla);
        }
      /*$( "#items" ).on( "autocompleteselect", function( event, ui ) {
            producto = $("#items").val();
            clipArray = producto.split("|");
            traerProducto(clipArray);
        });
        */
        
        
        $("#items").autocomplete({
            select: function (event, ui) {
                producto = ui.item.value;
                clipArray = producto.split("|");
                traerProducto(clipArray);
                $("#items").val("");
            }
        });
        
        
        
 });//end doc ready