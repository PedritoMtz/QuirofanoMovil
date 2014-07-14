package com.hu.quirofano;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class RandomList extends ListFragment{
	
	String p= "Prueba";
	
	String[] list_contents = {
		"Lista de Quirófanos",
		//"Quirófano central",
		//"Agenda del día",
		"Contacto",
		"Cerrar sesión"
	};
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
		return inflater.inflate(R.layout.list, container, false);
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState){
		super.onActivityCreated(savedInstanceState);
		setListAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, list_contents));
	}
	
	@Override
	public void onListItemClick(ListView l, View v, int position, long id){
		Fragment newFragment = new MainView();
		FragmentManager fm = getFragmentManager();
		switch (position){
		case 0:
			newFragment = new Item02(); //Lista de quirofanos
			break;
		case 1:
			newFragment = new Item5(); //Contacto
			break;
		case 2:
			newFragment = new Item6(); //Cerrar sesion
			break;
		//case 3:
		//	newFragment = new Item6(); //Cerrar sesion
		//	break;
		//case 5:
		//	newFragment = new Item6(); //Cerrar sesion
		//	break;
		}

		if (newFragment != null)
			switchContent(newFragment);
		FragmentTransaction ft = fm.beginTransaction();
		ft.replace(R.id.content_frame, newFragment);
		ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
		ft.commit();
	}
	
	private void switchContent(Fragment fragment){
		MainActivity2 ma = (MainActivity2)getActivity();
		ma.switchContent(fragment);
	}

}
