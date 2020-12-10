package MODEL;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Busqueda {

    //Atributos de la clase
    private int anguloDesviacion; //listo
    private int distanciaMaxima;//listo
    private Recorrido recorrido;//listo

    //Contructor
    public Busqueda() {
        this.anguloDesviacion = 0;
        this.distanciaMaxima = 0;
        this.recorrido = new Recorrido();
    }

    //Setters y getters

    public int getAnguloDesviacion() {
        return anguloDesviacion;
    }

    public void setAnguloDesviacion(int anguloDesviacion) {
        this.anguloDesviacion = anguloDesviacion;
    }

    public int getDistanciaMaxima() {
        return distanciaMaxima;
    }

    public void setDistanciaMaxima(int distanciaMaxima) {
        this.distanciaMaxima = distanciaMaxima;
    }

    public Recorrido getRecorrido() {
        return recorrido;
    }

    public void setRecorrido(Recorrido recorrido) {
        this.recorrido = recorrido;
    }

}
