package com.hu.quirofano;

import java.util.ArrayList;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.actionbarsherlock.app.SherlockFragment;
import com.hu.libreria.HttpPostAux;
import com.hu.quirofano.Item1.Agenda;

//Aqui va agenda del dia(cirugias programadas que no son diferidas)
public class MainView extends SherlockFragment{
	
	HttpPostAux post;
    String IP_Server="172.16.0.125";//IP DE NUESTRO PC
    String URL_connect="http://"+IP_Server+"/androidlogin/agendaDelDia.php";
    
    TableLayout tl;
    ListView lista;
    
    ArrayList<ArrayList<String>> padre = new ArrayList<ArrayList<String>>();
    boolean result_back;
    private ProgressDialog progress;
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
		post = new HttpPostAux();
		View v = inflater.inflate(R.layout.agendadeldia, container, false);
		final LinearLayout sv = (LinearLayout)v.findViewById(R.id.vista);
		final View home = inflater.inflate(R.layout.home, container, false);
		
		tl = (TableLayout)home.findViewById(R.id.table);
		TableRow tr = (TableRow) inflater.inflate(R.layout.tablerow, container, false);
		tl.addView(tr);
		
		lista = (ListView)home.findViewById(R.id.lista);
		
		String val = "ok";
		Log.e("antes de agenda", "antes de agenda");
		//new agendaDelDia().execute(val);
		
		new agendaDelDia(inflater, container).execute(val);
		
		SystemClock.sleep(1000);
		Log.e("paso1", "paso1");
			
//		LISTVIEW
//		for(int index = 0; index < padre.size(); index++){
//		
//			View tabler = inflater.inflate(R.layout.tablerow_editable, container, false);
//			
//			TextView fech = (TextView)tabler.findViewById(R.id.fech);
//			TextView hora = (TextView)tabler.findViewById(R.id.hora);
//			TextView sala = (TextView)tabler.findViewById(R.id.sala);
//			TextView pa = (TextView)tabler.findViewById(R.id.pa);
//			TextView dg = (TextView)tabler.findViewById(R.id.dg);
//			
//			TableRow trow = (TableRow) tabler;
//						
//			fech.setText(padre.get(index).get(0));
//			hora.setText(padre.get(index).get(1));	
//			sala.setText(padre.get(index).get(2));
//			pa.setText(padre.get(index).get(3));
//			dg.setText(padre.get(index).get(4));
//				
//			tl.addView(trow);
//		}
		
		//Fin de ciclo for
		sv.addView(home);
		
		return v;
		
	} //Fin de onCreateView
	
	public void mostrarAgendaDelDia(String s) throws JSONException{	
		
		String val = "";
		String value = "";
		String value1 = "";
		String value2 = "";
		String value3 = "";
		
		ArrayList<NameValuePair> datosEnviar= new ArrayList<NameValuePair>();
		datosEnviar.add(new BasicNameValuePair("stat", s));
		
		JSONArray jdata = post.getserverdata(datosEnviar, URL_connect);
		System.out.println(jdata.toString());
		System.out.println("largo de jdata = "+jdata.length());
		if (jdata!=null && jdata.length() > 0){
    		//JSONObject json_data; //creamos un objeto JSON
			try {
				
				for(int n = 0; n < jdata.length(); n++){
					//st.clear();
					System.out.println("vuelta:"+n);
					JSONObject json_data = jdata.getJSONObject(n);
					val = json_data.getString("dat");
					value = json_data.getString("dato");
					value1 = json_data.getString("dato1");
					value2 = json_data.getString("dato2");
					value3 = json_data.getString("dato3");
					
					ArrayList<String> temporary = new ArrayList<String>();
					
					temporary.add(val);
					temporary.add(value);
					temporary.add(value1);
					temporary.add(value2);
					temporary.add(value3);
					
					Log.e("log-st", "array temprary = "+temporary);
					
					padre.add(temporary);
				}
				
				Log.e("array padre", "padre = "+padre);
								
			}
			catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				Log.e("hi", "hi");
			}		            
			
    	}//Fin de if(comprueba si lo obtenido no es "null")
    	
    	else{	//json obtenido invalido verificar parte WEB.
    		Log.e("JSON  ", "ERROR");
	    }
	}//Fin de mostrarAgendaDelDia
	
	class agendaDelDia extends AsyncTask< String, String, String> {
		
		LayoutInflater inflater;
		ViewGroup container;
		
		agendaDelDia(LayoutInflater inflater, ViewGroup container){
			this.inflater = inflater;
			this.container = container;
		}
		
		//String date, hora, reg, paciente;
    	String st1; //El string que llevara la cadena "ok"
		
    	protected void onPreExecute() {
    		//progress = ProgressDialog.show(
    		//getActivity(), null, "Accesando a agenda...");
            super.onPreExecute();
        }
    	
        protected String doInBackground(String... params) {
			st1=params[0]; //obtenemos el string con "ok" 
			
			//enviamos y recibimos y analizamos los datos en segundo plano.
    		//al = mostrarAgenda(st1);		
    		try {
				mostrarAgendaDelDia(st1);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//Log.e("last-array", "last-array = "+al.get(0));
    		return "ok"; //login valido
    		
		}//Fin de doInBackground
       
        protected void onPostExecute(String resultado) {
        	//progress.dismiss();//ocultamos progess dialog.
            Log.e("onPostExecute=","Todo bien="+resultado);
    		lista.setAdapter(new MyVeryOwnArrayAdapter(getActivity(), R.layout.tablerow_editable, padre));


            //return al;
            //mostrarLeyenda();
            
        }//Fin de onPostExecute        
		
	}//Fin de la subclase agendaDelDia

} //Fin de la clase MainView
