package MODEL;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Abeja {

    private Flor flor;
    private Direccion direccionFav;
    private Busqueda busqueda;
    private ArrayList<String> polen = new ArrayList<>();
    private float kilometraje=0;
    private float puntaje;

    public Abeja(){}

    public Flor getFlor() {
        return flor;
    }

    public void setFlor(Flor flor) {
        this.flor = flor;
    }

    public Direccion getDireccionFav() {
        return direccionFav;
    }

    public void setDireccionFav(Direccion direccionFav) {
        this.direccionFav = direccionFav;
    }

    public Busqueda getBusqueda() {
        return busqueda;
    }

    public void setBusqueda(Busqueda busqueda) {
        this.busqueda = busqueda;
    }

    public ArrayList<String> getPolen() {
        return polen;
    }

    public void setPolen(ArrayList<String> polen) {
        this.polen = polen;
    }

    public float getkilometraje() {
        return kilometraje;
    }

    public void setkilometraje(float kl) {
        this.kilometraje = kl;
    }

    public float getPuntaje() {
        return puntaje;
    }

    public void setPuntaje(float kl) {
        this.puntaje = kl;
    }
}
