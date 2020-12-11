package MODEL;
import java.util.ArrayList;
import java.util.List;
public class Grafo {
    private List<Nodo> nodos;

    public void insertarUnion(Nodo nodo1,Nodo nodo2,double peso1,double peso2) {
        Arco arco1= new Arco(nodo1, nodo2,peso1);
        Arco arco2= new Arco(nodo2, nodo1,peso2);
        nodo1.insertarArco(arco1);
        nodo2.insertarArco(arco2);
    }
}