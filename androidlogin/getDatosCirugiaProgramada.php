
<?php 
/*
	 Obtener los datos de la cirugia programada - recibe el id del registro y manda toda la informacion
	 de la cirugia programada para mostrarla en la seccion de modificar cirugia de la aplicacion movil.
*/

$var = $_POST['registro_id'];

require_once 'funciones_bd.php';
$db = new funciones_BD();
	$db->getDatosCirugiaProgramada($var);		
?>