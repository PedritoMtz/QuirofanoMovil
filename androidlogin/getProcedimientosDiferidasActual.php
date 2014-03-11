<?php 
/*Muestra los procedimientos de las cirugias diferidas del dia de hoy*/

$status = $_POST['stat']; //valor de quirofano_id seleccionado
$intQuirofano = (int) $status;

require_once 'funciones_bd.php';

$db = new funciones_BD();
	$db->getProcedimientosDiferidasActual($intQuirofano);	

?>