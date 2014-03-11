<?php 
/*Muestra los procedimientos de las cirugias programadas para mostrarlos en la seccion 
"modificar cirugia" de la aplicación movil*/

$status = $_POST['stat']; //valor de quirofano_id seleccionado
$intQuirofano = (int) $status;

require_once 'funciones_bd.php';

$db = new funciones_BD();
	$db->getProcedimientosCirugiaProgramada($intQuirofano);	

?>