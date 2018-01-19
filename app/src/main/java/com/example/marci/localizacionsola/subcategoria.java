package com.example.marci.localizacionsola;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class subcategoria extends AppCompatActivity {
    LinearLayout categoria,categoriasss;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subcategoria);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Toast.makeText(this, "Selecciona una Subcategoría", Toast.LENGTH_LONG).show();



        String cat =  getIntent().getExtras().getString("categoria");
        this.setTitle(cat);
   /*     String direccion;
        Double lat, lon;

        Boolean cercano = getIntent().getExtras().getBoolean("cercano");

        if(cercano){
            direccion = getIntent().getExtras().getString("direccion");
            lat =  getIntent().getExtras().getDouble("latitud");
            lon =  getIntent().getExtras().getDouble("longitud");
          *//*  Toast.makeText(this, "Mi direccion es: "+lat + "\n" + "lon: "+ lon +"\n"
                    +direccion, Toast.LENGTH_LONG).show();*//*
        }*/

        // definir los arrays de cada categoría

        ArrayList<String> nombres = new ArrayList();
        ArrayList<Integer> imagenes= new ArrayList();
        ArrayList<String> descripcion = new ArrayList();

        if (cat.equals("Comercios / Productos")){

            imagenes.add(R.drawable.imagenuno);
            imagenes.add(R.drawable.imagendos);
            imagenes.add(R.drawable.imagentres);
            imagenes.add(R.drawable.imagenuno);
            imagenes.add(R.drawable.imagendos);
            imagenes.add(R.drawable.imagentres);
            imagenes.add(R.drawable.imagenuno);
            imagenes.add(R.drawable.imagendos);
            imagenes.add(R.drawable.imagentres);
            imagenes.add(R.drawable.imagenuno);
            imagenes.add(R.drawable.imagendos);
            imagenes.add(R.drawable.imagentres);
            imagenes.add(R.drawable.imagenuno);
            imagenes.add(R.drawable.imagendos);
            imagenes.add(R.drawable.imagentres);
            imagenes.add(R.drawable.imagenuno);
            imagenes.add(R.drawable.imagendos);

            nombres.add("Almacenes / mercados / panaderías");
            nombres.add("Kioscos / 24 hs / polirrubros");
            nombres.add("Fiambrerías / verdulerías");
            nombres.add("Granjas / carnicerías");
            nombres.add("Pizzerías / rotiserías");
            nombres.add("Heladerías / confiterías");
            nombres.add("Puestos de diarios / librerías");
            nombres.add("Locutorios / cibers");
            nombres.add("Ferreterías / pinturerías");
            nombres.add("Mueblerías");
            nombres.add("Concesionarías");
            nombres.add("Casas de repuestos");
            nombres.add("Gimnasios");
            nombres.add("Ópticas / farmacias");
            nombres.add("Electrónica / tecnología");
            nombres.add("Ropa y accesorios");
            nombres.add("Emprendimientos / artesanías / feriantes");

            descripcion.add("Tngrese una descripción de la categoría");
            descripcion.add("Tngrese una descripción de la categoría");
            descripcion.add("Tngrese una descripción de la categoría");
            descripcion.add("Tngrese una descripción de la categoría");
            descripcion.add("Tngrese una descripción de la categoría");
            descripcion.add("Tngrese una descripción de la categoría");
            descripcion.add("Tngrese una descripción de la categoría");
            descripcion.add("Tngrese una descripción de la categoría");
            descripcion.add("Tngrese una descripción de la categoría");
            descripcion.add("Tngrese una descripción de la categoría");
            descripcion.add("Tngrese una descripción de la categoría");
            descripcion.add("Tngrese una descripción de la categoría");
            descripcion.add("Tngrese una descripción de la categoría");
            descripcion.add("Tngrese una descripción de la categoría");
            descripcion.add("Tngrese una descripción de la categoría");
            descripcion.add("Tngrese una descripción de la categoría");
            descripcion.add("Tngrese una descripción de la categoría");

        } else {
            imagenes.add(R.drawable.imagenuno);
            imagenes.add(R.drawable.imagendos);
            imagenes.add(R.drawable.imagentres);
            imagenes.add(R.drawable.imagenuno);
            imagenes.add(R.drawable.imagendos);
            imagenes.add(R.drawable.imagentres);
            imagenes.add(R.drawable.imagenuno);
            imagenes.add(R.drawable.imagendos);
            imagenes.add(R.drawable.imagentres);
            imagenes.add(R.drawable.imagenuno);


            nombres.add("Servicios para el hogar");
            nombres.add("Profesionales");
            nombres.add("Cuidado de personas");
            nombres.add("Servicios para mascotas");
            nombres.add("Estética / cuidado personal");
            nombres.add("Mantenimiento / reparación vehicular");
            nombres.add("Técnicos / services");
            nombres.add("Profesores particulares");
            nombres.add("Eventos");
            nombres.add("Otros");

            descripcion.add("Tngrese una descripción de la categoría");
            descripcion.add("Tngrese una descripción de la categoría");
            descripcion.add("Tngrese una descripción de la categoría");
            descripcion.add("Tngrese una descripción de la categoría");
            descripcion.add("Tngrese una descripción de la categoría");
            descripcion.add("Tngrese una descripción de la categoría");
            descripcion.add("Tngrese una descripción de la categoría");
            descripcion.add("Tngrese una descripción de la categoría");
            descripcion.add("Tngrese una descripción de la categoría");
            descripcion.add("Tngrese una descripción de la categoría");
        }

        categoriasss = (LinearLayout) findViewById(R.id.raizcat);

        for (int i=0; i<nombres.size();i++){
            categoria = (LinearLayout) LayoutInflater.from(getApplicationContext()).inflate(R.layout.categoria,null);


            LinearLayout base= (LinearLayout) categoria.findViewById(R.id.baseCAT);
            ViewGroup.LayoutParams params = base.getLayoutParams();
            params.height =Math.round(170*(Resources.getSystem().getDisplayMetrics().xdpi/ DisplayMetrics.DENSITY_DEFAULT));;
            params.width = ViewGroup.LayoutParams.MATCH_PARENT;

            base.setLayoutParams(params);


            final TextView nombre = (TextView) categoria.findViewById(R.id.nombreCAT);
            TextView desc = (TextView) categoria.findViewById(R.id.desCAT);
            base.setBackground(getResources().getDrawable(imagenes.get(i)));
            nombre.setText(nombres.get(i));
            desc.setText(descripcion.get(i));


            final int finalI = i;
            categoria.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                   String nom = nombre.getText().toString();
                    Toast.makeText(subcategoria.this, ""+nom, Toast.LENGTH_SHORT).show();
                    Intent vete = new Intent(getApplicationContext(),cercanos.class);
                    vete.putExtra("categoria",nom);
                    startActivity(vete);
                }
            });
            categoriasss.addView(categoria);
        }




      /*  FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
    }

}
