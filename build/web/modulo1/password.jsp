<script src="scripts/password.min.js"> </script>
<link href="styles/tables.css" rel="stylesheet" type="text/css" />
<style type="text/css">
<!--                                 
#wrapper {
width: 600px;
margin: 0 auto;
text-align: center;
background:#00FF00;
align:center
}
body {
	text-align: center;
	background-color: #CCCCCC;
}
#content {
width: 650px;
float: center;
align:center
}
#content h1, #content h2, #content p {
padding-right: 20px;
}
-->
</style>
	<div id="gettingInfo" title="Mensaje">
	</div>
	<div id="wrapper" align="center">
		<form id="form1" action="password2" method="post">
		<div id="content">
			<table class="hovertable" width="100%">
			  <tr>
				<th colspan="2"><div align="center">Modificacion de contrase�a</div></th>
			  </tr>
                          <tr>
				<th align="right">
					Contrase�a actual:
				</th>
				<th align="left">
					<input type="password" name="actual" id="jsActual"/>
				</th>
			  </tr>
			  <tr>
				<th align="right">
					Contrase�a nueva:
				</th>
				<th align="left">
					<input type="password" name="nueva1" id="jsNueva1"/>
				</th>
			  </tr>
			  <tr>
				<th align="right">
					Repita nueva contrase�a:
				</th>
				<th align="left">
					<input type="password" name="nueva2" id="jsNueva2"/>
                                </th>
                         </tr>
                         <tr>
                                <th colspan="2"><div align="center">
                                        <input type="button" value="Modificar contrase�a" id="btnPassword"/>
                                </div></th>
                         </tr>
                      </table>
		</div>
		</form>
	</div>