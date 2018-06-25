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

public class categorias extends AppCompatActivity {
    LinearLayout categoria,categoriasss;



    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categorias);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Toast.makeText(this, "Selecciona una categoría", Toast.LENGTH_LONG).show();
        this.setTitle("Selecciona una categoría:");
        String direccion;
        Double lat, lon;

//        Boolean cercano = getIntent().getExtras().getBoolean("cercano");
//
//        if (cercano) {
//            direccion = getIntent().getExtras().getString("direccion");
//            lat = getIntent().getExtras().getDouble("latitud");
//            lon = getIntent().getExtras().getDouble("longitud");
//           /* Toast.makeText(this, "Mi direccion es: " + lat + "\n" + "lon: " + lon + "\n"
//                    + direccion, Toast.LENGTH_LONG).show();*/
//        }

        ArrayList<Integer> imagenes = new ArrayList();
        imagenes.add(R.drawable.comercios);
        imagenes.add(R.drawable.servicios);
        imagenes.add(R.drawable.ventas_por_mayor);
        imagenes.add(R.drawable.promociones);
        imagenes.add(R.drawable.destacados);
        imagenes.add(R.drawable.revendedores);
        imagenes.add(R.drawable.envios_transporte);
        imagenes.add(R.drawable.centros_art_dep);
        imagenes.add(R.drawable.centros_educativos);
        imagenes.add(R.drawable.centros_salud);
        imagenes.add(R.drawable.merenderos_comedores);
        imagenes.add(R.drawable.ofrezco_empleo);
        imagenes.add(R.drawable.venta);
        imagenes.add(R.drawable.inscripcion);
        imagenes.add(R.drawable.abono_mensual);
        imagenes.add(R.drawable.tienda_oficial);
        imagenes.add(R.drawable.concursos);
        imagenes.add(R.drawable.preguntas);


        final ArrayList<String> nombres = new ArrayList();
        nombres.add("Comercios / Productos");
        nombres.add("Servicios");
        nombres.add("Ventas por mayor");
        nombres.add("Promociones");
        nombres.add("Destacados");
        nombres.add("Revendedores");
        nombres.add("Envios / Transporte");
        nombres.add("Centros artísticos / deportivos");
        nombres.add("Centros educativos");
        nombres.add("Centros de salud");
        nombres.add("Merenderos / comedores");
        nombres.add("Ofrezco empleo");
        nombres.add("Venta de garaje");
        nombres.add("Inscripción");
        nombres.add("Mensualidad");
        nombres.add("Tienda Argendapp");
        nombres.add("Concursos y sorteos");
        nombres.add("Preguntas Frecuentes");

        final ArrayList<String> clave = new ArrayList();
        clave.add("1");
        clave.add("2");
        clave.add("3");
        clave.add("4");
        clave.add("5");
        clave.add("6");
        clave.add("7");
        clave.add("8");
        clave.add("9");
        clave.add("10");
        clave.add("11");
        clave.add("12");
        clave.add("13");
        clave.add("14");
        clave.add("15");
        clave.add("16");
        clave.add("17");
        clave.add("18");


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
                public void onClick(View view) {
                    if(clave.get(finalI).equals("1") || (clave.get(finalI).equals("2"))){
                        Intent sub= new Intent(getApplicationContext(),subcategoria.class);

                        sub.putExtra("origen", "categorias");
                        sub.putExtra("nombre", nombres.get(finalI));
                        sub.putExtra("clave", clave.get(finalI));
                        startActivity(sub);
//                        Toast.makeText(categorias.this, "P-"+finalI, Toast.LENGTH_SHORT).show();
                    }else {Intent vete = new Intent(getApplicationContext(),cercanos.class);

                        vete.putExtra("origen", "categorias");
                        vete.putExtra("categoria",nombres.get(finalI));
                        vete.putExtra("descripcion",clave.get(finalI));
                        startActivity(vete);
//                        Toast.makeText(categorias.this, "S-"+finalI, Toast.LENGTH_SHORT).show();
                    }//intent de activity categoria.
                }
            });
            categoriasss.addView(categoria);
        }

    }



}
