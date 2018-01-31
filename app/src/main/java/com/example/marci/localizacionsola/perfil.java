package com.example.marci.localizacionsola;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.ImageViewTarget;
import com.bumptech.glide.request.target.SimpleTarget;

public class perfil extends AppCompatActivity {

TextView descripcion,tel1,tel2,ubi1,ubi2,correo1,correo2;
ImageView ivperfil;


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




        Glide.with(getApplicationContext()).load("").placeholder(idimagen).override(600,300).into(ivperfil);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

       // ivperfil= (ImageView) findViewById(R.id.IVperfil);
       /* Glide.with(getApplicationContext()).load(img).into(ivperfil);*/
    }
}
