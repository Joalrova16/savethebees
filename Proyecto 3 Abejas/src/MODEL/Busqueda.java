package MODEL;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Busqueda {

    private int anguloDesviacion; //listo
    private int distanciaMaxima;//listo
    private Recorrido recorrido;//listo


    public Busqueda() {
    }

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

    public ArrayList<Integer> codigoAngulo(int angulo){
        ArrayList<Integer> anguloBin = new ArrayList<>();

        int nuevoAng = angulo;
        while(nuevoAng != 0){
            anguloBin.add(0, nuevoAng%2);
            nuevoAng = nuevoAng/2;
        }
        int tamanno = 6-anguloBin.size();
        if(anguloBin.size()<6){
            for (int i = 0; i < tamanno; i++){
                anguloBin.add(0,0);
            }
        }
        return anguloBin;
    }

    public Integer decodificarAnguloODis(ArrayList<Integer> codigo){
        int angulo = 0;
        Collections.reverse(codigo);
        int exponente = 0;
        for(int bit: codigo){
            int potencia = (int)(Math.pow(2, exponente));
            exponente ++;
            if(bit == 1) {
                angulo = angulo + potencia;
            }
        }
        System.out.println("Angulo funcion: " + angulo);
        return angulo;
    }

    public ArrayList<Integer> codigoDistMax(int distanciaMaxima){
        ArrayList<Integer> distMax = new ArrayList<>();

        int nuevaDistancia = distanciaMaxima;
        while(nuevaDistancia != 0){
            distMax.add(0, nuevaDistancia%2);
            nuevaDistancia = nuevaDistancia/2;
        }
        int tamanno = 6-distMax.size();
        if(distMax.size()<7){
            for (int i = 0; i< tamanno; i++){
                distMax.add(0, 0);
            }
        }

        return distMax;
    }

    public ArrayList<Integer> codigoBusqueda(Busqueda busqueda){
        ArrayList<Integer> codigoBusqueda = new ArrayList<>();

        codigoBusqueda.addAll( codigoAngulo(busqueda.getAnguloDesviacion()));
        codigoBusqueda.addAll( codigoDistMax(busqueda.getDistanciaMaxima()));
        codigoBusqueda.addAll( getRecorrido().codigoRecorrido(busqueda.getRecorrido()));

        return codigoBusqueda;
        //primeros 6 bits angulo
        //siguientes 7 distancia maxima
        //ultimos 3 recorrido: primero por punto inicio, últimos dos por orden
    }

    public Busqueda decodificarBusqueda(ArrayList<Integer> codigos){

        List<Integer> angulo = codigos.subList(0, 6);
        ArrayList<Integer> anguloA = new ArrayList<>(); anguloA.addAll(angulo);
        List<Integer> distanciaMax = codigos.subList(6, 13);
        ArrayList<Integer> distanciaMaxA = new ArrayList<>(); distanciaMaxA.addAll(distanciaMax);
        List<Integer> recorridol = codigos.subList(13, codigos.size());
        ArrayList<Integer> recorridoA = new ArrayList<>(); recorridoA.addAll(recorridol);
        recorrido = new Recorrido();
        recorrido = recorrido.decodificarRecorrido(recorridoA);
        setRecorrido(recorrido);
        setAnguloDesviacion(decodificarAnguloODis(anguloA));
        setDistanciaMaxima(decodificarAnguloODis(distanciaMaxA));

        return this;
    }

    public static void main(String[] args) {
        Busqueda busqueda = new Busqueda();
        ArrayList<Integer> codigos = new ArrayList<>(Arrays.asList(1,0,0,0,1,1,1,1,1,1,0,0,0,1,0,0));
        busqueda = busqueda.decodificarBusqueda(codigos);
        System.out.println("Angulo: " + busqueda.getAnguloDesviacion());
//        System.out.println("Distancia: " + busqueda.getDistanciaMaxima());
//        Recorrido recorrido = busqueda.getRecorrido();
//        System.out.println("Orden: " + recorrido.getOrden());
//        System.out.println("Punto inicio: " + recorrido.isPuntoInicio());
    }
}
