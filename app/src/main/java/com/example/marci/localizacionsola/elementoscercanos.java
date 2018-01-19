package com.example.marci.localizacionsola;

/**
 * Created by marci on 19/11/2017.
 */

public class elementoscercanos {
    private String nombre;
    private int idDrawable;
    private String profesion;
    private String ciudad;
    private String distancia;
    private int idUbi;

    public elementoscercanos(String nombre, int idDrawable, String profesion, String ciudad, String distancia, int idUbi) {
        this.nombre = nombre;
        this.idDrawable = idDrawable;
        this.profesion=profesion;
        this.ciudad=ciudad;
        this.distancia=distancia;
        this.idUbi=idUbi;

    }

    public String getNombre() {
        return nombre;
    }

    public int getIdDrawable() {
        return idDrawable;
    }

    public int getId() {
        return nombre.hashCode();
    }


    public int getIdUbi() {
        return  idUbi;
    }

    public String getProfesion(){
        return profesion;
    }

    public String getCiudad() {
        return ciudad;
    }

    public String getDistancia() {
        return distancia;
    }





    public static elementoscercanos[] ITEMS = {
            new elementoscercanos("Primer Lugar", R.drawable.resuno, "Cafe", "Buenos Aires", "11km", R.drawable.pin),
            new elementoscercanos("Segundo Lugar", R.drawable.resdos,"Restaurant", "Buenos Aires", "14km", R.drawable.pin),
            new elementoscercanos("Tercer Lugar", R.drawable.restres,"Restaurant", "Buenos Aires", "21km", R.drawable.pin),
            new elementoscercanos("Cuarto Lugar", R.drawable.rescuatro,"Cafe", "Buenos Aires", "22km", R.drawable.pin),
            new elementoscercanos("Quinto Lugar", R.drawable.rescinco,"Restaurant", "Buenos Aires", "25km", R.drawable.pin),
            new elementoscercanos("Sexto Lugar", R.drawable.resseis,"Cafe", "Buenos Aires", "28km", R.drawable.pin),
            new elementoscercanos("Septimo Lugar", R.drawable.ressiete,"Cafe", "Buenos Aires", "30km", R.drawable.pin),
    };

    /**
     * Obtiene item basado en su identificador
     *
     * @param id identificador
     * @return Coche
     */
    public static elementoscercanos getItem(int id) {
        for (elementoscercanos item : ITEMS) {
            if (item.getId() == id) {
                return item;
            }
        }
        return null;
    }

}
