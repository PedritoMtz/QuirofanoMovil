package com.hu.libreria;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import android.content.Context;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.os.Vibrator;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableRow;
import android.widget.Toast;

import android.content.Context;
import android.os.Vibrator;
import android.util.Log;
import android.widget.Toast;
/*CLASE AUXILIAR PARA EL ENVIO DE PETICIONES A NUESTRO SISTEMA
 * Y MANEJO DE RESPUESTA.*/
public class HttpPostAux extends Activity{
    
	  InputStream is = null;
	  String result = "";
	  ArrayList<String> albuilder = new ArrayList<String>();
	  
	  public JSONArray getserverdata(ArrayList<NameValuePair> parameters, String urlwebserver ){
	
		  //conecta via http y envia un post.
	  httppostconnect(parameters,urlwebserver);
		  
	  if (is!=null){//si obtuvo una respuesta
		  getpostresponse();
		  return getjsonarray();
	  
	  }else{
		  
	      return null;

	  }
	  }//Fin de getserverdata
	  
	   
	  //peticion HTTP
    private void httppostconnect(ArrayList<NameValuePair> parametros, String urlwebserver){
   	
  	//
  	try{
  			System.out.println(urlwebserver);
  	        HttpClient httpclient = new DefaultHttpClient();
  	        HttpPost httppost = new HttpPost(urlwebserver);
  	        //Toast toast1 = Toast.makeText(getApplicationContext(),"urlserver = "+urlwebserver, Toast.LENGTH_SHORT);
	   	    //toast1.show();
  	        httppost.setEntity(new UrlEncodedFormEntity(parametros));
  	        //ejecuto peticion enviando datos por POST
  	        HttpResponse response = httpclient.execute(httppost); 
  	        HttpEntity entity = response.getEntity();
  	        is = entity.getContent();
  	         
  	}catch(Exception e){
  	        Log.e("log_tag0", "Error in http connection "+e.toString());
  	}
  	
  }
  
  public void getpostresponse(){
  
  	//Convierte respuesta a String
  	try{
  			int n = 0;
  	        BufferedReader reader = new BufferedReader(new InputStreamReader(is,"iso-8859-1"),8);
  			//BufferedReader reader = new BufferedReader(new InputStreamReader(is));
  			StringBuilder sb = new StringBuilder();
  	        	        
  	        String line = null;
  	        while ((line = reader.readLine()) != null) {
  	        	System.out.println("line "+n+" = "+line);
  	        	//sb.append(line + "\n");
  	        	sb.append(line);
  	        	n++;
  	        	//albuilder.add(line+"\n");
  	        }
  	        is.close();
  	        //albuilder.add(sb);
  	        result=sb.toString();
  	        Log.e("getpostresponse aux"," result= "+sb.toString());
  	}catch(Exception e){
  	        Log.e("log_tag1", "Error converting result "+e.toString());
  	}
 }
  
  public JSONArray getjsonarray(){
  	//parse json data
  	try{
          JSONArray jArray = new JSONArray(result);
          System.out.println(result);
          
          System.out.println("jArray-HTTP = "+jArray.toString());
          return jArray;
  	}
  	catch(JSONException e){
  	        Log.e("log_tag2", "Error parsing data "+e.toString());
  	        return null;
  	}
		
  }
  	
  	
  }	
  
