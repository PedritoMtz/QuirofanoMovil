<?php 
/*Show diary - programadas que estan fuera de fecha(pasadas)*/

$status = $_POST['stat']; //valor de quirofano_id seleccionado
$intQuirofano = (int) $status;

require_once 'funciones_bd.php';

$db = new funciones_BD();
	$db->getProgramadasPasadas($intQuirofano);	

/*
$db = new funciones_BD();
	if($status == "ok"){
		$db->test();	
	}
*/	


	//if($status == "ok"){
		//$resultado[]=array("dato"=>"1");
		//$resultado[]=array("logstatus"=>"0");
	//	$resultado[]=array("dato"=>$db->mostrarAgenda());

	//	echo json_encode($resultado);
	//}//Fin de if
?>

