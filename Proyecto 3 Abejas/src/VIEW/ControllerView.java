package VIEW;


import CONTROLLER.ControllerArchivos;
import MODEL.Abeja;
import MODEL.Flor;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.swing.text.html.ImageView;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;


public class ControllerView {
    ControllerArchivos controllerArchivos = ControllerArchivos.getInstance();

    //Atributos
    private int gen = 0;
    private ArrayList<Rectangle> campoFloresMatriz = new ArrayList<>();
    private int cantGen;
    private ArrayList<ArrayList<Flor>> flores;
    private ArrayList<Abeja> abejasSeleccionadas;



    //Ventana Principal
    @FXML private RadioButton radioAbeja;
    @FXML private RadioButton radioFlor;
    @FXML private ComboBox simulacionesCB;

    //Abejas
    @FXML private ComboBox abejasGenCB;
    @FXML private Button verAbGenB;
    @FXML private Pane caracteristicas;
    @FXML private ComboBox<String> generacionAbeja;
    @FXML private Label madreL;
    @FXML private Label padreL;
    @FXML private Label adaptabilidadL;
    @FXML private Label cromoL;
    @FXML private Label colorL;
    @FXML private Label direccL;
    @FXML private Label anguL;
    @FXML private Label distL;
    @FXML private Label piL;
    @FXML private Label ordenL;
    @FXML private ImageView flecha;
    @FXML private Pane panal;

    //Flores
    @FXML private Label numGenCFL;
    @FXML private Pane campoFlores;
    @FXML private ImageView panalV;
    @FXML private ComboBox<String> generacionesFlores;


    //Métodos generales
    @FXML
    void cerrar (MouseEvent event){
        Node source = (Node) event.getSource();
        Stage stageActual = (Stage) source.getScene().getWindow();
        stageActual.close();
        System.exit(0);
    }
    //Métodos interfaz

    //Ventana principal

    @FXML
    void soloUnoA(ActionEvent event){
        radioFlor.setSelected(false);
        cargarSimulaciones();
    }

    @FXML
    void soloUnoF(ActionEvent event){
        radioAbeja.setSelected(false);
        cargarSimulaciones();
    }

    @FXML
    void seleccionar(ActionEvent event) throws IOException {
        if(radioAbeja.isSelected() && !simulacionesCB.getSelectionModel().isEmpty()) {
            cargarGeneracion();
            Node source = (Node) event.getSource();
            Stage stageActual = (Stage) source.getScene().getWindow();
            stageActual.close();
            try{
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FXMLS/caracteristicasAbejas.fxml"));
                Parent root = fxmlLoader.load();
                Stage stage = new Stage();
                stage.initStyle(StageStyle.TRANSPARENT);
                stage.setTitle("Campo Flores");
                stage.setScene(new Scene(root));
                stage.show();
            }
            catch (Exception e){
                System.out.println("Algo salió mal");
            }
        }
        else if(radioFlor.isSelected() && !simulacionesCB.getSelectionModel().isEmpty()){
            cargarGeneracion();
            Node source = (Node) event.getSource();
            Stage stageActual = (Stage) source.getScene().getWindow();
            stageActual.close();
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FXMLS/campoFlores.fxml"));
                Parent root = fxmlLoader.load();
                Stage stage = new Stage();
                stage.initStyle(StageStyle.TRANSPARENT);
                stage.setTitle("CAMPO FLORES");
                stage.setScene(new Scene(root));
                stage.show();

            }
            catch (Exception exception){
                exception.printStackTrace();
                System.out.println("no se pudo");
            }
        }
    }

    //Abejas

    @FXML
    void cargarCB(MouseEvent event){
        ArrayList<ArrayList<Abeja>> abejas = controllerArchivos.getAbejasArchivo();
        ObservableList<String> generaciones = FXCollections.observableArrayList();
        for(int i = 0; i<abejas.size(); i++){
            generaciones.add("Generación # " + i);
        }
        generacionAbeja.setItems(generaciones);
    }

    @FXML
    void selecAbeja(ActionEvent event){
        if(!generacionAbeja.getSelectionModel().isEmpty()) {
            verAbGenB.setDisable(false);
            abejasGenCB.setDisable(false);
            ObservableList<String> abejas = FXCollections.observableArrayList();
            String genSel = generacionAbeja.getSelectionModel().getSelectedItem();
            String[] sel = genSel.split(" ");
            Integer genNum = Integer.parseInt(sel[2]);
            abejasSeleccionadas = controllerArchivos.getAbejasArchivo().get(genNum);
            for(Abeja abeja: abejasSeleccionadas){
                int id = abeja.getID();
                String mostrar = "Abeja " + id;
                abejas.add(mostrar);
            }
            abejasGenCB.setItems(abejas);
        }
    }

    @FXML
    void caracteristicas(ActionEvent event){
        if(!abejasGenCB.getSelectionModel().isEmpty()) {
            caracteristicas.setVisible(true);
            panal.setVisible(false);
            String sel = abejasGenCB.getSelectionModel().getSelectedItem().toString();
            String[] abSel = sel.split(" ");
            Integer abId = Integer.parseInt(abSel[1]);
            Abeja abeja = new Abeja();
            for(Abeja a: abejasSeleccionadas){
                if(a.getID() == abId){
                    abeja = a;
                }
            }
            madreL.setText("Abeja " + abeja.getPadres().get(0));
            padreL.setText("Abeja " + abeja.getPadres().get(1));
            DecimalFormat nombre=new DecimalFormat("#.00");
            adaptabilidadL.setText(nombre.format(abeja.getPuntaje()) + " puntos");
            String crom = "";
            for(Integer c: abeja.getCromosomas()){
                crom = crom + c;
            }
            cromoL.setText(crom);
            String color = "";
            switch (abeja.getFlor().getColor()){
                case YELLOW: color = "Amarillo"; break;
                case VIOLET: color = "Morado"; break;
                case WHITE: color = "Blanco"; break;
                case GREEN: color = "Verde"; break;
                case CYAN: color = "Cian"; break;
                case BLUE: color = "Azul"; break;
                case RED: color = "Rojo"; break;
                case PINK: color = "Rosa"; break;
            }
            colorL.setText(color);
            direccL.setText(abeja.getDireccionFav().name());
            anguL.setText(abeja.getBusqueda().getAnguloDesviacion() + "grados");
            distL.setText(abeja.getBusqueda().getDistanciaMaxima() + "KM");
            String puntoI;
            if(abeja.getBusqueda().getRecorrido().isPuntoInicio())
                puntoI = "Panal";
            else
                puntoI = "Lejos";
            piL.setText(puntoI);
            ordenL.setText(abeja.getBusqueda().getRecorrido().getOrden().name());
        }
    }

    @FXML
    void atrasAbejas(MouseEvent event){
        Node source = (Node) event.getSource();
        Stage stageActual = (Stage) source.getScene().getWindow();
        stageActual.close();

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FXMLS/venPrincipal.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = new Stage();
            stage.initStyle(StageStyle.TRANSPARENT);
            stage.setTitle("Campo Flores");
            stage.setScene(new Scene(root));
            stage.show();
        }
        catch (Exception exception){
            System.out.println(getClass());
            exception.printStackTrace();
            System.out.println("no se pudo");
        }

    }

    //Campo flores
    @FXML
    void atrasCampo(MouseEvent event){
        Node source = (Node) event.getSource();
        Stage stageActual = (Stage) source.getScene().getWindow();
        stageActual.close();

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FXMLS/venPrincipal.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = new Stage();
            stage.initStyle(StageStyle.TRANSPARENT);
            stage.setTitle("Campo Flores");
            stage.setScene(new Scene(root));
            stage.show();
        }
        catch (Exception exception){
            System.out.println(getClass());
            exception.printStackTrace();
            System.out.println("no se pudo");
        }

    }

    @FXML
    void selecCBGen(ActionEvent event){
        String selG = generacionesFlores.getSelectionModel().getSelectedItem();
        String[] sel = selG.split(" ");
        Integer genSel = Integer.parseInt(sel[2]);
        gen = genSel;
        numGenCFL.setText(String.valueOf(gen));
        limpiarCampo();
        cargarCampoFlores();
    }

    @FXML
    void siguienteGen(ActionEvent event){
        if(gen>=cantGen-1){
            gen = cantGen-1;
        }
        else
            gen++;
        numGenCFL.setText(String.valueOf(gen));
        limpiarCampo();
        cargarCampoFlores();
    }

    @FXML
    void anterioresGen(ActionEvent event){
        if(gen<=0){
            gen = 0;
        }
        else{
            gen --;
        }
        numGenCFL.setText(String.valueOf(gen));
        limpiarCampo();
        cargarCampoFlores();
    }

    @FXML
    void cargarMatrizIncial(MouseEvent event){
        cargarCBFlores();
        int y = 125;
        int id = 0;
        for(int i = 0; i < 50; i++) {
            int x = 50;
            y = y + 15;
            for (int  j = 0; j < 100; j++){
                Rectangle rectangle = new Rectangle(15, 15);
                rectangle.setX(x);
                rectangle.setY(y);
                rectangle.setFill(Color.TRANSPARENT);
                rectangle.setId(String.valueOf(id));
                x = x + 15;
                id++;
                campoFlores.getChildren().add(rectangle);
                campoFloresMatriz.add(rectangle);
            }
        }
    }

    //Métodos para interfaz

    void cargarSimulaciones(){
        try {
            ObservableList<String> simulaciones = controllerArchivos.leerSimulaciones();
            simulacionesCB.setItems(simulaciones);
            simulacionesCB.getItems().remove(0);
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    void cargarCBFlores(){
        flores = controllerArchivos.getFloresArchivo();
        ObservableList<String> generacion = FXCollections.observableArrayList();
        for(int i = 0; i < flores.size(); i++){
            generacion.add("Generacion # "+ i);
        }
        generacionesFlores.setItems(generacion);
        cantGen = flores.size();

    }

    public void cargarCampoFlores(){
        for(Flor flor: flores.get(gen)){
            for(Rectangle rec: campoFloresMatriz){
                if(String.valueOf(flor.getId()).equals(rec.getId())){
                    rec.setStroke(Color.ORANGE);
                    rec.setStrokeWidth(3);
                    switch (flor.getColor()){
                        case PINK: rec.setFill(Color.DEEPPINK); break;
                        case RED: rec.setFill(Color.RED); break;
                        case BLUE: rec.setFill(Color.BLUE); break;
                        case CYAN: rec.setFill(Color.CYAN); break;
                        case GREEN: rec.setFill(Color.GREEN); break;
                        case WHITE: rec.setFill(Color.WHITE); break;
                        case VIOLET: rec.setFill(Color.PURPLE); break;
                        case YELLOW: rec.setFill(Color.YELLOW); break;

                    }

                }
            }
        }
    }

    public void limpiarCampo(){
        for(Rectangle rectangle: campoFloresMatriz){
            if(!rectangle.getFill().equals(Color.TRANSPARENT)){
                rectangle.setFill(Color.TRANSPARENT);
                rectangle.setStroke(Color.TRANSPARENT);
            }
        }
    }

    public void cargarGeneracion() throws IOException {
        String sim = simulacionesCB.getSelectionModel().getSelectedItem().toString();
        String[] sims = sim.split(" ");
        int simulacion = Integer.parseInt(sims[2]);
        controllerArchivos.leerArchivoGeneraciones(simulacion);
    }

}
