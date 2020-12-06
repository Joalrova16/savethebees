package MODEL;

public class Recorrido {

    private boolean puntoInicio; //true para empezar en el panal y false para empezar desde lejos
    private Orden orden;

    public Recorrido() {}

    public boolean isPuntoInicio() {
        return puntoInicio;
    }

    public void setPuntoInicio(boolean puntoInicio) {
        this.puntoInicio = puntoInicio;
    }

    public Orden getOrden() {
        return orden;
    }

    public void setOrden(Orden orden) {
        this.orden = orden;
    }
}
