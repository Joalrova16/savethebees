package CONTROLLER;

import MODEL.*;
import com.sun.org.apache.xpath.internal.operations.Or;

import java.util.*;

public class ControllerCodificar {

    public ControllerCodificar(){} //Constructor vacío

    //Métodos de la clase

    //Angulo de desviación de la abeja
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
            ArrayList<Integer> codigoOrden = recorrido.getCodigos().get(recorrido.getOrdenes().indexOf(recorrido.getOrden()));
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

        int index = recorrido.getCodigos().indexOf(orden);
        if(index != -1)
            recorrido.setOrden(recorrido.getOrdenes().get(index));
        else{
            Random random = new Random();
            int indexN = random.nextInt(3);
            recorrido.setOrden(recorrido.getOrdenes().get(indexN));
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

        busqueda.getRecorrido().setCodigos();
        busqueda.getRecorrido().setOrdenes();

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

        ArrayList<Integer> direcciones = abeja.codigoDireccion(abeja.getDireccionFav());
        codigo.addAll(direcciones);

        ArrayList<Integer> florL = abeja.getFlor().codigoColor(abeja.getFlor().getColor());
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

        abeja.setDireccionFav(abeja.decodificarDire(dir));
        abeja.getFlor().setColor(abeja.getFlor().decodificarColor(flo));
        abeja.setBusqueda(decodificarBusqueda(bus));

        return abeja;
    }

    public static void main(String[] args){
        ControllerCodificar codificar = new ControllerCodificar();
        Abeja abeja = new Abeja();
        abeja.setDireccionFav(Direccion.Oeste);
        Flor flor = new Flor();
        flor.setColor(Color.Morado);
        abeja.setFlor(flor);
        Busqueda busqueda = new Busqueda();
        busqueda.setAnguloDesviacion(200);
        busqueda.setDistanciaMaxima(57);
        Recorrido recorrido = new Recorrido();
        recorrido.setOrden(Orden.Profundidad);
        recorrido.setPuntoInicio(false);
        busqueda.setRecorrido(recorrido);
        abeja.setBusqueda(busqueda);

        ArrayList<Integer> abejaC = codificar.codificarAbeja(abeja);
        Abeja abeja1 = codificar.decodificarAbeja(abejaC);

        System.out.println("*************************************");
        abeja.imprimir();
        System.out.println("*************************************");
        abeja1.imprimir();


    }

}
