package CONTROLLER;
import MODEL.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class ControllerNaturaleza {
    final float KmXPolen = 10;
    final int repeticiones =10;
    final double PesoColorFav=0.10;
    final double SerFlor=0.05;
    private Grafo grafo;
    ArrayList<Abeja> Panal=new ArrayList<>();
    ArrayList<Flor> Campo=new ArrayList<>();
    ArrayList<Integer> Centro=new ArrayList<>(Arrays.asList(24,49));
    private ArrayList<ArrayList<Abeja>> generacionesAbejas = new ArrayList<>();
    private ArrayList<ArrayList<Flor>> generacionesCampoFlores = new ArrayList<>();
    private ControllerSeleccion controllerSeleccion = ControllerSeleccion.getInstance();
    private ControllerCodificar controllerCodificar = ControllerCodificar.getInstance();
    //private ControllerArchivos controllerArchivos = ControllerArchivos.getInstance();

    public ArrayList<ArrayList<Abeja>> getGeneracionesAbejas() {
        return generacionesAbejas;
    }

    public ArrayList<ArrayList<Flor>> getGeneracionesCampoFlores() {
        return generacionesCampoFlores;
    }

    public void setPanal(ArrayList<Abeja> panal) {
        Panal = panal;
    }

    public void setCampo(ArrayList<Flor> campo) {
        Campo = campo;
    }

    public ArrayList<Abeja> getPanal() {
        return Panal;
    }

    public ArrayList<Flor> getCampo() {
        return Campo;
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

    public void Simulacion (int cantFlores, int cantAbejas) throws IOException {
        controllerCodificar.setListas();
        ArrayList<Abeja> poblacionInicialAbejas = poblacionInicialAbejas(cantAbejas);
        ArrayList<Flor> poblacionInicialFlores = poblacionInicialFlores(cantFlores);
        Panal=poblacionInicialAbejas;
        Campo=poblacionInicialFlores;
        generacionesAbejas.add(poblacionInicialAbejas);
        generacionesCampoFlores.add(poblacionInicialFlores);
        int contRepeticiones=0;
        while (contRepeticiones!=repeticiones){
            GrafoAbeja();
            adaptabilidad();
            ArrayList<Abeja> pPanal=new ArrayList<>();
            for (Abeja pAbeja:Panal){
                pPanal.add(pAbeja);
            }
            generacionesAbejas.add(pPanal);
            generacionesCampoFlores.add(Campo);
            contRepeticiones++;

        }

        for(ArrayList<Flor>florecitas:generacionesCampoFlores){
            for(Flor florecita:florecitas){
                System.out.println(florecita.getPolen().size()+" "+florecita.getPunto());
            }

        }
    }

    public void GrafoAbeja(){

        for(Abeja jj:Panal){
            System.out.println(jj.getID()+" "+jj.getFlor().getColor()+" "+jj.getBusqueda().getDistanciaMaxima()+" ");

        }

        for(Abeja abejita :Panal) {
            grafo = new Grafo();
            Flor panal = new Flor();
            panal.setId(0);
            panal.setPunto(Centro);
            Nodo nPanal = new Nodo();
            nPanal.setFlor(panal);
            grafo.insertar(nPanal);
            for (Flor flora : Campo) {

                double dis = Math.sqrt((flora.getPunto().get(0) - panal.getPunto().get(0)) ^ 2 + (flora.getPunto().get(1) - panal.getPunto().get(1)) ^ 2);
                if (flora.getPunto().get(0) == panal.getPunto().get(0)) {
                    flora.setPunto(new ArrayList<>(Arrays.asList(26, flora.getPunto().get(1))));

                }else{
                    if (abejita.getBusqueda().getDistanciaMaxima() > dis) {
                        if (abejita.getDireccionFav() == Direccion.Este) {
                            double dirderecha = (360 - abejita.getBusqueda().getAnguloDesviacion() % 360);
                            double dirizq = (360 + abejita.getBusqueda().getAnguloDesviacion()) % 360;
                            double pendiente = (flora.getPunto().get(1) - panal.getPunto().get(1) / (flora.getPunto().get(0) - panal.getPunto().get(0)));
                            pendiente = Math.atan(pendiente);
                            if (dirderecha <= pendiente && pendiente <= dirizq) {
                                Nodo nuevo = new Nodo();
                                nuevo.setFlor(flora);
                                grafo.insertar(nuevo);
                            }
                        }
                        if (abejita.getDireccionFav() == Direccion.Noreste) {
                            double dirderecha = (45 - abejita.getBusqueda().getAnguloDesviacion()) % 360;
                            double dirizq = (45 + abejita.getBusqueda().getAnguloDesviacion()) % 360;
                            double pendiente = (flora.getPunto().get(1) - panal.getPunto().get(1) / (flora.getPunto().get(0) - panal.getPunto().get(0)));
                            pendiente = Math.atan(pendiente);
                            if (dirderecha <= pendiente && pendiente <= dirizq) {
                                Nodo nuevo = new Nodo();
                                nuevo.setFlor(flora);
                                grafo.insertar(nuevo);
                            }


                        }
                        if (abejita.getDireccionFav() == Direccion.Norte) {
                            double dirderecha = (90 - abejita.getBusqueda().getAnguloDesviacion()) % 360;
                            double dirizq = (90 + abejita.getBusqueda().getAnguloDesviacion()) % 360;
                            double pendiente = (flora.getPunto().get(1) - panal.getPunto().get(1) / (flora.getPunto().get(0) - panal.getPunto().get(0)));
                            pendiente = Math.atan(pendiente);
                            if (dirderecha <= pendiente && pendiente <= dirizq) {
                                Nodo nuevo = new Nodo();
                                nuevo.setFlor(flora);
                                grafo.insertar(nuevo);
                            }

                        }
                        if (abejita.getDireccionFav() == Direccion.Noroeste) {
                            double dirderecha = (135 - abejita.getBusqueda().getAnguloDesviacion()) % 360;
                            double dirizq = (135 + abejita.getBusqueda().getAnguloDesviacion()) % 360;
                            double pendiente = (flora.getPunto().get(1) - panal.getPunto().get(1) / (flora.getPunto().get(0) - panal.getPunto().get(0)));
                            pendiente = Math.atan(pendiente);
                            if (dirderecha <= pendiente && pendiente <= dirizq) {
                                Nodo nuevo = new Nodo();
                                nuevo.setFlor(flora);
                                grafo.insertar(nuevo);
                            }

                        }
                        if (abejita.getDireccionFav() == Direccion.Oeste) {
                            double dirderecha = (180 - abejita.getBusqueda().getAnguloDesviacion()) % 360;
                            double dirizq = (180 + abejita.getBusqueda().getAnguloDesviacion()) % 360;
                            double pendiente = (flora.getPunto().get(1) - panal.getPunto().get(1) / (flora.getPunto().get(0) - panal.getPunto().get(0)));
                            pendiente = Math.atan(pendiente);
                            if (dirderecha <= pendiente && pendiente <= dirizq) {
                                Nodo nuevo = new Nodo();
                                nuevo.setFlor(flora);
                                grafo.insertar(nuevo);
                            }

                        }
                        if (abejita.getDireccionFav() == Direccion.Suroeste) {
                            double dirderecha = (225 - abejita.getBusqueda().getAnguloDesviacion()) % 360;
                            double dirizq = (225 + abejita.getBusqueda().getAnguloDesviacion()) % 360;
                            double pendiente = (flora.getPunto().get(1) - panal.getPunto().get(1) / (flora.getPunto().get(0) - panal.getPunto().get(0)));
                            pendiente = Math.atan(pendiente);
                            if (dirderecha <= pendiente && pendiente <= dirizq) {
                                Nodo nuevo = new Nodo();
                                nuevo.setFlor(flora);
                                grafo.insertar(nuevo);
                            }

                        }
                        if (abejita.getDireccionFav() == Direccion.Sur) {
                            double dirderecha = (270 - abejita.getBusqueda().getAnguloDesviacion()) % 360;
                            double dirizq = (270 + abejita.getBusqueda().getAnguloDesviacion()) % 360;
                            double pendiente = (flora.getPunto().get(1) - panal.getPunto().get(1) / (flora.getPunto().get(0) - panal.getPunto().get(0)));
                            pendiente = Math.atan(pendiente);
                            if (dirderecha <= pendiente && pendiente <= dirizq) {
                                Nodo nuevo = new Nodo();
                                nuevo.setFlor(flora);
                                grafo.insertar(nuevo);
                            }

                        }
                        if (abejita.getDireccionFav() == Direccion.Sureste) {
                            double dirderecha = (315 - abejita.getBusqueda().getAnguloDesviacion()) % 360;
                            double dirizq = (315 + abejita.getBusqueda().getAnguloDesviacion()) % 360;
                            double pendiente = (flora.getPunto().get(1) - panal.getPunto().get(1) / (flora.getPunto().get(0) - panal.getPunto().get(0)));
                            pendiente = Math.atan(pendiente);
                            if (dirderecha <= pendiente && pendiente <= dirizq) {
                                Nodo nuevo = new Nodo();
                                nuevo.setFlor(flora);
                                grafo.insertar(nuevo);
                            }
                        }
                    }
                }


            }
            System.out.println(grafo.nodos);
            if (grafo.nodos.size() != 1) {

            //for mierda in gafo
                for (Nodo nodo : grafo.nodos) {
                    int contador = 0;
                    Nodo nodoAux = grafo.nodos.get(contador);
                    while (nodoAux != nodo) {
                        contador = contador + 1;
                        nodoAux = grafo.nodos.get(contador);
                    }
                    while ((grafo.nodos.size()) != contador + 1) {
                        contador = contador + 1;
                        nodoAux = grafo.nodos.get(contador);
                        double distanciaFlor = (nodoAux.getFlor().getPunto().get(0) - nodo.getFlor().getPunto().get(0)) ^ 2 + (nodoAux.getFlor().getPunto().get(1) - nodo.getFlor().getPunto().get(1)) ^ 2;
                        distanciaFlor = Math.sqrt(distanciaFlor);
                        double peso1 = distanciaFlor;
                        double peso2 = distanciaFlor;
                        if (abejita.getFlor().getColor() == nodo.getFlor().getColor()) {
                            peso1 = peso1-(peso1 * PesoColorFav);
                        }
                        if (abejita.getFlor().getColor() == nodoAux.getFlor().getColor()) {
                            peso2 = peso2 -(peso2* PesoColorFav);
                        }
                        if (nodo.getFlor().getPunto() != Centro) {
                            peso1 = peso1-(peso1 *SerFlor);
                        }
                        if (nodoAux.getFlor().getPunto() != Centro) {
                            peso2 = peso2 -(peso2* SerFlor);
                        }
                        Nodo nodo1;
                        Nodo nodo2;
                        if (grafo.InNodo(nodo.getFlor().getId())) {
                            nodo1 = grafo.BuscarNodo(nodo.getFlor().getId());
                        } else {
                            nodo1 = new Nodo();
                            nodo1.setFlor(nodo.getFlor());
                        }
                        if (grafo.InNodo(nodoAux.getFlor().getId())) {
                            nodo2 = grafo.BuscarNodo(nodoAux.getFlor().getId());
                        } else {
                            nodo2 = new Nodo();
                            nodo2.setFlor(nodoAux.getFlor());
                        }
                        grafo.insertarUnion(nodo1, nodo2, peso1, peso2);
                        //////////////////////////////////////////revisar grafos

                    }
                    if (abejita.getBusqueda().getRecorrido().getPuntoInicio()) {
                        //panal

                        Nodo nodoFinal = grafo.nodos.get(grafo.nodos.size() - 1);
                        Nodo nodoAct = grafo.nodos.get(0);
                        double peso = nodoAct.getArcos().get(0).getDistancia();

                        int indiceNodo = 0;
                        int indiceArco = 0;
                        int indiceNfinal = 0;
                        int indiceAfinal = 0;
                        while (nodoAct.getFlor().getId() != nodoFinal.getFlor().getId()) {
                            nodoAct.checkeado = true;
                            while (nodoAct.getArcos().size() - 1!= indiceArco ) {
                                if (nodoAct.getArcos().get(indiceArco).getDistancia() < peso && !(grafo.BuscarNodo(nodoAct.getArcos().get(indiceArco).getDestino()).checkeado)) {
                                    indiceAfinal = indiceArco;
                                    peso = nodoAct.getArcos().get(indiceArco).getDistancia();
                                }
                                indiceArco++;
                            }
                            nodoAct=grafo.BuscarNodo(nodoAct.getArcos().get(indiceArco).getDestino());

                            abejita.getPolen().add(nodoAct.getFlor().getColor());
                            abejita.setkilometraje(abejita.getkilometraje()+nodoAct.getArcos().get(0).getDistancia());
                            Panal.get(Panal.indexOf(abejita)).setkilometraje(abejita.getkilometraje()+nodoAct.getArcos().get(0).getDistancia());
                            for (Color polen : abejita.getPolen()) {
                                ArrayList<Color> Colores=Campo.get(Campo.indexOf(nodoAct.getFlor())).getPolen();
                                Colores.add(polen);
                                Campo.get(Campo.indexOf(nodoAct.getFlor())).setPolen(Colores);
                            }

                        }
                    } else {
                        Nodo nodoFinal = grafo.nodos.get(0);
                        Nodo nodoAct = grafo.nodos.get(grafo.nodos.size() - 1);
                        double peso = nodoAct.getArcos().get(0).getDistancia();
                        int indiceNodo = grafo.nodos.size() - 1;
                        int indiceArco = 0;
                        int indiceNfinal = grafo.nodos.size() - 1;
                        int indiceAfinal = 0;
                        while (nodoAct.getFlor().getPunto() != Centro) {
                            abejita.getPolen().add(nodoAct.getFlor().getColor());
                            for (Color polen : abejita.getPolen()) {
                                nodoAct.getFlor().getPolen().add(polen);
                            }

                            grafo.BuscarNodo(nodoAct.getFlor().getId()).checkeado=true;

                            while (nodoAct.getArcos().size()- 1 != indiceArco ) {
                                if (nodoAct.getArcos().get(indiceArco).getDistancia() < peso && !(grafo.BuscarNodo(nodoAct.getArcos().get(indiceArco).getDestino()).checkeado)) {
                                    indiceAfinal = indiceArco;
                                    peso = nodoAct.getArcos().get(indiceArco).getDistancia();
                                }
                                indiceArco++;
                            }
                            indiceArco=0;
                            nodoAct = grafo.BuscarNodo(nodoAct.getArcos().get(indiceArco).getDestino());
                            abejita.setkilometraje(abejita.getkilometraje()+nodoAct.getArcos().get(0).getDistancia());
                            for (Color polen : abejita.getPolen()) {
                                if(nodoAct.getFlor().getPunto()!=Centro){
                                    ArrayList<Color> Colores=Campo.get(Campo.indexOf(nodoAct.getFlor())).getPolen();
                                    Colores.add(polen);
                                    Campo.get(Campo.indexOf(nodoAct.getFlor())).setPolen(Colores);
                                }
                            }

                        }

                    }

                }
            }
            System.out.println("kilometraje"+abejita.getkilometraje());
        }



    }

    /*
    esta funcion remplaza las abejas viejas del panal con nuevas abejas
     */
    public void CrearNuevaGen(List<Abeja> pPanal, List<Abeja> pNuevaGen ) {

        /*
        aqui se tiene que hacer primero la combinacion de los pares de abejas
        y luego se adieren los ancestros
        por ultimo se remplazan las abejas en la lista de panal
        */
        int cont=0;
        ArrayList<Abeja> hermanas;
        pPanal.clear();

        while(cont<pNuevaGen.size()-1){
            hermanas=controllerSeleccion.cruce(pNuevaGen.get(cont),pNuevaGen.get(cont+1));
            hermanas.get(0).setID(cont);
            hermanas.get(1).setID(cont+1);
            pPanal.add(hermanas.get(0));
            pPanal.add(hermanas.get(1));
            cont=cont+2;
        }


    }

    /*
    esta funcion llama cuado las abejas terminen de hacer su recorrido y traigan el polen al panal
     */
    public void adaptabilidad() {
        double puntajeTotal = 0;
        double puntAnt = 0;
        System.out.println("\n\n");
        for (Abeja abeja : Panal) {
            //System.out.println(abeja.getkilometraje()+"bandera");
            if(abeja.getkilometraje()>1){
                puntajeTotal = puntajeTotal + (KmXPolen * abeja.getPolen().size() / abeja.getkilometraje());
                abeja.setPuntaje(KmXPolen * abeja.getPolen().size() / abeja.getkilometraje());
            }
            else{
                abeja.setPuntaje(0);
            }

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
        Campo= controllerSeleccion.seleccionarNuevasFlores(Campo);
    }
}
