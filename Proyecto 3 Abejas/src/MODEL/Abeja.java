package MODEL;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Abeja {

    private Flor flor;
    private Direccion direccionFav;
    private Busqueda busqueda;
    private int polen;
    private ArrayList<ArrayList<Integer>> codigos;
    private ArrayList<Direccion> direcciones;

    public ArrayList<ArrayList<Integer>> getCodigos() {
        return codigos;
    }

    public void setCodigos() {
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
        this.direcciones = new ArrayList<>(Arrays.asList(Direccion.values()));
    }

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

    public int getPolen() {
        return polen;
    }

    public void setPolen(int polen) {
        this.polen = polen;
    }

    public ArrayList<Integer> codigoDireccion(Direccion direccion){
        return codigos.get(direcciones.indexOf(direccion));
    }

    public Direccion decodificarDire (ArrayList<Integer> codigo){
        return direcciones.get(direcciones.indexOf(codigo));
    }

    public ArrayList<Integer> codificarAbeja(Abeja abeja) {
        ArrayList<Integer> codigo = new ArrayList<>();

        ArrayList<Integer> direcciones = abeja.codigoDireccion(abeja.getDireccionFav());
        codigo.addAll(direcciones);

        ArrayList<Integer> florL = abeja.flor.codigoColor(abeja.flor.getColor());
        codigo.addAll(florL);

        ArrayList<Integer> busq = abeja.busqueda.codigoBusqueda(abeja.getBusqueda());
        codigo.addAll(busq);

        return codigo;
        //primeros 3 bits, dirección
        //siguientes 3 bits flor
    }
    public Abeja decodificarAbeja(ArrayList<Integer> codigo){
        return this;
    }

}
