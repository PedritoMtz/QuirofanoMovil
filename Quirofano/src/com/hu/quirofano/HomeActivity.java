package com.hu.quirofano;

import android.os.Bundle;
import android.content.Intent;
import android.app.Activity;
import android.app.ListActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import android.view.Menu;
import android.view.View;
import android.widget.Button;

import com.jeremyfeinstein.slidingmenu.lib.app.SlidingActivity;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingListActivity;

public class HomeActivity extends SlidingActivity {
	String pruebas[] = { "LifeCycleTest", "SingleTouchTest", "MultiTouchTest", 
			"KeyTest", "AccelerometerTest", "AssetsTest", "ExternalStorageTest",
			"SoundPoolTest", "MediaPlayerTest", "FullScreenTest", "RenderView",
			"FontTest", "SurfaceViewTest" };

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.home);
		setBehindContentView(R.layout.activity_menu);
		getSlidingMenu().setBehindOffset(200);
		showContent();
		
	}//Fin de onCreate
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
}//Fin de la clase
