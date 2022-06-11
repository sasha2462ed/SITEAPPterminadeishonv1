package com.example.siteapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.siteapp.databinding.ActivityDepartamentoTecnicoBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class departamento_tecnico extends General {

    RequestQueue requestQueue;
    private ActivityDepartamentoTecnicoBinding v3;
    DatePickerDialog.OnDateSetListener setListener;
    private ListView lv1;
    Context ct;
    String trampa;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_departamento_tecnico);
        v3 = ActivityDepartamentoTecnicoBinding.inflate(getLayoutInflater());
        View view = v3.getRoot();
        setContentView(view);
        trampa = getIntent().getStringExtra("trampa");
        ct=view.getContext();



        String URL="http://192.168.101.5/conexion_php/listtec.php";

        StringRequest stringRequest = new StringRequest(Request.Method.GET,URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONArray nodos = new JSONArray(response);

                    JSONArray id = new JSONArray(nodos.get(0).toString());
                    JSONArray name = new JSONArray(nodos.get(1).toString());

                    lv1 = (ListView) findViewById(R.id.lv1);
                    String[] problemas = new String[name.length()];

                    JSONObject nods = new JSONObject();


                    for (int i = 0; i < name.length(); i++) {
                        problemas[i] = name.get(i).toString();
                        nods.put(name.get(i).toString(), id.get(i).toString());
                    }


                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(),R.layout.listview_items, problemas);
                    lv1.setAdapter(adapter);



                    lv1.setOnItemClickListener (new AdapterView.OnItemClickListener() {

                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            //valor=lv2.getItemAtPosition(position).toString();
                            final String[] valor = {""};
                            valor[0] = problemas[position];
                            Toast.makeText(getApplicationContext(),"Su incidencia es " + valor[0], Toast.LENGTH_SHORT).show();


                            Log.i("resultap",valor[0]);

                            //////////*////////////////

                            try{

                                v3.btn4tec.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {

                                        String URL= "http://192.168.101.5/conexion_php/insertar_incidencias.php";

                                        StringRequest stringRequest = new StringRequest(Request.Method.POST,URL, new Response.Listener<String>() {
                                            @Override
                                            public void onResponse(String response) {
                                                Log.i("oliver",response);
                                                if(response.equals("1")){
                                                    Toast.makeText(getBaseContext(), "OPERACION EXITOSA", Toast.LENGTH_SHORT).show();
                                                    v3.txp4tec.getText().clear();

                                                }else{
                                                    Toast.makeText(getBaseContext(), "OPERACION FALLIDA ", Toast.LENGTH_SHORT).show();


                                                }


                                            }

                                        }, new Response.ErrorListener(){
                                            @Override
                                            public void onErrorResponse(VolleyError error) {
                                                Toast.makeText(getApplicationContext(),error.toString(), Toast.LENGTH_SHORT).show();
                                            }

                                        }){
                                            @Override
                                            protected Map<String, String> getParams () throws AuthFailureError {
                                                Map<String,String> parametros = new HashMap<String, String>();
                                                //parametros.put("id".toString().toString());
                                                parametros.put("tipo",valor[0].toString());
                                                Log.i("olivertipo",valor[0]);
                                                parametros.put("departamento", String.valueOf(1));
                                                parametros.put("comentario",v3.txp4tec.getText().toString());
                                                SharedPreferences admin=ct.getSharedPreferences("x",MODE_PRIVATE);
                                                String id=admin.getString("id","");
                                                String ap=admin.getString("ap","");
                                                parametros.put("id_usuarios", id);
                                                Log.i("oliverid",id);
                                                parametros.put("ap", ap);
                                                Log.i("oliverap",ap);
                                                return parametros;
                                            }
                                        };
                                        requestQueue = Volley.newRequestQueue(getApplicationContext());
                                        requestQueue.add(stringRequest);


                                    }
                                });

                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                        }

                    });



                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error) {

            }

        }){
            @Override
            protected Map<String, String> getParams () throws AuthFailureError {
                Map<String,String> parametros = new HashMap<String, String>();
                return parametros;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

        v3.btn6tec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences admin=getBaseContext().getSharedPreferences("x", Context.MODE_PRIVATE);
                String tip_usuario=admin.getString("tip_usuario","");

                if (tip_usuario.equals("C")){

                    Intent intent = new Intent( getApplicationContext(),interfaz_usuario.class);
                    startActivity(intent);

                }

                else if (tip_usuario.equals("D")){

                    Intent intent = new Intent( getApplicationContext(),interfaz_dependiente.class);
                    startActivity(intent);


                }
            }
        });

        v3.btn7tec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finishAffinity();
                System.exit(0);
            }
        });

    }
}

