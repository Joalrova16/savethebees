package MODEL;

import java.util.ArrayList;
import java.util.Arrays;

public class Abeja {

    //Atributos de la clase

    private Flor flor;
    private Direccion direccionFav;
    private Busqueda busqueda;
    private float adapatablidad;
    private ArrayList<Integer> padres;
    private ArrayList<Integer> cromosomas;
    private int ID;

    private ArrayList<Color> polen ;
    private float kilometraje=0;
    private float puntaje;



    //Constructor
    public Abeja(){
        this.ID = 0;
        this.flor = new Flor();
        this.direccionFav = Direccion.Este;
        this.busqueda = new Busqueda();
        this.polen = new ArrayList<>();
        this.adapatablidad = 0;
        this.cromosomas = new ArrayList<>();
        this.padres = new ArrayList<>(Arrays.asList(0,0));
    }

    //Setters y getters de los atributos


    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public ArrayList<Integer> getCromosomas() {
        return cromosomas;
    }

    public void setCromosomas(ArrayList<Integer> cromosomas) {
        this.cromosomas = cromosomas;
    }

    public ArrayList<Integer> getPadres() {
        return padres;
    }

    public void setPadres(ArrayList<Integer> padres) {
        this.padres = padres;
    }

    public float getAdapatablidad() {
        return adapatablidad;
    }

    public void setAdapatablidad(float adapatablidad) {
        this.adapatablidad = adapatablidad;
    }

    public ArrayList<Color> getPolen() {
        return polen;
    }

    public void setPolen(ArrayList<Color> polen) {
        this.polen = polen;
    }

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

    public void imprimir(){
        System.out.println("Abeja:");
        System.out.println("->Flor: " + this.flor.getColor() + " " + this.flor.getPunto());
        System.out.println("->Direccion: " + this.direccionFav);
        System.out.println("->Busqueda:\n   *Angulo:" + this.busqueda.getAnguloDesviacion() + "\n   *Distancia: " + this.busqueda.getDistanciaMaxima() + "\n    *Recorrido:\n       --Orden:" + this.busqueda.getRecorrido().getOrden() + "\n       --Punto inicio: " + this.busqueda.getRecorrido().isPuntoInicio());
    }

}
