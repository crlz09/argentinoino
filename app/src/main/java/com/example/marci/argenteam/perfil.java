package com.example.marci.argenteam;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;

import java.util.ArrayList;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class perfil extends AppCompatActivity {

TextView descripcion,tel1,tel2,ubi1,ubi2,correo1,correo2;
ImageView ivperfil;

     ArrayList<Integer> imagenes;
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        String desc = getIntent().getExtras().getString("desc");
        String nombre = getIntent().getExtras().getString("nombre");
        String tel = getIntent().getExtras().getString("tel");
        int idimagen = getIntent().getExtras().getInt("idimagen");
        String ciudad = getIntent().getExtras().getString("ciudad");
        String distancia=getIntent().getExtras().getString("distancia");
        String correo=getIntent().getExtras().getString("correo");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        this.setTitle(nombre);
        descripcion = (TextView) findViewById(R.id.tvInfoEmpresa);
        tel1=(TextView) findViewById(R.id.tvNumber1);
        tel2=(TextView) findViewById(R.id.tvNumber2);
        correo1=(TextView) findViewById(R.id.tvNumber3);
        correo2=(TextView) findViewById(R.id.tvNumber4);
        ubi1=(TextView) findViewById(R.id.tvNumber5);
        ubi2=(TextView) findViewById(R.id.tvNumber6);
        ivperfil= (ImageView) findViewById(R.id.IVperfil);
        Drawable drawable =getResources().getDrawable(idimagen);

        descripcion.setText("Lorem ipsum dolor sit amet, consectetur adipisicing elit. Quisquam magni consequatur architecto nobis neque atque, officia laudantium cum dolore tempora eius nam repudiandae blanditiis consectetur");
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/cabo.otf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );

        Glide.with(getApplicationContext()).load("").placeholder(idimagen).centerCrop().into(ivperfil);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
/*                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*/
        showDialog();
            }
        });

       // ivperfil= (ImageView) findViewById(R.id.IVperfil);
       /* Glide.with(getApplicationContext()).load(img).into(ivperfil);*/
    }


    public void showDialog(){
        final Dialog dialog = new Dialog(this);
        dialog.setCancelable(true);

        View view  = this.getLayoutInflater().inflate(R.layout.image_dialog, null);
        dialog.setContentView(view);

        imagenes= new ArrayList<>();
        imagenes.add(R.drawable.resuno);
        imagenes.add(R.drawable.resdos);
        imagenes.add(R.drawable.restres);
        imagenes.add(R.drawable.rescuatro);

        CarouselView carouselView= dialog.findViewById(R.id.carouselView);
        carouselView.setPageCount(imagenes.size());
        carouselView.setImageListener(imageListener);



        dialog.show();
        dialog.setCanceledOnTouchOutside(true);
    }

    ImageListener imageListener = new ImageListener() {
        @Override
        public void setImageForPosition(int position, ImageView imageView) {
            imageView.setImageResource(imagenes.get(position));
        }
    };
}
