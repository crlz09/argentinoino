package com.example.marci.argenteam;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.media.Image;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

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


        String cat =  getIntent().getExtras().getString("nombre");
        final String clave =  getIntent().getExtras().getString("clave");

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

        final ArrayList<String> nombres = new ArrayList();
        ArrayList<Integer> imagenes= new ArrayList();
        final ArrayList<String> descripcion = new ArrayList();

        if (clave.equals("1")){

            imagenes.add(R.drawable.almacenes);
            imagenes.add(R.drawable.kioskos);
            imagenes.add(R.drawable.fiambrerias);
            imagenes.add(R.drawable.granjas);
            imagenes.add(R.drawable.pizzerias);
            imagenes.add(R.drawable.heladerias);
            imagenes.add(R.drawable.puestos_librerias);
            imagenes.add(R.drawable.locutorios);
            imagenes.add(R.drawable.ferreterias);
            imagenes.add(R.drawable.mueblerias);
            imagenes.add(R.drawable.concesionarias);
            imagenes.add(R.drawable.casas_de_repuestos);
            imagenes.add(R.drawable.gimnasios);
            imagenes.add(R.drawable.opticas_farmacias);
            imagenes.add(R.drawable.electronica);
            imagenes.add(R.drawable.ropa_accesorios);
            imagenes.add(R.drawable.emprendimientos);
            imagenes.add(R.drawable.otros);

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
            nombres.add("Otros");


            descripcion.add("19");
            descripcion.add("20");
            descripcion.add("21");
            descripcion.add("22");
            descripcion.add("23");
            descripcion.add("24");
            descripcion.add("25");
            descripcion.add("26");
            descripcion.add("27");
            descripcion.add("28");
            descripcion.add("29");
            descripcion.add("30");
            descripcion.add("31");
            descripcion.add("32");
            descripcion.add("33");
            descripcion.add("34");
            descripcion.add("35");
            descripcion.add("36");

        } else {
            imagenes.add(R.drawable.servicios_hogar);
            imagenes.add(R.drawable.profesionales);
            imagenes.add(R.drawable.cuidado_personas);
            imagenes.add(R.drawable.servicios_mascotas);
            imagenes.add(R.drawable.estetica);
            imagenes.add(R.drawable.mantenimiento);
            imagenes.add(R.drawable.tecnicos);
            imagenes.add(R.drawable.profesores_particulares);
            imagenes.add(R.drawable.eventos);
            imagenes.add(R.drawable.otros);


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

            descripcion.add("37");
            descripcion.add("38");
            descripcion.add("39");
            descripcion.add("40");
            descripcion.add("41");
            descripcion.add("42");
            descripcion.add("43");
            descripcion.add("44");
            descripcion.add("45");
            descripcion.add("46");

        }

        categoriasss = findViewById(R.id.raizcat);

        for (int i = 0; i < imagenes.size(); i++) {
            categoria = (LinearLayout) LayoutInflater.from(getApplicationContext()).inflate(R.layout.categoria, null);
            ImageView base = categoria.findViewById(R.id.baseCAT);
            ViewGroup.LayoutParams params = base.getLayoutParams();

            int dp = (int) (170 * getApplicationContext().getResources().getDisplayMetrics().density + 0.5f);

            LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(
                    /*width*/ ViewGroup.LayoutParams.MATCH_PARENT,
                   /*height*/ dp
            );
            base.setLayoutParams(param);

            Glide.with(getApplicationContext()).load(imagenes.get(i)).asBitmap().centerCrop().into(base);



            final int finalI = i;
            categoria.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                //   String nom = nombre.getText().toString();
//                    Toast.makeText(subcategoria.this, "hice click aqui", Toast.LENGTH_SHORT).show();
                    Intent vete = new Intent(getApplicationContext(),cercanos.class);
                    vete.putExtra("categoria",nombres.get(finalI));
                    vete.putExtra("descripcion",descripcion.get(finalI));
                    vete.putExtra("origen","categorias");
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
