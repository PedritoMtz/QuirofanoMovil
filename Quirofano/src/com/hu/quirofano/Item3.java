package com.hu.quirofano;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.actionbarsherlock.app.SherlockFragment;

public class Item3 extends SherlockFragment {
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
		View v = inflater.inflate(R.layout.home, container, false);
		
		Intent intent = new Intent(getActivity(), Traumatologia.class);
        startActivity(intent);
        return v;
		//return inflater.inflate(R.layout.qcentral, null);
	}
}//Fin de la clase Item3
