package MODEL;

import com.sun.org.apache.xpath.internal.operations.Or;

import java.awt.image.AreaAveragingScaleFilter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Recorrido {

    private boolean puntoInicio; //true para empezar en el panal y false para empezar desde lejos
    private Orden orden;
    private ArrayList<Orden> ordenes;
    private ArrayList<ArrayList<Integer>> codigos;

    public Recorrido() {}

    public ArrayList<Orden> getOrdenes() {
        return ordenes;
    }

    public void setOrdenes() {
        this.ordenes = new ArrayList<>(Arrays.asList(Orden.values()));
    }

    public ArrayList<ArrayList<Integer>> getCodigos() {
        return codigos;
    }

    public void setCodigos() {
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

    public ArrayList<Integer> codigoRecorrido(Recorrido recorrido){
        ArrayList<Integer> recorridoCod = new ArrayList<>(); //primer bit del punto inicio los otros 2 del orden
        if (recorrido.puntoInicio){
            recorridoCod.add(1);
        }
        else {
            recorridoCod.add(0);
        }
        ArrayList<Integer> codigoOrden = codigos.get(ordenes.indexOf(recorrido.orden));
        recorridoCod.addAll(codigoOrden);
        return recorridoCod;
    }

    public Recorrido decodificarRecorrido(ArrayList<Integer> codigo){
        setOrdenes();
        setCodigos();
        Recorrido recorrido = new Recorrido();
        if(codigo.get(0) == 1){
            recorrido.setPuntoInicio(true);
        }
        ArrayList<Integer> orden = new ArrayList<>();
        orden.add(codigo.get(1)); orden.add(codigo.get(2));

        int index = codigos.indexOf(orden);
        if(index != -1)
            recorrido.setOrden(ordenes.get(index));
        else{
            Random random = new Random();
            int indexN = random.nextInt(3);
            recorrido.setOrden(ordenes.get(indexN));
        }
        return recorrido;
    }

}
