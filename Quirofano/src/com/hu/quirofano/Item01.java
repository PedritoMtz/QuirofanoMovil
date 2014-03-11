package com.hu.quirofano;

import java.util.ArrayList;

import com.actionbarsherlock.app.SherlockFragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.os.Vibrator;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import android.widget.DatePicker;

import com.actionbarsherlock.app.SherlockFragment;
import com.hu.libreria.HttpPostAux;
import com.hu.quirofano.Item1.Agenda;

/*Fragment de Quirofano Central
 * Incluyendo por primera vez las funcionalidades de la Activity*/
public class Item01 extends SherlockFragment {

	//Bundle extras = getIntent().getExtras();
	//Obtenemos datos enviados en el intent.
	HttpPostAux post;
    //String IP_Server="192.168.1.73";//IP DE NUESTRO PC
	String IP_Server = MainActivity.IP_Server;
    String URL_connect="http://"+IP_Server+"/androidlogin/getQuirofanoName.php";
    
    View ll;
    
    ArrayList<ArrayList<String>> padre = new ArrayList<ArrayList<String>>();
    ArrayList<String> nombresQuirofanos = new ArrayList<String>();
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
		
		post = new HttpPostAux();
		
		String val = "ok";
		new GetQuirofanoName(inflater, container).execute(val);
		
		View v = inflater.inflate(R.layout.lista_quirofanos, container, false);
		ll = v.findViewById(R.id.info);
		
		return v;
	}//Fin de onCreateView
	
	public void getQuirofanoName(String s){
		String id = "";
		String nombre = "";
		
		ArrayList<NameValuePair> datosEnviar= new ArrayList<NameValuePair>();
		datosEnviar.add(new BasicNameValuePair("stat", s));
		
		JSONArray jdata=post.getserverdata(datosEnviar, URL_connect);
		
		System.out.println(jdata.toString());
		System.out.println("largo de jdata = "+jdata.length());
		
		if (jdata!=null && jdata.length() > 0){
    		//JSONObject json_data; //creamos un objeto JSON
			try {
				
				for(int n = 0; n < jdata.length(); n++){
					//st.clear();
					System.out.println("vuelta:"+n);
					JSONObject json_data = jdata.getJSONObject(n);
					id = json_data.getString("dato");
					nombre = json_data.getString("dato1");
					
					ArrayList<String> temporary = new ArrayList<String>();
					
					temporary.add(id);
					temporary.add(nombre);
					
					Log.e("log-st", "array temprary = "+temporary);
					
					padre.add(temporary);
					//st.clear();
				}
				
				Log.e("array padre", "padre = "+padre);
								
			}
			catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				Log.e("hi", "hi");
			}		            
			//return st;
			
    	}//Fin de if(comprueba si lo obtenido no es "null")
    	
    	else{	//json obtenido invalido verificar parte WEB.
    		Log.e("JSON  ", "ERROR");
	    	//return st;
	    }

	}
	
	class GetQuirofanoName extends AsyncTask< String, String, String> {
		
		LayoutInflater inflater;
		ViewGroup container;
		
		GetQuirofanoName(LayoutInflater inflater, ViewGroup container){
			this.inflater = inflater;
			this.container = container;
		}
		
    	String quir; //El string llevara el quirofano_name
		
    	protected void onPreExecute() {
    		//progress = ProgressDialog.show(
    		//getActivity(), null, "Accesando a agenda...");
            super.onPreExecute();
        }
    	
        protected String doInBackground(String... params) {
			quir=params[0]; //String "ok" 
			
			getQuirofanoName(quir);//(quir);
			//Log.e("last-array", "last-array = "+al.get(0));
    		return "ok"; //login valido
    		
		}//Fin de doInBackground
       
        protected void onPostExecute(String resultado) {
        	
        	OnClickListener clicks=new OnClickListener() {

    		    @Override
    		    public void onClick(View v) {
    		    		//Esta es la posicion del array padre sobre el que se hizo click
    		    		System.out.println("id = "+v.getId());  
    		    		
    		    		//Ahora se debe obtener el quirofano_id y el quirofano_name, desde v.getId()
    		            
    		    		//switch(v.getId())
    		            //{
    			            //case 0: System.out.println("0");
    		                //break;
	    		        Fragment duedateFrag = new Item1();	
	    		        Bundle bundle = new Bundle();
	    		                	    		                
	    		        String miArreglo[] = new String[2];
	    		              
	    		        miArreglo[0] = padre.get(v.getId()).get(0);		//llenar con id_quirofano
	    		        miArreglo[1] = padre.get(v.getId()).get(1);		//llenar con el nombre del quirofano
	    		                
	    		        System.out.println("POSICION DE QUIROFANO_ID = "+v.getId());
	    		        System.out.println("ARREGLO-QUIROFANO_ID = "+miArreglo[0]);
	    		        System.out.println("ARREGLO-QUIROFANO_NAME = "+miArreglo[1]);
	    		                	    		                	    		                
	    		        bundle.putStringArray("parametro", miArreglo); //Arreglo para mandar a Item1
	    		        duedateFrag.setArguments(bundle);
	    		                
	    		        FragmentTransaction ft  = getFragmentManager().beginTransaction();
	    		        ft.replace(R.id.content_frame, duedateFrag);
	    		        ft.addToBackStack(null);
	    		        ft.commit();
	    		        //break;
    		                
    		            //}       
    		    } //Fin de onClick
    		}; //Fin de onClickListener
        	
        	//View v = inflater.inflate(R.layout.lista_quirofanos, container, false);
    		//View ll = v.findViewById(R.id.info);
        	
        	for (int i = 0; i<padre.size(); i++){
    			TextView tv = (TextView) new TextView(getActivity());
    			tv.setText(padre.get(i).get(1));
    			tv.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT,LayoutParams.WRAP_CONTENT));
    			((LinearLayout) ll).addView(tv);
    			
    			//Primero pasamos la posicion del arraypadre en el que se hizo click
    			tv.setId(i);
    			//Opcion2 - settear Id con la posición de "padre" en que se hizo click
    			tv.setOnClickListener(clicks);
    		}//Fin de for
            
        }//Fin de onPostExecute        
	}//Fin de la subclase GetQuirofanoName
	
}//Fin de la clase Item01
  	             