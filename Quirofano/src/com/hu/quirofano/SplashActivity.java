package com.hu.quirofano;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class SplashActivity extends Activity{ //Inicia la clase SplashActivity
	/*Duracion en milisegundos que durara el splash*/
	private final int duracion_splash = 3000; /*5 Segundos*/
	
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		
		/*Tenemos una plantilla llamada splash.xml donde mostraremos la informacion*/
		setContentView(R.layout.splash);
		
		new Handler().postDelayed(new Runnable(){
			public void run(){
				/*Cuando pasen los 3 segundos pasamos a la actividad principal de la aplicacion*/
				Intent intent = new Intent(SplashActivity.this, MainActivity.class);
				startActivity(intent);
				finish();
			};
		}, duracion_splash);
	}/*Fin del metodo onCreate*/
}//Fin de la clase SplashActivity
