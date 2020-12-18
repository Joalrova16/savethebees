package CONTROLLER;
import MODEL.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class ControllerNaturaleza {

    private List<Abeja> Panal;
    final float KmXPolen = 10;
    private Grafo grafo;
    private Flor[][] floral = new Flor[100][50];
    private ArrayList<Integer> Centro = new ArrayList<>();
    private ArrayList<ArrayList<Abeja>> generacionesAbejas = new ArrayList<>();
    private ArrayList<ArrayList<Flor>> generacionesCampoFlores = new ArrayList<>();

    private ControllerSeleccion controllerSeleccion = ControllerSeleccion.getInstance();
    private ControllerCodificar controllerCodificar = ControllerCodificar.getInstance();


    public ArrayList<ArrayList<Abeja>> getGeneracionesAbejas() {
        return generacionesAbejas;
    }

    public void setGeneracionesAbejas(ArrayList<ArrayList<Abeja>> generacionesAbejas) {
        this.generacionesAbejas = generacionesAbejas;
    }

    private static ControllerNaturaleza controllerNaturaleza;

    public ControllerNaturaleza(){}

    public static ControllerNaturaleza getInstance(){
        if(controllerNaturaleza == null){
            controllerNaturaleza = new ControllerNaturaleza();
        }
        return controllerNaturaleza;
    }

    /*

     */

    public ArrayList<Abeja> poblacionInicialAbejas(int cantAbejas) {

        ArrayList<Abeja> poblacionInicial = new ArrayList<>();
        ArrayList<Direccion> direcciones = controllerCodificar.getDirecciones();
        ArrayList<Color> coloresFlores = controllerCodificar.getColores();
        Random random = new Random();
        ArrayList<Orden> ordenes = controllerCodificar.getOrdenes();
        ArrayList<Boolean> puntoInicio = new ArrayList<>(new ArrayList<>(Arrays.asList(true, false)));

        int direccion;
        int color;
        int angulo;
        int distancia;
        int pi;
        int orden;

        int cantAb = cantAbejas;
        int id = 1;
        while (cantAb != 0) {

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
            nuevaAbeja.setCromosomas(controllerCodificar.codificarAbeja(nuevaAbeja));
            nuevaAbeja.setID(id);

            poblacionInicial.add(nuevaAbeja);

            cantAb--;
            id++;
        }
        return poblacionInicial;
    }

    public ArrayList<Flor> poblacionInicialFlores(int cantFlores) {
        ArrayList<Flor> flores = new ArrayList<>();

        ArrayList<ArrayList<Integer>> puntos = new ArrayList<>();
        ArrayList<Color> colores = controllerCodificar.getColores();
        Random random = new Random();
        int x;
        int y;
        int col;

        int cant = cantFlores;
        while (cant != 0) {
            col = random.nextInt(colores.size());
            x = random.nextInt(50);
            y = random.nextInt(100);
            while(puntos.contains(new ArrayList<>(Arrays.asList(x,y)))){
                x = random.nextInt(50);
                y = random.nextInt(100);
            }
            puntos.add(new ArrayList<>(Arrays.asList(x,y)));
            Flor nuevaFlor = new Flor();
            nuevaFlor.setColor(colores.get(col));
            nuevaFlor.setPunto(new ArrayList<>(Arrays.asList(x, y)));
            nuevaFlor.setId(((y-1)*50+x));
            flores.add(nuevaFlor);
            cant--;
        }
        return flores;
    }

    public void iniciarSimulacion(int cantFlores, int cantAbejas) {
        controllerCodificar.setListas();

        ArrayList<Abeja> poblacionInicialAbejas = poblacionInicialAbejas(cantAbejas);
        ArrayList<Flor> poblacionInicialFlores = poblacionInicialFlores(cantFlores);

        //hacer recorrido
        List<Abeja> panal = new ArrayList<>();
        CrearNuevaGen(panal, panal);
    }

//    public void GrafoAbeja(List<Abeja> pPanal){
//        for(Abeja abejita :pPanal){
//            grafo=new Grafo();
//            //insertar panal
//
//            // busqueda de flores
//            Flor flor1 = null;
//            Flor flor2 = null;
//            //for mierda in gafo
//            double distanciaFlor=(flor2.getPunto().get(0)-flor1.getPunto().get(0))^2+(flor2.getPunto().get(1)-flor1.getPunto().get(1))^2;
//            distanciaFlor=Math.sqrt(distanciaFlor);
//            double peso1=distanciaFlor;
//            double peso2=distanciaFlor;
//            if(abejita.getFlor().getColor()==flor1.getColor()){
//                peso1=peso1-50;
//            }
//            if(abejita.getFlor().getColor()==flor2.getColor()){
//                peso2=peso2-50;
//            }
//            if(flor1.getPunto()== Centro){
//                peso1=peso1-50;
//            }
//            if(flor2.getPunto()== Centro){
//                peso2=peso2-50;
//            }
//            Nodo nodo1;
//            Nodo nodo2;
//            if(grafo.InNodo(flor1)){
//                nodo1=grafo.BuscarNodo(flor1);
//            }
//            else{
//                nodo1=new Nodo();
//                nodo1.setFlor(flor1);
//            }
//            if(grafo.InNodo(flor2)){
//                nodo2=grafo.BuscarNodo(flor2);
//            }
//            else{
//                nodo2=new Nodo();
//                nodo2.setFlor(flor2);
//            }
//            grafo.insertarUnion(nodo1,nodo2,peso1,peso2);
//
//        }
//
//
//    }

    /*
    esta funcion remplaza las abejas viejas del panal con nuevas abejas
     */
    public void CrearNuevaGen(List<Abeja> pPanal, List<Abeja> pNuevaGen) {
        /*
        aqui se tiene que hacer primero la combinacion de los pares de abejas
        y luego se adieren los ancestros
        por ultimo se remplazan las abejas en la lista de panal
        */


    }

    /*
    esta funcion llama cuado las abejas terminen de hacer su recorrido y traigan el polen al panal
     */
    public void adaptabilidad() {
        float puntajeTotal = 0;
        float puntAnt = 0;
        for (Abeja abeja : Panal) {
            puntajeTotal = puntajeTotal + (KmXPolen * abeja.getPolen().size() / abeja.getkilometraje());
            abeja.setPuntaje(KmXPolen * abeja.getPolen().size() / abeja.getkilometraje());
        }
        ArrayList<Float> numeros = new ArrayList();
        while (numeros.size() <= Panal.size()) {
            numeros.add((float) (Math.random() * puntajeTotal));
        }

        ArrayList<Abeja> NuevaGen = new ArrayList<>();
        for (float numeroAct : numeros) {
            puntAnt = 0;
            for (Abeja abeja : Panal) {
                if (puntAnt < numeroAct && numeroAct < (puntAnt + abeja.getPuntaje())) {
                    NuevaGen.add(abeja);
                    puntAnt = puntAnt + abeja.getPuntaje();
                } else {
                    puntAnt = puntAnt + abeja.getPuntaje();
                }
            }
        }
        CrearNuevaGen(Panal, NuevaGen);
    }
}
