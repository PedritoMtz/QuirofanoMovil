package com.hu.quirofano;

import com.actionbarsherlock.app.SherlockFragment;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/* 
 * Clase que cierra la sesion del usuario
 * y te lleva al activity inicial (login)
 * 
 * */
public class Item6 extends SherlockFragment {
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
		View v = inflater.inflate(R.layout.home, container, false);
		
		Intent intent = new Intent(getActivity(), MainActivity.class);
        startActivity(intent);
        return v;
		//return inflater.inflate(R.layout.qcentral, null);
	}
}//Fin de la clase
