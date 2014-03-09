package com.hu.quirofano;

import com.hu.quirofano.R.layout;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Spinner;
import android.widget.ArrayAdapter;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Toast;

public class ProgramarCirugiaAmbulatoria extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
    
        super.onCreate(savedInstanceState);
        setContentView(R.layout.programar_cirugia_ambulatoria);
        
        Spinner sp = (Spinner) findViewById(R.id.salaOpciones);
        ArrayAdapter adapter = ArrayAdapter.createFromResource(
                this, R.array.salas, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp.setAdapter(adapter);
        
        sp.setOnItemSelectedListener(new OnItemSelectedListener() {
           
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView,
             int position, long id) {
             //Toast.makeText(parentView.getContext(), "Has seleccionado " +
             //parentView.getItemAtPosition(position).toString(), Toast.LENGTH_LONG).show();
            
            }
                                 
            public void onNothingSelected(AdapterView<?> parentView) {
            
            }
        });
        
        /*Ahora para el spinner 2 - Duracion de la cirugia
         ************************************************** */
        Spinner sp1 = (Spinner) findViewById(R.id.duracion);
        ArrayAdapter adapter1 = ArrayAdapter.createFromResource(
                this, R.array.duraciones, android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp1.setAdapter(adapter1);
        
        sp1.setOnItemSelectedListener(new OnItemSelectedListener() {
            
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView,
             int position, long id) {
             //Toast.makeText(parentView.getContext(), "Has seleccionado " +
             //parentView.getItemAtPosition(position).toString(), Toast.LENGTH_LONG).show();
            
            }
                                 
            public void onNothingSelected(AdapterView<?> parentView) {
            
            }
        });
        /*Termina el spinner 2*/
        
        /*Ahora para el spinner 3 - Tipo de programacion
         ************************************************** */
        Spinner sp2 = (Spinner) findViewById(R.id.tipoDeProgramacion);
        ArrayAdapter adapter2 = ArrayAdapter.createFromResource(
                this, R.array.tiposDeProgramacion, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp2.setAdapter(adapter2);
        
        sp2.setOnItemSelectedListener(new OnItemSelectedListener() {
            
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView,
             int position, long id) {
             //Toast.makeText(parentView.getContext(), "Has seleccionado " +
             //parentView.getItemAtPosition(position).toString(), Toast.LENGTH_LONG).show();
            
            }
                                 
            public void onNothingSelected(AdapterView<?> parentView) {
            
            }
        });
        /*Termina el spinner 3*/
        
        /*Ahora para el spinner 4 - Servicio
         ************************************************** */
        Spinner sp3 = (Spinner) findViewById(R.id.servicio);
        ArrayAdapter adapter3 = ArrayAdapter.createFromResource(
                this, R.array.servicios, android.R.layout.simple_spinner_item);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp3.setAdapter(adapter3);
        
        sp3.setOnItemSelectedListener(new OnItemSelectedListener() {
            
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView,
             int position, long id) {
             //Toast.makeText(parentView.getContext(), "Has seleccionado " +
             //parentView.getItemAtPosition(position).toString(), Toast.LENGTH_LONG).show();
            
            }
                                 
            public void onNothingSelected(AdapterView<?> parentView) {
            
            }
        });
        /*Termina el spinner 4*/
        
        /*Ahora para el spinner 5 - En atencion a
         ************************************************** */
        Spinner sp4 = (Spinner) findViewById(R.id.enAtencionA);
        ArrayAdapter adapter4 = ArrayAdapter.createFromResource(
                this, R.array.atencionA, android.R.layout.simple_spinner_item);
        adapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp4.setAdapter(adapter4);
        
        sp4.setOnItemSelectedListener(new OnItemSelectedListener() {
            
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView,
             int position, long id) {
             //Toast.makeText(parentView.getContext(), "Has seleccionado " +
             //parentView.getItemAtPosition(position).toString(), Toast.LENGTH_LONG).show();
            
            }
                                 
            public void onNothingSelected(AdapterView<?> parentView) {
            
            }
        });
        /*Termina el spinner 5*/
        
        /*Ahora para el spinner 6 - Region
         ************************************************** */
        Spinner sp5 = (Spinner) findViewById(R.id.region);
        ArrayAdapter adapter5 = ArrayAdapter.createFromResource(
                this, R.array.listaRegion, android.R.layout.simple_spinner_item);
        adapter5.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp5.setAdapter(adapter5);
        
        sp5.setOnItemSelectedListener(new OnItemSelectedListener() {
            
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView,
             int position, long id) {
             //Toast.makeText(parentView.getContext(), "Has seleccionado " +
             //parentView.getItemAtPosition(position).toString(), Toast.LENGTH_LONG).show();
            
            }
                                 
            public void onNothingSelected(AdapterView<?> parentView) {
            
            }
        });
        /*Termina el spinner 6*/
        
        /*Ahora para el spinner 7 - Servicio 2
         ************************************************** */
        Spinner sp6 = (Spinner) findViewById(R.id.servicio2);
        ArrayAdapter adapter6 = ArrayAdapter.createFromResource(
                this, R.array.servicios2, android.R.layout.simple_spinner_item);
        adapter6.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp6.setAdapter(adapter6);
        
        sp6.setOnItemSelectedListener(new OnItemSelectedListener() {
            
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView,
             int position, long id) {
             //Toast.makeText(parentView.getContext(), "Has seleccionado " +
             //parentView.getItemAtPosition(position).toString(), Toast.LENGTH_LONG).show();
            
            }
                                 
            public void onNothingSelected(AdapterView<?> parentView) {
            
            }
        });
        /*Termina el spinner 6*/
        
    }
}