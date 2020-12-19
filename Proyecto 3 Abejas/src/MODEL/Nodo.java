package MODEL;
import java.util.ArrayList;
import java.util.List;

public class Nodo {
       private Flor Flor;
    private ArrayList<Arco> Arcos=new ArrayList<>();
    public boolean checkeado=false;


    public Nodo() {
        Flor = new Flor();
        Arcos = null;
        this.checkeado = false;
    }

    public void setFlor(Flor pFlor) {
        this.Flor = pFlor;
    }
    public Flor  getFlor() {
        return Flor;
    }

    public ArrayList<Arco> getArcos() {
        return Arcos;
    }
    public void insertarArco(Arco pArco) {
        if (Arcos == null) {
            Arcos = new ArrayList<>();
        }
        Arcos.add(pArco);
    }

}
