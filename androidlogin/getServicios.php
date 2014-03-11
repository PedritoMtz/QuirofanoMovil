
<?php 
/*
	 Obtener los servicios para llenar spinner de la seccion programar cirugia
*/

$var = $_POST['quirofano_id'];

require_once 'funciones_bd.php';
$db = new funciones_BD();
	$db->getServicios($var);		
?>