package CONTROLLER;

import MODEL.Abeja;
import MODEL.Flor;

import java.io.*;
import java.util.ArrayList;

public class ControllerArchivos {

    public ControllerArchivos() {}

    public void escribirArchivoInformacionGeneraciones(ArrayList<Flor> flores, ArrayList<Abeja> abejas) throws IOException {
        int genAct = escribirArchivoGeneraciones()+1;
        FileWriter fileWriter = new FileWriter("src/ARCHIVOS/campoFlores.txt");
        PrintWriter printWriter = new PrintWriter(fileWriter);
        printWriter.println("Generacion+"+genAct);
        printWriter.println("FLORES");
        printWriter.println("*****************************************");
        for(Flor flor: flores){
            printWriter.println(flor.getColor() + "--" + flor.getPunto().get(0) + "--" + flor.getPunto().get(1));
        }
        printWriter.println("*****************************************");
        for(Abeja abeja: abejas){
            printWriter.println(abeja.getPadres().get(0) + "~" + abeja.getPadres().get(1)
                    + "~" + abeja.getAdapatablidad() + "~" + abeja.getCromosomas() + "~" + abeja.getFlor().getColor() + "~"
                    + abeja.getDireccionFav() + "~" + abeja.getBusqueda().getAnguloDesviacion() + "~" + abeja.getBusqueda().getDistanciaMaxima()
                    + "~" + abeja.getBusqueda().getRecorrido().isPuntoInicio() + "~" + abeja.getBusqueda().getRecorrido().getOrden());
        }

        printWriter.println("*****************************************");

        fileWriter.close();
    }

    public Integer escribirArchivoGeneraciones() throws IOException{
        int lastGen;
        try {
            ArrayList<String> gens = new ArrayList<>();
            File archivo = new File("src/ARCHIVOS/GeneracionesNumero.txt");
            FileReader fileReader = new FileReader(archivo);
            BufferedReader reader = new BufferedReader(fileReader);
            while (reader.readLine()!=null){
                gens.add(reader.readLine());
            }
            lastGen = Integer.parseInt(gens.get(gens.size()-1));
        }
        catch (FileNotFoundException e) {
            FileWriter fileWriter = new FileWriter("src/ARCHIVOS/GeneracionesNumero.txt");
            PrintWriter printWriter = new PrintWriter(fileWriter);
            printWriter.println("GENERACIONES");
            printWriter.println(0);
            fileWriter.close();
            lastGen = 0;
        }
        return lastGen;

    }

    public static void main(String[] args) throws IOException {
        ControllerArchivos archivos = new ControllerArchivos();
        ControllerSeleccion controllerSeleccion = new ControllerSeleccion();
        ArrayList<Flor> flores = controllerSeleccion.poblacionInicialFlores(5);
        ArrayList<Abeja> abejas = controllerSeleccion.poblacionInicialAbejas(5);
        archivos.escribirArchivoInformacionGeneraciones(flores, abejas);

    }
}
