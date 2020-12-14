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

    public static void main(String[] args) {
        ControllerSeleccion seleccion = new ControllerSeleccion();
        ArrayList<Abeja> abejas =  new ArrayList<>();
        Abeja madre = new Abeja();
        madre.setDireccionFav(Direccion.Sur);
        Busqueda busqueda = new Busqueda();
        busqueda.setAnguloDesviacion(35);
        busqueda.setDistanciaMaxima(100);
        Recorrido recorrido = new Recorrido();
        recorrido.setPuntoInicio(false);
        recorrido.setOrden(Orden.Profundidad);
        busqueda.setRecorrido(recorrido);
        madre.setBusqueda(busqueda);
        Flor flor = new Flor();
        flor.setColor(Color.Azul);
        madre.setFlor(flor);

        Abeja padre = new Abeja();
        padre.setDireccionFav(Direccion.Oeste);
        Busqueda busqueda1 = new Busqueda();
        busqueda1.setDistanciaMaxima(65);
        busqueda1.setAnguloDesviacion(10);
        Recorrido recorrido1 = new Recorrido();
        recorrido1.setOrden(Orden.Random);
        recorrido1.setPuntoInicio(true);
        busqueda1.setRecorrido(recorrido1);
        padre.setBusqueda(busqueda1);
        Flor flor1 = new Flor();
        flor1.setColor(Color.Morado);
        padre.setFlor(flor1);

        abejas.addAll(Arrays.asList(padre, madre));

        ArrayList<Abeja> mutadas = seleccion.mutacion(abejas);
        padre.imprimir();
        madre.imprimir();
        for(Abeja abeja: mutadas){
            abeja.imprimir();
        }

    }
}
