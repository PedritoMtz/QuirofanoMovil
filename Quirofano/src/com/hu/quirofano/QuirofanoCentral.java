package com.hu.quirofano;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import android.app.Activity;
import android.view.Menu;

import com.actionbarsherlock.app.SherlockFragment;
import com.actionbarsherlock.app.SherlockFragmentActivity;

public class QuirofanoCentral extends SherlockFragment{
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
		View v = inflater.inflate(R.layout.entrar, container, false);
		
		//Boton de home
		
				Button boton_home = (Button) v.findViewById(R.id.homeButton);
				boton_home.setOnClickListener(new View.OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						//mostrar("Has presionado el boton Entrar");
						try{
							Class<?> clazz = Class.forName("com.hu.quirofano.HomeActivity");
							//Intent intent = new Intent(getApplicationContext(), clazz);
							Intent intent = new Intent(getActivity().getApplicationContext(), clazz);
							startActivity(intent);
							
						}catch (ClassNotFoundException e){
							e.printStackTrace();
						}
					}
				});
		
		//Intent intent = new Intent(getActivity(), QCentralActivity.class);
        //startActivity(intent);
        return v;
		//return inflater.inflate(R.layout.qcentral, null);
	}
}//Fin de la clase Item1