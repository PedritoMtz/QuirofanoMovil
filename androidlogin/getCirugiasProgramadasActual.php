<?php 
/*Muestra las cirugias programadas del dia (status=1)*/

$status = $_POST['stat']; //valor de quirofano_id seleccionado
$intQuirofano = (int) $status;

require_once 'funciones_bd.php';

$db = new funciones_BD();
	$db->getCirugiasProgramadasActual($intQuirofano);	

/*
  	String URL_connect16 = "http://"+IP_Server+"/androidlogin/getCirugiasProgramadasActual.php";
    String URL_connect17 = "http://"+IP_Server+"/androidlogin/getCirugiasDiferidasActual.php";
    String URL_connect18 = "http://"+IP_Server+"/androidlogin/getCirugiasTransoperatorioActual.php";
    String URL_connect19 = "http://"+IP_Server+"/androidlogin/getCirugiasRealizadasActual.php";
    String URL_connect20 = "http://"+IP_Server+"/androidlogin/getProcedimientosProgramadasActual.php";
    String URL_connect21 = "http://"+IP_Server+"/androidlogin/getProcedimientosDiferidasActual.php";
    String URL_connect22 = "http://"+IP_Server+"/androidlogin/getProcedimientosTransoperatorioActual.php";
    String URL_connect23 = "http://"+IP_Server+"/androidlogin/getProcedimientosRealizadasActual.php";

    /*****************************************************************************************************

    ArrayList<ArrayList<String>> arrayProgramadasActual = new ArrayList<ArrayList<String>>();
	ArrayList<ArrayList<String>> arrayDiferidasActual = new ArrayList<ArrayList<String>>();
	ArrayList<ArrayList<String>> arrayTransoperatorioActual = new ArrayList<ArrayList<String>>();
	ArrayList<ArrayList<String>> arrayRealizadasActual = new ArrayList<ArrayList<String>>();
	//----------------------- PROCEDIMIENTOS DE CIRUGIAS DEL DIA DE HOY------------------------
	ArrayList<ArrayList<String>> procedimientosProgramadaActual = new ArrayList<ArrayList<String>>();
	ArrayList<ArrayList<String>> procedimientosDiferidaActual = new ArrayList<ArrayList<String>>();
	ArrayList<ArrayList<String>> procedimientosTransoperatorioActual = new ArrayList<ArrayList<String>>();
	ArrayList<ArrayList<String>> procedimientosRealizadaActual = new ArrayList<ArrayList<String>>();
	*/
?>
