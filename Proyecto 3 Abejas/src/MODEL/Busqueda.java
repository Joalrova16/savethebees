package MODEL;

public class Busqueda {

    private float anguloDesviacion;
    private float distanciaMaxima;
    private Recorrido recorrido;

    public Busqueda() {
    }

    public float getAnguloDesviacion() {
        return anguloDesviacion;
    }

    public void setAnguloDesviacion(float anguloDesviacion) {
        this.anguloDesviacion = anguloDesviacion;
    }

    public float getDistanciaMaxima() {
        return distanciaMaxima;
    }

    public void setDistanciaMaxima(float distanciaMaxima) {
        this.distanciaMaxima = distanciaMaxima;
    }

    public Recorrido getRecorrido() {
        return recorrido;
    }

    public void setRecorrido(Recorrido recorrido) {
        this.recorrido = recorrido;
    }
}
