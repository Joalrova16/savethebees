package CONTROLLER;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


public class ControllerView {
    @FXML private Button cerrarB;
    @FXML private Pane InformacionGeneracion;
    @FXML private Pane caractAbeja;
    @FXML private Pane flores;
    @FXML private Pane ventanaPrincipal;
    @FXML private RadioButton radioAbeja;
    @FXML private RadioButton radioFlor;


    @FXML
    void cerrar (ActionEvent event){
        Stage stage = (Stage) cerrarB.getScene().getWindow();
        stage.close();
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
            ventanaPrincipal.setVisible(false);
            InformacionGeneracion.setVisible(true);
        }
        else if(radioFlor.isSelected()){
            ventanaPrincipal.setVisible(false);
            flores.setVisible(true);
        }
    }

    @FXML
    void seleccionarGeneracion(ActionEvent event){
        InformacionGeneracion.setVisible(false);
        caractAbeja.setVisible(true);
    }

    @FXML
    void atrasAbejas(ActionEvent event){
        ventanaPrincipal.setVisible(true);
        InformacionGeneracion.setVisible(false);
        radioAbeja.setSelected(false);
    }

    @FXML
    void atrasFlores(ActionEvent event){
        ventanaPrincipal.setVisible(true);
        flores.setVisible(false);
        radioFlor.setSelected(false);
    }

    @FXML
    void atrasAbCaract(ActionEvent event){
        InformacionGeneracion.setVisible(true);
        caractAbeja.setVisible(false);
    }

}
