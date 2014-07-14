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
import android.widget.ListView;
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
public class Item02 extends SherlockFragment {

	//Bundle extras = getIntent().getExtras();
	//Obtenemos datos enviados en el intent.
	HttpPostAux post;
    //String IP_Server="172.16.0.125";//IP DE NUESTRO PC
	String IP_Server = MainActivity.IP_Server;
	String URL_connect="http://"+IP_Server+"/androidlogin/getQuirofanoName.php";
    
    View ll;
    TextView agregarTema;
    
    ArrayList<ArrayList<String>> padre = new ArrayList<ArrayList<String>>();
    ArrayList<String> nombres = new ArrayList<String>();
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{		
		post = new HttpPostAux();
		
		String val = "ok";
//		new GetQuirofanoName(inflater, container, v).execute(val);
		View v = inflater.inflate(R.layout.lista_quirofanos, container, false);
		new GetQuirofanoName(inflater, container, v).execute(val); //Llamamos a la clase
		
		ll = v.findViewById(R.id.info);
		
		return v;
	}//Fin de onCreateView
	
	//Función que se encarga de llamar al php para obtener los quirofanos de la BD
	public void getNamesforBD(String s)
	{
		String id = "";
		String nombre = "";
		
		ArrayList<NameValuePair> datosEnviar= new ArrayList<NameValuePair>();
		datosEnviar.add(new BasicNameValuePair("stat", s));
		
		JSONArray jdata = post.getserverdata(datosEnviar, URL_connect);
		
		System.out.println("Esto que es:"+jdata.toString());
		System.out.println("largo de jdata = "+jdata.length());
		
		if (jdata!=null && jdata.length() > 0)
		{
			//JSONObject json_data; //creamos un objeto JSON
			try
			{
				for(int n = 0; n < jdata.length(); n++)
				{
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
			}catch (JSONException e) {
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
	
	class GetQuirofanoName extends AsyncTask< String, String, String> 
	{
		LayoutInflater inflater;
		ViewGroup container;
		View v;
		
		//Constructor de la clase
		GetQuirofanoName(LayoutInflater inflater, ViewGroup container, View v)
		{
			this.inflater = inflater;
			this.container = container;
			this.v = v;
		}
		
    	String quir; //El string llevara el quirofano_name
		
    	protected void onPreExecute()
    	{
    		//progress = ProgressDialog.show(
    		//getActivity(), null, "Accesando a agenda...");
            super.onPreExecute();
        }
    	
        protected String doInBackground(String... params)
        {
			quir=params[0]; //String "ok" 
			
			getNamesforBD(quir);//Llamamos a la función
			//Log.e("last-array", "last-array = "+al.get(0));
    		return "ok"; //login valido
    		
		}//Fin de doInBackground
       
        protected void onPostExecute(String resultado)
        {
        	for (int i = 0; i<padre.size(); i++)
        	{		
    			//Guardamos en un array todos los nombres de los quirofanos para desplegarlos en la listView
    			nombres.add(padre.get(i).get(1));
    		}//Fin de for
        	
        	ListView lista = new ListView(getActivity());
        	lista.setAdapter(new ArrayAdapter<String>(getActivity(), 
    				android.R.layout.simple_list_item_1, nombres));
        	
        	lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                public void onItemClick(AdapterView<?> av, View view, int i, long l)
                {
                    Toast.makeText(getActivity(), "Accediste a Quirófano: "+padre.get(i).get(1), Toast.LENGTH_LONG).show();
                    
                    Fragment duedateFrag = new Item1();	
    		        Bundle bundle = new Bundle();
    		        String miArreglo[] = new String[2];
			              
			        miArreglo[0] = padre.get(i).get(0);		//llenar con id_quirofano
			        miArreglo[1] = padre.get(i).get(1);		//llenar con el nombre del quirofano
			        
			        bundle.putStringArray("parametro", miArreglo); //Arreglo para mandar a Item1
    		        duedateFrag.setArguments(bundle);
    		                
    		        FragmentTransaction ft  = getFragmentManager().beginTransaction();
    		        ft.replace(R.id.content_frame, duedateFrag);
    		        ft.addToBackStack(null);
    		        //duedateFrag.getFragmentManager().popBackStackImmediate();
    		        ft.commit();
                }// Fin de onItemClick
            });
//    		lista.setAdapter(new ArrayAdapter<String>(getActivity(), 
//    				android.R.layout.simple_list_item_1, new String[]{"1","2","3","4"}));
        	if (nombres.size()==0){
    			agregarTema = (TextView)v.findViewById(R.id.lista_quirofanos);
    			agregarTema.setText("No hay quirófanos disponibles");
    		}
    		((LinearLayout)ll).addView(lista);
    		
//    		OnClickListener clicks=new OnClickListener() {
//
//    		    @Override
//    		    public void onClick(View v) {
//
//    		            switch(v.getId())
//    		            {
//    		                case 1000: System.out.println("FIRST");
//    		                break;
//
//    		                case 1004: System.out.println("FOURTH");
//    		                break; 
//    		            }       
//    		    }
//    		};
//    		for (int toShow = 1; toShow <= nShips; toShow++)
//    		{
//    		        btn = new Button(this);
//    		        btn.setBackgroundResource(shipDrawable.get(ima));
//    		        btn.setLayoutParams(params);
//    		        row[pos].addView(btn);
//    		        btn.setId(shipId.get(ima));
//    		        btn.setOnClickListener(clicks);
//    		        if (row[pos].getChildCount() == 3) pos++;
//    		        ima++;
//    		}
        	
        }//Fin de onPostExecute        
	}//Fin de la subclase GetQuirofanoName
	
}//Fin de la clase Item02
  	             