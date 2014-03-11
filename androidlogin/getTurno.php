<?php 
/*
	 Obtener todos los turnos disponibles para llenar los spinners de la accion iniciar cirugia (tabla: siga_turno)
*/
$var = $_POST['ID'];

require_once 'funciones_bd.php';
$db = new funciones_BD();
	
	$db->getTurno($var);		
?>