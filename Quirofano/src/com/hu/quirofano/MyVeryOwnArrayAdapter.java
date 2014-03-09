package com.hu.quirofano;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class MyVeryOwnArrayAdapter extends ArrayAdapter<ArrayList<String>> {

	Context context;
	int resource;
	ArrayList<ArrayList<String>> objects;

	public MyVeryOwnArrayAdapter(Context context, int resource, ArrayList<ArrayList<String>> objects) {
		super(context, resource, objects);
		this.context = context;
		this.resource = resource;
		this.objects = objects;
	}

	static class MyVeryOwnHolder{
		TextView fecha;
		TextView hora;
		TextView sala;
		TextView paciente;
		TextView diagnostico;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		View row = convertView;
		MyVeryOwnHolder holder = null;

		if(row == null){

			LayoutInflater inflater = ((Activity)context).getLayoutInflater();
			row = inflater.inflate(resource, parent, false);

			holder = new MyVeryOwnHolder();
			
			holder.fecha = (TextView)row.findViewById(R.id.fech);
			holder.hora = (TextView)row.findViewById(R.id.hora);  
			holder.sala = (TextView)row.findViewById(R.id.sala);  
			holder.paciente = (TextView)row.findViewById(R.id.pa);   
			holder.diagnostico = (TextView)row.findViewById(R.id.dg);    

			row.setTag(holder);
		}
		else{

			holder = (MyVeryOwnHolder)row.getTag();
		}

		ArrayList<String> objectItem = objects.get(position);

		if(objectItem != null) {

			holder.fecha.setText(objectItem.get(0));
			holder.hora.setText(objectItem.get(1));
			holder.sala.setText(objectItem.get(2));
			holder.paciente.setText(objectItem.get(3));
			holder.diagnostico.setText(objectItem.get(4));

		}

		return row;
	}

}
