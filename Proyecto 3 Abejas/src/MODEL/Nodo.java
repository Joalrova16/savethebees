package MODEL;
import java.util.ArrayList;
import java.util.List;

public class Nodo {
    private Flor Flor;
    private List<Arco> Arcos;

    public void setFlor(Flor pFlor) {
        this.Flor = pFlor;
    }
    public Flor  getFlor() {
        return Flor;
    }

    public List<Arco> getArcos() {
        return Arcos;
    }
    public void insertarArco(Arco pArco) {
        if (Arcos == null) {
            Arcos = new ArrayList<>();
        }
        Arcos.add(pArco);
    }

}
