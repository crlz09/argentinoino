package com.example.marci.argenteam;
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
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
/**
 * Created by marci on 19/11/2017.
 */

public class    GridAdapter extends BaseAdapter {
    public String nom, correo, telefono, ciu ,dist;
    public int drawid;


    private Context context;

    public GridAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return elementoscercanos.ITEMS.length;
    }

    @Override
    public elementoscercanos getItem(int position) {
        return elementoscercanos.ITEMS[position];
    }

    @Override
    public long getItemId(int position) {
        return getItem(position).getId();
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {

        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.grid_item, viewGroup, false);
        }
        final LinearLayout layfondo = (LinearLayout) view.findViewById(R.id.layFondo);

        ImageView pinImagen = (ImageView) view.findViewById(R.id.pin);
        TextView nombre = (TextView) view.findViewById(R.id.nombreLay);
        TextView descripcion = (TextView) view.findViewById(R.id.descripcioLay);
        TextView ciudad = (TextView) view.findViewById(R.id.ciudadaLay);
        TextView distancia = (TextView) view.findViewById(R.id.distanciaTV);

        final elementoscercanos item = getItem(position);
        // layfondo.setBackgroundResource(item.getIdDrawable());


        Glide.with(context).load(item.getIdDrawable()).asBitmap().into(new SimpleTarget<Bitmap>() {

            public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                Drawable drawable = new BitmapDrawable(resource);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    layfondo.setBackground(drawable);
                }
            }
        });

        pinImagen.setImageResource(item.getIdUbi());
        nombre.setText(item.getNombre());
        nom=nombre.getText().toString();
        descripcion.setText(item.getProfesion());
        drawid=item.getIdDrawable();

        ciudad.setText(item.getCiudad());
        ciu=ciudad.getText().toString();
        distancia.setText(item.getDistancia());
        dist=distancia.getText().toString();

        return view;
    }

}