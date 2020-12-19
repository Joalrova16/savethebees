package CONTROLLER;

import MODEL.*;
import com.sun.org.apache.xerces.internal.impl.xpath.regex.Match;
import com.sun.org.apache.xpath.internal.operations.Or;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.regex.*;

public class ControllerArchivos {

    private static ControllerArchivos controllerArchivos;
    private ControllerNaturaleza controllerNaturaleza = ControllerNaturaleza.getInstance();
    private ArrayList<ArrayList<Abeja>> abejasArchivo = new ArrayList<>();
    private ArrayList<ArrayList<Flor>> floresArchivo = new ArrayList<>();


    public ControllerArchivos(){}

    public static ControllerArchivos getInstance(){
        if(controllerArchivos == null){
            controllerArchivos = new ControllerArchivos();
        }
        return controllerArchivos;
    }

    public ArrayList<ArrayList<Abeja>> getAbejasArchivo() {
        return abejasArchivo;
    }

    public void setAbejasArchivo(ArrayList<ArrayList<Abeja>> abejasArchivo) {
        this.abejasArchivo = abejasArchivo;
    }

    public ArrayList<ArrayList<Flor>> getFloresArchivo() {
        return floresArchivo;
    }

    public void setFloresArchivo(ArrayList<ArrayList<Flor>> floresArchivo) {
        this.floresArchivo = floresArchivo;
    }

    public void escribirArchivoInformacionGeneraciones(ArrayList<ArrayList<Flor>> flores, ArrayList<ArrayList<Abeja>> abejas) throws IOException {
        System.out.println(abejas.size());
        System.out.println(abejas.get(0).size());
        int simAct = archivoNumSimulacion();
        FileWriter fileWriter = new FileWriter("src/ARCHIVOS/Generaciones_simulacionesNumero_"+ simAct +".txt");
        PrintWriter printWriter = new PrintWriter(fileWriter);
        printWriter.println("FLORES");
        printWriter.println("*****************************************");
        printWriter.println("<");
        int i = 0;
        for (ArrayList<Flor> generacion: flores) {
            printWriter.println((i+1));
            printWriter.println("¿");
            for(Flor flor: generacion){
                printWriter.println(flor.getId() + "--" + flor.getColor() + "--" + flor.getPunto().get(0) + "--" + flor.getPunto().get(1));
            }
            printWriter.println("?");
            i++;
        }
        printWriter.println(">");
        printWriter.println("ABEJAS");
        printWriter.println("*****************************************");
        printWriter.println("#");
        i = 0;
        for(ArrayList<Abeja> gen: abejas) {
            printWriter.println((i+1));
            printWriter.println("¡");
            for (Abeja abeja : gen) {
                printWriter.println(abeja.getID() + "~" + abeja.getPadres().get(0) + "~" + abeja.getPadres().get(1)
                        + "~" + abeja.getPuntaje() + "~" + abeja.getCromosomas() + "~" + abeja.getFlor().getColor() + "~"
                        + abeja.getDireccionFav() + "~" + abeja.getBusqueda().getAnguloDesviacion() + "~" + abeja.getBusqueda().getDistanciaMaxima()
                        + "~" + abeja.getBusqueda().getRecorrido().isPuntoInicio() + "~" + abeja.getBusqueda().getRecorrido().getOrden());
            }
            printWriter.println("!");
            i++;
        }
        printWriter.println("#");
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
            reader.readLine();
            while ((cadena = reader.readLine())!=null){
                sims.add(cadena);
            }
            ultimaSim = Integer.parseInt(sims.get(sims.size()-1))+1;
            FileWriter fileWriter = new FileWriter("src/ARCHIVOS/Simulaciones.txt", true);
            PrintWriter printWriter = new PrintWriter(fileWriter);
            printWriter.println((ultimaSim));
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

    public ObservableList<String> leerSimulaciones() throws IOException {

        File file = new File("src/ARCHIVOS/Simulaciones.txt");
        FileReader fileReader = new FileReader(file);
        BufferedReader reader = new BufferedReader(fileReader);
        ObservableList<String> numeroSims = FXCollections.observableArrayList();
        String cadena;
        while((cadena = reader.readLine())!=null){
            numeroSims.add("Simulación # "+ cadena);
        }
        return numeroSims;
    }

    public void leerArchivoGeneraciones(int simulacion) throws IOException {
        String nombreAr = "src/ARCHIVOS/Generaciones_simulacionesNumero_"+ simulacion +".txt";
        File file = new File(nombreAr);
        FileReader fileReader = new FileReader(file);
        BufferedReader reader = new BufferedReader(fileReader);

        String linea;

        while((linea=reader.readLine())!=null){
            if(linea.equals("<")){
                while(!(linea = reader.readLine()).equals(">")){
                    if(linea.equals("¿")){
                        ArrayList<Flor> floresL = new ArrayList<>();
                        while(!(linea = reader.readLine()).equals("?")){
                            String[] caract = linea.split("--");
                            Flor flor = new Flor();
                            flor.setId(Integer.parseInt(caract[0]));
                            flor.setColor(Color.valueOf(caract[1]));
                            flor.setPunto(new ArrayList<>(Arrays.asList(Integer.parseInt(caract[2]), Integer.parseInt(caract[3]))));
                            floresL.add(flor);
                        }
                        floresArchivo.add(floresL);
                    }
                }
            }
            if(linea.equals("#")){
                while (!(linea = reader.readLine()).equals("#")){
                    if(linea.equals("¡")){
                        ArrayList<Abeja> abejas = new ArrayList<>();
                        while (!(linea = reader.readLine()).equals("!")){
                            String[] caract = linea.split("~");
                            Abeja abeja = new Abeja();
                            abeja.setID(Integer.parseInt(caract[0]));
                            abeja.setPadres(new ArrayList<>(Arrays.asList(Integer.parseInt(caract[1]), Integer.parseInt(caract[2]))));
                            abeja.setPuntaje(Double.parseDouble(caract[3]));
                            String crom = caract[4];
                            crom = crom.replace("[", "");
                            crom = crom.replace("]", "");
                            String[] croms = crom.split(",");
                            ArrayList<Integer> cromosoma = new ArrayList<>();
                            for(String c: croms){
                                c = c.replace(" ", "");
                                cromosoma.add(Integer.parseInt(c));
                            }
                            abeja.setCromosomas(cromosoma);
                            Flor f = new Flor();
                            f.setColor(Color.valueOf(caract[5]));
                            abeja.setFlor(f);
                            abeja.setDireccionFav(Direccion.valueOf(caract[6]));
                            Busqueda bus = new Busqueda();
                            bus.setAnguloDesviacion(Integer.parseInt(caract[7]));
                            bus.setDistanciaMaxima(Integer.parseInt(caract[8]));
                            Recorrido re = new Recorrido();
                            re.setPuntoInicio(Boolean.getBoolean(caract[9]));
                            re.setOrden(Orden.valueOf(caract[10]));
                            bus.setRecorrido(re);
                            abeja.setBusqueda(bus);
                            abejas.add(abeja);
                        }
                        abejasArchivo.add(abejas);
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        ControllerArchivos archivos = ControllerArchivos.getInstance();
        ControllerNaturaleza naturaleza = new ControllerNaturaleza();
        ControllerCodificar codificar = ControllerCodificar.getInstance();
        ArrayList<ArrayList<Abeja>> abejas = new ArrayList<>();
        ArrayList<ArrayList<Flor>> flores = new ArrayList<>();
        codificar.setListas();
        for(int i = 0; i<5; i++){
            abejas.add(naturaleza.poblacionInicialAbejas(10));
            flores.add(naturaleza.poblacionInicialFlores(50));
        }
        archivos.escribirArchivoInformacionGeneraciones(flores, abejas);
//        archivos.leerArchivoGeneraciones(0);
    }
}
