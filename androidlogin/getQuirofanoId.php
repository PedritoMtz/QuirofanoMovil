
<?php 
/*Obtener el quirofano_id, por medio de la nombre del quirofano, ej: Central=1*/

//EditText de la aplicacion
$quirofano = $_POST['quirofano_name'];

require_once 'funciones_bd.php';
$db = new funciones_BD();
	
	$db->getQuirofanoId($quirofano);		
?>


