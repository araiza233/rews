
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<button id="move_up">Move Up</button>
<button id="move_down">Move Down</button>
<button id="color">Change Color</button>
<button id="disappear">Disappear/Re-appear</button>
<div id="change_me">Make Me Do Stuff!</div>

<div id="clickMe">Show me the Furry Friend of the Day</div>
<div id="picframe">
    <img src="images/zared.gif">
</div>
<script>   
 $(document).ready(function() {
    $("#move_up").click( function() {
        $("#change_me").animate({top:30},200);
    });//end move_up
    $("#move_down").click( function() {
        $("#change_me").animate({top:500},2000);
    });//end move_down
    $("#color").click( function() {
        $("#change_me").css("color", "purple");
    });//end color
    $("#disappear").click( function() {
        $("#change_me").toggle("slow");
    });//end disappear
    $("#introForm").submit(function(){
		var formInput=$(this).serialize();
		$.getJSON('ajax/sayHi',formInput,function(data){
			alert('Despues de volver del java');
			$('#cargarConAjax').html('' + data.greeting + '');
			$.each(data.countryList,function(index, value){
				$("ol").append("<li>value " + value+"</li>");
			});
			$.each(data.countryMap,function(key, value){
				$('#cargarConAjax').append("<br>key "+ key + ", value " + value);
			});
		});//END getJSON
		return false;
    });//END ("#introForm").submit
	
	
	$("#clickMe").click(function() {
		$("img").fadeIn(1000);   $("#picframe").slideToggle("slow");
	});// END clickMe
	$("#picframe").hover(
		function () {
		// this is the mouseenter event handler
			$(this).addClass("my_hover");
		},
		function () {
		// this is the mouseleave event handler
			$(this).removeClass("my_hover");
	});// END picframe
 });//end doc ready
 
 

   
   
</script>
<h4>Ajax using JQuery</h4>
<form action="" id="introForm">
    <label for="name">Boton para enviar llamar JQuery: </label>
	<input name="name">
    <input type="submit" id="enviarForm">
</form>
<div id="cargarConAjax">
    <s:property value="name" />
	Cargar con pinche Ajax
</div>

<ol>

</ol>