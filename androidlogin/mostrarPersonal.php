<?php 
/*Mostrar personal agregado en cirugia, devuelve el nombre del personal y tambien el tipo*/

$registro = $_POST['registro_id'];	//Recibe el id de la cirugia seleccionada
$newRegistro = (int)$registro;

require_once 'funciones_bd.php';

$db = new funciones_BD();
	$db->mostrarPersonal($newRegistro);	
?>


