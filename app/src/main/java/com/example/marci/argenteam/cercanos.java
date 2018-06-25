package com.example.marci.argenteam;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

/**
 * Created by carlosm on 09/01/2018.
 */

public class cercanos extends AppCompatActivity {
    private ProgressDialog pDialog;
    public  Double lon, lat, distance;
    private static String TAG = cercanos.class.getSimpleName();
    GridView gridView;
    String catextra;
    private RequestQueue requestQueue;
    JsonObjectRequest jsArrayRequest;
    ArrayList<String> ArrayNombre, ArrayCategoria, ArrayProvincia, ArrayDistancia, ArrayDrawables, ArrayImagen;
    private ImageAdapter adapter;
    String url = "http://argendapp.com.ar/process.php?";
    String titulo,clave,origen,contenido;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cercanos);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        titulo = getIntent().getExtras().getString("categoria");
        clave = getIntent().getExtras().getString("descripcion");
        origen = getIntent().getExtras().getString("origen");
        contenido = getIntent().getExtras().getString("contenido");

//        lat=-34.7381667;
//        lon=-58.5849039;

        this.setTitle(titulo);

        if (origen.equals("buscar")) {
            url += "buscar&param="+contenido;
            this.setTitle("Busqueda...");
        }else if (origen.equals("categorias")){
            url += "pub&idCategoria="+clave;
        }else if(origen.equals("cercano")){
            lat = Double.valueOf(getIntent().getExtras().getString("latitud"));
            lon = Double.valueOf(getIntent().getExtras().getString("longitud"));
            //Toast.makeText(this, "Latitud: "+ lat + " - Longitud: " + lon, Toast.LENGTH_SHORT).show();
            url += "haversine&lat="+lat+"&lon="+lon;
            this.setTitle("Cercanos...");
        }

        System.out.println(url);

        gridView = (GridView) findViewById(R.id.grid);
        pDialog = new ProgressDialog(cercanos.this);
        pDialog.setMessage("Filtrando cercanos...");
        pDialog.setCancelable(false);

        catextra=getIntent().getExtras().getString("cat");
        requestQueue = Volley.newRequestQueue(this);

        ArrayNombre = new ArrayList<>();
        ArrayCategoria = new ArrayList<>();
        ArrayProvincia = new ArrayList<>();
        ArrayDistancia = new ArrayList<>();
        ArrayDrawables = new ArrayList<>();
        ArrayImagen = new ArrayList<>();

        parsearJson();
        //context,nombre,drawable,categoria,provincia,distancia
        // gridView.setAdapter(new ImageAdapter(getApplicationContext(),));


    }

    private void parsearJson() {
    showpDialog();
        jsArrayRequest = new JsonObjectRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            final JSONArray jsonArray = response.getJSONArray("pubs");

//                            if (jsonArray.getJSONObject(0).getString("respuesta").equals("vacio")){
//                                Toast.makeText(cercanos.this, "se tiro un pedito weyy", Toast.LENGTH_SHORT).show();
//                                finish();
//                            }

//                            if (jsonArray.getJSONObject(0).getString("idPublicaciones").equals("0")){
//                                Toast.makeText(cercanos.this, "dentro", Toast.LENGTH_SHORT).show();
//                                hidepDialog();
//                            }

                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject employee = jsonArray.getJSONObject(i);

                                String nombre = employee.getString("nombre");
                                String categoria = employee.getString("categoriaPub");
                                String provincia = employee.getString("provincia");
                                String imagen = employee.getString("urlImagen");
                                if (origen.equals("cercano")) {
                                    String distance = employee.getString("distance");
                                    ArrayDistancia.add(distance);
                                }else {
                                    ArrayDistancia.add("NA");
                                }

                                ArrayNombre.add(nombre);
                                ArrayCategoria.add(categoria);
                                ArrayProvincia.add(provincia);

                                ArrayDrawables.add(imagen);
                            }

                            adapter = new ImageAdapter(cercanos.this,ArrayNombre,ArrayDrawables,ArrayCategoria,ArrayProvincia,ArrayDistancia);
                            gridView.setAdapter(adapter);
                            gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                    try {
                                        Intent vete = new Intent(getApplicationContext(), perfil.class);
                                        vete.putExtra("datosPub", jsonArray.getJSONObject(i).toString());

                                        startActivity(vete);
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                            });
                            hidepDialog();


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d(TAG, "Error Respuesta en JSON: " + error.getMessage());

                        hidepDialog();

                        String message = null;
                        if (error instanceof NetworkError) {
                            message = "Problemas de internet...Verifica tu conexión!";
                        } else if (error instanceof ServerError) {
                            message = "Problemas con el servidor... Intente más tarde!!";
                        } else if (error instanceof AuthFailureError) {
                            message = "Problemas de internet...Verifica tu conexión!";
                        } else if (error instanceof ParseError) {
                            if (origen.equals("buscar") ){
                                message = "No encontrado, intente nuevamente.";
                            }else {
                                message = "Proximamente";
                            }
                        } else if (error instanceof NoConnectionError) {
                            message = "Problemas de internet...Verifica tu conexión!";
                        } else if (error instanceof TimeoutError) {
                            message = "Problemas de internet...Verifica tu conexión.";
                        }

                        Toast.makeText(cercanos.this, ""+message, Toast.LENGTH_SHORT).show();
                        finish();
                    }
                }
        );
        requestQueue.add(jsArrayRequest);

    }


    //dialog
    private void showpDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hidepDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }



}