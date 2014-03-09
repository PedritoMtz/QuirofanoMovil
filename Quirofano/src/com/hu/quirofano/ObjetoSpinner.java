package com.hu.quirofano;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.AdapterView.OnItemSelectedListener;

public class ObjetoSpinner {
	
	int sala, duracion, programacion, servicio, atencion;
	
	public ObjetoSpinner(LayoutInflater li, Context context){
		
	}//Fin de constructor
	
	//--------------------
	
	public int getSala(){
		return sala;
	}//Fin de getSala
	
	public int getDuracion(){
		return duracion;
	}//Fin de getDuracion
	
	public int getProgramacion(){
		return programacion;
	}//Fin de getDuracion
	
	public int getServicio(){
		return servicio;
	}//Fin de getServicio
	
	public int getAtencion(){
		return atencion;
	}//Fin de getDuracion
	
}/*Fin de la clase ObjetoSpinner*/
