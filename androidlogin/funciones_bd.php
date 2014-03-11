<?php
 
class funciones_BD {
 
    private $db;
 
    // constructor

    function __construct() {
        require_once 'connectbd.php';
        // connecting to database

        $this->db = new DB_Connect();
        $this->db->connect();

    }
 
    // destructor
    function __destruct() {
 
    }
 
    /**
     * agregar nuevo usuario
     */
    public function adduser($username, $password) {

    $result = mysql_query("INSERT INTO sf_guard_user(username,password) VALUES('$username', '$password')");
        // check for successful store

        if ($result) {

            return true;

        } else {

            return false;
        }

    }
 
 
     /**
     * Verificar si el usuario ya existe por el username
     */

    public function isuserexist($username) {

        $result = mysql_query("SELECT username from usuarios WHERE username = '$username'");
        $num_rows = mysql_num_rows($result); //numero de filas retornadas

        if ($num_rows > 0) {
            // el usuario existe 
            return true;

        } else {
            // no existe
            return false;
        }
    }
 
   
	public function login($user,$password){

	//$pass = sha1($password);

	$result=mysql_query("SELECT COUNT(*) FROM sf_guard_user WHERE username='$user' AND password='$password' "); 
	$count = mysql_fetch_row($result);

	/*como el usuario debe ser unico cuenta el numero de ocurrencias con esos datos*/


		if ($count[0]==0){
			/*no hubo coincidencias*/
		return true;

		}else{
			/*si hubo coincidencias*/
		return false;

		}
	}

	// if (loginstatus(user,pass)==true){    

	public function closeConnection(){
        
    } /*Cerrar conexion con la BD*/

	//public function programarCirugia($date, $hora, $registro, $paciente){
	public function programarCirugia($date, $hora, $registro, $paciente, $edad, $genero, $procedencia, $diagnostico, $medico, $riesgoQuirurgico,
        $insumos, $requerimientosAnestesiologia, $hemoderivados, $requisitosLaboratorio, $otrasNecesidades,$newSala, 
        $newDuracion,$newProgramacion, $newServicio, $newAtencion, $newP, $newR, $newQuirofano)
    {
        //$newSala, $newDuracion,$newProgramacion, $newServicio, $newAtencion, $newP, $newR

        $mesesEnSegundos = 3600*24*28;
		
        $fechaActual = date("Y-m-d H:i:s");
        $fechaApp = $date." ".$hora;

        $newFechaActual = strtotime($fechaActual);
        $newFechaApp = strtotime($fechaApp);
        $fechaActualMasMes = $newFechaActual+$mesesEnSegundos;

        $fechaseleccMax = $date;                  //el dia actual de la programación
        $fechaseleccMin = date("Y-m-d",strtotime($fechaseleccMax.' -1 day')); //Un dia antes por si la cirugía de un día antes continua actualmente

        if ($newFechaApp > $newFechaActual) { 
            if ($newFechaApp < $fechaActualMasMes){

                //$control = $this->emHoras($date,$hora,$Quirofano->getid(),$salaselecc,$tiempo_est,NULL);
                $control = $this->emHoras2($fechaseleccMax, $fechaseleccMin, $hora, $newQuirofano, $newSala, $newDuracion, $newQuirofano);
                //echo "CONTROL=".$control;
                if ($control != "ok") {     //si es diferente a null quiere decir que se empalma con alguna programación anterior
                    //*********CIRUGIA EMPALMADA CON "$control"
                    //$text = 'se empalma con la cirugia: '.$control;
                    $text = "w".$control;
                    $resultado[]=array("registro_id"=>"$text");
                    echo json_encode($resultado);                
                }
                else {   // si es "ok" lo grabamos como debe ser
                    //********PROGRAMAR LA CIRUGIA NORMALMENTE
                
                    $result = mysql_query("INSERT INTO hc_agenda(programacion, hora, registro, paciente_name, edad, genero, procedencia, diagnostico,
                    riesgo_qx_pre, req_insumos, req_anestesico, req_hemoderiv, req_laboratorio, requerimiento,
                    sala_id, tiempo_est, tipo_proc_id, servicio, atencion_id, protocolo, reintervencion, quirofano_id, created_at,
                    fechaestado, horaestado) 
                    VALUES('$date', '$hora', '$registro', '$paciente', '$edad', '$genero', '$procedencia', '$diagnostico', 
                    '$riesgoQuirurgico', '$insumos', '$requerimientosAnestesiologia', '$hemoderivados', '$requisitosLaboratorio', 
                    '$otrasNecesidades', $newSala, '$newDuracion', $newProgramacion+1, $newServicio, $newAtencion+1, $newP, $newR, $newQuirofano,
                    '$fechaActual', '$date', '$hora');");

                    /*
                    $result = mysql_query("INSERT INTO hc_agenda(programacion, hora, registro, paciente_name, edad, genero, procedencia, diagnostico,
                        medico_name, riesgo_qx_pre, req_insumos, req_anestesico, req_hemoderiv, req_laboratorio, requerimiento) 
                        VALUES('$date', '$hora', '$registro', '$paciente', '$edad', '$genero', '$procedencia', '$diagnostico', '$medico', 
                        '$riesgoQuirurgico', '$insumos', '$requerimientosAnestesiologia', '$hemoderivados', '$requisitosLaboratorio', 
                        '$otrasNecesidades')");
                    */
                    // check for successful store
                    //en vez de inicio es registro

                    if ($result) {
                        //return true;
                        $res = mysql_query("SELECT LAST_INSERT_ID();");
                        $dato = mysql_fetch_assoc($res);
                        $temporary = $dato['LAST_INSERT_ID()'];

                        $sql01 = mysql_query("INSERT INTO hc_agenda_personal(agenda_id, personal_nombre, tipo, status, programa, finaliza, created_at) 
                        VALUES($temporary, '$medico', 0, 0, 1, 0, '$fechaActual')");

                        $resultado[]=array("registro_id"=>"$temporary");
                        echo json_encode($resultado);
                    }

                    else {
                        //return false;
                        $resultado2[]=array("registro_id"=>"error");
                        echo json_encode($resultado2);
                    }
                }//FIN DE else EMPALMAR :v
            }//COMPROBAR SI LA FECHA PROGRAMADA ES MENOR A 30 DIAS DESDE HOY

            else{
                $resultado2[]=array("registro_id"=>"error2");   //ERROR2: la fecha programada excede el limite mayor(1 mes)
                echo json_encode($resultado2);
            }
        }//COMPROBAR SI LA FECHA PROGRAMADA ES MAYOR AL DIA DE HOY

        else{
            $resultado2[]=array("registro_id"=>"error1");   //ERROR1: la fecha programada es menor que la actual
            echo json_encode($resultado2);
        }

		
	}//Fin de programar cirugia

    public function mostrarAgenda() {  //ya no se usa
        $result = mysql_query("SELECT id, programacion, hora, hora FROM hc_agenda"); /*AUN NO TERMINADA*/

        if ($result) {
            return $result;
        }

        else{
            return false;
        }
    }//Fin de mostrarAgenda

    /*
    while ($rsEmp = mysql_fetch_assoc($queEmp)){
        echo $rsEmp['username'];
    } 
    */

    public function test($quirofano_id){
        //$cont = mysql_query("SELECT * FROM hc_agenda");
        //$contador = mysql_num_rows($cont);
        //$res_contador[] = array("contador"=>$contador); 
        //echo json_encode($res_contador);
        $fechaActual = date("Y-m-d H:i:s");
        $newFecha = strtotime($fechaActual);

        //PROGRAMADAS
        $result = mysql_query("SELECT programacion, hora, sala_id, paciente_name, diagnostico, id, registro FROM hc_agenda WHERE 
            (quirofano_id='$quirofano_id') AND (cancelada=0) AND (status=1)");

        //HACER PRIMERO SELECT DE HC_AGENDA, TRAER REGISTROS DE CIRUGIAS NORMALES, APLICAR FILTRO, 
        //DENTRO DEL FILTRO REALIZAR OTRA CONSULTA (SELECT) DE LA TABLA HC_AGENDA_PERSONAL BUSCANDO NOMBRES DE MEDICO CON 
        //AGENDA ID ;) >>> HACER LO MISMO PARA PROGRAMADAS PASADAS, INCLUIR PARA PROCEDIMIENTOS(TENTATIVAMENTE)
        //Para los nombres de los médicos
        $procedimientosProgramadas =array();
        
        $resultado = array();
        
        while ($rsEmp = mysql_fetch_assoc($result)){
            $tem = ($rsEmp['programacion']);
            $temp = ($rsEmp['hora']);

            $fusion = $tem." ".$temp;
            
            $fechaTemporal = strtotime($fusion);
            
            $temp2 = ($rsEmp['sala_id']);
            $temp3 = ($rsEmp['paciente_name']);
            $temp4 = ($rsEmp['diagnostico']);
            $temp5 = ($rsEmp['id']);
            $temp6 = ($rsEmp['registro']);

            if($fechaTemporal>=$newFecha){
                //new 12 dic
                $result2 = mysql_query("SELECT personal_nombre FROM hc_agenda_personal WHERE (programa=1) AND (agenda_id=$temp5)");
                $nombreDelDoctor = mysql_fetch_assoc($result2);
                $temporary2 = ($nombreDelDoctor['personal_nombre']);
                //new 12 dic

                $miArray = array("dat"=>$tem, "dato"=>$temp, "dato1"=>$temp2, "dato2"=>$temp3, "dato3"=>$temp4, "dato4"=>$temp5, "dato5"=>$temp6, "dato6"=>$temporary2);

                $res[] = array("dat"=>$tem, "dato"=>$temp, "dato1"=>$temp2, "dato2"=>$temp3, "dato3"=>$temp4, "dato4"=>$temp5, "dato5"=>$temp6, "dato6"=>$temporary2);
                array_push($resultado, $miArray);
            }
        }//Fin de while
            
        echo json_encode($resultado);
        //echo json_encode($resultado);
    }//Fin de la funcion test

    public function agendaDelDia($quirofano){
        //Obtener fecha actual
        $f = date('Y-m-d');
        $newfecha = (string) $f;

        //Buscar con respecto a la fecha actual
        $result = mysql_query("SELECT programacion, hora, sala_id, paciente_name, diagnostico FROM hc_agenda WHERE 
            (quirofano_id=$quirofano) AND (programacion='$newfecha')");
        $resultado = array();

        while ($rsEmp = mysql_fetch_assoc($result)){
            $tem = ($rsEmp['programacion']);
            $temp = ($rsEmp['hora']);
            $temp2 = ($rsEmp['sala_id']);
            $temp3 = ($rsEmp['paciente_name']);
            $temp4 = ($rsEmp['diagnostico']);

            $miArray = array("dat"=>$tem, "dato"=>$temp, "dato1"=>$temp2, "dato2"=>$temp3, "dato3"=>$temp4);

            $res[] = array("dat"=>$tem, "dato"=>$temp, "dato1"=>$temp2, "dato2"=>$temp3, "dato3"=>$temp4);
            array_push($resultado, $miArray);
        }//Fin de while
            
        echo json_encode($resultado);
    }//Fin de agendaHoy 

    public function getQuirofanoId($quirofano_name){

        //Buscar id del quirofano_name dado
        $result = mysql_query("SELECT id FROM siga_quirofano WHERE nombre='$quirofano_name'");
        $dato = mysql_fetch_assoc($result);

        $temp = $dato['id'];
        $resultado[]=array("quirofano_id"=>"$temp");

        echo json_encode($resultado);
    }//Fin de agendaHoy 

    public function getQuirofanoName($var){

        $result = mysql_query("SELECT id, nombre FROM siga_quirofano");
        $resultado = array();

        while ($rsEmp = mysql_fetch_assoc($result)){
            $temp = ($rsEmp['id']);
            $temp2 = ($rsEmp['nombre']);

            $miArray = array("dato"=>$temp, "dato1"=>$temp2);

            $res[] = array("dato"=>$temp, "dato1"=>$temp2);
            array_push($resultado, $miArray);
        }//Fin de while

        echo json_encode($resultado);
    }//Fin de getQuirofanoName

    public function getSalas($var){

        $newVar = (int)$var;
        $result = mysql_query("SELECT id, nombre, activo, bloqueado FROM siga_sala WHERE quirofano_id=$newVar");
        $resultado = array();

        while ($rsEmp = mysql_fetch_assoc($result)){
            $temp = ($rsEmp['id']);
            $temp2 = ($rsEmp['nombre']);
            $temp3 = ($rsEmp['activo']);
            $temp4 = ($rsEmp['bloqueado']);


            $miArray = array("dato"=>$temp, "dato1"=>$temp2, "dato2"=>$temp3, "dato3"=>$temp4);

            $res[] = array("dato"=>$temp, "dato1"=>$temp2, "dato2"=>$temp3, "dato3"=>$temp4);
            array_push($resultado, $miArray);
        }//Fin de while

        echo json_encode($resultado);
    }//Fin de getSalas


    public function llenarProcedimientos($numRegistroInt, $largoProcedimientos, $arrayServicio, $arrayRegion, $arrayProcedimiento, $arrayDetalles){
        /*Orden: servicio, region, procedimientos, detalles*/
        $fechaActual = date("Y-m-d H:i:s");
        for ($i=0; $i<$largoProcedimientos; $i++){ 
            $region = $arrayRegion[$i];
            $intRegion = (int)$region;
            $servicio = $arrayServicio[$i];
            $intServicio = (int)$servicio;
            $stringProcedimiento = $arrayProcedimiento[$i];
            $stringDetalles = $arrayDetalles[$i];
            //$sql = mysql_query("INSERT INTO hc_agenda_procedimientos(servicio_id, region, cie9mc, detalles) 
            //VALUES($arrayServicio[$i], $arrayRegion[$i], '$arrayProcedimiento[$i]', '$arrayDetalles[$i]'");
            $sql = mysql_query("INSERT INTO hc_agenda_procedimientos(servicio_id, region, cie9mc, detalles, agenda_id, created_at) 
            VALUES($intServicio, $intRegion+1, '$stringProcedimiento', '$stringDetalles', $numRegistroInt, '$fechaActual')");
        } 

        if ($sql){
            return true;
        }
        else{
            return false;
        }

    }// Fin de llenarProcedimientos

    public function cancelarCirugia($id){
        $newId = (int)$id;
        //UPDATE 

        $sql = mysql_query("UPDATE hc_agenda SET cancelada=1 WHERE id=$newId");

        if($sql){
            return true;
        }
        else{
            return false;
        }
            //$sql = mysql_query("DELETE FROM hc_agenda_procedimientos WHERE agenda_id=$newId"); 
        //if ($sql){
        //    $query = mysql_query("DELETE FROM hc_agenda WHERE id=$newId");
        //    if ($query){
        //        return true;
        //    }
        //    else{
        //        return false;
        //    }
        //}
        //else{
        //    return false;
        //}
            
    }// Fin de cancelarCirugia

    //OBTENER CIRUGIAS DIFERIDAS
    public function getDiferidas($quirofano_id){

        //DIFERIDAS
        $result = mysql_query("SELECT programacion, hora, sala_id, paciente_name, diagnostico, id, registro FROM hc_agenda WHERE 
            (quirofano_id='$quirofano_id') AND (cancelada=0) AND (status=-50)");
      
        $resultado = array();
        
        while ($rsEmp = mysql_fetch_assoc($result)){
            $tem = ($rsEmp['programacion']);
            $temp = ($rsEmp['hora']);
            $temp2 = ($rsEmp['sala_id']);
            $temp3 = ($rsEmp['paciente_name']);
            $temp4 = ($rsEmp['diagnostico']);
            $temp5 = ($rsEmp['id']);
            $temp6 = ($rsEmp['registro']);

            //$miArray = array("dat"=>$tem, "dato"=>$temp, "dato1"=>$temp2, "dato2"=>$temp3, "dato3"=>$temp4, "dato4"=>$temp5);

            //$res[] = array("dat"=>$tem, "dato"=>$temp, "dato1"=>$temp2, "dato2"=>$temp3, "dato3"=>$temp4, "dato4"=>$temp5);
            //array_push($resultado, $miArray);
            //new 12 dic
            $result2 = mysql_query("SELECT personal_nombre FROM hc_agenda_personal WHERE (programa=1) AND (agenda_id=$temp5)");
            $nombreDelDoctor = mysql_fetch_assoc($result2);
            $temporary2 = ($nombreDelDoctor['personal_nombre']);
            //new 12 dic

            $miArray = array("dat"=>$tem, "dato"=>$temp, "dato1"=>$temp2, "dato2"=>$temp3, "dato3"=>$temp4, "dato4"=>$temp5, "dato5"=>$temp6, "dato6"=>$temporary2);

            $res[] = array("dat"=>$tem, "dato"=>$temp, "dato1"=>$temp2, "dato2"=>$temp3, "dato3"=>$temp4, "dato4"=>$temp5, "dato5"=>$temp6, "dato6"=>$temporary2);
            array_push($resultado, $miArray);
        }//Fin de while
            
        echo json_encode($resultado);
        //echo json_encode($resultado);
    }//Fin de la funcion getDiferidas

    //OBTENER CIRUGIAS EN TRANSOPERATORIO
    public function getTransoperatorio($quirofano_id){
  
        //TRANSOPERATORIO
        $result = mysql_query("SELECT programacion, hora, sala_id, paciente_name, diagnostico, id, registro FROM hc_agenda WHERE 
            (quirofano_id='$quirofano_id') AND (cancelada=0) AND (status=10)");
      
        $resultado = array();
        
        while ($rsEmp = mysql_fetch_assoc($result)){
            $tem = ($rsEmp['programacion']);
            $temp = ($rsEmp['hora']);
            $temp2 = ($rsEmp['sala_id']);
            $temp3 = ($rsEmp['paciente_name']);
            $temp4 = ($rsEmp['diagnostico']);
            $temp5 = ($rsEmp['id']);
            $temp6 = ($rsEmp['registro']);

            //$miArray = array("dat"=>$tem, "dato"=>$temp, "dato1"=>$temp2, "dato2"=>$temp3, "dato3"=>$temp4, "dato4"=>$temp5);

            //$res[] = array("dat"=>$tem, "dato"=>$temp, "dato1"=>$temp2, "dato2"=>$temp3, "dato3"=>$temp4, "dato4"=>$temp5);
            //array_push($resultado, $miArray);
            //12 dic
            $result2 = mysql_query("SELECT personal_nombre FROM hc_agenda_personal WHERE (programa=1) AND (agenda_id=$temp5)");
            $nombreDelDoctor = mysql_fetch_assoc($result2);
            $temporary2 = ($nombreDelDoctor['personal_nombre']);
            //new 12 dic

            $miArray = array("dat"=>$tem, "dato"=>$temp, "dato1"=>$temp2, "dato2"=>$temp3, "dato3"=>$temp4, "dato4"=>$temp5, "dato5"=>$temp6, "dato6"=>$temporary2);

            $res[] = array("dat"=>$tem, "dato"=>$temp, "dato1"=>$temp2, "dato2"=>$temp3, "dato3"=>$temp4, "dato4"=>$temp5, "dato5"=>$temp6, "dato6"=>$temporary2);
            array_push($resultado, $miArray);
        }//Fin de while
            
        echo json_encode($resultado);
        //echo json_encode($resultado);
    }//Fin de la funcion getTransoperatorio

    //OBTENER CIRUGIAS REALIZADAS
    public function getRealizadas($quirofano_id){
  
        //REALIZADAS
        $result = mysql_query("SELECT programacion, hora, sala_id, paciente_name, diagnostico, id, registro FROM hc_agenda WHERE 
            (quirofano_id='$quirofano_id') AND (cancelada=0) AND (status=100)");
      
        $resultado = array();
        
        while ($rsEmp = mysql_fetch_assoc($result)){
            $tem = ($rsEmp['programacion']);
            $temp = ($rsEmp['hora']);
            $temp2 = ($rsEmp['sala_id']);
            $temp3 = ($rsEmp['paciente_name']);
            $temp4 = ($rsEmp['diagnostico']);
            $temp5 = ($rsEmp['id']);
            $temp6 = ($rsEmp['registro']);

            //$miArray = array("dat"=>$tem, "dato"=>$temp, "dato1"=>$temp2, "dato2"=>$temp3, "dato3"=>$temp4, "dato4"=>$temp5);

            //$res[] = array("dat"=>$tem, "dato"=>$temp, "dato1"=>$temp2, "dato2"=>$temp3, "dato3"=>$temp4, "dato4"=>$temp5);
            //array_push($resultado, $miArray);
            $result2 = mysql_query("SELECT personal_nombre FROM hc_agenda_personal WHERE (programa=1) AND (agenda_id=$temp5)");
            $nombreDelDoctor = mysql_fetch_assoc($result2);
            $temporary2 = ($nombreDelDoctor['personal_nombre']);
            //new 12 dic

            $miArray = array("dat"=>$tem, "dato"=>$temp, "dato1"=>$temp2, "dato2"=>$temp3, "dato3"=>$temp4, "dato4"=>$temp5, "dato5"=>$temp6, "dato6"=>$temporary2);

            $res[] = array("dat"=>$tem, "dato"=>$temp, "dato1"=>$temp2, "dato2"=>$temp3, "dato3"=>$temp4, "dato4"=>$temp5, "dato5"=>$temp6, "dato6"=>$temporary2);
            array_push($resultado, $miArray);

        }//Fin de while
            
        echo json_encode($resultado);
        //echo json_encode($resultado);
    }//Fin de la funcion getTransoperatorio

    //OBTENER CIRUGIAS PROGRAMADAS PASADAS (FUERA DE FECHA)
    public function getProgramadasPasadas($quirofano_id){

        $fechaActual = date("Y-m-d H:i:s");
        $v = strtotime($fechaActual);
        //DIFERIDAS
        $result = mysql_query("SELECT programacion, hora, sala_id, paciente_name, diagnostico, id, registro FROM hc_agenda WHERE 
            (quirofano_id=$quirofano_id) AND (cancelada=0) AND (status=1)");
      
        $resultado = array();
        
        while ($rsEmp = mysql_fetch_assoc($result)){
            $tem = ($rsEmp['programacion']);
            $temp = ($rsEmp['hora']);

            $fusion = $tem." ".$temp;

            $programacionDate = strtotime($fusion);

            $temp2 = ($rsEmp['sala_id']);
            $temp3 = ($rsEmp['paciente_name']);
            $temp4 = ($rsEmp['diagnostico']);
            $temp5 = ($rsEmp['id']);
            $temp6 = ($rsEmp['registro']);

            if ($programacionDate<$v){

                $result2 = mysql_query("SELECT personal_nombre FROM hc_agenda_personal WHERE (programa=1) AND (agenda_id=$temp5)");
                $nombreDelDoctor = mysql_fetch_assoc($result2);
                $temporary2 = ($nombreDelDoctor['personal_nombre']);
                //new 12 dic

                $miArray = array("dat"=>$tem, "dato"=>$temp, "dato1"=>$temp2, "dato2"=>$temp3, "dato3"=>$temp4, "dato4"=>$temp5, "dato5"=>$temp6, "dato6"=>$temporary2);

                $res[] = array("dat"=>$tem, "dato"=>$temp, "dato1"=>$temp2, "dato2"=>$temp3, "dato3"=>$temp4, "dato4"=>$temp5, "dato5"=>$temp6, "dato6"=>$temporary2);
                array_push($resultado, $miArray);

                //$miArray = array("dat"=>$tem, "dato"=>$temp, "dato1"=>$temp2, "dato2"=>$temp3, "dato3"=>$temp4, "dato4"=>$temp5);

                //$res[] = array("dat"=>$tem, "dato"=>$temp, "dato1"=>$temp2, "dato2"=>$temp3, "dato3"=>$temp4, "dato4"=>$temp5);
                //array_push($resultado, $miArray);
            }
        }//Fin de while
            
        echo json_encode($resultado);
        //echo json_encode($resultado);
    }//Fin de la funcion getProgramacionPasadas

    public function iniciarCirugia($ingreso, $nombre_medico, $nombre_cirujano, $nombre_anestesiologo, $nombre_supervisor, $tipo_tecnica,
        $nombre_instrumentista, $nombre_circulante, $observaciones, $intTiempoFuera, $intTurnoInstrumentista, $intTurnoCirculante,
        $intId_agenda){
        
        $fechaActual = date("Y-m-d H:i:s");
        $onlyDate = date("Y-m-d");

        $ingreso = $onlyDate." ".$ingreso;

        //Tabla de hc_agenda
        //UPDATE nombretabla SET nomcolumna=expresion WHERE condicion ;
        $result = mysql_query("UPDATE hc_agenda SET ingreso='$ingreso', tiempo_fuera=$intTiempoFuera, anestesia_empleada='$tipo_tecnica', 
            observaciones='$observaciones', status=10, updated_at='$fechaActual' WHERE id=$intId_agenda");

        //Tabla de hc_agenda_personal ****************************************************
        //Para medico que inicia el procedimiento
        $sql1 = mysql_query("INSERT INTO hc_agenda_personal(agenda_id, personal_nombre, tipo, status, programa, inicia, transoperatorio, created_at) 
        VALUES($intId_agenda, '$nombre_medico', 0, 0, 0, 1, 1, '$fechaActual')");

        //Para cirujano que supervisa
        $sql2 = mysql_query("INSERT INTO hc_agenda_personal(agenda_id, personal_nombre, tipo, status, programa, inicia, transoperatorio, created_at) 
        VALUES($intId_agenda, '$nombre_cirujano', 0, 1, 0, 1, 1, '$fechaActual')");

        //Para anestesiologo inicial
        $sql3 = mysql_query("INSERT INTO hc_agenda_personal(agenda_id, personal_nombre, tipo, status, programa, inicia, transoperatorio, created_at) 
        VALUES($intId_agenda, '$nombre_anestesiologo', 1, 0, 0, 1, 1, '$fechaActual')");

        //Para anestesiologo que supervisa
        $sql4 = mysql_query("INSERT INTO hc_agenda_personal(agenda_id, personal_nombre, tipo, status, programa, inicia, transoperatorio, created_at) 
        VALUES($intId_agenda, '$nombre_supervisor', 1, 1, 0, 1, 1, '$fechaActual')");

        //Para instrumentista que inicia
        $sql5 = mysql_query("INSERT INTO hc_agenda_personal(agenda_id, personal_nombre, tipo, status, programa, inicia, transoperatorio, 
            created_at, turno) 
        VALUES($intId_agenda, '$nombre_instrumentista', 2, 2, 0, 1, 1, '$fechaActual', $intTurnoInstrumentista)");

        //Para circulante que inicia
        $sql6 = mysql_query("INSERT INTO hc_agenda_personal(agenda_id, personal_nombre, tipo, status, programa, inicia, transoperatorio, 
            created_at, turno) 
        VALUES($intId_agenda, '$nombre_circulante', 2, 3, 0, 1, 1, '$fechaActual', $intTurnoCirculante)");

        //if ($result and $sql){
        if ($result){
            return true;
        }
        else{
            return false;
        }
    }//Fin de iniciar cirugia

    public function getTurno($var){

        $newVar = (int)$var;
        $result = mysql_query("SELECT id, nombre FROM siga_turno");
        $resultado = array();

        while ($rsEmp = mysql_fetch_assoc($result)){
            $temp = ($rsEmp['id']);
            $temp2 = ($rsEmp['nombre']);

            $miArray = array("dato"=>$temp, "dato1"=>$temp2);

            $res[] = array("dato"=>$temp, "dato1"=>$temp2);
            array_push($resultado, $miArray);
        }//Fin de while

        echo json_encode($resultado);
    }//Fin de getTurno

    public function diferirCirugia($intCausa_diferido, $intAgenda_id){
        
        $fechaActual = date("Y-m-d H:i:s");

        //Tabla de hc_agenda
        //UPDATE nombretabla SET nomcolumna=expresion WHERE condicion ;
        $result = mysql_query("UPDATE hc_agenda SET status=-50, causa_diferido_id=$intCausa_diferido, updated_at='$fechaActual' 
            WHERE id=$intAgenda_id");

        //if ($result and $sql){
        if ($result){
            return true;
        }
        else{
            return false;
        }
    }//Fin de iniciar cirugia

    public function mostrarPersonal($newRegistro){

        $result = mysql_query("SELECT personal_nombre, tipo FROM hc_agenda_personal WHERE 
            (agenda_id=$newRegistro) AND (programa=0)");
      
        $resultado = array();
        
        while ($rsEmp = mysql_fetch_assoc($result)){
            $tem = ($rsEmp['personal_nombre']);
            $temp = ($rsEmp['tipo']);

            $miArray = array("dat"=>$tem, "dato"=>$temp);

            $res[] = array("dat"=>$tem, "dato"=>$temp);
            array_push($resultado, $miArray);
        }//Fin de while
            
        echo json_encode($resultado);
    }//Fin de la funcion mostrarPersonal

    //AGREGAR PERSONAL

    public function agregarPersonal($nombre_personal, $intTipo_personal, $intStatus_personal, $intTurno_personal, $intRegistro_ID){
        
        $fechaActual = date("Y-m-d H:i:s");

        if($intTurno_personal == 7){
            //CUANDO NO EXISTE EL TURNO DEL PERSONAL
            if ($intTipo_personal == 0 && $intStatus_personal == 0){         //MEDICO QUE INICIA EL PROCEDIMIENTO

                //Para medico que inicia el procedimiento
                $sql1 = mysql_query("INSERT INTO hc_agenda_personal(agenda_id, personal_nombre, tipo, status, programa, inicia, transoperatorio, created_at) 
                VALUES($intRegistro_ID, '$nombre_personal', 0, 0, 0, 1, 1, '$fechaActual')");
            }

            elseif ($intTipo_personal == 0 && $intStatus_personal == 1) {    //CIRUJANO QUE SUPERVISA
                //Para cirujano que supervisa
                $sql2 = mysql_query("INSERT INTO hc_agenda_personal(agenda_id, personal_nombre, tipo, status, programa, inicia, transoperatorio, created_at) 
                VALUES($intRegistro_ID, '$nombre_personal', 0, 1, 0, 1, 1, '$fechaActual')");
            }

            elseif ($intTipo_personal == 1 && $intStatus_personal == 0) {    //ANESTESIOLOGO INICIAL
                //Para anestesiologo inicial
                $sql3 = mysql_query("INSERT INTO hc_agenda_personal(agenda_id, personal_nombre, tipo, status, programa, inicia, transoperatorio, created_at) 
                VALUES($intRegistro_ID, '$nombre_personal', 1, 0, 0, 1, 1, '$fechaActual')");
            }

            elseif ($intTipo_personal == 1 && $intStatus_personal == 1) {    //ANESTESIOLOGO QUE SUPERVISA
                //Para anestesiologo que supervisa
                $sql4 = mysql_query("INSERT INTO hc_agenda_personal(agenda_id, personal_nombre, tipo, status, programa, inicia, transoperatorio, created_at) 
                VALUES($intRegistro_ID, '$nombre_personal', 1, 1, 0, 1, 1, '$fechaActual')");
            }

            /*

            elseif ($intTipo_personal == 2 && $intStatus_personal == 0) {   //INSTRUMENTISTA QUE INICIA
                //Para instrumentista que inicia
                $sql5 = mysql_query("INSERT INTO hc_agenda_personal(agenda_id, personal_nombre, tipo, status, programa, inicia, transoperatorio, 
                    created_at, turno) 
                VALUES($intId_agenda, '$nombre_instrumentista', 2, 2, 0, 1, 1, '$fechaActual', $intTurnoInstrumentista)");
            }

            elseif ($intTipo_personal == 2 && $intStatus_personal == 1) {   //CIRCULANTE QUE INICIA
                //Para circulante que inicia
                $sql6 = mysql_query("INSERT INTO hc_agenda_personal(agenda_id, personal_nombre, tipo, status, programa, inicia, transoperatorio, 
                    created_at, turno) 
                VALUES($intId_agenda, '$nombre_circulante', 2, 3, 0, 1, 1, '$fechaActual', $intTurnoCirculante)");
            }
            elseif ($intTipo_personal == 3 && $intStatus_personal == 0) {   //AGREGAR AYUDANTE
                
            }

            elseif ($intTipo_personal == 3 && $intStatus_personal == 1) {   //AGREGAR SUPERVISOR
                # code...
            }

            elseif ($intTipo_personal == 3 && $intStatus_personal == 2) {   //AGREGAR INSTRUMENTISTA
                # code...
            }

            elseif ($intTipo_personal == 3 && $intStatus_personal == 3) {   //AGREGAR CIRCULANTE
                # code...
            }
            */
            
        }//FIN DE PRIMER IF

        else {
            //CUANDO SI EXISTE EL TURNO DEL PERSONAL
            
            if ($intTipo_personal == 0 && $intStatus_personal == 0){         //MEDICO QUE INICIA EL PROCEDIMIENTO

                //Para medico que inicia el procedimiento
                $sql1 = mysql_query("INSERT INTO hc_agenda_personal(agenda_id, personal_nombre, tipo, status, programa, created_at) 
                VALUES($intRegistro_ID, '$nombre_personal', 0, 0, 1, '$fechaActual')");
            }

            elseif ($intTipo_personal == 0 && $intStatus_personal == 1) {    //CIRUJANO QUE SUPERVISA
                //Para cirujano que supervisa
                $sql2 = mysql_query("INSERT INTO hc_agenda_personal(agenda_id, personal_nombre, tipo, status, programa, inicia, transoperatorio, created_at) 
                VALUES($intRegistro_ID, '$nombre_personal', 0, 1, 0, 1, 1, '$fechaActual')");
            }

            elseif ($intTipo_personal == 1 && $intStatus_personal == 0) {    //ANESTESIOLOGO INICIAL
                //Para anestesiologo inicial
                $sql3 = mysql_query("INSERT INTO hc_agenda_personal(agenda_id, personal_nombre, tipo, status, programa, inicia, transoperatorio, created_at) 
                VALUES($intRegistro_ID, '$nombre_personal', 1, 0, 0, 1, 1, '$fechaActual')");
            }

            elseif ($intTipo_personal == 1 && $intStatus_personal == 1) {    //ANESTESIOLOGO QUE SUPERVISA
                //Para anestesiologo que supervisa
                $sql4 = mysql_query("INSERT INTO hc_agenda_personal(agenda_id, personal_nombre, tipo, status, programa, inicia, transoperatorio, created_at) 
                VALUES($intRegistro_ID, '$nombre_personal', 1, 1, 0, 1, 1, '$fechaActual')");
            }

            elseif ($intTipo_personal == 2 && $intStatus_personal == 0) {   //INSTRUMENTISTA QUE INICIA
                //Para instrumentista que inicia
                $sql5 = mysql_query("INSERT INTO hc_agenda_personal(agenda_id, personal_nombre, tipo, status, programa, inicia, transoperatorio, 
                    created_at, turno) 
                VALUES($intRegistro_ID, '$nombre_personal', 2, 2, 0, 1, 1, '$fechaActual', $intTurno_personal+1)");
            }

            elseif ($intTipo_personal == 2 && $intStatus_personal == 1) {   //CIRCULANTE QUE INICIA
                //Para circulante que inicia
                $sql6 = mysql_query("INSERT INTO hc_agenda_personal(agenda_id, personal_nombre, tipo, status, programa, inicia, transoperatorio, 
                    created_at, turno) 
                VALUES($intRegistro_ID, '$nombre_personal', 2, 3, 0, 1, 1, '$fechaActual', $intTurno_personal+1)");
            }

            // *************************<< OTRO >> ********************************************

            elseif ($intTipo_personal == 3 && $intStatus_personal == 0) {   //AGREGAR AYUDANTE
                //Para ayudante
                $sql7 = mysql_query("INSERT INTO hc_agenda_personal(agenda_id, personal_nombre, tipo, status, programa, inicia, transoperatorio, 
                    created_at, turno) 
                VALUES($intRegistro_ID, '$nombre_personal', 3, 0, 0, 1, 1, '$fechaActual', $intTurno_personal+1)");
            }

            elseif ($intTipo_personal == 3 && $intStatus_personal == 1) {   //AGREGAR SUPERVISOR
                //Para supervisor
                $sql8 = mysql_query("INSERT INTO hc_agenda_personal(agenda_id, personal_nombre, tipo, status, programa, inicia, transoperatorio, 
                    created_at, turno) 
                VALUES($intRegistro_ID, '$nombre_personal', 3, 1, 0, 1, 1, '$fechaActual', $intTurno_personal+1)");
            }

            elseif ($intTipo_personal == 3 && $intStatus_personal == 2) {   //AGREGAR INSTRUMENTISTA
                //Para instrumentista
                $sql9 = mysql_query("INSERT INTO hc_agenda_personal(agenda_id, personal_nombre, tipo, status, programa, inicia, transoperatorio, 
                    created_at, turno) 
                VALUES($intRegistro_ID, '$nombre_personal', 3, 2, 0, 1, 1, '$fechaActual', $intTurno_personal+1)");
            }

            elseif ($intTipo_personal == 3 && $intStatus_personal == 3) {   //AGREGAR CIRCULANTE
                //Para circulante
                $sql10 = mysql_query("INSERT INTO hc_agenda_personal(agenda_id, personal_nombre, tipo, status, programa, inicia, transoperatorio, 
                    created_at, turno) 
                VALUES($intRegistro_ID, '$nombre_personal', 3, 3, 0, 1, 1, '$fechaActual', $intTurno_personal+1)");
            }
        }//FIN DE ELSE

        //if ($result and $sql){
        if ($sql1){
            return true;
        }
        else{
            return false;
        }
    }//Fin de agregarPersonal

    //getPersonalInicia
    public function getPersonalInicia($newRegistro){

        $result = mysql_query("SELECT id, personal_nombre FROM hc_agenda_personal WHERE 
            (agenda_id=$newRegistro) AND (inicia=1)");
      
        $resultado = array();
        
        while ($rsEmp = mysql_fetch_assoc($result)){
            $tem = ($rsEmp['id']);
            $temp = ($rsEmp['personal_nombre']);

            $miArray = array("dat"=>$tem, "dato"=>$temp);

            $res[] = array("dat"=>$tem, "dato"=>$temp);
            array_push($resultado, $miArray);
        }//Fin de while
            
        echo json_encode($resultado);
    }//Fin de la funcion getPersonalInicia

    //FINALIZAR CIRUGIA **********************************************

    public function finalizarCirugia($sSalida, $sEventosEnAnestesia, $sComplicaciones, $intClasificacion, $intEvento, $intContaminacion, 
        $intInfeccion, $intD, $intRegistro_ID, $arrayPersonal, $arrayPersonalID, $intLargoPersonal){
        
        $fechaActual = date("Y-m-d H:i:s");
        $onlyDate = date("Y-m-d");
        $sSalida = $onlyDate." ".$sSalida;

        //Tabla de hc_agenda
        //UPDATE nombretabla SET nomcolumna=expresion WHERE condicion ;
        
        //$result = mysql_query("UPDATE hc_agenda SET egreso='$sSalida', ev_adversos_anestesia='$sEventosEnAnestesia', 
        //    complicaciones='$sComplicaciones', clasificacion_qx=$intClasificacion+1, eventoqx_id=$intEvento+1, 
        //    contamincacionqx_id=$intContaminacion+1, riesgoqx_id=$intInfeccion+1, destino_px=$intD+1,
        //    status=100, updated_at='$fechaActual' WHERE id=$intRegistro_ID");

        $result = mysql_query("UPDATE hc_agenda SET egreso='$sSalida', ev_adversos_anestesia='$sEventosEnAnestesia',
            complicaciones='$sComplicaciones', clasificacionqx=$intClasificacion+1, eventoqx_id=$intEvento+1, 
            contaminacionqx_id=$intContaminacion+1, riesgoqx_id=$intInfeccion+1, destino_px=$intD+1,
            status=100, updated_at='$fechaActual' WHERE id=$intRegistro_ID");

        //$result = mysql_query("INSERT INTO hc_agenda_personal(egreso, personal_nombre, tipo, status, programa, created_at) 
        //        VALUES($intRegistro_ID, '$nombre_personal', 0, 0, 1, '$fechaActual')");

        //Tabla de hc_agenda_personal ****************************************************
        //PERSONAL QUE FINALIZA LA CIRUGIA

        if ($result){
            //for
            for ($i=0; $i<$intLargoPersonal; $i++){ 
                $personalTemporal = $arrayPersonal[$i];
                //to-int
                $intPersonalTemporal=(int)$personalTemporal;
                //echo " check status: ";
                //echo $intPersonalTemporal;
                //echo " - ";
                $personalTemporalID = $arrayPersonalID[$i];
                //to-int
                $intPersonalTemporalID = (int)$personalTemporalID;
                //echo "id: ";
                //echo $intPersonalTemporalID;
                
                $sql = mysql_query("UPDATE hc_agenda_personal SET finaliza=$intPersonalTemporal WHERE (id=$intPersonalTemporalID) AND (inicia=1);");
                //echo $intPersonalTemporal;
                //echo " <> ";
            } 
        }

        //if ($result and $sql){
        if ($result){
            return true;

        }
        else{
            return false;
        }
    }//Fin de finalizar cirugia

    /*getProcedimientosProgramada*/
    
    public function getProcedimientosProgramada($quirofano_id){

        $fechaActual = date("Y-m-d H:i:s");
        $newFecha = strtotime($fechaActual);

        //PROGRAMADAS
        $result = mysql_query("SELECT programacion, hora, id FROM hc_agenda WHERE (quirofano_id='$quirofano_id') AND (cancelada=0) AND (status=1)");

        $procedimientosProgramadas =array();
        $resultado = array();
        
        while ($rsEmp = mysql_fetch_assoc($result)){
            $tem = ($rsEmp['programacion']);
            $tem2 = ($rsEmp['hora']);

            $fusion = $tem." ".$tem2;

            $fechaTemporal = strtotime($fusion);

            $temp = ($rsEmp['id']);

            if($fechaTemporal>=$newFecha){
                $result2 = mysql_query("SELECT id, cie9mc, region, detalles, servicio_id FROM hc_agenda_procedimientos WHERE agenda_id=$temp");
                while ($rsEmp2 = mysql_fetch_assoc($result2)){
                    $temporary0 = ($rsEmp2['id']);
                    $temporary1 = ($rsEmp2['cie9mc']);
                    $temporary2 = ($rsEmp2['region']);
                    $temporary3 = ($rsEmp2['detalles']);
                    $temporary4 = ($rsEmp2['servicio_id']);
                    $miArray = array("d"=>$temp, "d0"=>$temporary0, "d1"=>$temporary1, "d2"=>$temporary2, "d3"=>$temporary3, "d4"=>$temporary4);
                    array_push($resultado, $miArray);
                }
                //array_push($procedimientosProgramadas, $resultado); 
            }
        }//Fin de while            
        echo json_encode($resultado);
    }//Fin de la funcion getProcedimientosProgramada

    // getProcedimientosDiferida
    public function getProcedimientosDiferida($quirofano_id){

        $fechaActual = date("Y-m-d H:i:s");
        $newFecha = strtotime($fechaActual);

        //DIFERIDAS
        $result = mysql_query("SELECT programacion, id FROM hc_agenda WHERE (quirofano_id='$quirofano_id') AND (cancelada=0) AND (status=-50)");

        $procedimientosProgramadas =array();
        $resultado = array();
        
        while ($rsEmp = mysql_fetch_assoc($result)){
            $tem = ($rsEmp['programacion']);
            $fechaTemporal = strtotime($tem);

            $temp = ($rsEmp['id']);
            $intTemp = (int)$temp;

            $result2 = mysql_query("SELECT id, cie9mc, region, detalles, servicio_id FROM hc_agenda_procedimientos WHERE (agenda_id=$intTemp)");
            while ($rsEmp2 = mysql_fetch_assoc($result2)){
                $temporary0 = ($rsEmp2['id']);
                $temporary1 = ($rsEmp2['cie9mc']);
                $temporary2 = ($rsEmp2['region']);
                $temporary3 = ($rsEmp2['detalles']);
                $temporary4 = ($rsEmp2['servicio_id']);
                $miArray = array("d"=>$intTemp, "d0"=>$temporary0, "d1"=>$temporary1, "d2"=>$temporary2, "d3"=>$temporary3, "d4"=>$temporary4);
                //echo "blabla"; 
                array_push($resultado, $miArray);
            }
        }//Fin de while
        //echo "agenda_id";            
        echo json_encode($resultado);
    }//Fin de la funcion getProcedimientosDiferida

    //getProcedimientosProgramadaPasada
    public function getProcedimientosProgramadaPasada($quirofano_id){

        $fechaActual = date("Y-m-d H:i:s");
        $newFecha = strtotime($fechaActual);

        //PROGRAMADAS
        $result = mysql_query("SELECT programacion, hora, id FROM hc_agenda WHERE (quirofano_id='$quirofano_id') AND (cancelada=0) AND (status=1)");

        $procedimientosProgramadas =array();
        $resultado = array();
        
        while ($rsEmp = mysql_fetch_assoc($result)){
            $tem = ($rsEmp['programacion']);
            $tem2 = ($rsEmp['hora']);

            $fusion = $tem." ".$tem2; 

            $fechaTemporal = strtotime($fusion);

            $temp = ($rsEmp['id']);
            //PROGRAMADAS PASADAS DE FECHA
            if($fechaTemporal<$newFecha){
                $result2 = mysql_query("SELECT id, cie9mc, region, detalles, servicio_id FROM hc_agenda_procedimientos WHERE agenda_id=$temp");
                while ($rsEmp2 = mysql_fetch_assoc($result2)){
                    $temporary0 = ($rsEmp2['id']);
                    $temporary1 = ($rsEmp2['cie9mc']);
                    $temporary2 = ($rsEmp2['region']);
                    $temporary3 = ($rsEmp2['detalles']);
                    $temporary4 = ($rsEmp2['servicio_id']);
                    $miArray = array("d"=>$temp, "d0"=>$temporary0, "d1"=>$temporary1, "d2"=>$temporary2, "d3"=>$temporary3, "d4"=>$temporary4);
                    array_push($resultado, $miArray);
                }
                //array_push($procedimientosProgramadas, $resultado); 
            }
        }//Fin de while            
        echo json_encode($resultado);
    }//Fin de la funcion getProcedimientosProgramadaPasada

    // getProcedimientosTransoperatorio
    public function getProcedimientosTransoperatorio($quirofano_id){

        $fechaActual = date("Y-m-d H:i:s");
        $newFecha = strtotime($fechaActual);

        //DIFERIDAS
        $result = mysql_query("SELECT programacion, id FROM hc_agenda WHERE (quirofano_id='$quirofano_id') AND (cancelada=0) AND (status=10)");

        $procedimientosProgramadas =array();
        $resultado = array();
        
        while ($rsEmp = mysql_fetch_assoc($result)){
            $tem = ($rsEmp['programacion']);
            $fechaTemporal = strtotime($tem);

            $temp = ($rsEmp['id']);
            $intTemp = (int)$temp;

            $result2 = mysql_query("SELECT id, cie9mc, region, detalles, servicio_id FROM hc_agenda_procedimientos WHERE (agenda_id=$intTemp)");
            while ($rsEmp2 = mysql_fetch_assoc($result2)){
                $temporary0 = ($rsEmp2['id']);
                $temporary1 = ($rsEmp2['cie9mc']);
                $temporary2 = ($rsEmp2['region']);
                $temporary3 = ($rsEmp2['detalles']);
                $temporary4 = ($rsEmp2['servicio_id']);
                $miArray = array("d"=>$intTemp, "d0"=>$temporary0, "d1"=>$temporary1, "d2"=>$temporary2, "d3"=>$temporary3, "d4"=>$temporary4);
                //echo "blabla"; 
                array_push($resultado, $miArray);
            }
        }//Fin de while
        //echo "agenda_id";            
        echo json_encode($resultado);
    }//Fin de la funcion getProcedimientosTransoperatorio

    // getProcedimientosRealizada
    public function getProcedimientosRealizada($quirofano_id){

        $fechaActual = date("Y-m-d H:i:s");
        $newFecha = strtotime($fechaActual);

        $result = mysql_query("SELECT programacion, id FROM hc_agenda WHERE (quirofano_id='$quirofano_id') AND (cancelada=0) AND (status=100)");

        $procedimientosProgramadas =array();
        $resultado = array();
        
        while ($rsEmp = mysql_fetch_assoc($result)){
            $tem = ($rsEmp['programacion']);
            $fechaTemporal = strtotime($tem);

            $temp = ($rsEmp['id']);
            $intTemp = (int)$temp;

            $result2 = mysql_query("SELECT id, cie9mc, region, detalles, servicio_id FROM hc_agenda_procedimientos WHERE (agenda_id=$intTemp)");
            while ($rsEmp2 = mysql_fetch_assoc($result2)){
                $temporary0 = ($rsEmp2['id']);
                $temporary1 = ($rsEmp2['cie9mc']);
                $temporary2 = ($rsEmp2['region']);
                $temporary3 = ($rsEmp2['detalles']);
                $temporary4 = ($rsEmp2['servicio_id']);
                $miArray = array("d"=>$intTemp, "d0"=>$temporary0, "d1"=>$temporary1, "d2"=>$temporary2, "d3"=>$temporary3, "d4"=>$temporary4);
                //echo "blabla"; 
                array_push($resultado, $miArray);
            }
        }//Fin de while
        //echo "agenda_id";            
        echo json_encode($resultado);
    }//Fin de la funcion getProcedimientosRealizada

    /************************************* PARA AGENDA DEL DIA, CIRUGIAS Y PROCEDIMIENTOS *********************************
    ***********************************************************************************************************************/


    public function getCirugiasProgramadasActual($quirofano){
        //Obtener fecha actual
        $f = date('Y-m-d');
        $newfecha = (string) $f;

        //Buscar con respecto a la fecha actual

        $result = mysql_query("SELECT programacion, hora, sala_id, paciente_name, diagnostico, id, registro FROM hc_agenda WHERE 
            (quirofano_id='$quirofano') AND (cancelada=0) AND (status=1) AND (programacion='$newfecha')");
        
        $resultado = array();
        
        while ($rsEmp = mysql_fetch_assoc($result)){
            $tem = ($rsEmp['programacion']);

            $fechaTemporal = strtotime($tem);

            $temp = ($rsEmp['hora']);
            $temp2 = ($rsEmp['sala_id']);
            $temp3 = ($rsEmp['paciente_name']);
            $temp4 = ($rsEmp['diagnostico']);
            $temp5 = ($rsEmp['id']);
            $temp6 = ($rsEmp['registro']);

            $result2 = mysql_query("SELECT personal_nombre FROM hc_agenda_personal WHERE (programa=1) AND (agenda_id=$temp5)");
            $nombreDelDoctor = mysql_fetch_assoc($result2);
            $temporary2 = ($nombreDelDoctor['personal_nombre']);

            $miArray = array("dat"=>$tem, "dato"=>$temp, "dato1"=>$temp2, "dato2"=>$temp3, "dato3"=>$temp4, "dato4"=>$temp5, "dato5"=>$temp6, "dato6"=>$temporary2);

            $res[] = array("dat"=>$tem, "dato"=>$temp, "dato1"=>$temp2, "dato2"=>$temp3, "dato3"=>$temp4, "dato4"=>$temp5, "dato5"=>$temp6, "dato6"=>$temporary2);
            array_push($resultado, $miArray);
        }//Fin de while
            
        echo json_encode($resultado);
    }//Fin de la funcion getCirugiasProgramadasActual

    //*getProcedimientosProgramadaActual*/
    
    public function getProcedimientosProgramadasActual($quirofano_id){
        //Obtener fecha actual
        $f = date('Y-m-d');
        $newfecha = (string) $f;

        //PROGRAMADAS DEL DIA ACTUAL
        $result = mysql_query("SELECT programacion, id FROM hc_agenda WHERE (quirofano_id='$quirofano_id') AND (cancelada=0) AND (status=1)
            AND (programacion='$newfecha')");

        $resultado = array();
        
        while ($rsEmp = mysql_fetch_assoc($result)){
            $tem = ($rsEmp['programacion']);
            $fechaTemporal = strtotime($tem);

            $temp = ($rsEmp['id']);

            $result2 = mysql_query("SELECT id, cie9mc, region, detalles, servicio_id FROM hc_agenda_procedimientos WHERE agenda_id=$temp");
            while ($rsEmp2 = mysql_fetch_assoc($result2)){
                $temporary0 = ($rsEmp2['id']);
                $temporary1 = ($rsEmp2['cie9mc']);
                $temporary2 = ($rsEmp2['region']);
                $temporary3 = ($rsEmp2['detalles']);
                $temporary4 = ($rsEmp2['servicio_id']);
                $miArray = array("d"=>$temp, "d0"=>$temporary0, "d1"=>$temporary1, "d2"=>$temporary2, "d3"=>$temporary3, "d4"=>$temporary4);
                array_push($resultado, $miArray);
            }//Fin de while
        }//Fin de while            
        echo json_encode($resultado);
    }//Fin de la funcion getProcedimientosProgramadasActual

    //DIFERIDAS ACTUALES

    public function getCirugiasDiferidasActual($quirofano){
        //Obtener fecha actual
        $f = date('Y-m-d');
        $newfecha = (string) $f;

        //Buscar con respecto a la fecha actual

        $result = mysql_query("SELECT programacion, hora, sala_id, paciente_name, diagnostico, id, registro FROM hc_agenda WHERE 
            (quirofano_id='$quirofano') AND (cancelada=0) AND (status=-50) AND (programacion='$newfecha')");
        
        $resultado = array();
        
        while ($rsEmp = mysql_fetch_assoc($result)){
            $tem = ($rsEmp['programacion']);

            $fechaTemporal = strtotime($tem);

            $temp = ($rsEmp['hora']);
            $temp2 = ($rsEmp['sala_id']);
            $temp3 = ($rsEmp['paciente_name']);
            $temp4 = ($rsEmp['diagnostico']);
            $temp5 = ($rsEmp['id']);
            $temp6 = ($rsEmp['registro']);

            $result2 = mysql_query("SELECT personal_nombre FROM hc_agenda_personal WHERE (programa=1) AND (agenda_id=$temp5)");
            $nombreDelDoctor = mysql_fetch_assoc($result2);
            $temporary2 = ($nombreDelDoctor['personal_nombre']);

            $miArray = array("dat"=>$tem, "dato"=>$temp, "dato1"=>$temp2, "dato2"=>$temp3, "dato3"=>$temp4, "dato4"=>$temp5, "dato5"=>$temp6, "dato6"=>$temporary2);

            $res[] = array("dat"=>$tem, "dato"=>$temp, "dato1"=>$temp2, "dato2"=>$temp3, "dato3"=>$temp4, "dato4"=>$temp5, "dato5"=>$temp6, "dato6"=>$temporary2);
            array_push($resultado, $miArray);
        }//Fin de while
            
        echo json_encode($resultado);
    }//Fin de la funcion getCirugiasDiferidasActual

    //*getProcedimientosDiferidasActual*/
    
    public function getProcedimientosDiferidasActual($quirofano_id){
        //Obtener fecha actual
        $f = date('Y-m-d');
        $newfecha = (string) $f;

        //DIFERIDAS DEL DIA ACTUAL
        $result = mysql_query("SELECT programacion, id FROM hc_agenda WHERE (quirofano_id='$quirofano_id') AND (cancelada=0) AND (status=-50)
            AND (programacion='$newfecha')");

        $resultado = array();
        
        while ($rsEmp = mysql_fetch_assoc($result)){
            $tem = ($rsEmp['programacion']);
            $fechaTemporal = strtotime($tem);

            $temp = ($rsEmp['id']);

            $result2 = mysql_query("SELECT id, cie9mc, region, detalles, servicio_id FROM hc_agenda_procedimientos WHERE agenda_id=$temp");
            while ($rsEmp2 = mysql_fetch_assoc($result2)){
                $temporary0 = ($rsEmp2['id']);
                $temporary1 = ($rsEmp2['cie9mc']);
                $temporary2 = ($rsEmp2['region']);
                $temporary3 = ($rsEmp2['detalles']);
                $temporary4 = ($rsEmp2['servicio_id']);
                $miArray = array("d"=>$temp, "d0"=>$temporary0, "d1"=>$temporary1, "d2"=>$temporary2, "d3"=>$temporary3, "d4"=>$temporary4);
                array_push($resultado, $miArray);
            }//Fin de while
        }//Fin de while            
        echo json_encode($resultado);
    }//Fin de la funcion getProcedimientosDiferidasActual

    //TRANSOPERATORIO ACTUALES

    public function getCirugiasTransoperatorioActual($quirofano){
        //Obtener fecha actual
        $f = date('Y-m-d');
        $newfecha = (string) $f;

        //Buscar con respecto a la fecha actual

        $result = mysql_query("SELECT programacion, hora, sala_id, paciente_name, diagnostico, id, registro FROM hc_agenda WHERE 
            (quirofano_id='$quirofano') AND (cancelada=0) AND (status=10) AND (programacion='$newfecha')");
        
        $resultado = array();
        
        while ($rsEmp = mysql_fetch_assoc($result)){
            $tem = ($rsEmp['programacion']);

            $fechaTemporal = strtotime($tem);

            $temp = ($rsEmp['hora']);
            $temp2 = ($rsEmp['sala_id']);
            $temp3 = ($rsEmp['paciente_name']);
            $temp4 = ($rsEmp['diagnostico']);
            $temp5 = ($rsEmp['id']);
            $temp6 = ($rsEmp['registro']);

            $result2 = mysql_query("SELECT personal_nombre FROM hc_agenda_personal WHERE (programa=1) AND (agenda_id=$temp5)");
            $nombreDelDoctor = mysql_fetch_assoc($result2);
            $temporary2 = ($nombreDelDoctor['personal_nombre']);

            $miArray = array("dat"=>$tem, "dato"=>$temp, "dato1"=>$temp2, "dato2"=>$temp3, "dato3"=>$temp4, "dato4"=>$temp5, "dato5"=>$temp6, "dato6"=>$temporary2);

            $res[] = array("dat"=>$tem, "dato"=>$temp, "dato1"=>$temp2, "dato2"=>$temp3, "dato3"=>$temp4, "dato4"=>$temp5, "dato5"=>$temp6, "dato6"=>$temporary2);
            array_push($resultado, $miArray);
        }//Fin de while
            
        echo json_encode($resultado);
    }//Fin de la funcion getCirugiasTransoperatorioActual

    //*getProcedimientosTransoperatorioActual*/
    
    public function getProcedimientosTransoperatorioActual($quirofano_id){
        //Obtener fecha actual
        $f = date('Y-m-d');
        $newfecha = (string) $f;

        //EN TRANSOPERATORIO DEL DIA ACTUAL
        $result = mysql_query("SELECT programacion, id FROM hc_agenda WHERE (quirofano_id='$quirofano_id') AND (cancelada=0) AND (status=10)
            AND (programacion='$newfecha')");

        $resultado = array();
        
        while ($rsEmp = mysql_fetch_assoc($result)){
            $tem = ($rsEmp['programacion']);
            $fechaTemporal = strtotime($tem);

            $temp = ($rsEmp['id']);

            $result2 = mysql_query("SELECT id, cie9mc, region, detalles, servicio_id FROM hc_agenda_procedimientos WHERE agenda_id=$temp");
            while ($rsEmp2 = mysql_fetch_assoc($result2)){
                $temporary0 = ($rsEmp2['id']);
                $temporary1 = ($rsEmp2['cie9mc']);
                $temporary2 = ($rsEmp2['region']);
                $temporary3 = ($rsEmp2['detalles']);
                $temporary4 = ($rsEmp2['servicio_id']);
                $miArray = array("d"=>$temp, "d0"=>$temporary0, "d1"=>$temporary1, "d2"=>$temporary2, "d3"=>$temporary3, "d4"=>$temporary4);
                array_push($resultado, $miArray);
            }//Fin de while
        }//Fin de while            
        echo json_encode($resultado);
    }//Fin de la funcion getProcedimientosTransoperatorioActual

    //REALIZADAS ACTUALES

    public function getCirugiasRealizadasActual($quirofano){
        //Obtener fecha actual
        $f = date('Y-m-d');
        $newfecha = (string) $f;

        //Buscar con respecto a la fecha actual

        $result = mysql_query("SELECT programacion, hora, sala_id, paciente_name, diagnostico, id, registro FROM hc_agenda WHERE 
            (quirofano_id='$quirofano') AND (cancelada=0) AND (status=100) AND (programacion='$newfecha')");
        
        $resultado = array();
        
        while ($rsEmp = mysql_fetch_assoc($result)){
            $tem = ($rsEmp['programacion']);

            $fechaTemporal = strtotime($tem);

            $temp = ($rsEmp['hora']);
            $temp2 = ($rsEmp['sala_id']);
            $temp3 = ($rsEmp['paciente_name']);
            $temp4 = ($rsEmp['diagnostico']);
            $temp5 = ($rsEmp['id']);
            $temp6 = ($rsEmp['registro']);

            $result2 = mysql_query("SELECT personal_nombre FROM hc_agenda_personal WHERE (programa=1) AND (agenda_id=$temp5)");
            $nombreDelDoctor = mysql_fetch_assoc($result2);
            $temporary2 = ($nombreDelDoctor['personal_nombre']);

            $miArray = array("dat"=>$tem, "dato"=>$temp, "dato1"=>$temp2, "dato2"=>$temp3, "dato3"=>$temp4, "dato4"=>$temp5, "dato5"=>$temp6, "dato6"=>$temporary2);

            $res[] = array("dat"=>$tem, "dato"=>$temp, "dato1"=>$temp2, "dato2"=>$temp3, "dato3"=>$temp4, "dato4"=>$temp5, "dato5"=>$temp6, "dato6"=>$temporary2);
            array_push($resultado, $miArray);
        }//Fin de while
            
        echo json_encode($resultado);
    }//Fin de la funcion getCirugiasRealizadasActual

    public function quitarTildes($cadena){
        $no_permitidas = array("á","é","í","ó","ú","Á","É","Í","Ó","Ú","ñ","À","Ã","Ì","Ò","Ù","Ã™","Ã ","Ã¨","Ã¬","Ã²","Ã¹","ç","Ç","Ã¢","ê","Ã®","Ã´","Ã»","Ã‚","ÃŠ","ÃŽ","Ã”","Ã›","ü","Ã¶","Ã–","Ã¯","Ã¤","«","Ò","Ã","Ã„","Ã‹");
        $permitidas = array("a","e","i","o","u","A","E","I","O","U","n","N","A","E","I","O","U","a","e","i","o","u","c","C","a","e","i","o","u","A","E","I","O","U","u","o","O","i","a","e","U","I","A","E");
        $texto = str_replace($no_permitidas, $permitidas ,$cadena);
        return $texto;
    }

    //*getProcedimientosRealizadasActual*/
    
    public function getProcedimientosRealizadasActual($quirofano_id){
        //Obtener fecha actual
        $f = date('Y-m-d');
        $newfecha = (string) $f;

        //REALIZADAS DEL DIA ACTUAL
        $result = mysql_query("SELECT programacion, id FROM hc_agenda WHERE (quirofano_id='$quirofano_id') AND (cancelada=0) AND (status=100)
            AND (programacion='$newfecha')");

        $resultado = array();
        
        while ($rsEmp = mysql_fetch_assoc($result)){
            $tem = ($rsEmp['programacion']);
            $fechaTemporal = strtotime($tem);

            $temp = ($rsEmp['id']);

            $result2 = mysql_query("SELECT id, cie9mc, region, detalles, servicio_id FROM hc_agenda_procedimientos WHERE agenda_id=$temp");
            while ($rsEmp2 = mysql_fetch_assoc($result2)){
                $temporary0 = ($rsEmp2['id']);
                $temporary1 = ($rsEmp2['cie9mc']);
                $temporary2 = ($rsEmp2['region']);
                $temporary3 = ($rsEmp2['detalles']);
                $temporary4 = ($rsEmp2['servicio_id']);
                $miArray = array("d"=>$temp, "d0"=>$temporary0, "d1"=>$temporary1, "d2"=>$temporary2, "d3"=>$temporary3, "d4"=>$temporary4);
                array_push($resultado, $miArray);
            }//Fin de while
        }//Fin de while            
        echo json_encode($resultado);
    }//Fin de la funcion getProcedimientosRealizadasActual


    public function getServicios($var){
        $newVar = (int)$var;
        $result = mysql_query("SELECT id, nombre FROM siga_especialidad WHERE activo=1");
        $resultado = array();

        while ($rsEmp = mysql_fetch_assoc($result)){
            $tem = ($rsEmp['id']);
            $temp = ($rsEmp['nombre']);
            $cadena = utf8_encode($temp);

            $no_permitidas = array("á","é","í","ó","ú","Á","É","Í","Ó","Ú","ñ","Ñ");
            $permitidas = array("a","e","i","o","u","A","E","I","O","U","n","N");
            $texto = str_replace($no_permitidas, $permitidas ,$cadena);

            //$newTemp = quitarTildes($temp);

            $miArray = array("dat"=>$tem, "dato"=>$texto);

            $res[] = array("dat"=>$tem, "dato"=>$texto);
            array_push($resultado, $miArray);
        }//Fin de while

        echo json_encode($resultado);
    }//Fin de getServicios

    public function getCausaDiferido($var){
        $newVar = (int)$var;
        $result = mysql_query("SELECT id, nombre FROM siga_causa_diferido WHERE activo=1");
        $resultado = array();

        while ($rsEmp = mysql_fetch_assoc($result)){
            $tem = ($rsEmp['id']);
            $temp = ($rsEmp['nombre']);
            $cadena = utf8_encode($temp);

            $no_permitidas = array("á","é","í","ó","ú","Á","É","Í","Ó","Ú","ñ","Ñ");
            $permitidas = array("a","e","i","o","u","A","E","I","O","U","n","N");
            $texto = str_replace($no_permitidas, $permitidas ,$cadena);

            //$newTemp = quitarTildes($temp);

            $miArray = array("dat"=>$tem, "dato"=>$texto);

            $res[] = array("dat"=>$tem, "dato"=>$texto);
            array_push($resultado, $miArray);
        }//Fin de while

        echo json_encode($resultado);
    }//Fin de getCausaDiferido

    public function getDatosCirugiaProgramada($id_cirugia){

        //TRAER INFORMACION DE LA CIRUGIA PROGRAMADA
        $result = mysql_query("SELECT programacion, hora, registro, paciente_name, edad, genero, procedencia, diagnostico,
            riesgo_qx_pre, req_insumos, req_anestesico, req_hemoderiv, req_laboratorio, requerimiento,
            sala_id, tiempo_est, tipo_proc_id, servicio, atencion_id, protocolo, reintervencion, quirofano_id FROM hc_agenda 
            WHERE id=$id_cirugia");

        //TRAER INFORMACION DEL NOMBRE DEL DOCTOR QUE PROGRAMA LA CIRUGIA
        $sql01 = mysql_query("SELECT personal_nombre FROM hc_agenda_personal WHERE agenda_id=$id_cirugia");

        $resultado = array();
        $rsEmp = mysql_fetch_assoc($result);
        $rsEmp2 = mysql_fetch_assoc($sql01);

        $temporal0 = ($rsEmp['sala_id']);              //sala_id
        $temporal1 = ($rsEmp['programacion']);         //programacion
        $temporal2 = ($rsEmp['hora']);                 //hora
        $temporal3 = ($rsEmp['tiempo_est']);           //tiempo_est
        
        //**********************************************************************************
        $temporal4 = ($rsEmp['tipo_proc_id']);         //tipo_proc_id
        $sql02 = mysql_query("SELECT nombre FROM siga_procedimiento WHERE id=$temporal4");
        $rsEmp3 = mysql_fetch_assoc($sql02);
        $sTemporal4 = ($rsEmp3['nombre']);
        //**********************************************************************************

        $temporal5 = ($rsEmp['registro']);             //registro
        $temporal6 = ($rsEmp['paciente_name']);        //paciente_name
        $temporal7 = ($rsEmp['edad']);                 //edad
        $temporal8 = ($rsEmp['genero']);               //genero
        $temporal9 = ($rsEmp['procedencia']);          //procedencia

        //**********************************************************************************
        $temporal10 = ($rsEmp['servicio']);            //servicio
        $sql03 = mysql_query("SELECT nombre FROM siga_especialidad WHERE id=$temporal10");
        $rsEmp4 = mysql_fetch_assoc($sql03);
        $sTemporal10 = ($rsEmp4['nombre']);
        //**********************************************************************************
        
        $temporal11 = ($rsEmp['diagnostico']);         //diagnostico
        $temporal12 = ($rsEmp['protocolo']);           //protocolo
        $temporal13 = ($rsEmp['reintervencion']);      //reintervencion
        $temporal14 = ($rsEmp2['personal_nombre']);     //personal_nombre

        //**********************************************************************************
        $temporal15 = ($rsEmp['atencion_id']);         //atencion_id        
        $sql04 = mysql_query("SELECT nombre FROM siga_atencion WHERE id=$temporal15");
        $rsEmp5 = mysql_fetch_assoc($sql04);
        $sTemporal15 = ($rsEmp5['nombre']);
        //**********************************************************************************

        $temporal16 = ($rsEmp['riesgo_qx_pre']);       //riesgo_qx_pre
        $temporal17 = ($rsEmp['req_insumos']);         //req_insumos
        $temporal18 = ($rsEmp['req_anestesico']);      //req_anestesico
        $temporal19 = ($rsEmp['req_hemoderiv']);       //req_hemoderiv
        $temporal20 = ($rsEmp['req_laboratorio']);     //req_laboratorio
        $temporal21 = ($rsEmp['requerimiento']);       //requerimiento

        //**********************************************************************************
        $temporal22 = ($rsEmp['quirofano_id']);       //quirofano_id
        $sql05 = mysql_query("SELECT nombre FROM siga_quirofano WHERE id=$temporal22");
        $rsEmp6 = mysql_fetch_assoc($sql05);
        $sTemporal22 = ($rsEmp6['nombre']);
        //**********************************************************************************


        $miArray = array("dato0"=>$temporal0, "dato1"=>$temporal1, "dato2"=>$temporal2, "dato3"=>$temporal3, "dato4"=>$sTemporal4, 
            "dato5"=>$temporal5, "dato6"=>$temporal6, "dato7"=>$temporal7, "dato8"=>$temporal8, "dato9"=>$temporal9, "dato10"=>$sTemporal10,
            "dato11"=>$temporal11, "dato12"=>$temporal12, "dato13"=>$temporal13, "dato14"=>$temporal14, "dato15"=>$sTemporal15, 
            "dato16"=>$temporal16, "dato17"=>$temporal17, "dato18"=>$temporal18, "dato19"=>$temporal19, "dato20"=>$temporal20, 
            "dato21"=>$temporal21, "dato22"=>$sTemporal22);
        array_push($resultado, $miArray);

        echo json_encode($resultado);

    }//Fin de getDatosCirugiaProgramada

    //Obtener procedimientos para mostrar en la seccion de modificar cirugia
    public function getProcedimientosCirugiaProgramada($id_cirugia){

        $resultado = array();

        $result2 = mysql_query("SELECT id, cie9mc, region, detalles, servicio_id FROM hc_agenda_procedimientos WHERE agenda_id=$id_cirugia");
        while ($rsEmp2 = mysql_fetch_assoc($result2)){
            $temporary0 = ($rsEmp2['id']);
            $temporary1 = ($rsEmp2['cie9mc']);
            $temporary2 = ($rsEmp2['region']);
            $temporary3 = ($rsEmp2['detalles']);

            //********************************************
            $temporary4 = ($rsEmp2['servicio_id']);
            $result3 = mysql_query("SELECT nombre FROM siga_especialidad WHERE id=$temporary4");
            $rsEmp3 = mysql_fetch_assoc($result3);
            $sTemporary4 = ($rsEmp3['nombre']);
            //**********************++++++++++++++++++++**

            $miArray = array("d0"=>$temporary0, "d1"=>$temporary1, "d2"=>$temporary2, "d3"=>$temporary3, "d4"=>$sTemporary4);
            array_push($resultado, $miArray);
        }//Fin de while

        echo json_encode($resultado);

    }//Fin de getProcedimientosCirugiaProgramada

    //MODIFICAR CIRUGIA - RECIBE PARAMETRO EL ID DEL REGISTRO QUE VA A MODIFICAR
    public function modificarCirugia($date, $hora, $registro, $paciente, $edad, $genero, $procedencia, $diagnostico, $medico, $riesgoQuirurgico,
        $insumos, $requerimientosAnestesiologia, $hemoderivados, $requisitosLaboratorio, $otrasNecesidades,$newSala, 
        $newDuracion,$newProgramacion, $newServicio, $newAtencion, $newP, $newR, $newQuirofano, $intRegistro_ID){
        //$newSala, $newDuracion,$newProgramacion, $newServicio, $newAtencion, $newP, $newR
        
        $mesesEnSegundos = 3600*24*28;

        $fechaActual = date("Y-m-d H:i:s");
        $fechaApp = $date." ".$hora;

        $newFechaActual = strtotime($fechaActual);
        $newFechaApp = strtotime($fechaApp);
        $fechaActualMasMes = $newFechaActual+$mesesEnSegundos;

        $fechaseleccMax = $date;                  //el dia actual de la programación
        $fechaseleccMin = date("Y-m-d",strtotime($fechaseleccMax.' -1 day')); //Un dia antes por si la cirugía de un día antes continua actualmente

        //$fechaActual = date("Y-m-d H:i:s");

        if ($newFechaApp > $newFechaActual) {
            if ($newFechaApp < $fechaActualMasMes){

                $control = $this->emHoras2($fechaseleccMax, $fechaseleccMin, $hora, $newQuirofano, $newSala, $newDuracion, $newQuirofano);

                if ($control != "ok") {     //si es diferente a "ok" quiere decir que se empalma con alguna programación anterior
                    //*********CIRUGIA EMPALMADA CON "$control"
                    //$text = 'se empalma con la cirugia: '.$control;
                    $text = "w".$control;
                    $resultado[]=array("resultado"=>"$text");
                    echo json_encode($resultado);                
                }

                else {   // si es "ok" lo grabamos como debe ser
                    $test = mysql_query("SELECT status FROM hc_agenda WHERE id=$intRegistro_ID");
                    $contenido = mysql_fetch_assoc($test);
                    $statusCirugia = $contenido['status'];

                    if($statusCirugia == -50){
                        $changeStatus = mysql_query("UPDATE hc_agenda SET status=1 WHERE id = $intRegistro_ID");
                    }

                    $sql0  = mysql_query("UPDATE hc_agenda SET programacion = '$date', hora = '$hora', registro = '$registro', 
                        paciente_name = '$paciente', edad = '$edad', genero = '$genero', procedencia = '$procedencia', diagnostico = '$diagnostico',
                        riesgo_qx_pre = '$riesgoQuirurgico', req_insumos = '$insumos', req_anestesico = '$requerimientosAnestesiologia', 
                        req_hemoderiv = '$hemoderivados', req_laboratorio = '$requisitosLaboratorio', requerimiento = '$otrasNecesidades',
                        sala_id = $newSala, tiempo_est = '$newDuracion', tipo_proc_id = $newProgramacion+1, servicio = $newServicio, 
                        atencion_id = $newAtencion+1, protocolo = $newP, reintervencion = $newR, quirofano_id = $newQuirofano, 
                        fechaestado = '$date', horaestado= '$hora' WHERE id=$intRegistro_ID"); //QUITE created_at
                    $sql1 = mysql_query("UPDATE hc_agenda_personal SET personal_nombre = '$medico' WHERE (agenda_id=$intRegistro_ID) AND
                        programa = 1");

                    if ($sql0) {
                        //return true;
                        $resultado2[]=array("resultado"=>"ok");
                        echo json_encode($resultado2);
                    }

                    else {
                        //return false;
                        $resultado2[]=array("resultado"=>"error");
                        echo json_encode($resultado2);
                    }
                }//FIN DE ELSE QUE MODIFICA LA CIRUGIA NORMALMENTE
            }//FIN DE VALIDADOR DE FECHA PARA QUE NO SE PASE DE 1 MES

            else{
                $resultado2[]=array("resultado"=>"error2");   //ERROR2: la fecha programada excede el limite mayor(1 mes)
                echo json_encode($resultado2);
            }
        }//FIN DE VALIDADOR DE FECHA MAYOR A LA DEL DIA DE HOY

        else{
            $resultado2[]=array("resultado"=>"error1");   //ERROR1: la fecha programada es menor que la actual
            echo json_encode($resultado2);
        }
    }//Fin de modificarCirugia

    //MODIFICAR LOS PROCEDIMIENTOS DE LA CIRUGIA TAMBIEN MODIFICADA
    public function enviarProcedimientosModificados($numRegistroInt, $largoProcedimientos, $arrayServicio, $arrayRegion, $arrayProcedimiento, $arrayDetalles){
        /*Orden: servicio, region, procedimientos, detalles*/
        $fechaActual = date("Y-m-d H:i:s");

        $eliminar = mysql_query("DELETE FROM hc_agenda_procedimientos WHERE agenda_id=$numRegistroInt");

        for ($i=0; $i<$largoProcedimientos; $i++){ 
            $region = $arrayRegion[$i];
            $intRegion = (int)$region;
            $servicio = $arrayServicio[$i];
            $intServicio = (int)$servicio;
            $stringProcedimiento = $arrayProcedimiento[$i];
            $stringDetalles = $arrayDetalles[$i];
            //$sql = mysql_query("INSERT INTO hc_agenda_procedimientos(servicio_id, region, cie9mc, detalles) 
            //VALUES($arrayServicio[$i], $arrayRegion[$i], '$arrayProcedimiento[$i]', '$arrayDetalles[$i]'");
            $sql = mysql_query("INSERT INTO hc_agenda_procedimientos(servicio_id, region, cie9mc, detalles, agenda_id, created_at) 
            VALUES($intServicio, $intRegion+1, '$stringProcedimiento', '$stringDetalles', $numRegistroInt, '$fechaActual')");
        } 

        if ($sql){
            return true;
        }
        else{
            return false;
        }
    }// Fin de enviarProcedimientosModificados

    //********************EMPAALMES ********************************************************************************************
    //**************************************************************************************************************************

    //public function emHoras($fechaselecc,$horapropuesta,$Quiid,$salaselecc,$tiempo_est,$identificacion)

    public function emHoras($fechaseleccMax, $fechaseleccMin, $hora, $newQuirofano, $newSala, $newDuracion, $identificacion)
    {
        //Buscamos las posibles cirugias programadas que se pueden empalmar
        $control = NULL;
        /*CODIGO INICIAL +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        $estados['trans'] = 10;
        $estados['progr'] = 1;                         
        $agenda = AgendaQuery::create()                //filtramos por programaciones del dia actual y un dia anterior
            ->filterByquirofanoid($Quiid)              //en caso de que continue la cirugia al dia de hoy 
            ->filterByfechaestado($fechaselecc)
            ->filterBysalaid($salaselecc)
            ->filterByStatus($estados)
            ->filterByCancelada(false)
            ->find();
        * FIN DE CODIGO INICIAL ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++*/

        //datos de la cirugia que se trata programar   
        $tiempo1 = strtotime("".$fechaseleccMax."".$hora);           
        $hora1 = $this->darhora($newDuracion);    
        $tiempo2 = strtotime("".$fechaseleccMax."".$hora."".$hora1); //final
       
        $sql = mysql_query("SELECT fechaestado, horaestado, tiempo_est, id FROM hc_agenda WHERE 
            (quirofano_id='$newQuirofano') AND (cancelada=0) AND (status=1 OR status=10) AND (sala_id='$newSala') AND
            (fechaestado='$fechaseleccMax' OR fechaestado='$fechaseleccMin')");

            $resultado = array();
            
            while ($rsEmp = mysql_fetch_assoc($sql)){
                $temporary0 = ($rsEmp['fechaestado']);
                $temporary1 = ($rsEmp['horaestado']);
                $temporary2 = ($rsEmp['tiempo_est']);
                $tempprary3 = ($rsEmp['id']);
                //$tempprary4 = ($rsEmp['paciente_name']);
                //$temporary4 = ($rsEmp['diagnostico']);

                //$fusion = $tem." ".$temp;
                //$fechaTemporal = strtotime($fusion);

                /// ******* DATOS DE LA CIRUGIA YA EXISTENTE
                //$tiempo3 = strtotime("".$agendas->getFechaestado()."".$agendas->getHoraestado());  //tiempo inicial de la cirugía a comparar
                $tiempo3 = strtotime("".$temporary0."".$temporary1);

                //$hora2 = $this->darhora($agendas->getTiempoest());
                $hora2 = $this->darhora($temporary2);

                //$tiempo4 = strtotime("".$agendas->getFechaestado()."".$agendas->getHoraestado()."".$hora2);  
                $tiempo4 = strtotime("".$temporary0."".$temporary1."".$hora2);
                /// ****** DATOS DE LA CIRUGIA YA EXISTENTE

                //>>>COMPRARAMOS SI SE EMPALMAN
                if($tiempo1 == $tiempo3 and $tiempo2 == $tiempo4)
                {
                    //$control = $agendas->getId();
                    $control = $temporary3;
                }
                elseif(($tiempo3 < $tiempo1 && $tiempo1 < $tiempo4) or ($tiempo3 < $tiempo2 && $tiempo2 < $tiempo4))
                {                                                  
                    //$control = $agendas->getId();
                    $control = $temporary3;
                }
                elseif(($tiempo1 < $tiempo3 && $tiempo3 < $tiempo2) or ($tiempo1 < $tiempo4 && $tiempo4 < $tiempo2)) {
                    //$control = $agendas->getId();
                    $control = $temporary3;
                }
                //>>>COMPARAMOS SI SE EMPALMAN
            }
            return $control;

        /*
        foreach($agenda as $agendas):
            if($identificacion != $agendas->getId()) //verificamos si no es la misma :P
              {       
                  ///       datos de la cirugia ya existente
                  $tiempo3 = strtotime("".$agendas->getFechaestado()."".$agendas->getHoraestado());  //tiempo inicial de la cirugía a comparar
                  $hora2 = $this->darhora($agendas->getTiempoest());
                  $tiempo4 = strtotime("".$agendas->getFechaestado()."".$agendas->getHoraestado()."".$hora2);  
                  /// datos de la cirugia ya eistente

                  //comparamos si se empalman
                  if($tiempo1 == $tiempo3 and $tiempo2 == $tiempo4)
                  {
                    $control = $agendas->getId();
                  }
                  elseif(($tiempo3 < $tiempo1 && $tiempo1 < $tiempo4) or ($tiempo3 < $tiempo2 && $tiempo2 < $tiempo4))
                  {                                                  
                    $control = $agendas->getId();
                  }elseif(($tiempo1 < $tiempo3 && $tiempo3 < $tiempo2) or ($tiempo1 < $tiempo4 && $tiempo4 < $tiempo2)) {

                    $control = $agendas->getId();
                  }
                  //comparamos si se empalman   
             }
        endforeach;
        return $control;
        */
    }//fin de emHoras

    public function darhora($hor){
        $hora = date('H', strtotime($hor));           //sumamos la horas que tardaran en finalizar la cirugía  
        $minuto = date('i', strtotime($hor));
        $segundo = date('s', strtotime($hor));
        return "+".$hora." hour +". $minuto ." minutes +".$segundo ." second";
    } //fin de darhora

    //******************* FIN DE EMPALMES *************************************************************************************
    //*************************************************************************************************************************

    public function TT($v0, $v1,$v2,$v3,$v4,$v5,$v6){
        $control = $this->emHoras2($v0, $v1,$v2,$v3,$v4,$v5,$v6);
        //$fusion = $status." ".$world;
        if($control != "ok"){
            echo "La fecha se empalma con la cirugia: ".$control;
        }
        else{
            echo "La cirugia se programara con exito";
        }
    }

    public function sayWorld(){
        $hi = "mundo";
        return $hi;
    }

    /* +++++++++++++++++++++++++++++ PRUEBA WEB ++++++++++++++++++++++++++++++++++ */

    public function emHoras2($fechaseleccMax, $fechaseleccMin, $hora, $newQuirofano, $newSala, $newDuracion, $identificacion)
    {     
        //Buscamos las posibles cirugias programadas que se pueden empalmar
        $control = "ok";
        
        //datos de la cirugia que se trata programar   
        $tiempo1 = strtotime("".$fechaseleccMax."".$hora);           
        $hora1 = $this->darhora($newDuracion);    
        $tiempo2 = strtotime("".$fechaseleccMax."".$hora."".$hora1); //final
       
        $sql = mysql_query("SELECT fechaestado, horaestado, tiempo_est, id FROM hc_agenda WHERE 
            (quirofano_id='$newQuirofano') AND (cancelada=0) AND (status=1 OR status=10) AND (sala_id='$newSala') AND
            (fechaestado='$fechaseleccMax' OR fechaestado='$fechaseleccMin')");
            
        while ($rsEmp = mysql_fetch_assoc($sql)){
            $temporary0 = ($rsEmp['fechaestado']);
            $temporary1 = ($rsEmp['horaestado']);
            $temporary2 = ($rsEmp['tiempo_est']);
            $temporary3 = ($rsEmp['id']);

            $tiempo3 = strtotime("".$temporary0."".$temporary1);
            $hora2 = $this->darhora($temporary2);
            $tiempo4 = strtotime("".$temporary0."".$temporary1."".$hora2);
    
            if($tiempo1 == $tiempo3 and $tiempo2 == $tiempo4) {
                $control = $temporary3;
            }
            elseif(($tiempo3 < $tiempo1 && $tiempo1 < $tiempo4) or ($tiempo3 < $tiempo2 && $tiempo2 < $tiempo4)) {                                                  
                $control = $temporary3;
            }
            elseif(($tiempo1 < $tiempo3 && $tiempo3 < $tiempo2) or ($tiempo1 < $tiempo4 && $tiempo4 < $tiempo2)) {
                $control = $temporary3;
            }
        }//Fin de while
        return $control;
    }//fin de emHoras2

    /* ++++++++++++++++++++++++++ FIN DE PRUEBA WEB ++++++++++++++++++++++++++++++ */

    //OBTENER CIRUGIAS CANCELADAS
    public function getCirugiasCanceladas($quirofano_id){

        //CANCELADAS
        $result = mysql_query("SELECT programacion, hora, sala_id, paciente_name, diagnostico, id, registro FROM hc_agenda WHERE 
            (quirofano_id='$quirofano_id') AND (cancelada=1) AND (status = 1 OR status=-50)");
      
        $resultado = array();
        
        while ($rsEmp = mysql_fetch_assoc($result)){
            $tem = ($rsEmp['programacion']);
            $temp = ($rsEmp['hora']);
            $temp2 = ($rsEmp['sala_id']);
            $temp3 = ($rsEmp['paciente_name']);
            $temp4 = ($rsEmp['diagnostico']);
            $temp5 = ($rsEmp['id']);
            $temp6 = ($rsEmp['registro']);

            //$miArray = array("dat"=>$tem, "dato"=>$temp, "dato1"=>$temp2, "dato2"=>$temp3, "dato3"=>$temp4, "dato4"=>$temp5);

            //$res[] = array("dat"=>$tem, "dato"=>$temp, "dato1"=>$temp2, "dato2"=>$temp3, "dato3"=>$temp4, "dato4"=>$temp5);
            //array_push($resultado, $miArray);
            //new 12 dic
            $result2 = mysql_query("SELECT personal_nombre FROM hc_agenda_personal WHERE (programa=1) AND (agenda_id=$temp5)");
            $nombreDelDoctor = mysql_fetch_assoc($result2);
            $temporary2 = ($nombreDelDoctor['personal_nombre']);
            //new 12 dic

            $miArray = array("dat"=>$tem, "dato"=>$temp, "dato1"=>$temp2, "dato2"=>$temp3, "dato3"=>$temp4, "dato4"=>$temp5, "dato5"=>$temp6, "dato6"=>$temporary2);

            $res[] = array("dat"=>$tem, "dato"=>$temp, "dato1"=>$temp2, "dato2"=>$temp3, "dato3"=>$temp4, "dato4"=>$temp5, "dato5"=>$temp6, "dato6"=>$temporary2);
            array_push($resultado, $miArray);
        }//Fin de while
            
        echo json_encode($resultado);
        //echo json_encode($resultado);
    }//Fin de la funcion getCirugiasCanceladas

    // getProcedimientosCancelada
    public function getProcedimientosCancelada($quirofano_id){

        $fechaActual = date("Y-m-d H:i:s");
        $newFecha = strtotime($fechaActual);

        //CANCELADAS
        $result = mysql_query("SELECT programacion, id FROM hc_agenda WHERE (quirofano_id='$quirofano_id') AND (cancelada=1) ");
        //AND (status=1 OR status=-50)

        $procedimientosProgramadas =array();
        $resultado = array();
        
        while ($rsEmp = mysql_fetch_assoc($result)){
            $tem = ($rsEmp['programacion']);
            $fechaTemporal = strtotime($tem);

            $temp = ($rsEmp['id']);
            $intTemp = (int)$temp;

            $result2 = mysql_query("SELECT id, cie9mc, region, detalles, servicio_id FROM hc_agenda_procedimientos WHERE (agenda_id=$intTemp)");
            while ($rsEmp2 = mysql_fetch_assoc($result2)){
                $temporary0 = ($rsEmp2['id']);
                $temporary1 = ($rsEmp2['cie9mc']);
                $temporary2 = ($rsEmp2['region']);
                $temporary3 = ($rsEmp2['detalles']);
                $temporary4 = ($rsEmp2['servicio_id']);
                $miArray = array("d"=>$intTemp, "d0"=>$temporary0, "d1"=>$temporary1, "d2"=>$temporary2, "d3"=>$temporary3, "d4"=>$temporary4);
                //echo "blabla"; 
                array_push($resultado, $miArray);
            }
        }//Fin de while
        //echo "agenda_id";            
        echo json_encode($resultado);
    }//Fin de la funcion getProcedimientosCancelada

}//Fin de la clase
 
?>
