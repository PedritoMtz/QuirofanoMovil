<?php 
/*Muestra las cirugias programadas del dia (status=1)*/

$status = $_POST['stat']; //valor de quirofano_id seleccionado
$intQuirofano = (int) $status;

require_once 'funciones_bd.php';

$db = new funciones_BD();
	$db->getProcedimientosProgramadasActual($intQuirofano);	

?>
