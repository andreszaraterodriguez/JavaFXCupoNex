/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxcuponex;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Dell
 */
public class FXMLPrincipalController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void clickModAdministrador(ActionEvent event) {
        try{
             Parent vistaModAdmin = FXMLLoader.load(getClass().getResource("FXMLAdminModAdmin.fxml"));
             Scene scenaAdmin = new Scene(vistaModAdmin);
             Stage scenarioAdmin = new Stage();
             scenarioAdmin.setScene(scenaAdmin);
             scenarioAdmin.initModality(Modality.APPLICATION_MODAL);
             scenarioAdmin.showAndWait();
        } catch(IOException ex){
            
        }
    }

    @FXML
    private void clickModEmpresas(ActionEvent event) {
        try{
            Parent vistaModEmpresa = FXMLLoader.load(getClass().getResource("FXMLAdminModEmpresa.fxml"));
            Scene scenaAdmin = new Scene(vistaModEmpresa);
            Stage scenarioAdmin = new Stage();
            scenarioAdmin.setScene(scenaAdmin);
            scenarioAdmin.initModality(Modality.APPLICATION_MODAL);
            scenarioAdmin.showAndWait();
        }catch(IOException ex){
            
        }
    }

    @FXML
    private void clickModSucursales(ActionEvent event) {
         try{
            Parent vistaModSucursal = FXMLLoader.load(getClass().getResource("FXMLAdminModSucursales.fxml"));
            Scene scenaAdmin = new Scene(vistaModSucursal);
            Stage scenarioAdmin = new Stage();
            scenarioAdmin.setScene(scenaAdmin);
            scenarioAdmin.initModality(Modality.APPLICATION_MODAL);
            scenarioAdmin.showAndWait();
        }catch(IOException ex){
            
        }
        
    }

    @FXML
    private void clickModPromociones(ActionEvent event) {
         try{
            Parent vistaModPromociones = FXMLLoader.load(getClass().getResource("FXMLAdminModPromociones.fxml"));
            Scene scenaAdmin = new Scene(vistaModPromociones);
            Stage scenarioAdmin = new Stage();
            scenarioAdmin.setScene(scenaAdmin);
            scenarioAdmin.initModality(Modality.APPLICATION_MODAL);
            scenarioAdmin.showAndWait();
        }catch(IOException ex){
            ex.printStackTrace();
        }
    }
    
    

   
    
    
}
