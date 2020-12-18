package CONTROLLER;
import MODEL.*;

import java.util.ArrayList;
import java.util.List;
public class ControllerNaturaleza {
    final float KmXPolen = 10;
    private Grafo grafo;
    ArrayList<Abeja> Panal=new ArrayList<>();
    ArrayList<Flor> Campo=new ArrayList<>();
    ArrayList<Integer> Centro=new ArrayList<>();

    /*

     */
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
