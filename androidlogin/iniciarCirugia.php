
<?php 
/*Guardar formulario de iniciar cirugia*/

//EditText de la aplicacion
$ingreso = $_POST['ingreso'];
$nombre_medico = $_POST['nombre_medico'];
$nombre_cirujano = $_POST['nombre_cirujano'];
$nombre_anestesiologo = $_POST['nombre_anestesiologo'];
$nombre_supervisor = $_POST['nombre_supervisor'];
$tipo_tecnica = $_POST['tipo_tecnica'];
$nombre_instrumentista = $_POST['nombre_instrumentista'];
$nombre_circulante = $_POST['nombre_circulante'];
$observaciones = $_POST['observaciones'];

//Spinners y RadioButton de la aplicacion
$tiempoFuera = $_POST['tiempoFuera'];
//to int
$intTiempoFuera = (int)$tiempoFuera;

//Spinners
$turnoInstrumentista = $_POST['turnoInstrumentista'];
$turnoCirculante = $_POST['turnoCirculante'];
//to int
$intTurnoInstrumentista = (int)$turnoInstrumentista;
$intTurnoCirculante = (int)$turnoCirculante;
//id del registro de la agenda
$id_agenda = $_POST['agenda_id'];
//to int
$intId_agenda = (int)$id_agenda;

//quirofano_id
//$quirofano = $_POST['q_id'];
//$newQuirofano = (int)$quirofano;

require_once 'funciones_bd.php';
$db = new funciones_BD();

	if($db->iniciarCirugia($ingreso, $nombre_medico, $nombre_cirujano, $nombre_anestesiologo, $nombre_supervisor, $tipo_tecnica,
		$nombre_instrumentista, $nombre_circulante, $observaciones, $intTiempoFuera, $intTurnoInstrumentista, $intTurnoCirculante,
		$intId_agenda)) {		
		$resultado[]=array("logstatus"=>"1");
		echo json_encode($resultado);	
	}
	else {
		//echo(" Ha ocurrido un error durante la programacion de la cirugia.");
		$resultado[]=array("logstatus"=>"0");
		echo json_encode($resultado);
	}		
?>


