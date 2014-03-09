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
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
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
import com.hu.quirofano.Item02.GetQuirofanoName;
import com.hu.quirofano.Item1.Agenda;

public class AccionRealizada extends SherlockFragment{

	HttpPostAux post;
    String IP_Server="172.16.0.125";//IP DE NUESTRO PC
    String URL_connect="http://"+IP_Server+"/androidlogin/cancelarCirugia.php";
    
    View ll;
    TextView agregarTema;
    String myString[] = new String[6];
    
    ArrayList<ArrayList<String>> padre = new ArrayList<ArrayList<String>>();
    ArrayList<String> nombres = new ArrayList<String>();
	
    public View onCreateView(LayoutInflater inflater, ViewGroup container, final Bundle savedInstanceState){
    	
    	post = new HttpPostAux();
    	View v = inflater.inflate(R.layout.acciones_realizada, container, false);
    	final View iniciar = inflater.inflate(R.layout.transoperatorio, container, false);
    	final View diferir = inflater.inflate(R.layout.diferir, container, false);
    	
		final ScrollView sv = (ScrollView)v.findViewById(R.id.vista);
		
    	Bundle bundle = this.getArguments();
		myString = bundle.getStringArray("parametro");
		System.out.println("ITEM1 RECIBIDO nombre del paciente seleccionado = "+myString[3]);
		//System.out.println("ITEM1 RECIBIDO quirofano_name = "+myString[1]);
		TextView nombre = (TextView)v.findViewById(R.id.nombre_paciente);
		nombre.setText("Paciente: "+myString[3]);
		//ID_quirofano = myString[0];
    	//v.addView();
		//ll = v.findViewById(R.id.info);
		
//		Button cancelar = (Button)v.findViewById(R.id.cancelar);
//		Button iniciarButton = (Button)v.findViewById(R.id.iniciar);
//		Button diferirButton = (Button)v.findViewById(R.id.diferir);
		//Button po = (Button)v.findViewById(R.id.po);
		//Button salas = (Button)v.findViewById(R.id.salas);
		
		//sv.addView(v);
		
//		po.setOnClickListener(new OnClickListener(){
//
//			@Override
//			public void onClick(View v) {
//				sv.removeAllViews();
//				
//			}
//		});
		
		//CANCELAR BUTTON
//		cancelar.setOnClickListener(new OnClickListener(){
//
//			@Override
//			public void onClick(View v) {
//				
//				AlertDialog.Builder dialogo1 = new AlertDialog.Builder(getActivity());  
//		        dialogo1.setTitle("Importante");  
//		        dialogo1.setMessage("¿Desea cancelar la cirugía?");            
//		        dialogo1.setCancelable(false);  
//		        dialogo1.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {  
//		            public void onClick(DialogInterface dialogo1, int id) {  
//		                aceptar();  
//		            }  
//		        });  
//		        dialogo1.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {  
//		            public void onClick(DialogInterface dialogo1, int id) {  
//		                cancelar();
//		            }  
//		        });            
//		        dialogo1.show();
//			}
//		});
//		
//		//INICIAR BUTTON
//		
//		iniciarButton.setOnClickListener(new OnClickListener(){
//
//			@Override
//			public void onClick(View v) {
//				sv.removeAllViews();
//				sv.addView(iniciar);
//				
//			}
//		});
//		
//		diferirButton.setOnClickListener(new OnClickListener(){
//
//			@Override
//			public void onClick(View v) {
//				sv.removeAllViews();
//				sv.addView(diferir);
//				
//			}
//		});
		
		return v;
    	
    }//Fin de onCreateView
    
    public void aceptar() {
    	new CancelarCirugia().execute(myString[0], myString[1], myString[2], myString[3], myString[4], myString[5]);
        Toast t=Toast.makeText(getActivity(),"Cirugía cancelada", Toast.LENGTH_SHORT);
        t.show();
    }
    
    public void cancelar() {
        finish();
    }
    
    private void finish() {
		// TODO Auto-generated method stub
    	Toast t=Toast.makeText(getActivity(),"Operación cancelada.", Toast.LENGTH_SHORT);
        t.show();
	}
    public boolean cancelarCirugia(String ID){
    	int status = -1;
		
		ArrayList<NameValuePair> datosEnviar= new ArrayList<NameValuePair>();
		datosEnviar.add(new BasicNameValuePair("ID",ID));
				
		JSONArray jdata=post.getserverdata(datosEnviar, URL_connect);
  		  		  		
  		//si lo que obtuvimos no es null
    	if (jdata!=null && jdata.length() > 0){
    		JSONObject json_data; //creamos un objeto JSON
			try {
				json_data = jdata.getJSONObject(0); //leemos el primer segmento para conocer el status de la operacion
				status=json_data.getInt("logstatus");//accedemos al valor
				System.out.println("status cancelacion="+status);
				//qId = json_data.getString("quirofano_id");
				Log.e("getQuirofanoId","status= "+status);//muestro por log que obtuvimos
				return true;
			}
			catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}		            
    		
    	}//Fin de if(comprueba si lo obtenido no es "null")
    	
    	else{	//json obtenido invalido verificar parte WEB.
    		Log.e("JSON getQuirofanoId  ", "ERROR");
	    	return false;
	    }//Fin de else
    }//Fin de cancelarCirugia

    class CancelarCirugia extends AsyncTask< String, String, String> {
		
		//String date, hora, reg, paciente;
    	String st1; //String fecha
    	String st2; //String hora
    	String st3; //String sala
    	String st4; //String paciente
    	String st5; //String diagnostico 
    	String st6; //String ID
		
    	protected void onPreExecute() {
    		//progress = ProgressDialog.show(
    		//getActivity(), null, "Accesando a agenda...");
            super.onPreExecute();
        }
    	
        protected String doInBackground(String... params) {
			st1=params[0]; //obtenemos el string fecha 
			st2=params[1]; //obtenemos el string hora
			st3=params[2]; //obtenemos el string sala
			st4=params[3]; //obtenemos el string paciente
			st5=params[4]; //obtenemos el string diagnostico
			st6=params[5];	
			
			if (cancelarCirugia(st6) == true){
				return "ok";
			}
			else return "error";
    		
		}//Fin de doInBackground
       
        protected void onPostExecute(String resultado) {
        	//progress.dismiss();//ocultamos progess dialog.
            Log.e("onPostExecute=","status="+resultado);
            
        }//Fin de onPostExecute        
	}//Fin de la subclase CancelarCirugia
    
}//Fin de la clase Accion