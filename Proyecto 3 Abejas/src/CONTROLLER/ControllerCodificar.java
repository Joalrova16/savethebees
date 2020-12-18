package CONTROLLER;

import MODEL.*;
import com.sun.org.apache.xpath.internal.operations.Or;

import java.util.*;

public class ControllerCodificar {

    private static ControllerCodificar controllerCodificar;
    private ArrayList<ArrayList<Integer>> codigosColor;
    private ArrayList<Color> colores;
    private ArrayList<ArrayList<Integer>> codigosDire;
    private ArrayList<Direccion> direcciones;
    private ArrayList<Orden> ordenes;
    private ArrayList<ArrayList<Integer>> codigosOrden;

    public ControllerCodificar(){}

    public static ControllerCodificar getInstance(){
        if(controllerCodificar == null){
            controllerCodificar = new ControllerCodificar();
        }
        return controllerCodificar;
    }

    //Métodos de la clase
    public void setCodigosDire() {

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
        this.codigosDire = codigos;
    }

    public ArrayList<ArrayList<Integer>> getCodigosDire() {
        return codigosDire;
    }

    public void setOrdenes() {
        /**
         * Crea una lista de ordenes
         * No recibe ni retorna nada.
         * Setea el atributo de la lista de ordenes.
         * */

        this.ordenes = new ArrayList<>(Arrays.asList(Orden.values()));
    }

    public ArrayList<Orden> getOrdenes() {
        return ordenes;
    }

    public void setCodigosOrden() {
        /**
         * Crea una lista de codigos que corresponden a los ordedenes
         * No recibe ni retorna nada.
         * Setea el atributo de la lista de codigos.
         * */
        ArrayList<ArrayList<Integer>> codigos = new ArrayList<>();
        ArrayList<Integer> anchura = new ArrayList<>(Arrays.asList(0, 0)); ArrayList<Integer> profundidad = new ArrayList<>(Arrays.asList(0, 1)); ArrayList<Integer> random = new ArrayList<>(Arrays.asList(1, 0));
        codigos.add(anchura); codigos.add(profundidad); codigos.add(random);
        this.codigosOrden = codigos;
    }

    public ArrayList<ArrayList<Integer>> getCodigosOrden() {
        return codigosOrden;
    }

    public void setDirecciones() {
        /**
         * Crea una lista de direcciones
         * No recibe ni retorna nada.
         * Setea el atributo de la lista de direcciones.
         * */

        this.direcciones = new ArrayList<>(Arrays.asList(Direccion.values()));
    }

    public ArrayList<Direccion> getDirecciones() {
        return direcciones;
    }

    public void setCodigosColor() {
        ArrayList<ArrayList<Integer>> codigos = new ArrayList<>();
        ArrayList<Integer> negro = new ArrayList<>(Arrays.asList(0, 0, 0));
        ArrayList<Integer> blanco = new ArrayList<>(Arrays.asList(1, 1, 1));
        ArrayList<Integer> rojo = new ArrayList<>(Arrays.asList(1, 0, 0));
        ArrayList<Integer> verde = new ArrayList<>(Arrays.asList(0, 1, 0));
        ArrayList<Integer> azul = new ArrayList<>(Arrays.asList(0, 0, 1));
        ArrayList<Integer> morado = new ArrayList<>(Arrays.asList(1, 0, 1));
        ArrayList<Integer> amarillo = new ArrayList<>(Arrays.asList(1, 1, 0));
        ArrayList<Integer> cian = new ArrayList<>(Arrays.asList(0, 1, 1));
        codigos.add(negro);
        codigos.add(blanco);
        codigos.add(verde);
        codigos.add(azul);
        codigos.add(rojo);
        codigos.add(amarillo);
        codigos.add(morado);
        codigos.add(cian);
        this.codigosColor = codigos;
    }

    public ArrayList<ArrayList<Integer>> getCodigosColor() {
        return codigosColor;
    }

    public void setColores() {
        this.colores = new ArrayList<>(Arrays.asList(Color.values()));
    }

    public ArrayList<Color> getColores() {
        return colores;
    }


    public void setListas(){
        setCodigosColor();
        setCodigosDire();
        setCodigosOrden();
        setColores();
        setDirecciones();
        setOrdenes();
    }

    //Codificar

    public ArrayList<Integer> codigoColor(Color color){
        /**
         * Codifica el color ingresado
         * Recibe un Color
         * Retorna una lista que corresponde al código binario del color
         * */

        return codigosColor.get(colores.indexOf(color));
    }

    public Color decodificarColor (ArrayList<Integer> codigo){
        /**
         * Decodifica el codigo ingresado
         * Recibe una lista de bits que corresponden al código binario del color
         * Retorna un Color
         * */

        return colores.get(codigosColor.indexOf(codigo));
    }

    public ArrayList<Integer> codigoDireccion(Direccion direccion){
        /**
         * Codifica una Direccion recibida
         * Recibe una Direccion
         * Retorna una lista de bits que corresponden al código binario de la direccion
         * */

        return codigosDire.get(direcciones.indexOf(direccion));
    }

    public Direccion decodificarDire (ArrayList<Integer> codigo){
        /**
         * Decodifica el codigo recibido en una direccion
         * Recibe una lista de bits que corresponden al código binario de una dirección
         * Retorna una Direccion
         * */

        return direcciones.get(codigosDire.indexOf(codigo));
    }

    public ArrayList<Integer> codigoAngulo(int angulo){
        /**
         * Codifica el ángulo de desviación recibido, no puede ser mayor a 180
         * Recibe un int de ángulo
         * Retorna una lista de bits que corresponden al código binario del ángulo
         * La lista de bits es de 8
         * */

        ArrayList<Integer> anguloBin = new ArrayList<>();

        int nuevoAng = angulo;
        while(nuevoAng != 0){
            anguloBin.add(0, nuevoAng%2);
            nuevoAng = nuevoAng/2;
        }


        int tamanno = 8-anguloBin.size();
        if(anguloBin.size()<8){
            for (int i = 0; i < tamanno; i++){
                anguloBin.add(0,0);
            }
        }
        return anguloBin;
    }

    public ArrayList<Integer> codigoDistMax(int distanciaMaxima){
        /**
         * Codifica la distancia máxima en bits
         * Recibe un int de la distancia máxima de la abeja, no puede ser mayor a 55
         * Retorna una lista de bits que corresponden a la distancia máxima
         * La lista de bits es de 6
         * */

            ArrayList<Integer> distMax = new ArrayList<>();

            int nuevaDistancia = distanciaMaxima;
            while(nuevaDistancia != 0){
                distMax.add(0, nuevaDistancia%2);
                nuevaDistancia = nuevaDistancia/2;
            }
            int tamanno = 6-distMax.size();
            if(distMax.size()<6){
                for (int i = 0; i < tamanno; i++){
                    distMax.add(0, 0);
                }
            }
            return distMax;
    }

    public Integer decodificarAnguloODis(ArrayList<Integer> codigo, boolean distan){
        /**
         * Decodifica la distancia máxima o el ángulo
         * Recibe una lista de bits que corresponden a la distancia o al ángulo
         * Retorna una entero que puede ser la distancia o el ángulo
         * */
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
        if(distan){
            if (angulo > 55) {
                Random random = new Random();
                int dis = random.nextInt(55);
                angulo = dis;
            }
        }
        else{
            if(angulo>180){
                Random random = new Random();
                int ang = random.nextInt(180);
                angulo = ang;
            }
        }
        return angulo;
    }

    public ArrayList<Integer> codigoRecorrido(Recorrido recorrido){
        /**
         *Codifica el recorrido recibido
         * Recibe un Recorrido
         * Retorna una lista de bits que correspoden al recorrido.
         * Aclaración: el primer de la lista es el punto de Inicio, los otros dos son el orden
         * */

            ArrayList<Integer> recorridoCod = new ArrayList<>(); //primer bit del punto inicio los otros 2 del orden
            if (recorrido.isPuntoInicio()){
                recorridoCod.add(1);
            }
            else {
                recorridoCod.add(0);
            }
            ArrayList<Integer> codigoOrden = getCodigosOrden().get(getOrdenes().indexOf(recorrido.getOrden()));
            recorridoCod.addAll(codigoOrden);

            return recorridoCod;
        }

    public Recorrido decodificarRecorrido(ArrayList<Integer> codigo){
        /**
         *Decodifica la lista de bits en una recorrido
         * Recibe un una lista de bits
         * Retorna un Recorrido.
         * */

        Recorrido recorrido = new Recorrido();
        if(codigo.get(0) == 1){
            recorrido.setPuntoInicio(true);
        }
        else{
            recorrido.setPuntoInicio(false);
        }
        ArrayList<Integer> orden = new ArrayList<>();
        orden.add(codigo.get(1)); orden.add(codigo.get(2));

        int index = getCodigosOrden().indexOf(orden);
        if(index != -1)
            recorrido.setOrden(getOrdenes().get(index));
        else{
            Random random = new Random();
            int indexN = random.nextInt(3);
            recorrido.setOrden(getOrdenes().get(indexN));
        }
        return recorrido;
    }

    public ArrayList<Integer> codigoBusqueda(Busqueda busqueda) {
        /**
         * Toma las demás funciones de codificar la busqueda y las une para devolver un solo código de búsqueda
         * Recibe una Busqueda
         * Retorna una lista de bits que corresponden a la búsqueda.
         * Aclaración: los primeros 8 bits son el ángulo, los siguientes 6 la distancia máxuma y los últimos 3 el recorrido
         * */

        ArrayList<Integer> codigoBusqueda = new ArrayList<>();

        codigoBusqueda.addAll(codigoAngulo(busqueda.getAnguloDesviacion()));
        codigoBusqueda.addAll(codigoDistMax(busqueda.getDistanciaMaxima()));
        codigoBusqueda.addAll(codigoRecorrido(busqueda.getRecorrido()));

        return codigoBusqueda;

    }

    public Busqueda decodificarBusqueda(ArrayList<Integer> codigos){
        /**
         * Hace uso de las otras funciones de decodificar busqueda para decodificar toda la búsqueda.
         * Recibe una lista de códigos.
         * Retorna una Busqueda
         * La lista es de 17 bits
         * */

        Busqueda busqueda = new Busqueda();
        List<Integer> angulo = codigos.subList(0, 8);
        ArrayList<Integer> anguloA = new ArrayList<>(); anguloA.addAll(angulo);
        List<Integer> distanciaMax = codigos.subList(8, 14);
        ArrayList<Integer> distanciaMaxA = new ArrayList<>(); distanciaMaxA.addAll(distanciaMax);
        List<Integer> recorridol = codigos.subList(14, codigos.size());
        ArrayList<Integer> recorridoA = new ArrayList<>(); recorridoA.addAll(recorridol);

        Recorrido recorrido = decodificarRecorrido(recorridoA);
        busqueda.setRecorrido(recorrido);
        busqueda.setAnguloDesviacion(decodificarAnguloODis(anguloA, false));
        busqueda.setDistanciaMaxima(decodificarAnguloODis(distanciaMaxA, true));

        return busqueda;
    }

//Métodos finales de codificación y decodificación

    public ArrayList<Integer> codificarAbeja(Abeja abeja) {
        /**
         * Usa las demás funciones y codifica una abeja
         * Recibe una Abeja
         * Retorna una lista de bits que corresponden a una abeja codificada
         * Aclaración: los primeros 3 bits son de la direccion, los siguientes 3 de la flor y los demás(16) son de la búsqueda
         * */

        ArrayList<Integer> codigo = new ArrayList<>();

        ArrayList<Integer> direcciones = codigoDireccion(abeja.getDireccionFav());
        codigo.addAll(direcciones);

        ArrayList<Integer> florL = codigoColor(abeja.getFlor().getColor());
        codigo.addAll(florL);

        ArrayList<Integer> busq = codigoBusqueda(abeja.getBusqueda());
        codigo.addAll(busq);

        return codigo;
    }

    public Abeja decodificarAbeja(ArrayList<Integer> codigo){
        /**
         * Decodifica el array de bits para una Abeja
         * Recibe una lista de bits
         * Retorna una abeja basada en el codigo de bits
         * Aclaración: es un código de 23 bits, los primeros 3 la flor, los otros 3 la dirección, y los últimos 17 parámetros de busqueda
         * */



        Abeja abeja = new Abeja();

        List<Integer> direccion = codigo.subList(0, 3);
        ArrayList<Integer> dir = new ArrayList<>(); dir.addAll(direccion);

        List<Integer> flor = codigo.subList(3, 6);
        ArrayList<Integer> flo = new ArrayList<>(); flo.addAll(flor);

        List<Integer> busqueda = codigo.subList(6, codigo.size());
        ArrayList<Integer> bus = new ArrayList<>(); bus.addAll(busqueda);

        abeja.setDireccionFav(decodificarDire(dir));
        abeja.getFlor().setColor(decodificarColor(flo));
        abeja.setBusqueda(decodificarBusqueda(bus));

        return abeja;
    }

}
