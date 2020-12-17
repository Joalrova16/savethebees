package CONTROLLER;
import MODEL.Abeja;
import MODEL.Color;
import MODEL.Flor;
import MODEL.Grafo;
import MODEL.Nodo;
import java.util.ArrayList;
import java.util.List;
public class ControllerNaturaleza {
    private List<Abeja> Panal;
    final float KmXPolen = 10;
    private Grafo grafo;
    private Flor[][] floral= new Flor[100][50];
    ArrayList<Integer> Centro=new ArrayList<>();

    /*

     */
    public void GrafoAbeja(List<Abeja> pPanal){
        for(Abeja abejita :pPanal){
            grafo=new Grafo();
            //insertar panal

            // busqueda de flores
            Flor flor1;
            Flor flor2;
            //for mierda in gafo
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
            if(flor1.getPunto()== Centro){
                peso1=peso1-50;
            }
            if(flor2.getPunto()== Centro){
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
