package CONTROLLER;

import MODEL.Abeja;
import MODEL.Flor;

import java.io.*;
import java.util.ArrayList;

public class ControllerArchivos {

    public ControllerArchivos() {}

    public void escribirArchivoInformacionGeneraciones(ArrayList<ArrayList<Flor>> flores, ArrayList<ArrayList<Abeja>> abejas) throws IOException {
        int simAct = archivoNumSimulacion()+1;
        FileWriter fileWriter = new FileWriter("src/ARCHIVOS/Generaciones_simulacionesNumero_"+ simAct +".txt");
        PrintWriter printWriter = new PrintWriter(fileWriter);
        printWriter.println("FLORES");
        printWriter.println("*****************************************");
        int i = 0;
        for (ArrayList<Flor> generacion: flores) {
            printWriter.println((i+1));
            for(Flor flor: generacion){
                printWriter.println(flor.getColor() + "--" + flor.getPunto().get(0) + "--" + flor.getPunto().get(1));
            }
            i++;
        }
        printWriter.println("ABEJAS");
        printWriter.println("*****************************************");
        i = 0;
        for(ArrayList<Abeja> gen: abejas) {
            printWriter.println((i+1));
            for (Abeja abeja : gen) {
                printWriter.println(abeja.getPadres().get(0) + "~" + abeja.getPadres().get(1)
                        + "~" + abeja.getAdapatablidad() + "~" + abeja.getCromosomas() + "~" + abeja.getFlor().getColor() + "~"
                        + abeja.getDireccionFav() + "~" + abeja.getBusqueda().getAnguloDesviacion() + "~" + abeja.getBusqueda().getDistanciaMaxima()
                        + "~" + abeja.getBusqueda().getRecorrido().isPuntoInicio() + "~" + abeja.getBusqueda().getRecorrido().getOrden());
            }
            i++;
        }
        printWriter.println("*****************************************");

        fileWriter.close();
    }

    public Integer archivoNumSimulacion() throws IOException{
        int ultimaSim;
        try {
            String cadena;
            ArrayList<String> sims = new ArrayList<>();
            File archivo = new File("src/ARCHIVOS/Simulaciones.txt");
            FileReader fileReader = new FileReader(archivo);
            BufferedReader reader = new BufferedReader(fileReader);
            while ((cadena = reader.readLine())!=null){
                sims.add(cadena);
            }
            ultimaSim = Integer.parseInt(sims.get(sims.size()-1));
            FileWriter fileWriter = new FileWriter("src/ARCHIVOS/Simulaciones.txt", true);
            PrintWriter printWriter = new PrintWriter(fileWriter);
            printWriter.println((ultimaSim+1));
            fileWriter.close();
        }
        catch (FileNotFoundException e) {
            FileWriter fileWriter = new FileWriter("src/ARCHIVOS/Simulaciones.txt");
            PrintWriter printWriter = new PrintWriter(fileWriter);
            printWriter.println("SIMULACIONES");
            printWriter.println(0);
            fileWriter.close();
            ultimaSim = 0;
        }
        return ultimaSim;

    }

    public static void main(String[] args) throws IOException {
        ControllerArchivos archivos = new ControllerArchivos();
        ControllerNaturaleza naturaleza = new ControllerNaturaleza();
        ControllerCodificar.getInstance().setListas();
        ArrayList<Abeja> abejas = naturaleza.poblacionInicialAbejas(20);
        ArrayList<Abeja> abejas2 = naturaleza.poblacionInicialAbejas(20);
        ArrayList<Abeja> abejas3 = naturaleza.poblacionInicialAbejas(20);
        ArrayList<ArrayList<Abeja>> abejasTotales = new ArrayList<>();
        abejasTotales.add(abejas); abejasTotales.add(abejas2); abejasTotales.add(abejas3);
        ArrayList<Flor> flores = naturaleza.poblacionInicialFlores(10);
        ArrayList<Flor> flores2 = naturaleza.poblacionInicialFlores(10);
        ArrayList<Flor> flores3 = naturaleza.poblacionInicialFlores(10);
        ArrayList<ArrayList<Flor>> floresTotales = new ArrayList<>();
        floresTotales.add(flores); floresTotales.add(flores2); floresTotales.add(flores3);
        archivos.escribirArchivoInformacionGeneraciones(floresTotales, abejasTotales);
    }
}
