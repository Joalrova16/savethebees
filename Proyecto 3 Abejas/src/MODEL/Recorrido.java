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
    }

    //Setters y getters

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
    public boolean getPuntoInicio(){
        return puntoInicio;
    }


}
