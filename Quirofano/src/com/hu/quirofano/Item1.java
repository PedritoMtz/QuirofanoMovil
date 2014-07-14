package com.hu.quirofano;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.content.Context;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import android.widget.AutoCompleteTextView;

import com.actionbarsherlock.app.SherlockFragment;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.hu.libreria.HttpPostAux;
//import com.hu.quirofano.Item4.agendaDelDia; Esta clase esta marcada para eliminar

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.text.format.DateFormat;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
 
import com.hu.quirofano.TimePickerFragment;

//<<<<<<< HEAD
//prueba1
//	=======
//>>>>>>> f430b8bdebb5172a872119a5ea0a4c8815fe53e3
public class Item1 extends SherlockFragment {
	
	//27 dic
	ArrayList<ArrayList<String>> arrayDiferidas2 = new ArrayList<ArrayList<String>>();
	//fin 27 dic
	
	//10 Dic
	ArrayList<ArrayList<String>> procedimientosTraidos = new ArrayList<ArrayList<String>>();
	//10 Dic
	
	//PARA EL DATE PICKER ************************
	static int anio;
	static int mesDelAnio;
    static int diaDelMes;
    
    static String sAnio;
    static String sMesDelAnio;
    static String sDiaDelMes;
	
	private TextView pDisplayDate;
    private Button pPickDate;
    private int pYear;
    private int pMonth;
    private int pDay;
    /** This integer will uniquely define the dialog to be used for displaying date picker.*/
    static final int DATE_DIALOG_ID = 0;
    
    //PARA EL TIME PICKER ************************
    private TextView mPickedTimeText;
    static int horas;
    static int minutos;
    
    static String sHoras;
	static String sMinutos;
	
	//16noviembre
	TextView agregarTema;
	TextView temaDia;
	String[] myString;
	//FIN - 16noviembre
	
	//18 noviembre
	static int salaSpinner;
	ArrayList<ArrayList<String>> procedimientos = new ArrayList<ArrayList<String>>();
	String registroID;
	//18 noviembre
	
	//22oct
	//ArrayList<String> al = new ArrayList<String>(); 1st version
	TableLayout tl;				//Tabla que muestra la agenda1
	TableLayout tablaDeAgenda;	//Tabla que muestra la agenda2
	TableLayout tablaDeSalas;
	
	//27 nov
	TableLayout tablaDia;
	TableLayout tablaDiferida;
	
	//3-ene
	TableLayout tablaCancelada;
	
	//13nov
	String quirofano_id;
	String ID_quirofano;
	
	//3noviembre
	ProgramarCirugiaView object = new ProgramarCirugiaView();
	
	//Se desplegaran en la agenda los datos de hora, sala, paciente y diagnostico.
	
	ArrayList<String> st = new ArrayList<String>();		//Para almacenar las horas ... todavia no implementado
	ArrayList<String> st1 = new ArrayList<String>();	//Para almacenar las salas *NO SE USA*
	ArrayList<String> st2 = new ArrayList<String>();	//Para almacenar los pacientes **NO SE USA*
	ArrayList<String> st3 = new ArrayList<String>();	//Para almacenar los diagnosticos *NO SE USA*
	
	//27Nov
	ArrayList<ArrayList<String>> registroDelDia = new ArrayList<ArrayList<String>>();
	
	//22oct
	
	//4 noviembre *** Array de array - prueba *** (cirugias programadas = 1)
	ArrayList<ArrayList<String>> padre = new ArrayList<ArrayList<String>>();
	//27-noviembre ***Array de cirugias diferidas = -50 ***
	ArrayList<ArrayList<String>> arrayDiferidas = new ArrayList<ArrayList<String>>();
	// ***Array de cirugias transoperatorio = 10 ***
	ArrayList<ArrayList<String>> arrayTransoperatorio = new ArrayList<ArrayList<String>>();
	// ***Array de cirugias realizadas = 100 ***
	ArrayList<ArrayList<String>> arrayRealizadas = new ArrayList<ArrayList<String>>();
	// ***Array de cirugias programadas que pasaron de fecha
	ArrayList<ArrayList<String>> arrayProgramadasPasadas = new ArrayList<ArrayList<String>>();
	// ***Array de cirugias canceladas
	ArrayList<ArrayList<String>> arrayCanceladas = new ArrayList<ArrayList<String>>();
	
	/** ARRAYs PARA GUARDAR LOS PROCEDIMIENTOS OBTENIDOS - 12 DE DICIEMBRE */
	ArrayList<ArrayList<String>> arrayProcedimientosProgramada = new ArrayList<ArrayList<String>>();
	ArrayList<ArrayList<String>> arrayProcedimientosDiferida = new ArrayList<ArrayList<String>>();
	ArrayList<ArrayList<String>> arrayProcedimientosTransoperatorio = new ArrayList<ArrayList<String>>();
	ArrayList<ArrayList<String>> arrayProcedimientosRealizada = new ArrayList<ArrayList<String>>();
	ArrayList<ArrayList<String>> arrayProcedimientosProgramadaPasada = new ArrayList<ArrayList<String>>();
	//3enero
	ArrayList<ArrayList<String>> arrayProcedimientosCancelada = new ArrayList<ArrayList<String>>();
	
	/** ARRAYS DE CIRUGIAS DEL DIA DE HOY 
	 * 17-dic
	 * ***************************************************************************************/
	ArrayList<ArrayList<String>> arrayProgramadasActual = new ArrayList<ArrayList<String>>();
	ArrayList<ArrayList<String>> arrayDiferidasActual = new ArrayList<ArrayList<String>>();
	ArrayList<ArrayList<String>> arrayTransoperatorioActual = new ArrayList<ArrayList<String>>();
	ArrayList<ArrayList<String>> arrayRealizadasActual = new ArrayList<ArrayList<String>>();
	//----------------------- PROCEDIMIENTOS DE CIRUGIAS DEL DIA DE HOY------------------------
	ArrayList<ArrayList<String>> procedimientosProgramadaActual = new ArrayList<ArrayList<String>>();
	ArrayList<ArrayList<String>> procedimientosDiferidaActual = new ArrayList<ArrayList<String>>();
	ArrayList<ArrayList<String>> procedimientosTransoperatorioActual = new ArrayList<ArrayList<String>>();
	ArrayList<ArrayList<String>> procedimientosRealizadaActual = new ArrayList<ArrayList<String>>();
	/** **************************************************************************************/
		
	//18 de noviembre - Guarda los id y los nombres  de las salas
	ArrayList<ArrayList<String>> salasArray = new ArrayList<ArrayList<String>>();
	static ArrayList<String> nombreSalas = new ArrayList<String>();	//Alojar nombre de salas, similar al de arriba
	
	//18Dic - llenar el spinner de servicios en la seccion programar cirugia
	static ArrayList<String> nombreServicios = new ArrayList<String>();
	static ArrayList<ArrayList<String>> arrayServicios = new ArrayList<ArrayList<String>>();
	
	//26oct
	private TextView mDateDisplay;
	private int mYear;
	private int mMonth;
	private int mDay;
	//static final int DATE_DIALOG_ID = 0;
	//26oct
	
	//Spinners
	/*
	int sala;
	int duracion;
	int tipoDeProgramacion;
	int servicio;
	int enAtencionA;
	*/
	//------------------------------
	//int region;		//Vista dinamica
	//int servicio2;	//Vista dinamica
	
	//RadioGroup
	//Devuelve un entero, para conocer el String correspondiente usar RadioButton
	RadioGroup protocolo;
	RadioGroup reintervencion;
	//RadioButton
	RadioButton prot;
	RadioButton reint;
	
	//EditText
	EditText fecha;
	EditText horaPropuesta; 
	EditText registro;
	EditText nombreDelPaciente;
	EditText edad;
	EditText genero;
	EditText procedencia;
	EditText diagnostico;
	EditText medico;
	EditText riesgoQuirurgico;
	EditText insumosIndispensables;
	EditText requerimientos;
	EditText hemoderivados;
	EditText requisitos;
	EditText necesidades;
	
	//Button
	Button botonGuardar;
	
	HttpPostAux post;
	HttpPostAux envio;
	HttpPostAux send;
    //String IP_Server="172.16.0.125";//IP DE NUESTRO PC
	String IP_Server = MainActivity.IP_Server;
	String URL_connect="http://"+IP_Server+"/androidlogin/schedule.php";//ruta en donde estan nuestros archivos
    String URL_connect2 = "http://"+IP_Server+"/androidlogin/mostrarAgenda.php"; 
    String URL_connect3 = "http://"+IP_Server+"/androidlogin/getQuirofanoId.php";
    String URL_connect4 = "http://"+IP_Server+"/androidlogin/getSalas.php";
    String URL_connect5 = "http://"+IP_Server+"/androidlogin/llenarProcedimientos.php";
    String URL_connect6 = "http://"+IP_Server+"/androidlogin/agendaDelDia.php";
    String URL_connect7 = "http://"+IP_Server+"/androidlogin/registroDiferida.php";
    String URL_connect8 = "http://"+IP_Server+"/androidlogin/registroTransoperatorio.php";
    String URL_connect9 = "http://"+IP_Server+"/androidlogin/registroRealizada.php";
    String URL_connect10 = "http://"+IP_Server+"/androidlogin/registroPasada.php";
    String URL_connect11 = "http://"+IP_Server+"/androidlogin/getProcedimientosProgramada.php";
    String URL_connect12 = "http://"+IP_Server+"/androidlogin/getProcedimientosDiferida.php";
    String URL_connect13 = "http://"+IP_Server+"/androidlogin/getProcedimientosTransoperatorio.php";
    String URL_connect14 = "http://"+IP_Server+"/androidlogin/getProcedimientosRealizada.php";
    String URL_connect15 = "http://"+IP_Server+"/androidlogin/getProcedimientosProgramadaPasada.php";
    
	String URL_connect16 = "http://"+IP_Server+"/androidlogin/getCirugiasProgramadasActual.php";
    String URL_connect17 = "http://"+IP_Server+"/androidlogin/getCirugiasDiferidasActual.php";
    String URL_connect18 = "http://"+IP_Server+"/androidlogin/getCirugiasTransoperatorioActual.php";
    String URL_connect19 = "http://"+IP_Server+"/androidlogin/getCirugiasRealizadasActual.php";
    String URL_connect20 = "http://"+IP_Server+"/androidlogin/getProcedimientosProgramadasActual.php";
    String URL_connect21 = "http://"+IP_Server+"/androidlogin/getProcedimientosDiferidasActual.php";
    String URL_connect22 = "http://"+IP_Server+"/androidlogin/getProcedimientosTransoperatorioActual.php";
    String URL_connect23 = "http://"+IP_Server+"/androidlogin/getProcedimientosRealizadasActual.php";
    String URL_connect24 = "http://"+IP_Server+"/androidlogin/getServicios.php";
    String URL_connect25 = "http://"+IP_Server+"/androidlogin/getCirugiasCanceladas.php";
    String URL_connect26 = "http://"+IP_Server+"/androidlogin/getProcedimientosCancelada.php";
  
    ListView lista;
    ListView lista2; //prueba 27nov
    ListView lista3;
    ListView lista4;
    ListView lista_salas;
    
    //27nov
    ListView listaDia;
    
    //28nov
    ListView listaDiferida;
    
    boolean result_back;
    private ProgressDialog progress;
    
    /*9 de octubre - Fecha - Hora propuesta*/
	
    //Se cambio el inflater y container a final
	public View onCreateView(final LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState)
	{
		/*Recuperar parametros del fragment donde se llama: Item01*/
		Bundle bundle = this.getArguments();
		myString = bundle.getStringArray("parametro");
		System.out.println("ITEM1 RECIBIDO quirofano_id = "+myString[0]);
		System.out.println("ITEM1 RECIBIDO quirofano_name = "+myString[1]);
		
		ID_quirofano = myString[0];
				
		post = new HttpPostAux();
		envio = new HttpPostAux();
		send = new HttpPostAux();
		
		//*************************************************************
		
		View v = inflater.inflate(R.layout.qcentral, container, false);
		//Poner tema a la vista: el titulo sera el quirofano seleccionado
		agregarTema = (TextView)v.findViewById(R.id.titulo_quirofano);
		agregarTema.setText("Quirófano "+myString[1]);
		
		final ScrollView sv = (ScrollView)v.findViewById(R.id.vista);
		
		final View home = inflater.inflate(R.layout.home, container, false); //Layout incorrecto, no sera home
		// ... a diferencia del MainView estas seran cirugias programas del dia, incluyendo diferidas.
		final View homeDiferidas = inflater.inflate(R.layout.home, container, false); //seccion cirugias diferidas
		final View homeAgendaDelDia = inflater.inflate(R.layout.home, container, false);
		
		final View home2 = inflater.inflate(R.layout.home2, container, false);
		final View home3 = inflater.inflate(R.layout.home, container, false);
		final View home4 = inflater.inflate(R.layout.home, container, false); //Para diferidas
		final View home5 = inflater.inflate(R.layout.home, container, false); //Para cirugias canceladas
		
		final View programar = inflater.inflate(R.layout.programar_cirugia, container, false);
		//SALAS
		final View mostrarSalas = inflater.inflate(R.layout.salas_cabecera, container, false);
		final View fondoSalas = inflater.inflate(R.layout.tabla_salas, container, false);
		
		final View fondoAgendaDia = inflater.inflate(R.layout.tabla_dia, container, false);
		//new ProgramarCirugiaView(inflater, programar, getActivity());
		
		new GetServicios(inflater, programar, container).execute(ID_quirofano);	//OBTENER LOS SERVICIOS PARA PONER EN SPINNER DE PROGRAMAR CIR. 
		
		protocolo = (RadioGroup) programar.findViewById(R.id.groupProtocolo);
		reintervencion = (RadioGroup) programar.findViewById(R.id.groupReintervencion);
		
		//EditText
		fecha = (EditText) programar.findViewById(R.id.fecha);
		horaPropuesta = (EditText) programar.findViewById(R.id.horaPropuesta);
		registro = (EditText) programar.findViewById(R.id.registro);
		nombreDelPaciente = (EditText) programar.findViewById(R.id.nombreDelPaciente);
		edad =(EditText) programar.findViewById(R.id.edad);
		genero =(EditText) programar.findViewById(R.id.genero);
		procedencia =(EditText) programar.findViewById(R.id.procedencia);
		diagnostico =(EditText) programar.findViewById(R.id.diagnostico);
		//
		medico =(EditText) programar.findViewById(R.id.medico);
		edad =(EditText) programar.findViewById(R.id.edad);
		riesgoQuirurgico =(EditText) programar.findViewById(R.id.riesgoQuirurgico);
		insumosIndispensables =(EditText) programar.findViewById(R.id.insumosIndispensables);
		requerimientos =(EditText) programar.findViewById(R.id.requerimientos);
		hemoderivados =(EditText) programar.findViewById(R.id.hemoderivados);
		requisitos =(EditText) programar.findViewById(R.id.requisitosDeLaboratorio);
		necesidades =(EditText) programar.findViewById(R.id.otrasNecesidades);
		
		//EditText
		botonGuardar = (Button) programar.findViewById(R.id.botonGuardar);
		//***************************************************************
		
		tl = (TableLayout)home.findViewById(R.id.table);
		TableRow tr = (TableRow) inflater.inflate(R.layout.tablerow, container, false);
		tl.addView(tr);
		
		lista = (ListView)home.findViewById(R.id.lista);
		lista_salas = (ListView)fondoSalas.findViewById(R.id.lista);
		listaDia = (ListView)home3.findViewById(R.id.lista);
		
		lista2 = (ListView)home4.findViewById(R.id.lista); //para diferida
		lista3 = (ListView)home4.findViewById(R.id.lista); //Para transoperatorio, no se mostrara en la app
		lista4 = (ListView)home4.findViewById(R.id.lista); //Para las realizadas, no se mostrara en la app
		
		tablaDeAgenda = (TableLayout)home2.findViewById(R.id.table);
		TableRow tablerow = (TableRow) inflater.inflate(R.layout.tablerow, container, false);
		tablaDeAgenda.addView(tablerow);
		
		new GetSalas(inflater, container).execute(ID_quirofano);	//OBTENER LAS SALAS DEL RESPECTIVO QUIROFANO
				
		// 21 oct-----------------------------------------------------------------------
		
		/*Lo siguiente ira dentro de la sub-clase Agenda*/
		String val = ID_quirofano;
		Log.e("antes de agenda", "antes de agenda");
		
		new GetProcedimientosProgramada().execute(ID_quirofano);
		new GetProcedimientosDiferida().execute(ID_quirofano);
		new GetProcedimientosTransoperatorio().execute(ID_quirofano);
		new GetProcedimientosRealizada().execute(ID_quirofano);
		new GetProcedimientosProgramadaPasada().execute(ID_quirofano);
		new GetProcedimientosCancelada().execute(ID_quirofano);
		
		//Importa el orden
		//new RegistroDiferida(inflater, container).execute(ID_quirofano);		//OBTENER CIRUGAS DIFERIDAS(agenda global)
		new RegistroDiferida2(inflater, container).execute(ID_quirofano);		//OBTENER CIRUGAS DIFERIDAS2(agenda diferida)
		new RegistroTransoperatorio(inflater, container).execute(ID_quirofano);	//OBTENER CIRUGIAS TRANSOPERATORIO
		new RegistroRealizada(inflater, container).execute(ID_quirofano);		//OBTENER CIRUGIAS REALIZADAS
		new RegistroProgramadaPasada(inflater, container).execute(ID_quirofano);//OBTENER CIRUGIAS PROGRAMADAS PASADAS
		new Agenda(inflater, container).execute(val);							//OBTENER CIRUGIAS PROGRAMADAS	
		
		Log.e("paso1", "paso1");
		// 21 oct ----------------------------------------------------------------------
		
		sv.addView(home);
		Button agenda = (Button)v.findViewById(R.id.agenda);
		Button pc = (Button)v.findViewById(R.id.pc);
		Button cd = (Button)v.findViewById(R.id.cd);
		//3ene
		Button cc = (Button)v.findViewById(R.id.cc);
		Button agendaDia = (Button)v.findViewById(R.id.agenda_del_dia);
		Button salas = (Button)v.findViewById(R.id.salas);
		
		agenda.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				sv.removeAllViews();
				tl.removeAllViews();
				TableRow tr = (TableRow) inflater.inflate(R.layout.tablerow, container, false);
				tl.addView(tr);
				//new Agenda(inflater, container).execute(ID_quirofano);
				new GetProcedimientosProgramada().execute(ID_quirofano);
				new GetProcedimientosDiferida().execute(ID_quirofano);
				new GetProcedimientosTransoperatorio().execute(ID_quirofano);
				new GetProcedimientosRealizada().execute(ID_quirofano);
				new GetProcedimientosProgramadaPasada().execute(ID_quirofano);
				
				//Importa el orden
				new RegistroDiferida2(inflater, container).execute(ID_quirofano);			//OBTENER CIRUGAS DIFERIDAS
				new RegistroTransoperatorio(inflater, container).execute(ID_quirofano);		//OBTENER CIRUGIAS TRANSOPERATORIO
				new RegistroRealizada(inflater, container).execute(ID_quirofano);			//OBTENER CIRUGIAS REALIZADAS
				new RegistroProgramadaPasada(inflater, container).execute(ID_quirofano);	//OBTENER CIRUGIAS PROGRAMADAS PASADAS
				new Agenda(inflater, container).execute(ID_quirofano);
				sv.addView(home);
			}
		});
		
		tablaDiferida = (TableLayout)home4.findViewById(R.id.table);
		TableRow cabeceraDiferida = (TableRow) inflater.inflate(R.layout.tablerow, container, false);
		tablaDiferida.addView(cabeceraDiferida);
		
		cd.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				sv.removeAllViews();
				tl.removeAllViews();
				TableRow tr = (TableRow) inflater.inflate(R.layout.tablerow, container, false);
				tl.addView(tr);
				new RegistroDiferida(inflater, container).execute(ID_quirofano);
				TextView temaDiferida = (TextView)home.findViewById(R.id.title);
				temaDiferida.setText("Cirugías diferidas");
				sv.addView(home);
			}
		});
		
		tablaCancelada = (TableLayout) home5.findViewById(R.id.table);
		TableRow cabeceraCancelada = (TableRow) inflater.inflate(R.layout.tablerow, container, false);
		tablaCancelada.addView(cabeceraCancelada);
		
		cc.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				sv.removeAllViews();
				tl.removeAllViews();
				TableRow tr = (TableRow) inflater.inflate(R.layout.tablerow, container, false);
				tl.addView(tr);
				new GetCirugiasCanceladas(inflater, container).execute(ID_quirofano);
				TextView temaCancelada = (TextView)home.findViewById(R.id.title);
				temaCancelada.setText("Cirugías canceladas");
				sv.addView(home);
			}
		});
		
		tablaDia = (TableLayout)home3.findViewById(R.id.table);
		TableRow cabeceraDia = (TableRow) inflater.inflate(R.layout.tablerow, container, false);
		tablaDia.addView(cabeceraDia);
		
		agendaDia.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				sv.removeAllViews();
				tl.removeAllViews();
				TableRow tr = (TableRow) inflater.inflate(R.layout.tablerow, container, false);
				tl.addView(tr);
				new AgendaDelDia(inflater, container).execute(ID_quirofano);
				TextView temaDia = (TextView)home.findViewById(R.id.title);
				temaDia.setText("Agenda del Día");
				sv.addView(home);
			}
		});
		
		pc.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				sv.removeAllViews();
				TextView t = new TextView(getActivity());
				t.setText("Hola de nuevo");

				sv.addView(programar); //aqui termina victor
				
				/** PARA DATEPICKER **************************************************** */
				
				pDisplayDate = (TextView) programar.findViewById(R.id.displayDate);
		 
		        pDisplayDate.setOnClickListener(new OnClickListener() {
		        	   @Override
		        	   public void onClick(View v) {
		        	    showDatePicker();
		        	   }
		        	  });
				
				/** PARA DATEPICKER ******************************************************/
		        
		        /** PARA TIME PICKER***************************************************** */
		        mPickedTimeText = (TextView) programar.findViewById(R.id.hour);
		        mPickedTimeText.setOnClickListener(new OnClickListener()
		        {
		            @Override
		            public void onClick(View v ){
		            	showTimePicker();
		            }
		        });
		        
		        /** PARA TIME PICKER *****************************************************/
					
				//NUEVO SPINNER 1 ***************************************************************
				Spinner sp = (Spinner) programar.findViewById(R.id.salaOpciones);
				
				ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                        android.R.layout.simple_spinner_item, nombreSalas);
                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
				
				sp.setAdapter(adapter);

				sp.setOnItemSelectedListener(new OnItemSelectedListener() {

					public void onItemSelected(AdapterView<?> parentView, View selectedItemView,
							int position, long id) {
						
						parentView.getItemAtPosition(position);

						Log.e("spiner1 = ", "posicion1="+position);
						
						int sala=position;
						Item1.salaSpinner = sala;
					}

					public void onNothingSelected(AdapterView<?> parentView) {

					}
				});
		        
		        botonGuardar.setOnClickListener(new OnClickListener(){
		        	public void onClick(View v){
		        		
//		        		ArrayList<ArrayList<String>> procedimientos = new ArrayList<ArrayList<String>>();
		        		procedimientos.clear();
		        		ArrayList<ObjectoX> ob = new ArrayList<ObjectoX>();
		        		
		        		ob = ProgramarCirugiaView.vista;
		        		int number = ob.size();
		        		System.out.println("No. VISTAS = "+number);
		        		
		        		/*TRAER DATOS DE LOS PROCEDIMIENTOS DINAMICOS*/
		        		//******************************************************************
		        		for (int index = 0; index<number; index++){
							System.out.println(index+"####################################");
							
							ArrayList<String> temporary = new ArrayList<String>();
							temporary.clear();
//							String servicioString = Integer.toString(ob.get(index).getServicio());
							String servicioString = arrayServicios.get(ob.get(index).getServicio()).get(0);	
							String regionString = Integer.toString(ob.get(index).getRegion());
							
							temporary.add(servicioString);
							temporary.add(regionString);
							temporary.add(ob.get(index).getProc());
							temporary.add(ob.get(index).getDetalles());
							
							procedimientos.add(temporary);
							
							System.out.println(ob.get(index).getServicio());
							System.out.println(ob.get(index).getRegion());
							System.out.println(ob.get(index).getProc());
							System.out.println(ob.get(index).getDetalles());
							//temporary.clear();
						}
						System.out.println("PROCEDIMIENTOS: "+procedimientos);

						String date = sAnio+"-"+sMesDelAnio+"-"+sDiaDelMes;
						String hora = sHoras+":"+sMinutos;
						
				        String reg = registro.getText().toString();
				        String paciente = nombreDelPaciente.getText().toString();
				        String sEdad = edad.getText().toString();
				        String sGenero = genero.getText().toString();
				        String sProcedencia = procedencia.getText().toString();
				    	String sDiagnostico = diagnostico.getText().toString();
				        String sMedico = medico.getText().toString();
				        String sRiesgoQuirurgico = riesgoQuirurgico.getText().toString();
				    	String sInsumosIndispensables = insumosIndispensables.getText().toString();
				    	String sRequerimientos = requerimientos.getText().toString();
				    	String sHemoderivados = hemoderivados.getText().toString();
				    	String sRequisitos = requisitos.getText().toString();
				    	String sNecesidades = necesidades.getText().toString();
				    	
				        //Obtener enteros de RadioGroup
				        int protocoloSelected = 0;
				        int reintervencionSelected = 0;
				        protocoloSelected = protocolo.getCheckedRadioButtonId();
				        reintervencionSelected = reintervencion.getCheckedRadioButtonId();
				        
				        prot = (RadioButton) getActivity().findViewById(protocoloSelected);
				        reint = (RadioButton) getActivity().findViewById(reintervencionSelected);
				        //convertir a string para mandar a BD
				        String sProtocolo = prot.getText().toString();
				        String sReintervencion = reint.getText().toString();
				        
				        int p = 0;
				        int r = 0;
				        
				        if (sProtocolo.equals("No")){
				        	p = 0;
				        }
		        		else p = 1;
				        
				        if (sReintervencion.equals("No")){
				        	r = 0;
				        }
				        else p=1;
				        
				        System.out.println("intProtocolo = "+ p);
				        System.out.println("intReintervencion = "+ r);
				        
				        System.out.println("tipo sProtocolo = "+sProtocolo.getClass().getName()+"valor = "+sProtocolo);
				        System.out.println("tipo sReintervencion = "+sReintervencion.getClass().getName()+"valor = "+sReintervencion);
				        
				        ProgramarCirugiaView pos = new ProgramarCirugiaView();
				        
				        System.out.println("sala = "+getSala());
				        System.out.println("duracion = "+object.getDuracion());
				        System.out.println("programacion = "+object.getProgramacion());
				        System.out.println("servicio = "+object.getServicio());
				        System.out.println("atencion = "+object.getAtencion());
				        
				        //int sala = object.getSala();
				        int sala = getSala();
				        String sDuracion = object.getDuracion();
				        int programacion = object.getProgramacion();
				        int servicio = object.getServicio();
				        int atencion = object.getAtencion();
				        
				        String sSala = Integer.toString(sala);
				        //String sDuracion = Integer.toString(duracion);
				        String sProgramacion = Integer.toString(programacion);
//				        String sServicio = Integer.toString(servicio);
				        String sServicio = arrayServicios.get(servicio).get(0);
				        String sAtencion = Integer.toString(atencion);
				        
				        String sP = Integer.toString(p);
				        String sR = Integer.toString(r);
		        		
		        		if (validarFormulario(date, hora, reg, paciente, sEdad, sGenero, sProcedencia,
		        				sDiagnostico, sMedico, sRiesgoQuirurgico, sInsumosIndispensables,
		        				sRequerimientos, sHemoderivados, sRequisitos, sNecesidades) == true){
		        			
		        			// LOOP PARA OBTENER TODOS LOS DATOS DE LOS PROCEDIMIENTOS DINAMICOS*********
		        			//System.out.println(procedimientos);
		        			//Orden: Servicio-Region-Procedimiento-Detalles
		        			
		        			// LOOP PARA OBTENER TODOS LOS DATOS DE LOS PROCEDIMIENTOS DINAMICOS*********
		        			
		        			//Primero mandamos los Spinners luego los RadioButton
		        			new Formulario(inflater, container, sv, home).execute(date, hora, reg, paciente, sEdad, sGenero, sProcedencia,
		        					sDiagnostico, sMedico, sRiesgoQuirurgico, sInsumosIndispensables,
		        					sRequerimientos, sHemoderivados, sRequisitos, sNecesidades, 
		        					sSala, sDuracion, sProgramacion, sServicio, sAtencion, sP, sR);
		        			//sv.removeAllViews();
		        			//sv.addView(home);
				        }
				        else{
				        	error1();
				        }//Fin de else
		        	}//Fin de onClick
		        });//boton de guardar formulario de cirugia
		        
			}//Fin de onClick(Seccion - boton de programar cirugia)

		});
		
		tablaDeSalas = (TableLayout)fondoSalas.findViewById(R.id.table_salas);
		TableRow cabeceraSalas = (TableRow) inflater.inflate(R.layout.salas_cabecera, container, false);
		tablaDeSalas.addView(cabeceraSalas);
		
		salas.setOnClickListener(new OnClickListener(){
			
			@Override
			public void onClick(View v) {
				sv.removeAllViews();
				sv.addView(fondoSalas);
			}
		});
		
		return v;
	}// Fin de onCreateView
	
	//Primero validamos los campos
	public boolean validarFormulario(String date, String hora, String reg, String paciente, 
			String sEdad, String sGenero, String sProcedencia, String sDiagnostico, String sMedico,
			String sRiesgoQuirurgico, String sInsumosIndispensables, String sRequerimientos,
			String sHemoderivados, String sRequisitos, String sNecesidades){
		if 	(date.equals("") || hora.equals("") || reg.equals("") || paciente.equals("") ||
				sEdad.equals("") || sGenero.equals("") || sProcedencia.equals("") || 
				sDiagnostico.equals("") || sMedico.equals("") || sRiesgoQuirurgico.equals("") ||
				sInsumosIndispensables.equals("") || sRequerimientos.equals("") || sHemoderivados.equals("") ||
				sRequisitos.equals("") || sNecesidades.equals("") ){
			Log.e("date = ", "date="+date);
			Log.e("hora = ", "hora="+hora);
			Log.e("Login ui", "checklogindata user or pass error");
			//Toast toast1 = Toast.makeText(getActivity().getApplicationContext(),"Validacion d = "+d+" h = "+h, Toast.LENGTH_SHORT);
	 	    //toast1.show();
        	return false;
        
        }else{
        	//Toast toast2 = Toast.makeText(getActivity().getApplicationContext(),"Validacion d = "+d+" h = "+h, Toast.LENGTH_SHORT);
        	//toast2.show();
        	return true;
        }
	}//fin de validarFormulario
	
	//Enviar
	public String enviarFormulario(String date, String hora, String reg, String paciente, String sEdad,
			String sGenero, String sProcedencia, String sDiagnostico, String sMedico,
			String sRiesgoQuirurgico, String sInsumosIndispensables, String sRequerimientos, 
			String sHemoderivados, String sRequisitos, String sNecesidades, 
			String sSala, String sDuracion, String sProgramacion, String sServicio, String sAtencion, 
			String sP, String sR){
	
//		int status = -1;
		String status = "";
		
		/*Creamos un ArrayList del tipo nombre valor para agregar los datos recibidos por los parametros anteriores
    	 * y enviarlo mediante POST a nuestro sistema para relizar la validacion*/
		
		ArrayList<NameValuePair> datosEnviar= new ArrayList<NameValuePair>();
		
	    datosEnviar.add(new BasicNameValuePair("date",date));
		datosEnviar.add(new BasicNameValuePair("hora",hora));
		datosEnviar.add(new BasicNameValuePair("reg",reg));
		datosEnviar.add(new BasicNameValuePair("paciente",paciente));
		datosEnviar.add(new BasicNameValuePair("sEdad",sEdad));
		datosEnviar.add(new BasicNameValuePair("sGenero",sGenero));
		datosEnviar.add(new BasicNameValuePair("sProcedencia",sProcedencia));
		datosEnviar.add(new BasicNameValuePair("sDiagnostico",sDiagnostico));
		datosEnviar.add(new BasicNameValuePair("sMedico",sMedico));
		datosEnviar.add(new BasicNameValuePair("sRiesgoQuirurgico",sRiesgoQuirurgico));
		datosEnviar.add(new BasicNameValuePair("sInsumosIndispensables",sInsumosIndispensables));
		datosEnviar.add(new BasicNameValuePair("sRequerimientos",sRequerimientos));
		datosEnviar.add(new BasicNameValuePair("sHemoderivados",sHemoderivados));
		datosEnviar.add(new BasicNameValuePair("sRequisitos",sRequisitos));
		datosEnviar.add(new BasicNameValuePair("sNecesidades",sNecesidades));
		//EditText hasta aqui
		
		//Spinners y RadioButton
		datosEnviar.add(new BasicNameValuePair("sSala", sSala));
		datosEnviar.add(new BasicNameValuePair("sDuracion", sDuracion));
		datosEnviar.add(new BasicNameValuePair("sProgramacion", sProgramacion));
		datosEnviar.add(new BasicNameValuePair("sServicio", sServicio));
		datosEnviar.add(new BasicNameValuePair("sAtencion", sAtencion));
		datosEnviar.add(new BasicNameValuePair("sP", sP));
		datosEnviar.add(new BasicNameValuePair("sR", sR));
			
		//String con el "quirofano_id" --- 1:central, (2:ambulatoria, 3:traumatologia)
		//Obtener el quirofano_id de la tabla "siga_quirofano", ej: central = 1
		//Luego programar la cirugia con ese int
		
		//Nueva forma de obtener el id del quirofano
		System.out.println("q_id = "+ID_quirofano); 
		datosEnviar.add(new BasicNameValuePair("q_id", ID_quirofano));

		// FIN - Nueva forma de obtener el id del quirofano
		
		Log.e("datosEnviar","datosEnviar = "+datosEnviar);
		Log.e("URL_connect","URL_connect = "+URL_connect);
		
		//realizamos una peticion y como respuesta obtienes un array JSON
  		JSONArray jdata=post.getserverdata(datosEnviar, URL_connect);
  		  		
  		//si lo que obtuvimos no es null
    	if (jdata!=null && jdata.length() > 0){
    		JSONObject json_data; //creamos un objeto JSON
			try {
				json_data = jdata.getJSONObject(0); //leemos el primer segmento en nuestro caso el unico
//				status=json_data.getInt("logstatus");//accedemos al valor
				status=json_data.getString("registro_id");//nuevo valor 18-noviembre
				Log.e("enviarFormulario","status= "+status);//muestro por log que obtuvimos
			}
			catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		            
             
			//validamos el valor obtenido
    		if (status.equals("error")){// [{"logstatus":"0"}] 
    			Log.e("programacion-status ", "envio fallido");
    			return status;
    		}
    		else{// [{"logstatus":"1"}]
    			Log.e("programacion-status ", "envio exitoso");
    			registroID = status;
    			return status;
    		}
    	}//Fin de if(comprueba si lo obtenido no es "null")
    	
    	else{	//json obtenido invalido verificar parte WEB.
    		Log.e("JSON  ", "ERROR");
	    	return status;
	    }//Fin de else
	}//Fin de enviar formulario
	
	//public ArrayList<String> mostrarAgenda(String s){
	public void mostrarAgenda(String s) throws JSONException{	
		padre.clear();
		//ArrayList<String> st = new ArrayList<String>();
		String val = "";
		String value = "";
		String value1 = "";
		String value2 = "";
		String value3 = "";
		String value4 = "";
		String value5 = "";
		String value6 = "";
		String cont = "";
		
		ArrayList<NameValuePair> datosEnviar= new ArrayList<NameValuePair>();
		datosEnviar.add(new BasicNameValuePair("stat", s));
		
		//SystemClock.sleep(4450);
		//JSONObject json_objeto;
		JSONArray jdata = envio.getserverdata(datosEnviar, URL_connect2);
		//System.out.println("jdata = "+jdata.getString(0));
		System.out.println(jdata.toString());
		System.out.println("largo de jdata = "+jdata.length());
		if (jdata!=null && jdata.length() > 0){
    		//JSONObject json_data; //creamos un objeto JSON
			try {
				
				for(int n = 0; n < jdata.length(); n++){
					//st.clear();
					System.out.println("vuelta:"+n);
					JSONObject json_data = jdata.getJSONObject(n);
					val = json_data.getString("dat");		//Fecha
					value = json_data.getString("dato");	//Hora
					value1 = json_data.getString("dato1");	//Sala
					value2 = json_data.getString("dato2");	//Paciente
					value3 = json_data.getString("dato3");	//Diagnostico
					value4 = json_data.getString("dato4");	//ID
					value5 = json_data.getString("dato5");	//Registro
					value6 = json_data.getString("dato6"); 	//Nombre del dr que programa
					
					ArrayList<String> temporary = new ArrayList<String>();
					
					temporary.add(val);
					temporary.add(value);
					temporary.add(value1);
					temporary.add(value2);
					temporary.add(value3);
					temporary.add(value4);
					temporary.add(value5);
					temporary.add(value6);
					//st.add(value);
					//st.add(value1);
					//st.add(value2);
					//st.add(value3);
					
					Log.e("log-st", "array temprary = "+temporary);
					
					padre.add(temporary);
					//st.clear();
				}

				Log.e("array padre", "padre = "+padre);
			}
			catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				Log.e("hi", "hi");
			}
    	}//Fin de if(comprueba si lo obtenido no es "null")
    	
    	else{	//json obtenido invalido verificar parte WEB.
    		Log.e("JSON  ", "ERROR");
	    	//return st;
	    }
	}//Fin de mostrarAgenda
	
	public void mostrarRegistroDiferida(String s) throws JSONException{	
		arrayDiferidas.clear();
		String val = "", value = "", value1 = "", value2 = "", value3 = "", value4 = "",
				value5="", value6="",cont = "";
		
		ArrayList<NameValuePair> datosEnviar= new ArrayList<NameValuePair>();
		datosEnviar.add(new BasicNameValuePair("stat", s));

		JSONArray jdata = envio.getserverdata(datosEnviar, URL_connect7);
		//System.out.println("jdata = "+jdata.getString(0));
		System.out.println(jdata.toString());
		System.out.println("largo de jdata = "+jdata.length());
		if (jdata!=null && jdata.length() > 0){
    		//JSONObject json_data; //creamos un objeto JSON
			try {
				
				for(int n = 0; n < jdata.length(); n++){
					//st.clear();
					System.out.println("vuelta:"+n);
					JSONObject json_data = jdata.getJSONObject(n);
					val = json_data.getString("dat");		//Fecha
					value = json_data.getString("dato");	//Hora
					value1 = json_data.getString("dato1");	//Sala
					value2 = json_data.getString("dato2");	//Paciente
					value3 = json_data.getString("dato3");	//Diagnostico
					value4 = json_data.getString("dato4");	//ID
					value5 = json_data.getString("dato5");	//Registro
					value6 = json_data.getString("dato6"); 	//Nombre del dr que programa
					
					ArrayList<String> temporary = new ArrayList<String>();
					
					temporary.add(val);
					temporary.add(value);
					temporary.add(value1);
					temporary.add(value2);
					temporary.add(value3);
					temporary.add(value4);
					temporary.add(value5);
					temporary.add(value6);
		
					Log.e("RegistroDiferida", "array temporary = "+temporary);
					arrayDiferidas.add(temporary);
				}

				Log.e("array RegistroDiferida", "arrayDiferidas = "+arrayDiferidas);
			}
			catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				Log.e("hi", "hi");
			}		            	
    	}//Fin de if(comprueba si lo obtenido no es "null")
    	
    	else{	//json obtenido invalido verificar parte WEB.
    		Log.e("JSON  ", "ERROR");
	    	//return st;
	    }
	}//Fin de mostrarRegistroDiferida
	
	//MOSTRAR REGISTRO TRANSOPERATORIO
	public void mostrarRegistroTransoperatorio(String s) throws JSONException{	
		arrayTransoperatorio.clear();
		String val = "", value = "", value1 = "", value2 = "", value3 = "", value4 = "", 
				value5 = "", value6 = "", cont = "";
		
		ArrayList<NameValuePair> datosEnviar= new ArrayList<NameValuePair>();
		datosEnviar.add(new BasicNameValuePair("stat", s));

		JSONArray jdata = envio.getserverdata(datosEnviar, URL_connect8);
		//System.out.println("jdata = "+jdata.getString(0));
		System.out.println(jdata.toString());
		System.out.println("largo de jdata = "+jdata.length());
		if (jdata!=null && jdata.length() > 0){
    		//JSONObject json_data; //creamos un objeto JSON
			try {
				for(int n = 0; n < jdata.length(); n++){
					//st.clear();
					System.out.println("vuelta:"+n);
					JSONObject json_data = jdata.getJSONObject(n);
					val = json_data.getString("dat");		//Fecha
					value = json_data.getString("dato");	//Hora
					value1 = json_data.getString("dato1");	//Sala
					value2 = json_data.getString("dato2");	//Paciente
					value3 = json_data.getString("dato3");	//Diagnostico
					value4 = json_data.getString("dato4");	//ID
					value5 = json_data.getString("dato5");	//Registro
					value6 = json_data.getString("dato6"); 	//Nombre del dr que programa
					
					ArrayList<String> temporary = new ArrayList<String>();
					
					temporary.add(val);
					temporary.add(value);
					temporary.add(value1);
					temporary.add(value2);
					temporary.add(value3);
					temporary.add(value4);
					temporary.add(value5);
					temporary.add(value6);
					
					Log.e("RegistroDiferida", "array temporary = "+temporary);
					
					arrayTransoperatorio.add(temporary);
				}

				Log.e("array RegistroTransoperatorio", "arrayTransoperatorio = "+arrayTransoperatorio);
			}
			catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				Log.e("hi", "hi");
			}		            	
    	}//Fin de if(comprueba si lo obtenido no es "null")
    	
    	else{	//json obtenido invalido verificar parte WEB.
    		Log.e("JSON  ", "ERROR");
	    	//return st;
	    }
	}//Fin de mostrarRegistroTransoperatorio
	
	//MOSTRAR REGISTRO REALIZADAS
	public void mostrarRegistroRealizada(String s) throws JSONException{	
		arrayRealizadas.clear();
		String val = "", value = "", value1 = "", value2 = "", value3 = "", value4 = "", 
				value5 = "", value6 = "", cont = "";
			
		ArrayList<NameValuePair> datosEnviar= new ArrayList<NameValuePair>();
		datosEnviar.add(new BasicNameValuePair("stat", s));

		JSONArray jdata = envio.getserverdata(datosEnviar, URL_connect9);
		//System.out.println("jdata = "+jdata.getString(0));
		System.out.println(jdata.toString());
		System.out.println("largo de jdata = "+jdata.length());
		if (jdata!=null && jdata.length() > 0){
	   		//JSONObject json_data; //creamos un objeto JSON
			try {
					
				for(int n = 0; n < jdata.length(); n++){
					//st.clear();
					System.out.println("vuelta:"+n);
					JSONObject json_data = jdata.getJSONObject(n);
					val = json_data.getString("dat");		//Fecha
					value = json_data.getString("dato");	//Hora
					value1 = json_data.getString("dato1");	//Sala
					value2 = json_data.getString("dato2");	//Paciente
					value3 = json_data.getString("dato3");	//Diagnostico
					value4 = json_data.getString("dato4");	//ID
					value5 = json_data.getString("dato5");	//Registro
					value6 = json_data.getString("dato6"); 	//Nombre del dr que programa
						
					ArrayList<String> temporary = new ArrayList<String>();
						
					temporary.add(val);
					temporary.add(value);
					temporary.add(value1);
					temporary.add(value2);
					temporary.add(value3);
					temporary.add(value4);
					temporary.add(value5);
					temporary.add(value6);
			
					Log.e("Registro realizadas", "array temporary = "+temporary);
						
					arrayRealizadas.add(temporary);
				}

				Log.e("array RegistroRealizadas", "arrayRealizadas = "+arrayRealizadas);
			
				//Log.e("enviarFormulario","status= "+status);//muestro por log que obtuvimos
			}
			catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				Log.e("hi", "hi");
			}		            	
	    }//Fin de if(comprueba si lo obtenido no es "null")
	    	
	    else{	//json obtenido invalido verificar parte WEB.
	    	Log.e("JSON  ", "ERROR");
		   	//return st;
			}
	}//Fin de mostrarRegistroRealizada
	
	/**mostrarRegistroProgramadaPasada*/
	public void mostrarRegistroProgramadaPasada(String s) throws JSONException{	
		arrayProgramadasPasadas.clear();
		String val = "", value = "", value1 = "", value2 = "", value3 = "", value4 = "", 
				value5 = "", value6 = "", cont = "";
			
		ArrayList<NameValuePair> datosEnviar= new ArrayList<NameValuePair>();
		datosEnviar.add(new BasicNameValuePair("stat", s));

		JSONArray jdata = envio.getserverdata(datosEnviar, URL_connect10);
		//System.out.println("jdata = "+jdata.getString(0));
		System.out.println(jdata.toString());
		System.out.println("largo de jdata = "+jdata.length());
		if (jdata!=null && jdata.length() > 0){
	   		//JSONObject json_data; //creamos un objeto JSON
			try {
					
				for(int n = 0; n < jdata.length(); n++){
					//st.clear();
					System.out.println("vuelta:"+n);
					JSONObject json_data = jdata.getJSONObject(n);
					val = json_data.getString("dat");		//Fecha
					value = json_data.getString("dato");	//Hora
					value1 = json_data.getString("dato1");	//Sala
					value2 = json_data.getString("dato2");	//Paciente
					value3 = json_data.getString("dato3");	//Diagnostico
					value4 = json_data.getString("dato4");	//ID
					value5 = json_data.getString("dato5");	//Registro
					value6 = json_data.getString("dato6"); 	//Nombre del dr que programa
						
					ArrayList<String> temporary = new ArrayList<String>();
						
					temporary.add(val);
					temporary.add(value);
					temporary.add(value1);
					temporary.add(value2);
					temporary.add(value3);
					temporary.add(value4);
					temporary.add(value5);
					temporary.add(value6);
			
					Log.e("Registro realizadas", "array temporary = "+temporary);
						
					arrayProgramadasPasadas.add(temporary);
				}

				Log.e("array RegistroProgramadasPasadas", "arrayProgramadasPasadas = "+arrayProgramadasPasadas);
			
				//Log.e("enviarFormulario","status= "+status);//muestro por log que obtuvimos
			}
			catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				Log.e("hi", "hi");
			}		            	
	    }//Fin de if(comprueba si lo obtenido no es "null")
	    	
	    else{	//json obtenido invalido verificar parte WEB.
	    	Log.e("JSON  ", "ERROR");
		   	//return st;
			}
	}//Fin de mostrarRegistroProgramadaPasada
	
	/** OBTENER PROCEDIMIENTOS PARA CIRUGIAS PROGRAMADAS */
	public void getProcedimientosProgramada(String s) throws JSONException{	
		arrayProcedimientosProgramada.clear();
		String val = "", value = "", value1 = "", value2 = "", value3 = "", value4 = "", 
				value5 = "", value6 = "", cont = "";
			
		ArrayList<NameValuePair> datosEnviar= new ArrayList<NameValuePair>();
		datosEnviar.add(new BasicNameValuePair("stat", s));

		JSONArray jdata = envio.getserverdata(datosEnviar, URL_connect11);
		//System.out.println("jdata = "+jdata.getString(0));
		System.out.println(jdata.toString());
		System.out.println("largo de jdata = "+jdata.length());
		if (jdata!=null && jdata.length() > 0){
	   		//JSONObject json_data; //creamos un objeto JSON
			try {
					
				for(int n = 0; n < jdata.length(); n++){
					//st.clear();
					System.out.println("vuelta:"+n);
					JSONObject json_data = jdata.getJSONObject(n);
					cont = json_data.getString("d");	//id de la cirugia
					val = json_data.getString("d0");	//id de procedimiento
					value = json_data.getString("d1");	//cie9mc
					value1 = json_data.getString("d2");	//region
					value2 = json_data.getString("d3");	//detalles
					value3 = json_data.getString("d4");	//servicio
						
					ArrayList<String> temporary = new ArrayList<String>();
						
					temporary.add(cont);
					temporary.add(val);
					temporary.add(value);
					temporary.add(value1);
					temporary.add(value2);
					temporary.add(value3);
			
					Log.e("Procedimientos programadas", "array temporary = "+temporary);
						
					arrayProcedimientosProgramada.add(temporary);
				}
				Log.e("array ProcedimientosProgramada", "arrayProcedimientosProgramada = "+arrayProcedimientosProgramada);
			}
			catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				Log.e("hi", "hi");
			}		            	
	    }//Fin de if(comprueba si lo obtenido no es "null")
	    else{	//json obtenido invalido verificar parte WEB.
	    	Log.e("JSON  ", "ERROR");
			}
	}//Fin de getProcedimientosProgramada
	
	/** OBTENER PROCEDIMIENTOS DE CIRUGIAS DIFERIDAS*/
	public void getProcedimientosDiferida(String s) throws JSONException{	
		arrayProcedimientosDiferida.clear();
		String val = "", value = "", value1 = "", value2 = "", value3 = "", cont = "";
			
		ArrayList<NameValuePair> datosEnviar= new ArrayList<NameValuePair>();
		datosEnviar.add(new BasicNameValuePair("stat", s));

		JSONArray jdata = envio.getserverdata(datosEnviar, URL_connect12);
		//System.out.println("jdata = "+jdata.getString(0));
		System.out.println(jdata.toString());
		System.out.println("largo de jdata = "+jdata.length());
		if (jdata!=null && jdata.length() > 0){
	   		//JSONObject json_data; //creamos un objeto JSON
			try {
					
				for(int n = 0; n < jdata.length(); n++){
					//st.clear();
					System.out.println("vuelta:"+n);
					JSONObject json_data = jdata.getJSONObject(n);
					cont = json_data.getString("d");	//id de la cirugia
					val = json_data.getString("d0");	//id de procedimiento
					value = json_data.getString("d1");	//cie9mc
					value1 = json_data.getString("d2");	//region
					value2 = json_data.getString("d3");	//detalles
					value3 = json_data.getString("d4");	//servicio
						
					ArrayList<String> temporary = new ArrayList<String>();
						
					temporary.add(cont);
					temporary.add(val);
					temporary.add(value);
					temporary.add(value1);
					temporary.add(value2);
					temporary.add(value3);
			
					Log.e("Tempo-dif", "tempo-dif = "+temporary);
						
					arrayProcedimientosDiferida.add(temporary);
				}

				Log.e("array Procedimientos Diferida", "arrayProcedimientosDiferida = "+arrayProcedimientosDiferida);
			
				//Log.e("enviarFormulario","status= "+status);//muestro por log que obtuvimos
			}
			catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				Log.e("hi", "hi");
			}		            	
	    }//Fin de if(comprueba si lo obtenido no es "null")
	    	
	    else{	//json obtenido invalido verificar parte WEB.
	    	Log.e("JSON  ", "ERROR");
		   	//return st;
			}
	}//Fin de getProcedimientosDiferida
	
	/** OBTENER PROCEDIMIENTOS DE PROGRAMADAS CON FECHA PASADA*/
	public void getProcedimientosProgramadaPasada(String s) throws JSONException{	
		arrayProcedimientosProgramadaPasada.clear();
		String val = "", value = "", value1 = "", value2 = "", value3 = "", cont = "";
			
		ArrayList<NameValuePair> datosEnviar= new ArrayList<NameValuePair>();
		datosEnviar.add(new BasicNameValuePair("stat", s));

		JSONArray jdata = envio.getserverdata(datosEnviar, URL_connect15);
		//System.out.println("jdata = "+jdata.getString(0));
		System.out.println(jdata.toString());
		System.out.println("largo de jdata = "+jdata.length());
		if (jdata!=null && jdata.length() > 0){
	   		//JSONObject json_data; //creamos un objeto JSON
			try {
					
				for(int n = 0; n < jdata.length(); n++){
					//st.clear();
					System.out.println("vuelta:"+n);
					JSONObject json_data = jdata.getJSONObject(n);
					cont = json_data.getString("d");	//id de la cirugia
					val = json_data.getString("d0");	//id de procedimiento
					value = json_data.getString("d1");	//cie9mc
					value1 = json_data.getString("d2");	//region
					value2 = json_data.getString("d3");	//detalles
					value3 = json_data.getString("d4");	//servicio
						
					ArrayList<String> temporary = new ArrayList<String>();
						
					temporary.add(cont);
					temporary.add(val);
					temporary.add(value);
					temporary.add(value1);
					temporary.add(value2);
					temporary.add(value3);
			
					Log.e("Tempo-pasada", "tempo-pasada = "+temporary);
						
					arrayProcedimientosProgramadaPasada.add(temporary);
				}

				Log.e("array ProcedimientosPasadas", "arrayProcedimientosPasada = "+arrayProcedimientosProgramadaPasada);
			}
			catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				Log.e("hi", "hi");
			}		            	
	    }//Fin de if(comprueba si lo obtenido no es "null")
	    	
	    else{	//json obtenido invalido verificar parte WEB.
	    	Log.e("JSON  ", "ERROR");
		   	//return st;
			}
	}//Fin de getProcedimientosProgramadaPasada
	
	/** OBTENER PROCEDIMIENTOS DE CIRUGIAS EN TRANSOPERATORIO*/
	public void getProcedimientosTransoperatorio(String s) throws JSONException{	
		arrayProcedimientosTransoperatorio.clear();
		String val = "", value = "", value1 = "", value2 = "", value3 = "", cont = "";
			
		ArrayList<NameValuePair> datosEnviar= new ArrayList<NameValuePair>();
		datosEnviar.add(new BasicNameValuePair("stat", s));

		JSONArray jdata = envio.getserverdata(datosEnviar, URL_connect13);
		//System.out.println("jdata = "+jdata.getString(0));
		System.out.println(jdata.toString());
		System.out.println("largo de jdata = "+jdata.length());
		if (jdata!=null && jdata.length() > 0){
	   		//JSONObject json_data; //creamos un objeto JSON
			try {
					
				for(int n = 0; n < jdata.length(); n++){
					//st.clear();
					System.out.println("vuelta:"+n);
					JSONObject json_data = jdata.getJSONObject(n);
					cont = json_data.getString("d");	//id de la cirugia
					val = json_data.getString("d0");	//id de procedimiento
					value = json_data.getString("d1");	//cie9mc
					value1 = json_data.getString("d2");	//region
					value2 = json_data.getString("d3");	//detalles
					value3 = json_data.getString("d4");	//servicio
						
					ArrayList<String> temporary = new ArrayList<String>();
						
					temporary.add(cont);
					temporary.add(val);
					temporary.add(value);
					temporary.add(value1);
					temporary.add(value2);
					temporary.add(value3);
			
					Log.e("Tempo-transoperatorio", "tempo-transoperatorio = "+temporary);
						
					arrayProcedimientosTransoperatorio.add(temporary);
				}
				Log.e("array arrayProcedimientosTransoperatorio", "arrayProcedimientosTransoperatorio = "+arrayProcedimientosTransoperatorio);
			}
			catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				Log.e("hi", "hi");
			}		            	
	    }//Fin de if(comprueba si lo obtenido no es "null")
	    	
	    else{	//json obtenido invalido verificar parte WEB.
	    	Log.e("JSON  ", "ERROR");
		   	//return st;
			}
	}//Fin de getProcedimientosTransoperatorio
	
	/** OBTENER PROCEDIMIENTOS DE CIRUGIAS EN TRANSOPERATORIO*/
	public void getProcedimientosRealizada(String s) throws JSONException{	
		arrayProcedimientosRealizada.clear();
		String val = "", value = "", value1 = "", value2 = "", value3 = "", cont = "";
			
		ArrayList<NameValuePair> datosEnviar= new ArrayList<NameValuePair>();
		datosEnviar.add(new BasicNameValuePair("stat", s));

		JSONArray jdata = envio.getserverdata(datosEnviar, URL_connect14);
		//System.out.println("jdata = "+jdata.getString(0));
		System.out.println(jdata.toString());
		System.out.println("largo de jdata = "+jdata.length());
		if (jdata!=null && jdata.length() > 0){
	   		//JSONObject json_data; //creamos un objeto JSON
			try {
					
				for(int n = 0; n < jdata.length(); n++){
					//st.clear();
					System.out.println("vuelta:"+n);
					JSONObject json_data = jdata.getJSONObject(n);
					cont = json_data.getString("d");	//id de la cirugia
					val = json_data.getString("d0");	//id de procedimiento
					value = json_data.getString("d1");	//cie9mc
					value1 = json_data.getString("d2");	//region
					value2 = json_data.getString("d3");	//detalles
					value3 = json_data.getString("d4");	//servicio
						
					ArrayList<String> temporary = new ArrayList<String>();
						
					temporary.add(cont);
					temporary.add(val);
					temporary.add(value);
					temporary.add(value1);
					temporary.add(value2);
					temporary.add(value3);

					Log.e("Tempo-proc-realizada", "tempo-proc-realizada = "+temporary);

					arrayProcedimientosRealizada.add(temporary);
				}

				Log.e("array arrayProcedimientosRealizada", "arrayProcedimientosRealizada = "+arrayProcedimientosRealizada);
			
				//Log.e("enviarFormulario","status= "+status);//muestro por log que obtuvimos
			}
			catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				Log.e("hi", "hi");
			}		            	
	    }//Fin de if(comprueba si lo obtenido no es "null")	    	
	    else{	//json obtenido invalido verificar parte WEB.
	    	Log.e("JSON  ", "ERROR");
		   	//return st;
			}
	}//Fin de getProcedimientosRealizada
	
	//public void getSalas(String quir_id){
	public ArrayList<String> getSalas(String quir_id){
		salasArray.clear();
		ArrayList<String> salasTemporal = new ArrayList<String>();
		String qId;
		String value;
		String value1;
		String value2;
		String value3;
		
		ArrayList<NameValuePair> datosEnviar= new ArrayList<NameValuePair>();
		datosEnviar.add(new BasicNameValuePair("quirofano_id",quir_id));
		
		JSONArray jdata=post.getserverdata(datosEnviar, URL_connect4);
		
		if (jdata!=null && jdata.length() > 0){
			try {
				
				for(int n = 0; n < jdata.length(); n++){
					//st.clear();
					System.out.println("vuelta:"+n);
					JSONObject json_data = jdata.getJSONObject(n);
					value = json_data.getString("dato");	//Obtiene el id de la sala
					value1 = json_data.getString("dato1");	//Obtiene el nombre de la misma sala
					value2 = json_data.getString("dato2");	//Obtiene el campo activo de la sala
					value3 = json_data.getString("dato3");	//Obtiene el campo bloqueado de la sala
					
					ArrayList<String> temporary = new ArrayList<String>();
					salasTemporal.add(value1);
					//nombreSalas.add(value1);
					temporary.add(value);
					temporary.add(value1);
					temporary.add(value2);
					temporary.add(value3);
					
					Log.e("log-st", "array temprary = "+temporary);
					
					salasArray.add(temporary);
					//st.clear();
				}
				
				//return salasTemporal;
				Log.e("salasArray", "salasArray = "+salasArray);
				Log.e("salasTemporal", "salasTemporal = "+salasTemporal);
			}
			catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				Log.e("hi", "hi");
			}		            			
    	}//Fin de if(comprueba si lo obtenido no es "null")
    	else{	//json obtenido invalido verificar parte WEB.
    		Log.e("JSON getQuirofanoId  ", "ERROR");
	    	//return false;
	    }//Fin de else
		return salasTemporal;
	}//Fin de getSalas
	
	public boolean enviarProcedimientos(String registro){
		
		int status = -1;
		
		ArrayList<NameValuePair> datosEnviar= new ArrayList<NameValuePair>();
		
		//Luego enviamos el id del registro con que programamos la cirugia principal
		System.out.println("REGISTROOOOOOOOOO_ID = "+registro);
		datosEnviar.add(new BasicNameValuePair("numRegistro", registro));
		
		//procedimientos: ArrayList de ArrayList- lleva cada procedimiento y dentro cada campo de formulario
		/*Orden: servicio, region, procedimientos, detalles*/
		for (int i=0;i<procedimientos.size();i++){
			//for (int j=0; j<4; j++){
				//datosEnviar.add(new BasicNameValuePair("info"+i+j,procedimientos.get(i).get(j)));	
			datosEnviar.add(new BasicNameValuePair("servicio"+i,procedimientos.get(i).get(0)));
			datosEnviar.add(new BasicNameValuePair("region"+i,procedimientos.get(i).get(1)));
			datosEnviar.add(new BasicNameValuePair("procedimiento"+i,procedimientos.get(i).get(2)));
			datosEnviar.add(new BasicNameValuePair("detalles"+i,procedimientos.get(i).get(3)));
		}
		System.out.println("PROCEDIMIENTOS A ENVIAR = "+datosEnviar);
		//String largo = Integer.toString(datosEnviar.size());
		
		JSONArray jdata = envio.getserverdata(datosEnviar, URL_connect5); 
		
		//si lo que obtuvimos no es null
    	if (jdata!=null && jdata.length() > 0){
    		JSONObject json_data; //creamos un objeto JSON
			try {
				json_data = jdata.getJSONObject(0); //leemos el primer segmento en nuestro caso el unico
//				status=json_data.getInt("logstatus");//accedemos al valor
				status=json_data.getInt("respuesta");//acceder al valor 1 o 0
				Log.e("enviarProcedimientos","status= "+status);//muestro por log que obtuvimos
			}
			catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		            
             
			//validamos el valor obtenido
    		if (status==0){// [{"logstatus":"0"}] 
    			Log.e("programacion-procedimientos ", "procedimientos fallidos");
    			procedimientos.clear();
    			return false;
    		}
    		else{// [{"logstatus":"1"}]
    			Log.e("programacion-procedimientos ", "procedimientos exitosos");
    			procedimientos.clear();
    			return true;
    		}
    		 
    	}//Fin de if(comprueba si lo obtenido no es "null")
    	
    	else{	//json obtenido invalido verificar parte WEB.
    		Log.e("JSON  ", "ERROR");
	    	return false;
	    }//Fin de else
	}//Fin de enviarProcedimientos
	
	public void mostrarAgendaDelDia(String s) throws JSONException{	
		//ArrayList<String> st = new ArrayList<String>();
		registroDelDia.clear();
		String val = "";
		String value = "";
		String value1 = "";
		String value2 = "";
		String value3 = "";
		String cont = "";
		
		ArrayList<NameValuePair> datosEnviar= new ArrayList<NameValuePair>();
		datosEnviar.add(new BasicNameValuePair("quirofano", s));
		
		//SystemClock.sleep(4450);
		//JSONObject json_objeto;
		JSONArray jdata = send.getserverdata(datosEnviar, URL_connect6);
		//System.out.println("jdata = "+jdata.getString(0));
		//System.out.println(jdata.toString());
		//System.out.println("largo de jdata = "+jdata.length());
		if (jdata!=null && jdata.length() > 0){
    		//JSONObject json_data; //creamos un objeto JSON
			try {
				
				for(int n = 0; n < jdata.length(); n++){
					//st.clear();
					System.out.println("vuelta:"+n);
					JSONObject json_data = jdata.getJSONObject(n);
					val = json_data.getString("dat");
					value = json_data.getString("dato");
					value1 = json_data.getString("dato1");
					value2 = json_data.getString("dato2");
					value3 = json_data.getString("dato3");
					
					ArrayList<String> temporary = new ArrayList<String>();
					
					temporary.add(val);
					temporary.add(value);
					temporary.add(value1);
					temporary.add(value2);
					temporary.add(value3);
					
					Log.e("log-st", "array temporary = "+temporary);
					
					registroDelDia.add(temporary);
					//st.clear();
				}

				Log.e("array registroDelDia", "registroDelDia = "+registroDelDia);

			}
			catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				Log.e("hi", "hi");
			}		            
			
    	}//Fin de if(comprueba si lo obtenido no es "null")
    	
    	else{	//json obtenido invalido verificar parte WEB.
    		Log.e("JSON  ", "ERROR");
	    }
	}//Fin de mostrarAgendaDelDia
	
	//ESTE METODO YA NO SE USA :v
	public void getQuirofanoId(String qui){
		
		String qId;
		
		ArrayList<NameValuePair> datosEnviar= new ArrayList<NameValuePair>();
		datosEnviar.add(new BasicNameValuePair("quirofano_name",qui));
		
		JSONArray jdata=post.getserverdata(datosEnviar, URL_connect3);
  		
  		//SystemClock.sleep(1450);
  		  		
  		//si lo que obtuvimos no es null
    	if (jdata!=null && jdata.length() > 0){
    		JSONObject json_data; //creamos un objeto JSON
			try {
				json_data = jdata.getJSONObject(0); //leemos el primer segmento en nuestro caso el unico
				//status=json_data.getInt("logstatus");//accedemos al valor
				qId = json_data.getString("quirofano_id");
				Log.e("getQuirofanoId","status= "+qId);//muestro por log que obtuvimos
				
				quirofano_id = qId;
			}
			catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		            
             
			//validamos el valor obtenido
    		//if (qId.equals(null)){// [{"logstatus":"0"}] 
    		//	Log.e("getQuirofanoId ", "no existe ese quirofano_id");
    		//	return qId;
    		//}
    		//else{// [{"logstatus":"1"}]
    		//	Log.e("loginstatus ", "envio exitoso");
    		//	return true;
    		//}
    		 
    	}//Fin de if(comprueba si lo obtenido no es "null")
    	
    	else{	//json obtenido invalido verificar parte WEB.
    		Log.e("JSON getQuirofanoId  ", "ERROR");
	    	//return false;
	    }//Fin de else
		
		
	}//Fin de getQuirofanoId
	
	public void error1(){
    	Vibrator vibrator =(Vibrator) getActivity().getSystemService(Context.VIBRATOR_SERVICE);
	    vibrator.vibrate(200);
	    Toast toast1 = Toast.makeText(getActivity().getApplicationContext(),"Error:Favor de llenar todos los datos", Toast.LENGTH_SHORT);
 	    toast1.show();    	
    }
	
	public void error2(){
		Vibrator vibrator = (Vibrator) getActivity().getSystemService(Context.VIBRATOR_SERVICE);
		vibrator.vibrate(200);
		Toast t = Toast.makeText(getActivity().getApplicationContext(), "Error: error en el envio de datos", Toast.LENGTH_SHORT);
		t.show();
	}
	
	public void error3(){
		Vibrator vibrator = (Vibrator) getActivity().getSystemService(Context.VIBRATOR_SERVICE);
		vibrator.vibrate(200);
		Toast t = Toast.makeText(getActivity().getApplicationContext(), "Error: la fecha programada debe ser mayor a la fecha actual", Toast.LENGTH_SHORT);
		t.show();
	}
	
	public void error4(){
		Vibrator vibrator = (Vibrator) getActivity().getSystemService(Context.VIBRATOR_SERVICE);
		vibrator.vibrate(200);
		Toast t = Toast.makeText(getActivity().getApplicationContext(), "Error: la fecha programada no debe ser mayor a 30 días", Toast.LENGTH_SHORT);
		t.show();
	}
	
	public void error5(String result){
		Vibrator vibrator = (Vibrator) getActivity().getSystemService(Context.VIBRATOR_SERVICE);
		vibrator.vibrate(200);
		Toast t = Toast.makeText(getActivity().getApplicationContext(), "Error: la fecha y hora se empalman con otra cirugía: "+result.substring(1, (result.length())), Toast.LENGTH_SHORT);
		t.show();
	}
	
	public void mostrarLeyenda(){
		Toast t = Toast.makeText(getActivity().getApplicationContext(), "Cirugia programada con éxito", Toast.LENGTH_SHORT);
		t.show();
	}
	
	/** *********************************************************************************
	 * 		METODO PARA TRAER CIRUGIAS Y PROCEDIMIENTOS DEL DIA
	 * */
	public void mostrarAgendaActual(String s) throws JSONException{	
		arrayProgramadasActual.clear();
		arrayDiferidasActual.clear();
		arrayTransoperatorioActual.clear();
		arrayRealizadasActual.clear();
		//PROCEDIMIENTOS
		procedimientosProgramadaActual.clear();
		procedimientosDiferidaActual.clear();
		procedimientosTransoperatorioActual.clear();
		procedimientosRealizadaActual.clear();
		
		String val = "", value = "", value1 = "", value2 = "", value3 = "", value4 = "", value5 = "", value6 = "", cont = "";
		
		// REGISTROS Y PROCEDIMIENTO DE LAS CIRUGIAS PROGRAMADAS ACTUALES -----------------------------------------
		
		ArrayList<NameValuePair> datosEnviar= new ArrayList<NameValuePair>();
		datosEnviar.add(new BasicNameValuePair("stat", s));

		JSONArray jdata = envio.getserverdata(datosEnviar, URL_connect16);

		if (jdata!=null && jdata.length() > 0){
			try {
				
				for(int n = 0; n < jdata.length(); n++){
					
					JSONObject json_data = jdata.getJSONObject(n);
					val = json_data.getString("dat");		//Fecha
					value = json_data.getString("dato");	//Hora
					value1 = json_data.getString("dato1");	//Sala
					value2 = json_data.getString("dato2");	//Paciente
					value3 = json_data.getString("dato3");	//Diagnostico
					value4 = json_data.getString("dato4");	//ID
					value5 = json_data.getString("dato5");	//Registro
					value6 = json_data.getString("dato6"); 	//Nombre del dr que programa
					
					ArrayList<String> temporary = new ArrayList<String>();
					
					temporary.add(val);
					temporary.add(value);
					temporary.add(value1);
					temporary.add(value2);
					temporary.add(value3);
					temporary.add(value4);
					temporary.add(value5);
					temporary.add(value6);
										
					arrayProgramadasActual.add(temporary);
				}//fin de for			
			}//fin de try
			catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				Log.e("hi", "hi");
			}		            			
    	}//Fin de if(comprueba si lo obtenido no es "null")
    	
    	else{	//json obtenido invalido verificar parte WEB.
    		Log.e("JSON  ", "ERROR");
	    }
		
		
		ArrayList<NameValuePair> datosEnviar2= new ArrayList<NameValuePair>();
		datosEnviar2.add(new BasicNameValuePair("stat", s));

		JSONArray jdata2 = envio.getserverdata(datosEnviar2, URL_connect20);

		if (jdata2!=null && jdata2.length() > 0){
			try {
				
				for(int n = 0; n < jdata2.length(); n++){
					
					JSONObject json_data = jdata2.getJSONObject(n);
					cont = json_data.getString("d");	//id de la cirugia
					val = json_data.getString("d0");	//id de procedimiento
					value = json_data.getString("d1");	//cie9mc
					value1 = json_data.getString("d2");	//region
					value2 = json_data.getString("d3");	//detalles
					value3 = json_data.getString("d4");	//servicio
					
					ArrayList<String> temporary = new ArrayList<String>();
					
					temporary.add(cont);
					temporary.add(val);
					temporary.add(value);
					temporary.add(value1);
					temporary.add(value2);
					temporary.add(value3);
										
					procedimientosProgramadaActual.add(temporary);
				}//fin de for			
			}//fin de try
			catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				Log.e("hi", "hi");
			}		            			
    	}//Fin de if(comprueba si lo obtenido no es "null")
    	
    	else{	//json obtenido invalido verificar parte WEB.
    		Log.e("JSON  ", "ERROR");
	    }
		
		//FIN DE PROCEDIMIENTO DE LAS CIRUGIAS PROGRAMADAS ACTUALES------------------------
		
		// REGISTROS Y PROCEDIMIENTO DE LAS CIRUGIAS DIFERIDAS ACTUALES -----------------------------------------
		
			ArrayList<NameValuePair> datosEnviar3= new ArrayList<NameValuePair>();
			datosEnviar3.add(new BasicNameValuePair("stat", s));

			JSONArray jdata3 = envio.getserverdata(datosEnviar3, URL_connect17);

			if (jdata3!=null && jdata3.length() > 0){
				try {
						
					for(int n = 0; n < jdata3.length(); n++){
							
						JSONObject json_data = jdata3.getJSONObject(n);
						val = json_data.getString("dat");		//Fecha
						value = json_data.getString("dato");	//Hora
						value1 = json_data.getString("dato1");	//Sala
						value2 = json_data.getString("dato2");	//Paciente
						value3 = json_data.getString("dato3");	//Diagnostico
						value4 = json_data.getString("dato4");	//ID
						value5 = json_data.getString("dato5");	//Registro
						value6 = json_data.getString("dato6"); 	//Nombre del dr que programa
							
						ArrayList<String> temporary = new ArrayList<String>();
							
						temporary.add(val);
						temporary.add(value);
						temporary.add(value1);
						temporary.add(value2);
						temporary.add(value3);
						temporary.add(value4);
						temporary.add(value5);
						temporary.add(value6);
												
						arrayDiferidasActual.add(temporary);
					}//fin de for			
				}//fin de try
				catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					Log.e("hi", "hi");
				}		            			
			}//Fin de if(comprueba si lo obtenido no es "null")
		    	
		    else{	//json obtenido invalido verificar parte WEB.
		    	Log.e("JSON  ", "ERROR");
			}
				
				
			ArrayList<NameValuePair> datosEnviar4= new ArrayList<NameValuePair>();
			datosEnviar4.add(new BasicNameValuePair("stat", s));
			JSONArray jdata4 = envio.getserverdata(datosEnviar4, URL_connect21);

			if (jdata4!=null && jdata4.length() > 0){
				try {
						
					for(int n = 0; n < jdata4.length(); n++){
							
						JSONObject json_data = jdata4.getJSONObject(n);
						cont = json_data.getString("d");	//id de la cirugia
						val = json_data.getString("d0");	//id de procedimiento
						value = json_data.getString("d1");	//cie9mc
						value1 = json_data.getString("d2");	//region
						value2 = json_data.getString("d3");	//detalles
						value3 = json_data.getString("d4");	//servicio
							
						ArrayList<String> temporary = new ArrayList<String>();
							
						temporary.add(cont);
						temporary.add(val);
						temporary.add(value);
						temporary.add(value1);
						temporary.add(value2);
						temporary.add(value3);
											
						procedimientosDiferidaActual.add(temporary);
					}//fin de for			
				}//fin de try
				catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					Log.e("hi", "hi");
				}		            			
	    	}//Fin de if(comprueba si lo obtenido no es "null")
		    	
	    	else{	//json obtenido invalido verificar parte WEB.
	    		Log.e("JSON  ", "ERROR");
		    }
				
		//FIN DE PROCEDIMIENTO DE LAS CIRUGIAS DIFERIDAS ACTUALES------------------------
			
		// REGISTROS Y PROCEDIMIENTO DE LAS CIRUGIAS EN TRANSOPERATORIO ACTUALES ---------
		
			ArrayList<NameValuePair> datosEnviar5= new ArrayList<NameValuePair>();
			datosEnviar5.add(new BasicNameValuePair("stat", s));

			JSONArray jdata5 = envio.getserverdata(datosEnviar5, URL_connect18);

			if (jdata5!=null && jdata5.length() > 0){
				try {
						
					for(int n = 0; n < jdata5.length(); n++){
							
						JSONObject json_data = jdata5.getJSONObject(n);
						val = json_data.getString("dat");		//Fecha
						value = json_data.getString("dato");	//Hora
						value1 = json_data.getString("dato1");	//Sala
						value2 = json_data.getString("dato2");	//Paciente
						value3 = json_data.getString("dato3");	//Diagnostico
						value4 = json_data.getString("dato4");	//ID
						value5 = json_data.getString("dato5");	//Registro
						value6 = json_data.getString("dato6"); 	//Nombre del dr que programa
							
						ArrayList<String> temporary = new ArrayList<String>();
							
						temporary.add(val);
						temporary.add(value);
						temporary.add(value1);
						temporary.add(value2);
						temporary.add(value3);
						temporary.add(value4);
						temporary.add(value5);
						temporary.add(value6);
												
						arrayTransoperatorioActual.add(temporary);
					}//fin de for			
				}//fin de try
				catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					Log.e("hi", "hi");
				}		            			
			}//Fin de if(comprueba si lo obtenido no es "null")
		    	
		    else{	//json obtenido invalido verificar parte WEB.
		    	Log.e("JSON  ", "ERROR");
			}
				
				
			ArrayList<NameValuePair> datosEnviar6= new ArrayList<NameValuePair>();
			datosEnviar6.add(new BasicNameValuePair("stat", s));
			JSONArray jdata6 = envio.getserverdata(datosEnviar6, URL_connect22);

			if (jdata6!=null && jdata6.length() > 0){
				try {
						
					for(int n = 0; n < jdata6.length(); n++){
							
						JSONObject json_data = jdata6.getJSONObject(n);
						cont = json_data.getString("d");	//id de la cirugia
						val = json_data.getString("d0");	//id de procedimiento
						value = json_data.getString("d1");	//cie9mc
						value1 = json_data.getString("d2");	//region
						value2 = json_data.getString("d3");	//detalles
						value3 = json_data.getString("d4");	//servicio
							
						ArrayList<String> temporary = new ArrayList<String>();
							
						temporary.add(cont);
						temporary.add(val);
						temporary.add(value);
						temporary.add(value1);
						temporary.add(value2);
						temporary.add(value3);
											
						procedimientosTransoperatorioActual.add(temporary);
					}//fin de for			
				}//fin de try
				catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					Log.e("hi", "hi");
				}		            			
	    	}//Fin de if(comprueba si lo obtenido no es "null")
		    	
	    	else{	//json obtenido invalido verificar parte WEB.
	    		Log.e("JSON  ", "ERROR");
		    }
				
		//FIN DE PROCEDIMIENTO DE LAS CIRUGIAS EN TRANSOPERATORIO ACTUALES------------------------
		
		// REGISTROS Y PROCEDIMIENTO DE LAS CIRUGIAS REALIZADAS ACTUALES ---------
		
			ArrayList<NameValuePair> datosEnviar7= new ArrayList<NameValuePair>();
			datosEnviar7.add(new BasicNameValuePair("stat", s));

			JSONArray jdata7 = envio.getserverdata(datosEnviar7, URL_connect19);

			if (jdata7!=null && jdata7.length() > 0){
				try {
						
					for(int n = 0; n < jdata7.length(); n++){
							
						JSONObject json_data = jdata7.getJSONObject(n);
						val = json_data.getString("dat");		//Fecha
						value = json_data.getString("dato");	//Hora
						value1 = json_data.getString("dato1");	//Sala
						value2 = json_data.getString("dato2");	//Paciente
						value3 = json_data.getString("dato3");	//Diagnostico
						value4 = json_data.getString("dato4");	//ID
						value5 = json_data.getString("dato5");	//Registro
						value6 = json_data.getString("dato6"); 	//Nombre del dr que programa
							
						ArrayList<String> temporary = new ArrayList<String>();
							
						temporary.add(val);
						temporary.add(value);
						temporary.add(value1);
						temporary.add(value2);
						temporary.add(value3);
						temporary.add(value4);
						temporary.add(value5);
						temporary.add(value6);
												
						arrayRealizadasActual.add(temporary);
					}//fin de for			
				}//fin de try
				catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					Log.e("hi", "hi");
				}		            			
			}//Fin de if(comprueba si lo obtenido no es "null")
		    	
		    else{	//json obtenido invalido verificar parte WEB.
		    	Log.e("JSON  ", "ERROR");
			}
				
				
			ArrayList<NameValuePair> datosEnviar8= new ArrayList<NameValuePair>();
			datosEnviar8.add(new BasicNameValuePair("stat", s));
			JSONArray jdata8 = envio.getserverdata(datosEnviar8, URL_connect23);

			if (jdata8!=null && jdata8.length() > 0){
				try {
						
					for(int n = 0; n < jdata8.length(); n++){
							
						JSONObject json_data = jdata8.getJSONObject(n);
						cont = json_data.getString("d");	//id de la cirugia
						val = json_data.getString("d0");	//id de procedimiento
						value = json_data.getString("d1");	//cie9mc
						value1 = json_data.getString("d2");	//region
						value2 = json_data.getString("d3");	//detalles
						value3 = json_data.getString("d4");	//servicio
							
						ArrayList<String> temporary = new ArrayList<String>();
							
						temporary.add(cont);
						temporary.add(val);
						temporary.add(value);
						temporary.add(value1);
						temporary.add(value2);
						temporary.add(value3);
											
						procedimientosRealizadaActual.add(temporary);
					}//fin de for			
				}//fin de try
				catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					Log.e("hi", "hi");
				}		            			
	    	}//Fin de if(comprueba si lo obtenido no es "null")
		    	
	    	else{	//json obtenido invalido verificar parte WEB.
	    		Log.e("JSON  ", "ERROR");
		    }
				
		//FIN DE PROCEDIMIENTO DE LAS CIRUGIAS REALIZADAS ACTUALES------------------------
			
		
	}//Fin de mostrarAgendaActual
	
	//Traer los servicios del server, para llenar el spinner de programar cirugia
	public ArrayList<String> getServicios(String quir_id){
		arrayServicios.clear();
		
		ArrayList<String> serviciosTemporal = new ArrayList<String>();
		
		String val, value;
		
		ArrayList<NameValuePair> datosEnviar= new ArrayList<NameValuePair>();
		datosEnviar.add(new BasicNameValuePair("quirofano_id",quir_id));
		
		JSONArray jdata=post.getserverdata(datosEnviar, URL_connect24);
		
		if (jdata!=null && jdata.length() > 0){
		
			try {
				
				for(int n = 0; n < jdata.length(); n++){

					JSONObject json_data = jdata.getJSONObject(n);
					val = json_data.getString("dat");		//Obtiene el id de los servicios
					value = json_data.getString("dato");	//Obtiene los nombres de los servicios
					
					ArrayList<String> temporary = new ArrayList<String>();
					temporary.add(val);
					temporary.add(value);
					
					arrayServicios.add(temporary);
					
					serviciosTemporal.add(value);
				}
			}
			catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				Log.e("hi", "hi");
			}		            			
    	}//Fin de if(comprueba si lo obtenido no es "null")
    	
    	else{	//json obtenido invalido verificar parte WEB.
    		Log.e("JSON getQuirofanoId  ", "ERROR");
	    	//return false;
	    }//Fin de else
		return serviciosTemporal;
	}//Fin de getServicios
	
	/** **********************************************************************************/

	/*Uso de Asnyctask - [Documentacion]*/
	/*2 subclases apartir de aqui: Formulario - Agenda*/
	
	class Formulario extends AsyncTask< String, String, String > {
		
		LayoutInflater inflater;
		ViewGroup container;
		ScrollView sv;
		View home;
		
		Formulario(LayoutInflater inflater, ViewGroup container, ScrollView sv, View home){
			this.inflater = inflater;
			this.container = container;
			this.sv = sv;
			this.home = home;
		}
   	 
    	//String date, hora, reg, paciente;
    	String date, hora, reg, paciente, sEdad, sGenero, sProcedencia, sDiagnostico, sMedico,
    	sRiesgoQuirurgico, sInsumosIndispensables, sRequerimientos, sHemoderivados, sRequisitos,
    	sNecesidades;
    	
    	String status; 
    	
    	//Seran convertidos a enteros, excepto duracion, sera convertido a TIME
    	String sSala, sDuracion, sProgramacion, sServicio, sAtencion, sP, sR;
    	
    	protected void onPreExecute() {
    		progress = ProgressDialog.show(
    		getActivity(), null, "Programando cirugía ...");
            super.onPreExecute();
        }
    	
        protected String doInBackground(String... params) {
			//obtnemos datos
			date=params[0];						//Para date
			hora=params[1];						//Para hora
			reg=params[2];  					//Para registro
			paciente=params[3];					//Para nombre del paciente
			sEdad=params[4];					//Para edad del paciente
			sGenero=params[5];					//Para genero del paciente
			sProcedencia=params[6];				//Para procedencia
			sDiagnostico=params[7];				//Para diagnostico
			sMedico=params[8];					//Para nombre del medico
			sRiesgoQuirurgico=params[9];		//Para riesgo quirurgico
			sInsumosIndispensables=params[10];  //Para insumos indispensables
			sRequerimientos=params[11];			//Para requerimientos de anestesiologia
			sHemoderivados=params[12];			//Para hemoderivados necesarios
			sRequisitos=params[13];				//Para requisitos de laboratorio
			sNecesidades=params[14];			//Para otras necesidades
			
			sSala = params[15];
			sDuracion = params[16];
			sProgramacion = params[17];
			sServicio = params[18];
			sAtencion = params[19];
			sP = params[20];
			sR = params[21];	
			
			status = enviarFormulario(date, hora, reg, paciente, sEdad, sGenero, sProcedencia,
    				sDiagnostico, sMedico, sRiesgoQuirurgico, sInsumosIndispensables, sRequerimientos,
    				sHemoderivados, sRequisitos, sNecesidades, sSala, sDuracion, sProgramacion,
    				sServicio, sAtencion, sP , sR);
			
			return status;
			
		}//Fin de doInBackground
        
        protected void onPostExecute(String result) {
        	progress.dismiss();//ocultamos progess dialog.
            Log.e("onPostExecute=",""+result);
            String sub = result.substring(0,1);
            System.out.println("SUBSTRING: "+sub);
            //int intResult = Integer.parseInt(result);
            
            if (result.equals("error1")) {
             	error3();
//            	System.out.println("ERROR EN PROGRAMACION DE CIRUGIA!!");
            }
            else if (result.equals("error2")) {
             	error4();
//            	System.out.println("ERROR EN PROGRAMACION DE CIRUGIA!!");
            }
            else if (result.equals("error")) {
             	error2();
            	System.out.println("ERROR EN PROGRAMACION DE CIRUGIA!!");
            }
            else if (sub.equals("w")){
            	error5(result);
            }
            else {
            	new EnviarProcedimientos(inflater, container, sv, home).execute(registroID);
            }
        }//Fin de onPostExecute        
	}//Fin de la subclase Formulario
	
	class EnviarProcedimientos extends AsyncTask< String, String, String> {
		
		LayoutInflater inflater;
		ViewGroup container;
		ScrollView sv;
		View home;
		
		EnviarProcedimientos(LayoutInflater inflater, ViewGroup container, ScrollView sv, View home){
			this.inflater = inflater;
			this.container = container;
			this.sv = sv;
			this.home = home;
		}
		
		String registro; //lleva el identificador del registro programado para programar los
		//procedimientos en la tabla: siga_procedimientos
		
    	protected void onPreExecute() {
    		//progress = ProgressDialog.show(
    		//getActivity(), null, "Accesando a agenda...");
            super.onPreExecute();
        }
    	
        protected String doInBackground(String... params) {
			registro=params[0]; //obtenemos el string de quirofano_name 
			
			if (enviarProcedimientos(registro) == true){
	    		return "ok"; 
			}
			else return "error";
    		
		}//Fin de doInBackground
       
        protected void onPostExecute(String resultado) {
        	//progress.dismiss();//ocultamos progess dialog.
        	if (resultado == "ok"){
	            Log.e("EnviarProcedimientos=","Todo bien="+resultado);
	            mostrarLeyenda();
	            
	            sv.removeAllViews();
				tl.removeAllViews();
				TableRow tr = (TableRow) inflater.inflate(R.layout.tablerow, container, false);
				tl.addView(tr);
				//new Agenda(inflater, container).execute(ID_quirofano);
				new GetProcedimientosProgramada().execute(ID_quirofano);
				new GetProcedimientosDiferida().execute(ID_quirofano);
				new GetProcedimientosTransoperatorio().execute(ID_quirofano);
				new GetProcedimientosRealizada().execute(ID_quirofano);
				new GetProcedimientosProgramadaPasada().execute(ID_quirofano);
				
				//Importa el orden
				new RegistroDiferida2(inflater, container).execute(ID_quirofano);			//OBTENER CIRUGAS DIFERIDAS
				new RegistroTransoperatorio(inflater, container).execute(ID_quirofano);		//OBTENER CIRUGIAS TRANSOPERATORIO
				new RegistroRealizada(inflater, container).execute(ID_quirofano);			//OBTENER CIRUGIAS REALIZADAS
				new RegistroProgramadaPasada(inflater, container).execute(ID_quirofano);	//OBTENER CIRUGIAS PROGRAMADAS PASADAS
				new Agenda(inflater, container).execute(ID_quirofano);
				sv.addView(home);
	            
        	}			
        	else{ 
        		Log.e("EnviarProcedimientos=","Todo mal="+resultado);
        		System.out.println("RESULTADO DEL ENVIO DE PROCEDIMIENTOS:"+resultado);
        		error2();
        	}
            
        }//Fin de onPostExecute        
	}//Fin de la subclase EnviarProcedimientos
	
	/*Asyntask*/
	
	class Agenda extends AsyncTask< String, String, String> {
		
		LayoutInflater inflater;
		ViewGroup container;
		
		Agenda(LayoutInflater inflater, ViewGroup container){
			this.inflater = inflater;
			this.container = container;
		}
		
		//String date, hora, reg, paciente;
    	String st1; //El string que llevara la cadena "ok"
		
    	protected void onPreExecute() {
    		//progress = ProgressDialog.show(
    		//getActivity(), null, "Accesando a agenda...");
            super.onPreExecute();
        }
    	
        protected String doInBackground(String... params) {
			st1=params[0]; //obtenemos el string con "ok" 
			
			//enviamos y recibimos y analizamos los datos en segundo plano.
    		//al = mostrarAgenda(st1);		
    		try {
				mostrarAgenda(st1);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//Log.e("last-array", "last-array = "+al.get(0));
    		return "ok"; //login valido
    		
		}//Fin de doInBackground
       
        protected void onPostExecute(String resultado) {
        	//progress.dismiss();//ocultamos progess dialog.
            Log.e("onPostExecute=","Todo bien="+resultado);
            System.out.println("array Padre = "+padre);
            System.out.println("array Diferidas = "+arrayDiferidas);
            System.out.println("array Transoperatorio = "+arrayTransoperatorio);
            System.out.println("array Realizadas = "+arrayRealizadas);
            System.out.println("array ProgramadasPasadas = "+arrayProgramadasPasadas);
            
            tl.removeAllViews();
            TableRow tr = (TableRow) inflater.inflate(R.layout.tablerow, container, false);
			tl.addView(tr);
            
            //new 28 nov
            
            //ONCLICK PARA CIRUGIAS PROGRAMADAS
            OnClickListener clicks=new OnClickListener() {

    		    @Override
    		    public void onClick(View v) {
    		    		//Esta es la posicion del array padre sobre el que se hizo click
    		    		System.out.println("id = "+v.getId());  
    		    		
	    		        Fragment duedateFrag = new Accion();	
	    		        Bundle bundle = new Bundle();
	    		                	    		                
	    		        String miArreglo[] = new String[7];

	    		        miArreglo[0] = padre.get(v.getId()).get(0);		//Llenar con la fecha
				        miArreglo[1] = padre.get(v.getId()).get(1);		//Llenar con la hora
				        miArreglo[2] = padre.get(v.getId()).get(2);		//Sala	
				        miArreglo[3] = padre.get(v.getId()).get(3);		//Paciente
				        miArreglo[4] = padre.get(v.getId()).get(4);		//Diagnostico
				        miArreglo[5] = padre.get(v.getId()).get(5);		//ID
				        miArreglo[6] = ID_quirofano;					//Pasar el id del quirofano seleccionado
	    		                	    		                	    		                
	    		        bundle.putStringArray("parametro", miArreglo); //Arreglo para mandar a Accion
	    		        duedateFrag.setArguments(bundle);
	    		                
	    		        FragmentTransaction ft  = getFragmentManager().beginTransaction();
	    		        ft.replace(R.id.content_frame, duedateFrag);
	    		        ft.addToBackStack(null);
	    		        ft.commit();       
    		    } //Fin de onClick
    		}; //Fin de onClickListener
    		
    		//ONCLICK PARA TRANSOPERATORIO
    		OnClickListener clicks1=new OnClickListener() {

    		    @Override
    		    public void onClick(View v) {
    		    		//Esta es la posicion del array padre sobre el que se hizo click
    		    		System.out.println("id = "+v.getId());  
    		    		
	    		        Fragment duedateFrag = new AccionTransoperatorio();	
	    		        Bundle bundle = new Bundle();
	    		                	    		                
	    		        String miArreglo[] = new String[7];
	    		        
	    		        miArreglo[0] = arrayTransoperatorio.get(v.getId()).get(0);		//Llenar con la fecha
				        miArreglo[1] = arrayTransoperatorio.get(v.getId()).get(1);		//Llenar con la hora
				        miArreglo[2] = arrayTransoperatorio.get(v.getId()).get(2);		//Sala	
				        miArreglo[3] = arrayTransoperatorio.get(v.getId()).get(3);		//Paciente
				        miArreglo[4] = arrayTransoperatorio.get(v.getId()).get(4);		//Diagnostico
				        miArreglo[5] = arrayTransoperatorio.get(v.getId()).get(5);		//ID
				        miArreglo[6] = ID_quirofano;									//Pasar el id del quirofano seleccionado
	    		                	    		                	    		                
	    		        bundle.putStringArray("parametro", miArreglo); //Arreglo para mandar a Accion
	    		        duedateFrag.setArguments(bundle);
	    		                
	    		        FragmentTransaction ft  = getFragmentManager().beginTransaction();
	    		        ft.replace(R.id.content_frame, duedateFrag);
	    		        ft.addToBackStack(null);
	    		        ft.commit();       
    		    } //Fin de onClick
    		}; //Fin de onClickListener
    		
    		//ONCLICK PARA REALIZADAS
    		OnClickListener clicks2 = new OnClickListener() {

    		    @Override
    		    public void onClick(View v) {
    		    		//Esta es la posicion del array padre sobre el que se hizo click
    		    		System.out.println("id = "+v.getId());  
    		    		
	    		        Fragment duedateFrag = new AccionRealizada();	
	    		        Bundle bundle = new Bundle();

	    		        String miArreglo[] = new String[6];

	    		        miArreglo[0] = arrayRealizadas.get(v.getId()).get(0);		//Llenar con la fecha
				        miArreglo[1] = arrayRealizadas.get(v.getId()).get(1);		//Llenar con la hora
				        miArreglo[2] = arrayRealizadas.get(v.getId()).get(2);		//Sala	
				        miArreglo[3] = arrayRealizadas.get(v.getId()).get(3);		//Paciente
				        miArreglo[4] = arrayRealizadas.get(v.getId()).get(4);		//Diagnostico
				        miArreglo[5] = arrayRealizadas.get(v.getId()).get(5);		//ID

	    		        bundle.putStringArray("parametro", miArreglo); //Arreglo para mandar a Accion
	    		        duedateFrag.setArguments(bundle);

	    		        FragmentTransaction ft  = getFragmentManager().beginTransaction();
	    		        ft.replace(R.id.content_frame, duedateFrag);
	    		        ft.addToBackStack(null);
	    		        ft.commit();       
    		    } //Fin de onClick
    		}; //Fin de onClickListener
    		
    		//ONCLICK PARA DIFERIDAS
    		OnClickListener clicks3=new OnClickListener() {

    		    @Override
    		    public void onClick(View v) {
    		    		//Esta es la posicion del array padre sobre el que se hizo click
    		    		System.out.println("id = "+v.getId());  
    		    	
	    		        Fragment duedateFrag = new AccionDiferida();	
	    		        Bundle bundle = new Bundle();
	    		                	    		                
	    		        String miArreglo[] = new String[7];
	    		        
	    		        miArreglo[0] = arrayDiferidas2.get(v.getId()).get(0);		//Llenar con la fecha
				        miArreglo[1] = arrayDiferidas2.get(v.getId()).get(1);		//Llenar con la hora
				        miArreglo[2] = arrayDiferidas2.get(v.getId()).get(2);		//Sala	
				        miArreglo[3] = arrayDiferidas2.get(v.getId()).get(3);		//Paciente
				        miArreglo[4] = arrayDiferidas2.get(v.getId()).get(4);		//Diagnostico
				        miArreglo[5] = arrayDiferidas2.get(v.getId()).get(5);		//ID
				        miArreglo[6] = ID_quirofano;					//Pasar el id del quirofano seleccionado
	    		                	    		                	    		                
	    		        bundle.putStringArray("parametro", miArreglo); //Arreglo para mandar a Accion
	    		        duedateFrag.setArguments(bundle);
	    		                
	    		        FragmentTransaction ft  = getFragmentManager().beginTransaction();
	    		        ft.replace(R.id.content_frame, duedateFrag);
	    		        ft.addToBackStack(null);
	    		        ft.commit();
    		    } //Fin de onClick
    		}; //Fin de onClickListener
    		
    		//ONCLICK PARA PROGRAMADAS PASADAS
    		OnClickListener clicks4=new OnClickListener() {

    		    @Override
    		    public void onClick(View v) {
    		    		//Esta es la posicion del array padre sobre el que se hizo click
    		    		System.out.println("id = "+v.getId());  
    		    		
	    		        Fragment duedateFrag = new AccionProgramadaPasada(); //se puede diferir y se puede eliminar	
	    		        Bundle bundle = new Bundle();
	    		                	    		                
	    		        String miArreglo[] = new String[6];

	    		        miArreglo[0] = arrayProgramadasPasadas.get(v.getId()).get(0);	//Llenar con la fecha
				        miArreglo[1] = arrayProgramadasPasadas.get(v.getId()).get(1);	//Llenar con la hora
				        miArreglo[2] = arrayProgramadasPasadas.get(v.getId()).get(2);	//Sala	
				        miArreglo[3] = arrayProgramadasPasadas.get(v.getId()).get(3);	//Paciente
				        miArreglo[4] = arrayProgramadasPasadas.get(v.getId()).get(4);	//Diagnostico
				        miArreglo[5] = arrayProgramadasPasadas.get(v.getId()).get(5);	//ID
	    		                	    		                	    		                
	    		        bundle.putStringArray("parametro", miArreglo); //Arreglo para mandar a Accion
	    		        duedateFrag.setArguments(bundle);
	    		                
	    		        FragmentTransaction ft  = getFragmentManager().beginTransaction();
	    		        ft.replace(R.id.content_frame, duedateFrag);
	    		        ft.addToBackStack(null);
	    		        ft.commit();       
    		    } //Fin de onClick
    		}; //Fin de onClickListener
        	
        	View tab = inflater.inflate(R.layout.tiposde_cirugias, container, false);
        	TextView titulo = (TextView)tab.findViewById(R.id.title);
        	titulo.setText("Programada");
			TableRow tabRow = (TableRow) tab;
			tl.addView(tabRow);

    		//PARA LAS PROGRAMADAS *********************************************************************
        	for (int i = 0; i<padre.size(); i++){
    			
    			View tabler = inflater.inflate(R.layout.newtablerow, container, false);
    			TextView fech = (TextView)tabler.findViewById(R.id.fech);
				TextView hora = (TextView)tabler.findViewById(R.id.hora);
				TextView sala = (TextView)tabler.findViewById(R.id.sala);
				//Registro
				TextView registro = (TextView)tabler.findViewById(R.id.registro);
		
				TextView pa = (TextView)tabler.findViewById(R.id.pa);
				TextView dg = (TextView)tabler.findViewById(R.id.dg);
				//procedimiento - cirugia
				TextView procedimiento = (TextView)tabler.findViewById(R.id.procedimiento);
				
				//medico que programo
				TextView medico = (TextView)tabler.findViewById(R.id.medico);
				
				TableRow trow = (TableRow) tabler;
				
				fech.setText(padre.get(i).get(0));				//fecha
				hora.setText(padre.get(i).get(1));				//hora
				sala.setText(padre.get(i).get(2));				//sala
				registro.setText(padre.get(i).get(6));			//registro -new 10Dic
				pa.setText(padre.get(i).get(3));				//paciente
				dg.setText(padre.get(i).get(4));				//diagnostico
				
				int counter = 0;
				String tempo = "";
				
				for (int x = 0; x<arrayProcedimientosProgramada.size(); x++){
					if(padre.get(i).get(5).equals(arrayProcedimientosProgramada.get(x).get(0))){
						if(counter == 0){
							tempo += "Procedimiento "+(counter+1)+
									", id = "+arrayProcedimientosProgramada.get(x).get(1)+
									", cie9mc = "+arrayProcedimientosProgramada.get(x).get(2)+
									", región = "+arrayProcedimientosProgramada.get(x).get(3);
						}
						else{
							tempo += "\nProcedimiento "+(counter+1)+
									", id = "+arrayProcedimientosProgramada.get(x).get(1)+
									", cie9mc = "+arrayProcedimientosProgramada.get(x).get(2)+
									", región = "+arrayProcedimientosProgramada.get(x).get(3); 
						}
						counter = counter+1;
					}//Fin de if
				}//Fin de for para procedimientos
				procedimiento.setText(tempo);
				
				
				medico.setText(padre.get(i).get(7));			//nombre del medico
				
				//accion.setText("action:"+index);
				
				trow.setBackgroundResource(android.R.drawable.list_selector_background);
					
				tl.addView(trow);
    			//Primero pasamos la posicion del arraypadre en el que se hizo click
    			trow.setId(i);
    			//Opcion2 - settear Id con la posición de "padre" en que se hizo click
    			trow.setOnClickListener(clicks);
    		}//Fin de for
        	
        	View tab1 = inflater.inflate(R.layout.tiposde_cirugias, container, false);
        	TextView titulo1 = (TextView)tab1.findViewById(R.id.title);
        	titulo1.setText("Transoperatorio");
			TableRow tabRow1 = (TableRow) tab1;
			tl.addView(tabRow1);
        	
        	//PARA TRANSOPERATORIO ********************************************************************
        	for (int i = 0; i<arrayTransoperatorio.size(); i++){
        		View tabler = inflater.inflate(R.layout.newtablerow, container, false);
        		TextView fech = (TextView)tabler.findViewById(R.id.fech);
				TextView hora = (TextView)tabler.findViewById(R.id.hora);
				TextView sala = (TextView)tabler.findViewById(R.id.sala);
				//Registro
				TextView registro = (TextView)tabler.findViewById(R.id.registro);
		
				TextView pa = (TextView)tabler.findViewById(R.id.pa);
				TextView dg = (TextView)tabler.findViewById(R.id.dg);
				//procedimiento - cirugia
				TextView procedimiento = (TextView)tabler.findViewById(R.id.procedimiento);
				
				//medico que programo
				TextView medico = (TextView)tabler.findViewById(R.id.medico);
				
				TableRow trow = (TableRow) tabler;
				
				fech.setText(arrayTransoperatorio.get(i).get(0));	//fecha
				hora.setText(arrayTransoperatorio.get(i).get(1));	//hora
				sala.setText(arrayTransoperatorio.get(i).get(2));	//sala
				registro.setText(arrayTransoperatorio.get(i).get(6));					//registro -new 10Dic
				pa.setText(arrayTransoperatorio.get(i).get(3));		//paciente
				dg.setText(arrayTransoperatorio.get(i).get(4));		//diagnostico
				
				int counter = 0;
				String tempo = "";
				
				for (int x = 0; x<arrayProcedimientosTransoperatorio.size(); x++){
					if(arrayTransoperatorio.get(i).get(5).equals(arrayProcedimientosTransoperatorio.get(x).get(0))){
						if(counter == 0){
							tempo += "Procedimiento "+(counter+1)+
									", id = "+arrayProcedimientosTransoperatorio.get(x).get(1)+
									", cie9mc = "+arrayProcedimientosTransoperatorio.get(x).get(2)+
									", región = "+arrayProcedimientosTransoperatorio.get(x).get(3);
						}
						else{
							tempo += "\nProcedimiento "+(counter+1)+
									", id = "+arrayProcedimientosTransoperatorio.get(x).get(1)+
									", cie9mc = "+arrayProcedimientosTransoperatorio.get(x).get(2)+
									", región = "+arrayProcedimientosTransoperatorio.get(x).get(3); 
						}
						counter = counter+1;
					}//Fin de if
				}//Fin de for para procedimientos
				procedimiento.setText(tempo);
				
				medico.setText(arrayTransoperatorio.get(i).get(7));						//medico que programa la cirugia
				//accion.setText("action:"+index);
				trow.setBackgroundResource(R.drawable.selector_trans);
					
				tl.addView(trow);
    			//Primero pasamos la posicion del arraypadre en el que se hizo click
    			trow.setId(i);
    			//Opcion2 - settear Id con la posición de "padre" en que se hizo click
    			trow.setOnClickListener(clicks1);
    		}//Fin de for

        	View tab2 = inflater.inflate(R.layout.tiposde_cirugias, container, false);
        	TextView titulo2 = (TextView)tab2.findViewById(R.id.title);
        	titulo2.setText("Realizadas");
			TableRow tabRow2 = (TableRow) tab2;
			tl.addView(tabRow2);
        	
        	//PARA REALIZADAS *************************************************************************
        	for (int i = 0; i<arrayRealizadas.size(); i++){
        		
        		View tabler = inflater.inflate(R.layout.newtablerow, container, false);
        		TextView fech = (TextView)tabler.findViewById(R.id.fech);
				TextView hora = (TextView)tabler.findViewById(R.id.hora);
				TextView sala = (TextView)tabler.findViewById(R.id.sala);
				//Registro
				TextView registro = (TextView)tabler.findViewById(R.id.registro);
		
				TextView pa = (TextView)tabler.findViewById(R.id.pa);
				TextView dg = (TextView)tabler.findViewById(R.id.dg);
				//procedimiento - cirugia
				TextView procedimiento = (TextView)tabler.findViewById(R.id.procedimiento);
				
				//medico que programo
				TextView medico = (TextView)tabler.findViewById(R.id.medico);
				
				TableRow trow = (TableRow) tabler;
				
				fech.setText(arrayRealizadas.get(i).get(0));	//fecha
				hora.setText(arrayRealizadas.get(i).get(1));	//hora
				sala.setText(arrayRealizadas.get(i).get(2));	//sala
				registro.setText(arrayRealizadas.get(i).get(6));				//registro -new 10Dic
				pa.setText(arrayRealizadas.get(i).get(3));		//paciente
				dg.setText(arrayRealizadas.get(i).get(4));		//diagnostico
				
				int counter = 0;
				String tempo = "";
				
				for (int x = 0; x<arrayProcedimientosRealizada.size(); x++){
					if(arrayRealizadas.get(i).get(5).equals(arrayProcedimientosRealizada.get(x).get(0))){
						if(counter == 0){
							tempo += "Procedimiento "+(counter+1)+
									", id = "+arrayProcedimientosRealizada.get(x).get(1)+
									", cie9mc = "+arrayProcedimientosRealizada.get(x).get(2)+
									", región = "+arrayProcedimientosRealizada.get(x).get(3);
						}
						else{
							tempo += "\nProcedimiento "+(counter+1)+
									", id = "+arrayProcedimientosRealizada.get(x).get(1)+
									", cie9mc = "+arrayProcedimientosRealizada.get(x).get(2)+
									", región = "+arrayProcedimientosRealizada.get(x).get(3); 
						}
						counter = counter+1;
					}//Fin de if
				}//Fin de for para procedimientos
				procedimiento.setText(tempo);
				//procedimiento.setText("Procedimiento "+i+", id = "+i+", cie9mc = "+i); 		//procedimientos de la cirugia
				
				medico.setText(arrayRealizadas.get(i).get(7));					//medico que programa la cirugia
				//accion.setText("action:"+index);
				trow.setBackgroundResource(R.drawable.selector_rel);
					
				tl.addView(trow);
    			//Primero pasamos la posicion del arraypadre en el que se hizo click
    			trow.setId(i);
    			//Opcion2 - settear Id con la posición de "padre" en que se hizo click
    			trow.setOnClickListener(clicks2);
    		}//Fin de for
        
        	View tab3 = inflater.inflate(R.layout.tiposde_cirugias, container, false);
        	TextView titulo3 = (TextView)tab3.findViewById(R.id.title);
        	titulo3.setText("Diferidas");
			TableRow tabRow3 = (TableRow) tab3;
			tl.addView(tabRow3);
        	
        	//PARA DIFERIDAS **************************************************************************
        	for (int i = 0; i<arrayDiferidas2.size(); i++){
        		
        		View tabler = inflater.inflate(R.layout.newtablerow, container, false);
        		TextView fech = (TextView)tabler.findViewById(R.id.fech);
				TextView hora = (TextView)tabler.findViewById(R.id.hora);
				TextView sala = (TextView)tabler.findViewById(R.id.sala);
				//Registro
				TextView registro = (TextView)tabler.findViewById(R.id.registro);
		
				TextView pa = (TextView)tabler.findViewById(R.id.pa);
				TextView dg = (TextView)tabler.findViewById(R.id.dg);
				//procedimiento - cirugia
				TextView procedimiento = (TextView)tabler.findViewById(R.id.procedimiento);
				
				//medico que programo
				TextView medico = (TextView)tabler.findViewById(R.id.medico);
				
				TableRow trow = (TableRow) tabler;
				
				fech.setText(arrayDiferidas2.get(i).get(0));	//fecha
				fech.setTextColor(Color.parseColor("#ffffff"));
				
				hora.setText(arrayDiferidas2.get(i).get(1));	//hora
				hora.setTextColor(Color.parseColor("#ffffff"));
				
				sala.setText(arrayDiferidas2.get(i).get(2));	//sala
				sala.setTextColor(Color.parseColor("#ffffff"));
				
				registro.setText(arrayDiferidas2.get(i).get(6));				//registro -new 10Dic
				registro.setTextColor(Color.parseColor("#ffffff"));
				
				pa.setText(arrayDiferidas2.get(i).get(3));	//paciente
				pa.setTextColor(Color.parseColor("#ffffff"));
				
				dg.setText(arrayDiferidas2.get(i).get(4));	//diagnostico
				dg.setTextColor(Color.parseColor("#ffffff"));
				
				int counter = 0;
				String tempo = "";
				
				for (int x = 0; x<arrayProcedimientosDiferida.size(); x++){
					if(arrayDiferidas2.get(i).get(5).equals(arrayProcedimientosDiferida.get(x).get(0))){
						if(counter == 0){
							tempo += "Procedimiento "+(counter+1)+
									", id = "+arrayProcedimientosDiferida.get(x).get(1)+
									", cie9mc = "+arrayProcedimientosDiferida.get(x).get(2)+
									", región = "+arrayProcedimientosDiferida.get(x).get(3);
						}
						else{
							tempo += "\nProcedimiento "+(counter+1)+
									", id = "+arrayProcedimientosDiferida.get(x).get(1)+
									", cie9mc = "+arrayProcedimientosDiferida.get(x).get(2)+
									", región = "+arrayProcedimientosDiferida.get(x).get(3); 
						}
						counter = counter+1;
					}//Fin de if
				}//Fin de for para procedimientos
				
				procedimiento.setText(tempo);
 
				procedimiento.setTextColor(Color.parseColor("#ffffff"));
				
				medico.setText(arrayDiferidas2.get(i).get(7));					//medico que programa la cirugia
				medico.setTextColor(Color.parseColor("#ffffff"));
				
				trow.setBackgroundResource(R.drawable.selector_dif);		
					
				tl.addView(trow);
    			//Primero pasamos la posicion del arraypadre en el que se hizo click
    			trow.setId(i);
    			//Opcion2 - settear Id con la posición de "padre" en que se hizo click
    			trow.setOnClickListener(clicks3);
    		}//Fin de for
            //fin new 28 nov
        	
        	//10 dic
        	View tab4 = inflater.inflate(R.layout.tiposde_cirugias, container, false);
        	TextView titulo4 = (TextView)tab4.findViewById(R.id.title);
        	titulo4.setText("Cirugías pasadas");
			TableRow tabRow4 = (TableRow) tab4;
			tl.addView(tabRow4);
        	
        	//PARA PROGRAMADAS PASADAS  **********************************************************
        	for (int i = 0; i<arrayProgramadasPasadas.size(); i++){
        		
        		View tabler = inflater.inflate(R.layout.newtablerow, container, false);
        		TextView fech = (TextView)tabler.findViewById(R.id.fech);
				TextView hora = (TextView)tabler.findViewById(R.id.hora);
				TextView sala = (TextView)tabler.findViewById(R.id.sala);
				//Registro
				TextView registro = (TextView)tabler.findViewById(R.id.registro);
		
				TextView pa = (TextView)tabler.findViewById(R.id.pa);
				TextView dg = (TextView)tabler.findViewById(R.id.dg);
				//procedimiento - cirugia
				TextView procedimiento = (TextView)tabler.findViewById(R.id.procedimiento);
				
				//medico que programo
				TextView medico = (TextView)tabler.findViewById(R.id.medico);
				
				TableRow trow = (TableRow) tabler;
				
				fech.setText(arrayProgramadasPasadas.get(i).get(0));	//fecha
				fech.setTextColor(Color.parseColor("#ffffff"));
				
				hora.setText(arrayProgramadasPasadas.get(i).get(1));	//hora
				hora.setTextColor(Color.parseColor("#ffffff"));
				
				sala.setText(arrayProgramadasPasadas.get(i).get(2));	//sala
				sala.setTextColor(Color.parseColor("#ffffff"));
				
				registro.setText(arrayProgramadasPasadas.get(i).get(6));				//registro -new 10Dic
				registro.setTextColor(Color.parseColor("#ffffff"));
				
				pa.setText(arrayProgramadasPasadas.get(i).get(3));	//paciente
				pa.setTextColor(Color.parseColor("#ffffff"));
				
				dg.setText(arrayProgramadasPasadas.get(i).get(4));	//diagnostico
				dg.setTextColor(Color.parseColor("#ffffff"));
				
				//procedimiento.setText("Procedimiento "+i+", id = "+i+", cie9mc = "+i); 		//procedimientos de la cirugia
				
				int counter = 0;
				String tempo = "";
				
				for (int x = 0; x<arrayProcedimientosProgramadaPasada.size(); x++){
					if(arrayProgramadasPasadas.get(i).get(5).equals(arrayProcedimientosProgramadaPasada.get(x).get(0))){
						if(counter == 0){
							tempo += "Procedimiento "+(counter+1)+
									", id = "+arrayProcedimientosProgramadaPasada.get(x).get(1)+
									", cie9mc = "+arrayProcedimientosProgramadaPasada.get(x).get(2)+
									", región = "+arrayProcedimientosProgramadaPasada.get(x).get(3);
						}
						else{
							tempo += "\nProcedimiento "+(counter+1)+
									", id = "+arrayProcedimientosProgramadaPasada.get(x).get(1)+
									", cie9mc = "+arrayProcedimientosProgramadaPasada.get(x).get(2)+
									", región = "+arrayProcedimientosProgramadaPasada.get(x).get(3); 
						}
						counter = counter+1;
					}//Fin de if
				}//Fin de for para procedimientos
				procedimiento.setText(tempo);
				
				procedimiento.setTextColor(Color.parseColor("#ffffff"));

				medico.setText(arrayProgramadasPasadas.get(i).get(7));					//medico que programa la cirugia
				medico.setTextColor(Color.parseColor("#ffffff"));
				
				//accion.setText("action:"+index);
				trow.setBackgroundResource(R.drawable.selector_atr);		
					
				tl.addView(trow);
    			//Primero pasamos la posicion del arraypadre en el que se hizo click
    			trow.setId(i);
    			//Opcion2 - settear Id con la posición de "padre" en que se hizo click
    			trow.setOnClickListener(clicks4);
        		
    		}//Fin de for            
        }//Fin de onPostExecute
	}//Fin de la subclase Agenda
	
	//Sub clase para obtener las salas de un quirofano
	class GetSalas extends AsyncTask< String, String, ArrayList<String>> {
		
		LayoutInflater inflater;
		ViewGroup container;
		
		GetSalas(LayoutInflater inflater, ViewGroup container){
			this.inflater = inflater;
			this.container = container;
		}
		
    	String quir; //El string llevara el quirofano_id
		
    	protected void onPreExecute() {
            super.onPreExecute();
        }
    	
//      protected String doInBackground(String... params) {
    	protected ArrayList<String> doInBackground(String... params) {
			quir=params[0]; //obtenemos el string de quirofano_id 
			ArrayList<String> tempoSalas = new ArrayList<String>();
			
			tempoSalas = getSalas(quir);
    		return tempoSalas;
		}//Fin de doInBackground

    	protected void onPostExecute(ArrayList<String> resultado) {
    		nombreSalas = resultado;

    		for(int index = 0; index < salasArray.size(); index++){
        		
    			View tabler = inflater.inflate(R.layout.tabla_salas_editable, container, false);
    			
    			TextView sala = (TextView)tabler.findViewById(R.id.sala_name);
    			TextView activo = (TextView)tabler.findViewById(R.id.sala_activo);
    			TextView bloqueado = (TextView)tabler.findViewById(R.id.sala_bloqueado);
    			
    			TableRow trow = (TableRow) tabler;
    		
    			sala.setText(salasArray.get(index).get(1));			//nombre de la sala
    			activo.setText(salasArray.get(index).get(2));		//estado activo de la sala
    			bloqueado.setText(salasArray.get(index).get(3));	//estado bloqueado de la sala
    				
    			tablaDeSalas.addView(trow);
    		}//Fin de ciclo for
        }//Fin de onPostExecute
	}//Fin de la subclase GetSalas
	
	class agendaDelDia extends AsyncTask< String, String, String> {
    	String st1; //El string que llevara la cadena "ok"
    	
		LayoutInflater inflater;
		ViewGroup container;
		
		agendaDelDia(LayoutInflater inflater, ViewGroup container){
			this.inflater = inflater;
			this.container = container;
		}
		
    	protected void onPreExecute() {
    		//progress = ProgressDialog.show(
    		//getActivity(), null, "Accesando a agenda...");
            super.onPreExecute();
        }
    	
        protected String doInBackground(String... params) {
			st1=params[0]; //Obtenemos el id del quirofano
			
			//enviamos y recibimos y analizamos los datos en segundo plano.
    		//al = mostrarAgenda(st1);		
    		try {
				mostrarAgendaDelDia(st1);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//Log.e("last-array", "last-array = "+al.get(0));
    		return "ok"; //login valido
    		
		}//Fin de doInBackground
       
        protected void onPostExecute(String resultado) {
        	//progress.dismiss();//ocultamos progess dialog.
            //Log.e("onPostExecute=","Todo bien="+resultado);
            //return al;
            //mostrarLeyenda();
        	listaDia.setAdapter(new MyVeryOwnArrayAdapter(getActivity(), R.layout.tablerow_editable, registroDelDia));
        }//Fin de onPostExecute
	}//Fin de la subclase agendaDelDia

//	new RegistroDiferida(inflater, container).execute(ID_quirofano);		//OBTENER CIRUGAS DIFERIDAS
	class RegistroDiferida extends AsyncTask< String, String, String> {
		
		LayoutInflater inflater;
		ViewGroup container;
		
		RegistroDiferida(LayoutInflater inflater, ViewGroup container){
			this.inflater = inflater;
			this.container = container;
		}
		
    	String st1; //String con el id del quirofano seleccionado
		
    	protected void onPreExecute() {
    		//progress = ProgressDialog.show(
    		//getActivity(), null, "Accesando a agenda...");
            super.onPreExecute();
        }
    	
        protected String doInBackground(String... params) {
			st1=params[0]; //obtenemos el string con "ok" 
			
			//enviamos y recibimos y analizamos los datos en segundo plano.
    		try {
				mostrarRegistroDiferida(st1);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//Log.e("last-array", "last-array = "+al.get(0));
    		return "ok"; //login valido
    		
		}//Fin de doInBackground
       
        protected void onPostExecute(String resultado) {
        	//progress.dismiss();//ocultamos progess dialog.
            Log.e("onPostExecute=","Todo bien="+resultado);
            
            //ONCLICK PARA DIFERIDAS
    		OnClickListener clicks3=new OnClickListener() {

    		    @Override
    		    public void onClick(View v) {
    		    		//Esta es la posicion del array padre sobre el que se hizo click
    		    		System.out.println("id = "+v.getId());  
    		    		
	    		        Fragment duedateFrag = new AccionDiferida();	
	    		        Bundle bundle = new Bundle();
	    		                	    		                
	    		        String miArreglo[] = new String[6];
	    		        
	    		        miArreglo[0] = arrayDiferidas.get(v.getId()).get(0);		//Llenar con la fecha
				        miArreglo[1] = arrayDiferidas.get(v.getId()).get(1);		//Llenar con la hora
				        miArreglo[2] = arrayDiferidas.get(v.getId()).get(2);		//Sala	
				        miArreglo[3] = arrayDiferidas.get(v.getId()).get(3);		//Paciente
				        miArreglo[4] = arrayDiferidas.get(v.getId()).get(4);		//Diagnostico
				        miArreglo[5] = arrayDiferidas.get(v.getId()).get(5);		//ID
	    		                	    		                	    		                
	    		        bundle.putStringArray("parametro", miArreglo); //Arreglo para mandar a Accion
	    		        duedateFrag.setArguments(bundle);
	    		                
	    		        FragmentTransaction ft  = getFragmentManager().beginTransaction();
	    		        ft.replace(R.id.content_frame, duedateFrag);
	    		        ft.addToBackStack(null);
	    		        ft.commit();
    		    } //Fin de onClick
    		}; //Fin de onClickListener
            
    		View tab = inflater.inflate(R.layout.tiposde_cirugias, container, false);
        	TextView titulo4 = (TextView)tab.findViewById(R.id.title);
        	titulo4.setText("Cirugías diferidas");
			TableRow tabRow4 = (TableRow) tab;
			tl.addView(tabRow4);
    		
          //PARA DIFERIDAS **************************************************************************
        	for (int i = 0; i<arrayDiferidas.size(); i++){
        		
        		View tabler = inflater.inflate(R.layout.newtablerow, container, false);
        		TextView fech = (TextView)tabler.findViewById(R.id.fech);
				TextView hora = (TextView)tabler.findViewById(R.id.hora);
				TextView sala = (TextView)tabler.findViewById(R.id.sala);
				//Registro
				TextView registro = (TextView)tabler.findViewById(R.id.registro);
		
				TextView pa = (TextView)tabler.findViewById(R.id.pa);
				TextView dg = (TextView)tabler.findViewById(R.id.dg);
				//procedimiento - cirugia
				TextView procedimiento = (TextView)tabler.findViewById(R.id.procedimiento);
				
				//medico que programo
				TextView medico = (TextView)tabler.findViewById(R.id.medico);
				
				TableRow trow = (TableRow) tabler;
				
				fech.setText(arrayDiferidas.get(i).get(0));	//fecha
				fech.setTextColor(Color.parseColor("#ffffff"));
				
				hora.setText(arrayDiferidas.get(i).get(1));	//hora
				hora.setTextColor(Color.parseColor("#ffffff"));
				
				sala.setText(arrayDiferidas.get(i).get(2));	//sala
				sala.setTextColor(Color.parseColor("#ffffff"));
				
				registro.setText(arrayDiferidas.get(i).get(6));				//registro -new 10Dic
				registro.setTextColor(Color.parseColor("#ffffff"));
				
				pa.setText(arrayDiferidas.get(i).get(3));	//paciente
				pa.setTextColor(Color.parseColor("#ffffff"));
				
				dg.setText(arrayDiferidas.get(i).get(4));	//diagnostico
				dg.setTextColor(Color.parseColor("#ffffff"));
				
				int counter = 0;
				String tempo = "";
				
				for (int x = 0; x<arrayProcedimientosDiferida.size(); x++){
					if(arrayDiferidas.get(i).get(5).equals(arrayProcedimientosDiferida.get(x).get(0))){
						if(counter == 0){
							tempo += "Procedimiento "+(counter+1)+
									", id = "+arrayProcedimientosDiferida.get(x).get(1)+
									", cie9mc = "+arrayProcedimientosDiferida.get(x).get(2)+
									", región = "+arrayProcedimientosDiferida.get(x).get(3);
						}
						else{
							tempo += "\nProcedimiento "+(counter+1)+
									", id = "+arrayProcedimientosDiferida.get(x).get(1)+
									", cie9mc = "+arrayProcedimientosDiferida.get(x).get(2)+
									", región = "+arrayProcedimientosDiferida.get(x).get(3); 
						}
						counter = counter+1;
					}//Fin de if
				}//Fin de for para procedimientos
				
				procedimiento.setText(tempo);
 
				procedimiento.setTextColor(Color.parseColor("#ffffff"));
				
				medico.setText(arrayDiferidas.get(i).get(7));					//medico que programa la cirugia
				medico.setTextColor(Color.parseColor("#ffffff"));
				
				trow.setBackgroundResource(R.drawable.selector_dif);		
					
				tl.addView(trow);
    			//Primero pasamos la posicion del arraypadre en el que se hizo click
    			trow.setId(i);
    			//Opcion2 - settear Id con la posición de "padre" en que se hizo click
    			trow.setOnClickListener(clicks3);
    		}//Fin de for
        }//Fin de onPostExecute        
		
	}//Fin de la subclase RegistroDiferida
	
    //Para RegistroTransoperatorio
	class RegistroTransoperatorio extends AsyncTask< String, String, String> {
		
		LayoutInflater inflater;
		ViewGroup container;
		
		RegistroTransoperatorio(LayoutInflater inflater, ViewGroup container){
			this.inflater = inflater;
			this.container = container;
		}
		
    	String st1; //String con el id del quirofano seleccionado
		
    	protected void onPreExecute() {
    		//progress = ProgressDialog.show(
    		//getActivity(), null, "Accesando a agenda...");
            super.onPreExecute();
        }
    	
        protected String doInBackground(String... params) {
			st1=params[0]; //obtenemos el string con "ok" 
			
			//enviamos y recibimos y analizamos los datos en segundo plano.
    		try {
				mostrarRegistroTransoperatorio(st1);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		return "ok"; //login valido
		}//Fin de doInBackground
       
        protected void onPostExecute(String resultado) {
        	//progress.dismiss();//ocultamos progess dialog.
            Log.e("onPostExecute=","Todo bien="+resultado);
            lista3.setAdapter(new MyVeryOwnArrayAdapter(getActivity(), R.layout.tablerow_editable, arrayTransoperatorio));
            lista3.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                public void onItemClick(AdapterView<?> av, View view, int i, long l) {
                    //Toast.makeText(getActivity(), "Accediste a quirófano: "+padre.get(i).get(1), Toast.LENGTH_LONG).show();
                    
                    Fragment duedateFrag = new Accion();	//Nuevo item para las acciones
    		        Bundle bundle = new Bundle();
    		        String miArreglo[] = new String[6]; 	//Llenar arreglo con el nombre del paciente y el id
			              
			        miArreglo[0] = arrayTransoperatorio.get(i).get(0);		//Llenar con la fecha
			        miArreglo[1] = arrayTransoperatorio.get(i).get(1);		//Llenar con la hora
			        miArreglo[2] = arrayTransoperatorio.get(i).get(2);		//Sala	
			        miArreglo[3] = arrayTransoperatorio.get(i).get(3);		//Paciente
			        miArreglo[4] = arrayTransoperatorio.get(i).get(4);		//Diagnostico
			        miArreglo[5] = arrayTransoperatorio.get(i).get(5);		//ID
			        
			        bundle.putStringArray("parametro", miArreglo); //Arreglo para mandar a Item1
    		        duedateFrag.setArguments(bundle);

    		        FragmentTransaction ft  = getFragmentManager().beginTransaction();
    		        ft.replace(R.id.content_frame, duedateFrag);
    		        ft.addToBackStack(null);
    		        //duedateFrag.getFragmentManager().popBackStackImmediate();
    		        ft.commit();
                }// Fin de onItemClick
            });
        }//Fin de onPostExecute        
		
	}//Fin de la subclase RegistroTransoperatorio
	
	//new RegistroRealizada(inflater, container).execute(ID_quirofano);
	class RegistroRealizada extends AsyncTask< String, String, String> {
		
		LayoutInflater inflater;
		ViewGroup container;
		
		RegistroRealizada(LayoutInflater inflater, ViewGroup container){
			this.inflater = inflater;
			this.container = container;
		}
		
    	String st1; //String con el id del quirofano seleccionado
		
    	protected void onPreExecute() {
    		//progress = ProgressDialog.show(
    		//getActivity(), null, "Accesando a agenda...");
            super.onPreExecute();
        }
    	
        protected String doInBackground(String... params) {
			st1=params[0]; //obtenemos el string con "ok" 
			
			//enviamos y recibimos y analizamos los datos en segundo plano.
    		try {
				mostrarRegistroRealizada(st1);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//Log.e("last-array", "last-array = "+al.get(0));
    		return "ok"; //login valido
    		
		}//Fin de doInBackground
       
        protected void onPostExecute(String resultado) {
        	//progress.dismiss();//ocultamos progess dialog.
            Log.e("onPostExecute=","Todo bien="+resultado);
            lista4.setAdapter(new MyVeryOwnArrayAdapter(getActivity(), R.layout.tablerow_editable, arrayRealizadas));
            lista4.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                public void onItemClick(AdapterView<?> av, View view, int i, long l) {
                    //Toast.makeText(getActivity(), "Accediste a quirófano: "+padre.get(i).get(1), Toast.LENGTH_LONG).show();
                    
                    Fragment duedateFrag = new Accion();	//Nuevo item para las acciones
    		        Bundle bundle = new Bundle();
    		        String miArreglo[] = new String[6]; 	//Llenar arreglo con el nombre del paciente y el id
			              
			        miArreglo[0] = arrayRealizadas.get(i).get(0);		//Llenar con la fecha
			        miArreglo[1] = arrayRealizadas.get(i).get(1);		//Llenar con la hora
			        miArreglo[2] = arrayRealizadas.get(i).get(2);		//Sala	
			        miArreglo[3] = arrayRealizadas.get(i).get(3);		//Paciente
			        miArreglo[4] = arrayRealizadas.get(i).get(4);		//Diagnostico
			        miArreglo[5] = arrayRealizadas.get(i).get(5);		//ID
			        
			        bundle.putStringArray("parametro", miArreglo); //Arreglo para mandar a Item1
    		        duedateFrag.setArguments(bundle);

    		        FragmentTransaction ft  = getFragmentManager().beginTransaction();
    		        ft.replace(R.id.content_frame, duedateFrag);
    		        ft.addToBackStack(null);
    		        //duedateFrag.getFragmentManager().popBackStackImmediate();
    		        ft.commit();
                }// Fin de onItemClick
            });
        }//Fin de onPostExecute        
	}//Fin de la subclase RegistroRealizada
	
	class RegistroProgramadaPasada extends AsyncTask< String, String, String> 
	{
		LayoutInflater inflater;
		ViewGroup container;
		
		RegistroProgramadaPasada(LayoutInflater inflater, ViewGroup container){
			this.inflater = inflater;
			this.container = container;
		}
		
    	String st1; //String con el id del quirofano seleccionado
		
    	protected void onPreExecute() {
    		//progress = ProgressDialog.show(
    		//getActivity(), null, "Accesando a agenda...");
            super.onPreExecute();
        }
    	
        protected String doInBackground(String... params) {
			st1=params[0]; //obtenemos el string con "ok" 
			
			//enviamos y recibimos y analizamos los datos en segundo plano.
    		try {
				mostrarRegistroProgramadaPasada(st1);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//Log.e("last-array", "last-array = "+al.get(0));
    		return "ok"; //login valido
		}//Fin de doInBackground
       
        protected void onPostExecute(String resultado) {
        	//progress.dismiss();//ocultamos progess dialog.
            Log.e("onPostExecute=","Todo bien="+resultado);
            System.out.println(">>>"+arrayProgramadasPasadas);
        }//Fin de onPostExecute
	}//Fin de la subclase RegistroProgramadaPasada
	
	class GetProcedimientosProgramada extends AsyncTask< String, String, String> {
		
    	String st1; //String con el id del quirofano seleccionado
		
    	protected void onPreExecute() {
            super.onPreExecute();
        }
        protected String doInBackground(String... params) {
			st1=params[0]; //obtenemos el string con "ok" 
			
    		try {
				getProcedimientosProgramada(st1);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//Log.e("last-array", "last-array = "+al.get(0));
    		return "ok"; //login valido
		}//Fin de doInBackground
       
        protected void onPostExecute(String resultado) {
        	//progress.dismiss();//ocultamos progess dialog.
            Log.e("onPostExecute=","Todo bien="+resultado);
            System.out.println(">>>PROCEDIMIENTOS-PROGRAMADA"+arrayProcedimientosProgramada);
        }//Fin de onPostExecute        
	}//Fin de la subclase GetProcedimientosProgramada
	
	// GetProcedimientosDiferida
	class GetProcedimientosDiferida extends AsyncTask< String, String, String> {
		
    	String st1; //String con el id del quirofano seleccionado
		
    	protected void onPreExecute() {
            super.onPreExecute();
        }
        protected String doInBackground(String... params) {
			st1=params[0]; //obtenemos el string con "ok" 
			
    		try {
				getProcedimientosDiferida(st1);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//Log.e("last-array", "last-array = "+al.get(0));
    		return "ok"; //login valido
		}//Fin de doInBackground
       
        protected void onPostExecute(String resultado) {
        	//progress.dismiss();//ocultamos progess dialog.
            Log.e("onPostExecute=","Todo bien="+resultado);
            System.out.println(">>>ARRAYDIF*** : "+arrayProcedimientosDiferida);
        }//Fin de onPostExecute        
	}//Fin de la subclase GetProcedimientosDiferida
	
	//GetProcedimientosProgramadaPasada
	class GetProcedimientosProgramadaPasada extends AsyncTask< String, String, String> {
		
    	String st1; //String con el id del quirofano seleccionado
		
    	protected void onPreExecute() {
            super.onPreExecute();
        }
        protected String doInBackground(String... params) {
			st1=params[0]; //obtenemos el string con "ok" 
			
    		try {
				getProcedimientosProgramadaPasada(st1);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//Log.e("last-array", "last-array = "+al.get(0));
    		return "ok"; //login valido
		}//Fin de doInBackground
       
        protected void onPostExecute(String resultado) {
        	//progress.dismiss();//ocultamos progess dialog.
            Log.e("onPostExecute=","Todo bien="+resultado);
//            System.out.println(">>>ARRAY*** : "+arrayProcedimientosDiferida);
        }//Fin de onPostExecute        
	}//Fin de la subclase GetProcedimientosProgramadaPasada
	
	//GetProcedimientosTransoperatorio
	class GetProcedimientosTransoperatorio extends AsyncTask< String, String, String> {
		
    	String st1; //String con el id del quirofano seleccionado
		
    	protected void onPreExecute() {
            super.onPreExecute();
        }
        protected String doInBackground(String... params) {
			st1=params[0]; //obtenemos el string con "ok" 
			
    		try {
				getProcedimientosTransoperatorio(st1);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//Log.e("last-array", "last-array = "+al.get(0));
    		return "ok"; //login valido
		}//Fin de doInBackground
       
        protected void onPostExecute(String resultado) {
        	//progress.dismiss();//ocultamos progess dialog.
            Log.e("onPostExecute=","Todo bien="+resultado);
        }//Fin de onPostExecute
	}//Fin de la subclase GetProcedimientosTransoperatorio
	
	//GetProcedimientosRealizada
	class GetProcedimientosRealizada extends AsyncTask< String, String, String> {
		
    	String st1; //String con el id del quirofano seleccionado
		
    	protected void onPreExecute() {
            super.onPreExecute();
        }
        protected String doInBackground(String... params) {
			st1=params[0]; //obtenemos el string con "ok" 
			
    		try {
				getProcedimientosRealizada(st1);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//Log.e("last-array", "last-array = "+al.get(0));
    		return "ok"; //login valido
		}//Fin de doInBackground
       
        protected void onPostExecute(String resultado) {
        	//progress.dismiss();//ocultamos progess dialog.
            Log.e("onPostExecute=","Todo bien="+resultado);
//            System.out.println(">>>ARRAY*** : "+arrayProcedimientosDiferida);
        }//Fin de onPostExecute        
	}//Fin de la subclase GetProcedimientosRealizada
	
	/** ********************************************************************///
	class AgendaDelDia extends AsyncTask< String, String, String> {
		
		LayoutInflater inflater;
		ViewGroup container;
		
		AgendaDelDia(LayoutInflater inflater, ViewGroup container){
			this.inflater = inflater;
			this.container = container;
		}
		
    	String st1; //El string que llevara la cadena "ok"
		
    	protected void onPreExecute() {
    		//progress = ProgressDialog.show(
    		//getActivity(), null, "Accesando a agenda...");
            super.onPreExecute();
        }
    	
        protected String doInBackground(String... params) {
			st1=params[0]; //obtenemos el string con "ok" 
			
			//enviamos y recibimos y analizamos los datos en segundo plano.
    		//al = mostrarAgenda(st1);		
    		try {
				mostrarAgendaActual(st1);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//Log.e("last-array", "last-array = "+al.get(0));
    		return "ok"; //login valido
    		
		}//Fin de doInBackground
       
        protected void onPostExecute(String resultado) {
            Log.e("onPostExecute=","Todo bien="+resultado);
                       
            //ONCLICK PARA CIRUGIAS PROGRAMADAS ACTUALES
            OnClickListener clicks=new OnClickListener() {

    		    @Override
    		    public void onClick(View v) {
    		    		//Esta es la posicion del array padre sobre el que se hizo click
    		    		System.out.println("id = "+v.getId());  
    		    		
	    		        Fragment duedateFrag = new Accion();	
	    		        Bundle bundle = new Bundle();
	    		                	    		                
	    		        String miArreglo[] = new String[7];
	    		        
	    		        miArreglo[0] = arrayProgramadasActual.get(v.getId()).get(0);	//Llenar con la fecha
				        miArreglo[1] = arrayProgramadasActual.get(v.getId()).get(1);	//Llenar con la hora
				        miArreglo[2] = arrayProgramadasActual.get(v.getId()).get(2);	//Sala	
				        miArreglo[3] = arrayProgramadasActual.get(v.getId()).get(3);	//Paciente
				        miArreglo[4] = arrayProgramadasActual.get(v.getId()).get(4);	//Diagnostico
				        miArreglo[5] = arrayProgramadasActual.get(v.getId()).get(5);	//ID
				        miArreglo[6] = ID_quirofano;					//Pasar el id del quirofano seleccionado
	    		                	    		                	    		                
	    		        bundle.putStringArray("parametro", miArreglo); //Arreglo para mandar a Accion
	    		        duedateFrag.setArguments(bundle);
	    		                
	    		        FragmentTransaction ft  = getFragmentManager().beginTransaction();
	    		        ft.replace(R.id.content_frame, duedateFrag);
	    		        ft.addToBackStack(null);
	    		        ft.commit();
	    		        //break;
      
    		    } //Fin de onClick
    		}; //Fin de onClickListener
    		
    		//ONCLICK PARA TRANSOPERATORIO ACTUALES
    		OnClickListener clicks1=new OnClickListener() {

    		    @Override
    		    public void onClick(View v) {
    		    		//Esta es la posicion del array padre sobre el que se hizo click
    		    		System.out.println("id = "+v.getId());  
    		    		
	    		        Fragment duedateFrag = new AccionTransoperatorio();	
	    		        Bundle bundle = new Bundle();
	    		                	    		                
	    		        String miArreglo[] = new String[7];
	    		        
	    		        miArreglo[0] = arrayTransoperatorioActual.get(v.getId()).get(0);		//Llenar con la fecha
				        miArreglo[1] = arrayTransoperatorioActual.get(v.getId()).get(1);		//Llenar con la hora
				        miArreglo[2] = arrayTransoperatorioActual.get(v.getId()).get(2);		//Sala	
				        miArreglo[3] = arrayTransoperatorioActual.get(v.getId()).get(3);		//Paciente
				        miArreglo[4] = arrayTransoperatorioActual.get(v.getId()).get(4);		//Diagnostico
				        miArreglo[5] = arrayTransoperatorioActual.get(v.getId()).get(5);		//ID
				        miArreglo[6] = ID_quirofano;									//Pasar el id del quirofano seleccionado
	    		                	    		                	    		                
	    		        bundle.putStringArray("parametro", miArreglo); //Arreglo para mandar a Accion
	    		        duedateFrag.setArguments(bundle);
	    		                
	    		        FragmentTransaction ft  = getFragmentManager().beginTransaction();
	    		        ft.replace(R.id.content_frame, duedateFrag);
	    		        ft.addToBackStack(null);
	    		        ft.commit();
	    		        //break;
   
    		    } //Fin de onClick
    		}; //Fin de onClickListener
    		
    		//ONCLICK PARA REALIZADAS
    		OnClickListener clicks2=new OnClickListener() {

    		    @Override
    		    public void onClick(View v) {
    		    		//Esta es la posicion del array padre sobre el que se hizo click
    		    		System.out.println("id = "+v.getId());  

	    		        Fragment duedateFrag = new AccionRealizada();	
	    		        Bundle bundle = new Bundle();
	    		                	    		                
	    		        String miArreglo[] = new String[6];
	    		        
	    		        miArreglo[0] = arrayRealizadasActual.get(v.getId()).get(0);		//Llenar con la fecha
				        miArreglo[1] = arrayRealizadasActual.get(v.getId()).get(1);		//Llenar con la hora
				        miArreglo[2] = arrayRealizadasActual.get(v.getId()).get(2);		//Sala	
				        miArreglo[3] = arrayRealizadasActual.get(v.getId()).get(3);		//Paciente
				        miArreglo[4] = arrayRealizadasActual.get(v.getId()).get(4);		//Diagnostico
				        miArreglo[5] = arrayRealizadasActual.get(v.getId()).get(5);		//ID
	    		                	    		                	    		                
	    		        bundle.putStringArray("parametro", miArreglo); //Arreglo para mandar a Accion
	    		        duedateFrag.setArguments(bundle);
	    		                
	    		        FragmentTransaction ft  = getFragmentManager().beginTransaction();
	    		        ft.replace(R.id.content_frame, duedateFrag);
	    		        ft.addToBackStack(null);
	    		        ft.commit();
     
    		    } //Fin de onClick
    		}; //Fin de onClickListener
    		
    		//ONCLICK PARA DIFERIDAS
    		OnClickListener clicks3=new OnClickListener() {

    		    @Override
    		    public void onClick(View v) {
    		    		//Esta es la posicion del array padre sobre el que se hizo click
    		    		System.out.println("id = "+v.getId());  
    		
	    		        Fragment duedateFrag = new AccionDiferida();	
	    		        Bundle bundle = new Bundle();
	    		                	    		                
	    		        String miArreglo[] = new String[6];
	    		        
	    		        miArreglo[0] = arrayDiferidasActual.get(v.getId()).get(0);		//Llenar con la fecha
				        miArreglo[1] = arrayDiferidasActual.get(v.getId()).get(1);		//Llenar con la hora
				        miArreglo[2] = arrayDiferidasActual.get(v.getId()).get(2);		//Sala	
				        miArreglo[3] = arrayDiferidasActual.get(v.getId()).get(3);		//Paciente
				        miArreglo[4] = arrayDiferidasActual.get(v.getId()).get(4);		//Diagnostico
				        miArreglo[5] = arrayDiferidasActual.get(v.getId()).get(5);		//ID
	    		                	    		                	    		                
	    		        bundle.putStringArray("parametro", miArreglo); //Arreglo para mandar a Accion
	    		        duedateFrag.setArguments(bundle);
	    		                
	    		        FragmentTransaction ft  = getFragmentManager().beginTransaction();
	    		        ft.replace(R.id.content_frame, duedateFrag);
	    		        ft.addToBackStack(null);
	    		        ft.commit();
	    		          
    		    } //Fin de onClick
    		}; //Fin de onClickListener

    		//ONCLICK PARA PROGRAMADAS PASADAS
    		OnClickListener clicks4=new OnClickListener() {

    		    @Override
    		    public void onClick(View v) {
    		    		//Esta es la posicion del array padre sobre el que se hizo click
    		    		System.out.println("id = "+v.getId());  

	    		        Fragment duedateFrag = new AccionProgramadaPasada(); //se puede diferir y se puede eliminar	
	    		        Bundle bundle = new Bundle();
	    		                	    		                
	    		        String miArreglo[] = new String[6];
	    		        
	    		        miArreglo[0] = arrayProgramadasPasadas.get(v.getId()).get(0);	//Llenar con la fecha
				        miArreglo[1] = arrayProgramadasPasadas.get(v.getId()).get(1);	//Llenar con la hora
				        miArreglo[2] = arrayProgramadasPasadas.get(v.getId()).get(2);	//Sala	
				        miArreglo[3] = arrayProgramadasPasadas.get(v.getId()).get(3);	//Paciente
				        miArreglo[4] = arrayProgramadasPasadas.get(v.getId()).get(4);	//Diagnostico
				        miArreglo[5] = arrayProgramadasPasadas.get(v.getId()).get(5);	//ID
	    		                	    		                	    		                
	    		        bundle.putStringArray("parametro", miArreglo); //Arreglo para mandar a Accion
	    		        duedateFrag.setArguments(bundle);
	    		                
	    		        FragmentTransaction ft  = getFragmentManager().beginTransaction();
	    		        ft.replace(R.id.content_frame, duedateFrag);
	    		        ft.addToBackStack(null);
	    		        ft.commit();
     
    		    } //Fin de onClick
    		}; //Fin de onClickListener
        	
        	View tab = inflater.inflate(R.layout.tiposde_cirugias, container, false);
        	TextView titulo = (TextView)tab.findViewById(R.id.title);
        	titulo.setText("Programada");
			TableRow tabRow = (TableRow) tab;
			tl.addView(tabRow);

    		//PARA LAS PROGRAMADAS *********************************************************************
        	for (int i = 0; i<arrayProgramadasActual.size(); i++){
    			
    			View tabler = inflater.inflate(R.layout.newtablerow, container, false);
    			TextView fech = (TextView)tabler.findViewById(R.id.fech);
				TextView hora = (TextView)tabler.findViewById(R.id.hora);
				TextView sala = (TextView)tabler.findViewById(R.id.sala);
				//Registro
				TextView registro = (TextView)tabler.findViewById(R.id.registro);
		
				TextView pa = (TextView)tabler.findViewById(R.id.pa);
				TextView dg = (TextView)tabler.findViewById(R.id.dg);
				//procedimiento - cirugia
				TextView procedimiento = (TextView)tabler.findViewById(R.id.procedimiento);
				
				//medico que programo
				TextView medico = (TextView)tabler.findViewById(R.id.medico);
				
				TableRow trow = (TableRow) tabler;
				
				fech.setText(arrayProgramadasActual.get(i).get(0));				//fecha
				hora.setText(arrayProgramadasActual.get(i).get(1));				//hora
				sala.setText(arrayProgramadasActual.get(i).get(2));				//sala
				registro.setText(arrayProgramadasActual.get(i).get(6));			//registro -new 10Dic
				pa.setText(arrayProgramadasActual.get(i).get(3));				//paciente
				dg.setText(arrayProgramadasActual.get(i).get(4));				//diagnostico
								
				int counter = 0;
				String tempo = "";
				
				for (int x = 0; x<procedimientosProgramadaActual.size(); x++){
					if(arrayProgramadasActual.get(i).get(5).equals(procedimientosProgramadaActual.get(x).get(0))){
						if(counter == 0){
							tempo += "Procedimiento "+(counter+1)+
									", id = "+procedimientosProgramadaActual.get(x).get(1)+
									", cie9mc = "+procedimientosProgramadaActual.get(x).get(2)+
									", región = "+procedimientosProgramadaActual.get(x).get(3);
						}
						else{
							tempo += "\nProcedimiento "+(counter+1)+
									", id = "+procedimientosProgramadaActual.get(x).get(1)+
									", cie9mc = "+procedimientosProgramadaActual.get(x).get(2)+
									", región = "+procedimientosProgramadaActual.get(x).get(3); 
						}
						counter = counter+1;
					}//Fin de if
				}//Fin de for para procedimientos
				procedimiento.setText(tempo);
				
				
				medico.setText(arrayProgramadasActual.get(i).get(7));			//nombre del medico
								
				trow.setBackgroundResource(android.R.drawable.list_selector_background);
					
				tl.addView(trow);
    			//Primero pasamos la posicion del arraypadre en el que se hizo click
    			trow.setId(i);
    			//Opcion2 - settear Id con la posición de "padre" en que se hizo click
    			trow.setOnClickListener(clicks);
    		}//Fin de for
              	
        	View tab1 = inflater.inflate(R.layout.tiposde_cirugias, container, false);
        	TextView titulo1 = (TextView)tab1.findViewById(R.id.title);
        	titulo1.setText("Transoperatorio");
			TableRow tabRow1 = (TableRow) tab1;
			tl.addView(tabRow1);
        	
        	//PARA TRANSOPERATORIO ********************************************************************
        	for (int i = 0; i<arrayTransoperatorioActual.size(); i++){
        		
        		View tabler = inflater.inflate(R.layout.newtablerow, container, false);
        		TextView fech = (TextView)tabler.findViewById(R.id.fech);
				TextView hora = (TextView)tabler.findViewById(R.id.hora);
				TextView sala = (TextView)tabler.findViewById(R.id.sala);
				//Registro
				TextView registro = (TextView)tabler.findViewById(R.id.registro);
		
				TextView pa = (TextView)tabler.findViewById(R.id.pa);
				TextView dg = (TextView)tabler.findViewById(R.id.dg);
				//procedimiento - cirugia
				TextView procedimiento = (TextView)tabler.findViewById(R.id.procedimiento);
				
				//medico que programo
				TextView medico = (TextView)tabler.findViewById(R.id.medico);
				
				TableRow trow = (TableRow) tabler;
				
				fech.setText(arrayTransoperatorioActual.get(i).get(0));	//fecha
				hora.setText(arrayTransoperatorioActual.get(i).get(1));	//hora
				sala.setText(arrayTransoperatorioActual.get(i).get(2));	//sala
				registro.setText(arrayTransoperatorioActual.get(i).get(6));					//registro -new 10Dic
				pa.setText(arrayTransoperatorioActual.get(i).get(3));		//paciente
				dg.setText(arrayTransoperatorioActual.get(i).get(4));		//diagnostico
				
				int counter = 0;
				String tempo = "";
				
				for (int x = 0; x<procedimientosTransoperatorioActual.size(); x++){
					if(arrayTransoperatorioActual.get(i).get(5).equals(procedimientosTransoperatorioActual.get(x).get(0))){
						if(counter == 0){
							tempo += "Procedimiento "+(counter+1)+
									", id = "+procedimientosTransoperatorioActual.get(x).get(1)+
									", cie9mc = "+procedimientosTransoperatorioActual.get(x).get(2)+
									", región = "+procedimientosTransoperatorioActual.get(x).get(3);
						}
						else{
							tempo += "\nProcedimiento "+(counter+1)+
									", id = "+procedimientosTransoperatorioActual.get(x).get(1)+
									", cie9mc = "+procedimientosTransoperatorioActual.get(x).get(2)+
									", región = "+procedimientosTransoperatorioActual.get(x).get(3); 
						}
						counter = counter+1;
					}//Fin de if
				}//Fin de for para procedimientos
				procedimiento.setText(tempo);
				
				medico.setText(arrayTransoperatorioActual.get(i).get(7));						//medico que programa la cirugia
				trow.setBackgroundResource(R.drawable.selector_trans);
					
				tl.addView(trow);
    			//Primero pasamos la posicion del arraypadre en el que se hizo click
    			trow.setId(i);
    			//Opcion2 - settear Id con la posición de "padre" en que se hizo click
    			trow.setOnClickListener(clicks1);
        		
    		}//Fin de for
        	
        	View tab2 = inflater.inflate(R.layout.tiposde_cirugias, container, false);
        	TextView titulo2 = (TextView)tab2.findViewById(R.id.title);
        	titulo2.setText("Realizadas");
			TableRow tabRow2 = (TableRow) tab2;
			tl.addView(tabRow2);
        	
        	//PARA REALIZADAS *************************************************************************
        	for (int i = 0; i<arrayRealizadasActual.size(); i++){
        		
        		View tabler = inflater.inflate(R.layout.newtablerow, container, false);
        		TextView fech = (TextView)tabler.findViewById(R.id.fech);
				TextView hora = (TextView)tabler.findViewById(R.id.hora);
				TextView sala = (TextView)tabler.findViewById(R.id.sala);
				//Registro
				TextView registro = (TextView)tabler.findViewById(R.id.registro);
		
				TextView pa = (TextView)tabler.findViewById(R.id.pa);
				TextView dg = (TextView)tabler.findViewById(R.id.dg);
				//procedimiento - cirugia
				TextView procedimiento = (TextView)tabler.findViewById(R.id.procedimiento);
				
				//medico que programo
				TextView medico = (TextView)tabler.findViewById(R.id.medico);
				
				TableRow trow = (TableRow) tabler;
				
				fech.setText(arrayRealizadasActual.get(i).get(0));	//fecha
				hora.setText(arrayRealizadasActual.get(i).get(1));	//hora
				sala.setText(arrayRealizadasActual.get(i).get(2));	//sala
				registro.setText(arrayRealizadasActual.get(i).get(6));				//registro -new 10Dic
				pa.setText(arrayRealizadasActual.get(i).get(3));		//paciente
				dg.setText(arrayRealizadasActual.get(i).get(4));		//diagnostico
				
				int counter = 0;
				String tempo = "";
				
				for (int x = 0; x<procedimientosRealizadaActual.size(); x++){
					if(arrayRealizadasActual.get(i).get(5).equals(procedimientosRealizadaActual.get(x).get(0))){
						if(counter == 0){
							tempo += "Procedimiento "+(counter+1)+
									", id = "+procedimientosRealizadaActual.get(x).get(1)+
									", cie9mc = "+procedimientosRealizadaActual.get(x).get(2)+
									", región = "+procedimientosRealizadaActual.get(x).get(3);
						}
						else{
							tempo += "\nProcedimiento "+(counter+1)+
									", id = "+procedimientosRealizadaActual.get(x).get(1)+
									", cie9mc = "+procedimientosRealizadaActual.get(x).get(2)+
									", región = "+procedimientosRealizadaActual.get(x).get(3); 
						}
						counter = counter+1;
					}//Fin de if
				}//Fin de for para procedimientos
				procedimiento.setText(tempo);				
				medico.setText(arrayRealizadasActual.get(i).get(7));					//medico que programa la cirugia
					
				tl.addView(trow);
    			//Primero pasamos la posicion del arraypadre en el que se hizo click
    			trow.setId(i);
    			//Opcion2 - settear Id con la posición de "padre" en que se hizo click
    			trow.setOnClickListener(clicks2);
        		
    		}//Fin de for
        
        	View tab3 = inflater.inflate(R.layout.tiposde_cirugias, container, false);
        	TextView titulo3 = (TextView)tab3.findViewById(R.id.title);
        	titulo3.setText("Diferidas");
			TableRow tabRow3 = (TableRow) tab3;
			tl.addView(tabRow3);
        	
        	//PARA DIFERIDAS **************************************************************************
        	for (int i = 0; i<arrayDiferidasActual.size(); i++){
        		
        		View tabler = inflater.inflate(R.layout.newtablerow, container, false);
        		TextView fech = (TextView)tabler.findViewById(R.id.fech);
				TextView hora = (TextView)tabler.findViewById(R.id.hora);
				TextView sala = (TextView)tabler.findViewById(R.id.sala);
				//Registro
				TextView registro = (TextView)tabler.findViewById(R.id.registro);
		
				TextView pa = (TextView)tabler.findViewById(R.id.pa);
				TextView dg = (TextView)tabler.findViewById(R.id.dg);
				//procedimiento - cirugia
				TextView procedimiento = (TextView)tabler.findViewById(R.id.procedimiento);
				
				//medico que programo
				TextView medico = (TextView)tabler.findViewById(R.id.medico);
				
				TableRow trow = (TableRow) tabler;
				
				fech.setText(arrayDiferidasActual.get(i).get(0));	//fecha
				fech.setTextColor(Color.parseColor("#ffffff"));
				
				hora.setText(arrayDiferidasActual.get(i).get(1));	//hora
				hora.setTextColor(Color.parseColor("#ffffff"));
				
				sala.setText(arrayDiferidasActual.get(i).get(2));	//sala
				sala.setTextColor(Color.parseColor("#ffffff"));
				
				registro.setText(arrayDiferidasActual.get(i).get(6));				//registro -new 10Dic
				registro.setTextColor(Color.parseColor("#ffffff"));
				
				pa.setText(arrayDiferidasActual.get(i).get(3));	//paciente
				pa.setTextColor(Color.parseColor("#ffffff"));
				
				dg.setText(arrayDiferidasActual.get(i).get(4));	//diagnostico
				dg.setTextColor(Color.parseColor("#ffffff"));
				
				int counter = 0;
				String tempo = "";
				
				for (int x = 0; x<procedimientosDiferidaActual.size(); x++){
					if(arrayDiferidasActual.get(i).get(5).equals(procedimientosDiferidaActual.get(x).get(0))){
						if(counter == 0){
							tempo += "Procedimiento "+(counter+1)+
									", id = "+procedimientosDiferidaActual.get(x).get(1)+
									", cie9mc = "+procedimientosDiferidaActual.get(x).get(2)+
									", región = "+procedimientosDiferidaActual.get(x).get(3);
						}
						else{
							tempo += "\nProcedimiento "+(counter+1)+
									", id = "+procedimientosDiferidaActual.get(x).get(1)+
									", cie9mc = "+procedimientosDiferidaActual.get(x).get(2)+
									", región = "+procedimientosDiferidaActual.get(x).get(3); 
						}
						counter = counter+1;
					}//Fin de if
				}//Fin de for para procedimientos
				
				procedimiento.setText(tempo);
				procedimiento.setTextColor(Color.parseColor("#ffffff"));
				
				medico.setText(arrayDiferidasActual.get(i).get(7));					//medico que programa la cirugia
				medico.setTextColor(Color.parseColor("#ffffff"));
				
				//accion.setText("action:"+index);
				trow.setBackgroundResource(R.drawable.selector_dif);		
					
				tl.addView(trow);
    			//Primero pasamos la posicion del arraypadre en el que se hizo click
    			trow.setId(i);
    			//Opcion2 - settear Id con la posición de "padre" en que se hizo click
    			trow.setOnClickListener(clicks3);
        		
        		
    		}//Fin de for
        }//Fin de onPostExecute        
	}//Fin de la subclase AgendaDelDia
	
	/** *******************************************************************///
	//Sub clase para obtener los servicio del spinner de programar cirugia
	class GetServicios extends AsyncTask< String, String, ArrayList<String>> {
		
		LayoutInflater inflater;
		View programar;
		ViewGroup container;
		
		GetServicios(LayoutInflater inflater,View programar, ViewGroup container){
			this.inflater = inflater;
			this.programar = programar;
			this.container = container;
		}
		
		String quir; //El string llevara el quirofano_id
			
	    protected void onPreExecute() {
	    	super.onPreExecute();
	    }
	    	
    	protected ArrayList<String> doInBackground(String... params) {
			quir=params[0]; //obtenemos el string de quirofano_id 
			ArrayList<String> tempoServicios = new ArrayList<String>();
			tempoServicios = getServicios(quir);
    		return tempoServicios;
		}//Fin de doInBackground
	       
    	protected void onPostExecute(ArrayList<String> resultado) {	    		
	    	nombreServicios = resultado;
	    	System.out.println("LOS-SERVICIOS = "+nombreServicios);
	    	ProgramarCirugiaView pcv = new ProgramarCirugiaView();
			pcv.prog(inflater, programar, getActivity());
    	}//Fin de onPostExecute        		
	}//Fin de la subclase GetServicios
	
	/*DatePicker - 26 octubre*/
	//ESTA SUB-CLASE YA NO SE USA
	class GetQuirofanoId extends AsyncTask< String, String, String> {
		
    	String quir; //El string llevara el quirofano_name
		
    	protected void onPreExecute() {
    		//progress = ProgressDialog.show(
    		//getActivity(), null, "Accesando a agenda...");
            super.onPreExecute();
        }
    	
        protected String doInBackground(String... params) {
			quir=params[0]; //obtenemos el string de quirofano_name 
			
			getQuirofanoId(quir);
			//Log.e("last-array", "last-array = "+al.get(0));
    		return "ok"; //login valido
    		
		}//Fin de doInBackground
       
        protected void onPostExecute(String resultado) {
        	//progress.dismiss();//ocultamos progess dialog.
            //Log.e("onPostExecute=","Todo bien="+resultado);
            //return al;
            //mostrarLeyenda();
            
        }//Fin de onPostExecute        
		
	}//Fin de la subclase GetQuirofanoId
	
	
	//GETTERS
	public int getSala(){
		return salaSpinner;
	}//Fin de getSala
	
	// *** PARA EL DATE PICKER **************************************************************************
	
	private void showDatePicker() {
		  DatePickerFragment date = new DatePickerFragment();
		  /**
		   * Set Up Current Date Into dialog
		   */
		  Calendar calender = Calendar.getInstance();
		  Bundle args = new Bundle();
		  args.putInt("year", calender.get(Calendar.YEAR));
		  args.putInt("month", calender.get(Calendar.MONTH));
		  args.putInt("day", calender.get(Calendar.DAY_OF_MONTH));
		  date.setArguments(args);
		  /**
		   * Set Call back to capture selected date
		   */
		  date.setCallBack(ondate);
		  date.show(getFragmentManager(), "Date Picker");
	}
	
	//2
	OnDateSetListener ondate = new OnDateSetListener() {
		  @Override
		  public void onDateSet(DatePicker view, int year, int monthOfYear, //antes int
		    int dayOfMonth) {
//			  anio = year;
//			  mesDelAnio = monthOfYear+1;
//			  diaDelMes = dayOfMonth;
			  sAnio = Integer.toString(year);
			  monthOfYear = monthOfYear+1;
			  if(monthOfYear<10){
				  sMesDelAnio = "0"+Integer.toString(monthOfYear);
			  }
			  else{
				  sMesDelAnio = Integer.toString(monthOfYear);
			  }
			  
			  if(dayOfMonth<10){
				  sDiaDelMes = "0"+Integer.toString(dayOfMonth);
			  }
			  else{
				  sDiaDelMes = Integer.toString(dayOfMonth);
			  }
			  
//		   Toast.makeText(getActivity().getApplicationContext(), String.valueOf(year) + "-" + String.valueOf(monthOfYear+1) + "-" + String.valueOf(dayOfMonth), Toast.LENGTH_SHORT).show();
		   Toast.makeText(getActivity().getApplicationContext(), String.valueOf(sAnio) + "-" + String.valueOf(sMesDelAnio) + "-" + String.valueOf(sDiaDelMes), Toast.LENGTH_SHORT).show();
//		   String all = Integer.toString(anio)+"-"+ Integer.toString(mesDelAnio)+"-"+ Integer.toString(diaDelMes);
		   String all = sAnio+"-"+sMesDelAnio+"-"+sDiaDelMes;
		   pDisplayDate.setText("  "+all);
		  }
		 };
		 
	// *** PARA EL DATE PICKER **************************************************************************
	//PARA TIMEPICKER ***********************************************************************************
		 private void showTimePicker() {
			  TimePickerFragment time = new TimePickerFragment();
			  /**
			   * Set Up Current Date Into dialog
			   */
			  Calendar calender = Calendar.getInstance();
			  Bundle args = new Bundle();
			  args.putInt("hour", calender.get(Calendar.YEAR));
			  args.putInt("minutes", calender.get(Calendar.MONTH));
			  time.setArguments(args);
			  /**
			   * Set Call back to capture selected date
			   */
			  time.setCallBack(ontime);
			  time.show(getFragmentManager(), "Date Picker");
		}
		
		//2
		OnTimeSetListener ontime = new OnTimeSetListener() {
			  @Override
			  public void onTimeSet(TimePicker view, int hour, int minutes) {
				  
				  if (hour<10){
					  sHoras = "0"+Integer.toString(hour);
				  }
				  else{
					  sHoras = Integer.toString(hour);
				  }
				  if (minutes<10){
					  sMinutos = "0"+Integer.toString(minutes);
				  }
				  else{
					  sMinutos = Integer.toString(minutes); 
				  }
				  
				  horas = hour;
				  minutos = minutes;
			   Toast.makeText(getActivity().getApplicationContext(), String.valueOf(sHoras) + ":" + String.valueOf(sMinutos), Toast.LENGTH_SHORT).show();
//			   String all = Integer.toString(horas)+":"+ Integer.toString(minutos);
			   String all = sHoras+":"+sMinutos;
			   mPickedTimeText.setText("  "+all);
			  }
			 };
		 
	//PARA TIMEPICKER ***********************************************************************************
			 
	class RegistroDiferida2 extends AsyncTask<String, String, String> {

		LayoutInflater inflater;
		ViewGroup container;

		RegistroDiferida2(LayoutInflater inflater, ViewGroup container) {
			this.inflater = inflater;
			this.container = container;
		}
		String st1; // String con el id del quirofano seleccionado

		protected void onPreExecute() {
			// progress = ProgressDialog.show(
			// getActivity(), null, "Accesando a agenda...");
			super.onPreExecute();
		}

		protected String doInBackground(String... params) {
			st1 = params[0]; // obtenemos el string con "ok"

			// enviamos y recibimos y analizamos los datos en segundo plano.
			try {
				mostrarRegistroDiferida2(st1);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// Log.e("last-array", "last-array = "+al.get(0));
			return "ok"; // login valido
		}// Fin de doInBackground

		protected void onPostExecute(String resultado) {
//			Log.e("onPostExecute=", "Todo bien=" + resultado);
		}// Fin de onPostExecute
	}// Fin de la subclase RegistroDiferida2
	
	public void mostrarRegistroDiferida2(String s) throws JSONException{	
		arrayDiferidas2.clear();
		String val = "", value = "", value1 = "", value2 = "", value3 = "", value4 = "",
				value5="", value6="",cont = "";
		
		ArrayList<NameValuePair> datosEnviar= new ArrayList<NameValuePair>();
		datosEnviar.add(new BasicNameValuePair("stat", s));

		JSONArray jdata = envio.getserverdata(datosEnviar, URL_connect7);
		//System.out.println("jdata = "+jdata.getString(0));
		System.out.println(jdata.toString());
		System.out.println("largo de jdata = "+jdata.length());
		if (jdata!=null && jdata.length() > 0){
    		//JSONObject json_data; //creamos un objeto JSON
			try {
				
				for(int n = 0; n < jdata.length(); n++){
					//st.clear();
					System.out.println("vuelta:"+n);
					JSONObject json_data = jdata.getJSONObject(n);
					val = json_data.getString("dat");		//Fecha
					value = json_data.getString("dato");	//Hora
					value1 = json_data.getString("dato1");	//Sala
					value2 = json_data.getString("dato2");	//Paciente
					value3 = json_data.getString("dato3");	//Diagnostico
					value4 = json_data.getString("dato4");	//ID
					value5 = json_data.getString("dato5");	//Registro
					value6 = json_data.getString("dato6"); 	//Nombre del dr que programa
					
					ArrayList<String> temporary = new ArrayList<String>();
					
					temporary.add(val);
					temporary.add(value);
					temporary.add(value1);
					temporary.add(value2);
					temporary.add(value3);
					temporary.add(value4);
					temporary.add(value5);
					temporary.add(value6);
		
					Log.e("RegistroDiferida2", "array temporary = "+temporary);
					
					arrayDiferidas2.add(temporary);
				}

				Log.e("array RegistroDiferida2", "arrayDiferidas2 = "+arrayDiferidas2);
		
				//Log.e("enviarFormulario","status= "+status);//muestro por log que obtuvimos
			}
			catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				Log.e("hi", "hi");
			}		            	
    	}//Fin de if(comprueba si lo obtenido no es "null")
    	
    	else{	//json obtenido invalido verificar parte WEB.
    		Log.e("JSON  ", "ERROR");
	    	//return st;
	    }
	}//Fin de mostrarRegistroDiferida2
	
	/** OBTENER CIRUGIAS CANCELADAS */
	
	class GetCirugiasCanceladas extends AsyncTask< String, String, String> {
		
		LayoutInflater inflater;
		ViewGroup container;
		
		GetCirugiasCanceladas(LayoutInflater inflater, ViewGroup container){
			this.inflater = inflater;
			this.container = container;
		}
		
    	String st1; //String con el id del quirofano seleccionado
		
    	protected void onPreExecute() {
    		//progress = ProgressDialog.show(
    		//getActivity(), null, "Accesando a agenda...");
            super.onPreExecute();
        }
    	
        protected String doInBackground(String... params) {
			st1=params[0]; //obtenemos el string con "ok" 
			
			//enviamos y recibimos y analizamos los datos en segundo plano.
    		try {
				mostrarRegistroCancelada(st1);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//Log.e("last-array", "last-array = "+al.get(0));
    		return "ok"; //login valido
    		
		}//Fin de doInBackground
       
        protected void onPostExecute(String resultado) {
        	//progress.dismiss();//ocultamos progess dialog.
            Log.e("onPostExecute=","Todo bien="+resultado);
            
            //ONCLICK PARA DIFERIDAS
    		OnClickListener clicks3=new OnClickListener() {

    		    @Override
    		    public void onClick(View v) {
    		    		//Esta es la posicion del array padre sobre el que se hizo click
    		    		System.out.println("id = "+v.getId());  
    		    		
	    		        Fragment duedateFrag = new AccionDiferida();	
	    		        Bundle bundle = new Bundle();
	    		                	    		                
	    		        String miArreglo[] = new String[6];
	    		        
	    		        miArreglo[0] = arrayCanceladas.get(v.getId()).get(0);		//Llenar con la fecha
				        miArreglo[1] = arrayCanceladas.get(v.getId()).get(1);		//Llenar con la hora
				        miArreglo[2] = arrayCanceladas.get(v.getId()).get(2);		//Sala	
				        miArreglo[3] = arrayCanceladas.get(v.getId()).get(3);		//Paciente
				        miArreglo[4] = arrayCanceladas.get(v.getId()).get(4);		//Diagnostico
				        miArreglo[5] = arrayCanceladas.get(v.getId()).get(5);		//ID
	    		                	    		                	    		                
	    		        bundle.putStringArray("parametro", miArreglo); //Arreglo para mandar a Accion
	    		        duedateFrag.setArguments(bundle);
	    		                
	    		        FragmentTransaction ft  = getFragmentManager().beginTransaction();
	    		        ft.replace(R.id.content_frame, duedateFrag);
	    		        ft.addToBackStack(null);
	    		        ft.commit();
    		                
    		    } //Fin de onClick
    		}; //Fin de onClickListener
    		
    		View tab = inflater.inflate(R.layout.tiposde_cirugias, container, false);
        	TextView titulo4 = (TextView)tab.findViewById(R.id.title);
        	titulo4.setText("Cirugías canceladas");
			TableRow tabRow4 = (TableRow) tab;
			tl.addView(tabRow4);
    		
            //PARA CANCELADAS **************************************************************************
        	for (int i = 0; i<arrayCanceladas.size(); i++){
        		
        		View tabler = inflater.inflate(R.layout.newtablerow, container, false);
        		TextView fech = (TextView)tabler.findViewById(R.id.fech);
				TextView hora = (TextView)tabler.findViewById(R.id.hora);
				TextView sala = (TextView)tabler.findViewById(R.id.sala);
				//Registro
				TextView registro = (TextView)tabler.findViewById(R.id.registro);
		
				TextView pa = (TextView)tabler.findViewById(R.id.pa);
				TextView dg = (TextView)tabler.findViewById(R.id.dg);
				//procedimiento - cirugia
				TextView procedimiento = (TextView)tabler.findViewById(R.id.procedimiento);
				
				//medico que programo
				TextView medico = (TextView)tabler.findViewById(R.id.medico);
				
				TableRow trow = (TableRow) tabler;
				
				fech.setText(arrayCanceladas.get(i).get(0));	//fecha
				fech.setTextColor(Color.parseColor("#ffffff"));
				
				hora.setText(arrayCanceladas.get(i).get(1));	//hora
				hora.setTextColor(Color.parseColor("#ffffff"));
				
				sala.setText(arrayCanceladas.get(i).get(2));	//sala
				sala.setTextColor(Color.parseColor("#ffffff"));
				
				registro.setText(arrayCanceladas.get(i).get(6));				//registro -new 10Dic
				registro.setTextColor(Color.parseColor("#ffffff"));
				
				pa.setText(arrayCanceladas.get(i).get(3));	//paciente
				pa.setTextColor(Color.parseColor("#ffffff"));
				
				dg.setText(arrayCanceladas.get(i).get(4));	//diagnostico
				dg.setTextColor(Color.parseColor("#ffffff"));
				
				int counter = 0;
				String tempo = "";
				
				for (int x = 0; x<arrayProcedimientosCancelada.size(); x++){
					if(arrayCanceladas.get(i).get(5).equals(arrayProcedimientosCancelada.get(x).get(0))){
						if(counter == 0){
							tempo += "Procedimiento "+(counter+1)+
									", id = "+arrayProcedimientosCancelada.get(x).get(1)+
									", cie9mc = "+arrayProcedimientosCancelada.get(x).get(2)+
									", región = "+arrayProcedimientosCancelada.get(x).get(3);
						}
						else{
							tempo += "\nProcedimiento "+(counter+1)+
									", id = "+arrayProcedimientosCancelada.get(x).get(1)+
									", cie9mc = "+arrayProcedimientosCancelada.get(x).get(2)+
									", región = "+arrayProcedimientosCancelada.get(x).get(3); 
						}
						counter = counter+1;
					}//Fin de if
				}//Fin de for para procedimientos
				
				procedimiento.setText(tempo);
				procedimiento.setTextColor(Color.parseColor("#ffffff"));
				
				medico.setText(arrayCanceladas.get(i).get(7));					//medico que programa la cirugia
				medico.setTextColor(Color.parseColor("#ffffff"));
				
				trow.setBackgroundResource(R.drawable.selector_dif);		
					
				tl.addView(trow);
    			//Primero pasamos la posicion del arraypadre en el que se hizo click
    			trow.setId(i);
    			//Opcion2 - settear Id con la posición de "padre" en que se hizo click
    			trow.setOnClickListener(clicks3);
     
    		}//Fin de for
        }//Fin de onPostExecute        
	}//Fin de la subclase GetCirugiasCanceladas
	
	/** FIN DE OBTENER CIRUGIAS CANCELADAS */
	
	public void mostrarRegistroCancelada(String s) throws JSONException{	
		arrayCanceladas.clear();
		String val = "", value = "", value1 = "", value2 = "", value3 = "", value4 = "",
				value5="", value6="",cont = "";
		
		ArrayList<NameValuePair> datosEnviar= new ArrayList<NameValuePair>();
		datosEnviar.add(new BasicNameValuePair("stat", s));

		JSONArray jdata = envio.getserverdata(datosEnviar, URL_connect25);
		//System.out.println("jdata = "+jdata.getString(0));
		System.out.println(jdata.toString());
		System.out.println("largo de jdata = "+jdata.length());
		if (jdata!=null && jdata.length() > 0){
    		//JSONObject json_data; //creamos un objeto JSON
			try {
				
				for(int n = 0; n < jdata.length(); n++){
					//st.clear();
					System.out.println("vuelta:"+n);
					JSONObject json_data = jdata.getJSONObject(n);
					val = json_data.getString("dat");		//Fecha
					value = json_data.getString("dato");	//Hora
					value1 = json_data.getString("dato1");	//Sala
					value2 = json_data.getString("dato2");	//Paciente
					value3 = json_data.getString("dato3");	//Diagnostico
					value4 = json_data.getString("dato4");	//ID
					value5 = json_data.getString("dato5");	//Registro
					value6 = json_data.getString("dato6"); 	//Nombre del dr que programa
					
					ArrayList<String> temporary = new ArrayList<String>();
					
					temporary.add(val);
					temporary.add(value);
					temporary.add(value1);
					temporary.add(value2);
					temporary.add(value3);
					temporary.add(value4);
					temporary.add(value5);
					temporary.add(value6);
		
					Log.e("RegistroCancelada", "array temporary = "+temporary);
					
					arrayCanceladas.add(temporary);
				}

				Log.e("array RegistroCanceladas", "arrayCanceladas = "+arrayCanceladas);
		
				//Log.e("enviarFormulario","status= "+status);//muestro por log que obtuvimos
			}
			catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				Log.e("hi", "hi");
			}		            	
    	}//Fin de if(comprueba si lo obtenido no es "null")
    	
    	else{	//json obtenido invalido verificar parte WEB.
    		Log.e("JSON  ", "ERROR");
	    	//return st;
	    }
	}//Fin de mostrarRegistroCancelada
	
	// GetProcedimientosCancelada
	class GetProcedimientosCancelada extends AsyncTask< String, String, String> {
		
    	String st1; //String con el id del quirofano seleccionado
		
    	protected void onPreExecute() {
            super.onPreExecute();
        }
        protected String doInBackground(String... params) {
			st1=params[0]; //obtenemos el string con "ok" 
			
    		try {
				getProcedimientosCancelada(st1);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//Log.e("last-array", "last-array = "+al.get(0));
    		return "ok"; //login valido
		}//Fin de doInBackground
       
        protected void onPostExecute(String resultado) {
        	//progress.dismiss();//ocultamos progess dialog.
            Log.e("onPostExecute=","Todo bien="+resultado);
            System.out.println(">>>ARRAYCANCELADA*** : "+arrayProcedimientosCancelada);
        }//Fin de onPostExecute        
	}//Fin de la subclase GetProcedimientosCancelada
	
	/** OBTENER PROCEDIMIENTOS DE CIRUGIAS CANCELADAS*/
	public void getProcedimientosCancelada(String s) throws JSONException{	
		arrayProcedimientosCancelada.clear();
		String val = "", value = "", value1 = "", value2 = "", value3 = "", cont = "";
			
		ArrayList<NameValuePair> datosEnviar= new ArrayList<NameValuePair>();
		datosEnviar.add(new BasicNameValuePair("stat", s));

		JSONArray jdata = envio.getserverdata(datosEnviar, URL_connect26);
		//System.out.println("jdata = "+jdata.getString(0));
		System.out.println(jdata.toString());
		System.out.println("largo de jdata = "+jdata.length());
		if (jdata!=null && jdata.length() > 0){
	   		//JSONObject json_data; //creamos un objeto JSON
			try {
					
				for(int n = 0; n < jdata.length(); n++){
					//st.clear();
					System.out.println("vuelta:"+n);
					JSONObject json_data = jdata.getJSONObject(n);
					cont = json_data.getString("d");	//id de la cirugia
					val = json_data.getString("d0");	//id de procedimiento
					value = json_data.getString("d1");	//cie9mc
					value1 = json_data.getString("d2");	//region
					value2 = json_data.getString("d3");	//detalles
					value3 = json_data.getString("d4");	//servicio
						
					ArrayList<String> temporary = new ArrayList<String>();
						
					temporary.add(cont);
					temporary.add(val);
					temporary.add(value);
					temporary.add(value1);
					temporary.add(value2);
					temporary.add(value3);
			
					Log.e("Tempo-dif", "tempo-dif = "+temporary);
						
					arrayProcedimientosCancelada.add(temporary);
				}
				
				Log.e("array Procedimientos Cancelada", "arrayProcedimientosCancelada = "+arrayProcedimientosCancelada);
				//Log.e("enviarFormulario","status= "+status);//muestro por log que obtuvimos
			}
			catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				Log.e("hi", "hi");
			}		            	
	    }//Fin de if(comprueba si lo obtenido no es "null")
	    	
	    else{	//json obtenido invalido verificar parte WEB.
	    	Log.e("JSON  ", "ERROR");
		   	//return st;
			}
	}//Fin de getProcedimientosCancelada
}//Fin de la clase Item1