package com.example.marci.argenteam;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.android.volley.Response;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ImageAdapter extends BaseAdapter {

    private ArrayList<String> images;
    private ArrayList<String> nombres, categorias, provincias, distancias;
    private ArrayList<String> distances;
    LayoutInflater inflater;
    private Context context;

    public ImageAdapter(Context context, ArrayList<String> nombre, ArrayList<String> drawables, ArrayList<String> categoria,
                        ArrayList<String> provincia, ArrayList<String> distancia) {

        this.images = drawables;
        this.nombres=nombre;
        this.categorias=categoria;
        this.provincias=provincia;
        this.distances=distancia;
        this.context=context;
      //          inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return images.size();
    }

    @Override
    public Object getItem(int position) {

        return images.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {

        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.grid_item, viewGroup, false);
        }

        ImageView pinImagen = (ImageView) view.findViewById(R.id.pin);
        final LinearLayout layfondo = (LinearLayout) view.findViewById(R.id.layFondo);
        TextView nombre = (TextView) view.findViewById(R.id.nombreLay);
        TextView descripcion = (TextView) view.findViewById(R.id.descripcioLay);
        TextView distancia = (TextView) view.findViewById(R.id.distanciaTV);
        TextView provincia = (TextView) view.findViewById(R.id.ciudadaLay);

        System.out.println(images.get(position));

        Glide.with(context).load(images.get(position)).asBitmap().centerCrop().into(new SimpleTarget<Bitmap>() {

            public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                Drawable drawable = new BitmapDrawable(resource);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    layfondo.setBackground(drawable);
                }
            }
        });

        String nombreaux;

        if (nombres.get(position).length()>20){
             nombreaux= nombres.get(position).substring(0,20)+"..";
        } else nombreaux=nombres.get(position);


        pinImagen.setImageResource(R.drawable.pin);
        nombre.setText(nombreaux);
        descripcion.setText(categorias.get(position));
        distancia.setText("KM" + distances.get(position));
        provincia.setText(provincias.get(position));

        return view;
    }



}