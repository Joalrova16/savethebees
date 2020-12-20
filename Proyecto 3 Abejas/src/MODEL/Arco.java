package MODEL;

public class Arco {
    private Nodo origen=new Nodo();
    private int destino=0;;
    private double distancia;

    public Arco(Nodo pOrigen, int pDestino, double pDistancia) {
        this.origen = pOrigen;
        this.destino = pDestino;
        this.distancia = pDistancia;
    }


    public Nodo getOrigin() {
        return origen;
    }

    public void setOrigin(Nodo pOrigen) {
        this.origen = pOrigen;
    }

    public int getDestino() {
        return destino;
    }

    public void setDestino(int pDestino) {
        this.destino = pDestino;
    }

    public double getDistancia() {
        return distancia;
    }

    public void setDistance(double distancia) {
        this.distancia = distancia;
    }
}