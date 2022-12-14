/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxcuponex;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Dell
 */
public class FXMLAdminModSucursalesController implements Initializable {

    @FXML
    private TableView<?> tbSucursales;
    @FXML
    private TextField tfBusquedaSucursal;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void clickEditar(ActionEvent event) {
    }

    @FXML
    private void clickEliminar(ActionEvent event) {
    }

    @FXML
    private void clickAgregar(ActionEvent event) {
            try{
            Parent vista = FXMLLoader.load(getClass().getResource("FXMLModSucursarles.fxml"));
            Scene escenaFormulario = new Scene(vista);
            Stage escenarioFormulario = new Stage();
            escenarioFormulario.setScene(escenaFormulario);
            escenarioFormulario.initModality(Modality.APPLICATION_MODAL);
            escenarioFormulario.showAndWait();
        } catch(Exception e){
            
        }
    }
    
}
