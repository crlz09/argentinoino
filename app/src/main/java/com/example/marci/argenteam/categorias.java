package com.example.marci.argenteam;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
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

        Boolean cercano = getIntent().getExtras().getBoolean("cercano");

        if(cercano){
             direccion = getIntent().getExtras().getString("direccion");
             lat =  getIntent().getExtras().getDouble("latitud");
             lon =  getIntent().getExtras().getDouble("longitud");
          Toast.makeText(this, "Mi direccion es: "+lat + "\n" + "lon: "+ lon +"\n"
                    +direccion, Toast.LENGTH_LONG).show();
        }

        ArrayList<Integer> imagenes= new ArrayList();
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

        final ArrayList<String> nombres = new ArrayList();
        nombres.add("Comercios / Productos");
        nombres.add("Servicios");
        nombres.add("Ventas por mayor");
        nombres.add("Promociones");
        nombres.add("Destacados");
        nombres.add("Envíos / transporte");
        nombres.add("Comercios / Productos");
        nombres.add("Centros artísticos");
        nombres.add("Centros educativos");
        nombres.add("Centros de salud");
        nombres.add("Busco empleo");
        nombres.add("Ofrezco empleo");

        ArrayList<String> descripcion = new ArrayList();
        descripcion.add("Encontrá los productos que buscás.");
        descripcion.add("Todos los servicios que necesitás.");
        descripcion.add("Ideal para revendedores.");
        descripcion.add("Ofertas y descuentos mensuales");
        descripcion.add("Comercios , productos y servicios destacados del mes.");
        descripcion.add("Remises , fletes , mensajería.");
        descripcion.add("Sin Descripcion");
        descripcion.add("Sin Descripcion");
        descripcion.add("Sin Descripcion");
        descripcion.add("Ayudanos a ayudar!");
        descripcion.add("Sin Descripcion");
        descripcion.add("Empleo ofrecido por nuestros anunciantes.");

        categoriasss = (LinearLayout) findViewById(R.id.raizcat);


        LinearLayout aiuda = (LinearLayout) findViewById(R.id.LLayuda);

        ViewGroup.LayoutParams params1 = aiuda.getLayoutParams();
        params1.height =Math.round(170*(Resources.getSystem().getDisplayMetrics().xdpi/DisplayMetrics.DENSITY_DEFAULT));;
        params1.width = ViewGroup.LayoutParams.MATCH_PARENT;

        aiuda.setLayoutParams(params1);


        for (int i=0; i<nombres.size();i++){
            categoria = (LinearLayout) LayoutInflater.from(getApplicationContext()).inflate(R.layout.categoria,null);


            LinearLayout base= (LinearLayout) categoria.findViewById(R.id.baseCAT);
            ViewGroup.LayoutParams params = base.getLayoutParams();
            params.height =Math.round(170*(Resources.getSystem().getDisplayMetrics().xdpi/DisplayMetrics.DENSITY_DEFAULT));;
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
                    if ((nombre.getText().toString().equals("Comercios / Productos")) ||
                            (nombre.getText().toString().equals("Servicios"))){
                        // subcategorias
                        String nom = nombre.getText().toString();
                    //    Toast.makeText(categorias.this, ""+nom, Toast.LENGTH_SHORT).show();
                        Intent vete = new Intent(getApplicationContext(),subcategoria.class);
                        vete.putExtra("categoria",nom);
                        startActivity(vete);

                    } else {
                        //todos los normales con intent de grid!
                        String nom = nombre.getText().toString();
                       // Toast.makeText(categorias.this, ""+nom, Toast.LENGTH_SHORT).show();
                        Intent vete = new Intent(getApplicationContext(),cercanos.class);
                        vete.putExtra("categoria",nom);
                        startActivity(vete);
                    }
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
