package CONTROLLER;

import MODEL.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class ControllerSeleccion {

    private int indiceMutacion = 10;

    private ControllerCodificar codificar = new ControllerCodificar();

    public ControllerSeleccion() {}

    public ControllerCodificar getCodificar() {
        return codificar;
    }

    public void setCodificar(ControllerCodificar codificar) {
        this.codificar = codificar;
    }

    public ArrayList<Abeja> cruce(Abeja abeja1, Abeja abeja2) {
        /**
         * Cruzar dos abejas para generar dos nuevos hijos
         * Recibe 2 abejas
         * Retorna 2 nuevas abejas cruzadas
         * */
        Random cruzarEn = new Random();
        int cruzarAl = cruzarEn.nextInt(22);

        ArrayList<Abeja> abejasNuevas = new ArrayList<>();

        ArrayList<Integer> abejaMadre = codificar.codificarAbeja(abeja1);
        ArrayList<Integer> abejaPadre = codificar.codificarAbeja(abeja2);
        List<Integer> cromosomasCruzarMadre = abejaMadre.subList(0, cruzarAl);
        List<Integer> cromosomasCruzarPadre = abejaPadre.subList(0, cruzarAl);

        ArrayList<Integer> hijo1 = new ArrayList<>();
        hijo1.addAll(cromosomasCruzarPadre);
        hijo1.addAll(abejaMadre.subList(cruzarAl, abejaMadre.size()));

        ArrayList<Integer> hijo2 = new ArrayList<>();
        hijo2.addAll(cromosomasCruzarMadre);
        hijo2.addAll(abejaPadre.subList(cruzarAl, abejaPadre.size()));

        Abeja abejaHija1 = codificar.decodificarAbeja(hijo1);
        Abeja abejaHija2 = codificar.decodificarAbeja(hijo2);

        abeja1.setPadres(new ArrayList<>(Arrays.asList(abeja1, abeja2)));
        abeja1.setCromosomas(hijo1);
        abeja2.setPadres(new ArrayList<>(Arrays.asList(abeja1, abeja2)));
        abeja2.setCromosomas(hijo2);

        abejasNuevas.add(abejaHija1);
        abejasNuevas.add(abejaHija2);

        System.out.println("Cortar a: " + cruzarAl);
        System.out.println("\n\nMADRE: " + abejaMadre);
        System.out.println("\nCromosomas Madre: " + cromosomasCruzarMadre);
        System.out.println("\nPADRE: " + abejaPadre);
        System.out.println("\nCromosomas Padre: " + cromosomasCruzarPadre);
        System.out.println("\nHIJO 1: " + hijo1);
        System.out.println("\nHIJO 2: " + hijo2);

        System.out.println("\n********************************************\n");
        System.out.println("Abeja madre:");
        abeja1.imprimir();
        System.out.println("\nAbeja padre:");
        abeja2.imprimir();
        System.out.println("\n-----------------------------------------------\nAbeja hija 1:");
        abejaHija1.imprimir();
        System.out.println("\nAbeja hija 2:");
        abejaHija2.imprimir();
        return abejasNuevas;
    }

    public Flor cambiarFlor(Flor flor){
        /**
         * Cambia el color de una flor según la cantidad e polén que recibió(colores de otras flores)
         * Recibe una flor
         * Retorna una nueva flor
         * Nota: si la flor no recibió polén se cambia por un color distinto al actual
         * */
        Flor florNueva = new Flor();
        florNueva.setPunto(flor.getPunto());

        Color colorM = flor.getColor();

        ArrayList<Color> coloresFlor = flor.getPolen();
        ArrayList<Color> colores = flor.getColores();

        if(!coloresFlor.isEmpty()){
            Random random = new Random();
            int color = random.nextInt(coloresFlor.size());
            colorM = coloresFlor.get(color);
        }
        else{
            Random random = new Random();
            int nueva = random.nextInt(8);
            while(true){
                if(!colores.get(nueva).equals(colorM)) {
                    colorM = colores.get(nueva);
                    break;
                }
                nueva = random.nextInt(8);
            }
        }
        florNueva.setColor(colorM);
        System.out.println(flor.getPunto());
        System.out.println(flor.getColor());
        System.out.println(florNueva.getColor());
        System.out.println(florNueva.getPunto());
        return florNueva;
    }

    public ArrayList<Flor> seleccionarNuevasFlores(ArrayList<Flor> flores){
        /**
         * Cruza todas las flores del campo de flores
         * Recibe una lista de flores
         * Retorna una lista con flores de diferentes colores
         * */

        ArrayList<Flor> nuevas = new ArrayList<>();

        for(Flor flor: flores){
            nuevas.add(cambiarFlor(flor));
        }

        return nuevas;
    }

    public ArrayList<Abeja> mutacion(ArrayList<Abeja> nuevaGen) {
        ArrayList<Abeja> abejasMutadas = new ArrayList<>();
        ArrayList<Integer> nuevaGenCodigos = new ArrayList<>();

        for(Abeja abeja: nuevaGen){
            nuevaGenCodigos.addAll(codificar.codificarAbeja(abeja));
        }
        Random random = new Random();
        int i = indiceMutacion;
        while (i != 0){

            int cambiar = random.nextInt(nuevaGenCodigos.size());

            if (nuevaGenCodigos.get(cambiar) == 0){
                nuevaGenCodigos.set(cambiar, 1);
            }
            else{
                nuevaGenCodigos.set(cambiar, 0);
            }
            i--;
        }
        int cont = 0;
        int subList = 0;
        while(cont != nuevaGen.size()){
            Abeja nueva = codificar.decodificarAbeja(new ArrayList<>(nuevaGenCodigos.subList(subList, subList+22)));
            abejasMutadas.add(nueva);
            subList+=22;
            cont++;
        }
        return abejasMutadas;
    }

    //Pasar después a naturaleza

    public ArrayList<Abeja> poblacionInicialAbejas(int cantAbejas){

        ArrayList<Abeja> poblacionInicial = new ArrayList<>();
        Abeja abeja = new Abeja();
        ArrayList<Direccion> direcciones = abeja.getDirecciones();
        ArrayList<Color> coloresFlores = abeja.getFlor().getColores();
        Random random = new Random();
        ArrayList<Orden> ordenes = abeja.getBusqueda().getRecorrido().getOrdenes();
        ArrayList<Boolean> puntoInicio = new ArrayList<>(new ArrayList<>(Arrays.asList(true, false)));

        int direccion;
        int color;
        int angulo;
        int distancia;
        int pi;
        int orden;

        int cantAb = cantAbejas;
        while(cantAb!=0){

            direccion = random.nextInt(direcciones.size());
            color = random.nextInt(coloresFlores.size());
            angulo = random.nextInt(180);
            distancia = random.nextInt(55);
            pi = random.nextInt(2);
            orden = random.nextInt(ordenes.size());

            Flor florAb = new Flor();
            florAb.setColor(coloresFlores.get(color));

            Recorrido recorridoAb = new Recorrido();
            recorridoAb.setPuntoInicio(puntoInicio.get(pi));
            recorridoAb.setOrden(ordenes.get(orden));

            Busqueda busquedaAb = new Busqueda();
            busquedaAb.setDistanciaMaxima(distancia);
            busquedaAb.setAnguloDesviacion(angulo);
            busquedaAb.setRecorrido(recorridoAb);

            Abeja nuevaAbeja = new Abeja();
            nuevaAbeja.setDireccionFav(direcciones.get(direccion));
            nuevaAbeja.setFlor(florAb);
            nuevaAbeja.setBusqueda(busquedaAb);
            abeja.setCromosomas(codificar.codificarAbeja(abeja));

            poblacionInicial.add(nuevaAbeja);

            cantAb--;
        }
        return poblacionInicial;
    }

    public ArrayList<Flor> poblacionInicialFlores(int cantFlores){
        ArrayList<Flor> flores = new ArrayList<>();

        Flor flor = new Flor();
        ArrayList<Color> colores = flor.getColores();
        Random random = new Random();
        int x;
        int y;
        int col;

        int cant = cantFlores;
        while (cant!=0){
            col = random.nextInt(colores.size());
            x = random.nextInt(50);
            y = random.nextInt(100);
            Flor nuevaFlor = new Flor();
            nuevaFlor.setColor(colores.get(col));
            nuevaFlor.setPunto(new ArrayList<>(Arrays.asList(x,y)));
            flores.add(nuevaFlor);
            cant--;
        }
        return flores;
    }

    public static void main(String[] args) {
        ControllerSeleccion seleccion = new ControllerSeleccion();
        ArrayList<Flor> flores = seleccion.poblacionInicialFlores(1000);
        for(Flor flor: flores){
            flor.imprimir();
        }
    }
}
