package MODEL;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Flor {

    //Atributos de la clase
    private Color color;
    private ArrayList<Integer> punto = new ArrayList<>();
    private ArrayList<ArrayList<Integer>> codigos = new ArrayList<>();
    private ArrayList<Color> colores = new ArrayList<>();

    //Constructor
    public Flor() {
        this.color = Color.Amarillo;
        this.punto = new ArrayList<>(Arrays.asList(0,0));
        setCodigos();
        setColores();
    }

    //Setters y getters

    public ArrayList<ArrayList<Integer>> getCodigos() {
        return codigos;
    }

    public void setCodigos() {
        ArrayList<ArrayList<Integer>> codigos = new ArrayList<>();
        ArrayList<Integer> negro = new ArrayList<>(Arrays.asList(0, 0, 0));
        ArrayList<Integer> blanco = new ArrayList<>(Arrays.asList(1, 1, 1));
        ArrayList<Integer> rojo = new ArrayList<>(Arrays.asList(1, 0, 0));
        ArrayList<Integer> verde = new ArrayList<>(Arrays.asList(0, 1, 0));
        ArrayList<Integer> azul = new ArrayList<>(Arrays.asList(0, 0, 1));
        ArrayList<Integer> morado = new ArrayList<>(Arrays.asList(1, 0, 1));
        ArrayList<Integer> amarillo = new ArrayList<>(Arrays.asList(1, 1, 0));
        ArrayList<Integer> cian = new ArrayList<>(Arrays.asList(0, 1, 1));
        codigos.add(negro);
        codigos.add(blanco);
        codigos.add(verde);
        codigos.add(azul);
        codigos.add(rojo);
        codigos.add(amarillo);
        codigos.add(morado);
        codigos.add(cian);
        this.codigos = codigos;
    }

    public ArrayList<Color> getColores() {
        return colores;
    }

    public void setColores() {
        this.colores = new ArrayList<>(Arrays.asList(Color.values()));
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

    //Funciones de Flor

    public ArrayList<Integer> codigoColor(Color color){
        /**
         * Codifica el color ingresado
         * Recibe un Color
         * Retorna una lista que corresponde al código binario del color
         * */

        return codigos.get(colores.indexOf(color));
    }

    public Color decodificarColor (ArrayList<Integer> codigo){
        /**
         * Decodifica el codigo ingresado
         * Recibe una lista de bits que corresponden al código binario del color
         * Retorna un Color
         * */

        return colores.get(codigos.indexOf(codigo));
    }

}
