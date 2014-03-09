package com.hu.quirofano;

import android.os.Bundle;
import android.content.Intent;
import android.content.res.Resources;
import android.app.Activity;
import android.app.TabActivity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TabHost;

public class CAmbulatoriaActivity extends TabActivity {

	TabHost tabHost;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.c_ambulatoria);
		// Boton de entrar
		
		Resources res = getResources();
		
        tabHost = getTabHost();  // The activity TabHost  
        TabHost.TabSpec spec2;  // Resusable TabSpec for each tab    
        Intent intent;  // Reusable Intent for each tab  
		
		TabHost tabs=(TabHost)findViewById(android.R.id.tabhost);
		tabs.setup();
		 
		TabHost.TabSpec spec=tabs.newTabSpec("mitab1");
		spec.setContent(R.id.tab1);
		spec.setIndicator("Agenda",
		    res.getDrawable(android.R.drawable.ic_btn_speak_now));
		tabs.addTab(spec);
		
		/*Para tab 2*/
		intent = new Intent().setClass(this, ProgramarCirugiaAmbulatoria.class);  
        spec2 = tabHost.newTabSpec("mitab2").setIndicator("Programar cirugía",res.getDrawable(android.R.drawable.ic_dialog_map)).setContent(intent); 
        tabHost.addTab(spec2);
		
		
		//spec=tabs.newTabSpec("mitab2");
		//spec.setContent(R.id.tab2);
		//spec.setIndicator("Programar cirugía",
		//    res.getDrawable(android.R.drawable.ic_dialog_map));
		//	tabs.addTab(spec);
		
		/*Para tab 3*/
		spec=tabs.newTabSpec("mitab3");
		spec.setContent(R.id.tab3);
		spec.setIndicator("Cirugías diferidas",
		    res.getDrawable(android.R.drawable.ic_dialog_map));
		tabs.addTab(spec);
		
		/*Para tab 4*/
		spec=tabs.newTabSpec("mitab4");
		spec.setContent(R.id.tab4);
		spec.setIndicator("Salas",
		    res.getDrawable(android.R.drawable.ic_dialog_map));
		tabs.addTab(spec);
		 
		tabs.setCurrentTab(0);
		
	}//Fin de onCreate
	
}//Fin de la clase


