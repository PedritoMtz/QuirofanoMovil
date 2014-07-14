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

public class ObjectoX {

	EditText proc;
	EditText detalles;
	int region;
	int servicio;
	LinearLayout ll;

	public ObjectoX(LayoutInflater li, Context context){
		ll = (LinearLayout) li.inflate(R.layout.add_linear, null);
		proc = (EditText)ll.findViewById(R.id.procedimientoOCirugia);
		detalles = (EditText)ll.findViewById(R.id.detallesAdicionales);

	}
	
	public int getRegion(){
		return region;
	}

	public int getServicio(){
		return servicio;
	}
	
	public void setRegion(int region){
		this.region =region;
	}

	public void setServicio(int servicio){
		this.servicio= servicio;
	}
	
	public String getProc(){
		return proc.getText().toString();
	}

	public String getDetalles(){
		return detalles.getText().toString();
	}

	public View getView() {
		// TODO Auto-generated method stub
		return ll;
	}
}