package com.hu.quirofano;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class MyVeryOwnArrayAdapter2 extends ArrayAdapter<ArrayList<String>> {

	Context context;
	int resource;
	ArrayList<ArrayList<String>> objects;

	public MyVeryOwnArrayAdapter2(Context context, int resource, ArrayList<ArrayList<String>> objects) {
		super(context, resource, objects);
		this.context = context;
		this.resource = resource;
		this.objects = objects;
	}

	static class MyVeryOwnHolder{
		TextView sala;
		TextView activo;
		TextView bloqueado;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		View row = convertView;
		MyVeryOwnHolder holder = null;

		if(row == null){

			LayoutInflater inflater = ((Activity)context).getLayoutInflater();
			row = inflater.inflate(resource, parent, false);

			holder = new MyVeryOwnHolder();
			
			holder.sala = (TextView)row.findViewById(R.id.sala_name);
			holder.activo = (TextView)row.findViewById(R.id.sala_activo);  
			holder.bloqueado = (TextView)row.findViewById(R.id.sala_bloqueado);     

			row.setTag(holder);
		}
		else{

			holder = (MyVeryOwnHolder)row.getTag();
		}

		ArrayList<String> objectItem = objects.get(position);

		if(objectItem != null) {

			holder.sala.setText(objectItem.get(1));
			holder.activo.setText(objectItem.get(2));
			holder.bloqueado.setText(objectItem.get(3));
		}

		return row;
	}

}
