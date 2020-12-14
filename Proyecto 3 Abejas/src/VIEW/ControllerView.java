package VIEW;


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
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import javax.swing.text.html.ImageView;
import java.awt.*;


public class ControllerView {
    //Atributos
    private int gen = 0;

    //Ventana Principal
    @FXML private RadioButton radioAbeja;
    @FXML private RadioButton radioFlor;

    //Abejas
    @FXML private ComboBox abejasGenCB;
    @FXML private Button verAbGenB;
    @FXML private Pane caracteristicas;
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



    @FXML
    void cerrar (MouseEvent event){
        Node source = (Node) event.getSource();
        Stage stageActual = (Stage) source.getScene().getWindow();
        stageActual.close();
        System.exit(0);
    }

    @FXML
    void soloUnoA(ActionEvent event){
        radioFlor.setSelected(false);
    }

    @FXML
    void soloUnoF(ActionEvent event){
        radioAbeja.setSelected(false);
    }

    @FXML
    void seleccionar(ActionEvent event){
        if(radioAbeja.isSelected()) {
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
        else if(radioFlor.isSelected()){
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
                System.out.println(getClass());
                exception.printStackTrace();
                System.out.println("no se pudo");
            }
        }
    }

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

    @FXML
    void selecAbeja(ActionEvent event){
        verAbGenB.setDisable(false);
        abejasGenCB.setDisable(false);
    }

    @FXML
    void caracteristicas(ActionEvent event){
        caracteristicas.setVisible(true);
        panal.setVisible(false);

    }

    @FXML
    void siguienteGen(ActionEvent event){
        numGenCFL.setText(String.valueOf(gen));
        if (gen!=0){
            gen ++;
        }
        if(gen == 0){
            gen ++;
            int y = 125;
            for(int i = 0; i < 50; i++) {
                int x = 50;
                y = y + 15;
                for (int  j = 0; j < 100; j++){
                    Rectangle rectangle = new Rectangle(15, 15);
                    rectangle.setX(x);
                    rectangle.setY(y);
                    rectangle.setStroke(Color.rgb(37,180,3));
                    rectangle.setFill(Color.rgb(37,180,3));
                    x = x + 15;
                    campoFlores.getChildren().add(rectangle);
                }
            }

        }

    }

    @FXML
    void anterioresGen(ActionEvent event){
        gen --;
        numGenCFL.setText(String.valueOf(gen));
    }



}
