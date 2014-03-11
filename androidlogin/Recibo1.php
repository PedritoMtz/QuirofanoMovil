<?php 
//Recibir datos, luego los mete a la BD
$usuario = $_POST['usuario'];
$passw = $_POST['password'];
require_once 'funciones_bd.php';
$db = new funciones_BD();
	if($db->isuserexist($usuario,$passw)){

		echo(" Este usuario ya existe ingrese otro diferente!");
		$resultado[]=array("logstatus"=>"0");
		echo json_encode($resultado);	
	}else{

		if($db->adduser($usuario,$passw)) {	
			echo(" El usuario fue agregado a la Base de Datos correctamente.");	
			$resultado[]=array("logstatus"=>"1");
			echo json_encode($resultado);		
		}
		else {
			echo(" ha ocurrido un error.");
		}		
	}
?>

