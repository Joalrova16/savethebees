package MODEL;

import com.sun.org.apache.xpath.internal.operations.Or;

import java.awt.image.AreaAveragingScaleFilter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Recorrido {

    //Atributos de la clase
    private boolean puntoInicio; //true para empezar en el panal y false para empezar desde lejos
    private Orden orden;
    private ArrayList<Orden> ordenes;
    private ArrayList<ArrayList<Integer>> codigos;

    //Constructor
    public Recorrido() {
        this.puntoInicio = true;
        this.orden = Orden.Profundidad;
        setCodigos();
        setOrdenes();
    }

    //Setters y getters

    public ArrayList<Orden> getOrdenes() {
        return ordenes;
    }

    public void setOrdenes() {
        /**
         * Crea una lista de ordenes
         * No recibe ni retorna nada.
         * Setea el atributo de la lista de ordenes.
         * */

        this.ordenes = new ArrayList<>(Arrays.asList(Orden.values()));
    }

    public ArrayList<ArrayList<Integer>> getCodigos() {
        return codigos;
    }

    public void setCodigos() {
        /**
         * Crea una lista de codigos que corresponden a los ordedenes
         * No recibe ni retorna nada.
         * Setea el atributo de la lista de codigos.
         * */
        ArrayList<ArrayList<Integer>> codigos = new ArrayList<>();
        ArrayList<Integer> anchura = new ArrayList<>(Arrays.asList(0, 0)); ArrayList<Integer> profundidad = new ArrayList<>(Arrays.asList(0, 1)); ArrayList<Integer> random = new ArrayList<>(Arrays.asList(1, 0));
        codigos.add(anchura); codigos.add(profundidad); codigos.add(random);
        this.codigos = codigos;
    }

    public boolean isPuntoInicio() {
        return puntoInicio;
    }

    public void setPuntoInicio(boolean puntoInicio) {
        this.puntoInicio = puntoInicio;
    }

    public Orden getOrden() {
        return orden;
    }

    public void setOrden(Orden orden) {
        this.orden = orden;
    }


}
