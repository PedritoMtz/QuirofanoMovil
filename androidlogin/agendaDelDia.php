
<?php 
/*Agenda del dia - Envia los registros del dia a la aplicacion Android*/

$id_quirofano = $_POST['quirofano'];

require_once 'funciones_bd.php';
$db = new funciones_BD();
		$id = (int)$id_quirofano;
		$db->agendaDelDia($id);	
	//if($status == "ok"){
		//$resultado[]=array("dato"=>"1");
		//$resultado[]=array("logstatus"=>"0");
	//	$resultado[]=array("dato"=>$db->mostrarAgenda());

	//	echo json_encode($resultado);
	//}//Fin de if
?>


