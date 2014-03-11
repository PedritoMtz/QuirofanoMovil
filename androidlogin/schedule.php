
<?php 
/*Schedule a surgery - Programar cirugia*/

//EditText de la aplicacion
$date = $_POST['date'];
$hora = $_POST['hora'];
$registro = $_POST['reg'];
$paciente = $_POST['paciente'];
$edad = $_POST['sEdad'];
$genero = $_POST['sGenero'];
$procedencia = $_POST['sProcedencia'];
$diagnostico = $_POST['sDiagnostico'];
$medico = $_POST['sMedico'];
$riesgoQuirurgico = $_POST['sRiesgoQuirurgico'];
$insumos = $_POST['sInsumosIndispensables'];
$requerimientosAnestesiologia = $_POST['sRequerimientos'];
$hemoderivados = $_POST['sHemoderivados'];
$requisitosLaboratorio = $_POST['sRequisitos'];
$otrasNecesidades = $_POST['sNecesidades'];

//Spinners y RadioButton de la aplicacion
$sala = $_POST['sSala'];
$duracion = $_POST['sDuracion'];
$programacion = $_POST['sProgramacion'];
$servicio = $_POST['sServicio'];
$atencion = $_POST['sAtencion'];
$p = $_POST['sP'];
$r = $_POST['sR'];


$newSala = (int)$sala;					//int
$newDuracion = $duracion;				//time
$newProgramacion = (int)$programacion;	//int
$newServicio = (int)$servicio;			//int
$newAtencion = (int)$atencion;			//int
$newP = (int)$p;						//tinyint(1)
$newR = (int)$r; 						//tinyint(1)

//quirofano_id
$quirofano = $_POST['q_id'];
$newQuirofano = (int)$quirofano;

$sala2 = $newSala; //Quite el +1, id de salas arreglado, 3 ene

require_once 'funciones_bd.php';
$db = new funciones_BD();

	$db->programarCirugia($date, $hora, $registro, $paciente, $edad, $genero, $procedencia, $diagnostico, $medico, $riesgoQuirurgico,
		$insumos, $requerimientosAnestesiologia, $hemoderivados, $requisitosLaboratorio, $otrasNecesidades, $sala2, $newDuracion,
		$newProgramacion, $newServicio, $newAtencion, $newP, $newR, $newQuirofano);

	/*
	if($db->programarCirugia($date, $hora, $registro, $paciente, $edad, $genero, $procedencia, $diagnostico, $medico, $riesgoQuirurgico,
		$insumos, $requerimientosAnestesiologia, $hemoderivados, $requisitosLaboratorio, $otrasNecesidades, $sala2, $newDuracion,
		$newProgramacion, $newServicio, $newAtencion, $newP, $newR, $newQuirofano)) {
	*/
	/*
	if($db->programarCirugia($date, $hora, $registro, $paciente, $edad, $genero, $procedencia, $diagnostico, $medico, $riesgoQuirurgico,
		$insumos, $requerimientosAnestesiologia, $hemoderivados, $requisitosLaboratorio, $otrasNecesidades)) {		
	*/
		//echo(" La cirugia fue programada correctamente.");	
		//$resultado[]=array("logstatus"=>"1");
		//echo json_encode($resultado);	
			
	//}
	//else {
	//	//echo(" Ha ocurrido un error durante la programacion de la cirugia.");
	//	$resultado[]=array("logstatus"=>"0");
	//	echo json_encode($resultado);
	//}		
?>


