package CONTROLLER;
import MODEL.Abeja;
import java.util.ArrayList;
import java.util.List;
public class ControllerNaturaleza {

    private List<Abeja> Panal;
    final float KmXPolen = 10;

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
