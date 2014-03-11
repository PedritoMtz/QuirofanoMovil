
<?php 
/*
	 Cancelar cirugia: 
	 	1. prueba 1 con todos los parametros de la agenda.
	 	2. prueba 2 con el id. (Y)
*/

$id = $_POST['ID'];

require_once 'funciones_bd.php';
$db = new funciones_BD();
	if ($db->cancelarCirugia($id)){
		$resultado[]=array("logstatus"=>"1");
        echo json_encode($resultado);
	}
	else{
		$resultado[]=array("logstatus"=>"0");
        echo json_encode($resultado);
	}
			
?>