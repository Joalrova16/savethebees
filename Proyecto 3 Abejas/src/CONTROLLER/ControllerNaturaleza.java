package CONTROLLER;
import MODEL.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class ControllerNaturaleza {
    final float KmXPolen = 10;
    private Grafo grafo;
    ArrayList<Abeja> Panal=new ArrayList<>();
    ArrayList<Flor> Campo=new ArrayList<>();
    ArrayList<Integer> Centro=new ArrayList<>();

    private ControllerSeleccion controllerSeleccion = ControllerSeleccion.getInstance();
    private ControllerCodificar controllerCodificar = ControllerCodificar.getInstance();


    public ArrayList<ArrayList<Abeja>> getGeneraciones() {
        return generaciones;
    }

    public void setGeneraciones(ArrayList<ArrayList<Abeja>> generaciones) {
        this.generaciones = generaciones;
    }

    public ControllerNaturaleza() {}

    /*

     */


    public ArrayList<Abeja> poblacionInicialAbejas(int cantAbejas){

        ArrayList<Abeja> poblacionInicial = new ArrayList<>();
        Abeja abeja = new Abeja();
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
            abeja.setCromosomas(controllerCodificar.codificarAbeja(abeja));

            poblacionInicial.add(nuevaAbeja);

            cantAb--;
        }
        return poblacionInicial;
    }

    public ArrayList<Flor> poblacionInicialFlores(int cantFlores){
        ArrayList<Flor> flores = new ArrayList<>();

        ArrayList<Color> colores = controllerCodificar.getColores();
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

    public void iniciarSimulacion(int cantFlores, int cantAbejas){
        controllerCodificar.setListas();

        ArrayList<Abeja> poblacionInicialAbejas = poblacionInicialAbejas(cantAbejas);
        ArrayList<Flor> poblacionInicialFlores = poblacionInicialFlores(cantFlores);

        //hacer recorrido
        List<Abeja> panal = new ArrayList<>();
        CrearNuevaGen(panal,panal);
    }

    public void GrafoAbeja(List<Abeja> pPanal){
        for(Abeja abejita :pPanal){
            grafo=new Grafo();
            Flor panal=new Flor();
            panal.setPunto(Centro);
            Nodo nPanal=new Nodo();
            nPanal.setFlor(panal);
            grafo.insertar(nPanal);
            for(Flor flora:Campo){
                double dis=Math.sqrt((flora.getPunto().get(0)-panal.getPunto().get(0))^2+(flora.getPunto().get(1)-panal.getPunto().get(1))^2);
                if(abejita.getBusqueda().getDistanciaMaxima()>dis) {
                    if (abejita.getDireccionFav() == Direccion.Este) {
                        double dirderecha = ( 360 - abejita.getBusqueda().getAnguloDesviacion()%360);
                        double dirizq = (360 + abejita.getBusqueda().getAnguloDesviacion())%360;
                        double pendiente= (flora.getPunto().get(1)-panal.getPunto().get(1)/(flora.getPunto().get(0)-panal.getPunto().get(0)));
                        pendiente=Math.atan(pendiente);
                        if(dirderecha<=pendiente&&pendiente<=dirizq){
                            Nodo nuevo=new Nodo();
                            nuevo.setFlor(flora);
                            grafo.insertar(nuevo);
                        }
                    }
                    if (abejita.getDireccionFav() == Direccion.Noreste) {
                        double dirderecha = (45 - abejita.getBusqueda().getAnguloDesviacion())%360;
                        double dirizq = (45 + abejita.getBusqueda().getAnguloDesviacion())%360;
                        double pendiente= (flora.getPunto().get(1)-panal.getPunto().get(1)/(flora.getPunto().get(0)-panal.getPunto().get(0)));
                        pendiente=Math.atan(pendiente);
                        if(dirderecha<=pendiente&&pendiente<=dirizq){
                            Nodo nuevo=new Nodo();
                            nuevo.setFlor(flora);
                            grafo.insertar(nuevo);
                        }


                    }
                    if (abejita.getDireccionFav() == Direccion.Norte) {
                        double dirderecha =( 90 - abejita.getBusqueda().getAnguloDesviacion())%360;
                        double dirizq = (90 + abejita.getBusqueda().getAnguloDesviacion())%360;
                        double pendiente= (flora.getPunto().get(1)-panal.getPunto().get(1)/(flora.getPunto().get(0)-panal.getPunto().get(0)));
                        pendiente=Math.atan(pendiente);
                        if(dirderecha<=pendiente&&pendiente<=dirizq){
                            Nodo nuevo=new Nodo();
                            nuevo.setFlor(flora);
                            grafo.insertar(nuevo);
                        }

                    }
                    if (abejita.getDireccionFav() == Direccion.Noroeste) {
                        double dirderecha = (135 - abejita.getBusqueda().getAnguloDesviacion())%360;
                        double dirizq = (135 + abejita.getBusqueda().getAnguloDesviacion())%360;
                        double pendiente= (flora.getPunto().get(1)-panal.getPunto().get(1)/(flora.getPunto().get(0)-panal.getPunto().get(0)));
                        pendiente=Math.atan(pendiente);
                        if(dirderecha<=pendiente&&pendiente<=dirizq){
                            Nodo nuevo=new Nodo();
                            nuevo.setFlor(flora);
                            grafo.insertar(nuevo);
                        }

                    }
                    if (abejita.getDireccionFav() == Direccion.Oeste) {
                        double dirderecha =( 180 - abejita.getBusqueda().getAnguloDesviacion())%360;
                        double dirizq =( 180 + abejita.getBusqueda().getAnguloDesviacion())%360;
                        double pendiente= (flora.getPunto().get(1)-panal.getPunto().get(1)/(flora.getPunto().get(0)-panal.getPunto().get(0)));
                        pendiente=Math.atan(pendiente);
                        if(dirderecha<=pendiente&&pendiente<=dirizq){
                            Nodo nuevo=new Nodo();
                            nuevo.setFlor(flora);
                            grafo.insertar(nuevo);
                        }

                    }
                    if (abejita.getDireccionFav() == Direccion.Suroeste) {
                        double dirderecha = (225 - abejita.getBusqueda().getAnguloDesviacion())%360;
                        double dirizq =( 225 + abejita.getBusqueda().getAnguloDesviacion())%360;
                        double pendiente= (flora.getPunto().get(1)-panal.getPunto().get(1)/(flora.getPunto().get(0)-panal.getPunto().get(0)));
                        pendiente=Math.atan(pendiente);
                        if(dirderecha<=pendiente&&pendiente<=dirizq){
                            Nodo nuevo=new Nodo();
                            nuevo.setFlor(flora);
                            grafo.insertar(nuevo);
                        }

                    }
                    if (abejita.getDireccionFav() == Direccion.Sur) {
                        double dirderecha =( 270 - abejita.getBusqueda().getAnguloDesviacion())%360;
                        double dirizq =( 270 + abejita.getBusqueda().getAnguloDesviacion())%360;
                        double pendiente= (flora.getPunto().get(1)-panal.getPunto().get(1)/(flora.getPunto().get(0)-panal.getPunto().get(0)));
                        pendiente=Math.atan(pendiente);
                        if(dirderecha<=pendiente&&pendiente<=dirizq){
                            Nodo nuevo=new Nodo();
                            nuevo.setFlor(flora);
                            grafo.insertar(nuevo);
                        }

                    }
                    if (abejita.getDireccionFav() == Direccion.Sureste) {
                        double dirderecha =( 315 - abejita.getBusqueda().getAnguloDesviacion())%360;
                        double dirizq =( 315 + abejita.getBusqueda().getAnguloDesviacion())%360;
                        double pendiente= (flora.getPunto().get(1)-panal.getPunto().get(1)/(flora.getPunto().get(0)-panal.getPunto().get(0)));
                        pendiente=Math.atan(pendiente);
                        if(dirderecha<=pendiente&&pendiente<=dirizq){
                            Nodo nuevo=new Nodo();
                            nuevo.setFlor(flora);
                            grafo.insertar(nuevo);
                        }
                    }
                }

            }

            //for mierda in gafo
            for(Nodo nodo:grafo){
                int contador=0;
                Nodo nodoAux=grafo.nodos.get(contador);
                while(nodoAux!=nodo){
                    contador=contador+1;
                    nodoAux=grafo.nodos.get(contador);
                }
                while((grafo.nodos.size())!=contador+1){
                    contador=contador+1;
                    nodoAux=grafo.nodos.get(contador);
                    double distanciaFlor=(nodoAux.getFlor().getPunto().get(0)-nodo.getFlor().getPunto().get(0))^2+(nodoAux.getFlor().getPunto().get(1)-nodo.getFlor().getPunto().get(1))^2;

                }

            }
            double distanciaFlor=(flor2.getPunto().get(0)-flor1.getPunto().get(0))^2+(flor2.getPunto().get(1)-flor1.getPunto().get(1))^2;
            distanciaFlor=Math.sqrt(distanciaFlor);
            double peso1=distanciaFlor;
            double peso2=distanciaFlor;
            if(abejita.getFlor().getColor()==flor1.getColor()){
                peso1=peso1-50;
            }
            if(abejita.getFlor().getColor()==flor2.getColor()){
                peso2=peso2-50;
            }
            if(flor1.getPunto()!= Centro){
                peso1=peso1-50;
            }
            if(flor2.getPunto()!= Centro){
                peso2=peso2-50;
            }
            Nodo nodo1;
            Nodo nodo2;
            if(grafo.InNodo(flor1)){
                nodo1=grafo.BuscarNodo(flor1);
            }
            else{
                nodo1=new Nodo();
                nodo1.setFlor(flor1);
            }
            if(grafo.InNodo(flor2)){
                nodo2=grafo.BuscarNodo(flor2);
            }
            else{
                nodo2=new Nodo();
                nodo2.setFlor(flor2);
            }
            grafo.insertarUnion(nodo1,nodo2,peso1,peso2);

        }


    }

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
