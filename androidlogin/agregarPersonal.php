
<?php 
/*Agregar personal a la cirugia*/

//EditText de la aplicacion
$nombre_personal = $_POST['nombre_personal'];

//Spinners
$tipo_personal = $_POST['tipo_personal'];
$status_personal = $_POST['status_personal'];
$turno_personal = $_POST['turno_personal'];
//to int
$intTipo_personal = (int) $tipo_personal;
$intStatus_personal = (int) $status_personal;
$intTurno_personal = (int) $turno_personal;

//Numero de ID de la cirugia seleccionada
$registro_ID = $_POST['registro_ID'];
//to int
$intRegistro_ID = (int) $registro_ID;

require_once 'funciones_bd.php';
$db = new funciones_BD();

	if($db->agregarPersonal($nombre_personal, $intTipo_personal, $intStatus_personal, $intTurno_personal, $intRegistro_ID)) {		
		$resultado[]=array("logstatus"=>"1");
		echo json_encode($resultado);	
	}
	
	else {
		//echo(" Ha ocurrido un error durante la programacion de la cirugia.");
		$resultado[]=array("logstatus"=>"0");
		echo json_encode($resultado);
	}

?>


