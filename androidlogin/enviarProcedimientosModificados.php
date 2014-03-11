
<?php 
/*Programar procedimientos modificados apartir del id de la cirugia programada (Procedimientos de vistas dinamicas)
**Maximo 4 procedimientos*/

$numRegistro = $_POST['numRegistro'];
$numRegistroInt = (int)$numRegistro;

$numero2 = count($_POST);
$tags2 = array_keys($_POST); // obtiene los nombres de las varibles
$valores2 = array_values($_POST);// obtiene los valores de las variables

// crea las variables y les asigna el valor
for($i=0;$i<$numero2;$i++){ 
	$$tags2[$i]=$valores2[$i]; 
}

array_splice($tags2, 0, 1);		//Borramos el primer elemento de los array
array_splice($valores2, 0, 1);	

$number = count($valores2);		//Obtener la nueva cantidad de elementos del array

/*OPCION ALTERNATIVA - ARRAY PARA CADA CAMPO*/

$arrayServicio = array();
$arrayRegion = array();
$arrayProcedimiento = array();							
$arrayDetalles = array();

//Para llenar el array de servicios
for($index = 0; $index<$number; $index+=4){ //LLENAR ARRAY DE SERVICIOS
	if($index == 0){
		//array_push($arrayServicio, (int)$valores2[$index]);
		array_push($arrayServicio, $valores2[$index]);
	}
	else{
		//array_push($arrayServicio, (int)$valores2[floor(($index / 4))]);   
		//array_push($arrayServicio, (int)$valores2[$index]);
		array_push($arrayServicio, $valores2[$index]);
	}
}

//Para llenar el array de region
for($index2 = 1; $index2<$number; $index2+=4){ //LLENAR ARRAY DE SERVICIOS
	if($index2 == 1){
		//array_push($arrayRegion, (int)$valores2[$index2]);
		array_push($arrayRegion, $valores2[$index2]);
	}
	else{
		//array_push($arrayRegion, (int)$valores2[floor(($index2 / 4))]);
		//array_push($arrayRegion, (int)$valores2[$index2]);
		array_push($arrayRegion, $valores2[$index2]);
	}
}

//Para llenar el array de procedimientos
for($index3 = 2; $index3<$number; $index3+=4){ //LLENAR ARRAY DE SERVICIOS
	if($index3 == 2){
		array_push($arrayProcedimiento, $valores2[$index3]);
	}
	else{
		//array_push($arrayProcedimiento, $valores2[floor(($index3 / 4))]);
		array_push($arrayProcedimiento, $valores2[$index3]);
	}
}

//Para llenar el array de detalles
for($index4 = 3; $index4<$number; $index4+=4){ //LLENAR ARRAY DE SERVICIOS
	if($index4 == 3){
		array_push($arrayDetalles, $valores2[$index4]);
	}
	else{
		//array_push($arrayDetalles, $valores2[floor(($index4 / 4))]);
		array_push($arrayDetalles, $valores2[$index4]);
	}
}

/*arrays de campos llenos*/

//Almacenar cada uno de los campos de todos los procedimientos
//$camposProcedimientos = array();

/*Orden: servicio, region, procedimientos, detalles*/
//for ($i=0; $i<$largoProcedimientos; $i++){ 
//
//	$sql = mysql_query("INSERT INTO hc_agenda_procedimientos (servicio_id, region, cie9mc, detalles) 
//		VALUES($arrayServicio[$i], $arrayRegion[$i], '$arrayProcedimiento[$i]', '$arrayDetalles[$i]'");
//}  

$largoProcedimientos = ($number/4);

require_once 'funciones_bd.php';
$db = new funciones_BD();
	if($db->enviarProcedimientosModificados($numRegistroInt, $largoProcedimientos, $arrayServicio, $arrayRegion, 
		$arrayProcedimiento, $arrayDetalles)){
		$resultado[]=array("respuesta"=>"1");
		echo json_encode($resultado);	
	}
	else{
		$resultado[]=array("respuesta"=>"0");
		echo json_encode($resultado);	
	}
			
?>