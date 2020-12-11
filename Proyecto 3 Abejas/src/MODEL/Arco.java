package MODEL;

public class Arco {
    private Nodo origen;
    private Nodo destino;
    private double distancia;
    public Arco(){
        this.origen = null;
        this.destino = null;
        this.distancia = 0;
    }
    public Arco(Nodo pOrigen, Nodo pDestino, double pDistancia) {
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

    public Nodo getDestino() {
        return destino;
    }

    public void setDestino(Nodo pDestino) {
        this.destino = pDestino;
    }

    public double getDistancia() {
        return distancia;
    }

    public void setDistance(double distancia) {
        this.distancia = distancia;
    }
}