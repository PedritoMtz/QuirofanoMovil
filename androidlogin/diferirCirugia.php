<?php 
/*Diferir cirugia, necesitamos de la causa de diferido(int) y de el id del registro(agenda_id, int)*/

//EditText de la aplicacion
$causa_diferido = $_POST['causa_diferido'];
$agenda_id = $_POST['agenda_id'];

//to int
$intCausa_diferido = (int)$causa_diferido;
$intAgenda_id = (int)$agenda_id;

require_once 'funciones_bd.php';
$db = new funciones_BD();

	if($db->diferirCirugia($intCausa_diferido, $intAgenda_id)) {		
		$resultado[]=array("logstatus"=>"1");
		echo json_encode($resultado);	
	}
	else {
		//echo(" Ha ocurrido un error durante la programacion de la cirugia.");
		$resultado[]=array("logstatus"=>"0");
		echo json_encode($resultado);
	}		
?>