package CONTROLLER;

import MODEL.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class ControllerSeleccion {

    private int indiceMutacion = 10;

    private ControllerCodificar codificar = ControllerCodificar.getInstance();

    private static ControllerSeleccion controllerSeleccion;

    public ControllerSeleccion(){}

    public static ControllerSeleccion getInstance(){
        if(controllerSeleccion == null){
            controllerSeleccion = new ControllerSeleccion();
        }
        return controllerSeleccion;
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

        abeja1.setPadres(new ArrayList<>(Arrays.asList(abeja1.getID(), abeja2.getID())));
        abeja1.setCromosomas(hijo1);
        abeja2.setPadres(new ArrayList<>(Arrays.asList(abeja1.getID(), abeja2.getID())));
        abeja2.setCromosomas(hijo2);

        abejasNuevas.add(abejaHija1);
        abejasNuevas.add(abejaHija2);

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
        ArrayList<Color> colores = codificar.getColores();

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
        return florNueva;
    }

    public ArrayList<Flor> seleccionarNuevasFlores(ArrayList<Flor> flores){ //probar
        /**
         * Cruza todas las flores del campo de flores
         * Recibe una lista de flores
         * Retorna una lista con flores de diferentes colores
         * */

        ArrayList<Flor> nuevas = new ArrayList<>();
        ArrayList<ArrayList<Integer>> puntos = new ArrayList<>();
        for(Flor flor: flores){
            puntos.add(flor.getPunto());
            if(flor.getPolen().isEmpty()){
                Random random = new Random();
                int x = random.nextInt(50);
                int y = random.nextInt(100);
                while(puntos.contains(new ArrayList<>(Arrays.asList(x,y)))) {
                    x = random.nextInt(50);
                    y = random.nextInt(100);
                }
                flor.setId(((y-1)*50+x));
                flor.setPunto(new ArrayList<>(Arrays.asList(x,y)));
            }
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
            Abeja nueva = codificar.decodificarAbeja(new ArrayList<>(nuevaGenCodigos.subList(subList, subList+23)));
            abejasMutadas.add(nueva);
            subList+=23;
            cont++;
        }
        return abejasMutadas;
    }
}
