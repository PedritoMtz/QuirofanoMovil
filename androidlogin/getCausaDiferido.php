
<?php 
/*
	 Obtener las causas de diferido para llenar spinner de la seccion diferir cirugia
*/

$var = $_POST['registro_id'];

require_once 'funciones_bd.php';
$db = new funciones_BD();
	$db->getCausaDiferido($var);		
?>