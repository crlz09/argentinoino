package com.example.marci.argenteam;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
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
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class perfil extends AppCompatActivity implements OnMapReadyCallback {

TextView descripcion, precio, telf, redes, pagWeb, correo, horas, provincia, partido, localidad, direccion;
ImageView ivperfil, favorito;
    private GoogleMap mMap;
    private static String TAG = cercanos.class.getSimpleName();
    double latitud;
    double  longitud;
    ArrayList<String> imagenes;
    String idPublicacion,url;
    private RequestQueue requestQueue;
    JsonObjectRequest jsArrayRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        String desc = getIntent().getExtras().getString("datosPub");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        requestQueue = Volley.newRequestQueue(this);

        ivperfil = findViewById(R.id.IVperfil);
        descripcion = findViewById(R.id.tvDescripcion);
        precio = findViewById(R.id.tvPrecio);
        telf = findViewById(R.id.tvNumTelf);
        redes = findViewById(R.id.textoRedes);
        pagWeb = findViewById(R.id.tvPagWeb);
        correo = findViewById(R.id.tvCorreoElec);
        horas = findViewById(R.id.tvHoras);
        provincia = findViewById(R.id.tvProvincia);
        partido = findViewById(R.id.tvPartido);
        localidad = findViewById(R.id.tvLocalidad);
        direccion = findViewById(R.id.tvDireccion);
        favorito = findViewById(R.id.favoritobtn);

        imagenes = new ArrayList<>();


        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(desc);

            idPublicacion = jsonObject.getString("idPublicaciones");
            this.setTitle(jsonObject.getString("nombre"));

            descripcion.setText(jsonObject.getString("info"));
            precio.setText(jsonObject.getString("codigoUsuario"));
            telf.setText(jsonObject.getString("telefono"));
            redes.setText(jsonObject.getString("redesSociales"));
            pagWeb.setText(jsonObject.getString("pagWeb"));
            correo.setText(jsonObject.getString("email"));
            horas.setText(jsonObject.getString("horario"));
            provincia.setText(jsonObject.getString("provincia"));
            partido.setText(jsonObject.getString("partido"));
            localidad.setText(jsonObject.getString("localidad"));
            direccion.setText(jsonObject.getString("direccion"));
            latitud = Double.parseDouble(jsonObject.getString("lat"));
            longitud = Double.parseDouble(jsonObject.getString("lon"));
            Glide.with(getApplicationContext()).load(jsonObject.getString("urlImagen")).centerCrop().into(ivperfil);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        url = "http://argendapp.com.ar/process.php?img&param="+idPublicacion;

        parsearJson();

        //if de favorito true or false para set estrella llena o vacia

        favorito.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //agregar a favoritos o eliminar de favoritos
                Toast.makeText(perfil.this, "Guardado con el ID: " + idPublicacion, Toast.LENGTH_SHORT).show();
            }
        });

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


//        Glide.with(getApplicationContext()).load("").placeholder(idimagen).fitCenter().into(ivperfil);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
/*                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*/
        showDialog();
            }
        });

    }

    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;


        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(latitud, longitud);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Aquí está"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        mMap.animateCamera( CameraUpdateFactory.zoomTo( 14.0f ) );

        //  Toast.makeText(this, ""+MainActivity.ultimadireccion.toString(), Toast.LENGTH_SHORT).show();

    }


    public void showDialog(){
        final Dialog dialog = new Dialog(this);
        dialog.setCancelable(true);

        View view  = this.getLayoutInflater().inflate(R.layout.image_dialog, null);
        dialog.setContentView(view);

        CarouselView carouselView= dialog.findViewById(R.id.carouselView);
        carouselView.setPageCount(imagenes.size());
        carouselView.setImageListener(imageListener);

        dialog.show();
        dialog.setCanceledOnTouchOutside(true);
    }

    ImageListener imageListener = new ImageListener() {
        @Override
        public void setImageForPosition(int position, ImageView imageView) {
//            imageView.setImageResource(imagenes.get(position));
            Glide.with(getApplicationContext()).load(imagenes.get(position)).centerCrop().into(imageView);
        }
    };

    private void parsearJson() {

        jsArrayRequest = new JsonObjectRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            final JSONArray jsonArray = response.getJSONArray("pubs");

                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject employee = jsonArray.getJSONObject(i);

                                String nombre = employee.getString("urlImagen");

                                imagenes.add(nombre);
                            }

                            System.out.println(imagenes);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d(TAG, "Error Respuesta en JSON: " + error.getMessage());

                        String message = null;
                        if (error instanceof NetworkError) {
                            message = "Problemas de internet...Verifica tu conexión!";
                        } else if (error instanceof ServerError) {
                            message = "Problemas con el servidor... Intente más tarde!!";
                        } else if (error instanceof AuthFailureError) {
                            message = "Problemas de internet...Verifica tu conexión!";
                        } else if (error instanceof ParseError) {
                            message = "No encontrado, intente nuevamente.";
                        } else if (error instanceof NoConnectionError) {
                            message = "Problemas de internet...Verifica tu conexión!";
                        } else if (error instanceof TimeoutError) {
                            message = "Problemas de internet...Verifica tu conexión.";
                        }

                        Toast.makeText(perfil.this, ""+message, Toast.LENGTH_SHORT).show();
                    }
                }
        );
        requestQueue.add(jsArrayRequest);

    }

}
