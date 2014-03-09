package com.hu.quirofano;

import java.util.ArrayList;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.actionbarsherlock.app.SherlockFragment;
import com.hu.libreria.HttpPostAux;
import com.hu.quirofano.Item4.agendaDelDia;
//<<<<<<< HEAD
//prueba1
//	=======
//>>>>>>> f430b8bdebb5172a872119a5ea0a4c8815fe53e3
public class Item1 extends SherlockFragment{
	
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
	
	//18 de noviembre - Guarda los id y los nombres  de las salas
	ArrayList<ArrayList<String>> salasArray = new ArrayList<ArrayList<String>>();
	ArrayList<String> nombreSalas = new ArrayList<String>();	//Alojar nombre de salas, similar al de arriba
	
	//26oct
	private TextView mDateDisplay;
	private int mYear;
	private int mMonth;
	private int mDay;
	static final int DATE_DIALOG_ID = 0;
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
    String IP_Server="172.16.0.125";//IP DE NUESTRO PC
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
    
    /*
     * Programadas 1
     * Diferidas   -50
     * removeAllViews();
     * */
  
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
    
	//Spinner sp;
	
    //Se cambio el inflater y container a final
	public View onCreateView(final LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState){
		
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
		
		final LinearLayout sv = (LinearLayout)v.findViewById(R.id.vista);
		
		final View home = inflater.inflate(R.layout.home, container, false); //Layout incorrecto, no sera home
		// ... a diferencia del MainView estas seran cirugias programas del dia, incluyendo diferidas.
		
		final View home2 = inflater.inflate(R.layout.home2, container, false);
		final View home3 = inflater.inflate(R.layout.home, container, false);
		final View home4 = inflater.inflate(R.layout.home, container, false); //Para diferidas

		
		final View programar = inflater.inflate(R.layout.programar_cirugia, container, false);
		//SALAS
		final View mostrarSalas = inflater.inflate(R.layout.salas_cabecera, container, false);
		final View fondoSalas = inflater.inflate(R.layout.tabla_salas, container, false);
		
		final View fondoAgendaDia = inflater.inflate(R.layout.tabla_dia, container, false);
		//new ProgramarCirugiaView(inflater, programar, getActivity());
		 
		//ProgramarCirugiaView pcv = new ProgramarCirugiaView();
		//pcv.prog(inflater, programar, getActivity());
		ProgramarCirugiaView pcv = new ProgramarCirugiaView();
		pcv.prog(inflater, programar, getActivity());
		
		//spinner 1
		//final Spinner sp = (Spinner) programar.findViewById(R.id.salaOpciones);
		//final Spinner sp1 = (Spinner) programar.findViewById(R.id.duracion);

		mDateDisplay = (TextView) programar.findViewById(R.id.stringFecha);
		
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
		
		//-- FABRICADO POR VICTOR
		/*
		for(int index = 0; index < 10; index++){
			View tabler = inflater.inflate(R.layout.tablerow_editable, container, false);

			TextView hora = (TextView)tabler.findViewById(R.id.hora);
			TextView sala = (TextView)tabler.findViewById(R.id.sala);
			TextView pa = (TextView)tabler.findViewById(R.id.pa);
			TextView dg = (TextView)tabler.findViewById(R.id.dg);

			TableRow trow = (TableRow) tabler;

			hora.setText("Hora "+ index);	
			sala.setText("Sala "+index);	
			pa.setText("Paciente "+ index);	
			dg.setText("Diagnostico "+ index);

			tl.addView(trow);
		}
		*/
		
				
		// 21 oct-----------------------------------------------------------------------
		
		/*Lo siguiente ira dentro de la sub-clase Agenda*/
		String val = ID_quirofano;
		Log.e("antes de agenda", "antes de agenda");
		new RegistroDiferida(inflater, container).execute(ID_quirofano);		//OBTENER CIRUGAS DIFERIDAS
		new RegistroTransoperatorio(inflater, container).execute(ID_quirofano);	//OBTENER CIRUGIAS TRANSOPERATORIO
		new RegistroRealizada(inflater, container).execute(ID_quirofano);		//OBTENER CIRUGIAS REALIZADAS
		new Agenda(inflater, container).execute(val);							//OBTENER CIRUGIAS PROGRAMADAS
		
		/*
		1. Lo primero que traiga todos los registros
		*/
		
		//if (padre.size() != 0){
		
		//SystemClock.sleep(1000);
		Log.e("paso1", "paso1");
		
		//FABRICADO POR TRIANA *****************************************************
		
		//System.out.println("largo de padre = "+padre.size());
		//System.out.println("PADRE = "+padre);,
			
		//for(int index = 0; index < 10; index++){
//		for(int index = 0; index < padre.size(); index++){
//
//			View tabler = inflater.inflate(R.layout.tablerow_editable, container, false);
//
//			TextView fech = (TextView)tabler.findViewById(R.id.fech);
//			TextView hora = (TextView)tabler.findViewById(R.id.hora);
//			TextView sala = (TextView)tabler.findViewById(R.id.sala);
//			TextView pa = (TextView)tabler.findViewById(R.id.pa);
//			TextView dg = (TextView)tabler.findViewById(R.id.dg);
//			TextView accion = (TextView)tabler.findViewById(R.id.accion);
//
//			TableRow trow = (TableRow) tabler;
//
//			fech.setText(padre.get(index).get(0));	//fecha
//			hora.setText(padre.get(index).get(1));	//hora
//			sala.setText(padre.get(index).get(2));	//sala
//			pa.setText(padre.get(index).get(3));	//paciente
//			dg.setText(padre.get(index).get(4));	//diagnostico
//			//accion.setText("action:"+index);
//
//			tl.addView(trow);
//		}//Fin de ciclo for
		//}//Fin de validacion
		//FABRICADO POR TRIANA *********************************************************		
		
		
		//ArrayList<String> data = new ArrayList<String>();
		//data = mostrarAgenda(); YA ESTA EN LA SUB-CLASE
		
		//********************************ESCRIBIR EN LA VISTA*****************************+
		/*
		View tabler = inflater.inflate(R.layout.tablerow_editable, container, false);

		TextView hora = (TextView)tabler.findViewById(R.id.hora);
		TextView sala = (TextView)tabler.findViewById(R.id.sala);
		TextView pa = (TextView)tabler.findViewById(R.id.pa);
		TextView dg = (TextView)tabler.findViewById(R.id.dg);

		TableRow trow = (TableRow) tabler;
		
		Log.e("ultimo-array", "ultimo = "+padre);
		//Log.e("unico-elemento", "unico = "+al.get(0));
		
		//SystemClock.sleep(4450);
		
		if( padre.size() != 0){
			hora.setText(padre.get(0).get(0)); //hora
			sala.setText(padre.get(0).get(1)); //sala
			pa.setText(padre.get(0).get(2));   //paciente(nombre)
			dg.setText(padre.get(0).get(3));   //diagnostico
		}
		*/
		//tl.addView(trow);
		// 21 oct ----------------------------------------------------------------------
		
		sv.addView(home);
		Button agenda = (Button)v.findViewById(R.id.agenda);
		Button pc = (Button)v.findViewById(R.id.pc);
		Button cd = (Button)v.findViewById(R.id.cd);
		Button agendaDia = (Button)v.findViewById(R.id.agenda_del_dia);
		Button salas = (Button)v.findViewById(R.id.salas);
		
		agenda.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				sv.removeAllViews();
				//new Agenda(inflater, container).execute(ID_quirofano);
				sv.addView(home);
			}
		});
		
		tablaDiferida = (TableLayout)home4.findViewById(R.id.table);
		TableRow cabeceraDiferida = (TableRow) inflater.inflate(R.layout.tablerow, container, false);
		tablaDiferida.addView(cabeceraDiferida);
		
		cd.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				sv.removeAllViews();
				new RegistroDiferida(inflater, container).execute(ID_quirofano);
				TextView temaDiferida = (TextView)home4.findViewById(R.id.title);
				temaDiferida.setText("Cirugías diferidas");
				sv.addView(home4);
			}
		});
		
		tablaDia = (TableLayout)home3.findViewById(R.id.table);
		TableRow cabeceraDia = (TableRow) inflater.inflate(R.layout.tablerow, container, false);
		tablaDia.addView(cabeceraDia);
		
		agendaDia.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				sv.removeAllViews();
				new agendaDelDia(inflater, container).execute(ID_quirofano);
				temaDia = (TextView)home3.findViewById(R.id.title);
				temaDia.setText("Agenda del día");
				sv.addView(home3);

			}
		});
		

		pc.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				sv.removeAllViews();
				TextView t = new TextView(getActivity());
				t.setText("Hola de nuevo");
				sv.addView(programar); //aqui termina victor
				
//				new GetSalas().execute(ID_quirofano);
				
				/*Para DatePicker*/
				//mDateDisplay.setOnClickListener (new OnClickListener(){
					//public void onClick(View v){
					//showDialog(DATE_DIALOG_ID);
					//}
				//});//Fin de setOnClickListener
				
				//final Calendar c = Calendar.getInstance();        
		    	//mYear = c.get(Calendar.YEAR);        
		    	//mMonth = c.get(Calendar.MONTH);        
		    	//mDay = c.get(Calendar.DAY_OF_MONTH);        
		    	// display the current date (this method is below)        
		    	//updateDisplay(); 
					
				//NUEVO SPINNER 1 ***************************************************************
				Spinner sp = (Spinner) programar.findViewById(R.id.salaOpciones);
				
				//String[] salas = new String[nombreSalas.size()];
				//salas = nombreSalas;
				//nombreSalas.add("hola que hace");
				ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                        android.R.layout.simple_spinner_item, nombreSalas);
                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
				
//				ArrayAdapter<CharSequence> adapter = new ArrayAdapter<CharSequence>(getActivity(),
//				        android.R.layout.simple_spinner_item);
//				    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//				    
//				adapter.add("Hola");
//				adapter.add("que hace");
				
//				ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource( //MANERA 1
//						context, R.array.salas, android.R.layout.simple_spinner_item);
//				adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
				sp.setAdapter(adapter);

				sp.setOnItemSelectedListener(new OnItemSelectedListener() {

					public void onItemSelected(AdapterView<?> parentView, View selectedItemView,
							int position, long id) {
						//Toast.makeText(parentView.getContext(), "Has seleccionado " +
						//parentView.getItemAtPosition(position).toString(), Toast.LENGTH_LONG).show();
						parentView.getItemAtPosition(position);
						//spins.add(position);
						//Log.e("arreglo spins", "s1="+spins);
						Log.e("spiner1 = ", "posicion1="+position);
						//spin1 = position;
						//setSala(position);
				
						
						int sala=position;
						Item1.salaSpinner = sala;
						//Log.e("spiner1 = ", "posicion1="+Item1.salaSpinner);
						//ProgramarCirugiaView.sala = sala;
						//System.out.println("sala origi="+sala);
						//= position;
					}

					public void onNothingSelected(AdapterView<?> parentView) {

					}
				});
				
				// FIN - NUEVO SPINNER 1 ***************************************************************
				
				
				// INICIA SPINNER 1
				
				//Para el spinner 1 - numero de sala
        		
				/*
				
				String [] array = getResources().getStringArray(R.array.salas);
				ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.test_list_item, array);
		        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		        sp.setAdapter(adapter);
//		        
		        sp.setOnItemSelectedListener(new OnItemSelectedListener() {
		           
		            public void onItemSelected(AdapterView<?> parentView, View selectedItemView,
		             int position, long id) {
		             Toast.makeText(parentView.getContext(), "Has seleccionado " +
		             parentView.getItemAtPosition(position).toString(), Toast.LENGTH_LONG).show();
		            
		            }
		                                 
		            public void onNothingSelected(AdapterView<?> parentView) {
		            
		            }
		        });
				
				*/
				 
				// TERMINA SPINNER 1
				
		        
		        // INICIA SPINNER 2
				
				//Para el spinner 2 - Duraciones
       				
				/*
				
				String [] array1 = getResources().getStringArray(R.array.duraciones);
				ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(getActivity(), android.R.layout.test_list_item, array1);
		        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		        sp1.setAdapter(adapter1);
//		        
		        sp1.setOnItemSelectedListener(new OnItemSelectedListener() {
		           
		            public void onItemSelected(AdapterView<?> parentView, View selectedItemView,
		             int position, long id) {
		             //Toast.makeText(parentView.getContext(), "Has seleccionado " +
		             //parentView.getItemAtPosition(position).toString(), Toast.LENGTH_LONG).show();
		            
		            }
		                                 
		            public void onNothingSelected(AdapterView<?> parentView) {
		            
		            }
		        });
				
				*/ 
				 
				// TERMINA SPINNER 2
		        
		        //Recoleccion y envio de datos
		        
		        //final String date=fecha.getText().toString();
		        //final String hora=horaPropuesta.getText().toString();
		        //final String reg = registro.getText().toString();
		        //final String paciente = nombreDelPaciente.getText().toString();
		        
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
							String servicioString = Integer.toString(ob.get(index).getServicio());
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
						//procedimientos.clear();
						//**********************************************************
		        		/*TRAER DATOS DE LOS PROCEDIMIENTOS DINAMICOS*/
		        		
		        		String date = fecha.getText().toString();
		        		String hora=horaPropuesta.getText().toString();
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
				        
				        //Toast toast1 = Toast.makeText(getActivity().getApplicationContext(),"protocolo = "+prot.getText(), Toast.LENGTH_SHORT);
				 	    //toast1.show();
				 	    
				 	    //Toast toast2 = Toast.makeText(getActivity().getApplicationContext(),"reintervencion = "+reint.getText(), Toast.LENGTH_SHORT);
				 	   	//toast2.show();
				        
				        ProgramarCirugiaView pos = new ProgramarCirugiaView();
				        
				        System.out.println("sala = "+getSala());
				        System.out.println("duracion = "+object.getDuracion());
				        System.out.println("programacion = "+object.getProgramacion());
				        System.out.println("servicio = "+object.getServicio());
				        System.out.println("atencion = "+object.getAtencion());
				        
				        /*
				        Toast toa = Toast.makeText(getActivity().getApplicationContext(), 
				        		"sala = "+object.getSala()+" duracion = "+object.getDuracion()+
				        		" programacion = "+object.getProgramacion()+" servicio = "+
				        		object.getServicio()+" atencion = "+object.getAtencion(),
				        		Toast.LENGTH_SHORT);
				        toa.show();
				        */
				        
				        //int sala = object.getSala();
				        int sala = getSala();
				        String sDuracion = object.getDuracion();
				        int programacion = object.getProgramacion();
				        int servicio = object.getServicio();
				        int atencion = object.getAtencion();
				        
				        String sSala = Integer.toString(sala);
				        //String sDuracion = Integer.toString(duracion);
				        String sProgramacion = Integer.toString(programacion);
				        String sServicio = Integer.toString(servicio);
				        String sAtencion = Integer.toString(atencion);
				        
				        String sP = Integer.toString(p);
				        String sR = Integer.toString(r);
				        
				        //Toast toast10 = Toast.makeText(getActivity().getApplicationContext(),"posiciones = "+sala+duracion, Toast.LENGTH_SHORT);
				 	    //toast10.show();
				        //ArrayList<Integer> posicion = pos.getPosition();
				        //Toast toast1 = Toast.makeText(getActivity().getApplicationContext(),"posiciones = "+pos.getPosition(), Toast.LENGTH_SHORT);
				 	    //toast1.show();
		        		
		        		if (validarFormulario(date, hora, reg, paciente, sEdad, sGenero, sProcedencia,
		        				sDiagnostico, sMedico, sRiesgoQuirurgico, sInsumosIndispensables,
		        				sRequerimientos, sHemoderivados, sRequisitos, sNecesidades) == true){
		        			
		        			// LOOP PARA OBTENER TODOS LOS DATOS DE LOS PROCEDIMIENTOS DINAMICOS*********
		        			//System.out.println(procedimientos);
		        			//Orden: Servicio-Region-Procedimiento-Detalles
		        			
		        			// LOOP PARA OBTENER TODOS LOS DATOS DE LOS PROCEDIMIENTOS DINAMICOS*********
		        			
		        			//Primero mandamos los Spinners luego los RadioButton
		        			new Formulario().execute(date, hora, reg, paciente, sEdad, sGenero, sProcedencia,
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
		//Intent intent = new Intent(getActivity(), QCentralActivity.class);
		//startActivity(intent);tablaDeSalas = (TableLayout)fondoSalas.findViewById(R.id.table_salas);
		//TableRow cabeceraSalas = (TableRow) inflater.inflate(R.layout.salas_cabecera, container, false);
		//tablaDeSalas.addView(cabeceraSalas);
		
		tablaDeSalas = (TableLayout)fondoSalas.findViewById(R.id.table_salas);
		TableRow cabeceraSalas = (TableRow) inflater.inflate(R.layout.salas_cabecera, container, false);
		tablaDeSalas.addView(cabeceraSalas);
		
		//new GetSalas(inflater, container).execute(ID_quirofano);
		
		salas.setOnClickListener(new OnClickListener(){
			
			@Override
			public void onClick(View v) {
				sv.removeAllViews();
				//sv.addView(mostrarSalas);
				
				sv.addView(fondoSalas);
							
				//TextView t = new TextView(getActivity());
				//t.setText("Hola otra vez");
				//sv.addView(home);
			}

		});
		
		return v;



		//return inflater.inflate(R.layout.qcentral, null);
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
	public boolean enviarFormulario(String date, String hora, String reg, String paciente, String sEdad,
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
		
		//Forma antigua de obtener el id del quirofano
//		String quirofano = "Central";
//		new GetQuirofanoId().execute(quirofano);
//		SystemClock.sleep(2000);
//		System.out.println("q_id = "+quirofano_id); 
		// FIN - Forma antigua de obtener el id del quirofano
		
		//Nueva forma de obtener el id del quirofano
		System.out.println("q_id = "+ID_quirofano); 
		datosEnviar.add(new BasicNameValuePair("q_id", ID_quirofano));

		// FIN - Nueva forma de obtener el id del quirofano
		
		//datosEnviar.add(new BasicNameValuePair("q_id", quirofano_id));
		
		Log.e("datosEnviar","datosEnviar = "+datosEnviar);
		Log.e("URL_connect","URL_connect = "+URL_connect);
		
		//realizamos una peticion y como respuesta obtienes un array JSON
  		JSONArray jdata=post.getserverdata(datosEnviar, URL_connect);
  		
  		//SystemClock.sleep(1450);
  		  		
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
    			return false;
    		}
    		else{// [{"logstatus":"1"}]
    			Log.e("programacion-status ", "envio exitoso");
    			registroID = status;
    			return true;
    		}
    		 
    	}//Fin de if(comprueba si lo obtenido no es "null")
    	
    	else{	//json obtenido invalido verificar parte WEB.
    		Log.e("JSON  ", "ERROR");
	    	return false;
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
					
					ArrayList<String> temporary = new ArrayList<String>();
					
					temporary.add(val);
					temporary.add(value);
					temporary.add(value1);
					temporary.add(value2);
					temporary.add(value3);
					temporary.add(value4);
					//st.add(value);
					//st.add(value1);
					//st.add(value2);
					//st.add(value3);
					
					Log.e("log-st", "array temprary = "+temporary);
					
					padre.add(temporary);
					//st.clear();
				}
				
				/*
				json_data = jdata.getJSONObject(0);
				cont = json_data.getString("contador");
				Log.e("cont", "contador = "+cont);
				
				json_data = jdata.getJSONObject(0);	//leemos el primer segmento en nuestro caso el unico
				value=json_data.getString("dato");	//accedemos al valor
				
				json_data = jdata.getJSONObject(0);
				value1=json_data.getString("dato1");
				
				json_data = jdata.getJSONObject(0);
				value2=json_data.getString("dato2");
				
				json_data = jdata.getJSONObject(0);
				value3=json_data.getString("dato3");
				
				System.out.println("Contador = "+cont);
	    		*/
	    						
				//st.add(value);
				//st.add(value1);
				//st.add(value2);
				//st.add(value3);
				Log.e("array padre", "padre = "+padre);
								
				//return st;
				
				//Log.e("enviarFormulario","status= "+status);//muestro por log que obtuvimos
			}
			catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				Log.e("hi", "hi");
			}		            
			//return st;
			
    	}//Fin de if(comprueba si lo obtenido no es "null")
    	
    	else{	//json obtenido invalido verificar parte WEB.
    		Log.e("JSON  ", "ERROR");
	    	//return st;
	    }
		
		
		
	}//Fin de mostrarAgenda
	
	public void mostrarRegistroDiferida(String s) throws JSONException{	
		arrayDiferidas.clear();
		String val = "", value = "", value1 = "", value2 = "", value3 = "", value4 = "", cont = "";
		
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
					
					ArrayList<String> temporary = new ArrayList<String>();
					
					temporary.add(val);
					temporary.add(value);
					temporary.add(value1);
					temporary.add(value2);
					temporary.add(value3);
					temporary.add(value4);
		
					Log.e("RegistroDiferida", "array temporary = "+temporary);
					
					arrayDiferidas.add(temporary);
				}

				Log.e("array RegistroDiferida", "arrayDiferidas = "+arrayDiferidas);
		
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
	}//Fin de mostrarRegistroDiferida
	
	//MOSTRAR REGISTRO TRANSOPERATORIO
	public void mostrarRegistroTransoperatorio(String s) throws JSONException{	
		arrayTransoperatorio.clear();
		String val = "", value = "", value1 = "", value2 = "", value3 = "", value4 = "", cont = "";
		
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
					
					ArrayList<String> temporary = new ArrayList<String>();
					
					temporary.add(val);
					temporary.add(value);
					temporary.add(value1);
					temporary.add(value2);
					temporary.add(value3);
					temporary.add(value4);
		
					Log.e("RegistroDiferida", "array temporary = "+temporary);
					
					arrayTransoperatorio.add(temporary);
				}

				Log.e("array RegistroTransoperatorio", "arrayTransoperatorio = "+arrayTransoperatorio);
		
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
	}//Fin de mostrarRegistroTransoperatorio
	
	//MOSTRAR REGISTRO REALIZADAS
	public void mostrarRegistroRealizada(String s) throws JSONException{	
		arrayRealizadas.clear();
		String val = "", value = "", value1 = "", value2 = "", value3 = "", value4 = "", cont = "";
			
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
						
					ArrayList<String> temporary = new ArrayList<String>();
						
					temporary.add(val);
					temporary.add(value);
					temporary.add(value1);
					temporary.add(value2);
					temporary.add(value3);
					temporary.add(value4);
			
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
    		//JSONObject json_data; //creamos un objeto JSON
			
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
		//Primero enviamos el tamaño del arreglo procedimientos*4
		//int num = procedimientos.size()*4;
		//String largo = Integer.toString(num);
		//datosEnviar.add(new BasicNameValuePair("info", largo));
		
		//Luego enviamos el id del registro con que programamos la cirugia principal
		System.out.println("REGISTROOOOOOOOOO_ID = "+registro);
		datosEnviar.add(new BasicNameValuePair("numRegistro", registro));
		
		//Luego enviamos el tamaño del arreglo procedimientos
		//int num2 = procedimientos.size();
		//String largo2 = Integer.toString(num2);
		//datosEnviar.add(new BasicNameValuePair("info2", largo2));
		
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
					//st.add(value);
					//st.add(value1);
					//st.add(value2);
					//st.add(value3);
					
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
	
	public void mostrarLeyenda(){
		Toast t = Toast.makeText(getActivity().getApplicationContext(), "Cirugia programada con éxito", Toast.LENGTH_SHORT);
		t.show();
	}
	
	/*Uso de Asnyctask - [Documentacion]*/
	/*2 subclases apartir de aqui: Formulario - Agenda*/
	
	class Formulario extends AsyncTask< String, String, String > {
   	 
    	//String date, hora, reg, paciente;
    	String date, hora, reg, paciente, sEdad, sGenero, sProcedencia, sDiagnostico, sMedico,
    	sRiesgoQuirurgico, sInsumosIndispensables, sRequerimientos, sHemoderivados, sRequisitos,
    	sNecesidades;
    	
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
			
			//enviamos y recibimos y analizamos los datos en segundo plano.
    		if (enviarFormulario(date, hora, reg, paciente, sEdad, sGenero, sProcedencia,
    				sDiagnostico, sMedico, sRiesgoQuirurgico, sInsumosIndispensables, sRequerimientos,
    				sHemoderivados, sRequisitos, sNecesidades, sSala, sDuracion, sProgramacion,
    				sServicio, sAtencion, sP , sR)==true){    		    		
    			return "ok"; //login valido
    		}else{    		
    			return "error"; //login invalido     	          	  
    		}	
		}//Fin de doInBackground
        
        protected void onPostExecute(String result) {
        	progress.dismiss();//ocultamos progess dialog.
            Log.e("onPostExecute=",""+result);
            
            if (result.equals("ok")){
            	mostrarLeyenda();
            	
            	//Nueva subclase: EnviarProcedimientos
            	new EnviarProcedimientos().execute(registroID);
            	
            	//Intent i = new Intent(MainActivity.this, MainActivity2.class);
 				//i.putExtra("user",user);
 				//startActivity(i); 
            }
            else {
             	error2();
            }
        }//Fin de onPostExecute        
	}//Fin de la subclase Formulario
	
	class EnviarProcedimientos extends AsyncTask< String, String, String> {
		
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
        	}
        	else Log.e("EnviarProcedimientos=","Todo mal="+resultado);
            //return al;
            //mostrarLeyenda();
        	System.out.println("RESULTADO DEL ENVIO DE PROCEDIMIENTOS:"+resultado);
            
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
            
            //new 28 nov
            
            //ONCLICK PARA CIRUGIAS PROGRAMADAS
            OnClickListener clicks=new OnClickListener() {

    		    @Override
    		    public void onClick(View v) {
    		    		//Esta es la posicion del array padre sobre el que se hizo click
    		    		System.out.println("id = "+v.getId());  
    		    		
    		    		//Ahora se debe obtener el quirofano_id y el quirofano_name, desde v.getId()
    		            
    		    		//switch(v.getId())
    		            //{
    			            //case 0: System.out.println("0");
    		                //break;
	    		        Fragment duedateFrag = new Accion();	
	    		        Bundle bundle = new Bundle();
	    		                	    		                
	    		        String miArreglo[] = new String[6];
	    		              
//	    		        miArreglo[0] = padre.get(v.getId()).get(0);		//llenar con id_quirofano
//	    		        miArreglo[1] = padre.get(v.getId()).get(1);		//llenar con el nombre del quirofano
	    		        
	    		        miArreglo[0] = padre.get(v.getId()).get(0);		//Llenar con la fecha
				        miArreglo[1] = padre.get(v.getId()).get(1);		//Llenar con la hora
				        miArreglo[2] = padre.get(v.getId()).get(2);		//Sala	
				        miArreglo[3] = padre.get(v.getId()).get(3);		//Paciente
				        miArreglo[4] = padre.get(v.getId()).get(4);		//Diagnostico
				        miArreglo[5] = padre.get(v.getId()).get(5);		//ID
	    		                
//	    		        System.out.println("POSICION DE QUIROFANO_ID = "+v.getId());
//	    		        System.out.println("ARREGLO-QUIROFANO_ID = "+miArreglo[0]);
//	    		        System.out.println("ARREGLO-QUIROFANO_NAME = "+miArreglo[1]);
	    		                	    		                	    		                
	    		        bundle.putStringArray("parametro", miArreglo); //Arreglo para mandar a Accion
	    		        duedateFrag.setArguments(bundle);
	    		                
	    		        FragmentTransaction ft  = getFragmentManager().beginTransaction();
	    		        ft.replace(R.id.content_frame, duedateFrag);
	    		        //ft.addToBackStack(null);
	    		        ft.commit();
	    		        //break;
    		                
    		            //}       
    		    } //Fin de onClick
    		}; //Fin de onClickListener
    		
    		//ONCLICK PARA TRANSOPERATORIO
    		OnClickListener clicks1=new OnClickListener() {

    		    @Override
    		    public void onClick(View v) {
    		    		//Esta es la posicion del array padre sobre el que se hizo click
    		    		System.out.println("id = "+v.getId());  
    		    		
    		    		//Ahora se debe obtener el quirofano_id y el quirofano_name, desde v.getId()
    		            
    		    		//switch(v.getId())
    		            //{
    			            //case 0: System.out.println("0");
    		                //break;
	    		        Fragment duedateFrag = new AccionTransoperatorio();	
	    		        Bundle bundle = new Bundle();
	    		                	    		                
	    		        String miArreglo[] = new String[6];
	    		              
//	    		        miArreglo[0] = padre.get(v.getId()).get(0);		//llenar con id_quirofano
//	    		        miArreglo[1] = padre.get(v.getId()).get(1);		//llenar con el nombre del quirofano
	    		        
	    		        miArreglo[0] = arrayTransoperatorio.get(v.getId()).get(0);		//Llenar con la fecha
				        miArreglo[1] = arrayTransoperatorio.get(v.getId()).get(1);		//Llenar con la hora
				        miArreglo[2] = arrayTransoperatorio.get(v.getId()).get(2);		//Sala	
				        miArreglo[3] = arrayTransoperatorio.get(v.getId()).get(3);		//Paciente
				        miArreglo[4] = arrayTransoperatorio.get(v.getId()).get(4);		//Diagnostico
				        miArreglo[5] = arrayTransoperatorio.get(v.getId()).get(5);		//ID
	    		                
//	    		        System.out.println("POSICION DE QUIROFANO_ID = "+v.getId());
//	    		        System.out.println("ARREGLO-QUIROFANO_ID = "+miArreglo[0]);
//	    		        System.out.println("ARREGLO-QUIROFANO_NAME = "+miArreglo[1]);
	    		                	    		                	    		                
	    		        bundle.putStringArray("parametro", miArreglo); //Arreglo para mandar a Accion
	    		        duedateFrag.setArguments(bundle);
	    		                
	    		        FragmentTransaction ft  = getFragmentManager().beginTransaction();
	    		        ft.replace(R.id.content_frame, duedateFrag);
	    		        //ft.addToBackStack(null);
	    		        ft.commit();
	    		        //break;
    		                
    		            //}       
    		    } //Fin de onClick
    		}; //Fin de onClickListener
    		
    		//ONCLICK PARA REALIZADAS
    		OnClickListener clicks2=new OnClickListener() {

    		    @Override
    		    public void onClick(View v) {
    		    		//Esta es la posicion del array padre sobre el que se hizo click
    		    		System.out.println("id = "+v.getId());  
    		    		
    		    		//Ahora se debe obtener el quirofano_id y el quirofano_name, desde v.getId()
    		            
    		    		//switch(v.getId())
    		            //{
    			            //case 0: System.out.println("0");
    		                //break;
	    		        Fragment duedateFrag = new AccionRealizada();	
	    		        Bundle bundle = new Bundle();
	    		                	    		                
	    		        String miArreglo[] = new String[6];
	    		              
//	    		        miArreglo[0] = padre.get(v.getId()).get(0);		//llenar con id_quirofano
//	    		        miArreglo[1] = padre.get(v.getId()).get(1);		//llenar con el nombre del quirofano
	    		        
	    		        miArreglo[0] = arrayRealizadas.get(v.getId()).get(0);		//Llenar con la fecha
				        miArreglo[1] = arrayRealizadas.get(v.getId()).get(1);		//Llenar con la hora
				        miArreglo[2] = arrayRealizadas.get(v.getId()).get(2);		//Sala	
				        miArreglo[3] = arrayRealizadas.get(v.getId()).get(3);		//Paciente
				        miArreglo[4] = arrayRealizadas.get(v.getId()).get(4);		//Diagnostico
				        miArreglo[5] = arrayRealizadas.get(v.getId()).get(5);		//ID
	    		                
//	    		        System.out.println("POSICION DE QUIROFANO_ID = "+v.getId());
//	    		        System.out.println("ARREGLO-QUIROFANO_ID = "+miArreglo[0]);
//	    		        System.out.println("ARREGLO-QUIROFANO_NAME = "+miArreglo[1]);
	    		                	    		                	    		                
	    		        bundle.putStringArray("parametro", miArreglo); //Arreglo para mandar a Accion
	    		        duedateFrag.setArguments(bundle);
	    		                
	    		        FragmentTransaction ft  = getFragmentManager().beginTransaction();
	    		        ft.replace(R.id.content_frame, duedateFrag);
	    		        //ft.addToBackStack(null);
	    		        ft.commit();
	    		        //break;
    		                
    		            //}       
    		    } //Fin de onClick
    		}; //Fin de onClickListener
    		
    		//ONCLICK PARA DIFERIDAS
    		OnClickListener clicks3=new OnClickListener() {

    		    @Override
    		    public void onClick(View v) {
    		    		//Esta es la posicion del array padre sobre el que se hizo click
    		    		System.out.println("id = "+v.getId());  
    		    		
    		    		//Ahora se debe obtener el quirofano_id y el quirofano_name, desde v.getId()
    		            
    		    		//switch(v.getId())
    		            //{
    			            //case 0: System.out.println("0");
    		                //break;
	    		        Fragment duedateFrag = new AccionDiferida();	
	    		        Bundle bundle = new Bundle();
	    		                	    		                
	    		        String miArreglo[] = new String[6];
	    		              
//	    		        miArreglo[0] = padre.get(v.getId()).get(0);		//llenar con id_quirofano
//	    		        miArreglo[1] = padre.get(v.getId()).get(1);		//llenar con el nombre del quirofano
	    		        
	    		        miArreglo[0] = arrayDiferidas.get(v.getId()).get(0);		//Llenar con la fecha
				        miArreglo[1] = arrayDiferidas.get(v.getId()).get(1);		//Llenar con la hora
				        miArreglo[2] = arrayDiferidas.get(v.getId()).get(2);		//Sala	
				        miArreglo[3] = arrayDiferidas.get(v.getId()).get(3);		//Paciente
				        miArreglo[4] = arrayDiferidas.get(v.getId()).get(4);		//Diagnostico
				        miArreglo[5] = arrayDiferidas.get(v.getId()).get(5);		//ID
	    		                
//	    		        System.out.println("POSICION DE QUIROFANO_ID = "+v.getId());
//	    		        System.out.println("ARREGLO-QUIROFANO_ID = "+miArreglo[0]);
//	    		        System.out.println("ARREGLO-QUIROFANO_NAME = "+miArreglo[1]);
	    		                	    		                	    		                
	    		        bundle.putStringArray("parametro", miArreglo); //Arreglo para mandar a Accion
	    		        duedateFrag.setArguments(bundle);
	    		                
	    		        FragmentTransaction ft  = getFragmentManager().beginTransaction();
	    		        ft.replace(R.id.content_frame, duedateFrag);
	    		        //ft.addToBackStack(null);
	    		        ft.commit();
	    		        //break;
    		                
    		            //}       
    		    } //Fin de onClick
    		}; //Fin de onClickListener
        	
        	//View v = inflater.inflate(R.layout.lista_quirofanos, container, false);
    		//View ll = v.findViewById(R.id.info);
        	
        	View tab = inflater.inflate(R.layout.tiposde_cirugias, container, false);
        	TextView titulo = (TextView)tab.findViewById(R.id.title);
        	titulo.setText("Programada");
			TableRow tabRow = (TableRow) tab;
			tl.addView(tabRow);

    		//PARA LAS PROGRAMADAS *********************************************************************
        	for (int i = 0; i<padre.size(); i++){
//    			TextView tv = (TextView) new TextView(getActivity());
//    			tv.setText(padre.get(i).get(1));
//    			tv.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT,LayoutParams.WRAP_CONTENT));
//    			((LinearLayout) ll).addView(tv);
    			
    			View tabler = inflater.inflate(R.layout.newtablerow, container, false);
    			TextView fech = (TextView)tabler.findViewById(R.id.fech);
				TextView hora = (TextView)tabler.findViewById(R.id.hora);
				TextView sala = (TextView)tabler.findViewById(R.id.sala);
				TextView pa = (TextView)tabler.findViewById(R.id.pa);
				TextView dg = (TextView)tabler.findViewById(R.id.dg);
				
				TableRow trow = (TableRow) tabler;
				
				fech.setText(padre.get(i).get(0));	//fecha
				hora.setText(padre.get(i).get(1));	//hora
				sala.setText(padre.get(i).get(2));	//sala
				pa.setText(padre.get(i).get(3));	//paciente
				dg.setText(padre.get(i).get(4));	//diagnostico
				//accion.setText("action:"+index);
					
				tl.addView(trow);
    			//Primero pasamos la posicion del arraypadre en el que se hizo click
    			trow.setId(i);
    			//Opcion2 - settear Id con la posición de "padre" en que se hizo click
    			trow.setOnClickListener(clicks);
    		}//Fin de for
        	
//        	for(int index = 0; index < padre.size(); index++){
////    		
////			View tabler = inflater.inflate(R.layout.tablerow_editable, container, false);
////			
////			TextView fech = (TextView)tabler.findViewById(R.id.fech);
////			TextView hora = (TextView)tabler.findViewById(R.id.hora);
////			TextView sala = (TextView)tabler.findViewById(R.id.sala);
////			TextView pa = (TextView)tabler.findViewById(R.id.pa);
////			TextView dg = (TextView)tabler.findViewById(R.id.dg);
////			TextView accion = (TextView)tabler.findViewById(R.id.accion);
////			
////			TableRow trow = (TableRow) tabler;
////			
////			fech.setText(padre.get(index).get(0));	//fecha
////			hora.setText(padre.get(index).get(1));	//hora
////			sala.setText(padre.get(index).get(2));	//sala
////			pa.setText(padre.get(index).get(3));	//paciente
////			dg.setText(padre.get(index).get(4));	//diagnostico
////			//accion.setText("action:"+index);
////				
////			tl.addView(trow);
////		}
        	
        	
        	View tab1 = inflater.inflate(R.layout.tiposde_cirugias, container, false);
        	TextView titulo1 = (TextView)tab1.findViewById(R.id.title);
        	titulo1.setText("Transoperatorio");
			TableRow tabRow1 = (TableRow) tab1;
			tl.addView(tabRow1);
        	
        	//PARA TRANSOPERATORIO ********************************************************************
        	for (int i = 0; i<arrayTransoperatorio.size(); i++){
//    			TextView tv = (TextView) new TextView(getActivity());
//    			tv.setText(arrayTransoperatorio.get(i).get(1));
//    			tv.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT,LayoutParams.WRAP_CONTENT));
//    			((LinearLayout) ll).addView(tv);
//    			
//    			//Primero pasamos la posicion del arraypadre en el que se hizo click
//    			tv.setId(i);
//    			//Opcion2 - settear Id con la posición de "padre" en que se hizo click
//    			tv.setOnClickListener(clicks);
        		
        		View tabler = inflater.inflate(R.layout.newtablerow, container, false);
    			TextView fech = (TextView)tabler.findViewById(R.id.fech);
				TextView hora = (TextView)tabler.findViewById(R.id.hora);
				TextView sala = (TextView)tabler.findViewById(R.id.sala);
				TextView pa = (TextView)tabler.findViewById(R.id.pa);
				TextView dg = (TextView)tabler.findViewById(R.id.dg);
				
				TableRow trow = (TableRow) tabler;
				
				fech.setText(arrayTransoperatorio.get(i).get(0));	//fecha
				hora.setText(arrayTransoperatorio.get(i).get(1));	//hora
				sala.setText(arrayTransoperatorio.get(i).get(2));	//sala
				pa.setText(arrayTransoperatorio.get(i).get(3));	//paciente
				dg.setText(arrayTransoperatorio.get(i).get(4));	//diagnostico
				//accion.setText("action:"+index);
					
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
//    			TextView tv = (TextView) new TextView(getActivity());
//    			tv.setText(arrayRealizadas.get(i).get(1));
//    			tv.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT,LayoutParams.WRAP_CONTENT));
//    			((LinearLayout) ll).addView(tv);
//    			
//    			//Primero pasamos la posicion del arraypadre en el que se hizo click
//    			tv.setId(i);
//    			//Opcion2 - settear Id con la posición de "padre" en que se hizo click
//    			tv.setOnClickListener(clicks);
        		
        		View tabler = inflater.inflate(R.layout.newtablerow, container, false);
    			TextView fech = (TextView)tabler.findViewById(R.id.fech);
				TextView hora = (TextView)tabler.findViewById(R.id.hora);
				TextView sala = (TextView)tabler.findViewById(R.id.sala);
				TextView pa = (TextView)tabler.findViewById(R.id.pa);
				TextView dg = (TextView)tabler.findViewById(R.id.dg);
				
				TableRow trow = (TableRow) tabler;
				
				fech.setText(arrayRealizadas.get(i).get(0));	//fecha
				hora.setText(arrayRealizadas.get(i).get(1));	//hora
				sala.setText(arrayRealizadas.get(i).get(2));	//sala
				pa.setText(arrayRealizadas.get(i).get(3));	//paciente
				dg.setText(arrayRealizadas.get(i).get(4));	//diagnostico
				//accion.setText("action:"+index);
					
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
        	for (int i = 0; i<arrayDiferidas.size(); i++){
//    			TextView tv = (TextView) new TextView(getActivity());
//    			tv.setText(arrayDiferidas.get(i).get(1));
//    			tv.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT,LayoutParams.WRAP_CONTENT));
//    			((LinearLayout) ll).addView(tv);
//    			
//    			//Primero pasamos la posicion del arraypadre en el que se hizo click
//    			tv.setId(i);
//    			//Opcion2 - settear Id con la posición de "padre" en que se hizo click
//    			tv.setOnClickListener(clicks);
        		
        		View tabler = inflater.inflate(R.layout.newtablerow, container, false);
    			TextView fech = (TextView)tabler.findViewById(R.id.fech);
				TextView hora = (TextView)tabler.findViewById(R.id.hora);
				TextView sala = (TextView)tabler.findViewById(R.id.sala);
				TextView pa = (TextView)tabler.findViewById(R.id.pa);
				TextView dg = (TextView)tabler.findViewById(R.id.dg);
				
				TableRow trow = (TableRow) tabler;
				
				fech.setText(arrayDiferidas.get(i).get(0));	//fecha
				hora.setText(arrayDiferidas.get(i).get(1));	//hora
				sala.setText(arrayDiferidas.get(i).get(2));	//sala
				pa.setText(arrayDiferidas.get(i).get(3));	//paciente
				dg.setText(arrayDiferidas.get(i).get(4));	//diagnostico
				//accion.setText("action:"+index);
					
				tl.addView(trow);
    			//Primero pasamos la posicion del arraypadre en el que se hizo click
    			trow.setId(i);
    			//Opcion2 - settear Id con la posición de "padre" en que se hizo click
    			trow.setOnClickListener(clicks3);
        		
        		
    		}//Fin de for
        	
            //fin new 28 nov
            
            
//            lista.setAdapter(new MyVeryOwnArrayAdapter(getActivity(), R.layout.tablerow_editable, padre));
//            lista2.setAdapter(new MyVeryOwnArrayAdapter(getActivity(), R.layout.tablerow_editable, arrayDiferidas));
//            lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                public void onItemClick(AdapterView<?> av, View view, int i, long l) {
//                    //Toast.makeText(getActivity(), "Accediste a quirófano: "+padre.get(i).get(1), Toast.LENGTH_LONG).show();
//                    
//                    Fragment duedateFrag = new Accion();	//Nuevo item para las acciones
//    		        Bundle bundle = new Bundle();
//    		        String miArreglo[] = new String[6]; 	//Llenar arreglo con el nombre del paciente y el id
//			              
//			        miArreglo[0] = padre.get(i).get(0);		//Llenar con la fecha
//			        miArreglo[1] = padre.get(i).get(1);		//Llenar con la hora
//			        miArreglo[2] = padre.get(i).get(2);		//Sala	
//			        miArreglo[3] = padre.get(i).get(3);		//Paciente
//			        miArreglo[4] = padre.get(i).get(4);		//Diagnostico
//			        miArreglo[5] = padre.get(i).get(5);		//ID
//			        
//			        bundle.putStringArray("parametro", miArreglo); //Arreglo para mandar a Item1
//    		        duedateFrag.setArguments(bundle);
//    		                
////    		        FragmentManager fm = getFragmentManager();
////    		        fm.beginTransaction().add(R.id.content_frame, duedateFrag).addToBackStack("hi").commit();
//    		        FragmentTransaction ft  = getFragmentManager().beginTransaction();
//    		        ft.replace(R.id.content_frame, duedateFrag);
//    		        ft.addToBackStack(null);
//    		        //duedateFrag.getFragmentManager().popBackStackImmediate();
//    		        ft.commit();
//                }// Fin de onItemClick
//            });
////            for(int index = 0; index < padre.size(); index++){
////        		
////    			View tabler = inflater.inflate(R.layout.tablerow_editable, container, false);
////    			
////    			TextView fech = (TextView)tabler.findViewById(R.id.fech);
////    			TextView hora = (TextView)tabler.findViewById(R.id.hora);
////    			TextView sala = (TextView)tabler.findViewById(R.id.sala);
////    			TextView pa = (TextView)tabler.findViewById(R.id.pa);
////    			TextView dg = (TextView)tabler.findViewById(R.id.dg);
////    			TextView accion = (TextView)tabler.findViewById(R.id.accion);
////    			
////    			TableRow trow = (TableRow) tabler;
////    			
////    			fech.setText(padre.get(index).get(0));	//fecha
////    			hora.setText(padre.get(index).get(1));	//hora
////    			sala.setText(padre.get(index).get(2));	//sala
////    			pa.setText(padre.get(index).get(3));	//paciente
////    			dg.setText(padre.get(index).get(4));	//diagnostico
////    			//accion.setText("action:"+index);
////    				
////    			tl.addView(trow);
////    		}
//            //Fin de ciclo for
//            //return al;
//            //mostrarLeyenda();
            
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
    		//progress = ProgressDialog.show(
    		//getActivity(), null, "Accesando a agenda...");
            super.onPreExecute();
        }
    	
//      protected String doInBackground(String... params) {
    	protected ArrayList<String> doInBackground(String... params) {
			quir=params[0]; //obtenemos el string de quirofano_id 
			ArrayList<String> tempoSalas = new ArrayList<String>();
			
			tempoSalas = getSalas(quir);
			//Log.e("last-array", "last-array = "+al.get(0));
    		//return "ok"; //login valido
    		return tempoSalas;
		}//Fin de doInBackground
       
//        protected void onPostExecute(String resultado) {
    	protected void onPostExecute(ArrayList<String> resultado) {
        	//progress.dismiss();//ocultamos progess dialog.
            //Log.e("onPostExecute=","Todo bien="+resultado);
            //return al;
            //mostrarLeyenda();
    		
    		lista_salas.setAdapter(new MyVeryOwnArrayAdapter2(getActivity(), R.layout.tabla_salas_editable, salasArray));
    		nombreSalas = resultado;
    		Log.e("Resultado","resultado"+resultado);
    		Log.e("NombreSalas","nombreSalas"+nombreSalas);
//    		
//    		for(int index = 0; index < salasArray.size(); index++){
//        		
//    			View tabler = inflater.inflate(R.layout.tabla_salas_editable, container, false);
//    			
//    			TextView sala = (TextView)tabler.findViewById(R.id.sala_name);
//    			TextView activo = (TextView)tabler.findViewById(R.id.sala_activo);
//    			TextView bloqueado = (TextView)tabler.findViewById(R.id.sala_bloqueado);
//    			
//    			TableRow trow = (TableRow) tabler;
//    		
//    			sala.setText(salasArray.get(index).get(1));			//nombre de la sala
//    			activo.setText(salasArray.get(index).get(2));		//estado activo de la sala
//    			bloqueado.setText(salasArray.get(index).get(3));	//estado bloqueado de la sala
//    				
//    			tablaDeSalas.addView(trow);
//    		}//Fin de ciclo for
    		
            
        }//Fin de onPostExecute        
		
	}//Fin de la subclase GetQuirofanoId
	
	class agendaDelDia extends AsyncTask< String, String, String> {
		
		//String date, hora, reg, paciente;
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
        	
//        	for(int index = 0; index < padre.size(); index++){
//        		
//        		View tabler = inflater.inflate(R.layout.tablerow_editable, container, false);
//        			
//        		TextView fech = (TextView)tabler.findViewById(R.id.fech);
//        		TextView hora = (TextView)tabler.findViewById(R.id.hora);
//        		TextView sala = (TextView)tabler.findViewById(R.id.sala);
//        		TextView pa = (TextView)tabler.findViewById(R.id.pa);
//        		TextView dg = (TextView)tabler.findViewById(R.id.dg);
//        			
//        		TableRow trow = (TableRow) tabler;
//        						
//        		fech.setText(padre.get(index).get(0));
//        		hora.setText(padre.get(index).get(1));	
//        		sala.setText(padre.get(index).get(2));
//        		pa.setText(padre.get(index).get(3));
//        		dg.setText(padre.get(index).get(4));
//        				
//        		tl.addView(trow);
//        	}//Fin de ciclo for
            
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
            lista2.setAdapter(new MyVeryOwnArrayAdapter(getActivity(), R.layout.tablerow_editable, arrayDiferidas));
            lista2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                public void onItemClick(AdapterView<?> av, View view, int i, long l) {
                    //Toast.makeText(getActivity(), "Accediste a quirófano: "+padre.get(i).get(1), Toast.LENGTH_LONG).show();
                    
                    Fragment duedateFrag = new Accion();	//Nuevo item para las acciones
    		        Bundle bundle = new Bundle();
    		        String miArreglo[] = new String[6]; 	//Llenar arreglo con el nombre del paciente y el id
			              
			        miArreglo[0] = arrayDiferidas.get(i).get(0);		//Llenar con la fecha
			        miArreglo[1] = arrayDiferidas.get(i).get(1);		//Llenar con la hora
			        miArreglo[2] = arrayDiferidas.get(i).get(2);		//Sala	
			        miArreglo[3] = arrayDiferidas.get(i).get(3);		//Paciente
			        miArreglo[4] = arrayDiferidas.get(i).get(4);		//Diagnostico
			        miArreglo[5] = arrayDiferidas.get(i).get(5);		//ID
			        
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
			//Log.e("last-array", "last-array = "+al.get(0));
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
	
	
//	new RegistroTransoperatorio(inflater, container).execute(ID_quirofano);	//OBTENER CIRUGIAS TRANSOPERATORIO
//	new RegistroRealizada(inflater, container).execute(ID_quirofano);		//OBTENER CIRUGIAS REALIZADAS
	
	
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
	
}//Fin de la clase Item1