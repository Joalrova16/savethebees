package MODEL;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Abeja {

    //Atributos de la clase

    private Flor flor;
    private Direccion direccionFav;
    private Busqueda busqueda;
<<<<<<< HEAD
    private ArrayList<String> polen = new ArrayList<>();
    private float kilometraje=0;
    private float puntaje;
=======
    private int polen;
    private ArrayList<ArrayList<Integer>> codigos;
    private ArrayList<Direccion> direcciones;

    //Constructor
    public Abeja(){
        this.flor = new Flor();
        this.direccionFav = Direccion.Este;
        this.busqueda = new Busqueda();
        this.polen = 0;
        setDirecciones();
        setCodigos();
    }

    //Setters y getters de los atributos

    public ArrayList<ArrayList<Integer>> getCodigos() {
        return codigos;
    }
>>>>>>> Cruces

    public void setCodigos() {
        /**
         * Crea una lista de codigos correspondientes a cada direccion
         * No recibe ni retorna nada.
         * Setea el atributo de la lista de códigos.
         * */

        ArrayList<ArrayList<Integer>> codigos = new ArrayList<>();
        ArrayList<Integer> norte = new ArrayList<>(Arrays.asList(0, 0, 0)); ArrayList<Integer> sur = new ArrayList<>(Arrays.asList(1, 1, 1)); ArrayList<Integer> este = new ArrayList<>(Arrays.asList(1, 0, 0));
        ArrayList<Integer> oeste = new ArrayList<>(Arrays.asList(0, 1, 0)); ArrayList<Integer> noreste = new ArrayList<>(Arrays.asList(0, 0, 1)); ArrayList<Integer> noroeste = new ArrayList<>(Arrays.asList(1, 0, 1));
        ArrayList<Integer> sureste = new ArrayList<>(Arrays.asList(1, 1, 0)); ArrayList<Integer> suroeste = new ArrayList<>(Arrays.asList(0, 1, 1));
        codigos.add(norte); codigos.add(sur); codigos.add(este); codigos.add(noreste); codigos.add(oeste); codigos.add(sureste); codigos.add(noroeste); codigos.add(suroeste);
        this.codigos = codigos;
    }

    public ArrayList<Direccion> getDirecciones() {
        return direcciones;
    }

    public void setDirecciones() {
        /**
         * Crea una lista de direcciones
         * No recibe ni retorna nada.
         * Setea el atributo de la lista de direcciones.
         * */

        this.direcciones = new ArrayList<>(Arrays.asList(Direccion.values()));
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

    public int getPolen() {
        return polen;
    }

    public void setPolen(int polen) {
        this.polen = polen;
    }

<<<<<<< HEAD
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
=======
    //Funciones de la abeja
    public ArrayList<Integer> codigoDireccion(Direccion direccion){
        /**
         * Codifica una Direccion recibida
         * Recibe una Direccion
         * Retorna una lista de bits que corresponden al código binario de la direccion
         * */

        return codigos.get(direcciones.indexOf(direccion));
    }

    public Direccion decodificarDire (ArrayList<Integer> codigo){
        /**
         * Decodifica el codigo recibido en una direccion
         * Recibe una lista de bits que corresponden al código binario de una dirección
         * Retorna una Direccion
         * */

        return direcciones.get(codigos.indexOf(codigo));
    }

    public void imprimir(){
        System.out.println("Abeja:");
        System.out.println("->Flor: " + this.flor.getColor() + " " + this.flor.getPunto());
        System.out.println("->Direccion: " + this.direccionFav);
        System.out.println("->Busqueda:\n   *Angulo:" + this.busqueda.getAnguloDesviacion() + "\n   *Distancia: " + this.busqueda.getDistanciaMaxima() + "\n    *Recorrido:\n       --Orden:" + this.busqueda.getRecorrido().getOrden() + "\n       --Punto inicio: " + this.busqueda.getRecorrido().isPuntoInicio());
    }


>>>>>>> Cruces
}
