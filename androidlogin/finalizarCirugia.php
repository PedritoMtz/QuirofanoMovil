
<?php 
/*Finalizar cirugia seleccionada (mediante el ID del registro seleccionado)*/
		
//EDITTEXT de la aplicacion **************************************
$sSalida = $_POST['sSalida'];
$sEventosEnAnestesia = $_POST['sEventosEnAnestesia'];
$sComplicaciones = $_POST['sComplicaciones'];

//SPINNERS *******************************************************
$sClasificacion = $_POST['sClasificacion'];
$sEvento = $_POST['sEvento'];
$sContaminacion = $_POST['sContaminacion'];
$sInfeccion = $_POST['sInfeccion'];

//to int
$intClasificacion = (int) $sClasificacion;
$intEvento = (int) $sEvento;
$intContaminacion = (int) $sContaminacion;
$intInfeccion = (int) $sInfeccion;

//RADIOBUTTON *****************************************************
$sD = $_POST['sD'];
//to int
$intD = (int) $sD;

//LARGO DE PERSONAL (CHECKLIST)
$largoPersonal = $_POST['largoPersonal'];
//to int
$intLargoPersonal = (int) $largoPersonal;

//Numero de ID de la cirugia seleccionada
$registro_ID = $_POST['registro_ID'];
//to int
$intRegistro_ID = (int) $registro_ID;

//CHECKLIST *******************************************************
$numero2 = count($_POST);			//OBTIENE EL LARGO DE MENSAJES ENVIADOS POR POST
$tags2 = array_keys($_POST); 		//OBTIENE LOS NOMBRES DE LAS VARIABLES ENVIADAS POR POST
$valores2 = array_values($_POST);	//OBTIENE LOS VALORES DE LAS VARIABLES ENVIADAS POR POST

// crea las variables y les asigna el valor
for($i=0;$i<$numero2;$i++){ 
	$$tags2[$i]=$valores2[$i]; 
}

array_splice($tags2, 0, 10);		//Borramos los primeros 10 elementos del array
array_splice($valores2, 0, 10);		//Borramos los primeros 10 elementos del array

$number = count($valores2);		//Obtener la nueva cantidad de elementos del array



//Array para poner los elementos de personal (incluidos los que finalizan y no finalizan)
$arrayPersonal = array();
$arrayPersonalID = array();

//Para llenar el array del personal 
for($index = 0; $index<$number; $index++){ //LLENAR ARRAY DE PERSONAL
	if($index < $number/2){
		array_push($arrayPersonal, $valores2[$index]);
	}
	else{
		array_push($arrayPersonalID, $valores2[$index]);
	}
	
}

require_once 'funciones_bd.php';
$db = new funciones_BD();
	/*
	$db->finalizarCirugia($sSalida, $sEventosEnAnestesia, $sComplicaciones, $intClasificacion, $intEvento, $intContaminacion, 
		$intInfeccion, $intD, $intRegistro_ID, $arrayPersonal, $intLargoPersonal);
	*/
	if($db->finalizarCirugia($sSalida, $sEventosEnAnestesia, $sComplicaciones, $intClasificacion, $intEvento, $intContaminacion, 
		$intInfeccion, $intD, $intRegistro_ID, $arrayPersonal, $arrayPersonalID, $intLargoPersonal)) {		
		$resultado[]=array("logstatus"=>"1");
		echo json_encode($resultado);	
	}
	
	else {
	//echo(" Ha ocurrido un error durante la programacion de la cirugia.");
		$resultado[]=array("logstatus"=>"0");
		echo json_encode($resultado);
	}

?>


