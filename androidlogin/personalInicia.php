<?php 
/*Devolver el personal que inicia la cirugia*/

$registro = $_POST['registro_id'];	//Recibe el id de la cirugia seleccionada
$newRegistro = (int)$registro;

require_once 'funciones_bd.php';

$db = new funciones_BD();
	$db->getPersonalInicia($newRegistro);	
?>


