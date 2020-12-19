package MODEL;
import java.util.ArrayList;
import java.util.List;
public class Grafo {
    public ArrayList<Nodo> nodos=new ArrayList<>();

    public void insertar(Nodo pNodo1){
            nodos.add(pNodo1);
    }
    public void insertarUnion(Nodo nodo1,Nodo nodo2,double peso1,double peso2) {
        Arco arco1= new Arco(nodo1, nodo2,peso1);
        Arco arco2= new Arco(nodo2, nodo1,peso2);
        nodo1.insertarArco(arco1);
        nodo2.insertarArco(arco2);
    }
    public boolean InNodo(Flor pFlor){
        for(Nodo nodo:nodos){
            if(nodo.getFlor()==pFlor){
                return true;
            }
        }
        return false;
    }
    public Nodo BuscarNodo(Flor pFlor){

        Nodo nodoAux=new Nodo();
        for(Nodo nodo:nodos){
            if(nodo.getFlor()==pFlor){
                nodoAux=nodo;
            }
        }
        return nodoAux;
    }
}
