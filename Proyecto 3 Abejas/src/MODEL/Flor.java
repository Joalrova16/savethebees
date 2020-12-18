package MODEL;

import java.util.ArrayList;
import java.util.Arrays;

public class Flor {

    //Atributos de la clase
    private Color color;
    private ArrayList<Integer> punto;
    private ArrayList<Color> polen;

    //Constructor
    public Flor() {
        this.color = Color.Amarillo;
        this.punto = new ArrayList<>(Arrays.asList(0,0));
        this.polen = new ArrayList<>();
    }

    //Setters y getters

    public ArrayList<Color> getPolen() {
        return polen;
    }

    public void setPolen(ArrayList<Color> polen) {
        this.polen = polen;
    }


    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public ArrayList<Integer> getPunto() {
        return punto;
    }

    public void setPunto(ArrayList<Integer> punto) {
        this.punto = punto;
    }

    public void imprimir(){
        System.out.println(getColor());
        System.out.println(getPunto());
    }

}
