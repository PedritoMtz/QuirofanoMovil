
<?php 
/*
	 Obtener todas las salas del mismo quirofano_id (por medio del quirofano_id)
*/

//EditText de la aplicacion
//$quirofano = $_POST['quirofano_name'];

$var = $_POST['quirofano_id'];

require_once 'funciones_bd.php';
$db = new funciones_BD();
	
	$db->getSalas($var);		
?>