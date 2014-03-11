package com.hu.quirofano;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.EditText;
import android.widget.Toast;

public class ProgramarCirugiaView extends Activity {

	/*
	 * 17 oct 
	 * 
	 * Las posiciones de los spinners estan dados por el entero "position"
	 * 
	 * 18 noviembre
	 * 
	 * Instanciar objeto de la clase Item1 para traer el valor de salas del respectivo quirofano
	 * Llenar los elementos del spinner "salas" con el valor obtenido de Item1 
	 * */

	ArrayList<String> salasList = new ArrayList<String>();
	
	//18-dic < serviciosList
	ArrayList<String> serviciosList = new ArrayList<String>();
	static int serviciosSpinner;
	static int serviciosSpinner2;
	//Fin 18-dic
	
	static ArrayList<ObjectoX> vista = new ArrayList<ObjectoX>();
	static //ObjetoSpinner obj = new ObjetoSpinner();
	
	int sala;
	static String duracion;
	static int programacion;
	static int servicio;
	static int atencion;
	
	ArrayList<ArrayList<String>> procedimientos = new ArrayList<ArrayList<String>>();
	
	EditText fecha, horaPropuesta, registro, nombrePaciente, edad, genero, procedencia;
	EditText diagnostico, nombreMedico, riesgoQuirurgico, insumos, requerimientosAnestesiologia;
	EditText hemoderivados, requisitosLaboratorio, otrasNecesidades;

	//int spin1, spin2, spin3, spin4, spin5, spin6;
	ArrayList<Integer> spins = new ArrayList<Integer>();

	//public ProgramarCirugiaView(final LayoutInflater inflater, View view, final Context context){
//	public void onCreate(Bundle savedInstanceState) {
//	    super.onCreate(savedInstanceState);
//	    setContentView(R.layout.programar_cirugia);
	

	public void prog(final LayoutInflater inflater, View view, final Context context){
//		serviciosList = Item1.nombreServicios;
//		System.out.println("SERVICES EN VIEW = "+serviciosList);
//		/*Spinner 1 - Salas*/
//		salasList.add("enero");
//		salasList.add("febrero");
//		salasList.add("marzo");
//		//final ObjetoSpinner obj = new ObjetoSpinner(inflater, context);
//				
//		Spinner sp = (Spinner) view.findViewById(R.id.salaOpciones);
//		ArrayAdapter<CharSequence> adapter = new ArrayAdapter<CharSequence>(this,
//		        android.R.layout.simple_spinner_item);
//		    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//		    
//		adapter.add("Hola");
//		adapter.add("que hace");
//		
////		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
////				context, R.array.salas, android.R.layout.simple_spinner_item);
////		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//		sp.setAdapter(adapter);
//
//		sp.setOnItemSelectedListener(new OnItemSelectedListener() {
//
//			public void onItemSelected(AdapterView<?> parentView, View selectedItemView,
//					int position, long id) {
//				//Toast.makeText(parentView.getContext(), "Has seleccionado " +
//				//parentView.getItemAtPosition(position).toString(), Toast.LENGTH_LONG).show();
//				parentView.getItemAtPosition(position);
//				//spins.add(position);
//				//Log.e("arreglo spins", "s1="+spins);
//				Log.e("spiner1 = ", "posicion1="+position);
//				//spin1 = position;
//				//setSala(position);
//				
//				int sala=position;
//				ProgramarCirugiaView.sala = sala;
//				//System.out.println("sala origi="+sala);
//				//= position;
//			}
//
//			public void onNothingSelected(AdapterView<?> parentView) {
//
//			}
//		});

		/*Ahora para el spinner 2 - Duracion de la cirugia
		 ************************************************** */
		Spinner sp1 = (Spinner) view.findViewById(R.id.duracion);
		ArrayAdapter adapter1 = ArrayAdapter.createFromResource(
				context, R.array.duraciones, android.R.layout.simple_spinner_item);
		adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		sp1.setAdapter(adapter1);

		sp1.setOnItemSelectedListener(new OnItemSelectedListener() {

			public void onItemSelected(AdapterView<?> parentView, View selectedItemView,
					int position, long id) {
				//Toast.makeText(parentView.getContext(), "Has seleccionado " +
				//parentView.getItemAtPosition(position).toString(), Toast.LENGTH_LONG).show();
				String tempo = parentView.getItemAtPosition(position).toString();
				//spins.add(position);
				//setDuracion(position);
				int duracion = position;
				ProgramarCirugiaView.duracion = tempo;
				//duracion = position;
			}

			public void onNothingSelected(AdapterView<?> parentView) {

			}
		});
		/*Termina el spinner 2*/

		/*Ahora para el spinner 3 - Tipo de programacion
		 ************************************************** */
		Spinner sp2 = (Spinner) view.findViewById(R.id.tipoDeProgramacion);
		ArrayAdapter adapter2 = ArrayAdapter.createFromResource(
				context, R.array.tiposDeProgramacion, android.R.layout.simple_spinner_item);
		adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		sp2.setAdapter(adapter2);

		sp2.setOnItemSelectedListener(new OnItemSelectedListener() {

			public void onItemSelected(AdapterView<?> parentView, View selectedItemView,
					int position, long id) {
				//Toast.makeText(parentView.getContext(), "Has seleccionado " +
				//parentView.getItemAtPosition(position).toString(), Toast.LENGTH_LONG).show();
				//parentView.getItemAtPosition(position);
				//spins.add(position);
				//obj.programacion = position;
				//setProgramacion(position);
				//programacion = position;
				int programacion = position;
				ProgramarCirugiaView.programacion = programacion;
			}

			public void onNothingSelected(AdapterView<?> parentView) {

			}
		});
		/*Termina el spinner 3*/

		/*Ahora para el spinner 4 - Servicio
		 ************************************************** */
		serviciosList = Item1.nombreServicios;
		System.out.println("SERVICES EN VIEW = "+serviciosList);
		
		/** NEW SERVICIOS */
		Spinner sp = (Spinner) view.findViewById(R.id.servicio);

		ArrayAdapter<String> adapter = new ArrayAdapter<String>(context,
                android.R.layout.simple_spinner_item, serviciosList);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		
		sp.setAdapter(adapter);

		sp.setOnItemSelectedListener(new OnItemSelectedListener() {

			public void onItemSelected(AdapterView<?> parentView, View selectedItemView,
					int position, long id) {
				//Toast.makeText(parentView.getContext(), "Has seleccionado " +
				//parentView.getItemAtPosition(position).toString(), Toast.LENGTH_LONG).show();
				//parentView.getItemAtPosition(position);
				//spins.add(position);
				//Log.e("arreglo spins", "s1="+spins);
				//Log.e("spiner1 = ", "posicion1="+position);
				//spin1 = position;
				//setSala(position);
		
				
				serviciosSpinner=position;
				//Log.e("spiner1 = ", "posicion1="+Item1.salaSpinner);
				//ProgramarCirugiaView.sala = sala;
				//System.out.println("sala origi="+sala);
				//= position;
			}

			public void onNothingSelected(AdapterView<?> parentView) {

			}
		});
		
		/** FIN DE NEW SERVICIOS */
		
		
//		Spinner sp3 = (Spinner) view.findViewById(R.id.servicio);
//		ArrayAdapter adapter3 = ArrayAdapter.createFromResource(
//				context, R.array.servicios, android.R.layout.simple_spinner_item);
//		adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//		sp3.setAdapter(adapter3);
//
//		sp3.setOnItemSelectedListener(new OnItemSelectedListener() {
//
//			public void onItemSelected(AdapterView<?> parentView, View selectedItemView,
//					int position, long id) {
//				//Toast.makeText(parentView.getContext(), "Has seleccionado " +
//				//parentView.getItemAtPosition(position).toString(), Toast.LENGTH_LONG).show();
//				parentView.getItemAtPosition(position);
//				//spins.add(position);
//				//obj.servicio = position;
//				//setServicio(position);
//				//servicio = position;
//				int servicio = position;
//				ProgramarCirugiaView.servicio = servicio;
//			}
//
//			public void onNothingSelected(AdapterView<?> parentView) {
//
//			}
//		});
		/*Termina el spinner 4*/

		/*Ahora para el spinner 5 - En atencion a
		 ************************************************** */
		Spinner sp4 = (Spinner) view.findViewById(R.id.enAtencionA);
		ArrayAdapter adapter4 = ArrayAdapter.createFromResource(
				context, R.array.atencionA, android.R.layout.simple_spinner_item);
		adapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		sp4.setAdapter(adapter4);

		sp4.setOnItemSelectedListener(new OnItemSelectedListener() {

			public void onItemSelected(AdapterView<?> parentView, View selectedItemView,
					int position, long id) {
				//Toast.makeText(parentView.getContext(), "Has seleccionado " +
				//parentView.getItemAtPosition(position).toString(), Toast.LENGTH_LONG).show();
				parentView.getItemAtPosition(position);
				//spins.add(position);
				//obj.atencion = position;
				//setAtencion(position);
				//atencion = position;
				int atencion = position;
				ProgramarCirugiaView.atencion = atencion;
			}

			public void onNothingSelected(AdapterView<?> parentView) {

			}
		});
		final LinearLayout parent = (LinearLayout)view.findViewById(R.id.add_linear);

		Button agregar = (Button)view.findViewById(R.id.agregar);
		Button eliminar = (Button)view.findViewById(R.id.eliminar);
		
		//Button test = (Button)view.findViewById(R.id.test);
		vista.clear();
		parent.removeAllViews();	//ELIMINAR LAS VISTAS DE LOS PROCEDIMIENTOS DINAMICOS
		/*
		test.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				for (int index = 0; index<vista.size(); index++){
					System.out.println(index+"####################################");
					
					ArrayList<String> temporary = new ArrayList<String>();
					String servicioString = Integer.toString(vista.get(index).getServicio());
					String regionString = Integer.toString(vista.get(index).getRegion());
					
					temporary.add(servicioString);
					temporary.add(regionString);
					temporary.add(vista.get(index).getProc());
					temporary.add(vista.get(index).getDetalles());
					
					procedimientos.add(temporary);
					
					System.out.println(vista.get(index).getServicio());
					System.out.println(vista.get(index).getRegion());
					System.out.println(vista.get(index).getProc());
					System.out.println(vista.get(index).getDetalles());
				}
				System.out.println("PROCEDIMIENTOS: "+procedimientos);
				procedimientos.clear();

			}});
		*/
//		********************************************************************+
		final ObjectoX objeto = new ObjectoX(inflater, context);
		System.out.println("VISTA = "+(vista.size()+1));
		vista.add(objeto);
		LinearLayout ll = (LinearLayout) objeto.getView();
		parent.addView(ll);
		
		Spinner region = (Spinner) ll.findViewById(R.id.region);
		ArrayAdapter adapter5 = ArrayAdapter.createFromResource(
				context, R.array.listaRegion, android.R.layout.simple_spinner_item);
		adapter5.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		region.setAdapter(adapter5);

		region.setOnItemSelectedListener(new OnItemSelectedListener() {

			public void onItemSelected(AdapterView<?> parentView, View selectedItemView,
					int position, long id) {
				objeto.region = position;
			}
			public void onNothingSelected(AdapterView<?> parentView) {

			}
		});
		
		Spinner servicio = (Spinner) ll.findViewById(R.id.servicio2);
//		ArrayAdapter adapter6 = ArrayAdapter.createFromResource(
//				context, R.array.servicios2, android.R.layout.simple_spinner_item);
//		adapter6.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//		servicio.setAdapter(adapter6);
		
		ArrayAdapter<String> adapter6 = new ArrayAdapter<String>(context,
                android.R.layout.simple_spinner_item, serviciosList);
                adapter6.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		
		servicio.setAdapter(adapter6);
		
		servicio.setOnItemSelectedListener(new OnItemSelectedListener() {

			public void onItemSelected(AdapterView<?> parentView, View selectedItemView,
					int position, long id) {
				objeto.servicio = position;
			}
			public void onNothingSelected(AdapterView<?> parentView) {

			}
		});
//		********************************************************************+
		
		
		agregar.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				
				if ((vista.size()+1)<5){
				final ObjectoX objeto = new ObjectoX(inflater, context);
				System.out.println("VISTA = "+(vista.size()+1));
				vista.add(objeto);
				LinearLayout ll = (LinearLayout) objeto.getView();
				parent.addView(ll);

				Spinner region = (Spinner) ll.findViewById(R.id.region);
				ArrayAdapter adapter5 = ArrayAdapter.createFromResource(
						context, R.array.listaRegion, android.R.layout.simple_spinner_item);
				adapter5.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
				region.setAdapter(adapter5);

				region.setOnItemSelectedListener(new OnItemSelectedListener() {

					public void onItemSelected(AdapterView<?> parentView, View selectedItemView,
							int position, long id) {
						objeto.region = position;
					}
					public void onNothingSelected(AdapterView<?> parentView) {

					}
				});
				
				Spinner servicio = (Spinner) ll.findViewById(R.id.servicio2);
//				ArrayAdapter adapter6 = ArrayAdapter.createFromResource(
//						context, R.array.servicios2, android.R.layout.simple_spinner_item);
//				adapter6.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//				servicio.setAdapter(adapter6);
				
				ArrayAdapter<String> adapter6 = new ArrayAdapter<String>(context,
		                android.R.layout.simple_spinner_item, serviciosList);
		                adapter6.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
				
				servicio.setAdapter(adapter6);
				
				servicio.setOnItemSelectedListener(new OnItemSelectedListener() {

					public void onItemSelected(AdapterView<?> parentView, View selectedItemView,
							int position, long id) {
						objeto.servicio = position;
					}
					public void onNothingSelected(AdapterView<?> parentView) {

					}
				});
			} //Fin de if
			} //Fin de onClick
			}); //Fin de onClickListener 

		eliminar.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				int index =  parent.getChildCount();
				if (index != 1){
				System.out.println("VISTA ELIMINADA = "+(vista.size()-1));
//				System.out.println("VISTA ELIMINADA = "+parent.getChildCount()-1);
//				int index =  parent.getChildCount();
//				if (index != 1){
					parent.removeViewAt(index-1);
					vista.remove(vista.size()-1);
				}
			}
		});
	}/*Fin de constructor*/
//	}
	
	//setters
	public void setSala(int sala){
		this.sala = sala;
	}
	public void setDuracion(String duracion){
		this.duracion = duracion;
	}
	public void setProgramacion(int programacion){
		this.programacion = programacion;
	}
	public void setServicio(int servicio){
		this.servicio = servicio;
	}
	public void setAtencion(int atencion){
		this.atencion = atencion;
	}
	
	//getters
	public int getSala(){
		return sala;
	}//Fin de getSala
	
	public String getDuracion(){
		return duracion;
	}//Fin de getDuracion
	
	public int getProgramacion(){
		return programacion;
	}//Fin de getDuracion
	
	public int getServicio(){
		//return servicio;
		return serviciosSpinner;
	}//Fin de getServicio
	
	public int getServicio2(){
		return serviciosSpinner2;
	}
	
	public int getAtencion(){
		return atencion;
	}//Fin de getDuracion
	
	
}/*Fin de la clase ProgramarCirugiaView*/
